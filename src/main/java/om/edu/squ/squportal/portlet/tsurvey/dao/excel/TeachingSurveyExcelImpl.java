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
 * File Name			:	TeachingSurveyExcelImpl.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.dao.excel
 * Date of creation		:	Mar 8, 2016  9:40:46 AM
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
package om.edu.squ.squportal.portlet.tsurvey.dao.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceResponse;

import om.edu.squ.squportal.portlet.tsurvey.bo.ReportSummary;
import om.edu.squ.squportal.portlet.tsurvey.bo.StudentResponse;
import om.edu.squ.squportal.portlet.tsurvey.utility.Constants;
import om.edu.squ.squportal.portlet.tsurvey.utility.UtilProperty;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;

/**
 * @author Bhabesh
 *
 */
public class TeachingSurveyExcelImpl
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String	SUB_HEADER	= "sub_header";
	private static final String	TITLE		= "title";
	private static final String	FORMULA_1	= "formula_1";

	
	
	/**
	 * 
	 * method name  : getExcelSurveyReport
	 * @param object
	 * @param response
	 * @param params
	 * @param locale
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 * TeachingSurveyExcelImpl
	 * return type  : OutputStream
	 * 
	 * purpose		: Get Streaming excel object for valid/invalid survey report
	 *
	 * Date    		:	Mar 16, 2016 1:23:57 PM
	 */
	public OutputStream  getExcelSurveyReport(String templateName,Object object,ResourceResponse response, Map<String, String> params, Locale locale) throws DocumentException, IOException
	{
		int 					colHead				=	0;
		int 					rowNum 				= 	0;
		String					paramStaffRole		=	params.get(Constants.CONST_ROLE_STAFF);
		String					paramTypeSurvey		=	params.get(Constants.CONST_PARAM_TYPE_SURVEY);
		String					paramSemesterCode	=	params.get(Constants.CONST_PARAM_SEMESTER_CODE);
		String					titleRegion			=	null;
		
		Workbook				workbook			=	new HSSFWorkbook();
		CreationHelper			creationHelper		=	workbook.getCreationHelper();
		Map<String, CellStyle> 	styles 				= 	createStyles(workbook);
		Sheet					sheet				=	null;
		Cell 					cellSH				=	null;
		List<ReportSummary> 	reportSummaries		=	(List<ReportSummary>) object;
		
		if(templateName.equals(Constants.CONST_VALID_SURVEY_REPORT))
		{
			sheet	=	workbook.createSheet(UtilProperty.getMessage("prop.course.teaching.survey.report.survey.valid", null, locale));
		}
		if(templateName.equals(Constants.CONST_INVALID_SURVEY_REPORT))
		{
			sheet	=	workbook.createSheet(UtilProperty.getMessage("prop.course.teaching.survey.report.survey.invalid", null, locale));
		}
		
		sheet.getPrintSetup().setLandscape(true);
		sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); 

/**  Header Footer **/
		Footer 					footer 				= 	sheet.getFooter();
		Header					header				=	sheet.getHeader();
								footer.setRight("Page &P of &N");
								footer.setLeft("&D");
								header.setLeft(UtilProperty.getMessage("prop.course.teaching.survey.heading", null, locale));
								header.setCenter(UtilProperty.getMessage("prop.course.teaching.survey.title", null, locale));
								header.setRight(paramTypeSurvey +" - "+paramSemesterCode);
		
		sheet.setRepeatingRows(CellRangeAddress.valueOf("2:2"));
		sheet.setDisplayGridlines(true);
		sheet.setPrintGridlines(true);
							
		
		

/**  Title **/		
		Row titleRow = sheet.createRow(rowNum);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(paramTypeSurvey +" - "+paramSemesterCode);
        titleCell.setCellStyle(styles.get(TITLE));

        ++rowNum;
        titleRegion="$A$"+rowNum+":$O$"+rowNum;
        sheet.addMergedRegion(CellRangeAddress.valueOf(titleRegion));

        
/**  Header Row **/
        Row	rowSubHeader	=	sheet.createRow(rowNum++);
        
		if(!paramStaffRole.equals(Constants.CONST_ROLE_STAFF_HOD) && !templateName.equals(Constants.CONST_INVALID_SURVEY_REPORT))
		{
			
		cellSH = 	rowSubHeader.createCell(colHead++); cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.rank.university", null, locale))); 
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH = rowSubHeader.createCell(colHead++); cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.rank.college", null, locale))); 
					cellSH.setCellStyle(styles.get(SUB_HEADER));	
		cellSH = rowSubHeader.createCell(colHead++); cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.rank.department", null, locale)));	
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		}
		cellSH 	=	rowSubHeader.createCell(colHead++); cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.instructor.id", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.instructor.name", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.college", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		if(!paramStaffRole.equals(Constants.CONST_ROLE_STAFF_HOD))
		{
			cellSH	=	rowSubHeader.createCell(colHead++); cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.department", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		}
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.course.code", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.section", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.student.registered", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.response.number", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.analysis.mean", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.percentage", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.analysis.mean", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		cellSH	=	rowSubHeader.createCell(colHead++);	cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.analysis.response.percentage", null, locale)));
					cellSH.setCellStyle(styles.get(SUB_HEADER));
		
		
/**  Report details **/		
		for(ReportSummary reportSummary : reportSummaries)
		{
			int colNum=0;
			Row		row		=	sheet.createRow((short)rowNum);
			
			
			if(!paramStaffRole.equals(Constants.CONST_ROLE_STAFF_HOD) && !templateName.equals(Constants.CONST_INVALID_SURVEY_REPORT) )
			{
				row.createCell(colNum++).setCellValue(reportSummary.getUniversityRank());
				row.createCell(colNum++).setCellValue(reportSummary.getCollegeRank());
				row.createCell(colNum++).setCellValue(reportSummary.getDepartmentRank());
			}
			row.createCell(colNum++).setCellValue(Double.parseDouble(reportSummary.getEmpNumber()));
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(reportSummary.getEmpName()));
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(reportSummary.getCollegeCode()));
			if(!paramStaffRole.equals(Constants.CONST_ROLE_STAFF_HOD))
			{
				row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(reportSummary.getDepartmentName()));
			}
			row.createCell(colNum++).setCellValue(reportSummary.getCourseCode());
			row.createCell(colNum++).setCellValue(Integer.parseInt(reportSummary.getSectionNo()));
			row.createCell(colNum++).setCellValue(reportSummary.getRegisteredStudent());
			
			Cell	cellStudentNoResponse		=	row.createCell(colNum++);
					cellStudentNoResponse.setCellValue(reportSummary.getStudentNoResponse());
					cellStudentNoResponse.setCellStyle(styles.get(FORMULA_1));
			
			Cell 	cellTeachingMean			=	row.createCell(colNum++);
					cellTeachingMean.setCellValue(reportSummary.getTeachingMean());
					cellTeachingMean.setCellStyle(styles.get(FORMULA_1));
					
			Cell	cellTeachingPercentageFavor	=	row.createCell(colNum++);
					cellTeachingPercentageFavor.setCellValue(reportSummary.getTeachingPercentageFavor());
					cellTeachingPercentageFavor.setCellStyle(styles.get(FORMULA_1));
			
			Cell	cellQuestionMean			=	row.createCell(colNum++);
					cellQuestionMean.setCellValue(reportSummary.getQuestionMean());
					cellQuestionMean.setCellStyle(styles.get(FORMULA_1));
			
			Cell	cellQuestionPercentageFavor	=	row.createCell(colNum++);
					cellQuestionPercentageFavor.setCellValue(reportSummary.getQuestionPercentageFavor());
					cellQuestionPercentageFavor.setCellStyle(styles.get(FORMULA_1));

			
			rowNum ++;
			
		}

		
		response.setContentType("application/vnd.ms-excel");
		OutputStream	outputStream	=	response.getPortletOutputStream(); 
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		
		
		return null;
	}

	/**
	 * 
	 * method name  : getExcelCollegeCoursesAsstDean
	 * @param templateName
	 * @param object
	 * @param response
	 * @param params
	 * @param locale
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 * TeachingSurveyExcelImpl
	 * return type  : OutputStream
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Jun 7, 2016 11:49:24 AM
	 */
	public OutputStream  getExcelCollegeCoursesAsstDean(String templateName,Object object,ResourceResponse response, Map<String, String> params, Locale locale) throws DocumentException, IOException
	{
		int 					colHead					=	0;
		int 					rowNum 					= 	0;
		String					paramTypeSurvey			=	params.get(Constants.CONST_PARAM_TYPE_SURVEY);
		String					titleRegion				=	null;
		List<StudentResponse> 	studentResponses		=	(List<StudentResponse>) object;
		
		Workbook				workbook				=	new HSSFWorkbook();
		CreationHelper			creationHelper			=	workbook.getCreationHelper();
		Map<String, CellStyle> 	styles 					= 	createStyles(workbook);
		Sheet					sheet					=	null;
		Cell 					cellSH					=	null;
		
		sheet	=	workbook.createSheet(UtilProperty.getMessage("prop.course.teaching.survey.courses.list", null, locale));
		
		sheet.getPrintSetup().setLandscape(true);
		sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); 
		
/**  Header Footer **/
		Footer 					footer 				= 	sheet.getFooter();
		Header					header				=	sheet.getHeader();
								footer.setRight("Page &P of &N");
								footer.setLeft("&D");
								header.setLeft(UtilProperty.getMessage("prop.course.teaching.survey.heading", null, locale));
								header.setCenter(UtilProperty.getMessage("prop.course.teaching.survey.title", null, locale));
								header.setRight(paramTypeSurvey);
		
		sheet.setRepeatingRows(CellRangeAddress.valueOf("2:2"));
		sheet.setDisplayGridlines(true);
		sheet.setPrintGridlines(true);

/**  Title **/		
		Row titleRow = sheet.createRow(rowNum);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(paramTypeSurvey );
        titleCell.setCellStyle(styles.get(TITLE));

        ++rowNum;
        titleRegion="$A$"+rowNum+":$O$"+rowNum;
        sheet.addMergedRegion(CellRangeAddress.valueOf(titleRegion));		
		
/**  Header Row **/
        Row	rowSubHeader	=	sheet.createRow(rowNum++);		

        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.analysis.department", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.course", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.section", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.committee.member.number", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.committee.member.name", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.seats.taken", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.response.students", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
        cellSH 	=	rowSubHeader.createCell(colHead++); 
        cellSH.setCellValue(creationHelper.createRichTextString(UtilProperty.getMessage("prop.course.teaching.survey.include.exclude", null, locale)));
		cellSH.setCellStyle(styles.get(SUB_HEADER));
		
/**  Report details **/
		for(StudentResponse studentResponse : studentResponses)
		{
			int colNum=0;
			Row		row		=	sheet.createRow((short)rowNum);
			
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(studentResponse.getDepartmentName()));
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(studentResponse.getCourseCode()));
			row.createCell(colNum++).setCellValue(Integer.parseInt(studentResponse.getSectionNo()));
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(studentResponse.getEmpNumber()));
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(studentResponse.getEmpName()));
			row.createCell(colNum++).setCellValue(studentResponse.getSeatsTaken());
			row.createCell(colNum++).setCellValue(studentResponse.getStudentResponse());
			row.createCell(colNum++).setCellValue(creationHelper.createRichTextString(studentResponse.getIncludeExclude()));
			
			rowNum ++;
			
		}
		
		response.setContentType("application/vnd.ms-excel");
		OutputStream	outputStream	=	response.getPortletOutputStream(); 
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		
		return null;
	}
	
	
	
	/**
	 * 
	 * method name  : createStyles
	 * @param wb
	 * @return
	 * TeachingSurveyExcelImpl
	 * return type  : Map<String,CellStyle>
	 * 
	 * purpose		:	Creating Styles for Excell sheet cells
	 *
	 * Date    		:	Mar 16, 2016 1:25:00 PM
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb)
	{
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        
        /*** TITLE  ***/
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put(TITLE, style);
        
        
        /*** SUB-HEADER  ***/
        Font subHeaderFont = wb.createFont();
        subHeaderFont.setFontHeightInPoints((short)9);
        subHeaderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style = wb.createCellStyle();
        style.setFont(subHeaderFont);
        style.setWrapText(true);
        styles.put(SUB_HEADER, style);
        
        
        /*** MAX. TWO DIGIT DECIMAL VALUE  ***/
        style = wb.createCellStyle();
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put(FORMULA_1, style);
        
        

		return styles;
	}
	
}
