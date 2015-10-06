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
 * File Name			:	Department.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 10, 2015  9:11:06 AM
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

import java.util.List;
import java.util.Set;

/**
 * @author Bhabesh
 *
 */
public class Department
{
	private	String 			departmentCode;
	private	String			departmentName;
	private	List<Course>		children;
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
	 * Date          : Sep 10, 2015 9:17:46 AM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getDepartmentName
	 * @return the departmentName
	 * 
	 * Date				: Sep 10, 2015
	 */
	public String getDepartmentName()
	{
		return this.departmentName;
	}
	/**
	 * Setter method : setDepartmentName
	 * @param departmentName the departmentName to set
	 * 
	 * Date          : Sep 10, 2015 9:17:46 AM
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	/**
	 * Getter Method	: getChildren
	 * @return the children
	 * 
	 * Date				: Sep 10, 2015
	 */
	public List<Course> getChildren()
	{
		return this.children;
	}
	/**
	 * Setter method : setChildren
	 * @param children the children to set
	 * 
	 * Date          : Sep 10, 2015 9:17:46 AM
	 */
	public void setChildren(List<Course> children)
	{
		this.children = children;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Department ["
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode + ", " : "")
				+ (this.departmentName != null ? "departmentName="
						+ this.departmentName + ", " : "")
				+ (this.children != null ? "children=" + this.children : "")
				+ "]";
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
				+ ((this.departmentCode == null) ? 0 : this.departmentCode
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
		Department other = (Department) obj;
		if (this.departmentCode == null)
		{
			if (other.departmentCode != null) return false;
		}
		else if (!this.departmentCode.equals(other.departmentCode))
			return false;
		return true;
	}
	
	
}
