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
 * File Name			:	AccessVisibilitySurveyServlet.java
 * Package Name			:	om.edu.squ.squportal.servlet.tsurvey
 * Date of creation		:	Sep 22, 2015  11:45:13 AM
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
package om.edu.squ.squportal.servlet.tsurvey;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.tsurvey.dao.db.TeachingSurveyDbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;



/**
 * @author Bhabesh
 *
 */
@Component("AccessVisibilitySurveyServlet")
public class AccessVisibilitySurveyServlet implements HttpRequestHandler
{
	@Autowired
	TeachingSurveyDbDao	teachingSurveyDbDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String 	chkSurveyVisibleCommittee	=	null;
		String	chkSurveyVisibleFaculty		=	null;
		String	dateCommittee				=	null;
		String	dateFaculty					=	null;
		String 	semesterCode				=	null;
		String  result						=	null;
		
		try
		{
			chkSurveyVisibleCommittee		=	request.getParameter("chkSurveyVisibleCommittee");
			chkSurveyVisibleFaculty			=	request.getParameter("chkSurveyVisibleFaculty");
			dateCommittee					=	request.getParameter("dateCommittee");
			dateFaculty						=	request.getParameter("dateFaculty");
			semesterCode					=	request.getParameter("yrSem");
			
			//logger.info("chkSurveyVisibleCommittee : "+chkSurveyVisibleCommittee + " chkSurveyVisibleFaculty : "+chkSurveyVisibleFaculty + " dateCommunity : "+dateCommittee + " dateFaculty : "+dateFaculty + " yrSem : "+semesterCode);
			
			
			if(!chkSurveyVisibleCommittee.equals("na"))
			{
				teachingSurveyDbDao.setDateAdminCommitteeView(semesterCode, dateCommittee);
			}
			if(!chkSurveyVisibleFaculty.equals("na"))
			{
				teachingSurveyDbDao.setDateAdminFacultyView(semesterCode, dateFaculty);
			}
			
			
		}
		catch(Exception ex)
		{
			logger.error("Error : database issue  : -- " + ex.getMessage());
		}
	}


	
	
}
