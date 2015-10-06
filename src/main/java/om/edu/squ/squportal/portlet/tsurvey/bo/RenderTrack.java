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
 * File Name			:	RenderTrack.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Sep 29, 2015  12:12:08 PM
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
import java.util.Arrays;
import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class RenderTrack implements Serializable
{
	
	private static final long	serialVersionUID	= 1L;
	private	String 			actionName;
	private	String			linkName;
	//private	List<Param>		params; 	
	private	Param[]			params; 
	
	public RenderTrack(String actionName, String linkName)
	{
		this.actionName	=	actionName;
		this.linkName	=	linkName;
	}
	//public RenderTrack(String actionName, String linkName,List<Param> params)
	public RenderTrack(String actionName, String linkName,Param... params)
	{
		this.actionName	=	actionName;
		this.linkName	=	linkName;
		this.params		=	params;
		//this.params		=	Arrays.asList(params);
	}
	
	/**
	 * Getter Method	: getActionName
	 * @return the actionName
	 * 
	 * Date				: Sep 29, 2015
	 */
	public String getActionName()
	{
		return this.actionName;
	}



	/**
	 * Getter Method	: getLinkName
	 * @return the linkName
	 * 
	 * Date				: Sep 29, 2015
	 */
	public String getLinkName()
	{
		return this.linkName;
	}


	/**
	 * Setter method : setParams
	 * @param params the params to set
	 * 
	 * Date          : Sep 29, 2015 12:33:47 PM
	 */
	//public void setParams(List<Param> params)
	public void setParams(Param... params)
	{
		this.params = params;
	}

	/**
	 * Getter Method	: getParams
	 * @return the params
	 * 
	 * Date				: Sep 29, 2015
	 */
	//public List<Param> getParams()
	public Param[] getParams()
	{
		return this.params;
	}



	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RenderTrack ["
				+ (this.actionName != null ? "actionName=" + this.actionName
						+ ", " : "")
				+ (this.linkName != null ? "linkName=" + this.linkName + ", "
						: "")
				+ (this.params != null ? "params=" + this.params : "") + "]";
	}
	
}


