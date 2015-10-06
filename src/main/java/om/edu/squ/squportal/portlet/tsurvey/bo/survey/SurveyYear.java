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
 * File Name			:	SurveyYear.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.survey
 * Date of creation		:	Aug 19, 2015  12:45:42 PM
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
package om.edu.squ.squportal.portlet.tsurvey.bo.survey;

import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class SurveyYear
{
	private	String			academicYear;
	private	List<Survey>	surveys;
	/**
	 * Getter Method	: getAcademicYear
	 * @return the academicYear
	 * 
	 * Date				: Aug 19, 2015
	 */
	public String getAcademicYear()
	{
		return this.academicYear;
	}
	/**
	 * Setter method : setAcademicYear
	 * @param academicYear the academicYear to set
	 * 
	 * Date          : Aug 19, 2015 12:48:20 PM
	 */
	public void setAcademicYear(String academicYear)
	{
		this.academicYear = academicYear;
	}
	/**
	 * Getter Method	: getSurveys
	 * @return the surveys
	 * 
	 * Date				: Aug 19, 2015
	 */
	public List<Survey> getSurveys()
	{
		return this.surveys;
	}
	/**
	 * Setter method : setSurveys
	 * @param surveys the surveys to set
	 * 
	 * Date          : Aug 19, 2015 12:48:20 PM
	 */
	public void setSurveys(List<Survey> surveys)
	{
		this.surveys = surveys;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SurveyYear ["
				+ (this.academicYear != null ? "academicYear="
						+ this.academicYear + ", " : "")
				+ (this.surveys != null ? "surveys=" + this.surveys : "") + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.academicYear == null) ? 0 : this.academicYear
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SurveyYear other = (SurveyYear) obj;
		if (this.academicYear == null)
		{
			if (other.academicYear != null) return false;
		}
		else if (!this.academicYear.equals(other.academicYear)) return false;
		return true;
	}
	
	
}
