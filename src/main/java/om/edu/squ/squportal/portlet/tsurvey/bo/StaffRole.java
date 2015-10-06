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
 * File Name			:	StaffRole.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 1, 2015  12:20:35 PM
 * Date of modification :	
 * 
 * Summary				:	Staff Role object
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
public class StaffRole
{
	private String	empNumber;
	private	String	staffRole;
	private	String	collegeCode;
	private	String	departmentCode;
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
	 * Date          : Sep 1, 2015 12:25:58 PM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getStaffRole
	 * @return the staffRole
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getStaffRole()
	{
		return this.staffRole;
	}
	/**
	 * Setter method : setStaffRole
	 * @param staffRole the staffRole to set
	 * 
	 * Date          : Sep 1, 2015 12:25:58 PM
	 */
	public void setStaffRole(String staffRole)
	{
		this.staffRole = staffRole;
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
	 * Date          : Sep 1, 2015 12:25:58 PM
	 */
	public void setCollegeCode(String collegeCode)
	{
		this.collegeCode = collegeCode;
	}
	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Sep 1, 2015
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Sep 1, 2015 12:25:58 PM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StaffRole ["
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.staffRole != null ? "staffRole=" + this.staffRole
						+ ", " : "")
				+ (this.collegeCode != null ? "collegeCode=" + this.collegeCode
						+ ", " : "")
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode : "") + "]";
	}
	
	
	
}
