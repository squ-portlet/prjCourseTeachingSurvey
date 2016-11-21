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
import java.util.Date;

import om.edu.squ.squportal.portlet.tsurvey.dao.service.TeachingSurveyServiceDao;
import om.edu.squ.squportal.portlet.tsurvey.utility.MailUtility;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;



/**
 * @author Bhabesh
 *
 */
@EnableScheduling
public class PreSurveyOperation implements SurveyLoader, SchedulingConfigurer
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
@Autowired
TeachingSurveyServiceDao teachingSurveyServiceDao;	

	/**
	 * This method configured in spring context level for cron scheduling. Normally no need to call
	 */
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
	
	/**
	 * 	TODO - This method is an example of Spring scheduling 
	 */
	//@Scheduled(cron="5 * * * * *")
	public void preSurveyLoadOperation()
	{
		logger.info("Testing of cron schedule" );
	}

	/**
	 *  This method triggering to pass custom parameter from DB for scheduling 
	 */
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
	{
		
		final MailUtility	mailUtility	=	new MailUtility();
		
		taskRegistrar.addTriggerTask(new Runnable()
		{
			
			@Override
			public void run()
			{
				try
				{
					teachingSurveyServiceDao.loadPreSurvey();
					mailUtility.sendEmail
					(
					UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.subject.success", null), 
					UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.body.student.success", null)
					);
				}
				catch (IOException ex)
				{

					logger.error("Failure in loading pre survey data into db tables");
					mailUtility.sendEmail
							(
							UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.subject.fail", null), 
							UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.body.student.fail", null)
							);
				}
				
			}
		}, new Trigger()
		{
			
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext)
			{
				Date	nextExec		= null;
			
				try
				{
				String 		cron		=	teachingSurveyServiceDao.getCronStudentSurveyStart();
				CronTrigger	cronTrigger	=	new CronTrigger(cron);
							nextExec	=	cronTrigger.nextExecutionTime(triggerContext);
				}
				catch(Exception ex)
				{
					logger.error("Error occurs in cron scheduling for data transfer. Details : "+ex.getMessage());
				}
				return nextExec;
			}
		});
		
	}


	
}
