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
 * File Name			:	SurveyResponse.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Aug 18, 2015  11:20:44 AM
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

import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class SurveyResponse
{
	private	String				courseCode;
	private	String				sectionNo;
	private	int					seatsTaken;
	private	int					studentResponse;
	private	List<Analysis>		analysisList;
	
	
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
	 * Date          : Aug 18, 2015 11:21:56 AM
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
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
	 * Date          : Aug 18, 2015 11:21:56 AM
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
	 * Date          : Aug 18, 2015 11:21:56 AM
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
	 * Date          : Aug 18, 2015 11:21:56 AM
	 */
	public void setStudentResponse(int studentResponse)
	{
		this.studentResponse = studentResponse;
	}
	
	/**
	 * Getter Method	: getAnalysisList
	 * @return the analysisList
	 * 
	 * Date				: Aug 24, 2015
	 */
	public List<Analysis> getAnalysisList()
	{
		return this.analysisList;
	}
	/**
	 * Setter method : setAnalysisList
	 * @param analysisList the analysisList to set
	 * 
	 * Date          : Aug 24, 2015 1:06:17 PM
	 */
	public void setAnalysisList(List<Analysis> analysisList)
	{
		this.analysisList = analysisList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SurveyResponse ["
				+ (this.courseCode != null ? "courseCode=" + this.courseCode
						+ ", " : "")
				+ (this.sectionNo != null ? "sectionNo=" + this.sectionNo
						+ ", " : "")
				+ "seatsTaken="
				+ this.seatsTaken
				+ ", studentResponse="
				+ this.studentResponse
				+ ", "
				+ (this.analysisList != null ? "analysisList="
						+ this.analysisList : "") + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.courseCode == null) ? 0 : this.courseCode.hashCode());
		result = prime * result
				+ ((this.sectionNo == null) ? 0 : this.sectionNo.hashCode());
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
		SurveyResponse other = (SurveyResponse) obj;
		if (this.courseCode == null)
		{
			if (other.courseCode != null) return false;
		}
		else if (!this.courseCode.equals(other.courseCode)) return false;
		if (this.sectionNo == null)
		{
			if (other.sectionNo != null) return false;
		}
		else if (!this.sectionNo.equals(other.sectionNo)) return false;
		return true;
	}
	
}
