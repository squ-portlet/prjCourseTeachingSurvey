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
 * File Name			:	StudentSurveyStartDay.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Nov 21, 2016  2:10:23 PM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2016 the original author or authors and Organization.
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
package om.edu.squ.squportal.portlet.tsurvey.bo;

/**
 * @author Bhabesh
 *
 */
public class StudentSurveyStartDay
{
	private	String	surveyDay;
	private	String	surveyMonth;
	/**
	 * Getter Method	: getSurveyDay
	 * @return the surveyDay
	 * 
	 * Date				: Nov 21, 2016
	 */
	public String getSurveyDay()
	{
		return this.surveyDay;
	}
	/**
	 * Setter method : setSurveyDay
	 * @param surveyDay the surveyDay to set
	 * 
	 * Date          : Nov 21, 2016 2:13:01 PM
	 */
	public void setSurveyDay(String surveyDay)
	{
		this.surveyDay = surveyDay;
	}
	/**
	 * Getter Method	: getSurveyMonth
	 * @return the surveyMonth
	 * 
	 * Date				: Nov 21, 2016
	 */
	public String getSurveyMonth()
	{
		return this.surveyMonth;
	}
	/**
	 * Setter method : setSurveyMonth
	 * @param surveyMonth the surveyMonth to set
	 * 
	 * Date          : Nov 21, 2016 2:13:01 PM
	 */
	public void setSurveyMonth(String surveyMonth)
	{
		this.surveyMonth = surveyMonth;
	}
	
	
	
}
