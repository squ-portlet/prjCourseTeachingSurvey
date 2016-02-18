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
 * File Name			:	Staff.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Feb 15, 2016  2:21:01 PM
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

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bhabesh
 *
 */
public class Staff implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private	String empNumber;
	private	String courseCode;
	private String semesterCode;
	private String sectionNo;
	
	public Staff(){}
	public Staff(String empNumber, String courseCode, String semesterCode, String sectionNo)
	{
		this.empNumber		=	empNumber;
		this.courseCode		=	courseCode;
		this.semesterCode	=	semesterCode;
		this.sectionNo		=	sectionNo;
	}
	
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Feb 15, 2016
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Feb 15, 2016 3:27:17 PM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getCourseCode
	 * @return the courseCode
	 * 
	 * Date				: Feb 15, 2016
	 */
	public String getCourseCode()
	{
		return this.courseCode;
	}
	/**
	 * Setter method : setCourseCode
	 * @param courseCode the courseCode to set
	 * 
	 * Date          : Feb 15, 2016 3:27:17 PM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Getter Method	: getSemesterCode
	 * @return the semesterCode
	 * 
	 * Date				: Feb 15, 2016
	 */
	public String getSemesterCode()
	{
		return this.semesterCode;
	}
	/**
	 * Setter method : setSemesterCode
	 * @param semesterCode the semesterCode to set
	 * 
	 * Date          : Feb 15, 2016 3:27:17 PM
	 */
	public void setSemesterCode(String semesterCode)
	{
		this.semesterCode = semesterCode;
	}
	/**
	 * Getter Method	: getSectionNo
	 * @return the sectionNo
	 * 
	 * Date				: Feb 15, 2016
	 */
	public String getSectionNo()
	{
		return this.sectionNo;
	}
	/**
	 * Setter method : setSectionNo
	 * @param sectionNo the sectionNo to set
	 * 
	 * Date          : Feb 15, 2016 3:27:17 PM
	 */
	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Staff [empNumber=" + this.empNumber + ", courseCode="
				+ this.courseCode + ", semesterCode=" + this.semesterCode
				+ ", sectionNo=" + this.sectionNo + "]";
	}
	
	
	
	
}
