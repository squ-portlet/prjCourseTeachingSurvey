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
 * File Name			:	TeachingSurveyServiceImpl.java
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
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import javax.xml.transform.stream.StreamSource;

import om.edu.squ.squportal.portlet.tsurvey.bo.AccessSurvey;
import om.edu.squ.squportal.portlet.tsurvey.bo.CommitteeMember;
import om.edu.squ.squportal.portlet.tsurvey.bo.Course;
import om.edu.squ.squportal.portlet.tsurvey.bo.CourseDetail;
import om.edu.squ.squportal.portlet.tsurvey.bo.Department;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportYrSem;
import om.edu.squ.squportal.portlet.tsurvey.bo.StaffRole;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentSurveyStartDay;
import om.edu.squ.squportal.portlet.tsurvey.bo.load.StatementSqlBo;
import om.edu.squ.squportal.portlet.tsurvey.bo.load.StatementSqlListBo;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.OpenEndQuestion;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyYear;
import om.edu.squ.squportal.portlet.tsurvey.dao.db.TeachingSurveyDbDao;
import om.edu.squ.squportal.portlet.tsurvey.dao.excel.TeachingSurveyExcelImpl;
import om.edu.squ.squportal.portlet.tsurvey.dao.pdf.TeachingSurveyPdfImpl;
import om.edu.squ.squportal.portlet.tsurvey.utility.Constants;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;

/**
 * @author Bhabesh
 *
 */
public class TeachingSurveyServiceImpl implements TeachingSurveyServiceDao
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UtilService	utilService;
	@Autowired
	TeachingSurveyDbDao	teachingSurveyDbDao;
	
	@Autowired
	@Qualifier("jaxb2Marshaller")
	private	 Unmarshaller unmarshaller;
	
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
		AccessSurvey	accessSurvey	=	new AccessSurvey();
	
		if(null != empNumber && null != courseCode && null != sectionNo)
		{
			accessSurvey	=	teachingSurveyDbDao.getAccessToSurvey(empNumber, courseCode, sectionNo);
		}
		else
		{
			accessSurvey=teachingSurveyDbDao.getAccessToSurveyReport(empNumber);
		}
		
		return accessSurvey;
	}
	
	
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
	public  String getEmployeeNumber(PortletRequest request)
	{
		return utilService.getEmployeeNumber(request);
	}
	
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
	public String getUserName(PortletRequest request)
	{
		return utilService.getUserName(request);
	}
	
	
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
	public List<Survey> getStudentResponses(String empNumber, Locale locale )
	{
		List<Survey> surveys	=	new ArrayList<Survey>();
		
		List<StudentResponse> studentResponses	=	teachingSurveyDbDao.getStudentResponses(empNumber, locale);
		
		//Get unique courses
		Set<String>	courseCodes	=	new HashSet<String>();
		for(StudentResponse response : studentResponses)
		{
			courseCodes.add(response.getCourseCode());
		}

		//For each courses get the survey results
		
		for (String courseCode : courseCodes)
		{
			Survey	survey	=	new Survey();
			for(StudentResponse studentResponse : studentResponses)
			{
				if(courseCode.equals(studentResponse.getCourseCode()))
				{
					survey.setCourseCode(studentResponse.getCourseCode());
					survey.setCourseName(studentResponse.getCourseName());
					survey.setYearSemester(studentResponse.getYearSemester());
					break;
				}

			}
			List<SurveyResponse> surveyResponses	=	new ArrayList<SurveyResponse>();
			
			for(StudentResponse studentResponse : studentResponses)
			{
				if (courseCode.equals(studentResponse.getCourseCode()))
				{
					SurveyResponse	surveyResponse	=	new SurveyResponse();
					surveyResponse.setCourseCode(studentResponse.getCourseCode());
					surveyResponse.setSectionNo(studentResponse.getSectionNo());
					surveyResponse.setSeatsTaken(studentResponse.getSeatsTaken());
					surveyResponse.setStudentResponse(studentResponse.getStudentResponse());
					surveyResponses.add(surveyResponse);
				}
			}
			
			survey.setSurveyResponses(surveyResponses);
			surveyResponses = null;
			
			surveys.add(survey);
		}
		return surveys;
	}
	
	
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
	public List<SurveyYear> getAllSurveys(String empNumber)
	{
		List<StudentResponse> studentResponses	=	teachingSurveyDbDao.getAllSurveys(empNumber);
		Set<SurveyYear> surveyYears	=	new LinkedHashSet<SurveyYear>();
		
		for(StudentResponse vote : studentResponses)
		{
			SurveyYear	surveyYear	=	new SurveyYear();
			surveyYear.setAcademicYear(vote.getAcademicYear());
			surveyYears.add(surveyYear);
		}
		
		for(SurveyYear surveyYear : surveyYears)
		{
			List<Survey> surveys	=	new ArrayList<Survey>();
			surveys.addAll(getSurveys(surveyYear.getAcademicYear(), studentResponses));
			surveyYear.setSurveys(surveys);
		}

		return new ArrayList<SurveyYear>(surveyYears);
	}
	
	
	
	
	/**
	 * 
	 * method name  : getSurveys
	 * @param acadamicYear
	 * @param studentResponses
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : Set<Survey>
	 * 
	 * purpose		: get surveys
	 *
	 * Date    		:	Aug 20, 2015 9:53:49 AM
	 */
	private Set<Survey> getSurveys(String acadamicYear, List<StudentResponse> studentResponses)
	{
		Set<Survey>	surveys	=	new LinkedHashSet<Survey>();
		for (StudentResponse vote : studentResponses)
		{
			if(acadamicYear.equals(vote.getAcademicYear()))
			{
				List<SurveyResponse>	surveyResponses	=	new ArrayList<SurveyResponse>();
				Survey					survey			=	new Survey();
				survey.setCourseCode(vote.getCourseCode());
				survey.setCourseName(vote.getCourseName());
				survey.setYearSemester(vote.getYearSemester());
				survey.setEmpNumber(vote.getEmpNumber());
				
				for(StudentResponse election : studentResponses)
				{
					if(
							acadamicYear.equals(vote.getAcademicYear()) && 
							vote.getCourseCode().equals(election.getCourseCode()) && 
							vote.getYearSemester().equals(election.getYearSemester())
						)
					{
						SurveyResponse	surveyResponse	=	new SurveyResponse();
						surveyResponse.setSectionNo(election.getSectionNo());
						surveyResponse.setCourseCode(election.getCourseCode());
						surveyResponse.setSeatsTaken(election.getSeatsTaken());
						surveyResponse.setCourseCode(election.getCourseCode());
						
						surveyResponses.add(surveyResponse);
					}
				}
				survey.setSurveyResponses(surveyResponses);
				surveys.add(survey);
				surveyResponses = null;
			}
		}
		
		return surveys;
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
		List<SurveyResponse> surveyResponses	=	new ArrayList<SurveyResponse>();
		Survey				survey				=	null;
		SurveyResponse		surveyResponse		=	null;
		AccessSurvey		accessSurvey		=	getAccessToSurvey(empNumber, courseCode, sectionNo);
		
		if(accessSurvey.isBooAccessEmpCourse() || accessSurvey.isBooAccessAllSurvey())
		{
							survey				=	teachingSurveyDbDao.getSurVeyAnalysis(empNumber, courseCode, semesterCode, sectionNo, locale);
							surveyResponse		=	survey.getSurveyResponses().get(0);	
				surveyResponse.setAnalysisList(teachingSurveyDbDao.getTeachingAnalysis(empNumber, courseCode, semesterCode, sectionNo));
				surveyResponses.add(surveyResponse);
				
				survey.setEmpNumber(empNumber);
				survey.setSurveyResponses(null);
				survey.setSurveyResponses(surveyResponses);
		}
		return survey;
	}
	
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
	public String getSemesterYear(String semesterCode, Locale locale)
	{
		String semesterYear	=	null;
		if(semesterCode.endsWith("SP"))
		{
			semesterYear = UtilProperty.getMessage("prop.course.teaching.survey.spring", null, locale);
		}
		if(semesterCode.endsWith("FL"))
		{
			semesterYear = UtilProperty.getMessage("prop.course.teaching.survey.fall", null, locale);
		}
		if(semesterCode.endsWith("SU"))
		{
			semesterYear = UtilProperty.getMessage("prop.course.teaching.survey.summer", null, locale);
		}
		
		semesterYear = semesterYear + " 20"+semesterCode.substring(0, 2);
		
		return semesterYear;
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
		List<OpenEndQuestion>	openEndQuestions	=	null;
		
		AccessSurvey			accessSurvey		=	getAccessToSurvey(empNumber, courseCode, sectionNo);
		
		if(accessSurvey.isBooAccessEmpCourse())
		{
			openEndQuestions	=	teachingSurveyDbDao.getOpenEndQuestions(empNumber, courseCode, semesterCode, sectionNo);
		}
		return openEndQuestions;
	}
	

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
	public List<SurveyYear> getAllYearSemestersForReport()
	{
		List<ReportYrSem> 	yrSems		=	teachingSurveyDbDao.getYearSemestersForReport();
		
		Set<SurveyYear>		surveyYears	=	new LinkedHashSet<SurveyYear>();	
		for(ReportYrSem yearSemester : yrSems)
		{
			SurveyYear	surveyYear	=	new SurveyYear();
			surveyYear.setAcademicYear(yearSemester.getAcademicYear());
			
			surveyYears.add(surveyYear);
		}
		
		
		for(SurveyYear surveyYear : surveyYears)
		{
			List<Survey> surveys	=	new ArrayList<Survey>();
			for(ReportYrSem yearSemester : yrSems)
			{
				
				if (surveyYear.getAcademicYear().equals(yearSemester.getAcademicYear()))
				{
					Survey		survey		=	new Survey();
					survey.setYearSemester(yearSemester.getYearSemester());
					surveys.add(survey);
				}
			}
			
			if(surveys.size() != 0)
			{
				surveyYear.setSurveys(surveys);
			}
				
		}
		
		return new ArrayList<SurveyYear>(surveyYears);
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
		return teachingSurveyDbDao.getStaffRole(empNumber);
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
	public List<ReportSummary>  getValidReportSummaries(String empNumber, String semesterCode, Locale locale)
	{
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		String		question		=	null;
		
		if(year2digit > 10)
		{
			question	=	"Q15";
		}
		else
		{
			question	=	"Q16";
		}
		
		return teachingSurveyDbDao.getValidReportSummaries(empNumber, semesterCode, question, locale);
	}

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
	public String getValidReportSummariesJSON(String empNumber, String semesterCode, Locale locale)
	{
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		String		question		=	null;
		
		if(year2digit > 10)
		{
			question	=	"Q15";
		}
		else
		{
			question	=	"Q16";
		}
		
		
		String				strJson		=	null;
		Gson 				gson 		= 	new Gson();
							strJson	=	gson.toJson(teachingSurveyDbDao.getValidReportSummaries(empNumber, semesterCode, question, locale));
		return strJson;
	}
	
	
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
	public List<ReportSummary>  getNotValidReportSummaries(String empNumber, String semesterCode, Locale locale)
	{
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		String		question		=	null;
		
		if(year2digit > 10)
		{
			question	=	"Q15";
		}
		else
		{
			question	=	"Q16";
		}
		return teachingSurveyDbDao.getNotValidReportSummaries(empNumber, semesterCode, question, locale);
	}
	
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
	public String  getNotValidReportSummariesJSON(String empNumber, String semesterCode, Locale locale)
	{
		String				strJson		=	null;
		Gson 				gson 		= 	new Gson();
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		String		question		=	null;
		
		if(year2digit > 10)
		{
			question	=	"Q15";
		}
		else
		{
			question	=	"Q16";
		}
		
		strJson	=	gson.toJson( teachingSurveyDbDao.getNotValidReportSummaries(empNumber, semesterCode, question, locale));
		
		return strJson;
	}
	
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
	public List<StudentResponse> getCollegeCoursesForAsstDean(String empNumber, Locale locale)
	{
		return teachingSurveyDbDao.getCollegeCoursesForAsstDean(empNumber, locale);
	}

	
	/**
	 * 
	 * method name  : getCollegeCoursesForAsstDeanJSON
	 * @param empNumber
	 * @return
	 * TeachingSurveyServiceImpl
	 * return type  : List<Department>
	 * 
	 * purpose		: get list of department wise college courses (can be viewed by assistant dean) in JSON format
	 *
	 * Date    		:	Sep 10, 2015 9:42:01 AM
	 */
	public String getCollegeCoursesForAsstDeanJSON(String empNumber, Locale locale)
	{
		List<StudentResponse> 	studentResponses	=	teachingSurveyDbDao.getCollegeCoursesForAsstDean(empNumber, locale);
		Set<Department> 		departments			=	new LinkedHashSet<Department>();

		/*get departments*/
		for(StudentResponse response : studentResponses)
		{
			Department	department	=	new Department();
			department.setDepartmentCode(response.getDepartmentCode());
			department.setDepartmentName(response.getDepartmentName());
			departments.add(department);
		}
		
		/*get courses for each departments*/
		for(Department department : departments)
		{
			List<Course> courses	=	new ArrayList<Course>();
			for(StudentResponse response : studentResponses)
			{
				
				if(department.getDepartmentCode().equals(response.getDepartmentCode()))
				{
					Course	course	=	new Course();
					course.setDepartmentCode(department.getDepartmentCode());
					course.setCourseCode(response.getCourseCode());
					course.setSectionNo(response.getSectionNo());
					course.setEmpNumber(response.getEmpNumber());
					course.setEmpName(response.getEmpName());
					course.setSeatsTaken(response.getSeatsTaken());
					course.setStudentResponse(response.getStudentResponse());
					course.setIncludeExclude(response.getIncludeExclude());
					courses.add(course);
				}
			}
			department.setChildren(courses);
		}
		
		/* get course details for each course and each department*/
/*		
		for(Department department : departments)
		{
			Set<Course> courses	=	department.getChildren();
			
			for(Course course : courses)
			{
				List<CourseDetail> courseDetails	=	new ArrayList<CourseDetail>();
				for(StudentResponse response : studentResponses)
				{
					if(department.getDepartmentCode().equals(response.getDepartmentCode()))
					{
						if(course.getCourseCode().equals(response.getCourseCode()))
						{
							CourseDetail	courseDetail	=	new CourseDetail();
							courseDetail.setDepartmentCode(department.getDepartmentCode());
							courseDetail.setCourseCode(response.getCourseCode());
							courseDetail.setSectionNo(response.getSectionNo());
							courseDetail.setEmpNumber(response.getEmpNumber());
							courseDetail.setEmpName(response.getEmpName());
							courseDetail.setSeatsTaken(response.getSeatsTaken());
							courseDetail.setStudentResponse(response.getStudentResponse());
							courseDetail.setIncludeExclude(response.getIncludeExclude());
							courseDetails.add(courseDetail);
						}
					}
				}
				course.setChildren(courseDetails);
			}
			
			department.setChildren(courses);
		}
*/		
		String				strJson		=	null;
		Gson 				gson 		= 	new Gson();
		strJson	=	gson.toJson(new ArrayList<Department>(departments));
		
		
		return strJson;
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
		return teachingSurveyDbDao.getCommitteeMembers(locale);
	}
	
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
	public String getCurrentYrSemAdmin()
	{
		return teachingSurveyDbDao.getCurrentYrSemAdmin();
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
		return teachingSurveyDbDao.getAccessViewRights();
	}

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
	public String getCronStudentSurveyStart()
	{
		String 		cronExpression	=	null;
		// Following are example of cron expression
		//cron="5 * * * * *" -- Every fifth seconds of each minute
		//
		// 0 30 10 15 4 FRI
		// seconds 0,  minute 30,hour 10,day of month 15,month 4 (april),day of week (friday)
		StudentSurveyStartDay	studentSurveyStartDay	 = 	teachingSurveyDbDao.getSurveyStartDate();
								cronExpression			=	"0 01 01 "+studentSurveyStartDay.getSurveyDay()+" "+studentSurveyStartDay.getSurveyMonth()+" ?";
		
		
		return cronExpression;
	}
	
	
	/**
	 * 
	 * method name  : getPreSurveyQueries
	 * @return
	 * @throws IOException
	 * TeachingSurveyServiceImpl
	 * return type  : StatementSqlListBo
	 * 
	 * purpose		: Load queries for presurvey loading operation
	 *
	 * Date    		:	Nov 5, 2015 12:23:38 PM
	 */
	public StatementSqlListBo getPreSurveyQueries() throws IOException
	{
		Resource			resource	=	new ClassPathResource(Constants.CONST_FILE_XML_SQL_PRESURVEY_LOAD);
		InputStream			stream		=	resource.getInputStream();
				
		
		return (StatementSqlListBo) unmarshaller.unmarshal(new StreamSource(stream));
	}
	
	
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
	public String	loadPreSurvey() throws IOException
	{
		//TODO Check dates etc
		if(true)
		{
			StatementSqlListBo	sqlListBo	=	getPreSurveyQueries();
			if(null != sqlListBo)
			{
				try
				{
					for(StatementSqlBo sqlBo : sqlListBo.getSqlBos())
					{
						logger.info("success in loading survey data");
						teachingSurveyDbDao.loadPreSurvey(sqlBo);
					}
				}
				catch (Exception ex)
				{
					logger.error("failure in pre loading survey data");
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * method name  : getPdfContent
	 * @param strTemplateName
	 * @param byos
	 * @param res
	 * @param locale
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * TeachingSurveyServiceImpl
	 * return type  : OutputStream
	 * 
	 * purpose		: Get PDF Content
	 *
	 * Date    		:	Feb 15, 2016 12:56:06 PM
	 */
	public OutputStream getPdfContent(String strTemplateName, Object object, ByteArrayOutputStream	byos, String semesterYear, String questionByYear, int questionSetNo, ResourceResponse res, Locale locale) throws IOException, DocumentException
	{
		Resource				resource		=	null;
		InputStream				inputStream		=	null;
		TeachingSurveyPdfImpl	pdfImpl			=	new TeachingSurveyPdfImpl();
		OutputStream			outputStream	=	null;
		
		if(strTemplateName.equals(Constants.CONST_SURVEY_ANALYSIS))
		{
			
					resource		=	new ClassPathResource(Constants.CONST_FILE_PDF_TEMPLATE_SURVEY_ANALYSIS);
					inputStream		=	resource.getInputStream();
					outputStream	=	pdfImpl.getPdfSurveyAnalysis(object,semesterYear,questionByYear,questionSetNo, byos,inputStream, res);
			
		}
		
		return outputStream;
	}

	
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
	public OutputStream getExcelContent(String strTemplateName, ResourceResponse response, Object object, Map<String, String> params, Locale locale) throws DocumentException, IOException
	{
		TeachingSurveyExcelImpl	excelImpl		=	new TeachingSurveyExcelImpl();
		OutputStream			outputStream	=	null;
		
		if(strTemplateName.equals(Constants.CONST_VALID_SURVEY_REPORT))
		{
			outputStream	=	excelImpl.getExcelSurveyReport(Constants.CONST_VALID_SURVEY_REPORT,object, response, params, locale);
		}
		if(strTemplateName.equals(Constants.CONST_INVALID_SURVEY_REPORT))
		{
			outputStream	=	excelImpl.getExcelSurveyReport(Constants.CONST_INVALID_SURVEY_REPORT,object, response, params, locale);
		}
		if(strTemplateName.equals(Constants.CONST_COL_COURSES_ASST_DEAN))
		{
			outputStream	=	excelImpl.getExcelCollegeCoursesAsstDean(Constants.CONST_COL_COURSES_ASST_DEAN,object, response, params, locale);
		}
		
		
		
		return outputStream;
		
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
		return teachingSurveyDbDao.isPostSurveyAnalysisAvailable(semesterCode);
	}
	
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
	public boolean postSurveyStartAnalysis(String semesterYear)
	{
		boolean booResult	=	true;
		if( isPostSurveyAnalysisAvailable(semesterYear) && teachingSurveyDbDao.countSuccessAnalysisProcess(semesterYear) == 0)
		{
			teachingSurveyDbDao.postSurveyStartAnalysis();		// Copy the records
			teachingSurveyDbDao.postSurveyAnalysisExecuteSurveyProc(semesterYear); // Call the stored procedure for analysis
			teachingSurveyDbDao.postSurveyAnalysisAvailableUpdate(semesterYear); // update to notify finish of analysis process
		}
		
		  if( (teachingSurveyDbDao.countSuccessAnalysisProcess(semesterYear) != 0) && ! teachingSurveyDbDao.isPostSurveyAnalysisAvailable(semesterYear))
		  {
			  booResult	= false;
		  }
		  return booResult;
	}
	
	
	
	
}
