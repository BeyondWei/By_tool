package com.sztech.szcloud.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台与Excel表转入转出
 *
 * @author YScredit-WRF
 */
@Slf4j
public class ExcelUtil {

    /**
     * 写入excle并上传
     *
     * @param realPath
     * @param objectList
     * @param headList
     * @return
     */
    public static String writeToExcel(String realPath, List<String[]> objectList, List<String> headList) throws IOException {
        if (objectList != null && objectList.size() > 0) {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet");
            int rows = objectList.size();
            //写入表头
            Row row1 = sheet.createRow(0);
            for (int i = 0; i < headList.size(); i++) {
                row1.createCell(i).setCellValue(headList.get(i));
            }
            //循环写入行数据
            for (int i = 0; i < rows; i++) {
                Row row = sheet.createRow(i + 1);
                String[] object = objectList.get(i);
                for (int j = 0; j < headList.size(); j++) {
                    row.createCell(j).setCellValue(object[j]);
                }
            }

            //创建文件流
            String fileName = String.valueOf(System.currentTimeMillis());
            String fileNamePath = realPath + fileName + ".xls";
            OutputStream os = null;
            try {
                File file = new File(fileNamePath);
                file.createNewFile();
                os = new FileOutputStream(file);
                workbook.write(os);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                os.close();
            }

            return fileNamePath;
        } else {
            return null;
        }
    }

    /**
     * 从excel表读入数据
     *
     * @param filePath：excel文件全路径名（包括文件名）
     * @return：返回读取到的字符串数组集合
     */
    public static List<String[]> readFromExcel(String filePath) throws IOException {
        List<String[]> strList = new ArrayList<String[]>();
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            Workbook workbook = null;
            if (fileType.equals("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else {
                log.error("文件格式不正确");
                return strList;
            }
            Sheet sheet = workbook.getSheetAt(0);
            //列数
            int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
            for (Row row : sheet) {
                String[] str = new String[colNum];
                for (int i = 0; i < colNum; i++) {
                    try {
                        Cell cell = row.getCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        str[i] = cell.getStringCellValue();
                    } catch (NullPointerException e) {
                        //e.printStackTrace();
                    }
                }
                strList.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return strList;
        } catch (IOException e) {
            e.printStackTrace();
            return strList;
        }finally {
            is.close();
        }
        return strList;
    }

    /**
     * excle文件生成并导出
     */
    public static void getExcle(HttpServletRequest request, HttpServletResponse response, List<List<String>> objectList, List<List<String>> headList, String fileName) {
        if (objectList != null) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet");
            int len = 0;
            //写入表头
            for (int i = 0; i < headList.size(); i++) {
                Row headRow = sheet.createRow(i);
                List<String> object = headList.get(i);
                for (int j = 0; j < object.size(); j++) {
                    headRow.createCell(j).setCellValue(object.get(j));
                }
                len = object.size();
            }

            //循环写入行数据
            for (int i = 0; i < objectList.size(); i++) {
                Row row = sheet.createRow(i + headList.size());
                List<String> object = objectList.get(i);
                for (int j = 0; j < len; j++) {
                    row.createCell(j).setCellValue(object.get(j));
                }
            }

            //创建文件流
            if (StringUtils.isEmpty(fileName)) {
                fileName = System.currentTimeMillis() + "";
            }
            fileName = fileName + ".xls";
            try {
                outWrite(request, response, workbook, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * excle文件导出
     */
    private static void outWrite(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb,
                                 String fileName) throws IOException {
        OutputStream output = null;
        try {
            String userAgent = request.getHeader("User-Agent");
            output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            response.setContentType("application/msexcel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            wb.write(output);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * 导入excle解析
     */
    public static List<List<String>> getExcleInfo(MultipartFile file, Integer header) throws IOException {
        Workbook workBook = getWorkBook(file);
        Sheet sheet = workBook.getSheetAt(0);
        List<List<String>> str = new ArrayList<>();
        int number = sheet.getRow(header).getPhysicalNumberOfCells();
        for (Row row : sheet) {
            List<String> rowStr = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                try {
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    } else {
                        rowStr.add(null);
                    }
                    rowStr.add(cell.getStringCellValue());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            str.add(rowStr);
        }
        return str;
    }

    /**
     * 得到Workbook
     *
     * @param file
     * @return
     */
    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        InputStream is = null;
        try {
            //获取excel文件的io流
            is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                //2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }finally {
            is.close();
        }
        return workbook;
    }
}
