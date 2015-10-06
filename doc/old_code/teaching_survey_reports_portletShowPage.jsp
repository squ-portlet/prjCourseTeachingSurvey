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
        import ="java.util.Calendar"
%>
<%@ page isThreadSafe="false" %>
<%@ page errorPage="errorPage.jsp" %>
<%
    
    Connection  conn         = null;
    Statement   stmt         = null;
    ResultSet   rsQuery      = null;
    String      strQuery     = null;

    
    Statement   stmtNotValid     = null;
    ResultSet   rsQueryNotValid  = null;
    String      strQueryNotValid = null;

    Statement   stmtAccess         = null;
    ResultSet   rsQueryAccess      = null;
    String      strQueryAccess     = null;
 
    Statement   stmtAcademicYear         = null;
    ResultSet   rsQueryAcademicYear      = null;
    String      strQueryAcademicYear     = null;

//response.sendRedirect("serviceNotAvailable.html");
   PortletRenderRequest pReq = (PortletRenderRequest)
      request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);


//create new instance for the resource bundle
   ResourceBundle rb =
      ResourceBundle.getBundle("SISResourceBundle",pReq.getLocale());
   
// getting the language setting  
  String strlang= pReq.getLocale().toString();
 
String paramNameNextPage     = "next_page";
   String qualParamNameNextPage = HttpPortletRendererUtil.portletParameter(pReq, paramNameNextPage);

  
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


 

  String paramAcademicYear            = "paramAcademicYear";
  String paramAcademicYearQualifiled  =  HttpPortletRendererUtil.portletParameter(pReq,paramAcademicYear);
  String paramAcademicYearValue       =  pReq.getQualifiedParameter(paramAcademicYear); 

  String paramValidSurvey  = "paramValidSurvey";
  String paramValidSurveyQualifiled  =  HttpPortletRendererUtil.portletParameter(pReq,paramValidSurvey);
  String paramValidSurveyValue       =  pReq.getQualifiedParameter(paramValidSurvey); 
 
 strQueryAccess= " select   ACCESS_TEACHING_SURVEY_REP(to_number('"+strEmpId+"'))   access_rep         "+       
                 " from     dual      ";
   stmtAccess =  conn.createStatement();     
   rsQueryAccess= stmtAccess.executeQuery(strQueryAccess);

if (rsQueryAccess.next())
 if (rsQueryAccess.getString("access_rep").equals("Y"))
 { 
   strQueryAcademicYear = " select distinct SEM_CODE,                                                             "+
                          "            decode( substr(SEM_CODE,3,4)                                               "+                                             
                          "                      ,'FL','20'||substr(SEM_CODE,0,2)||'-'||                          "+
                          "                              to_char(to_number('20'||substr(SEM_CODE,0,2))+1)         "+ 
                          "                      ,'SP', decode(length(to_char(to_number(substr(SEM_CODE,0,2))-1)) "+
                          "                                ,1,'200'||to_char(to_number(substr(SEM_CODE,0,2))-1)   "+
                          "                                  ,'20'||to_char(to_number(substr(SEM_CODE,0,2))-1)    "+
                       //   "                                  ,'20'||substr(SEM_CODE,0,2)                        "+
                          "                                )                                                    "+
                          "                        ||'-20'||substr(SEM_CODE,0,2)                                "+                                    
                          "                  )academic_year                                                     "+
                          " from COURSE_TEACHING_RANKING                                                        "+
                          //" where (SEM_CODE != '15SP'  or ("+strEmpId+" =44) or ("+strEmpId+" =989))          "+ // excluded temporary until this semester is evaluated
                          " ORDER BY academic_year DESC,                                                        "+
                          "          SEM_CODE DESC                                                              ";                                                
                  
   stmtAcademicYear =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);    
   rsQueryAcademicYear= stmtAcademicYear.executeQuery(strQueryAcademicYear);


 if (rsQueryAcademicYear.next())
 {
 
    int rows   = (rsQueryAcademicYear.last()) ? rsQueryAcademicYear.getRow() : 0;  // Number of rows
    int cols  = 300; // Number of columns
    String[][] ArrayAcademicYear = new String[rows][cols];
    int MaxCol= 0;
   
    int rowNum = 0;
    int colNum = 0; 
    String strAcademicYear="";
    
    // get the current year
     Calendar c = Calendar.getInstance();
     int intCuurentYear= c.get(Calendar.YEAR);
 
     boolean BoolCurrentAcadYear =false;
     boolean BoolPrevAcadYear=false;
     
    int  intFirstYear  =0;
    int  intSecondYear = 0;

     rsQueryAcademicYear.beforeFirst();     
    if (rsQueryAcademicYear.next()) 
    { 
    
     strAcademicYear = rsQueryAcademicYear.getString("academic_year");
     ArrayAcademicYear[rowNum][colNum++] = rsQueryAcademicYear.getString("academic_year");
     
     // splitting the acedemic year into two years ex. 2008-2009 to be splitted into
     // 2008 and 2009
     intFirstYear  = Integer.parseInt(strAcademicYear.substring(0,4));
     intSecondYear = Integer.parseInt(strAcademicYear.substring(5));

     // check if the last semester of the analysised teaching survey is in the 
     // current academic year or not
       if  ((intCuurentYear == intFirstYear)|| (intCuurentYear== intSecondYear))
          BoolCurrentAcadYear= true;
      else
          BoolPrevAcadYear=true;
    }
     rsQueryAcademicYear.beforeFirst();
      while (rsQueryAcademicYear.next())
     {
         if (!(strAcademicYear.equals(rsQueryAcademicYear.getString("academic_year"))))
                 {               
                   colNum=0;
                   ArrayAcademicYear[++rowNum][colNum++] = rsQueryAcademicYear.getString("academic_year"); 
                   ArrayAcademicYear[rowNum][colNum++]   = rsQueryAcademicYear.getString("SEM_CODE");
                  }
           else
                  {   
                  ArrayAcademicYear[rowNum][colNum++] =    rsQueryAcademicYear.getString("SEM_CODE");
                  }
          strAcademicYear = rsQueryAcademicYear.getString("academic_year"); 
          
        if (colNum > MaxCol)
              MaxCol= colNum;    
                  
     }// end while loop of academic year result set
      //  rsQueryAcademicYear.beforeFirst();
  NameValue[] paramNextPageParams = new NameValue[3];  
 paramNextPageParams[0] = new NameValue(qualParamNameNextPage, "/htdocs/teaching_survey_reports_portlet/summary_result.jsp"); 
%>
<p><%=rb.getString("TSAY_Result")%> </p>
<% 


     
for (int row=0; row<=rowNum; row++)    
     {
      if (BoolCurrentAcadYear)
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
      <a class="Sem_Yr" onclick="showHide('AcademicYear<%=row%>')">
        <%=ArrayAcademicYear[row][0]%></a></br>
          <!-- <div class="hide" id="AcademicYear<%=row%>"> -->
           <div class="hide" id="AcademicYear<%=row%>">
<%
    for (int col=1; col<MaxCol; col++)
      {  
      
         paramNextPageParams[1] = new NameValue(paramAcademicYearQualifiled,  ArrayAcademicYear[row][col]);

%>         
   <ul> 
   <%
     if (null != ArrayAcademicYear[row][col])
     {
   %>
      <li><%=ArrayAcademicYear[row][col]%>:
    
     <% paramNextPageParams[2] = new NameValue(paramValidSurveyQualifiled,"Y");  %>
    ( <%=UrlUtils.constructHTMLLink(pReq,UrlUtils.PAGE_LINK,rb.getString("TSR_valid_survey"),"", paramNextPageParams,true,true) %>)  
    / 
     <% paramNextPageParams[2] = new NameValue(paramValidSurveyQualifiled,"N");  %>                              
    ( <%=UrlUtils.constructHTMLLink(pReq,UrlUtils.PAGE_LINK,rb.getString("TSR_invalid_survey"),"", paramNextPageParams,true,true) %>)  
    </li>
  </ul>
<%
     }
    } // end of inner for loop
%>
    </div></br>
<%  
  } // end of while loop of academic year

}// end of if academic year is not null
   // closing all resultsets and statments and connections
}// end if the user has the previlage to access the teaching survey report


      if (rsQueryNotValid!= null) rsQueryNotValid.close();
      if (stmtNotValid!= null) stmtNotValid.close();


      if (rsQueryAcademicYear!= null) rsQueryAcademicYear.close();
      if (stmtAcademicYear!= null) stmtAcademicYear.close();
       
   if (rsQueryAccess!= null) rsQueryAccess.close();
   if (stmtAccess!= null) stmtAccess.close();
       
      if (conn != null) conn.close(); 

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

        if(menu_status[theid] != 'show') 
          {
           switch_id.className = 'show';
           menu_status[theid] = 'show';
          }
        else
          {
           switch_id.className = 'hide';
           menu_status[theid] = 'hide';
          }
    }
}

//-->
</script>

