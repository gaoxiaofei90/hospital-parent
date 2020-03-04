package com.llw.hospital.bs.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;


/** 
 * excel读写工具类---兼容后缀名xls和xlsx
 * 
 **/

public class POIUtil {
	
	private static Logger logger  = Logger.getLogger(POIUtil.class);  
    private final static String xls = "xls";  
    private final static String xlsx = "xlsx";
    private static int poiFlag = 0;//版本号标志
	
    /** 
     * 读入excel文件，解析后返回 
     * @param file 
     * @throws IOException  
     */  
    public List<String[]> readExcel(MultipartFile file) throws IOException{  
       
    	//检查文件  
        checkFile(file);  
        //获得Workbook工作薄对象  
        Workbook workbook = getWorkBook(file);  
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
        List<String[]> list = new ArrayList<String[]>();  
        if(workbook != null){  
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){  
                //获得当前sheet工作表  
                Sheet sheet = workbook.getSheetAt(sheetNum);  
                if(sheet == null){  
                    continue;  
                }  
                 
                if(sheetNum == 0){
                	//获得当前sheet的开始行  
                    int firstRowNum  = sheet.getFirstRowNum();  
                    //获得当前sheet的结束行  
                    int lastRowNum = sheet.getLastRowNum();  
                    //循环除了第一行的所有行  
                    //for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){ 
                      for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){
                        //获得当前行  
                        Row row = sheet.getRow(rowNum);  
                        if(row == null){  
                            continue;  
                        }  
                        //获得当前行的开始列  
                       // int firstCellNum = row.getFirstCellNum();  
                        //获得当前行的列数  int lastCellNum = row.getPhysicalNumberOfCells();  
                        //int lastCellNum = row.getLastCellNum();
                        int arrayLength =sheet.getRow(0).getLastCellNum();//数组长度已第一行为准，即每次产生的数组长度一样
                        String[] cells = new String[arrayLength];  
                        //循环当前行  
                       // for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
                        for(int cellNum = 0; cellNum < arrayLength;cellNum++){ 
                            Cell cell = row.getCell(cellNum); 
                        	cells[cellNum] = getCellValue(cell);              
                        }  
                        list.add(cells);  
                    }  
                }
                
            }  
        }  
        return list;  
    }  
    
    /**
     * 检查文件格式
     * @param file
     * @throws IOException
     */
    public void checkFile(MultipartFile file) throws IOException{  
        //判断文件是否存在  
        if(null == file){  
            logger.error("文件不存在！");  
            throw new FileNotFoundException("文件不存在！");  
        }  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //判断文件是否是excel文件  
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){  
            logger.error(fileName + "不是excel文件");  
            throw new IOException(fileName + "不是excel文件");  
        }  
    } 
    
    
    public  Workbook getWorkBook(MultipartFile file) {  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
            //获取excel文件的io流  
            InputStream is = file.getInputStream();  
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith(xls)){   //2003  
            	poiFlag = 2003;
                workbook = new HSSFWorkbook(is); 
            }else if(fileName.endsWith(xlsx)){  //2007  
            	poiFlag = 2007;
            	workbook = new XSSFWorkbook(is); 
            }  
        } catch (IOException e) {  
            logger.info(e.getMessage());  
        }  
        return workbook;  
    }  

    /**
     * 获取单元格内容
     * @param cell
     * @return
     */
    public  String getCellValue(Cell cell){  
        String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());  
                break;  
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());  
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";  
                break; 
            default:  
                cellValue = "未知类型";  
                break;  
        }  
        return cellValue;  
    } 
    
    
    public  HSSFWorkbook  createExcel(String sheetName, String []title, String [][]values,  String [][]validationData,  HSSFWorkbook wb){
    	
    	 	// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
	        if(wb == null){
	            wb = new HSSFWorkbook();
	        }
	
	        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet 
	        HSSFSheet sheet = wb.createSheet(sheetName);
	     
	        //设置sheet格式---设置表格整体样式
	        HSSFCellStyle cellStyle = wb.createCellStyle();// 样式对象
            HSSFDataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            cellStyle.setWrapText(true);//自动换行
            if(sheetName.equals("住院信息模板")){
            	for(int i=0; i<title.length; i++){
            		sheet.setDefaultColumnStyle(i, cellStyle);//设置列单元格格式为文本格式
            	}
            	String[] medicalData  =  {"住院","门诊","门特","购药"};
            	String[] busiTypeDate =  {"城镇职工医保","城乡居民医保"};
            	String[] statusData   =  {"在院","出院"};
            	String[] zoneData   =  null;
            	/*
            	if(validationData != null){
            		zoneData = new String[validationData.length];
    		        for(int i=0;i<validationData.length;i++){
    		            for(int j=0;j<validationData[i].length;j++){
    		            	if(j==0){
    		            		zoneData[i] = validationData[i][j]+"("+ validationData[i][j+1]+")";
    		            	}
    		            }
    		        }
    		        sheet.addValidationData(setDataValidation(sheet, zoneData, 1, 65535, 0, 0));
    	        }*/
            	
            	//sheet.addValidationData(setDataValidation(sheet, busiTypeDate, 1, 65535, 1, 1));
            	//sheet.addValidationData(setDataValidation(sheet, medicalData, 1, 65535, 3, 3));
            	//sheet.addValidationData(setDataValidation(sheet, statusData, 1, 65535, 12, 12));
            }
	        short rowHeight = 250;
	        sheet.setDefaultRowHeight(rowHeight);
	        //设置列宽
	        sheet.setColumnWidth(1, 4000);
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
	        HSSFRow row = sheet.createRow(0);
	        //声明列对象
	        HSSFCell cell = null;
	        
	        //创建标题并设置标题样式
	        HSSFCellStyle headStyle = wb.createCellStyle();// 样式对象
	        HSSFFont hssfFont =  wb.createFont();//生成一个字体
	        hssfFont.setColor(HSSFColor.RED.index);//字体颜色为红色
	       // hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体增粗
	        headStyle.setFont(hssfFont);
	        for(int i=0;i<title.length;i++){
	            cell = row.createCell(i);
	            cell.setCellValue(title[i]);
	            cell.setCellStyle(headStyle);
	        }
	
	        if(values != null){
	        	//创建内容
		        for(int i=0;i<values.length;i++){
		            //row = sheet.createRow((short) i+1);
		            row = sheet.createRow(i+1);//不这样处理当行数超过32767会报错
		            for(int j=0;j<values[i].length;j++){
		                //将内容按顺序赋给对应的列对象
		                //row.createCell(j).setCellValue(values[i][j]);
		                //row.createCell(j).setCellStyle(cellStyle);
		            	//if(j != 2 || j != 3 || j != 12 ){
//		            	if(j != 2 || j != 3 || j != 12 ){
		            		cell = row.createCell(j);
			            	cell.setCellValue(values[i][j]);
			            	cell.setCellStyle(cellStyle);
//		            	}
		            }
		        }
	        }
	        
	        for(int i=0; i<title.length; i++){
	        	
	        	if(sheetName.equals("填写规范")){
	        		sheet.setColumnWidth(i, 7000*3);
	        	}else{
	        		sheet.setColumnWidth(i, 6000);
	        	}
	        	
	        }
	        
	        
	        return wb;
    }
    
    
    
	/**
	 * 就诊人员excel生成
	 * @param sheetName
	 * @param title
	 * @param values
	 * @param wb
	 * @return
	 */
	public  HSSFWorkbook  createRecogPersonExcel(String sheetName, String []title, List<String[]> values,   HSSFWorkbook wb){
    	
	 	// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet 
        HSSFSheet sheet = wb.createSheet(sheetName);
     
        //设置sheet格式---设置表格整体样式
        HSSFCellStyle cellStyle = wb.createCellStyle();// 样式对象
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        cellStyle.setWrapText(true);//自动换行
    	for(int i=0; i<title.length; i++){
    		sheet.setDefaultColumnStyle(i, cellStyle);//设置列单元格格式为文本格式
    	}
        	
        short rowHeight = 300;
        sheet.setDefaultRowHeight(rowHeight);
        //设置列宽
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //声明列对象
        HSSFCell cell = null;
        
        //创建标题并设置标题样式
        HSSFCellStyle headStyle = wb.createCellStyle();// 样式对象
        HSSFFont hssfFont =  wb.createFont();//生成一个字体
        // hssfFont.setColor(HSSFColor.RED.index);//字体颜色为红色
        headStyle.setFont(hssfFont);
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headStyle);
        }

        if(values != null){
        	//创建内容
	        for(int i=0; i<values.size(); i++){
	            row = sheet.createRow(i+1);//不这样处理当行数超过32767会报错
	            String [] personArray = values.get(i);
	            for(int j=0; j<personArray.length; j++){
	            		cell = row.createCell(j);
		            	cell.setCellValue(personArray[j]);
		            	cell.setCellStyle(cellStyle);
	            }
	        }
        }
        
     
        return wb;
	}

	


}

