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
 * File Name			:	TeachingSurveyDbDao.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.dao.db
 * Date of creation		:	May 20, 2015  12:50:54 PM
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
package om.edu.squ.squportal.portlet.tsurvey.dao.db;

import java.util.List;
import java.util.Locale;

import om.edu.squ.squportal.portlet.tsurvey.bo.AccessSurvey;
import om.edu.squ.squportal.portlet.tsurvey.bo.CommitteeMember;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportYrSem;
import om.edu.squ.squportal.portlet.tsurvey.bo.StaffRole;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.load.StatementSqlBo;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Analysis;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.OpenEndQuestion;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;

/**
 * @author Bhabesh
 *
 */
public interface TeachingSurveyDbDao
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
	 * method name  : getAccessToSurveyReport
	 * @param empNumber
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : AccessSurvey
	 * 
	 * purpose		: permission to access survey report of others
	 *
	 * Date    		:	Aug 31, 2015 11:11:32 AM
	 */
	public AccessSurvey getAccessToSurveyReport(String empNumber);
	
	/**
	 * 
	 * method name  : getStudentResponses
	 * @param empNumber
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<StudentResponse>
	 * 
	 * purpose		: Get current student responses to a faculty
	 *
	 * Date    		:	Aug 18, 2015 8:53:53 AM
	 */
	public List<StudentResponse> getStudentResponses(String empNumber, Locale locale );
	
	/**
	 * 
	 * method name  : getAllSurveys
	 * @param empNumber
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<StudentResponse>
	 * 
	 * purpose		: Get all surveys
	 *
	 * Date    		:	Aug 19, 2015 12:55:56 PM
	 */
	public List<StudentResponse> getAllSurveys(String empNumber);
	
	/**
	 * 
	 * method name  : getTeachingAnalysis
	 * @param empNumber
	 * @param courseCode
	 * @param semesterCode
	 * @param sectionNo
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<Analysis>
	 * 
	 * purpose		: Get list of analysis for teaching survey
	 *
	 * Date    		:	Aug 24, 2015 12:22:09 PM
	 */
	public List<Analysis> getTeachingAnalysis(String empNumber, String courseCode, String semesterCode, String sectionNo);
	
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
	 * method name  : getYearSemestersForReport
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<ReportYrSem>
	 * 
	 * purpose		: academic years and year semesters for reporting
	 *
	 * Date    		:	Aug 31, 2015 1:52:21 PM
	 */
	public List<ReportYrSem> getYearSemestersForReport();

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
	 * @param question
	 * @param locale
	 * @return
	 * TeachingSurveyDbDao
	 * return type  : List<ReportSummary>
	 * 
	 * purpose		: valid survey report summary
	 *
	 * Date    		:	Sep 7, 2015 12:32:03 PM
	 */
	public List<ReportSummary>  getValidReportSummaries(String empNumber, String semesterCode, String question, Locale locale);
	
	/**
	 * 
	 * method name  : getNotValidReportSummaries
	 * @param empNumber
	 * @param semesterCode
	 * @param question
	 * @param locale
	 * @return
	 * TeachingSurveyDbDao
	 * return type  : List<ReportSummary>
	 * 
	 * purpose		: Not valid survey report summary
	 *
	 * Date    		:	Sep 7, 2015 12:45:55 PM
	 */
	public List<ReportSummary>  getNotValidReportSummaries(String empNumber, String semesterCode, String question, Locale locale);
	
	

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
	
	/**
	 * 
	 * method name  : setDateAdminCommitteeView
	 * @param flag
	 * @param semesterCode
	 * @param dateCommitteeView
	 * @return
	 * TeachingSurveyDbDao
	 * return type  : int
	 * 
	 * purpose		: Update Committee View Date for admin use (using ajax)
	 *
	 * Date    		:	Sep 27, 2015 9:15:21 AM
	 */
	public int setDateAdminCommitteeView(String semesterCode, String dateCommitteeView);
	
	/**
	 * 
	 * method name  : setDateAdminFacultyView
	 * @param semesterCode
	 * @param dateFacultyView
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : int
	 * 
	 * purpose		: Update Others (Faculty) View Date for admin use (using ajax)
	 *
	 * Date    		:	Sep 27, 2015 9:58:06 AM
	 */
	public int setDateAdminFacultyView(String semesterCode, String dateFacultyView);

	/************************************************ SURVEY LOADING OPERATIONS ***************************************/ 
	
	/**
	 * 
	 * method name  : loadPreSurvey
	 * @param sqlBo
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : String
	 * 
	 * purpose		: Truncate and load data in different tables from SIS
	 *
	 * Date    		:	Nov 5, 2015 12:56:36 PM
	 */
	public  String loadPreSurvey(StatementSqlBo  sqlBo);
	
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
	 * method name  : postSurveyAnalysisAvailableUpdate
	 * @param semesterCode
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : int
	 * 
	 * purpose		: 
	 *
	 * Date    		:	May 26, 2016 11:53:16 AM
	 */
	public int postSurveyAnalysisAvailableUpdate(String semesterCode);
	
	/**
	 * 
	 * method name  : postSurveyStartAnalysis
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : int
	 * 
	 * purpose		:
	 *
	 * Date    		:	May 10, 2016 2:33:53 PM
	 */
	public int postSurveyStartAnalysis();
	
	/**
	 * 
	 * method name  : postSurveyAnalysisExecuteSurveyProc
	 * @param semCode
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : int
	 * 
	 * purpose		:
	 *
	 * Date    		:	May 25, 2016 9:38:14 AM
	 */
	public int postSurveyAnalysisExecuteSurveyProc(String semCode);
	
	/**
	 * 
	 * method name  : countSuccessAnalysisProcess
	 * @param semesterCode
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : int
	 * 
	 * purpose		: Count the number of each success of individual three process for analysing 
	 *
	 * Date    		:	May 25, 2016 12:08:06 PM
	 */
	public int countSuccessAnalysisProcess(String semesterCode);
	
}
