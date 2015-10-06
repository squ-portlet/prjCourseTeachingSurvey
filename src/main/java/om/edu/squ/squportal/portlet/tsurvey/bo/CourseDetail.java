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
 * File Name			:	CourseDetail.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 10, 2015  8:51:31 AM
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
package om.edu.squ.squportal.portlet.tsurvey.bo;

/**
 * @author Bhabesh
 *
 */
public class CourseDetail
{
	private	String	courseCode;
	private	String 	departmentCode;
	private	String	sectionNo;
	private	String	empNumber;
	private	String	empName;
	private	int		seatsTaken;
	private	int		studentResponse;
	private	String	includeExclude;
	
	/**
	 * Getter Method	: getCourseCode
	 * @return the courseCode
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getCourseCode()
	{
		return this.courseCode;
	}
	/**
	 * Setter method : setCourseCode
	 * @param courseCode the courseCode to set
	 * 
	 * Date          : Sep 10, 2015 1:16:12 PM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Sep 10, 2015 1:16:12 PM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getSectionNo
	 * @return the sectionNo
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getSectionNo()
	{
		return this.sectionNo;
	}
	/**
	 * Setter method : setSectionNo
	 * @param sectionNo the sectionNo to set
	 * 
	 * Date          : Sep 10, 2015 9:00:26 AM
	 */
	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Sep 10, 2015 9:00:26 AM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Sep 10, 2015 9:00:26 AM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getSeatsTaken
	 * @return the seatsTaken
	 * 
	 * Date				: Sep 10, 2015
	 */
	public int getSeatsTaken()
	{
		return this.seatsTaken;
	}
	/**
	 * Setter method : setSeatsTaken
	 * @param seatsTaken the seatsTaken to set
	 * 
	 * Date          : Sep 10, 2015 9:00:26 AM
	 */
	public void setSeatsTaken(int seatsTaken)
	{
		this.seatsTaken = seatsTaken;
	}
	/**
	 * Getter Method	: getStudentResponse
	 * @return the studentResponse
	 * 
	 * Date				: Sep 10, 2015
	 */
	public int getStudentResponse()
	{
		return this.studentResponse;
	}
	/**
	 * Setter method : setStudentResponse
	 * @param studentResponse the studentResponse to set
	 * 
	 * Date          : Sep 10, 2015 9:00:26 AM
	 */
	public void setStudentResponse(int studentResponse)
	{
		this.studentResponse = studentResponse;
	}
	
	/**
	 * Getter Method	: getIncludeExclude
	 * @return the includeExclude
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getIncludeExclude()
	{
		return this.includeExclude;
	}
	/**
	 * Setter method : setIncludeExclude
	 * @param includeExclude the includeExclude to set
	 * 
	 * Date          : Sep 10, 2015 12:43:28 PM
	 */
	public void setIncludeExclude(String includeExclude)
	{
		this.includeExclude = includeExclude;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CourseDetail ["
				+ (this.courseCode != null ? "courseCode=" + this.courseCode
						+ ", " : "")
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode + ", " : "")
				+ (this.sectionNo != null ? "sectionNo=" + this.sectionNo
						+ ", " : "")
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ "seatsTaken="
				+ this.seatsTaken
				+ ", studentResponse="
				+ this.studentResponse
				+ ", "
				+ (this.includeExclude != null ? "includeExclude="
						+ this.includeExclude : "") + "]";
	}
	
	
	
}
