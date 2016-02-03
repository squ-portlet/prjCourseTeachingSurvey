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
 * File Name			:	ReportSummary.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 1, 2015  1:58:10 PM
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
public class ReportSummary
{

	private	String	serialNum;
	private	String 	yearSemester;
	private	int		universityRank;
	private	int		collegeRank;
	private	int		departmentRank;
	private	String	empNumber;
	private	String	empName;
	private	String	collegeCode;
	private	String	departmentCode;
	private	String	departmentName;
	private	String	courseCode;
	private	String	sectionNo;
	private	int		registeredStudent;
	private	int		studentNoResponse;
	private	float	teachingMean;
	private	float	teachingPercentageFavor;
	private	float	questionMean;
	private	float	questionPercentageFavor;
	
	/**
	 * Getter Method	: getSerialNum
	 * @return the serialNum
	 * 
	 * Date				: Feb 2, 2016
	 */
	public String getSerialNum()
	{
		return this.serialNum;
	}
	/**
	 * Setter method : setSerialNum
	 * @param serialNum the serialNum to set
	 * 
	 * Date          : Feb 2, 2016 1:12:57 PM
	 */
	public void setSerialNum(String serialNum)
	{
		this.serialNum = serialNum;
	}
	/**
	 * Getter Method	: getYearSemester
	 * @return the yearSemester
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getYearSemester()
	{
		return this.yearSemester;
	}
	/**
	 * Setter method : setYearSemester
	 * @param yearSemester the yearSemester to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setYearSemester(String yearSemester)
	{
		this.yearSemester = yearSemester;
	}
	/**
	 * Getter Method	: getUniversityRank
	 * @return the universityRank
	 * 
	 * Date				: Sep 1, 2015
	 */
	public int getUniversityRank()
	{
		return this.universityRank;
	}
	/**
	 * Setter method : setUniversityRank
	 * @param universityRank the universityRank to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setUniversityRank(int universityRank)
	{
		this.universityRank = universityRank;
	}
	/**
	 * Getter Method	: getCollegeRank
	 * @return the collegeRank
	 * 
	 * Date				: Sep 1, 2015
	 */
	public int getCollegeRank()
	{
		return this.collegeRank;
	}
	/**
	 * Setter method : setCollegeRank
	 * @param collegeRank the collegeRank to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setCollegeRank(int collegeRank)
	{
		this.collegeRank = collegeRank;
	}
	/**
	 * Getter Method	: getDepartmentRank
	 * @return the departmentRank
	 * 
	 * Date				: Sep 1, 2015
	 */
	public int getDepartmentRank()
	{
		return this.departmentRank;
	}
	/**
	 * Setter method : setDepartmentRank
	 * @param departmentRank the departmentRank to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setDepartmentRank(int departmentRank)
	{
		this.departmentRank = departmentRank;
	}
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getCollegeCode
	 * @return the collegeCode
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getCollegeCode()
	{
		return this.collegeCode;
	}
	/**
	 * Setter method : setCollegeCode
	 * @param collegeCode the collegeCode to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setCollegeCode(String collegeCode)
	{
		this.collegeCode = collegeCode;
	}
	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Sep 6, 2015
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Sep 6, 2015 9:14:59 AM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getDepartmentName
	 * @return the departmentName
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getDepartmentName()
	{
		return this.departmentName;
	}
	/**
	 * Setter method : setDepartmentName
	 * @param departmentName the departmentName to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	/**
	 * Getter Method	: getCourseCode
	 * @return the courseCode
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getCourseCode()
	{
		return this.courseCode;
	}
	/**
	 * Setter method : setCourseCode
	 * @param courseCode the courseCode to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Getter Method	: getSectionNo
	 * @return the sectionNo
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getSectionNo()
	{
		return this.sectionNo;
	}
	/**
	 * Setter method : setSectionNo
	 * @param sectionNo the sectionNo to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}
	/**
	 * Getter Method	: getRegisteredStudent
	 * @return the registeredStudent
	 * 
	 * Date				: Sep 1, 2015
	 */
	public int getRegisteredStudent()
	{
		return this.registeredStudent;
	}
	/**
	 * Setter method : setRegisteredStudent
	 * @param registeredStudent the registeredStudent to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setRegisteredStudent(int registeredStudent)
	{
		this.registeredStudent = registeredStudent;
	}
	/**
	 * Getter Method	: getStudentNoResponse
	 * @return the studentNoResponse
	 * 
	 * Date				: Sep 1, 2015
	 */
	public int getStudentNoResponse()
	{
		return this.studentNoResponse;
	}
	/**
	 * Setter method : setStudentNoResponse
	 * @param studentNoResponse the studentNoResponse to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setStudentNoResponse(int studentNoResponse)
	{
		this.studentNoResponse = studentNoResponse;
	}
	/**
	 * Getter Method	: getTeachingMean
	 * @return the teachingMean
	 * 
	 * Date				: Sep 1, 2015
	 */
	public float getTeachingMean()
	{
		return this.teachingMean;
	}
	/**
	 * Setter method : setTeachingMean
	 * @param teachingMean the teachingMean to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setTeachingMean(float teachingMean)
	{
		this.teachingMean = teachingMean;
	}
	/**
	 * Getter Method	: getTeachingPercentageFavor
	 * @return the teachingPercentageFavor
	 * 
	 * Date				: Sep 1, 2015
	 */
	public float getTeachingPercentageFavor()
	{
		return this.teachingPercentageFavor;
	}
	/**
	 * Setter method : setTeachingPercentageFavor
	 * @param teachingPercentageFavor the teachingPercentageFavor to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setTeachingPercentageFavor(float teachingPercentageFavor)
	{
		this.teachingPercentageFavor = teachingPercentageFavor;
	}
	/**
	 * Getter Method	: getQuestionMean
	 * @return the questionMean
	 * 
	 * Date				: Sep 1, 2015
	 */
	public float getQuestionMean()
	{
		return this.questionMean;
	}
	/**
	 * Setter method : setQuestionMean
	 * @param questionMean the questionMean to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setQuestionMean(float questionMean)
	{
		this.questionMean = questionMean;
	}
	/**
	 * Getter Method	: getQuestionPercentageFavor
	 * @return the questionPercentageFavor
	 * 
	 * Date				: Sep 1, 2015
	 */
	public float getQuestionPercentageFavor()
	{
		return this.questionPercentageFavor;
	}
	/**
	 * Setter method : setQuestionPercentageFavor
	 * @param questionPercentageFavor the questionPercentageFavor to set
	 * 
	 * Date          : Sep 1, 2015 2:12:22 PM
	 */
	public void setQuestionPercentageFavor(float questionPercentageFavor)
	{
		this.questionPercentageFavor = questionPercentageFavor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ReportSummary [serialNum=" + this.serialNum + ", yearSemester="
				+ this.yearSemester + ", universityRank=" + this.universityRank
				+ ", collegeRank=" + this.collegeRank + ", departmentRank="
				+ this.departmentRank + ", empNumber=" + this.empNumber
				+ ", empName=" + this.empName + ", collegeCode="
				+ this.collegeCode + ", departmentCode=" + this.departmentCode
				+ ", departmentName=" + this.departmentName + ", courseCode="
				+ this.courseCode + ", sectionNo=" + this.sectionNo
				+ ", registeredStudent=" + this.registeredStudent
				+ ", studentNoResponse=" + this.studentNoResponse
				+ ", teachingMean=" + this.teachingMean
				+ ", teachingPercentageFavor=" + this.teachingPercentageFavor
				+ ", questionMean=" + this.questionMean
				+ ", questionPercentageFavor=" + this.questionPercentageFavor
				+ "]";
	}
	
	
	
	
}
