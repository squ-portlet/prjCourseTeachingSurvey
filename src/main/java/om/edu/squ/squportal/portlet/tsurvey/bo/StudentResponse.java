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
 * File Name			:	StudentResponse.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Aug 17, 2015  2:44:53 PM
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
public class StudentResponse
{	
	private	String	academicYear;
	private	String	courseCode;
	private	String	courseName;
	private	String	sectionNo;
	private	int		seatsTaken;
	private	int		studentResponse;
	private	String	yearSemester;
	private	String	empNumber;
	private	String	empName;
	private	String	departmentCode;
	private	String	departmentName;
	private	String	includeExclude;
	
	
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
	 * Date          : Aug 19, 2015 12:44:13 PM
	 */
	public void setAcademicYear(String academicYear)
	{
		this.academicYear = academicYear;
	}
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
	 * Date          : Aug 18, 2015 8:10:38 AM
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
	 * Date          : Aug 18, 2015 8:10:38 AM
	 */
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	/**
	 * Getter Method	: getSectionNo
	 * @return the sectionNo
	 * 
	 * Date				: Aug 18, 2015
	 */
	public String getSectionNo()
	{
		return this.sectionNo;
	}
	/**
	 * Setter method : setSectionNo
	 * @param sectionNo the sectionNo to set
	 * 
	 * Date          : Aug 18, 2015 8:10:39 AM
	 */
	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}
	/**
	 * Getter Method	: getSeatsTaken
	 * @return the seatsTaken
	 * 
	 * Date				: Aug 18, 2015
	 */
	public int getSeatsTaken()
	{
		return this.seatsTaken;
	}
	/**
	 * Setter method : setSeatsTaken
	 * @param seatsTaken the seatsTaken to set
	 * 
	 * Date          : Aug 18, 2015 8:10:39 AM
	 */
	public void setSeatsTaken(int seatsTaken)
	{
		this.seatsTaken = seatsTaken;
	}
	/**
	 * Getter Method	: getStudentResponse
	 * @return the studentResponse
	 * 
	 * Date				: Aug 18, 2015
	 */
	public int getStudentResponse()
	{
		return this.studentResponse;
	}
	/**
	 * Setter method : setStudentResponse
	 * @param studentResponse the studentResponse to set
	 * 
	 * Date          : Aug 18, 2015 8:10:39 AM
	 */
	public void setStudentResponse(int studentResponse)
	{
		this.studentResponse = studentResponse;
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
	 * Date          : Aug 18, 2015 8:10:39 AM
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
	 * Date          : Aug 18, 2015 8:10:39 AM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Sep 9, 2015
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Sep 9, 2015 2:43:19 PM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
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
	 * Date          : Sep 10, 2015 9:15:05 AM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getDepartmentName
	 * @return the departmentName
	 * 
	 * Date				: Sep 9, 2015
	 */
	public String getDepartmentName()
	{
		return this.departmentName;
	}
	/**
	 * Setter method : setDepartmentName
	 * @param departmentName the departmentName to set
	 * 
	 * Date          : Sep 9, 2015 2:44:43 PM
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	/**
	 * Getter Method	: getIncludeExclude
	 * @return the includeExclude
	 * 
	 * Date				: Sep 9, 2015
	 */
	public String getIncludeExclude()
	{
		return this.includeExclude;
	}
	/**
	 * Setter method : setIncludeExclude
	 * @param includeExclude the includeExclude to set
	 * 
	 * Date          : Sep 9, 2015 2:44:43 PM
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
		return "StudentResponse ["
				+ (this.academicYear != null ? "academicYear="
						+ this.academicYear + ", " : "")
				+ (this.courseCode != null ? "courseCode=" + this.courseCode
						+ ", " : "")
				+ (this.courseName != null ? "courseName=" + this.courseName
						+ ", " : "")
				+ (this.sectionNo != null ? "sectionNo=" + this.sectionNo
						+ ", " : "")
				+ "seatsTaken="
				+ this.seatsTaken
				+ ", studentResponse="
				+ this.studentResponse
				+ ", "
				+ (this.yearSemester != null ? "yearSemester="
						+ this.yearSemester + ", " : "")
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode + ", " : "")
				+ (this.departmentName != null ? "departmentName="
						+ this.departmentName + ", " : "")
				+ (this.includeExclude != null ? "includeExclude="
						+ this.includeExclude : "") + "]";
	}
	

	
}
