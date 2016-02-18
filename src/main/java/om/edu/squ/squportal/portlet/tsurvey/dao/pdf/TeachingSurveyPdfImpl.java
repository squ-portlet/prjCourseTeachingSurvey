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
 * File Name			:	TeachingSurveyPdfImpl.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.dao.pdf
 * Date of creation		:	Feb 15, 2016  9:38:09 AM
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
package om.edu.squ.squportal.portlet.tsurvey.dao.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Bhabesh
 *
 */
public class TeachingSurveyPdfImpl 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public OutputStream  getPdfSurveyAnalysis(Object object, String semesterYear, ByteArrayOutputStream	byos,  InputStream	inputStream,  ResourceResponse res) throws DocumentException, IOException
	{

		PdfReader	pdfTemplate			=	new PdfReader(inputStream);
		PdfStamper	pdfStamper			=	new PdfStamper(pdfTemplate, byos );
		Survey		survey				=	(Survey) object;
		String		sectionNos			=	"";
		String		seatsTaken			=	"";
		
		
		
		logger.info("CourseCode : "+survey.getCourseCode());
		
		//pdfStamper.setFormFlattening(true);
//		pdfStamper.getAcroFields().setFieldProperty("txtName", "setfflags", PdfFormField.FF_READ_ONLY, null);
		
		pdfStamper.getAcroFields().setField("txtCourse", survey.getCourseCode() + " / " +survey.getCourseName());
		pdfStamper.getAcroFields().setField("txtCollegeName", survey.getCollegeName());
		
		for (SurveyResponse resp : survey.getSurveyResponses() )
		{
			sectionNos = 	sectionNos + resp.getSectionNo() + " ";
			seatsTaken	=	String.valueOf(resp.getSeatsTaken());
		}
		
		pdfStamper.getAcroFields().setField("txtSectionNo", sectionNos);
		pdfStamper.getAcroFields().setField("txtDepartmentName", survey.getDepartmentName());
		pdfStamper.getAcroFields().setField("txtEmpName", survey.getEmpName());
		pdfStamper.getAcroFields().setField("txtStudentRegistered", seatsTaken);
		
		pdfStamper.getAcroFields().setField("txtSemesterYear", semesterYear);
		
		
		
		pdfStamper.close();
		
		pdfTemplate.close();
		
		
		

		res.setContentType("application/pdf");
		
		return  res.getPortletOutputStream();
		
	}
	
}
