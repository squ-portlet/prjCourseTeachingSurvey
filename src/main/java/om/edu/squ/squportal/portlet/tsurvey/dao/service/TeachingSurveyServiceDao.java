/**
 * Project				:	prjCourseTeachingSurvey
 * Organization			:	Sultan Qaboos University | Muscat | Oman
 * Centre				:	Centre for Information System
 * Department			:	Web & E-Services
 * 
 * Author				:	Bhabesh
 *
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	TeachingSurveyServiceDao.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.dao.service
 * Date of creation		:	Aug 17, 2015
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2015 the original author or authors and Organization.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * 
 */
package om.edu.squ.squportal.portlet.tsurvey.dao.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

import com.itextpdf.text.DocumentException;

import om.edu.squ.squportal.portlet.tsurvey.bo.AccessSurvey;
import om.edu.squ.squportal.portlet.tsurvey.bo.CommitteeMember;
import om.edu.squ.squportal.portlet.tsurvey.bo.Department;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.StaffRole;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.load.StatementSqlListBo;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.OpenEndQuestion;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyYear;

/**
 * @author Bhabesh
 *
 */
public interface TeachingSurveyServiceDao
{
	
	/**
	 * 
	 * method name  : getAccessToSurvey
	 * @param empNumber
	 * @param courseCode
	 * @param sectionNo
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : AccessSurvey
	 * 
	 * purpose		: permission to access survey
	 *
	 * Date    		:	Aug 27, 2015 12:07:29 PM
	 */
	public AccessSurvey getAccessToSurvey(String empNumber,  String courseCode, String sectionNo );
	
	/**
	 * 
	 * method name  : getEmployeeNumber
	 * @param request
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: get employee number
	 *
	 * Date    		:	Aug 18, 2015 9:05:09 AM
	 */
	public  String getEmployeeNumber(PortletRequest request);
	
	/**
	 * 
	 * method name  : getUserName
	 * @param request
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: get userName
	 *
	 * Date    		:	Aug 18, 2015 9:05:26 AM
	 */
	public String getUserName(PortletRequest request);
	
	/**
	 * 
	 * method name  : getStudentResponses
	 * @param empNumber
	 * @param locale
	 * @return
	 * TeachingSurveyServiceDao
	 * return type  : List<Survey>
	 * 
	 * purpose		: Get current student responses to a faculty
	 *
	 * Date    		:	Aug 18, 2015 12:04:02 PM
	 */
	public List<Survey> getStudentResponses(String empNumber, Locale locale );
	
	/**
	 * 
	 * method name  : getAllSurveys
	 * @param empNumber
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : List<SurveyYear>
	 * 
	 * purpose		: Get Survey list according to survey year
	 *
	 * Date    		:	Aug 20, 2015 9:52:56 AM
	 */
	public List<SurveyYear> getAllSurveys(String empNumber);
	
	/**
	 * 
	 * method name  : getSurVeyAnalysis
	 * @param empNumber
	 * @param courseCode
	 * @param semesterCode
	 * @param sectionNo
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : Survey
	 * 
	 * purpose		: Get Survey heading for analysis
	 *
	 * Date    		:	Aug 24, 2015 1:50:43 PM
	 */
	public	Survey  getSurVeyAnalysis(String empNumber, String courseCode, String semesterCode, String sectionNo, Locale locale);
	
	/**
	 * 
	 * method name  : getSemesterYear
	 * @param semesterCode
	 * @param locale
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Aug 25, 2015 9:09:01 AM
	 */
	public String getSemesterYear(String semesterCode, Locale locale);
	
	/**
	 * 
	 * method name  : getOpenEndQuestions
	 * @param empNumber
	 * @param courseCode
	 * @param semesterCode
	 * @param sectionNo
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<OpenEndQuestion>
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 27, 2015 1:44:28 PM
	 */
	public List<OpenEndQuestion> getOpenEndQuestions(String empNumber, String courseCode, String semesterCode, String sectionNo);
	
	/**
	 * 
	 * method name  : getAllYearSemestersForReport
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : List<SurveyYear>
	 * 
	 * purpose		: Get all year semesters
	 *
	 * Date    		:	Sep 1, 2015 9:03:45 AM
	 */
	public List<SurveyYear> getAllYearSemestersForReport();
	
	/**
	 * 	
	 * method name  : getStaffRole
	 * @param empNumber
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : StaffRole
	 * 
	 * purpose		: Get Staff Role (i.e. position. e.g. DEAN/HOD etc
	 *
	 * Date    		:	Sep 1, 2015 12:43:51 PM
	 */
	public StaffRole  getStaffRole(String empNumber);
	
	/**
	 * 
	 * method name  : getValidReportSummaries
	 * @param empNumber
	 * @param semesterCode
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<ReportSummary>
	 * 
	 * purpose		: valid survey report summary
	 *
	 * Date    		:	Sep 3, 2015 9:30:59 AM
	 */
	public List<ReportSummary>  getValidReportSummaries(String empNumber, String semesterCode, Locale locale);
	
	/**
	 * 
	 * method name  : getValidReportSummariesJSON
	 * @param empNumber
	 * @param semesterCode
	 * @param locale
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Sep 14, 2015 2:39:03 PM
	 */
	public String getValidReportSummariesJSON(String empNumber, String semesterCode, Locale locale);
	
	
	/**
	 * 
	 * method name  : getNotValidReportSummaries
	 * @param empNumber
	 * @param semesterCode
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<ReportSummary>
	 * 
	 * purpose		: not valid survey report summary
	 *
	 * Date    		:	Sep 6, 2015 9:08:50 AM
	 */
	public List<ReportSummary>  getNotValidReportSummaries(String empNumber, String semesterCode, Locale locale);
	
	/**
	 * method name  : getCollegeCoursesForAsstDean
	 * @param empNumber
	 * @param locale
	 * @return
	 * TeachingSurveyDbDao
	 * return type  : List<StudentResponse>
	 * 
	 * purpose		: get list of college courses (can be viewed by assistant dean)
	 *
	 * Date    		:	Oct 1, 2015 1:26:28 PM
	 */
	public List<StudentResponse> getCollegeCoursesForAsstDean(String empNumber, Locale locale);
	
	
	/**
	 * 
	 * method name  : getNotValidReportSummariesJSON
	 * @param empNumber
	 * @param semesterCode
	 * @param locale
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: not valid survey report summary (in json format)
	 *
	 * Date    		:	Sep 30, 2015 2:27:40 PM
	 */
	public String  getNotValidReportSummariesJSON(String empNumber, String semesterCode, Locale locale);
	

	
	
	
	/**
	 * 
	 * method name  : getCollegeCoursesForAsstDean
	 * @param empNumber
	 * @param locale TODO
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : List<Department>
	 * 
	 * purpose		: get list of department wise college courses (can be viewed by assistant dean) in JSON format
	 *
	 * Date    		:	Sep 10, 2015 9:42:01 AM
	 */
	public String getCollegeCoursesForAsstDeanJSON(String empNumber, Locale locale);
	
	/**
	 * 
	 * method name  : getCommitteeMembers
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<CommitteeMember>
	 * 
	 * purpose		: get list of committee memebers
	 *
	 * Date    		:	Sep 17, 2015 11:51:59 AM
	 */
	public List<CommitteeMember> getCommitteeMembers(Locale locale);
	
	/**
	 * 
	 * method name  : getCurrentYrSemAdmin
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : String
	 * 
	 * purpose		: Get current yr semester for administrator
	 *
	 * Date    		:	Sep 20, 2015 12:34:42 PM
	 */
	public String getCurrentYrSemAdmin();
	
	/**
	 * 
	 * method name  : getAccessViewRights
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : AccessSurvey
	 * 
	 * purpose		: control the view of current semester survey of committee members / faculty/hod/dean/asst. dean etc.
	 *
	 * Date    		:	Sep 21, 2015 4:06:18 PM
	 */
	public AccessSurvey getAccessViewRights();
	
	/************************************************ SURVEY LOADING OPERATIONS ***************************************/ 
	
	/**
	 * 
	 * method name  : getCronStudentSurveyStart
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: Cron Format for date input from DB
	 *
	 * Date    		:	Nov 17, 2016 9:53:51 AM
	 */
	public String getCronStudentSurveyStart();
	
	
	/**
	 * 	
	 * method name  : loadPreSurvey
	 * @return
	 * @throws IOException
	 * TeachingSurveyServiceImpl
	 * return type  : String
	 * 
	 * purpose		: DB preparation before survey start
	 *
	 * Date    		:	Nov 5, 2015 12:36:54 PM
	 */
		public String	loadPreSurvey() throws IOException;	

	/************************************************ PDF GENERATION OPERATIONS ***************************************/		
	/**
	 * 
	 * method name  : getPdfContent
	 * @param strTemplateName
	 * @param object
	 * @param byos
	 * @param semesterYear
	 * @param questionByYear
	 * @param questionSetNo
	 * @param res
	 * @param locale
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * TeachingSurveyServiceDao
	 * return type  : OutputStream
	 * 
	 * purpose		: Get PDF Content
	 *
	 * Date    		:	Mar 28, 2016 7:19:41 PM
	 */
	public OutputStream getPdfContent(String strTemplateName, Object object, ByteArrayOutputStream	byos, String semesterYear,  String questionByYear, int questionSetNo, ResourceResponse res, Locale locale) throws IOException, DocumentException;
	
	
	/**
	 * 
	 * method name  : getExcelContent
	 * @param strTemplateName
	 * @param response
	 * @param object
	 * @param params
	 * @param locale
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 * TeachingSurveyServiceDao
	 * return type  : OutputStream
	 * 
	 * purpose		: Get Excel Content
	 *
	 * Date    		:	Mar 16, 2016 1:23:01 PM
	 */
	public OutputStream getExcelContent(String strTemplateName, ResourceResponse response, Object object, Map<String, String> params, Locale locale) throws DocumentException, IOException;
	

	/************************************************ POST SURVEY CONTROL OPERATIONS ***************************************/ 
	
	/**
	 * 
	 * method name  : isPostSurveyAnalysisAvailable
	 * @param semesterCode
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : boolean
	 * 
	 * purpose		: To check whether the analysis process already started. It will help to stop accidental parallel approach; 
	 *
	 * Date    		:	May 26, 2016 12:52:36 PM
	 */
	public boolean isPostSurveyAnalysisAvailable(String semesterCode );
	
	/**
	 * 
	 * method name  : postSurveyStartAnalysis
	 * @param semesterYear
	 * @return
	 * TeachingSurveyServiceDao
	 * return type  : boolean
	 * 
	 * purpose		: Analysis process - post student survey
	 *
	 * Date    		:	May 26, 2016 1:15:26 PM
	 */
	public boolean postSurveyStartAnalysis(String semesterYear);
	
	
}
