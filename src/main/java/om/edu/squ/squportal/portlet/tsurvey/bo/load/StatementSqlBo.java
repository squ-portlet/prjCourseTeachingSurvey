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
 * File Name			:	StatementSqlBo.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.bo.load
 * Date of creation		:	Nov 5, 2015  11:54:29 AM
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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bhabesh
 *
 */
@XmlRootElement(name="sqload")
public class StatementSqlBo implements Serializable
{
private static final long	serialVersionUID	= 1L;
	
	private String	statementLoadNum;
	private	String	loadType;
	private	String	sqlTruncate;
	private	String	sqlInsert;
	private String  message;
	/**
	 * Getter Method	: getStatementLoadNum
	 * @return the statementLoadNum
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlAttribute(name="stmtno")
	public String getStatementLoadNum()
	{
		return this.statementLoadNum;
	}
	/**
	 * Setter method : setStatementLoadNum
	 * @param statementLoadNum the statementLoadNum to set
	 * 
	 * Date          : Nov 5, 2015 11:55:14 AM
	 */
	public void setStatementLoadNum(String statementLoadNum)
	{
		this.statementLoadNum = statementLoadNum;
	}
	/**
	 * Getter Method	: getLoadType
	 * @return the loadType
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlAttribute(name="type")
	public String getLoadType()
	{
		return this.loadType;
	}
	/**
	 * Setter method : setLoadType
	 * @param loadType the loadType to set
	 * 
	 * Date          : Nov 5, 2015 11:55:14 AM
	 */
	public void setLoadType(String loadType)
	{
		this.loadType = loadType;
	}
	/**
	 * Getter Method	: getSqlTruncate
	 * @return the sqlTruncate
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlElement(name="stmtTruncate")
	public String getSqlTruncate()
	{
		return this.sqlTruncate;
	}
	/**
	 * Setter method : setSqlTruncate
	 * @param sqlTruncate the sqlTruncate to set
	 * 
	 * Date          : Nov 5, 2015 11:55:14 AM
	 */
	public void setSqlTruncate(String sqlTruncate)
	{
		this.sqlTruncate = sqlTruncate;
	}
	/**
	 * Getter Method	: getSqlInsert
	 * @return the sqlInsert
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlElement(name="stmtInsert")
	public String getSqlInsert()
	{
		return this.sqlInsert;
	}
	/**
	 * Setter method : setSqlInsert
	 * @param sqlInsert the sqlInsert to set
	 * 
	 * Date          : Nov 5, 2015 11:55:14 AM
	 */
	public void setSqlInsert(String sqlInsert)
	{
		this.sqlInsert = sqlInsert;
	}
	/**
	 * Getter Method	: getMessage
	 * @return the message
	 * 
	 * Date				: Nov 5, 2015
	 */
	@XmlElement(name="msgNotSuccess")
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * Setter method : setMessage
	 * @param message the message to set
	 * 
	 * Date          : Nov 5, 2015 11:55:14 AM
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StatementSqlBo ["
				+ (this.statementLoadNum != null ? "statementLoadNum="
						+ this.statementLoadNum + ", " : "")
				+ (this.loadType != null ? "loadType=" + this.loadType + ", "
						: "")
				+ (this.sqlTruncate != null ? "sqlTruncate=" + this.sqlTruncate
						+ ", " : "")
				+ (this.sqlInsert != null ? "sqlInsert=" + this.sqlInsert
						+ ", " : "")
				+ (this.message != null ? "message=" + this.message : "") + "]";
	}
	
	
}
