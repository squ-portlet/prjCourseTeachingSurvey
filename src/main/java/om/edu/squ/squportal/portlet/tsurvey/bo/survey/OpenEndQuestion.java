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
 * File Name			:	OpenEndQuestion.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.survey
 * Date of creation		:	Aug 27, 2015  1:37:09 PM
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

/**
 * @author Bhabesh
 *
 */
public class OpenEndQuestion implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private	String	question1;
	private	String	question2;
	private	String	question3;
	/**
	 * Getter Method	: getQuestion1
	 * @return the question1
	 * 
	 * Date				: Aug 27, 2015
	 */
	public String getQuestion1()
	{
		return this.question1;
	}
	/**
	 * Setter method : setQuestion1
	 * @param question1 the question1 to set
	 * 
	 * Date          : Aug 27, 2015 1:37:55 PM
	 */
	public void setQuestion1(String question1)
	{
		this.question1 = question1;
	}
	/**
	 * Getter Method	: getQuestion2
	 * @return the question2
	 * 
	 * Date				: Aug 27, 2015
	 */
	public String getQuestion2()
	{
		return this.question2;
	}
	/**
	 * Setter method : setQuestion2
	 * @param question2 the question2 to set
	 * 
	 * Date          : Aug 27, 2015 1:37:55 PM
	 */
	public void setQuestion2(String question2)
	{
		this.question2 = question2;
	}
	/**
	 * Getter Method	: getQuestion3
	 * @return the question3
	 * 
	 * Date				: Aug 27, 2015
	 */
	public String getQuestion3()
	{
		return this.question3;
	}
	/**
	 * Setter method : setQuestion3
	 * @param question3 the question3 to set
	 * 
	 * Date          : Aug 27, 2015 1:37:55 PM
	 */
	public void setQuestion3(String question3)
	{
		this.question3 = question3;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OpenEndQuestion ["
				+ (this.question1 != null ? "question1=" + this.question1
						+ ", " : "")
				+ (this.question2 != null ? "question2=" + this.question2
						+ ", " : "")
				+ (this.question3 != null ? "question3=" + this.question3 : "")
				+ "]";
	}
	
	
}
