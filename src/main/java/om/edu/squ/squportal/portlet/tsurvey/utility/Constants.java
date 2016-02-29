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
 * File Name			:	Constants.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.utility
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
package om.edu.squ.squportal.portlet.tsurvey.utility;

/**
 * @author Bhabesh
 *
 */
public interface Constants
{
	/**********LDAP***************************************/
	public	static	String	LDAP_COMMON_NAME									=	"cn";
	public	static	String	LDAP_EMPLOYEE_NUMBER								=	"employeenumber"; 
	public	static	String	LDAP_EMPLOYEE_NUMBER2								=	"description"; 
	/******************************************************/

	
	/************* PROPERTY FILE ******************************/
	public	static	String	RESOURCE_PROPERTY_FILE_NAME							=	"messages";
	/******************************************************/

	
	/**********CONSTANTS - SQL - QUERY - NAME**************/	
	public	static	String	SQL_SURVEY_ACCESS									=	"access.survey";
	public	static	String	SQL_SURVEY_ACCESS_REPORT							=	"access.survey.report";
	public	static	String	SQL_SURVEY_ACCESS_VIEW_RIGHTS						=	"view.survey.committee.faculty.admin";
	public	static	String	SQL_CURRENT_STUDENT_RESPONSE_FOR_FACULTY			=	"student.current.response.for.faculty";
	public	static	String	SQL_SURVEY_ALL										=	"survey.all";
	public	static	String	SQL_TEACHING_SURVEY_ANALYSIS						=	"teaching.survey.analysis";
	public	static	String	SQL_SURVEY_ANALYSIS_HEADING							=	"survey.analysis.heading";
	public	static	String	SQL_SURVEY_OPEN_END_QUESTIONS						=	"survey.open.end.questions";
	public	static	String	SQL_SURVEY_REPORT_ACADEMIC_YEAR						=	"report.academic.year";
	public	static	String	SQL_STAFF_ROLE										=	"staff.role";
	public	static	String	SQL_VALID_SURVEY_REPORT_SUMMARY						=	"valid.survey.report.summary";
	public	static	String	SQL_NOT_VALID_SURVEY_REPORT_SUMMARY					=	"not.valid.survey.report.summary";
	public	static	String	SQL_COLLEGE_COURSES_FOR_ASST_DEAN					=	"college.courses.for.asst.dean";
	public	static	String	SQL_COMMITTEE_MEMBERS_IN							=	"committee.members.in";
	public	static	String	SQL_CURRENT_YRSEM_ADMIN								=	"current.yrsem.admin";
	public	static	String	SQL_UPDATE_DATE_ADMIN_COMITTEE_VIEW					=	"update.date.admin.committee.view";
	public	static	String	SQL_UPDATE_DATE_ADMIN_FACULTY_VIEW					=	"update.date.admin.faculty.view";
	
	
	
	
	
	/******************************************************/

	
	/**********CONSTANTS - SQL - COLUMN VALUE *************/
	public 	static	String	CONST_COLMN_SERIAL_NO								=	"SERIAL_NO";
	public 	static	String	CONST_COLMN_COURSE_CODE								=	"COURSE_CODE";
	public 	static	String	CONST_COLMN_COURSE_NAME								=	"COURSE_NAME";
	public 	static	String	CONST_COLMN_COLLEGE_CODE							=	"COLLEGE_CODE";
	public 	static	String	CONST_COLMN_COLLEGE_NAME							=	"COLLEGE_NAME";
	public 	static	String	CONST_COLMN_DEPARTMENT_CODE							=	"DEPARTMENT_CODE";
	public 	static	String	CONST_COLMN_DEPARTMENT_NAME							=	"DEPARTMENT_NAME";
	public 	static	String	CONST_COLMN_SECTION_NO								=	"SECTION_NO";
	public 	static	String	CONST_COLMN_SEATS_TAKEN								=	"SEATS_TAKEN";
	public 	static	String	CONST_COLMN_REGISTERED_STUDENT						=	"REGISTERED_STUDENT";
	public 	static	String	CONST_COLMN_STUDENT_RESPONSE						=	"STUDENT_RESPONSE";
	public 	static	String	CONST_COLMN_STUDENT_NO_RESPONSE						=	"STUDENT_NO_RESPONSE";
	public 	static	String	CONST_COLMN_YR_SEM									=	"YR_SEM";
	public 	static	String	CONST_COLMN_EMP_NUMBER								=	"EMP_NUMBER";
	public 	static	String	CONST_COLMN_EMP_NAME								=	"EMP_NAME";
	public 	static	String	CONST_COLMN_ACADEMIC_YEAR							=	"ACADEMIC_YEAR";
	public 	static	String	CONST_COLMN_STAFF_ROLE								=	"STAFF_ROLE";
	public 	static	String	CONST_COLMN_SURVEY_MESSAGE							=	"COURSE_SURVEY_VALID_MSG";
	public 	static	String	CONST_COLMN_INCLUDE_EXCLUDE							=	"INCLUDE_EXCLUDE";
	
	public 	static	String	CONST_COLMN_QUESTION_NO								=	"QUESTION_NO";
	public 	static	String	CONST_COLMN_STRONG_DISAGREE							=	"STRONG_DISAGREE";
	public 	static	String	CONST_COLMN_DISAGREE								=	"DISAGREE";
	public 	static	String	CONST_COLMN_AGREE									=	"AGREE";
	public 	static	String	CONST_COLMN_STRONG_AGREE							=	"STRONG_AGREE";
	public 	static	String	CONST_COLMN_NOT_APPLICABLE							=	"NOT_APPLICABLE";
	public 	static	String	CONST_COLMN_TOTAL									=	"TOTAL";
	public 	static	String	CONST_COLMN_PERCENTAGE_RESPONSE						=	"PERCENTAGE_RESPONSE";
	public 	static	String	CONST_COLMN_SECTION_MEAN							=	"SECTION_MEAN";
	public 	static	String	CONST_COLMN_COURSE_MEAN								=	"COURSE_MEAN";
	public 	static	String	CONST_COLMN_DEPARTMENT_MEAN							=	"DEPARTMENT_MEAN";
	public 	static	String	CONST_COLMN_COLLEGE_MEAN							=	"COLLEGE_MEAN";
	public 	static	String	CONST_COLMN_QUEST_LABEL								=	"QUEST_LABEL";
	public 	static	String	CONST_COLMN_TEACHING_MEAN							=	"TEACHING_MEAN";
	public 	static	String	CONST_COLMN_TEACHING_PERCENTAGE_FAV					=	"TEACHING_PCTFAV";
	public 	static	String	CONST_COLMN_QUESTION_MEAN							=	"Q_MEAN";
	public 	static	String	CONST_COLMN_QUESTION_PERCENTAGE_FAV					=	"Q_PCTFAV";
	
	public 	static	String	CONST_COLMN_OPEN_END_QUESTION1						=	"OPEN_END_QUESTION1";
	public 	static	String	CONST_COLMN_OPEN_END_QUESTION2						=	"OPEN_END_QUESTION2";
	public 	static	String	CONST_COLMN_OPEN_END_QUESTION3						=	"OPEN_END_QUESTION3";
	
	
	public 	static	String	CONST_COLMN_IS_ACCESS_EMP_COURSE					=	"IS_ACCESS_EMP_COURSE";
	public 	static	String	CONST_COLMN_IS_ACCESS_ALL_SURVEY					=	"IS_ACCESS_ALL_SURVEY";
	public 	static	String	CONST_COLMN_IS_ACCESS_REPORT						=	"IS_ACCESS_REPORT";
	public 	static	String	CONST_COLMN_IS_ACCESS_ASST_DEAN						=	"IS_ACCESS_ASST_DEAN";
	public 	static	String	CONST_COLMN_IS_ADMIN								=	"IS_ADMIN";
	
	public 	static	String	CONST_COLMN_UNIVERSITY_RANK							=	"UNIVERSITY_RANK";
	public 	static	String	CONST_COLMN_COLLEGE_RANK							=	"COLLEGE_RANK";
	public 	static	String	CONST_COLMN_DEPARTMENT_RANK							=	"DEPARTMENT_RANK";
	public 	static	String	CONST_COLMN_COMMITTEE_ROLE							=	"COMMITTEE_ROLE";
	public 	static	String	CONST_COLMN_COMMITTEE_VIEW_DATE						=	"COMMITTEE_VIEW_DATE";
	public 	static	String	CONST_COLMN_OTHERS_VIEW_DATE						=	"OTHERS_VIEW_DATE";
	
	
	/******************************************************/
	
	/**********CONSTANTS MODEL ATTRIBUTES *************/
	public 	static	String	CONST_MODEL_ATTR_CURRENT_RESPONSES					=	"currentSurvey";
	public 	static	String	CONST_MODEL_ATTR_ALL_SURVEY							=	"allSurvey";
	public 	static	String	CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE				=	"isCurrentSemesterViewable";
	public 	static	String	CONST_MODEL_CURR_SEMESTER							=	"currentSemester";
	
	public static  String 	CONST_MAPTRACK 										= 	"MAPTRACK";
	
	/******************************************************/
	
	/**********CONSTANTS XML FILES *************/
	public static String	CONST_FILE_XML_SQL_PRESURVEY_LOAD					=	"sql/sql_preSurveyLoading.xml";
	
	/**********CONSTANTS PDF TEMPLATE NAME *************/
	public static String	CONST_SURVEY_ANALYSIS								=	"SURVEY_ANALYSIS";
	
	
	/**********CONSTANTS PDF TEMPLATE FILES *************/
	public static String	CONST_FILE_PDF_TEMPLATE_SURVEY_ANALYSIS				=	"template/pdf/temSurveyAnalysisEn.pdf";
	
	/**********CONSTANTS FOR SESSIONS  *************/
	public static String	CONST_SESSION_STAFF									=	"SESSION_STAFF";

	
	/**********CONSTANTS FOR TEXT ALIGN  *************/
	public static String	RIGHT												=	"R";
	public static String	LEFT												=	"L";
	public static String	CENTER												=	"C";
	
	
	
	
}
