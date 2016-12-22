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
 * File Name			:	MailUtility.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.schedular
 * Date of creation		:	Nov 18, 2016  11:42:11 AM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2016 the original author or authors and Organization.
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

package om.edu.squ.squportal.portlet.tsurvey.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class MailUtility
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private String emailFrom;
  private String[] emailTo;
  private String[] emailCC;
  private String[] emailBCC;
  private String emailSubject;
  private String emailBody;
  private MultipartFile emailFile;
  
  public MailUtility()
  {
    this.emailFrom = UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.from", null);
    this.emailTo = UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.to", null).split(",");
    this.emailCC = UtilProperty.getMessage("prop.course.teaching.survey.email.presurvey.data.upload.cc", null).split(",");
  }
  
  public boolean sendEmail(String subject, String body)
  {
    boolean mailSuccess = false;
    this.emailSubject = subject;
    this.emailBody = body;
    MailProcess mailProcess = new MailProcess();
    try
    {
      mailSuccess = mailProcess.sendMail(this.emailFrom, this.emailTo, this.emailCC, this.emailBCC, this.emailSubject, this.emailBody, this.emailFile);
    }
    catch (Exception ex)
    {
      this.logger.error("Email send failure. Cause : " + ex.getMessage());
    }
    return mailSuccess;
  }
}
