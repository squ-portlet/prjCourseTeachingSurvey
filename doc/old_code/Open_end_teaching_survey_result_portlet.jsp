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
    Connection  connOpenEndQues      = null;
    Statement   stmtOpenEndQues      = null;
    ResultSet   rsOpenEndQues        = null;
    String      strQueryOpenEndQues  = null;
    
    Statement   stmtAllowedAccess      = null;
    ResultSet   rsAllowedAccess        = null;    
    String      strQueryAllowedAccess   = null;
    
    Connection  connSIS         = null;
    Statement   stmtSIS         = null;
    ResultSet   rsQuerySIS      = null;
    String      strQuerySIS     = null;
    
    String strQueryColDept     = null;
    Statement   stmtColDept    = null;
    ResultSet   rsQueryColDept = null;

//response.sendRedirect("serviceNotAvailable.html");
   PortletRenderRequest pReq = (PortletRenderRequest)
      request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);


//create new instance for the resource bundle
   ResourceBundle rb =
      ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
 
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
      String           strEmpId;
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
// getting the language setting  
  String strlang= pReq.getLocale().toString();
  
try{
  // connect to the portal DB database
  javax.naming.InitialContext icPortal                     = new javax.naming.InitialContext();
  oracle.jdbc.pool.OracleConnectionPoolDataSource dsPortal = (oracle.jdbc.pool.OracleConnectionPoolDataSource)icPortal.lookup("jdbc/pool/DSTS");
  oracle.jdbc.pool.OraclePooledConnection pcPortal         = (oracle.jdbc.pool.OraclePooledConnection)dsPortal.getPooledConnection();


   connOpenEndQues                                               =  pcPortal.getConnection();
   
   strQueryAllowedAccess  = "  SELECT  EMP_COURSE('"+strEmpId+"',                   "+
                            "                         '"+paramCourseCodeValue[0]+"',"+
                            "                         '"+paramSectionNoValue[0]+"') "+
                            "  FROM DUAL                                            ";
   stmtAllowedAccess = connOpenEndQues.createStatement ();    
   rsAllowedAccess= stmtAllowedAccess.executeQuery(strQueryAllowedAccess);                    

 if (rsAllowedAccess.next())
    if (rsAllowedAccess.getString(1).equals("Y"))
     {
    

   strQueryOpenEndQues =" SELECT         NVL(OPEN_ENDED_Q1,'-')                     "+
                           "            ,NVL(OPEN_ENDED_Q2,'-')                     "+
                           "            ,NVL(OPEN_ENDED_Q3,'-')                     "+
                           " FROM        COURSE_TEACHING_SURVEY                     "+        
                           " WHERE       SEM_CODE='"+paramSemesterValue[0]+"'       "+
                           "       AND   COURSE_CODE ='"+paramCourseCodeValue[0]+"' "+         
                           "       AND   SECTION_NO ='"+paramSectionNoValue[0]+"'   "+ 
                           "       AND   INSTRUCTOR_ID ='"+strEmpId+"'              "+
                           " ORDER BY    1,2,3                                      ";
   stmtOpenEndQues = connOpenEndQues.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);    
   rsOpenEndQues= stmtOpenEndQues.executeQuery(strQueryOpenEndQues);
   
if (rsOpenEndQues.next())
{
String strSem="";

if ((null != paramSemesterValue[0])|| (!(paramSemesterValue[0].equals(""))))
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
           <%= rb.getString("CourseTeachOpenEnd")%> - <%=strSem%></font></span> </center>
      </td>
  </tr>
  <tr>
      <td>
       <center>
          <table>
              <tr>
                  <td><span class="PortletHeaderText"><font color="black">
                        <%=rb.getString("CourseCodeText")%>: <%=paramCourseCodeValue[0]%></font></span></td>
                  <td> &nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <td><span class="PortletHeaderText"><font color="black">
                        <%=rb.getString("SectionNotext")%>: <%=paramSectionNoValue[0]%></font></span></td>
              </tr>
          </table>
        </center>
      </td>
  </tr>
  <tr>
      <td>
          <table  border="1">
              <tr>
                  <td class="PortletHeaderColor" align="center">&nbsp;</td>
                  <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("OpenEndQ1")%></span></td>
                  <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("OpenEndQ2")%></span></td>
                  <td class="PortletHeaderColor" align="center"><span class="PortletHeaderText"><%= rb.getString("OpenEndQ3")%></span></td>
              </tr>
<%
        rsOpenEndQues.beforeFirst();
        int counter=1;
        while (rsOpenEndQues.next())
            {
%>
               <tr>
                  <td><span class="PortletText1"><%=counter%></span></td>
                  <td><span class="PortletText1"><%=rsOpenEndQues.getString(1)%> &nbsp;</span></td>   
                  <td><span class="PortletText1"><%=rsOpenEndQues.getString(2)%> &nbsp;</span></td>
                  <td><span class="PortletText1"><%=rsOpenEndQues.getString(3)%> &nbsp;</span></td>               
              </tr>
<%            counter++;
            }
%>
          </table>
      </TD>
  </TR>
</TABLE>  
</center>
<%  
   } // end if rsOpenEndQues
  }// end if allow access
}// end try
catch (SQLException exc){
      throw new SQLException ("SQL statement is not executed!");}
finally 
   {
   // closing all resultsets and statments and connections
   
      if (rsOpenEndQues!= null) rsOpenEndQues.close();
      if (stmtOpenEndQues!= null) stmtOpenEndQues.close();
      if (connOpenEndQues != null) connOpenEndQues.close(); 

   }
 } //end if 
%>
