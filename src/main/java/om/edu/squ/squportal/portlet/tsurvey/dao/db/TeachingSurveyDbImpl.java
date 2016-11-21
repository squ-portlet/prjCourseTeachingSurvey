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
 * File Name			:	TeachingSurveyDbImpl.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.dao.db
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
package om.edu.squ.squportal.portlet.tsurvey.dao.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import om.edu.squ.squportal.portlet.tsurvey.bo.AccessSurvey;
import om.edu.squ.squportal.portlet.tsurvey.bo.CommitteeMember;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportYrSem;
import om.edu.squ.squportal.portlet.tsurvey.bo.StaffRole;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentSurveyStartDay;
import om.edu.squ.squportal.portlet.tsurvey.bo.load.StatementSqlBo;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Analysis;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.OpenEndQuestion;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyResponse;
import om.edu.squ.squportal.portlet.tsurvey.utility.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bhabesh
 *
 */
public class TeachingSurveyDbImpl implements TeachingSurveyDbDao
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private	NamedParameterJdbcTemplate	namedParameterJdbcTemplate;
	private	Properties					queryProps;
	private	Properties					queryPropsPostSurveyControl;
	
	
	/**
	 * 
	 * method name  : setNamedParameterJdbcTemplate
	 * @param namedParameterJdbcTemplate
	 * StudentStatusDbImpl
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 10, 2015 
	 */
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{ 
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	/**
	 * 
	 * method name  : setQueryProps
	 * @param queryProps
	 * StudentStatusDbImpl
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 10, 2015 
	 */
	public void setQueryProps(Properties queryProps)
	{
		this.queryProps = queryProps;
	}

	
	
	/**
	 * Setter method : setQueryPropsPostSurveyControl
	 * @param queryPropsPostSurveyControl the queryPropsPostSurveyControl to set
	 * 
	 * Date          : May 10, 2016 1:59:59 PM
	 */
	public void setQueryPropsPostSurveyControl(
			Properties queryPropsPostSurveyControl)
	{
		this.queryPropsPostSurveyControl = queryPropsPostSurveyControl;
	}

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
	public AccessSurvey getAccessToSurvey(String empNumber,  String courseCode, String sectionNo )
	{
		String SQL_SURVEY_ACCESS		=	queryProps.getProperty(Constants.SQL_SURVEY_ACCESS);
		RowMapper<AccessSurvey> mapper	=	new RowMapper<AccessSurvey>()
		{
			@Override
			public AccessSurvey mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				AccessSurvey	accessSurvey	=	new AccessSurvey();
				
				if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_EMP_COURSE).equals("Y"))
				{
					accessSurvey.setBooAccessEmpCourse(true);
				}
				if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_ALL_SURVEY).equals("Y"))
				{
					accessSurvey.setBooAccessAllSurvey(true);
				}
				if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_ASST_DEAN).equals("Y"))
				{
					accessSurvey.setBooAccessAssistDean(true);
				}
				if(rs.getString(Constants.CONST_COLMN_IS_ADMIN).equals("Y"))
				{
					accessSurvey.setBooAccessAdmin(true);
				}
				
				return accessSurvey;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramCourseCode", courseCode);
		namedParameterMap.put("paramSection", sectionNo);
		namedParameterMap.put("paramEmpNumber", empNumber);
		return namedParameterJdbcTemplate.queryForObject(SQL_SURVEY_ACCESS, namedParameterMap, mapper);
	}


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
	public AccessSurvey getAccessToSurveyReport(String empNumber)
	{
		String	SQL_SURVEY_ACCESS_REPORT	=	queryProps.getProperty(Constants.SQL_SURVEY_ACCESS_REPORT);
		RowMapper<AccessSurvey> mapper = new RowMapper<AccessSurvey>()
				{

					@Override
					public AccessSurvey mapRow(ResultSet rs, int rowNum)
							throws SQLException
					{
						AccessSurvey	accessSurvey	=	new AccessSurvey();
						if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_REPORT).equals("Y"))
						{
							accessSurvey.setBooAccesSurveyReport(true);
						}
						if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_ASST_DEAN).equals("Y"))
						{
							accessSurvey.setBooAccessAssistDean(true);
						}
						if(rs.getString(Constants.CONST_COLMN_IS_ACCESS_ALL_SURVEY).equals("Y"))
						{
							accessSurvey.setBooAccessAllSurvey(true);
						}
						if(rs.getString(Constants.CONST_COLMN_IS_ADMIN).equals("Y"))
						{
							accessSurvey.setBooAccessAdmin(true);
						}
						
						return accessSurvey;
					}
			
				};
				Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
				namedParameterMap.put("paramEmpNumber", empNumber);
				
				
				return namedParameterJdbcTemplate.queryForObject(SQL_SURVEY_ACCESS_REPORT, namedParameterMap, mapper);
	}
	
	
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
	public List<StudentResponse> getStudentResponses(String empNumber, Locale locale )
	{
		String SQL_CURRENT_STUDENT_RESPONSE_FOR_FACULTY		=	queryProps.getProperty(Constants.SQL_CURRENT_STUDENT_RESPONSE_FOR_FACULTY);
		
		RowMapper<StudentResponse> mapper	=	new RowMapper<StudentResponse>()
		{
			
			@Override
			public StudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StudentResponse		studentResponse	=	new StudentResponse();
				studentResponse.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE).trim());
				studentResponse.setCourseName(rs.getString(Constants.CONST_COLMN_COURSE_NAME).trim());
				studentResponse.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO).trim());
				studentResponse.setSeatsTaken(rs.getInt(Constants.CONST_COLMN_SEATS_TAKEN));
				studentResponse.setStudentResponse(rs.getInt(Constants.CONST_COLMN_STUDENT_RESPONSE));
				studentResponse.setYearSemester(rs.getString(Constants.CONST_COLMN_YR_SEM).trim());
				studentResponse.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				return studentResponse;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramLocale", locale.getLanguage());
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.query(SQL_CURRENT_STUDENT_RESPONSE_FOR_FACULTY, namedParameterMap, mapper);
	}
	
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
	public List<StudentResponse> getAllSurveys(String empNumber)
	{
		String	SQL_SURVEY_ALL		=	queryProps.getProperty(Constants.SQL_SURVEY_ALL);
		
		RowMapper<StudentResponse> mapper = new RowMapper<StudentResponse>()
		{
			
			@Override
			public StudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StudentResponse	studentResponse	=	new StudentResponse();
				
				studentResponse.setAcademicYear(rs.getString(Constants.CONST_COLMN_ACADEMIC_YEAR));
				studentResponse.setYearSemester(rs.getString(Constants.CONST_COLMN_YR_SEM));
				studentResponse.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				studentResponse.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				studentResponse.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				return studentResponse;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.query(SQL_SURVEY_ALL, namedParameterMap, mapper);
	}
	
	
	
	
	
	
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
	public List<Analysis> getTeachingAnalysis(String empNumber, String courseCode, String semesterCode, String sectionNo)
	{
		String				SQL_TEACHING_SURVEY_ANALYSIS		=	queryProps.getProperty(Constants.SQL_TEACHING_SURVEY_ANALYSIS);
		
		RowMapper<Analysis> mapper								=	new RowMapper<Analysis>()
		{
			
			@Override
			public Analysis mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Analysis	analysis	=	new Analysis();
				analysis.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				analysis.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				analysis.setQuestion(rs.getString(Constants.CONST_COLMN_QUESTION_NO));
				analysis.setStrongDisagree(rs.getInt(Constants.CONST_COLMN_STRONG_DISAGREE));
				analysis.setDisAgree(rs.getInt(Constants.CONST_COLMN_DISAGREE));
				analysis.setAgree(rs.getInt(Constants.CONST_COLMN_AGREE));
				analysis.setStrongAgree(rs.getInt(Constants.CONST_COLMN_STRONG_AGREE));
				analysis.setNotApplicable(rs.getInt(Constants.CONST_COLMN_NOT_APPLICABLE));
				analysis.setTotal(rs.getInt(Constants.CONST_COLMN_TOTAL));
				analysis.setPercentageResponse(rs.getFloat(Constants.CONST_COLMN_PERCENTAGE_RESPONSE));
				analysis.setSectionMean(rs.getFloat(Constants.CONST_COLMN_SECTION_MEAN));
				analysis.setCourseMean(rs.getFloat(Constants.CONST_COLMN_COURSE_MEAN));
				analysis.setDepartmentMean(rs.getFloat(Constants.CONST_COLMN_DEPARTMENT_MEAN));
				analysis.setCollegeMean(rs.getFloat(Constants.CONST_COLMN_COLLEGE_MEAN));
				analysis.setQuestionLabel(rs.getString(Constants.CONST_COLMN_QUEST_LABEL));
				return analysis;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramCourseCode", courseCode);
		namedParameterMap.put("paramSection", sectionNo);
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.query(SQL_TEACHING_SURVEY_ANALYSIS, namedParameterMap, mapper);

	}


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
	public	Survey  getSurVeyAnalysis(String empNumber, String courseCode, String semesterCode, String sectionNo, Locale locale)
	{
		String				SQL_SURVEY_ANALYSIS_HEADING		=	queryProps.getProperty(Constants.SQL_SURVEY_ANALYSIS_HEADING);
		
		RowMapper<Survey> 	mapper		=	new RowMapper<Survey>()
		{
			
			@Override
			public Survey mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Survey				survey				=	new Survey();
				SurveyResponse		surveyResponse		=	new SurveyResponse();
				List<SurveyResponse> surveyResponses	=	new ArrayList<SurveyResponse>();
				
				survey.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				survey.setCourseName(rs.getString(Constants.CONST_COLMN_COURSE_NAME));
				
				surveyResponse.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				surveyResponse.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				surveyResponse.setSeatsTaken(rs.getInt(Constants.CONST_COLMN_SEATS_TAKEN));
				
				survey.setEmpName(rs.getString(Constants.CONST_COLMN_EMP_NAME));
				survey.setCollegeName(rs.getString(Constants.CONST_COLMN_COLLEGE_NAME));
				survey.setDepartmentName(rs.getString(Constants.CONST_COLMN_DEPARTMENT_NAME));
				survey.setMessage(rs.getString(Constants.CONST_COLMN_SURVEY_MESSAGE).trim());
				
				surveyResponses.add(surveyResponse);
				survey.setSurveyResponses(surveyResponses);
				return survey;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramLocale", locale.getLanguage());
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramCourseCode", courseCode);
		namedParameterMap.put("paramSection", sectionNo);
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		
		return namedParameterJdbcTemplate.queryForObject(SQL_SURVEY_ANALYSIS_HEADING, namedParameterMap, mapper);
	}

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
	public List<OpenEndQuestion> getOpenEndQuestions(String empNumber, String courseCode, String semesterCode, String sectionNo)
	{
		String	SQL_SURVEY_OPEN_END_QUESTIONS	=	queryProps.getProperty(Constants.SQL_SURVEY_OPEN_END_QUESTIONS);
		RowMapper<OpenEndQuestion> mapper		=	new RowMapper<OpenEndQuestion>()
		{
			@Override
			public OpenEndQuestion mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OpenEndQuestion	question	=	new OpenEndQuestion();
				question.setQuestion1(rs.getString(Constants.CONST_COLMN_OPEN_END_QUESTION1));
				question.setQuestion2(rs.getString(Constants.CONST_COLMN_OPEN_END_QUESTION2));
				question.setQuestion3(rs.getString(Constants.CONST_COLMN_OPEN_END_QUESTION3));
				return question;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramCourseCode", courseCode);
		namedParameterMap.put("paramSection", sectionNo);
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.query(SQL_SURVEY_OPEN_END_QUESTIONS, namedParameterMap, mapper);
		
	}
	
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
	public List<ReportYrSem> getYearSemestersForReport()
	{
		String					SQL_SURVEY_REPORT_ACADEMIC_YEAR		=	queryProps.getProperty(Constants.SQL_SURVEY_REPORT_ACADEMIC_YEAR);
		RowMapper<ReportYrSem> 	mapper								=	new RowMapper<ReportYrSem>()
		{
			
			@Override
			public ReportYrSem mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ReportYrSem	reportYrSem	=	new ReportYrSem();
				reportYrSem.setAcademicYear(rs.getString(Constants.CONST_COLMN_ACADEMIC_YEAR));
				reportYrSem.setYearSemester(rs.getString(Constants.CONST_COLMN_YR_SEM));
				
				return reportYrSem;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		
		return namedParameterJdbcTemplate.query(SQL_SURVEY_REPORT_ACADEMIC_YEAR, namedParameterMap, mapper);
	}

	
	
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
	public StaffRole  getStaffRole(String empNumber)
	{
		String					SQL_STAFF_ROLE			=	queryProps.getProperty(Constants.SQL_STAFF_ROLE);
		
		RowMapper<StaffRole> 	mapper					=	new RowMapper<StaffRole>()
		{
			
			@Override
			public StaffRole mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StaffRole	staffRole	=	new StaffRole();
				staffRole.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				staffRole.setStaffRole(rs.getString(Constants.CONST_COLMN_STAFF_ROLE));
				staffRole.setCollegeCode(rs.getString(Constants.CONST_COLMN_COLLEGE_CODE));
				staffRole.setDepartmentCode(rs.getString(Constants.CONST_COLMN_DEPARTMENT_CODE));
				return staffRole;
			}
		};				
		
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.queryForObject(SQL_STAFF_ROLE, namedParameterMap, mapper);
	}
	
	
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
	public List<ReportSummary>  getValidReportSummaries(String empNumber, String semesterCode, String question, Locale locale)
	{
		String SQL_VALID_SURVEY_REPORT_SUMMARY		=	queryProps.getProperty(Constants.SQL_VALID_SURVEY_REPORT_SUMMARY);
		
		RowMapper<ReportSummary> mapper			=	new RowMapper<ReportSummary>()
		{
			
			@Override
			public ReportSummary mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ReportSummary	reportSummary	=	new ReportSummary();
				reportSummary.setSerialNum(rs.getString(Constants.CONST_COLMN_SERIAL_NO));
				reportSummary.setYearSemester(rs.getString(Constants.CONST_COLMN_YR_SEM));
				reportSummary.setUniversityRank(rs.getInt(Constants.CONST_COLMN_UNIVERSITY_RANK));
				reportSummary.setCollegeRank(rs.getInt(Constants.CONST_COLMN_COLLEGE_RANK));
				reportSummary.setDepartmentRank(rs.getInt(Constants.CONST_COLMN_DEPARTMENT_RANK));
				reportSummary.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				reportSummary.setEmpName(rs.getString(Constants.CONST_COLMN_EMP_NAME));
				reportSummary.setCollegeCode(rs.getString(Constants.CONST_COLMN_COLLEGE_CODE));
				reportSummary.setDepartmentName(rs.getString(Constants.CONST_COLMN_DEPARTMENT_NAME));
				reportSummary.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				reportSummary.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				reportSummary.setRegisteredStudent(rs.getInt(Constants.CONST_COLMN_REGISTERED_STUDENT));
				reportSummary.setStudentNoResponse(rs.getInt(Constants.CONST_COLMN_STUDENT_NO_RESPONSE));
				reportSummary.setTeachingMean(rs.getFloat(Constants.CONST_COLMN_TEACHING_MEAN));
				reportSummary.setTeachingPercentageFavor(rs.getFloat(Constants.CONST_COLMN_TEACHING_PERCENTAGE_FAV));
				reportSummary.setQuestionMean(rs.getFloat(Constants.CONST_COLMN_QUESTION_MEAN));
				reportSummary.setQuestionPercentageFavor(rs.getFloat(Constants.CONST_COLMN_QUESTION_PERCENTAGE_FAV));
				return reportSummary;
			}
		};
		
		StaffRole	staffRole	=	getStaffRole(empNumber);
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramEmpNumber", empNumber);
		namedParameterMap.put("paramStaffRole", staffRole.getStaffRole());
		namedParameterMap.put("paramCollege", staffRole.getCollegeCode());
		namedParameterMap.put("paramDepartment", staffRole.getDepartmentCode());
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramLocale", locale.getLanguage());
		namedParameterMap.put("paramQuestion", question);
		
		return namedParameterJdbcTemplate.query(SQL_VALID_SURVEY_REPORT_SUMMARY, namedParameterMap, mapper);
	}

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
	public List<ReportSummary>  getNotValidReportSummaries(String empNumber, String semesterCode, String question, Locale locale)
	{
		String SQL_NOT_VALID_SURVEY_REPORT_SUMMARY		=	queryProps.getProperty(Constants.SQL_NOT_VALID_SURVEY_REPORT_SUMMARY);
		
		RowMapper<ReportSummary> 	mapper				=	new RowMapper<ReportSummary>()
		{
			
			@Override
			public ReportSummary mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ReportSummary	reportSummary	=	new ReportSummary();
				reportSummary.setSerialNum(rs.getString(Constants.CONST_COLMN_SERIAL_NO));
				reportSummary.setYearSemester(rs.getString(Constants.CONST_COLMN_YR_SEM));
				reportSummary.setCollegeCode(rs.getString(Constants.CONST_COLMN_COLLEGE_CODE));
				reportSummary.setDepartmentName(rs.getString(Constants.CONST_COLMN_DEPARTMENT_NAME));
				reportSummary.setDepartmentCode(rs.getString(Constants.CONST_COLMN_DEPARTMENT_CODE));
				reportSummary.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				reportSummary.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				reportSummary.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				reportSummary.setEmpName(rs.getString(Constants.CONST_COLMN_EMP_NAME));
				reportSummary.setRegisteredStudent(rs.getInt(Constants.CONST_COLMN_REGISTERED_STUDENT));
				reportSummary.setStudentNoResponse(rs.getInt(Constants.CONST_COLMN_STUDENT_NO_RESPONSE));
				reportSummary.setTeachingMean(rs.getFloat(Constants.CONST_COLMN_TEACHING_MEAN));
				reportSummary.setTeachingPercentageFavor(rs.getFloat(Constants.CONST_COLMN_TEACHING_PERCENTAGE_FAV));
				reportSummary.setQuestionMean(rs.getFloat(Constants.CONST_COLMN_QUESTION_MEAN));
				reportSummary.setQuestionPercentageFavor(rs.getFloat(Constants.CONST_COLMN_QUESTION_PERCENTAGE_FAV));
				return reportSummary;
			}
		};
		StaffRole	staffRole	=	getStaffRole(empNumber);
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramEmpNumber", empNumber);
		namedParameterMap.put("paramStaffRole", staffRole.getStaffRole());
		namedParameterMap.put("paramCollege", staffRole.getCollegeCode());
		namedParameterMap.put("paramDepartment", staffRole.getDepartmentCode());
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramQuestion", question);
		namedParameterMap.put("paramLocale", locale.getLanguage());
		return namedParameterJdbcTemplate.query(SQL_NOT_VALID_SURVEY_REPORT_SUMMARY, namedParameterMap, mapper);
	}
	
	/**
	 * 
	 * method name  : getCollegeCoursesForAsstDean
	 * @param empNumber
	 * @param locale
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : List<StudentResponse>
	 * 
	 * purpose		: get list of college courses (can be viewed by assistant dean)
	 *
	 * Date    		:	Sep 10, 2015 8:31:37 AM
	 */

	public List<StudentResponse> getCollegeCoursesForAsstDean(String empNumber, Locale locale)
	{
		String SQL_COLLEGE_COURSES_FOR_ASST_DEAN		=	queryProps.getProperty(Constants.SQL_COLLEGE_COURSES_FOR_ASST_DEAN);
		RowMapper<StudentResponse>  mapper				=	new RowMapper<StudentResponse>()
		{
			
			@Override
			public StudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StudentResponse	studentResponse	=	new StudentResponse();
				studentResponse.setDepartmentCode(rs.getString(Constants.CONST_COLMN_DEPARTMENT_CODE));
				studentResponse.setDepartmentName(rs.getString(Constants.CONST_COLMN_DEPARTMENT_NAME));
				studentResponse.setIncludeExclude(rs.getString(Constants.CONST_COLMN_INCLUDE_EXCLUDE));
				studentResponse.setCourseCode(rs.getString(Constants.CONST_COLMN_COURSE_CODE));
				studentResponse.setSectionNo(rs.getString(Constants.CONST_COLMN_SECTION_NO));
				studentResponse.setEmpNumber(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				studentResponse.setEmpName(rs.getString(Constants.CONST_COLMN_EMP_NAME));
				studentResponse.setSeatsTaken(rs.getInt(Constants.CONST_COLMN_SEATS_TAKEN));
				studentResponse.setStudentResponse(rs.getInt(Constants.CONST_COLMN_STUDENT_RESPONSE));
				studentResponse.setIncludeExclude(rs.getString(Constants.CONST_COLMN_INCLUDE_EXCLUDE));
				
				return studentResponse;
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramLocale", locale.getLanguage());
		namedParameterMap.put("paramEmpNumber", empNumber);
		
		return namedParameterJdbcTemplate.query(SQL_COLLEGE_COURSES_FOR_ASST_DEAN, namedParameterMap, mapper);
	}

	
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
	public List<CommitteeMember> getCommitteeMembers(Locale locale)
	{
		String SQL_COMMITTEE_MEMBERS_IN		=	queryProps.getProperty(Constants.SQL_COMMITTEE_MEMBERS_IN);
		RowMapper<CommitteeMember> mapper	=	new RowMapper<CommitteeMember>()
		{

			@Override
			public CommitteeMember mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				CommitteeMember	committeeMember	=	new CommitteeMember();
				committeeMember.setEmpNo(rs.getString(Constants.CONST_COLMN_EMP_NUMBER));
				committeeMember.setEmpName(rs.getString(Constants.CONST_COLMN_EMP_NAME));
				committeeMember.setRole(rs.getString(Constants.CONST_COLMN_COMMITTEE_ROLE));
				return committeeMember;
			}
			
		};
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramLocale", locale.getLanguage());
		
		return namedParameterJdbcTemplate.query(SQL_COMMITTEE_MEMBERS_IN, namedParameterMap, mapper);
	}
	
	
	/**
	 * 
	 * method name  : getCurrentYrSemAdmin
	 * @return
	 * TeachingSurveyDbImpl
	 * return type  : String
	 * 
	 * purpose		: Get current yr semester for administrator
	 *                !!!!! --- IMPORTANT ---  Use this method before any admin work !!!!!
	 *
	 * Date    		:	Sep 20, 2015 12:34:42 PM
	 */
	public String getCurrentYrSemAdmin()
	{
		String SQL_CURRENT_YRSEM_ADMIN		=	queryProps.getProperty(Constants.SQL_CURRENT_YRSEM_ADMIN);
		RowMapper<String> mapper	=	new RowMapper<String>()
		{
			
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return rs.getString(Constants.CONST_COLMN_YR_SEM);
			}
		};
		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		
		return namedParameterJdbcTemplate.queryForObject(SQL_CURRENT_YRSEM_ADMIN, namedParameterMap, mapper);
	}

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
	public AccessSurvey getAccessViewRights()
	{
		String	SQL_SURVEY_ACCESS_VIEW_RIGHTS	=	queryProps.getProperty(Constants.SQL_SURVEY_ACCESS_VIEW_RIGHTS);
		RowMapper<AccessSurvey> 	mapper		=	new RowMapper<AccessSurvey>()
		{
			
			@Override
			public AccessSurvey mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				AccessSurvey	accessSurvey	=	new AccessSurvey();
				if(null == rs.getString(Constants.CONST_COLMN_COMMITTEE_VIEW_DATE))
				{
					accessSurvey.setBooViewCommitteeSurvey(false);
				}
				else
				{
					accessSurvey.setBooViewCommitteeSurvey(true);
					accessSurvey.setDateCommitteeView(rs.getString(Constants.CONST_COLMN_COMMITTEE_VIEW_DATE));
				}
				
				if(null == rs.getString(Constants.CONST_COLMN_OTHERS_VIEW_DATE))
				{
					accessSurvey.setBooViewOtherSurvey(false);
				}
				else
				{
					accessSurvey.setBooViewOtherSurvey(true);
					accessSurvey.setDateOthersView(rs.getString(Constants.CONST_COLMN_OTHERS_VIEW_DATE));
				}
				
				return accessSurvey;
			}
		};
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		
		return namedParameterJdbcTemplate.queryForObject(SQL_SURVEY_ACCESS_VIEW_RIGHTS, namedParameterMap, mapper);
	}
	
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
	public int setDateAdminCommitteeView(String semesterCode, String dateCommitteeView)
	{
		String	SQL_UPDATE_DATE_ADMIN_COMITTEE_VIEW		=	queryProps.getProperty(Constants.SQL_UPDATE_DATE_ADMIN_COMITTEE_VIEW);

		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramDate", ((dateCommitteeView.equals("na"))?null:dateCommitteeView));
		
		return namedParameterJdbcTemplate.update(SQL_UPDATE_DATE_ADMIN_COMITTEE_VIEW, namedParameterMap);
		
	}

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
	public int setDateAdminFacultyView(String semesterCode, String dateFacultyView)
	{
		String	SQL_UPDATE_DATE_ADMIN_FACULTY_VIEW		=	queryProps.getProperty(Constants.SQL_UPDATE_DATE_ADMIN_FACULTY_VIEW);

		
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramSemester", semesterCode);
		namedParameterMap.put("paramDate", ((dateFacultyView.equals("na"))?null:dateFacultyView));
		
		return namedParameterJdbcTemplate.update(SQL_UPDATE_DATE_ADMIN_FACULTY_VIEW, namedParameterMap);
	}
	
	
	/************************************************ SURVEY LOADING OPERATIONS ***************************************/ 
	
    /**
     * 
     * method name  : getSurveyStartDate
     * @return
     * StdRegCourseDaoImpl
     * return type  : StudentSurveyStartDay
     * 
     * purpose		: Get Student Survey start date 
     *
     * Date    		:	Nov 16, 2016 2:03:40 PM
     */
    public StudentSurveyStartDay getSurveyStartDate() 
    {
    	String SQL_STUDENT_SURVEY_START_DATE=queryProps.getProperty(Constants.SQL_STUDENT_SURVEY_START_DATE);
    	RowMapper<StudentSurveyStartDay> mapper	=	new RowMapper<StudentSurveyStartDay>()
		{
			
			@Override
			public StudentSurveyStartDay mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				StudentSurveyStartDay	surveyStartDay	=	new StudentSurveyStartDay();
				surveyStartDay.setSurveyDay(rs.getString(Constants.CONST_COLMN_SURVEY_START_DAY));
				surveyStartDay.setSurveyMonth(rs.getString(Constants.CONST_COLMN_SURVEY_START_MONTH));
				return surveyStartDay;
			}
		};
    	
    	Map<String,String> parameters=new HashMap<String, String>();
    	return namedParameterJdbcTemplate.queryForObject(SQL_STUDENT_SURVEY_START_DATE, parameters, mapper);
    }
    
   
	
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
	public synchronized String loadPreSurvey(StatementSqlBo  sqlBo)
	{
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		String	message	= "success";
		try
		{
			int resultTruncate = namedParameterJdbcTemplate.update(sqlBo.getSqlTruncate(), namedParameterMap);
		}
		catch(Exception ex)
		{
			message =	sqlBo.getMessage();
		}
		
		try
		{
			int resultInsert = namedParameterJdbcTemplate.update(sqlBo.getSqlInsert(), namedParameterMap);
		}
		catch(Exception ex)
		{
			message =	sqlBo.getMessage();
		}
		
		return message;
	}
	
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
	public boolean isPostSurveyAnalysisAvailable(String semesterCode )
	{
		String 				strResult;
		boolean				booLeanResult;
		String				SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK		=	queryPropsPostSurveyControl.getProperty(Constants.SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK);
		Map<String, String> namedParameterMap							=	new HashMap<String, String>();
		namedParameterMap.put("paramYrSem", semesterCode);
		strResult	=	 namedParameterJdbcTemplate.queryForObject(SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK, namedParameterMap, String.class);
		return strResult.equals("N")?true:false;
	}
	
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
	@Transactional("qAnalysis")
	public int postSurveyAnalysisAvailableUpdate(String semesterCode)
	{
		String	SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK_UPDATE				=	queryPropsPostSurveyControl.getProperty(Constants.SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK_UPDATE);
		Map<String, String> namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramYrSem", semesterCode);
		
		return  namedParameterJdbcTemplate.update(SQL_POST_SURVEY_ANALYSIS_PROCESS_CHECK_UPDATE, namedParameterMap);
	}
	
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
	@Transactional("qAnalysis")
	public int postSurveyStartAnalysis()
	{
		
		String	SQL_POST_SURVEY_ANALYSIS_PROCESS		=	queryPropsPostSurveyControl.getProperty(Constants.SQL_POST_SURVEY_ANALYSIS_PROCESS);
		/* Make Sure Current Year Semester is in correnct place for admin operation */
		Map<String, String> 	namedParameterMap	=	new HashMap<String, String>();
		
		return namedParameterJdbcTemplate.update(SQL_POST_SURVEY_ANALYSIS_PROCESS, namedParameterMap);
	}


	
	
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
	@Transactional("qAnalysis")
	public int postSurveyAnalysisExecuteSurveyProc(String semesterCode)
	{
		String	SQL_POST_SURVEY_ANALYSIS_EXE_TEACHING_SURVEY_PROC		=	queryPropsPostSurveyControl.getProperty(Constants.SQL_POST_SURVEY_ANALYSIS_EXE_TEACHING_SURVEY_PROC);
		Map<String, String> namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramYrSem", semesterCode);
		return  namedParameterJdbcTemplate.update(SQL_POST_SURVEY_ANALYSIS_EXE_TEACHING_SURVEY_PROC, namedParameterMap);
	}

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
	public int countSuccessAnalysisProcess(String semesterCode)
	{
		String	SQL_POST_SURVEY_ANALYSIS_PROC_COUNT_SUCCESS				=	queryPropsPostSurveyControl.getProperty(Constants.SQL_POST_SURVEY_ANALYSIS_PROC_COUNT_SUCCESS);
		Map<String, String> namedParameterMap	=	new HashMap<String, String>();
		namedParameterMap.put("paramYrSem", semesterCode);
		
		return namedParameterJdbcTemplate.queryForInt(SQL_POST_SURVEY_ANALYSIS_PROC_COUNT_SUCCESS, namedParameterMap);
	}
	


	
}
