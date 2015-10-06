<%@page contentType="text/html; charset=UTF-8"
        import="oracle.portal.provider.v2.render.PortletRenderRequest"
        import="oracle.portal.provider.v2.http.HttpCommonConstants"
        import="java.util.ResourceBundle"
        import="java.lang.String "
        import= "oracle.portal.provider.v2.url.UrlUtils"
        import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil"
        import="java.sql.*"
        import="oracle.portal.utils.NameValue"
        import= "PersonalInfo.UserPersonalInfo"

%>
<%@ page isThreadSafe="false" %>
<%@ page errorPage="errorPage.jsp" %>
<%
    
    Connection  conn         = null;
    Statement   stmt         = null;
    ResultSet   rsQuery      = null;
    String      strQuery     = null;
 
    Statement   stmtStaff         = null;
    ResultSet   rsQueryStaff      = null;
    String      strQueryStaff     = null;
 
//response.sendRedirect("serviceNotAvailable.html");
   PortletRenderRequest pReq = (PortletRenderRequest)
      request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);


  
// getting the language setting  
  String strlang= pReq.getLocale().toString();
 
String paramNameNextPage     = "next_page";
   String qualParamNameNextPage = HttpPortletRendererUtil.portletParameter(pReq, paramNameNextPage);



  String paramAcademicYearValue =  pReq.getQualifiedParameter("paramAcademicYear"); 
  String paramValidSurveyValue  =  pReq.getQualifiedParameter("paramValidSurvey"); 

  ResourceBundle rb;
int year = 0;
String question="";
String statementInfo = "";
rb = ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
//create new instance for the resource bundle
if (null != paramAcademicYearValue)
 { year= Integer.parseInt(paramAcademicYearValue.substring(0,2));
  if (year >10)
     {
        //  rb = ResourceBundle.getBundle("TeachingSurvey2ResourceBundle",pReq.getLocale());
          question= "Q15";
          statementInfo = rb.getString("TSR_Statement15");
      }
  else
      { // rb = ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
         question= "Q16";
         statementInfo = rb.getString("TSR_Statement16");
      } 
  }
// getting username
 // 
   String userDn = pReq.getUser().getUserDN(); 
   String ou = userDn.substring(userDn.indexOf(",")+1, userDn.indexOf(",",userDn.indexOf(",")+1));
   

// reads the employee number from OID using the class UserPersonalInfo()
   String           strUserName = pReq.getUser().getName();
   UserPersonalInfo uinfo       = new UserPersonalInfo();
   String           strEmpId="";
   if (strUserName.equals("PORTAL1")) 
        strEmpId="44";
    else          strEmpId    = uinfo.getEmpId(strUserName,ou);
 

   if (strEmpId.equals("NotFound"))
     {
       session.putValue("errorDescription2",rb.getString("ERROR_MSG_NO_EMP_NO"));
%>
       <!-- employee number not found in OID. -->
       <jsp:forward page="errorPage.jsp"> </jsp:forward> 
<%
      }    



  // connect to the siscl database
  javax.naming.InitialContext ic                     = new javax.naming.InitialContext();
  oracle.jdbc.pool.OracleConnectionPoolDataSource ds = (oracle.jdbc.pool.OracleConnectionPoolDataSource)ic.lookup("jdbc/pool/DSTS");
  oracle.jdbc.pool.OraclePooledConnection pc         = (oracle.jdbc.pool.OraclePooledConnection)ds.getPooledConnection();


  conn                                               =  pc.getConnection();
   strQueryStaff= " select  STAFF_ROLE,                          "+
                 "          STAFF_COL,                           "+
                 "          STAFF_DEPT                           "+
                 " from     V_SIS_STAFF                          "+            
                 " where    STAFF_ID = to_number('"+strEmpId+"') ";
   stmtStaff =  conn.createStatement();     
   rsQueryStaff= stmtStaff.executeQuery(strQueryStaff); 
  
  String strStaffRole="";
  String strCol      = "";
  String strDept     = "";
  
 if (rsQueryStaff.next())
  {
        strStaffRole = rsQueryStaff.getString("STAFF_ROLE");
        strCol       = rsQueryStaff.getString("STAFF_COL");
        strDept      = rsQueryStaff.getString("STAFF_DEPT");
  }

if (rsQueryStaff!= null) rsQueryStaff.close();
if (stmtStaff!= null) stmtStaff.close();

if (null != paramValidSurveyValue)
if (paramValidSurveyValue.equals("Y"))
{
 // getting the course and teaching survey ranking result
   strQuery=    " select     r.SEM_CODE                                           "+
                "           ,r.UNI_RANK                                           "+
                "           ,r.COL_RANK                                           "+
                "           ,r.DEPT_RANK                                          "+
                "           ,r.INSTRUCTOR_ID                                      "+
                "           ,get_instructor_name(r.INSTRUCTOR_ID) Instructor_name "+
                "           ,get_course_col_code(SEM_CODE,COURSE_CODE) COL_CODE  "+
                "           ,get_course_dept_name(r.SEM_CODE,r.COURSE_CODE) DEPT_NAME        "+
                "           ,r.COURSE_CODE                                        "+
                "           ,r.SECTNO                                             "+                
                "           ,r.REGISTERED_STD 	REG_STD                           "+  
                "           ,get_std_responses(r.SEM_CODE,r.COURSE_CODE, r.SECTNO,r.INSTRUCTOR_ID,null,null)	  NO_RESPONSE     "+         
                "           ,get_teaching_mean_pctfav(r.SEM_CODE,r.COURSE_CODE, r.SECTNO,r.INSTRUCTOR_ID)	      TEACHING_MEAN   "+        
                "           ,get_teaching_mean_pctfav(r.SEM_CODE,r.COURSE_CODE, r.SECTNO,r.INSTRUCTOR_ID,'Fav') TEACHING_PCTFAV "+
                "           ,get_statement_info(r.SEM_CODE,r.COURSE_CODE, r.SECTNO,r.INSTRUCTOR_ID,'"+question+"') 	    Q_MEAN        "+            
                "           ,get_statement_info(r.SEM_CODE,r.COURSE_CODE, r.SECTNO,r.INSTRUCTOR_ID,'"+question+"','pct') Q_PCTFAV      "+                                                                                   
                " from      COURSE_TEACHING_RANKING     r                         "+
                " where     UNI_RANK is not null                                   "+
                "        and (   ('"+strStaffRole+"' = 'DEAN' and get_course_col_code(r.SEM_CODE,r.COURSE_CODE) = '"+strCol+"') "+
                "             or ('"+strStaffRole+"' = 'HOD' and get_course_dept(r.SEM_CODE,r.COURSE_CODE) = '"+strDept+"'  and '"+strCol+"' != 'LAN')   "+
                "            or (ACCESS_ALL_SURVEYS('"+strEmpId+"')='Y' )           "+              
                "            )                                                     "+
                "        and sem_code = '"+paramAcademicYearValue+"' "+
                " order by  UNI_RANK "; //,COL_RANK,DEPT_RANK DESC                        ";

   stmt =  conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);     
   rsQuery= stmt.executeQuery(strQuery);



rsQuery.beforeFirst();
 if (rsQuery.next())
   {  

%>
<br>
<table border=1>
  <tr>
<%
      if (!(strStaffRole.equals("HOD")))
      {
%>      
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
              <center><%= rb.getString("TSR_UniRank")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
               <center><%= rb.getString("TSR_ColRank")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText">
              <center><%= rb.getString("TSR_DeptRank")%></center></span> </td>    

<%      
      }
%>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_InstructorID")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_InstructorName")%></center>   </span> </td>
            
<td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_College")%></center>   </span> </td>
<%
      if (!(strStaffRole.equals("HOD")))
      {
%>   
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText">
            <center><%= rb.getString("TSR_Departement")%> </center>  </span> </td>
<%      
      }
%>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_CourseCode")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_sec")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_StudentsNum")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_ResponseNum")%></center></span> </td>
    <td colspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_TeachingItem")%></center></span> </td>
    <td colspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= statementInfo%></center></span> </td>
  </tr>
  <tr>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_Mean")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_PctFav")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_Mean")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_PctFav")%></center></span> </td> 
  </tr>
<% 
   // show the header of the courses list
    rsQuery.beforeFirst();
    
// display the result 
    while (rsQuery.next())
    { 
%>
  <tr>
<%
      if (!(strStaffRole.equals("HOD")))
      {            
%>    
           <td><CENTER><%=rsQuery.getString("UNI_RANK")%></CENTER></td>
           <td><CENTER><%=rsQuery.getString("COL_RANK")%></CENTER></td>
           <td><CENTER><%=rsQuery.getString("DEPT_RANK")%></CENTER></td>
<%
      }
%>
      <td>        
          <a  href="https://sslportal.squ.edu.om/portal/page/portal/SQUPortal_Faculty_Page_Group/instructor_survey_analysis?paramCourseCode=<%=rsQuery.getString("COURSE_CODE")%>&paramSectionNo=<%=rsQuery.getString("SECTNO")%>&paramSemester=<%=rsQuery.getString("SEM_CODE")%>&paramInstructor=<%=rsQuery.getString("INSTRUCTOR_ID")%>"
              target="_blank">
                <%=rsQuery.getString("INSTRUCTOR_ID")%>
          </a>
      </td>
      <td><%=rsQuery.getString("Instructor_name")%> </td>
      <td><%=rsQuery.getString("COL_CODE")%> </td>
      
<% 
      if (!(strStaffRole.equals("HOD")))
      { 
%>   
       <td><%=rsQuery.getString("DEPT_NAME")%></CENTER></td>
<%
     }
 %>
      <td><CENTER><%=rsQuery.getString("COURSE_CODE")%></CENTER></td>
      <td><CENTER><%=rsQuery.getString("SECTNO")%></CENTER></td> 
      <td><CENTER><%=rsQuery.getString("REG_STD")%></CENTER></td>
      <td><CENTER><%=rsQuery.getString("NO_RESPONSE")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("TEACHING_MEAN")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("TEACHING_PCTFAV")%></CENTER></td>  
      <td><CENTER><%=rsQuery.getString("Q_MEAN")%></CENTER></td> 
      <td><CENTER><%=rsQuery.getString("Q_PCTFAV")%></CENTER></td>   
    
     
 </tr>
<%       

    } // end while loop
%>

</table>    

<% 
    } //end of if the COURSE ARE VALID
     
}
else
{
//begining of  not valid surveys section
strQuery=" select    SEM_CODE                                             "+
                "           ,get_course_col_code(SEM_CODE,COURSE_CODE) COL_CODE  "+
                "           ,get_course_dept_name(SEM_CODE,COURSE_CODE) DEPT_NAME "+
                "           ,get_course_dept(SEM_CODE,COURSE_CODE) DEPT_CODE      "+
                "           ,COURSE_CODE                                          "+
                "           ,SECTNO                                               "+
                "           ,INSTRUCTOR_ID                                        "+
                "           ,get_instructor_name(INSTRUCTOR_ID) Instructor_name   "+
                "           ,REGISTERED_STD 	REG_STD                             "+  
                "           ,get_std_responses(SEM_CODE,COURSE_CODE, SECTNO,INSTRUCTOR_ID,null,null)	  NO_RESPONSE     "+         
                "           ,get_teaching_mean_pctfav(SEM_CODE,COURSE_CODE, SECTNO,INSTRUCTOR_ID)	      TEACHING_MEAN   "+        
                "           ,get_teaching_mean_pctfav(SEM_CODE,COURSE_CODE, SECTNO,INSTRUCTOR_ID,'Fav') TEACHING_PCTFAV "+
                "           ,get_statement_info(SEM_CODE,COURSE_CODE, SECTNO,INSTRUCTOR_ID,'"+question+"') 	    Q_MEAN        "+            
                "           ,get_statement_info(SEM_CODE,COURSE_CODE, SECTNO,INSTRUCTOR_ID,'"+question+"','pct') Q_PCTFAV      "+                                                                                     
                " from      COURSE_TEACHING_RANKING    r                     "+               
                " where    UNI_RANK is null  "+                
                "        and (   ('"+strStaffRole+"' = 'DEAN' and get_course_col_code(r.SEM_CODE,r.COURSE_CODE) = '"+strCol+"') "+
                "              or ('"+strStaffRole+"' = 'HOD' and get_course_dept(r.SEM_CODE,r.COURSE_CODE) = '"+strDept+"'  and '"+strCol+"' != 'LAN')   "+
                "              or (ACCESS_ALL_SURVEYS('"+strEmpId+"')='Y')           "+              
                "            )                                                     "+
               "         and   SEM_CODE = '"+paramAcademicYearValue+"'     ";                 



   stmt =  conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);     
   rsQuery= stmt.executeQuery(strQuery);

  if (rsQuery.next())
   {  

   // show the header of the courses list
%>
</br><br>
<center>
<table>
   <tr>
        <td class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_invalidTitle")%></center></span> </td>
   </tr>
 </table>
 </center>
<font color="red"><%= rb.getString("TSR_Note_title")%></font>
<br><span class="PortletHeaderText"><%= rb.getString("TSR_InvalidSurvey")%></span></br>
<ul>
    <li><%= rb.getString("TSR_invalid_reason1")%></li>
    <li> <%= rb.getString("TSR_invalid_reason2")%></li>
</ul>
<table border=1>
  <tr><td rowspan="2" class="PortletHeaderColor"> &nbsp; </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_InstructorID")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_InstructorName")%></center>   </span> </td>
       
<td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> 
            <center><%= rb.getString("TSR_College")%></center>   </span> </td>
<%
      if (!(strStaffRole.equals("HOD")))
      {
%>   
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText">
            <center><%= rb.getString("TSR_Departement")%> </center>  </span> </td>
<%      
      }
%> 
   <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_CourseCode")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_sec")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_StudentsNum")%></center></span> </td>
    <td rowspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_ResponseNum")%></center></span> </td>
    <td colspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_TeachingItem")%></center></span> </td>
    <td colspan="2" class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= statementInfo%></center></span> </td>
  </tr>
  <tr>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_Mean")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_PctFav")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_Mean")%></center></span> </td>
    <td class="PortletHeaderColor"><span class="PortletHeaderText"> <center><%= rb.getString("TSR_PctFav")%></center></span> </td> 
  </tr>
<% 

// going back  to the beging ing of the result set
   rsQuery.beforeFirst();

// display the result 
int i=0;
    while (rsQuery.next())
    { 
%>
  <tr><td><%= ++i %></td>
      <td>        
          <a  href="https://sslportal.squ.edu.om/portal/page/portal/SQUPortal_Faculty_Page_Group/instructor_survey_analysis?paramCourseCode=<%=rsQuery.getString("COURSE_CODE")%>&paramSectionNo=<%=rsQuery.getString("SECTNO")%>&paramSemester=<%=rsQuery.getString("SEM_CODE")%>&paramInstructor=<%=rsQuery.getString("INSTRUCTOR_ID")%>"
              target="_blank">
                <%=rsQuery.getString("INSTRUCTOR_ID")%>
          </a>
      </td>
      <td><%=rsQuery.getString("Instructor_name")%> </td>
      <td><%=rsQuery.getString("COL_CODE")%> </td>
<% 
      if (!(strStaffRole.equals("HOD")))
      { 
%>   
       <td><%=rsQuery.getString("DEPT_NAME")%></CENTER></td>
<%
     }
 %>
      <td><CENTER><%=rsQuery.getString("COURSE_CODE")%></CENTER></td>
      <td><CENTER><%=rsQuery.getString("SECTNO")%></CENTER></td> 
      <td><CENTER><%=rsQuery.getString("REG_STD")%></CENTER></td>
      <td><CENTER><%=rsQuery.getString("NO_RESPONSE")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("TEACHING_MEAN")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("TEACHING_PCTFAV")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("Q_MEAN")%></CENTER></td>   
      <td><CENTER><%=rsQuery.getString("Q_PCTFAV")%></CENTER></td>   
 </tr>
<%       

    } // end while loop
%>

</table>    

<% 
    } //end of if the COURSE ARE INVALID
} //end of if the survey is not valid

// going back to TransportaionPortletShowPage.jsp
  NameValue[] paramBackLinkParams = new NameValue[1];
  paramBackLinkParams[0] = new NameValue(
            qualParamNameNextPage, "/htdocs/teaching_survey_reports_portlet/teaching_survey_reports_portletShowPage.jsp"); 

%>

</br>

 <a href="<%=UrlUtils.constructLink(pReq,UrlUtils.PAGE_LINK,
            paramBackLinkParams,true,true) %>"> <%=rb.getString("Back")%> </a> 


</br>

<%

   // closign the resultset and the statment
     
     if (rsQuery!= null) rsQuery.close();
     if (stmt!= null) stmt.close();
       
      if (conn != null) conn.close(); 

%>

