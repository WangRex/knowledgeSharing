package com.knowledgeSharing.common;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.knowledgeSharing.pojo.Deliver;

public class JXLUtil {
	
    private final static String[] headers = {"NO.", "Developer", "Delivery Date", "Task Summary", "Description", "App Changes",
    										"Web Changes", "Config Changes", "DB Changes", "Status", "APAC/US", "UXF",
    										"GC Build IQA", "GC UAT IQA", "SIT", "UAT", "Remark"};
    private final static String[] subHeaders = {"DP", "VF"};
    
    public static void main(String[] args) {
    	List<Deliver> list = new ArrayList<Deliver>();
    	writeExcel(list);  
    }  
      
    public static String writeExcel(List<Deliver> list) {  
        try { 
        	//create workbook
        	String fileName = "deliverExcel" + DataUtils.getNowDate() + ".xls";
        	String fullPath = JXLUtil.class.getResource("/").toString() + "downloads/";
        	fullPath = fullPath.substring(6);
        	String fullFileName = fullPath + fileName;
        	
        	File file = new File(fullPath);
            if (!file.exists()) {
              file.mkdir();
            }
        	
            WritableWorkbook workbook = Workbook.createWorkbook(new File(fullFileName));  

        	//create sheet
            WritableSheet  sheet = workbook.createSheet("Delivery Record", 0);  
//            sheet.getSettings().setDefaultColumnWidth(20);
            //mergeCells
            for(int i = 0; i < 11; i++) {
            	sheet.mergeCells(i, 0, i, 1); 
            }
            for(int j = 11; j < 20; j++) {
            	sheet.mergeCells(j, 0, ++j, 0); 
            }
            sheet.mergeCells(21, 0, 21, 1); 
            
            WritableFont fontTitle = new WritableFont(WritableFont.createFont("Calibri"),14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE);
            WritableCellFormat cellFormatTitle = new WritableCellFormat(fontTitle); 
            Color color = Color.decode("#E6B8B7"); // 自定义的颜色
            workbook.setColourRGB(Colour.PINK2, color.getRed(),color.getGreen(), color.getBlue());
            cellFormatTitle.setBackground(Colour.PINK2);
            cellFormatTitle.setBorder(Border.ALL, BorderLineStyle.THIN); 
            cellFormatTitle.setAlignment(Alignment.CENTRE);
            cellFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableCellFormat formatTitle = new WritableCellFormat(cellFormatTitle);
            
            WritableFont fontContent = new WritableFont(WritableFont.createFont("Calibri"),12);
            WritableCellFormat cellFormatContent = new WritableCellFormat(fontContent); 
            cellFormatContent.setBorder(Border.ALL, BorderLineStyle.THIN);
            //write headers
            for (int i = 0; i < 11; i++) {
        		Label label = new Label(i, 0 , headers[i], formatTitle);  
        		sheet.addCell(label);
            }  
    		Label labelh11 = new Label(11, 0 , headers[11], formatTitle);  
    		sheet.addCell(labelh11);
    		Label labelh13 = new Label(13, 0 , headers[12], formatTitle);  
    		sheet.addCell(labelh13);
    		Label labelh15 = new Label(15, 0 , headers[13], formatTitle);  
    		sheet.addCell(labelh15);
    		Label labelh17 = new Label(17, 0 , headers[14], formatTitle);  
    		sheet.addCell(labelh17);
    		Label labelh19 = new Label(19, 0 , headers[15], formatTitle);  
    		sheet.addCell(labelh19);
    		Label labelh21 = new Label(21, 0 , headers[16], formatTitle);  
    		sheet.addCell(labelh21);
    		
            for(int j = 11;j < 20;) { 
                Label label1 = new Label(j++, 1 , subHeaders[0], formatTitle);  
                Label label2 = new Label(j++, 1 , subHeaders[1], formatTitle);  
                sheet.addCell(label1);   
                sheet.addCell(label2);  
            }
            
            //write contents
        	int rowNum = 2;
        	int noOfList = 1;
            for(Deliver deliver : list) {
            	int colNum = 0;
            	Label label0 = new Label(colNum++, rowNum , String.valueOf(noOfList), cellFormatContent);
            	Label label1 = new Label(colNum++, rowNum , deliver.getDeveloper(), cellFormatContent);
            	Label label2 = new Label(colNum++, rowNum , deliver.getDeliveryDate(), cellFormatContent);
            	Label label3 = new Label(colNum++, rowNum , deliver.getTaskSummary(), cellFormatContent);
            	Label label4 = new Label(colNum++, rowNum , deliver.getDescription(), cellFormatContent);
            	Label label5 = new Label(colNum++, rowNum , deliver.getAppChanges(), cellFormatContent);
            	Label label6 = new Label(colNum++, rowNum , deliver.getWebChanges(), cellFormatContent);
            	Label label7 = new Label(colNum++, rowNum , deliver.getConfigChanges(), cellFormatContent);
            	Label label8 = new Label(colNum++, rowNum , deliver.getDbChanges(), cellFormatContent);
            	Label label9 = new Label(colNum++, rowNum , deliver.getStatus(), cellFormatContent);
            	Label label10 = new Label(colNum++, rowNum , deliver.getRegion(), cellFormatContent);
            	Label label11 = new Label(colNum++, rowNum , deliver.getUxfdp(), cellFormatContent);
            	Label label12 = new Label(colNum++, rowNum , deliver.getUxfvf(), cellFormatContent);
            	Label label13 = new Label(colNum++, rowNum , deliver.getGcBuildIqadp(), cellFormatContent);
            	Label label14 = new Label(colNum++, rowNum , deliver.getGcBuildIqavf(), cellFormatContent);
            	Label label15 = new Label(colNum++, rowNum , deliver.getGcUatIqadp(), cellFormatContent);
            	Label label16 = new Label(colNum++, rowNum , deliver.getGcUatIqavf(), cellFormatContent);
            	Label label17 = new Label(colNum++, rowNum , deliver.getSitdp(), cellFormatContent);
            	Label label18 = new Label(colNum++, rowNum , deliver.getSitvf(), cellFormatContent);
            	Label label19 = new Label(colNum++, rowNum , deliver.getUatdp(), cellFormatContent);
            	Label label20 = new Label(colNum++, rowNum , deliver.getUatvf(), cellFormatContent);
            	Label label21 = new Label(colNum++, rowNum , deliver.getRemark(), cellFormatContent);
            	sheet.addCell(label0);
            	sheet.addCell(label1);
            	sheet.addCell(label2);
            	sheet.addCell(label3);
            	sheet.addCell(label4);
            	sheet.addCell(label5);
            	sheet.addCell(label6);
            	sheet.addCell(label7);
            	sheet.addCell(label8);
            	sheet.addCell(label9);
            	sheet.addCell(label10);
            	sheet.addCell(label11);
            	sheet.addCell(label12);
            	sheet.addCell(label13);
            	sheet.addCell(label14);
            	sheet.addCell(label15);
            	sheet.addCell(label16);
            	sheet.addCell(label17);
            	sheet.addCell(label18);
            	sheet.addCell(label19);
            	sheet.addCell(label20);
            	sheet.addCell(label21);
            	rowNum++;
            	noOfList++;
            }
            
            workbook.write();  
            workbook.close();  
            return fileName;
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;
        } catch (RowsExceededException e) {  
            e.printStackTrace();  
            return null;
        } catch (WriteException e) {  
            e.printStackTrace();  
            return null;
        }  
  
    }  

}
