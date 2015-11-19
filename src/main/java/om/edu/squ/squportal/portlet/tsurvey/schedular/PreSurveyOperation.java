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
 * File Name			:	PreSurveyOperation.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.schedular
 * Date of creation		:	Nov 5, 2015  11:42:11 AM
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
package om.edu.squ.squportal.portlet.tsurvey.schedular;

import java.io.IOException;

import om.edu.squ.squportal.portlet.tsurvey.dao.service.TeachingSurveyServiceDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Bhabesh
 *
 */

public class PreSurveyOperation implements SurveyLoader
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
@Autowired
TeachingSurveyServiceDao teachingSurveyServiceDao;	

	@Override
	public void operation()
	{
		// TODO Auto-generated method stub
		try
		{
			teachingSurveyServiceDao.loadPreSurvey();
		}
		catch (IOException ex)
		{
			// TODO Auto-generated catch block
			logger.error("Failure in loading pre survey data into db tables");
		}
		
	}
	
}
