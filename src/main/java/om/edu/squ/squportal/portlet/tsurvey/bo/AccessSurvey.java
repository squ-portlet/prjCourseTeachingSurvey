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
 * File Name			:	AccessSurvey.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo
 * Date of creation		:	Aug 27, 2015  11:56:26 AM
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
public class AccessSurvey
{
	private	boolean	booAccessEmpCourse;
	private	boolean booAccessAllSurvey;
	private boolean	booAccesSurveyReport;
	private	boolean	booAccessAssistDean;
	private	boolean	booAccessAdmin;
	private	boolean booViewCommitteeSurvey; 		/*Whether Committee member can view the survey*/
	private	boolean booViewOtherSurvey;				/*Whether Other than Committee member (i.e. Faculty, HOD, Dean, Asst.Dean can view the survey*/
	private	String	dateCommitteeView;
	private	String	dateOthersView;
	
	/**
	 * Getter Method	: isBooAccessEmpCourse
	 * @return the booAccessEmpCourse
	 * 
	 * Date				: Aug 27, 2015
	 */
	public boolean isBooAccessEmpCourse()
	{
		return this.booAccessEmpCourse;
	}
	/**
	 * Setter method : setBooAccessEmpCourse
	 * @param booAccessEmpCourse the booAccessEmpCourse to set
	 * 
	 * Date          : Aug 27, 2015 11:58:16 AM
	 */
	public void setBooAccessEmpCourse(boolean booAccessEmpCourse)
	{
		this.booAccessEmpCourse = booAccessEmpCourse;
	}
	/**
	 * Getter Method	: isBooAccessAllSurvey
	 * @return the booAccessAllSurvey
	 * 
	 * Date				: Aug 27, 2015
	 */
	public boolean isBooAccessAllSurvey()
	{
		return this.booAccessAllSurvey;
	}
	/**
	 * Setter method : setBooAccessAllSurvey
	 * @param booAccessAllSurvey the booAccessAllSurvey to set
	 * 
	 * Date          : Aug 27, 2015 11:58:16 AM
	 */
	public void setBooAccessAllSurvey(boolean booAccessAllSurvey)
	{
		this.booAccessAllSurvey = booAccessAllSurvey;
	}
	
	/**
	 * Getter Method	: isBooAccesSurveyReport
	 * @return the booAccesSurveyReport
	 * 
	 * Date				: Aug 31, 2015
	 */
	public boolean isBooAccesSurveyReport()
	{
		return this.booAccesSurveyReport;
	}
	/**
	 * Setter method : setBooAccesSurveyReport
	 * @param booAccesSurveyReport the booAccesSurveyReport to set
	 * 
	 * Date          : Aug 31, 2015 11:13:11 AM
	 */
	public void setBooAccesSurveyReport(boolean booAccesSurveyReport)
	{
		this.booAccesSurveyReport = booAccesSurveyReport;
	}
	
	/**
	 * Getter Method	: isBooAccessAssistDean
	 * @return the booAccessAssistDean
	 * 
	 * Date				: Sep 9, 2015
	 */
	public boolean isBooAccessAssistDean()
	{
		return this.booAccessAssistDean;
	}
	/**
	 * Setter method : setBooAccessAssistDean
	 * @param booAccessAssistDean the booAccessAssistDean to set
	 * 
	 * Date          : Sep 9, 2015 8:20:39 AM
	 */
	public void setBooAccessAssistDean(boolean booAccessAssistDean)
	{
		this.booAccessAssistDean = booAccessAssistDean;
	}
	
	/**
	 * Getter Method	: isBooAccessAdmin
	 * @return the booAccessAdmin
	 * 
	 * Date				: Sep 16, 2015
	 */
	public boolean isBooAccessAdmin()
	{
		return this.booAccessAdmin;
	}
	/**
	 * Setter method : setBooAccessAdmin
	 * @param booAccessAdmin the booAccessAdmin to set
	 * 
	 * Date          : Sep 16, 2015 1:08:27 PM
	 */
	public void setBooAccessAdmin(boolean booAccessAdmin)
	{
		this.booAccessAdmin = booAccessAdmin;
	}
	
	/**
	 * Getter Method	: isBooViewCommitteeSurvey
	 * @return the booViewCommitteeSurvey
	 * 
	 * Date				: Sep 21, 2015
	 */
	public boolean isBooViewCommitteeSurvey()
	{
		return this.booViewCommitteeSurvey;
	}
	/**
	 * Setter method : setBooViewCommitteeSurvey
	 * @param booViewCommitteeSurvey the booViewCommitteeSurvey to set
	 * 
	 * Date          : Sep 21, 2015 2:13:40 PM
	 */
	public void setBooViewCommitteeSurvey(boolean booViewCommitteeSurvey)
	{
		this.booViewCommitteeSurvey = booViewCommitteeSurvey;
	}
	/**
	 * Getter Method	: isBooViewOtherSurvey
	 * @return the booViewOtherSurvey
	 * 
	 * Date				: Sep 21, 2015
	 */
	public boolean isBooViewOtherSurvey()
	{
		return this.booViewOtherSurvey;
	}
	/**
	 * Setter method : setBooViewOtherSurvey
	 * @param booViewOtherSurvey the booViewOtherSurvey to set
	 * 
	 * Date          : Sep 21, 2015 2:13:40 PM
	 */
	public void setBooViewOtherSurvey(boolean booViewOtherSurvey)
	{
		this.booViewOtherSurvey = booViewOtherSurvey;
	}
	
	/**
	 * Getter Method	: getDateCommitteeView
	 * @return the dateCommitteeView
	 * 
	 * Date				: Sep 21, 2015
	 */
	public String getDateCommitteeView()
	{
		return this.dateCommitteeView;
	}
	/**
	 * Setter method : setDateCommitteeView
	 * @param dateCommitteeView the dateCommitteeView to set
	 * 
	 * Date          : Sep 21, 2015 3:51:08 PM
	 */
	public void setDateCommitteeView(String dateCommitteeView)
	{
		this.dateCommitteeView = dateCommitteeView;
	}
	/**
	 * Getter Method	: getDateOthersView
	 * @return the dateOthersView
	 * 
	 * Date				: Sep 21, 2015
	 */
	public String getDateOthersView()
	{
		return this.dateOthersView;
	}
	/**
	 * Setter method : setDateOthersView
	 * @param dateOthersView the dateOthersView to set
	 * 
	 * Date          : Sep 21, 2015 3:51:08 PM
	 */
	public void setDateOthersView(String dateOthersView)
	{
		this.dateOthersView = dateOthersView;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AccessSurvey [booAccessEmpCourse="
				+ this.booAccessEmpCourse
				+ ", booAccessAllSurvey="
				+ this.booAccessAllSurvey
				+ ", booAccesSurveyReport="
				+ this.booAccesSurveyReport
				+ ", booAccessAssistDean="
				+ this.booAccessAssistDean
				+ ", booAccessAdmin="
				+ this.booAccessAdmin
				+ ", booViewCommitteeSurvey="
				+ this.booViewCommitteeSurvey
				+ ", booViewOtherSurvey="
				+ this.booViewOtherSurvey
				+ ", "
				+ (this.dateCommitteeView != null ? "dateCommitteeView="
						+ this.dateCommitteeView + ", " : "")
				+ (this.dateOthersView != null ? "dateOthersView="
						+ this.dateOthersView : "") + "]";
	}
	
	
}
