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
 * File Name			:	Analysis.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.survey
 * Date of creation		:	Aug 24, 2015  10:04:00 AM
 * Date of modification :	
 * 
 * Summary				:	Teaching Survey Analysis on section level
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

/**
 * @author Bhabesh
 *
 */
public class Analysis
{
	private	String	courseCode;
	private	String	sectionNo;
	private	String	question;
	private	int		strongDisagree;
	private	int		disAgree;
	private	int		agree;
	private	int		strongAgree;
	private	int		notApplicable;
	private	int		total;
	private	float	percentageResponse;
	private	float	sectionMean;
	private	float	courseMean;
	private	float	departmentMean;
	private	float	collegeMean;
	private	String	questionLabel;
	
	/**
	 * Getter Method	: getCourseCode
	 * @return the courseCode
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getCourseCode()
	{
		return this.courseCode;
	}
	/**
	 * Setter method : setCourseCode
	 * @param courseCode the courseCode to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Getter Method	: getSectionNo
	 * @return the sectionNo
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getSectionNo()
	{
		return this.sectionNo;
	}
	/**
	 * Setter method : setSectionNo
	 * @param sectionNo the sectionNo to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}
	/**
	 * Getter Method	: getQuestion
	 * @return the question
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getQuestion()
	{
		return this.question;
	}
	/**
	 * Setter method : setQuestion
	 * @param question the question to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}
	/**
	 * Getter Method	: getStrongDisagree
	 * @return the strongDisagree
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getStrongDisagree()
	{
		return this.strongDisagree;
	}
	/**
	 * Setter method : setStrongDisagree
	 * @param strongDisagree the strongDisagree to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setStrongDisagree(int strongDisagree)
	{
		this.strongDisagree = strongDisagree;
	}
	/**
	 * Getter Method	: getDisAgree
	 * @return the disAgree
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getDisAgree()
	{
		return this.disAgree;
	}
	/**
	 * Setter method : setDisAgree
	 * @param disAgree the disAgree to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setDisAgree(int disAgree)
	{
		this.disAgree = disAgree;
	}
	/**
	 * Getter Method	: getAgree
	 * @return the agree
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getAgree()
	{
		return this.agree;
	}
	/**
	 * Setter method : setAgree
	 * @param agree the agree to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setAgree(int agree)
	{
		this.agree = agree;
	}
	/**
	 * Getter Method	: getStrongAgree
	 * @return the strongAgree
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getStrongAgree()
	{
		return this.strongAgree;
	}
	/**
	 * Setter method : setStrongAgree
	 * @param strongAgree the strongAgree to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setStrongAgree(int strongAgree)
	{
		this.strongAgree = strongAgree;
	}
	/**
	 * Getter Method	: getNotApplicable
	 * @return the notApplicable
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getNotApplicable()
	{
		return this.notApplicable;
	}
	/**
	 * Setter method : setNotApplicable
	 * @param notApplicable the notApplicable to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setNotApplicable(int notApplicable)
	{
		this.notApplicable = notApplicable;
	}
	/**
	 * Getter Method	: getTotal
	 * @return the total
	 * 
	 * Date				: Aug 24, 2015
	 */
	public int getTotal()
	{
		return this.total;
	}
	/**
	 * Setter method : setTotal
	 * @param total the total to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}
	/**
	 * Getter Method	: getPercentageResponse
	 * @return the percentageResponse
	 * 
	 * Date				: Aug 24, 2015
	 */
	public float getPercentageResponse()
	{
		return this.percentageResponse;
	}
	/**
	 * Setter method : setPercentageResponse
	 * @param percentageResponse the percentageResponse to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setPercentageResponse(float percentageResponse)
	{
		this.percentageResponse = percentageResponse;
	}
	/**
	 * Getter Method	: getSectionMean
	 * @return the sectionMean
	 * 
	 * Date				: Aug 24, 2015
	 */
	public float getSectionMean()
	{
		return this.sectionMean;
	}
	/**
	 * Setter method : setSectionMean
	 * @param sectionMean the sectionMean to set
	 * 
	 * Date          : Aug 24, 2015 10:49:50 AM
	 */
	public void setSectionMean(float sectionMean)
	{
		this.sectionMean = sectionMean;
	}
	
	/**
	 * Getter Method	: getCourseMean
	 * @return the courseMean
	 * 
	 * Date				: Aug 24, 2015
	 */
	public float getCourseMean()
	{
		return this.courseMean;
	}
	/**
	 * Setter method : setCourseMean
	 * @param courseMean the courseMean to set
	 * 
	 * Date          : Aug 24, 2015 12:07:14 PM
	 */
	public void setCourseMean(float courseMean)
	{
		this.courseMean = courseMean;
	}
	/**
	 * Getter Method	: getDepartmentMean
	 * @return the departmentMean
	 * 
	 * Date				: Aug 24, 2015
	 */
	public float getDepartmentMean()
	{
		return this.departmentMean;
	}
	/**
	 * Setter method : setDepartmentMean
	 * @param departmentMean the departmentMean to set
	 * 
	 * Date          : Aug 24, 2015 12:07:14 PM
	 */
	public void setDepartmentMean(float departmentMean)
	{
		this.departmentMean = departmentMean;
	}
	/**
	 * Getter Method	: getCollegeMean
	 * @return the collegeMean
	 * 
	 * Date				: Aug 24, 2015
	 */
	public float getCollegeMean()
	{
		return this.collegeMean;
	}
	/**
	 * Setter method : setCollegeMean
	 * @param collegeMean the collegeMean to set
	 * 
	 * Date          : Aug 24, 2015 12:07:14 PM
	 */
	public void setCollegeMean(float collegeMean)
	{
		this.collegeMean = collegeMean;
	}
	/**
	 * Getter Method	: getQuestionLabel
	 * @return the questionLabel
	 * 
	 * Date				: Aug 24, 2015
	 */
	public String getQuestionLabel()
	{
		return this.questionLabel;
	}
	/**
	 * Setter method : setQuestionLabel
	 * @param questionLabel the questionLabel to set
	 * 
	 * Date          : Aug 24, 2015 12:07:14 PM
	 */
	public void setQuestionLabel(String questionLabel)
	{
		this.questionLabel = questionLabel;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Analysis ["
				+ (this.courseCode != null ? "courseCode=" + this.courseCode
						+ ", " : "")
				+ (this.sectionNo != null ? "sectionNo=" + this.sectionNo
						+ ", " : "")
				+ (this.question != null ? "question=" + this.question + ", "
						: "")
				+ "strongDisagree="
				+ this.strongDisagree
				+ ", disAgree="
				+ this.disAgree
				+ ", agree="
				+ this.agree
				+ ", strongAgree="
				+ this.strongAgree
				+ ", notApplicable="
				+ this.notApplicable
				+ ", total="
				+ this.total
				+ ", percentageResponse="
				+ this.percentageResponse
				+ ", sectionMean="
				+ this.sectionMean
				+ ", courseMean="
				+ this.courseMean
				+ ", departmentMean="
				+ this.departmentMean
				+ ", collegeMean="
				+ this.collegeMean
				+ ", "
				+ (this.questionLabel != null ? "questionLabel="
						+ this.questionLabel : "") + "]";
	}

	
	
}
