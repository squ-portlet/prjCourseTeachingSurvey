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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.portlet.ResourceResponse;

import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Analysis;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.Survey;
import om.edu.squ.squportal.portlet.tsurvey.bo.survey.SurveyResponse;
import om.edu.squ.squportal.portlet.tsurvey.utility.Constants;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	/**
	 * 
	 * method name  : getPdfSurveyAnalysis
	 * @param object
	 * @param semesterYear
	 * @param questionByYear
	 * @param questionSetNo
	 * @param byos
	 * @param inputStream
	 * @param res
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 * TeachingSurveyPdfImpl
	 * return type  : OutputStream
	 * 
	 * purpose		: Generate PDF content
	 *
	 * Date    		:	Mar 28, 2016 7:21:04 PM
	 */
	public OutputStream  getPdfSurveyAnalysis(Object object, String semesterYear, String questionByYear, int questionSetNo,  ByteArrayOutputStream	byos,  InputStream	inputStream,  ResourceResponse res) throws DocumentException, IOException
	{

		Font	font	=	FontFactory.getFont(FontFactory.TIMES_ROMAN,10,BaseColor.BLACK);
		
		PdfReader		pdfTemplate			=	new PdfReader(inputStream);
		PdfStamper		pdfStamper			=	new PdfStamper(pdfTemplate, byos );
		Survey			survey				=	(Survey) object;
		String			sectionNos			=	"";
		String			seatsTaken			=	"";
		DecimalFormat 	formatter	 		= new DecimalFormat("###.##");
		
		String			RIGHT				=	Constants.RIGHT;
		String			CENTER				=	Constants.CENTER;
		String			LEFT				=	Constants.LEFT;
		
		
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
		
		pdfStamper.getAcroFields().setGenerateAppearances(true);
		
		

		
		/* ****************** */

		
		PdfPTable table = new PdfPTable(13);
		
		table.addCell(getPdfCell("",2,0,font,LEFT));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.course.teaching.items", null),2,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.number", null),0,6,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.percentage", null),2,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.mean", null),0,4,font,CENTER));
		
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.disagree.strong",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.disagree",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.agree",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.agree.strong",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.applicable.not",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.total",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.sect",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.crs",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.dept",null),0,0,font,CENTER));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.col",null),0,0,font,CENTER));
		
		/* ---------------------------------------------------------------------------- */
		table.addCell(getPdfCell("", 0, 0, font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.course.items",null), 0, 12,font,CENTER));
		
		int 	cTotal 			= 	0;
		float 	cPctVal			=	0f;
		float	cSectionMean	=	0f;
		float	cCourseMean		=	0f;
		float	cDepart			=	0f;
		float	cCollege		=	0f;
		int		rowCount		=	0;
		
		for(SurveyResponse sures : survey.getSurveyResponses())
		{
			for(Analysis analysis : sures.getAnalysisList())
			{
				if(analysis.getQuestion().equals("Q2") || analysis.getQuestion().equals("Q14") || analysis.getQuestion().equals(questionByYear))
				{
					/***  First part    ***/
					table.addCell(getPdfCell(analysis.getQuestion(),0,0,font));
					table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.set"+questionSetNo+".question"+analysis.getQuestionLabel(), null),0,0,font,LEFT));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongDisagree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getDisAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getNotApplicable()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getTotal()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getPercentageResponse()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getSectionMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getCourseMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getDepartmentMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getCollegeMean()),0,0,font,RIGHT));
					
					cTotal			=	cTotal 			+ 	analysis.getTotal();
					cPctVal			=	cPctVal			+ 	analysis.getPercentageResponse();
					cSectionMean	=	cSectionMean	+	analysis.getSectionMean();
					cCourseMean		=	cCourseMean		+	analysis.getCollegeMean();
					cDepart			=	cDepart			+	analysis.getDepartmentMean();
					cCollege		=	cCollege		+	analysis.getCollegeMean();
					rowCount		=	rowCount		+	1;
					
				}
			}
		}
		
		
					/***  First part - Summary   ***/
					table.addCell(getPdfCell(String.valueOf(""),0,0,font));
					table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.summary", null),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(""),0,5,font));
					table.addCell(getPdfCell(String.valueOf(cTotal),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(formatter.format(cPctVal/rowCount)),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(formatter.format(cSectionMean/rowCount)),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(formatter.format(cCourseMean/rowCount)),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(formatter.format(cDepart/rowCount)),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(formatter.format(cCollege/rowCount)),0,0,font,RIGHT));
					
					table.addCell(getPdfCell("", 0, 13, font));
					
					table.addCell(getPdfCell("", 0, 0, font));
					table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.teaching.items", null), 0, 12, font,LEFT));
		
					
					
					
			cTotal 			= 	0;
		 	cPctVal			=	0f;
			cSectionMean	=	0f;
			cCourseMean		=	0f;
			cDepart			=	0f;
			cCollege		=	0f;
			rowCount		=	0;			
		for(SurveyResponse sures : survey.getSurveyResponses())
		{
			for(Analysis analysis : sures.getAnalysisList())
			{
				if(!(analysis.getQuestion().equals("Q2") || analysis.getQuestion().equals("Q14") || analysis.getQuestion().equals(questionByYear)))
				{

					table.addCell(getPdfCell(analysis.getQuestion(),0,0,font));
					table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.set"+questionSetNo+".question"+analysis.getQuestionLabel(), null),0,0,font,LEFT));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongDisagree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getDisAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getStrongAgree()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getNotApplicable()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getTotal()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getPercentageResponse()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getSectionMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getCourseMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getDepartmentMean()),0,0,font,RIGHT));
					table.addCell(getPdfCell(String.valueOf(analysis.getCollegeMean()),0,0,font,RIGHT));
					
					
					cTotal			=	cTotal 			+ 	analysis.getTotal();
					cPctVal			=	cPctVal			+ 	analysis.getPercentageResponse();
					cSectionMean	=	cSectionMean	+	analysis.getSectionMean();
					cCourseMean		=	cCourseMean		+	analysis.getCollegeMean();
					cDepart			=	cDepart			+	analysis.getDepartmentMean();
					cCollege		=	cCollege		+	analysis.getCollegeMean();
					rowCount		=	rowCount		+	1;
					
				}
				
			}
			
		}
					
		
		
		/***  Second part - Summary   ***/
		table.addCell(getPdfCell(String.valueOf(""),0,0,font));
		table.addCell(getPdfCell(UtilProperty.getMessage("prop.course.teaching.survey.analysis.summary", null),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(""),0,5,font));
		table.addCell(getPdfCell(String.valueOf(cTotal),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(formatter.format(cPctVal/rowCount)),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(formatter.format(cSectionMean/rowCount)),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(formatter.format(cCourseMean/rowCount)),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(formatter.format(cDepart/rowCount)),0,0,font,RIGHT));
		table.addCell(getPdfCell(String.valueOf(formatter.format(cCollege/rowCount)),0,0,font,RIGHT));
		
		if(!survey.getMessage().equals(""))
		{
			table.addCell(getPdfCell("", 0, 13, font));
			table.addCell(getPdfCell("", 0, 0, font));
			table.addCell(getPdfCell(survey.getMessage(), 0, 12, font,CENTER));			
		}

		
		
		
		
		
		
		table.setTotalWidth(750); 
		table.setLockedWidth(true); 
		table.setWidths(new float[]{1,6, 1, 1,1,1,1,1,1,1,1,1,1});             
		
		ColumnText	column = new ColumnText(pdfStamper.getOverContent(1));
		Rectangle rectPage1 = new Rectangle(120, 20, 659, 480);
		
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
	private PdfPCell	getPdfCell(String content, int rowSpan, int colSpan,Font font, String txtAlign)
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

			if(txtAlign.equals(Constants.CENTER))
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if(null == txtAlign || txtAlign.equals(Constants.LEFT))
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if(txtAlign.equals(Constants.RIGHT))
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			
			
		return cell;
	}
	
	private PdfPCell	getPdfCell(String content, int rowSpan, int colSpan,Font font)
	{
		return getPdfCell(content, rowSpan, colSpan, font, Constants.LEFT);
	}
	
	
}
