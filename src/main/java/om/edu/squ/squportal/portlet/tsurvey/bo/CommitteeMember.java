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
 * File Name			:	CommitteeMember.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 17, 2015  11:41:14 AM
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
public class CommitteeMember
{
	private		String	empNo;
	private		String	empName;
	private		String	role;
	/**
	 * Getter Method	: getEmpNo
	 * @return the empNo
	 * 
	 * Date				: Sep 17, 2015
	 */
	public String getEmpNo()
	{
		return this.empNo;
	}
	/**
	 * Setter method : setEmpNo
	 * @param empNo the empNo to set
	 * 
	 * Date          : Sep 17, 2015 11:44:08 AM
	 */
	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Sep 17, 2015
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Sep 17, 2015 11:44:08 AM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getRole
	 * @return the role
	 * 
	 * Date				: Sep 17, 2015
	 */
	public String getRole()
	{
		return this.role;
	}
	/**
	 * Setter method : setRole
	 * @param role the role to set
	 * 
	 * Date          : Sep 17, 2015 11:44:08 AM
	 */
	public void setRole(String role)
	{
		this.role = role;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CommitteeMember ["
				+ (this.empNo != null ? "empNo=" + this.empNo + ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ (this.role != null ? "role=" + this.role : "") + "]";
	}
	
	
}
