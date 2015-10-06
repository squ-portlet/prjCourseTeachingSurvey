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
 * File Name			:	Param.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 29, 2015  1:59:52 PM
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

import java.io.Serializable;

/**
 * @author Bhabesh
 *
 */
public class Param implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private	String pname;
	private String pvalue;
	
	public Param(){};
	public Param (String pname, String pvalue)
	{
		this.pname	=	pname;
		this.pvalue	=	pvalue;
	}

	/**
	 * Getter Method	: getPname
	 * @return the pname
	 * 
	 * Date				: Sep 29, 2015
	 */
	public String getPname()
	{
		return this.pname;
	}

	/**
	 * Setter method : setPname
	 * @param pname the pname to set
	 * 
	 * Date          : Sep 29, 2015 2:46:49 PM
	 */
	public void setPname(String pname)
	{
		this.pname = pname;
	}

	/**
	 * Getter Method	: getPvalue
	 * @return the pvalue
	 * 
	 * Date				: Sep 29, 2015
	 */
	public String getPvalue()
	{
		return this.pvalue;
	}

	/**
	 * Setter method : setPvalue
	 * @param pvalue the pvalue to set
	 * 
	 * Date          : Sep 29, 2015 2:46:49 PM
	 */
	public void setPvalue(String pvalue)
	{
		this.pvalue = pvalue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Param ["
				+ (this.pname != null ? "pname=" + this.pname + ", " : "")
				+ (this.pvalue != null ? "pvalue=" + this.pvalue : "") + "]";
	}

	
	
	
	
}
