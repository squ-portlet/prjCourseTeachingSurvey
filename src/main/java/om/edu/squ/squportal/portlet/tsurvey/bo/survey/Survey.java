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
 * File Name			:	Survey.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.survey
 * Date of creation		:	Aug 18, 2015  11:23:24 AM
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

import java.io.Serializable;
import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class Survey implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private	String					courseCode		=	null;
	private	String					courseName  	=	null;
	private	String					yearSemester 	=	null;
	private	String					empNumber		=	null;
	private	String					empName			=	null;
	private	String					collegeName		=	null;
	private	String					departmentName	=	null;
	private	String					message			=	null;
	private	List<SurveyResponse> 	surveyResponses	=	null;
	

	
	/**
	 * Getter Method	: getCourseCode
	 * @return the courseCode
	 * 
	 * Date				: Aug 18, 2015
	 */
	public String getCourseCode()
	{
		return this.courseCode;
	}
	/**
	 * Setter method : setCourseCode
	 * @param courseCode the courseCode to set
	 * 
	 * Date          : Aug 18, 2015 11:27:25 AM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Getter Method	: getCourseName
	 * @return the courseName
	 * 
	 * Date				: Aug 18, 2015
	 */
	public String getCourseName()
	{
		return this.courseName;
	}
	/**
	 * Setter method : setCourseName
	 * @param courseName the courseName to set
	 * 
	 * Date          : Aug 18, 2015 11:27:25 AM
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	/**
	 * Getter Method	: getYearSemester
	 * @return the yearSemester
	 * 
	 * Date				: Aug 18, 2015
	 */
	public String getYearSemester()
	{
		return this.yearSemester;
	}
	/**
	 * Setter method : setYearSemester
	 * @param yearSemester the yearSemester to set
	 * 
	 * Date          : Aug 18, 2015 11:27:25 AM
	 */
	public void setYearSemester(String yearSemester)
	{
		this.yearSemester = yearSemester;
	}
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Aug 18, 2015
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Aug 18, 2015 11:27:25 AM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Aug 24, 2015 11:01:34 AM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getCollegeName
	 * @return the collegeName
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getCollegeName()
	{
		return this.collegeName;
	}
	/**
	 * Setter method : setCollegeName
	 * @param collegeName the collegeName to set
	 * 
	 * Date          : Aug 24, 2015 11:01:34 AM
	 */
	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}
	/**
	 * Getter Method	: getDepartmentName
	 * @return the departmentName
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getDepartmentName()
	{
		return this.departmentName;
	}
	/**
	 * Setter method : setDepartmentName
	 * @param departmentName the departmentName to set
	 * 
	 * Date          : Aug 24, 2015 11:01:34 AM
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	
	/**
	 * Getter Method	: getMessage
	 * @return the message
	 * 
	 * Date				: Sep 8, 2015
	 */
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * Setter method : setMessage
	 * @param message the message to set
	 * 
	 * Date          : Sep 8, 2015 11:29:40 AM
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	/**
	 * Getter Method	: getSurveyResponses
	 * @return the surveyResponses
	 * 
	 * Date				: Aug 18, 2015
	 */
	public List<SurveyResponse> getSurveyResponses()
	{
		return this.surveyResponses;
	}
	/**
	 * Setter method : setSurveyResponses
	 * @param surveyResponses the surveyResponses to set
	 * 
	 * Date          : Aug 18, 2015 11:27:25 AM
	 */
	public void setSurveyResponses(List<SurveyResponse> surveyResponses)
	{
		this.surveyResponses = surveyResponses;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Survey ["
				+ (this.courseCode != null ? "courseCode=" + this.courseCode
						+ ", " : "")
				+ (this.courseName != null ? "courseName=" + this.courseName
						+ ", " : "")
				+ (this.yearSemester != null ? "yearSemester="
						+ this.yearSemester + ", " : "")
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ (this.collegeName != null ? "collegeName=" + this.collegeName
						+ ", " : "")
				+ (this.departmentName != null ? "departmentName="
						+ this.departmentName + ", " : "")
				+ (this.message != null ? "message=" + this.message + ", " : "")
				+ (this.surveyResponses != null ? "surveyResponses="
						+ this.surveyResponses : "") + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.courseCode == null) ? 0 : this.courseCode.hashCode());
		result = prime
				* result
				+ ((this.yearSemester == null) ? 0 : this.yearSemester
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
		Survey other = (Survey) obj;
		if (this.courseCode == null)
		{
			if (other.courseCode != null) return false;
		}
		else if (!this.courseCode.equals(other.courseCode)) return false;
		if (this.yearSemester == null)
		{
			if (other.yearSemester != null) return false;
		}
		else if (!this.yearSemester.equals(other.yearSemester)) return false;
		return true;
	}
	
	
	
}
