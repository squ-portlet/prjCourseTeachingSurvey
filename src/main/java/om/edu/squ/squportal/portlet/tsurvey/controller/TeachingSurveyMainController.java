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
 * File Name			:	TeachingSurveyMainController.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.controller
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
package om.edu.squ.squportal.portlet.tsurvey.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import java.util.Random;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import om.edu.squ.squportal.portlet.tsurvey.bo.AccessSurvey;
import om.edu.squ.squportal.portlet.tsurvey.bo.Param;
import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.Staff;
import om.edu.squ.squportal.portlet.tsurvey.bo.StaffRole;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyYear;
import om.edu.squ.squportal.portlet.tsurvey.dao.service.TeachingSurveyServiceDao;
import om.edu.squ.squportal.portlet.tsurvey.utility.Constants;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilRenderTrack;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;

/**
 * @author Bhabesh
 *
 */
@Controller
@RequestMapping("VIEW")
public class TeachingSurveyMainController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TeachingSurveyServiceDao	teachingSurveyServiceDao;

	/**
	 * 
	 * method name  : welcome
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: Default render method
	 *
	 * Date    		:	Aug 31, 2015 1:10:39 PM
	 */
	@RequestMapping
	private String welcome(PortletRequest request,  Model model, Locale locale)
	{
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		AccessSurvey		accessSurvey	=	teachingSurveyServiceDao.getAccessToSurvey(empNumber, null, null);
		PortletSession 		session 		=	request.getPortletSession();
		
		if(session.getAttribute(Constants.CONST_MAPTRACK)!=null)
		{
			session.setAttribute(Constants.CONST_MAPTRACK, null);
		}
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "backToMain", UtilProperty.getMessage("prop.course.teaching.survey.link.welcome", null, locale),null));
		
		if(accessSurvey.isBooAccessAllSurvey())
		{
			
			if(accessSurvey.isBooAccessAdmin())
			{
				model.addAttribute("isAdmin", true);
				model.addAttribute("colAdjust", 3);
			}
			else
			{
				model.addAttribute("isAdmin", false);
				model.addAttribute("colAdjust", 4);
			}
			return committeeMemberWelcome(request,model,locale);
		}
		else
		{
			if(accessSurvey.isBooAccesSurveyReport())
			{
				return "welcome/welcome";
			}
			else 
			{
				if(accessSurvey.isBooAccessAssistDean())
				{
					return assistantDeanWelcome(request, model, locale);
				}
				else
				{
					return facultyWelcome(request, model, locale);
				}
			}
		}
	}

	/**
	 * 
	 * method name  : facultyWelcome
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render for survey summary for faculty
	 *
	 * Date    		:	Aug 31, 2015 1:10:32 PM
	 */
	@RequestMapping(params="action=facultyWelcome")
	private String facultyWelcome( 
									PortletRequest request, Model model, Locale locale
								)
	{
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		List<Survey> 		currentSurveys	=	teachingSurveyServiceDao.getStudentResponses(empNumber, locale);
		List<SurveyYear>	surveyYears		=	teachingSurveyServiceDao.getAllSurveys(empNumber);
		AccessSurvey		accessViewRights=	teachingSurveyServiceDao.getAccessViewRights();
		
		model.addAttribute(Constants.CONST_MODEL_ATTR_CURRENT_RESPONSES, currentSurveys);
		model.addAttribute(Constants.CONST_MODEL_ATTR_ALL_SURVEY, surveyYears);
		
		model.addAttribute(Constants.CONST_MODEL_CURR_SEMESTER, teachingSurveyServiceDao.getCurrentYrSemAdmin());
		
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "facultyWelcome", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.summary", null, locale),null));
		try
		{
			if(accessViewRights.isBooViewOtherSurvey() && new UtilService().dateCompare(new Date(),accessViewRights.getDateOthersView()) >= 0)
			{
				model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, accessViewRights.isBooViewOtherSurvey());
			}
			else
			{
				model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, false);
			}
		}
		catch (ParseException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return "welcome/facultyWelcome";
	}
	
	/**
	 * 
	 * method name  : assistantDeanWelcome
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render method for assistant dean
	 *
	 * Date    		:	Sep 9, 2015 9:22:38 AM
	 */
	@RequestMapping(params="action=assistantDeanWelcome")
	private	String assistantDeanWelcome(PortletRequest request, Model model, Locale locale)
	{
		
		return "welcome/assistantDeanWelcome";
	}

	@RequestMapping(params="action=committeeMemberWelcome")
	private	String committeeMemberWelcome(PortletRequest request, Model model, Locale locale)
	{
		return "welcome/committeeMemberWelcome";
	}
	
	/**
	 * 
	 * method name  : teachingSurveyAnalysis
	 * @param courseCode
	 * @param semesterCode
	 * @param sectionNo
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 24, 2015 2:26:09 PM
	 */
	@RequestMapping(params="action=surveyAnalysis")
	private String teachingSurveyAnalysis(
											@RequestParam("empNumber") 		String empNumber,
											@RequestParam("courseCode") String courseCode,
											@RequestParam("semesterCode") String semesterCode,
											@RequestParam("sectionNo") String sectionNo,
											PortletRequest request, Model model, Locale locale
											)
	{
		int			questionSetNo	=	0;
		String		questionByYear	=	null;
		Survey 		survey			=	teachingSurveyServiceDao.getSurVeyAnalysis(empNumber, courseCode, semesterCode, sectionNo, locale);
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		
		
		
		if(year2digit > 10)
		{
			questionSetNo	=	2;
			questionByYear	=	"Q13";
		}
		else
		{
			questionSetNo	=	1;
			questionByYear	=	"Q15";
		}
		
		model.addAttribute("semesterYear", teachingSurveyServiceDao.getSemesterYear(semesterCode, locale));
		model.addAttribute("survey", survey);
		model.addAttribute("questionByYear", questionByYear.trim());
		model.addAttribute("questionSetNo", questionSetNo);
		
		Param			paramEmpNumber		=	new Param("empNumber", empNumber);
		Param			paramCourseCode		=	new Param("courseCode", courseCode);
		Param			paramSemesterCode	=	new Param("semesterCode", semesterCode);
		Param			paramSectionNo		=	new Param("sectionNo", sectionNo);
		
		PortletSession  portletSession		=	request.getPortletSession();

			portletSession.setAttribute(Constants.CONST_SESSION_STAFF, null);
			portletSession.setAttribute(Constants.CONST_SESSION_STAFF, new Staff(empNumber, courseCode, semesterCode, sectionNo));

		
		//portletSession.setAttribute(arg0, arg1);
		
		model.addAttribute(
							"track", 
							UtilRenderTrack.getTrack(
													request, 
													"surveyAnalysis", 
													UtilProperty.getMessage("prop.course.teaching.survey.link.survey.analysis", null, locale),
													paramEmpNumber,
													paramCourseCode,
													paramSemesterCode,
													paramSectionNo
													)
							);

		
		return "surveyAnalysis";
	}

	
	
	/**
	 * 
	 * method name  : pdfTeachingSurveyAnalysis
	 * @param empNumber
	 * @param req
	 * @param res
	 * @param locale
	 * @throws DocumentException
	 * @throws IOException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		: PDF generation
	 *
	 * Date    		:	Feb 15, 2016 1:44:48 PM
	 */
	@ResourceMapping(value="pdfSurveyAnalysis")
	private void pdfTeachingSurveyAnalysis(
			@RequestParam("semesterYear") 		String semesterYear,
			@RequestParam("questionByYear") 	String questionByYear,
			@RequestParam("questionSetNo") 		int questionSetNo,
			ResourceRequest req, ResourceResponse res, Locale locale) throws DocumentException, IOException
	{
		
		PortletSession	portletSession	=	req.getPortletSession();
		Staff			staff			=	null;
		if(null != portletSession.getAttribute(Constants.CONST_SESSION_STAFF))
		{
			staff	=	(Staff)portletSession.getAttribute(Constants.CONST_SESSION_STAFF);
			Survey 		survey			=	teachingSurveyServiceDao.getSurVeyAnalysis(staff.getEmpNumber(), staff.getCourseCode(), staff.getSemesterCode(), staff.getSectionNo(), locale);
			
			ByteArrayOutputStream	byos		=	new ByteArrayOutputStream();
			OutputStream os = teachingSurveyServiceDao.getPdfContent(Constants.CONST_SURVEY_ANALYSIS, survey, byos,semesterYear,questionByYear, questionSetNo, res, locale);
			
			res.setContentType("application/pdf");
			
			res.setContentLength(byos.size());

			byos.writeTo(os);
			
			
			os.flush();
			os.close();
		}
		
		

	}
	
	
	/**
	 * 
	 * method name  : teachingSurveyOpenEndQuestions
	 * @param empNumber
	 * @param courseCode
	 * @param semesterCode
	 * @param sectionNo
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render mapping for open end questions
	 *
	 * Date    		:	Aug 30, 2015 9:11:01 AM
	 */
	@RequestMapping(params="action=openEndQuestions")
	private String teachingSurveyOpenEndQuestions(
			@RequestParam("empNumber") 		String empNumber, 
			@RequestParam("courseCode") 	String courseCode, 
			@RequestParam("semesterCode") 	String semesterCode, 
			@RequestParam("sectionNo") 		String sectionNo,
			PortletRequest	request, Model model, Locale locale
			
			)
	{
		Survey 		survey			=	teachingSurveyServiceDao.getSurVeyAnalysis(empNumber, courseCode, semesterCode, sectionNo, locale);
		
		model.addAttribute("survey", survey);
		model.addAttribute("semesterYear", teachingSurveyServiceDao.getSemesterYear(semesterCode, locale));
		model.addAttribute("questions", teachingSurveyServiceDao.getOpenEndQuestions(empNumber,courseCode,semesterCode,sectionNo));
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "openEndQuestions", UtilProperty.getMessage("prop.course.teaching.survey.link.questions.open.end", null, locale),null));
		return "openEndQuestions";
	}
	
	/**
	 * 
	 * method name  : surveyReport
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render method for survey report for hod, dean, committee members
	 *
	 * Date    		:	Sep 1, 2015 9:10:48 AM
	 */

	@RequestMapping(params="action=surveyReport")
	private String surveyReportSummary(PortletRequest	request, Model model, Locale locale)
	{
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		AccessSurvey		accessSurvey	=	teachingSurveyServiceDao.getAccessToSurvey(empNumber, null, null);
		AccessSurvey		accessViewRights=	teachingSurveyServiceDao.getAccessViewRights();
		
		if(!accessSurvey.isBooAccessAdmin())
		{
			if(accessSurvey.isBooAccessAllSurvey())
			{
				try
				{
					if	( accessViewRights.isBooViewCommitteeSurvey() && new UtilService().dateCompare(new Date(),accessViewRights.getDateCommitteeView()) >= 0 )
					{
						model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, accessViewRights.isBooViewCommitteeSurvey());
					}
					else
					{
						model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, false);
					}
				}
				catch(ParseException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			else
			{
				try
				{
					if(accessViewRights.isBooViewOtherSurvey() && new UtilService().dateCompare(new Date(),accessViewRights.getDateOthersView()) >= 0)
					{
						model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, accessViewRights.isBooViewOtherSurvey());
					}
					else
					{
						model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, false);
					}
				}
				catch (ParseException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			

		}
		else
		{
			model.addAttribute(Constants.CONST_MODEL_IS_CURR_SEMESTER_VIEWABLE, true);
		}
		model.addAttribute(Constants.CONST_MODEL_CURR_SEMESTER, teachingSurveyServiceDao.getCurrentYrSemAdmin());
		
		model.addAttribute("surveyYears", teachingSurveyServiceDao.getAllYearSemestersForReport());
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "surveyReport", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.report", null, locale),null));
		return "surveyReportSummary";
	}

	
	/**
	 * 
	 * method name  : surveyReportValid
	 * @param semesterCode
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render method for valid report survey
	 *
	 * Date    		:	Sep 6, 2015 10:47:25 AM
	 */
	@RequestMapping(params="action=validSurveyReportSummary")
	private String surveyReportValid(
										@RequestParam("semesterCode") String semesterCode, 
										PortletRequest	request, Model model, Locale locale
									)
	{
		String				statement		=	null;
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		StaffRole			staffRole		=	teachingSurveyServiceDao.getStaffRole(empNumber);
		
		int 		year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		
		if(year2digit > 10)
		{
			statement	=	UtilProperty.getMessage("prop.course.teaching.survey.statement.15", null, locale);
		}
		else
		{
			statement	=	UtilProperty.getMessage("prop.course.teaching.survey.statement.16", null, locale);
		}
		

		
		model.addAttribute("semCode",semesterCode);
		model.addAttribute("statement", statement);
		model.addAttribute("staffRole", staffRole);
		model.addAttribute("validSurveyReportJSON", teachingSurveyServiceDao.getValidReportSummariesJSON(empNumber, semesterCode, locale) );
		model.addAttribute("validSurveyReport", teachingSurveyServiceDao.getValidReportSummaries(empNumber, semesterCode, locale) );
		
		Param			param	=	new Param("semesterCode", semesterCode);

		model.addAttribute("track", UtilRenderTrack.getTrack(request, "validSurveyReportSummary", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.report.valid", null, locale),param));
		
		return "surveyReportSummaryValid";
	}

	
	/**
	 * 
	 * method name  : excelSurveyReportValid
	 * @param semesterCode
	 * @param staffRole
	 * @param req
	 * @param res
	 * @param locale
	 * @throws IOException
	 * @throws DocumentException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		: Ms-Excel generation for valid survey report 
	 *
	 * Date    		:	Jun 7, 2016 11:39:42 AM
	 */
	@ResourceMapping(value="excelValidSurveyReportSummary")
	private void excelSurveyReportValid (@RequestParam("semCode")  String semesterCode, @RequestParam("staffRole")  String staffRole, ResourceRequest req, ResourceResponse res, Locale locale) throws IOException, DocumentException
	{
		
		String 		empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(req);

		
		Map<String, String> params			=	new HashMap<String,String>();
		params.put(Constants.CONST_ROLE_STAFF, staffRole);
		params.put(Constants.CONST_PARAM_TYPE_SURVEY, UtilProperty.getMessage("prop.course.teaching.survey.report.survey.valid", null, locale));
		params.put(Constants.CONST_PARAM_SEMESTER_CODE, semesterCode);

		List<ReportSummary> reportSummaries	=	teachingSurveyServiceDao.getValidReportSummaries(empNumber, semesterCode, locale);
		
		teachingSurveyServiceDao.getExcelContent(Constants.CONST_VALID_SURVEY_REPORT, res, reportSummaries,params,locale);
		
	}
	
	
	
	
	/**
	 * 
	 * method name  : surveyReportNotValid
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: render method for report for not valid survey
	 *
	 * Date    		:	Sep 6, 2015 10:47:07 AM
	 */
	@RequestMapping(params="action=notValidSurveyReportSummary")
	private String surveyReportNotValid(
							@RequestParam("semesterCode") String semesterCode, 
							PortletRequest	request, Model model, Locale locale
			)
	{
		String				statement		=	null;
		
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		StaffRole			staffRole		=	teachingSurveyServiceDao.getStaffRole(empNumber);
		
		int 				year2digit		=	Integer.parseInt(semesterCode.substring(0,2));
		
		if(year2digit > 10)
		{
			statement	=	UtilProperty.getMessage("prop.course.teaching.survey.statement.15", null, locale);
		}
		else
		{
			statement	=	UtilProperty.getMessage("prop.course.teaching.survey.statement.16", null, locale);
		}
		
		Param			param	=	new Param("semesterCode", semesterCode);
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "notValidSurveyReportSummary", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.report.valid.not", null, locale),param));
		
		model.addAttribute("semCode", semesterCode);
		model.addAttribute("statement", statement);
		model.addAttribute("staffRole", staffRole);
		model.addAttribute("notValidSurveyReport", teachingSurveyServiceDao.getNotValidReportSummaries(empNumber, semesterCode, locale) );
		model.addAttribute("notValidSurveyReportJSON", teachingSurveyServiceDao.getNotValidReportSummariesJSON(empNumber, semesterCode, locale) );		
		return "surveyReportSummaryNotValid";
	}
	
	
	/**
	 * 
	 * method name  : excelSurveyReportInValid
	 * @param semesterCode
	 * @param staffRole
	 * @param req
	 * @param res
	 * @param locale
	 * @throws IOException
	 * @throws DocumentException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Jun 6, 2016 8:52:43 AM
	 */
	@ResourceMapping(value="excelNotValidSurveyReportSummary")
	private void excelSurveyReportInValid (@RequestParam("semsCode")  String semesterCode, @RequestParam("stafRole")  String staffRole, ResourceRequest req, ResourceResponse res, Locale locale) throws IOException, DocumentException
	{
		
		String 		empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(req);

		
		Map<String, String> params			=	new HashMap<String,String>();
		params.put(Constants.CONST_ROLE_STAFF, staffRole);
		params.put(Constants.CONST_PARAM_TYPE_SURVEY, UtilProperty.getMessage("prop.course.teaching.survey.report.survey.invalid", null, locale));
		params.put(Constants.CONST_PARAM_SEMESTER_CODE, semesterCode);

		List<ReportSummary> reportSummaries	=	teachingSurveyServiceDao.getNotValidReportSummaries(empNumber, semesterCode, locale);
		
		teachingSurveyServiceDao.getExcelContent(Constants.CONST_INVALID_SURVEY_REPORT, res, reportSummaries,params,locale);
		
	}
	
	
	
	
	/**
	 * 
	 * method name  : listCollegeCoursesForAsstDean
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Sep 9, 2015 10:09:05 AM
	 */
	@RequestMapping(params="action=listCollegeCoursesForAsstDean")
	private String listCollegeCoursesForAsstDean (PortletRequest	request, Model model, Locale locale)
	{
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(request);
		model.addAttribute("colCourseList", teachingSurveyServiceDao.getCollegeCoursesForAsstDeanJSON(empNumber, locale));
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "listCollegeCoursesForAsstDean", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.list.college.courses", null, locale),null));
		return "listCollegeCoursesForAsstDean";
	}

	/**
	 * 
	 * method name  : listCollegeCoursesForAsstDean
	 * @param req
	 * @param res
	 * @param locale
	 * @throws IOException
	 * @throws DocumentException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Jun 7, 2016 1:51:35 PM
	 */
	@ResourceMapping(value="excelCollegeCoursesForAsstDean")
	private void listCollegeCoursesForAsstDean (ResourceRequest req, ResourceResponse res, Locale locale) throws IOException, DocumentException
	{
		String 				empNumber 		=	teachingSurveyServiceDao.getEmployeeNumber(req);
		Map<String, String> params			=	new HashMap<String,String>();
		params.put(Constants.CONST_PARAM_TYPE_SURVEY, UtilProperty.getMessage("prop.course.teaching.survey.courses.list", null, locale));
		List<StudentResponse> studentResponses	=	teachingSurveyServiceDao.getCollegeCoursesForAsstDean(empNumber, locale);
		teachingSurveyServiceDao.getExcelContent(Constants.CONST_COL_COURSES_ASST_DEAN, res, studentResponses,params,locale);
		
	}
	
	
	
	/**
	 * 
	 * method name  : adminWelcome
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: admin control
	 *
	 * Date    		:	Sep 16, 2015 1:20:01 PM
	 */
	@RequestMapping(params="action=adminWelcome")
	private String adminWelcome(PortletRequest	request, RenderResponse response, Model model, Locale locale)
	{
		String	yearSemester	=	teachingSurveyServiceDao.getCurrentYrSemAdmin();

		model.addAttribute("isPostSurveyAnalysisAvailable",teachingSurveyServiceDao.isPostSurveyAnalysisAvailable(yearSemester));
		model.addAttribute("fakeRandomId", new Random(System.currentTimeMillis()).nextLong()); 
		
		model.addAttribute("yrSem", yearSemester);
		model.addAttribute("committeeMembers", teachingSurveyServiceDao.getCommitteeMembers(locale));
		model.addAttribute("viewRights", teachingSurveyServiceDao.getAccessViewRights());
		model.addAttribute("track", UtilRenderTrack.getTrack(request, "listCollegeCoursesForAsstDean", UtilProperty.getMessage("prop.course.teaching.survey.link.survey.admin", null, locale),null));
		return "admin/adminWelcome";
	}

	
	
	
	
	/**
	 * 
	 * method name  : backToMain
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * TeachingSurveyMainController
	 * return type  : String
	 * 
	 * purpose		: back to main
	 *
	 * Date    		:	Sep 29, 2015 9:43:47 AM
	 */
	@RequestMapping(params="action=backToMain")
	private String backToMain(PortletRequest	request, Model model, Locale locale)
	{
		return welcome(request, model, locale);
	}
	
	
	/************************************************ POST SURVEY CONTROL OPERATIONS 
	 * @throws IOException ***************************************/ 
	/**
	 * 
	 * method name  : analysisDataTransfer
	 * @param request
	 * @param response
	 * @param locale
	 * @throws IOException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		: Resource rendering for ajax control to start analysis process
	 *
	 * Date    		:	May 29, 2016 9:39:58 AM
	 */
	@ResourceMapping(value="analysisDataTransferAjax") 
	private	void analysisDataTransfer(ResourceRequest request, ResourceResponse response, Locale locale) throws IOException
	{
		String	yrSem	=	teachingSurveyServiceDao.getCurrentYrSemAdmin();
		
		Gson	gson	= new Gson();
		String strTest = gson.toJson(teachingSurveyServiceDao.postSurveyStartAnalysis(yrSem));
		response.getWriter().write(strTest);
		
	}

	/**
	 * 
	 * method name  : surveyPeriodIndicator
	 * @param request
	 * @param response
	 * @param locale
	 * @throws IOException
	 * TeachingSurveyMainController
	 * return type  : void
	 * 
	 * purpose		:  Resource rendering for survey period indicator (Survey period is over or not)
	 *
	 * Date    		:	May 1, 2017 11:12:47 AM
	 */
	@ResourceMapping(value="surveyPeriodAjax")
	private void surveyPeriodIndicator(ResourceRequest request, ResourceResponse response, Locale locale) throws IOException
	{
		Gson	gson		= 	new Gson();
		String	strResult	=	gson.toJson(teachingSurveyServiceDao.isSurveyOver());
		response.getWriter().write(strResult);
	}
	
	
}
