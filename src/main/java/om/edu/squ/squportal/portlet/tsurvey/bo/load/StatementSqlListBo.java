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
 * File Name			:	StatementSqlListBo.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.load
 * Date of creation		:	Nov 5, 2015  11:58:16 AM
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
package om.edu.squ.squportal.portlet.tsurvey.bo.load;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * @author Bhabesh
 *
 */
@XmlRootElement(name="sqloads")
public class StatementSqlListBo
{
	private List<StatementSqlBo> sqlBos;

	/**
	 * Getter Method	: getSqlBos
	 * @return the sqlBos
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlElement(name="sqload")
	public List<StatementSqlBo> getSqlBos()
	{
		return this.sqlBos;
	}

	/**
	 * Setter method : setSqlBos
	 * @param sqlBos the sqlBos to set
	 * 
	 * Date          : Nov 5, 2015 11:59:00 AM
	 */
	public void setSqlBos(List<StatementSqlBo> sqlBos)
	{
		this.sqlBos = sqlBos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StatementSqlListBo ["
				+ (this.sqlBos != null ? "sqlBos=" + this.sqlBos : "") + "]";
	}
	
	
}
