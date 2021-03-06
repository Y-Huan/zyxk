package com.zyy.zyxk.service.util;

import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.BizException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yang.H
 * @version 1.0
 * @date 1/19/22 10:33 AM
 */
public class ExcelUtil {

    //设置Excel导出样式
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 14);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("宋体");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    public static Workbook  getExcelInfo(MultipartFile file)throws IOException{
        // 1.创建workbook对象，读取整个文档
        InputStream inputStream = null;
        Workbook wb = null;
        try {
            if (file.isEmpty()) {
                throw new BizException(ErrorCode.EXCL_NULL_ERROR);
            }
            String filename=file.getOriginalFilename();
            inputStream = file.getInputStream();
            //得到excel

            String fileType = filename.substring(filename.lastIndexOf("."));
            if((".xls").equals(fileType))
            {
                wb = new HSSFWorkbook(inputStream);  //2003-
            }else if((".xlsx").equals(fileType))
            {
                wb = new XSSFWorkbook(inputStream);  //2007+
            }else
            {
                throw new BizException(ErrorCode.BIND_ERROR);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return wb;
    }
}
