<%@page contentType="text/html; charset=UTF-8"
        import="oracle.portal.provider.v2.render.PortletRenderRequest"
        import="oracle.portal.provider.v2.http.HttpCommonConstants"
        import="java.util.ResourceBundle"
        import="java.lang.String "
        import= "oracle.portal.provider.v2.url.UrlUtils"
        import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil"
        import="java.sql.*"
        import="oracle.portal.utils.NameValue"
        import="oracle.portal.provider.v2.ParameterDefinition"
        import="java.text.DecimalFormat"
        import= "PersonalInfo.UserPersonalInfo"
%>
<%@ page isThreadSafe="false" %>
<%@ page errorPage="errorPage.jsp" %>
<%
    Connection  conn      = null;
    Statement   stmtSurveyAnalysis      = null;
    ResultSet   rsSurveyAnalysis        = null;
    String      strQuerySurveyAnalysis  = null;
    
    Statement   stmtAllowedAccess      = null;
    ResultSet   rsAllowedAccess        = null;    
    String      strQueryAllowedAccess   = null;
    

    Statement   stmtSIS         = null;
    ResultSet   rsQuerySIS      = null;
    String      strQuerySIS     = null;
    
    Statement   stmtClinical    = null;
    ResultSet   rsClinical      = null;
    String      strClinical     = null;
 
 %>

<%
//response.sendRedirect("serviceNotAvailable.html");
   PortletRenderRequest pReq = (PortletRenderRequest)
      request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);



 
 // getting the passed parameter " InstructorViewSurveyShowPage.jsp" to be passed 
// to "insert_survey.jsp" page
ParameterDefinition[] params   = pReq.getPortletDefinition().getInputParameters();  
String   paramCourseCode       = params[0].getName();
String[] paramCourseCodeValue  = pReq.getQualifiedParameterValues(paramCourseCode);
String   paramSectionNo        = params[1].getName();
String[] paramSectionNoValue   = pReq.getQualifiedParameterValues(paramSectionNo);
String   paramInstructor       = params[2].getName();
String[] paramInstructorValue  = pReq.getQualifiedParameterValues(paramInstructor);
String   paramSemester         = params[3].getName();
String[] paramSemesterValue    = pReq.getQualifiedParameterValues(paramSemester);
   

String paramNameNextPage     = "next_page";
   String qualParamNameNextPage = HttpPortletRendererUtil.portletParameter(pReq, paramNameNextPage);

ResourceBundle rb;
int year = 0;   

//create new instance for the resource bundle
if (null != paramSemesterValue[0])
  year =Integer.parseInt(paramSemesterValue[0].substring(0,2));
  if (year >10)
     rb = ResourceBundle.getBundle("TeachingSurvey2ResourceBundle",pReq.getLocale());
  else
      rb = ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
     

  if ((null != paramCourseCodeValue[0])&& (null != paramSectionNoValue[0])
     && (null != paramInstructorValue[0])&& (null != paramSemesterValue[0]))
  {
// getting username
 // 
   String userDn = pReq.getUser().getUserDN(); 
   String ou = userDn.substring(userDn.indexOf(",")+1, userDn.indexOf(",",userDn.indexOf(",")+1));
   

// reads the employee number from OID using the class UserPersonalInfo()
   String           strUserName = pReq.getUser().getName();
   UserPersonalInfo uinfo       = new UserPersonalInfo();
   String           strEmpId    = uinfo.getEmpId(strUserName,ou);

   if (strUserName.equals("PORTAL1")) 
        strEmpId="44";
  

   if (strEmpId.equals("NotFound"))
     {
       session.putValue("errorDescription2",rb.getString("ERROR_MSG_NO_EMP_NO"));
%>
       <!-- employee number not found in OID. -->
       <jsp:forward page="errorPage.jsp"> </jsp:forward> 
<%
      }    
// getting the language setting  
  String strlang= pReq.getLocale().toString();
  
try{
  // connect to the portal DB database
  javax.naming.InitialContext icPortal                     = new javax.naming.InitialContext();
  oracle.jdbc.pool.OracleConnectionPoolDataSource dsPortal = (oracle.jdbc.pool.OracleConnectionPoolDataSource)icPortal.lookup("jdbc/pool/DSTS");
  oracle.jdbc.pool.OraclePooledConnection pcPortal         = (oracle.jdbc.pool.OraclePooledConnection)dsPortal.getPooledConnection();


   conn                                             =  pcPortal.getConnection();
   
   strQueryAllowedAccess  = "  SELECT  EMP_COURSE('"+strEmpId+"',                  "+
                            "                         '"+paramCourseCodeValue[0]+"',"+
                            "                         '"+paramSectionNoValue[0]+"') "+
                            "        ,ACCESS_ALL_SURVEYS('"+strEmpId+"')            "+ 
                            "  FROM DUAL                                            ";
   stmtAllowedAccess = conn.createStatement ();    
   rsAllowedAccess= stmtAllowedAccess.executeQuery(strQueryAllowedAccess);                    

 if (rsAllowedAccess.next())
    if (    (rsAllowedAccess.getString(1).equals("Y")) 
        ||  (rsAllowedAccess.getString(2).equals("Y"))
       )
     {
   strQuerySurveyAnalysis =" SELECT      QUESTION,                              "+
                           "             STR_DISAGREE,                          "+ 
                           "             DISAGREE,                              "+
                           "             AGREE,                                 "+
                           "             STR_AGREE,                             "+
                           "             NOT_APPLICABLE,                        "+
                           "             TOTAL,                                 "+
                           "             PCT_FAV,                               "+
                           "             SECT_MEAN,                             "+ 
                           "             GET_RESPONSE_MEAN( '"+paramSemesterValue[0]+"'   "+
                           "                                ,'"+paramCourseCodeValue[0]+"'"+
                           "                                ,'CRS'                        "+
                           "                                ,QUESTION),                   "+
                           "             GET_RESPONSE_MEAN( '"+paramSemesterValue[0]+"'   "+
                           "                                ,'"+paramCourseCodeValue[0]+"'"+
                           "                                ,'DPT'                        "+
                           "                                ,QUESTION),                   "+
                           "             GET_RESPONSE_MEAN( '"+paramSemesterValue[0]+"'   "+
                           "                                ,'"+paramCourseCodeValue[0]+"'"+
                           "                                ,'COL'                        "+
                           "                                ,QUESTION),                   "+ 
                           // this column to select the right question label since some questions are omitted 
                           "             substr(QUESTION,2) quest_label                   "+
                           " FROM        TEACHING_SURVEY_ANALYSIS                     "+
                           " WHERE       SEM_CODE='"+paramSemesterValue[0]+"'         "+
                           "       AND   COURSE_CODE ='"+paramCourseCodeValue[0]+"'   "+         
                           "       AND   SECTNO ='"+paramSectionNoValue[0]+"'         "+ 
                           "       AND   INSTRUCTOR_ID = '"+paramInstructorValue[0]+"'"+
                           " ORDER BY    TO_NUMBER(SUBSTR(QUESTION,2))                ";
   stmtSurveyAnalysis = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);    
   rsSurveyAnalysis= stmtSurveyAnalysis.executeQuery(strQuerySurveyAnalysis);


   strClinical = " select distinct CLINICAL_COURSE(course_code "+
                 "                        ,crs_type_code)  is_clinical    "+
                 " from   V_COURSE_EVALUATION_HISTORY  "+
                 " where  course_code='"+paramCourseCodeValue[0]+"'"+
                 "   and  YR_SEM='"+paramSemesterValue[0]+"'         ";


   stmtClinical = conn.createStatement ();    
   rsClinical= stmtClinical.executeQuery(strClinical);

   if (rsClinical.next())
      if (rsClinical.getString("is_clinical").equals("Y"))
         if (year >10)
            rb= ResourceBundle.getBundle("clinicalTeachingSurveyResourceBundle",pReq.getLocale());
         else
          rb= ResourceBundle.getBundle("nursing_resource_bundle",pReq.getLocale());
 
  String strCondition ="";
  String strFieldName="";
  String strCourseName="";
  String strColName="";
  String strDeptName="";
  // the following query is to get a list of all courses that instructor teach 
  // in arabic verision or english 
  if (strlang.startsWith("en"))
    {
        strFieldName  ="F.EMP_NAME";
        strCourseName ="CEH.crsname";
        strColName    ="CEH.CRS_COL_NAME";
        strDeptName   ="CEH.CRS_DEPT_NAME";
    }
  else
   {
      strFieldName  ="F.A_EMP_NAME";
      strCourseName ="CEH.a_crsname";
      strColName    ="CEH.CRS_COL_NAME_ARABIC";
      strDeptName   ="CEH.CRS_DEPT_NAME_ARABIC";
   } 

       strQuerySIS= " SELECT       CEH.course_code    course_code                  "+
                    "             ,"+strCourseName+"  course_name                  "+ 
                    "             ,CEH.sectno    sect_no                           "+
                    "             ,CEH.seatstaken   seats_taken                    "+
                    "             ,"+strFieldName+"   emp_name                     "+
                    "             ,"+strColName+" col_name                         "+
                    "             ,"+strDeptName+" dept_name                       "+      
                    "             ,course_survey_valid_msg(GET_STD_RESPONSES(      "+
                    "                                        CEH.YR_SEM            "+
                    "                                       ,CEH.course_code       "+
                    "                                       ,CEH.sectno,CEH.EMPNO  "+
                    "                                       ,null,null)            "+
                    "                                   ,CEH.seatstaken            "+
                    "                                   ,substr('"+strlang+"',0,2) "+
                    "                                   ) course_survey_valid      "+
                    " FROM        V_COURSE_EVALUATION_HISTORY CEH,  "+
                    "             V_FACULTY_PORTAL   F              "+
                    " WHERE       CEH.EMPNO = F.EMPNO                              "+
                    "       AND   CEH.YR_SEM='"+paramSemesterValue[0]+"'           "+
                    "       AND   CEH.COURSE_CODE='"+paramCourseCodeValue[0]+"'    "+      
                    "       AND   CEH.sectno ='"+paramSectionNoValue[0]+"'         "+
                    "       AND   CEH.EMPNO= '"+paramInstructorValue[0]+"'         ";

// execute the query to get a list of all courses that instructor teach 
   stmtSIS = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);    
   rsQuerySIS= stmtSIS.executeQuery(strQuerySIS);


 if (rsQuerySIS.next())
   {  
   

String strSem="";
if (null != paramSemesterValue[0])
{
   strSem = " 20"+paramSemesterValue[0].substring(0,2);
  if (paramSemesterValue[0].endsWith("SP"))
      strSem =rb.getString("Spring") + strSem;
  else if (paramSemesterValue[0].endsWith("FL")) 
      strSem =rb.getString("Fall") + strSem;
   else if (paramSemesterValue[0].endsWith("SU")) 
      strSem =rb.getString("Summer") + strSem;
 
 }
%>
<br>
<center>
<table align="center">
  <tr>
      <td><center><span class="PortletHeaderText"><font color="black"> <%= rb.getString("Heading1")%> </br>
           <%= rb.getString("CourseTeachSummary")%>- <%=strSem%></font></span> </center>
      </td>
  </tr>
  <tr>
      <td>
          <table>
              <tr>
                  <td><span class="PortletText1"><b><%= rb.getString("Course")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("course_code")%>/<%=rsQuerySIS.getString("course_name")%></span></td>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <td align="right"><span class="PortletText1"><b><%= rb.getString("College")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("col_name")%></span></td>                   
              </tr>
               <tr>
                  <td><span class="PortletText1"><b><%= rb.getString("Section")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("sect_no")%></span></td>   
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <td align="right"><span class="PortletText1"><b><%= rb.getString("Department2")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("dept_name")%></span></td>                   
              </tr>
              <tr>
                  <td><span class="PortletText1"><b><%= rb.getString("Instructor")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("emp_name")%></span></td>  
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <td align="right"><span class="PortletText1"><b><%= rb.getString("NoRegStd")%>:</b></span></td>
                  <td><span class="PortletText2"><%=rsQuerySIS.getString("seats_taken")%></span></td>                   
              </tr>
          </table>
      </td>
  </tr>
  <tr>
      <td>
          <table align="center" border="1" cellspacing="0">
              <tr>
                  <td rowspan="2"> &nbsp;</td>
                  <td rowspan="2" class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("CourseTeachItem")%></span></td>
                  <td colspan="6" class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("NumResponses")%></span></td>
                  <td rowspan="2" class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("PercentageResponse")%></span></td>
                  <td colspan="4" class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("Mean")%></span></td>
              </tr>
              <tr>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("StrDisagree")%>   </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Disagree")%>   </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Agree")%>    </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("StrAgree")%></span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("NotApplicable")%>  </span></td>            
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Total")%>  </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Section")%>   </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Course")%>   </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("Department")%>    </span> </td>
                <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"> <%= rb.getString("College")%></span> </td>                           
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="center"><span class="PortletText1"><%= rb.getString("CourseItem")%></span></td>
                <td colspan="6">&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="4">&nbsp;</td>            
              </tr>
        <% //int i=1;
           int   RowsNo = 0;
           int   SumTotal=0;
           float SumPctFav =0;
           float SumSection =0;
           float SumCourse =0;
           float SumDept =0;
           float SumCollege =0;
           String TotalSum="";
           String TotalPctFav="";
           String TotalSection="";
           String TotalCourse="";
           String TotalDept="";
           String TotalCollege="";
           
           DecimalFormat dfTotal = new DecimalFormat ("##.#");
           DecimalFormat df = new DecimalFormat ("#.##");  
           
           String item_question="";
           if (year> 10)
                   item_question= "Q13";
                else
                    item_question= "Q15";
            
            while (rsSurveyAnalysis.next())
              {   
               if (  (rsSurveyAnalysis.getString(1).equals("Q2"))
                  || (rsSurveyAnalysis.getString(1).equals(item_question))
                  || (rsSurveyAnalysis.getString(1).equals("Q14"))
                  )
                { 
                 
                 RowsNo++;
        %>
              <tr> 
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(1)%></span></td>
                <td><span class="PortletText2"><%= rb.getString("Qusetion"+rsSurveyAnalysis.getString("quest_label"))%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(2)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(3)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(4)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(5)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(6)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(7)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(8)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(9)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(10)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(11)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(12)%></span></td>
                
              </tr>
          <%    SumTotal= SumTotal + Integer.parseInt(rsSurveyAnalysis.getString(7));
                SumPctFav= SumPctFav + Float.parseFloat(rsSurveyAnalysis.getString(8));
                SumSection= SumSection + Float.parseFloat(rsSurveyAnalysis.getString(9));
                SumCourse= SumCourse + Float.parseFloat(rsSurveyAnalysis.getString(10));
                SumDept= SumDept + Float.parseFloat(rsSurveyAnalysis.getString(11));
                SumCollege= SumCollege + Float.parseFloat(rsSurveyAnalysis.getString(12));
                }
           //   i++;
             }
             
             TotalPctFav = dfTotal.format (SumPctFav/RowsNo); 
             TotalSection = df.format (SumSection/RowsNo); 
             TotalCourse = df.format (SumCourse/RowsNo); 
             TotalDept = df.format (SumDept/RowsNo);             
             TotalCollege = df.format (SumCollege/RowsNo);  
           
            
                      %>
             <tr>
                <td>&nbsp;</td>
                <td align="center"><span class="PortletText1"><%= rb.getString("Summary")%></span></td>
                <td colspan="5">&nbsp;</td>
                <td align="center"><span class="PortletText2"><%=SumTotal%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalPctFav%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalSection%></span></td>               
                <td align="center"><span class="PortletText2"><%=TotalCourse%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalDept%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalCollege%></span></td>
             </tr>
             <tr><td colspan="13"  class="PortletSubHeaderColor" >&nbsp;</td>
             </tr>
             <tr><td>&nbsp;</td>
                <td align="center"><span class="PortletText1"><%= rb.getString("TeachingItem")%></span></td>
                 <td colspan="6">&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="4">&nbsp;</td>
             </tr>
          <%
          //  i=1;
            RowsNo = 0;
            SumTotal= 0;
            SumPctFav= 0;
            SumSection= 0;
            SumCourse= 0;
            SumDept= 0;
            SumCollege=0;
            rsSurveyAnalysis.beforeFirst();
            while (rsSurveyAnalysis.next())
              {
               if (!( (rsSurveyAnalysis.getString(1).equals("Q2"))
                    || (rsSurveyAnalysis.getString(1).equals(item_question))
                    || (rsSurveyAnalysis.getString(1).equals("Q14"))
                  ))
                {
                 RowsNo++;
        %>
              <tr> 
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(1)%></span></td>
                <td><span class="PortletText2"><%= rb.getString("Qusetion"+rsSurveyAnalysis.getString("quest_label"))%></span></td>
               <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(2)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(3)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(4)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(5)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(6)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(7)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(8)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(9)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(10)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(11)%></span></td>
                <td align="center"><span class="PortletText2"><%= rsSurveyAnalysis.getString(12)%></span></td>
                
              </tr>
          <%    SumTotal= SumTotal + Integer.parseInt(rsSurveyAnalysis.getString(7));
                SumPctFav= SumPctFav + Float.parseFloat(rsSurveyAnalysis.getString(8));
                SumSection= SumSection + Float.parseFloat(rsSurveyAnalysis.getString(9));
                SumCourse= SumCourse + Float.parseFloat(rsSurveyAnalysis.getString(10));
                SumDept= SumDept + Float.parseFloat(rsSurveyAnalysis.getString(11));
                SumCollege= SumCollege + Float.parseFloat(rsSurveyAnalysis.getString(12));
                }
             // i++;
             }
             
             TotalPctFav = dfTotal.format (SumPctFav/RowsNo); 
             TotalSection = df.format (SumSection/RowsNo); 
             TotalCourse = df.format (SumCourse/RowsNo); 
             TotalDept = df.format (SumDept/RowsNo);             
             TotalCollege = df.format (SumCollege/RowsNo);  
           
            
                      %>
             <tr>
                <td>&nbsp;</td>
                <td align="center"><span class="PortletText1"><%= rb.getString("Summary")%></span></td>
                <td colspan="5">&nbsp;</td>
                <td align="center"><span class="PortletText2"><%=SumTotal%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalPctFav%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalSection%></span></td>               
                <td align="center"><span class="PortletText2"><%=TotalCourse%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalDept%></span></td>
                <td align="center"><span class="PortletText2"><%=TotalCollege%></span></td>
             </tr>
          
          </table>
      </td>
  </tr> 
</table>
</center>
</br>     
<FONT color="RED"> <B><%=rsQuerySIS.getString("course_survey_valid")%></B></FONT>
</br> 
<span class="PortletText2">
<ul>
<li>
<%=rb.getString("Note1")%></li>
<li><%=rb.getString("Note2")%></li>
</ul></span>
<%
   }// end of rsQuerySIS
   

}// end if rsAllowedAccess

}// end of try
catch (SQLException exc){
      throw new SQLException ("SQL statement is not executed!");}
finally 
   {
   // closing all resultsets and statments and connections
   
      if (rsSurveyAnalysis!= null) rsSurveyAnalysis.close();
      if (stmtSurveyAnalysis!= null) stmtSurveyAnalysis.close();

 
 
      if (rsQuerySIS!= null) rsQuerySIS.close();
      if (stmtSIS!= null) stmtSIS.close();


     
      if (conn != null) conn.close(); 
     
   }
}// end if tthe passed parameters were not null

  
%>
