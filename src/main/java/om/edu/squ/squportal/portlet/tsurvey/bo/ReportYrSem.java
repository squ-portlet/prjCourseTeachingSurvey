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
 * File Name			:	ReportYrSem.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Aug 31, 2015  1:37:43 PM
 * Date of modification :	
 * 
 * Summary				:	academic year semester report
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
package om.edu.squ.squportal.portlet.tsurvey.bo;

/**
 * @author Bhabesh
 *
 */
public class ReportYrSem
{
	private	String	academicYear;
	private	String	yearSemester;
	/**
	 * Getter Method	: getAcademicYear
	 * @return the academicYear
	 * 
	 * Date				: Aug 31, 2015
	 */
	public String getAcademicYear()
	{
		return this.academicYear;
	}
	/**
	 * Setter method : setAcademicYear
	 * @param academicYear the academicYear to set
	 * 
	 * Date          : Aug 31, 2015 1:39:35 PM
	 */
	public void setAcademicYear(String academicYear)
	{
		this.academicYear = academicYear;
	}
	/**
	 * Getter Method	: getYearSemester
	 * @return the yearSemester
	 * 
	 * Date				: Aug 31, 2015
	 */
	public String getYearSemester()
	{
		return this.yearSemester;
	}
	/**
	 * Setter method : setYearSemester
	 * @param yearSemester the yearSemester to set
	 * 
	 * Date          : Aug 31, 2015 1:39:35 PM
	 */
	public void setYearSemester(String yearSemester)
	{
		this.yearSemester = yearSemester;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ReportYrSem ["
				+ (this.academicYear != null ? "academicYear="
						+ this.academicYear + ", " : "")
				+ (this.yearSemester != null ? "yearSemester="
						+ this.yearSemester : "") + "]";
	}
	
	
	
	
}
