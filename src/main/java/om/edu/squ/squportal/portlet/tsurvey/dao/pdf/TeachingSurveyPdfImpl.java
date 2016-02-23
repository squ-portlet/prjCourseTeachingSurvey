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
















import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Analysis;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyResponse;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Bhabesh
 *
 */
public class TeachingSurveyPdfImpl 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public OutputStream  getPdfSurveyAnalysis(Object object, String semesterYear, String questionByYear, ByteArrayOutputStream	byos,  InputStream	inputStream,  ResourceResponse res) throws DocumentException, IOException
	{

		Font	font	=	FontFactory.getFont(FontFactory.TIMES_ROMAN,10,BaseColor.BLACK);
		
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
		
		/* PDF Table Creation */
/*		
		PdfPTable table = new PdfPTable(2);
		table.addCell("#");
		table.addCell("description");
		
		table.setHeaderRows(1);
		table.setWidths(new int[]{ 1, 15 });
		for (int i = 1; i <= 150; i++) {
			table.addCell(String.valueOf(i));
			table.addCell("test " + i);
		}
		
		ColumnText column = new ColumnText(pdfStamper.getOverContent(1));
		Rectangle rectPage1 = new Rectangle(36, 36, 559, 540);
		column.setSimpleColumn(rectPage1);
		column.addElement(table);
		
		int status = column.go();
*/

/*		
		PdfPTable table = new PdfPTable(3);
		// the cell object
		PdfPCell cell;
		// we add a cell with colspan 3
		cell = new PdfPCell(new Phrase("Cell with colspan 3"));
		cell.setColspan(3);
		table.addCell(cell);
		// now we add a cell with rowspan 2
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		// we add the four remaining cells with addCell()
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		
		ColumnText column = new ColumnText(pdfStamper.getOverContent(1));
		Rectangle rectPage1 = new Rectangle(36, 36, 559, 540);
		column.setSimpleColumn(rectPage1);
		column.addElement(table);
		
		int status = column.go();
*/
		
		/* ****************** */

		
		PdfPTable table = new PdfPTable(13);
		
		table.addCell(getPdfCell("",2,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.course.teaching.items", null),2,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.number", null),0,6,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.percentage", null),2,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.mean", null),0,4,font));
		
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.disagree.strong",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.disagree",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.agree",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.agree.strong",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.applicable.not",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.total",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.sect",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.crs",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.dept",null),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.col",null),0,0,font));
		
		/* ---------------------------------------------------------------------------- */
		table.addCell(getPdfCell("", 0, 0, font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.course.items",null), 0, 12,font));
		
		for(SurveyResponse sures : survey.getSurveyResponses())
		{
			for(Analysis analysis : sures.getAnalysisList())
			{
				if(analysis.getQuestion().equals("Q2") || analysis.getQuestion().equals("Q14") || analysis.getQuestion().equals(questionByYear))
				{
					table.addCell(getPdfCell(analysis.getQuestion(),0,0,font));
					table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.question"+analysis.getQuestionLabel(), null),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongDisagree()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getDisAgree()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getAgree()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongAgree()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getNotApplicable()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getTotal()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getPercentageResponse()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getSectionMean()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getCourseMean()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getDepartmentMean()),0,0,font));
					table.addCell(getPdfCell(String.valueOf(analysis.getCollegeMean()),0,0,font));
				}
			}
		}
		
		
		
		table.setTotalWidth(515); 
		table.setLockedWidth(true); 
		table.setWidths(new float[]{1,2, 1, 1,1,1,1,1,1,1,1,1,1});             
		
		ColumnText column = new ColumnText(pdfStamper.getOverContent(1));
		Rectangle rectPage1 = new Rectangle(0, 36, 659, 540);
		column.setSimpleColumn(rectPage1);
		column.addElement(table);
		
		int status = column.go();
		
		pdfStamper.setFormFlattening(true);
		
		pdfStamper.close();
		
		pdfTemplate.close();
		
		
		

		res.setContentType("application/pdf");
		
		return  res.getPortletOutputStream();
		
	}
	
	/**
	 * 	
	 * method name  : getPdfCell
	 * @param content
	 * @param rowSpan
	 * @param colSpan
	 * @return
	 * TeachingSurveyPdfImpl
	 * return type  : PdfPCell
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Feb 22, 2016 12:52:28 PM
	 */
	private PdfPCell	getPdfCell(String content, int rowSpan, int colSpan,Font font)
	{
		PdfPCell	cell 	= 	new PdfPCell(new Phrase(content,font));
					
			if(rowSpan == 0 && colSpan !=0)
			{
				cell.setColspan(colSpan);
			}
			if(rowSpan != 0 && colSpan == 0)
			{
				cell.setRowspan(rowSpan);
			}
			//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return cell;
	}
	
	
}
