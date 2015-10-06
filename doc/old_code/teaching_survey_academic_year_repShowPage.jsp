<%@page contentType="text/html; charset=UTF-8"
        import="oracle.portal.provider.v2.render.PortletRenderRequest"
        import="oracle.portal.provider.v2.http.HttpCommonConstants"
        import="java.util.ResourceBundle"
        import="java.lang.String "
        import= "oracle.portal.provider.v2.url.UrlUtils"
        import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil"
        import="java.sql.*,
                java.util.Date"
        import="oracle.portal.utils.NameValue"
        import="java.util.ResourceBundle, java.util.Set, java.util.SortedSet, 
                java.util.TreeSet, java.util.HashSet, java.util.Iterator"
        import= "PersonalInfo.UserPersonalInfo"
        import ="java.util.Calendar"
%>

<%@ page errorPage="errorPage.jsp" %>
<%@ page isThreadSafe="false" %>
<%
    
    Connection  conn         = null;
    Statement   stmt         = null;
    ResultSet   rsQuery      = null;
    String      strQuery     = null;
    
    String      strQueryCurrSem =null;
    Statement   stmtCurrSem     = null;
    ResultSet   rsQueryCurrSem  = null;
    
    Statement   stmtAcademicYear         = null;
    ResultSet   rsQueryAcademicYear      = null;
    String      strQueryAcademicYear     = null;
    
 

   PortletRenderRequest pReq = (PortletRenderRequest)
      request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);


//create new instance for the resource bundle
   ResourceBundle rb =
      ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
   
// getting the language setting  
  String strlang= pReq.getLocale().toString();
 
String paramNextPage               = "next_page";
String paramNextPageQualified      = HttpPortletRendererUtil.portletParameter(pReq, paramNextPage);

String ParamSemYear                = "ParamSemYear";
String ParamSemYearQualified       = HttpPortletRendererUtil.portletParameter(pReq,ParamSemYear);
String ParamSemYearValues          = pReq.getQualifiedParameter(ParamSemYear); 

  
// getting username
 // 
   String userDn = pReq.getUser().getUserDN(); 
   String ou = userDn.substring(userDn.indexOf(",")+1, userDn.indexOf(",",userDn.indexOf(",")+1));
   

// reads the employee number from OID using the class UserPersonalInfo()
   String           strUserName = pReq.getUser().getName();
   UserPersonalInfo uinfo       = new UserPersonalInfo();
   String           strEmpId="";
   if (strUserName.equals("PORTAL1")) 
      // strEmpId="4171";
         strEmpId="44";
    else         strEmpId    = uinfo.getEmpId(strUserName,ou);
 
 

   if (strEmpId.equals("NotFound"))
     {
       session.putValue("errorDescription2",rb.getString("ERROR_MSG_NO_EMP_NO"));
%>
       <!-- employee number not found in OID. -->
       <jsp:forward page="errorPage.jsp"> </jsp:forward> 
<%
      }    

try{    
  // connect to the siscl database
  javax.naming.InitialContext ic                     = new javax.naming.InitialContext();
  oracle.jdbc.pool.OracleConnectionPoolDataSource ds = (oracle.jdbc.pool.OracleConnectionPoolDataSource)ic.lookup("jdbc/pool/DSTS");
  oracle.jdbc.pool.OraclePooledConnection pc         = (oracle.jdbc.pool.OraclePooledConnection)ds.getPooledConnection();

  conn                                               =  pc.getConnection();
  
  strQueryCurrSem = " SELECT  course_code,                                           "+
                    "         decode(substr('"+strlang+"',0,2),                      "+
                    "                         'en',crsname,a_crsname) course_name,   "+
                    "         sectno section_no,                                     "+
                    "         seatstaken,                                            "+
                    "         get_std_responses(l_abr_yrsem, course_code, sectno,    "+ 
                    "                            empno, null, null) std_resp,        "+
                    "         l_abr_yrsem   YR_SEM,                                  "+
                    "         empno  empno                                           "+
                    " from    v_course_details  ,                                    "+
/* the following line commented on 23/12/2009 as it was presnenting the 
 next not current semester. This was due to the change of registration time.
*/
//                    "         v_evaluation_yrsem@sis_link.squ.edu.om                 "+
                    "         v_current_yrsem_portal                                 "+
                    " WHERE   empno  = '"+strEmpId+"'                                "+               
                    "   and   (evaluate_course(course_code, crs_type_code)='Y')      "+       
                    "   and   is_semester_evaluated(l_abr_yrsem)='N'                 "+
                       /*
              condition seatstaken added on 15/4/2012 to exclude the course with less than 5 registered students.
             */
                    "       and  seatstaken > 4                                          ";

  stmtCurrSem =  conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);    
  rsQueryCurrSem= stmtCurrSem.executeQuery(strQueryCurrSem);
  
 if (rsQueryCurrSem.next())
   {  
   
   // show the header of the courses list
%>
<br>
<center><%=rb.getString("TSAY_CurrentSem")%>
<table border=1>
  <tr>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("CourseCodeText")%>   </span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("CourseNameText")%>   </span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("SectionNotext")%>    </span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("StudentRegistered")%></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("NumFilledSurvey")%>  </span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <%= rb.getString("NumNotFilledSurvey")%></span></td>
  </tr>
<% 
   
String paramNameNextPage     = "next_page";
   String qualParamNameNextPage = HttpPortletRendererUtil.portletParameter(pReq, paramNameNextPage);

// decalring next page to be used to pass parameters to the next page.
    NameValue[] paramNextPageParams = new NameValue[4];  
    paramNextPageParams[0] = new NameValue(
            qualParamNameNextPage, "/htdocs/teachinganalysissurveyportlet/TeachingAnalysisSurveyPortletShowPage.jsp"); 


    // get the server path
   String strProtocol =request.getProtocol();
   String strServerName= request.getRemoteHost();
   String strServerPath =strProtocol.substring(0,4) +"://"+ strServerName+"/";
  
   int    intFillSurvey = 0;
   int    intNotFillSurvey = 0;

    // going back one step " to the beging ing of the result set"
   rsQueryCurrSem.previous();
      
      // display the result 
    while (rsQueryCurrSem.next())
    { 
%>
  <tr>
<!--
       // construct a hyperlink to the form with paramteres course code
       // section number and semester 
-->
      <td><center><%=rsQueryCurrSem.getString("course_code")%></center></td>
      <td><center><%=rsQueryCurrSem.getString("course_name")%></center></td>
      <td><center><%=rsQueryCurrSem.getString("section_no")%></center></td>
      <td><center><%=rsQueryCurrSem.getString("seatstaken")%></center></td>
<%    
     // getting the number of student who filled or not the course and teach
     // survey.             
       intFillSurvey= Integer.parseInt(rsQueryCurrSem.getString("std_resp"));                 
       intNotFillSurvey =Integer.parseInt(rsQueryCurrSem.getString("seatstaken")) - intFillSurvey;  
%>     
            <td><center><%=intFillSurvey%></center></td>
            <td><center><%=intNotFillSurvey%></center></td>
  </tr>
<%       

    } // end while loop
    
%>

</table>    
</center>
<% 
    } // end displaying the current courses
/*   if (strUserName.equals("RASHDIAS")) 
        strEmpId="44";
       //  strEmpId="4022";
      
      */
      // the following query is to get all analyesed  survey for the previous 
      // academic year
  strQuery = " SELECT  distinct                                                                        "+   
             "               decode( substr(SEM_CODE,3,4)                                              "+
             "                       ,'FL','20'||substr(SEM_CODE,0,2)||'-'||                           "+
             "                              to_char(to_number('20'||substr(SEM_CODE,0,2))+1)           "+ 
             "                       ,'SP', decode(length(to_char(to_number(substr(SEM_CODE,0,2))-1))  "+
             "                                    ,1,'200'||to_char(to_number(substr(SEM_CODE,0,2))-1) "+
             "                                      ,'20'||to_char(to_number(substr(SEM_CODE,0,2))-1)    "+
            // "                                      ,'20'||substr(SEM_CODE,0,2)                        "+
             "                                    )                                                    "+
             "                            ||'-20'||substr(SEM_CODE,0,2)                                "+                                   
             "                      )academic_year                                                     "+
             "         ,SEM_CODE                                                                       "+                              
             "         ,course_code                                                                    "+                                             
             "         ,section_no                                                                     "+                                        
             "         ,INSTRUCTOR_ID                                                                  "+
             " FROM    course_teaching_survey                                                          "+
             " WHERE   INSTRUCTOR_ID ='"+strEmpId+"'                                                   "+
             "     AND is_semester_evaluated(SEM_CODE) ='Y'                                           "+          
            //"      and (SEM_CODE='09SP'  OR INSTRUCTOR_ID =44 )                                                           "+
             //  "    and SEM_CODE != '15SP'         "+ // excluded temporary until this semester is evaluated
             " ORDER BY academic_year DESC,                                                            "+
             "          SEM_CODE DESC                                                                  ";                                                
                  

   stmt =  conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);     
   rsQuery= stmt.executeQuery(strQuery);




    NameValue[] paramTeachSurveySummary = new NameValue[2];
   // paramTeachSurveySummary[0] = new NameValue(
   //       paramNextPageQualified, "/htdocs/teaching_survey_academic_year_rep/teaching_survey_summary.jsp");
       
    String strAcademicYear=null;  
   
    int rows   = (rsQuery.last()) ? rsQuery.getRow() : 0;  // Number of rows
    int cols  = 2000; // Number of columns
    String[][] ArrayAcademicYear = new String[rows][cols];
    int MaxCol= 0;
   
    int rowNum = 0;
    int colNum = 0; 
 rsQuery.beforeFirst();
 if (rsQuery.next())
   { 
   // getting the previous semester i.e. last evaluated semster
   strQueryAcademicYear= " select l_abr_yrsem  year_sem "+
                       //  " from   v_evaluation_yrsem      "; 
                         " from   v_current_yrsem_portal  "; //modified on 12/02/2014
   stmtAcademicYear =  conn.createStatement ();     
   rsQueryAcademicYear= stmtAcademicYear.executeQuery(strQueryAcademicYear);
          
   
   
     strAcademicYear = rsQuery.getString("academic_year");
     ArrayAcademicYear[rowNum][colNum++] = rsQuery.getString("academic_year");
   
    rsQuery.previous();
       while (rsQuery.next()) 
           {     
              // Moving all the data records returned by the query to 2D array
              if (!(strAcademicYear.equals(rsQuery.getString("academic_year"))))
                 {               
                   colNum=0;
                   ArrayAcademicYear[++rowNum][colNum++] = rsQuery.getString("academic_year");                 
                   ArrayAcademicYear[rowNum][colNum++] =    rsQuery.getString("SEM_CODE")+
                                                        "-"+rsQuery.getString("course_code")+
                                                        "/"+rsQuery.getString("section_no")+
                                                        "-"+rsQuery.getString("INSTRUCTOR_ID");      
                 }
              else
                {   
                  ArrayAcademicYear[rowNum][colNum++] =    rsQuery.getString("SEM_CODE")+
                                                        "-"+rsQuery.getString("course_code")+
                                                        "/"+rsQuery.getString("section_no")+
                                                        "-"+rsQuery.getString("INSTRUCTOR_ID"); 
                }
            strAcademicYear = rsQuery.getString("academic_year");  

             if (colNum > MaxCol)
                      MaxCol= colNum;          
            }  // while loop end
   String strSemCode="";
   String strCourseCode="";
   String strSection ="";
   String strInstructorID="";
   
/*   // get the server path
   String strProtocol =request.getProtocol();
   String strServerName= request.getRemoteHost();
   String strServerPath =strProtocol.substring(0,4) +"://"+ strServerName+"/";
   String strTeachSurveySummaryLink=strServerPath + "portal/page/portal/SQUPortal_Faculty_Page_Group/instructor_survey_analysis";
   
   // declaring parameters to be passed to the next page "open end question survey result page".
    NameValue[] paramOpenEndPageParams = new NameValue[5];  
    String strTeachSurveyOpenEndQusLink=strServerPath + "portal/page/portal/SQUPortal_Faculty_Page_Group/open_end_question_survey";
*/


// get the current year
  Calendar c = Calendar.getInstance();
  int intCuurentYear= c.get(Calendar.YEAR);
 
  boolean BoolCurrentAcadYear =false;
  boolean BoolPrevAcadYear=false;
  int  intFirstYear  = Integer.parseInt(ArrayAcademicYear[0][0].substring(0,4));
  int  intSecondYear = Integer.parseInt(ArrayAcademicYear[0][0].substring(5));

  if  ((intCuurentYear == intFirstYear)|| (intCuurentYear== intSecondYear))
        if (rsQueryAcademicYear.next())
           if (rsQueryAcademicYear.getString("year_sem").equals(ArrayAcademicYear[0][1].substring(0,4))) 
                 BoolCurrentAcadYear= true;
           else
                 BoolPrevAcadYear=true;
%>
<div>
<p><%=rb.getString("TSAY_Result")%> </p>
<%   
     for (int row=0; row<=rowNum; row++)    
     {
       if ((BoolCurrentAcadYear) && (row==0))
       {    BoolPrevAcadYear=true;
            BoolCurrentAcadYear=false;
            %><%=rb.getString("TSAY_CuurentAcademicYear")%><%
        }
        else if (BoolPrevAcadYear)
        { 
            BoolPrevAcadYear= false;
            %><%=rb.getString("TSAY_PrevAcademicYear")%><%
        }
%>
<a class="Sem_Yr" onclick="showHide('AcademicYear<%=row%>')"><%=ArrayAcademicYear[row][0]%></a></br>
    <div class="hide" id="AcademicYear<%=row%>">
      <ul>
<%    
       for (int col=1; col<MaxCol; col++)
        {
          if (null != ArrayAcademicYear[row][col])
          {
            strSemCode = ArrayAcademicYear[row][col].substring(0,4);
            strCourseCode= ArrayAcademicYear[row][col].substring(5,13);           
            strSection= ArrayAcademicYear[row][col].substring(14, ArrayAcademicYear[row][col].lastIndexOf("-"));
            strInstructorID= ArrayAcademicYear[row][col].substring( ArrayAcademicYear[row][col].lastIndexOf("-")+1);
%>        <li><%=ArrayAcademicYear[row][col].substring(0,ArrayAcademicYear[row][col].lastIndexOf("-"))%>
               <%
               // construct a hyperlink to the page with paramteres course code
               // section number and semester
            String paramNameNextPage     = "next_page";
            String qualParamNameNextPage = HttpPortletRendererUtil.portletParameter(pReq, paramNameNextPage);
            NameValue[] paramNextPageParams = new NameValue[5];    
            paramNextPageParams[0] = new NameValue(
            qualParamNameNextPage, "/htdocs/teachinganalysissurveyportlet/TeachingAnalysisSurveyPortletShowPage.jsp"); 
    
                

                    paramNextPageParams[1] = new NameValue("paramCourseCode", strCourseCode);
                    paramNextPageParams[2] = new NameValue("paramSectionNo", strSection);
                    paramNextPageParams[3] = new NameValue("paramInstructor", strInstructorID);                    
                    paramNextPageParams[4] = new NameValue("paramSemester", strSemCode);              
                %>         
                (<%=UrlUtils.constructHTMLLink(pReq,UrlUtils.PAGE_LINK,rb.getString("TSAY_SurveySummary"),"",
                                    paramNextPageParams,true,true) %>) -   
                <%
                paramNextPageParams[0] = new NameValue(
                qualParamNameNextPage, "/htdocs/open_end_teaching_survey_result_portlet/Open_end_teaching_survey_result_portlet.jsp");       
                %>
                (<%=UrlUtils.constructHTMLLink(pReq,UrlUtils.PAGE_LINK,rb.getString("TSAY_OpenEnd"),"",
                                    paramNextPageParams,true,true) %>)  
          <!-- <a  href="<%//=strTeachSurveySummaryLink%>?paramCourseCode=<%=strCourseCode%>&paramSectionNo=<%=strSection%>&paramSemester=<%=strSemCode%>&paramInstructor=<%=strInstructorID%>"
              target="_blank"><%=ArrayAcademicYear[row][col].substring(0,ArrayAcademicYear[row][col].lastIndexOf("-"))%></a>
            -->  
              </li>            
<%        }
        }   // end of inner for loop
       
%>     </ul>  
   </div>
  
                 
           
<%



      }// end of outer for loop
%>
</div>

<%

}// end if;
  }
  catch (SQLException exc){
      throw new SQLException ("SQL statement is not executed!");}
finally{

// closing the resultset and the statment
     if (rsQueryCurrSem!= null) rsQueryCurrSem.close();
     if (stmtCurrSem!= null) stmtCurrSem.close();
 

     if (rsQueryAcademicYear!= null) rsQueryAcademicYear.close();
     if (stmtAcademicYear!= null) stmtAcademicYear.close();
 

      if (rsQuery!= null) rsQuery.close();
      if (stmt!= null) stmt.close();
 
 
      if (conn != null) conn.close(); 
}
%>
 

<style type="text/css">

.Sem_Yr{ 
      display: block;
      font-family:Verdana, Arial, Helvetica, sans-serif;
      font-size:12px;
      list-style-position: inside ;
      text-decoration:underline;
      cursor: pointer;
}

.hide{
      display: none;
}

.show{
      display: block;
}
</style>

 <script language="JavaScript" type="text/JavaScript">
<!--
menu_status = new Array();

function showHide(theid){
    if (document.getElementById) {
    var switch_id = document.getElementById(theid);

        if(menu_status[theid] != 'show') {
           switch_id.className = 'show';
           menu_status[theid] = 'show';
        }else{
           switch_id.className = 'hide';
           menu_status[theid] = 'hide';
        }
    }
}

//-->
</script>
