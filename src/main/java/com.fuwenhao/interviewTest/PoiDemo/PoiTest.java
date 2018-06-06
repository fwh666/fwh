package com.fuwenhao.interviewTest.PoiDemo;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Date;

/**
 * create by fwh on 2018/6/6 下午2:13
 * 根据小范的需求：
 *      使用指定的模版文件，每次打印后流水码的值自增1；
 *
 */
public class PoiTest {

    /**
     * 输出已经加载excle文件
     */
    @Test
    public void excleWriteDemo() {
        try {
            OutputStream outputStream = null;
            long l=0;
           /* File fileIn = new File("/Users/fwh/Downloads/test3.xls");
            InputStream is = new FileInputStream(fileIn);*/
            InputStream is = PoiTest.class.getClassLoader().getResourceAsStream("export/test3.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(is);//读取exlce文件
            //获取指定单元格的流水码
            HSSFSheet sheet1 = workbook.getSheet("Sheet1");
            String lsm = readExcelCell(sheet1, 1, 8);//参数：工作表、行、列
            //循环导出新流水码的excel文件
            for (int i = 1; i < 10; i++) {
                //自增流水码
                l = Long.parseLong(lsm);
                l+=i;
                //重定义新的流水码
                HSSFRow row = sheet1.getRow(0);
                row.createCell(7).setCellValue(l);
                //生成excle文件
                String filePath = "/Users/fwh/Downloads/test_"+l+".xls";
                File fileOut = new File(filePath);
                outputStream = new FileOutputStream(fileOut);
                workbook.write(outputStream);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * excle文件的导入
     */
    @Test
    public void excleRead() throws Exception {
        InputStream resourceAsStream = PoiTest.class.getClassLoader().getResourceAsStream("export/test3.xls");
//        File file = new File("/Users/fwh/Downloads/test3.xls");
//        InputStream is = new FileInputStream(file);
//        HSSFWorkbook workbook = new HSSFWorkbook(is);//读取exlce文件
        HSSFWorkbook workbook = new HSSFWorkbook(resourceAsStream);//读取exlce文件
        HSSFSheet sheet = workbook.getSheet("Sheet1");//读取工作表
        int lastRowIndex = sheet.getLastRowNum();//一共的行数
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);//每行
            if (row == null) {
                break;
            }
            short lastCellNum = row.getLastCellNum();//每行一共的单元格
            for (int j = 0; j < lastCellNum; j++) {
                row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.println(cellValue);//打印每个单元格的值
            }
        }
    }

    /**
     * 另一中加载文件方式--copy
     *
     * @throws IOException
     */
    public void excleReadTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/fwh/Downloads/test2.xls");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        int lastRowIndex = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.println(cellValue);
            }
        }
    }

    /**
     * 读取excle指定单元格的内容
     */
    @Test
    public void rowReadTest() throws IOException {
        File file = new File("/Users/fwh/Downloads/test3.xls");
        InputStream is = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(is);//读取exlce文件
        HSSFSheet sheet = workbook.getSheet("Sheet1");//读取工作表
        int lastRowIndex = sheet.getLastRowNum();//一共的行数
        readExcelCell(sheet, 1, 8);//参数：工作表、行、列

    }

    private String readExcelCell(Sheet sheet, int row, int col) {
        String result = null;
        try {
            Cell cell = sheet.getRow(row - 1).getCell(col - 1, Row.CREATE_NULL_AS_BLANK);
            //可以根据需要设置需要的单元格类型
            cell.setCellType(Cell.CELL_TYPE_STRING);
            result = cell.getStringCellValue();
//            System.out.println(result);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }


    /**
     * 输出excle文件-网上copy
     */
    @Test
    public void excleWriteTest() throws IOException {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
//        String filePath = desktop + "/template.xls";
        String filePath = "/Users/fwh/Downloads/test4.xls";
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("订单号");
        row.createCell(2).setCellValue("下单时间");
        row.createCell(3).setCellValue("个数");
        row.createCell(4).setCellValue("单价");
        row.createCell(5).setCellValue("订单金额");
        row.setHeightInPoints(30); // 设置行的高度

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("NO00001");

        // 日期格式化
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度

        HSSFCell cell2 = row1.createCell(2);
        cell2.setCellStyle(cellStyle2);
        cell2.setCellValue(new Date());

        row1.createCell(3).setCellValue(2);


        // 保留两位小数
        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        HSSFCell cell4 = row1.createCell(4);
        cell4.setCellStyle(cellStyle3);
        cell4.setCellValue(29.5);


        // 货币格式化
        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short) 15);
        font.setColor(HSSFColor.RED.index);
        cellStyle4.setFont(font);

        HSSFCell cell5 = row1.createCell(5);
        cell5.setCellFormula("D2*E2");  // 设置计算公式

        // 获取计算公式的值
        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
        cell5 = e.evaluateInCell(cell5);
        System.out.println(cell5.getNumericCellValue());


        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }

}
