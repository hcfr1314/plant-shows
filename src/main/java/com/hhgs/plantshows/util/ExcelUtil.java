package com.hhgs.plantshows.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.hhgs.plantshows.common.CommonConstant.EMPTY_STRING;
import static org.apache.poi.ss.usermodel.CellType.*;

public class ExcelUtil {


    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    private String filename;
    private XSSFSheetXMLHandler.SheetContentsHandler handler;

    private static int size=0;

    public ExcelUtil(String filename) {
        this.filename = filename;
    }

    public ExcelUtil setHandler(XSSFSheetXMLHandler.SheetContentsHandler handler) {
        this.handler = handler;
        return this;
    }

    public static int size(){
        return size;
    }


    public void parse() {

        OPCPackage pkg = null;
        InputStream sheetInputStream = null;

        try {
            pkg = OPCPackage.open(filename, PackageAccess.READ);
            XSSFReader xssfReader = new XSSFReader(pkg);

            StylesTable styles = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
            sheetInputStream = xssfReader.getSheetsData().next();

            processSheet(styles, strings, sheetInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (sheetInputStream != null) {
                try {
                    sheetInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pkg != null) {
                try {
                    pkg.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream) throws SAXException, ParserConfigurationException, IOException {
        XMLReader sheetParser = SAXHelper.newXMLReader();

        if (handler != null) {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, handler, false));
        } else {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, new SimpleSheetContentsHandler(), false));
        }

        sheetParser.parse(new InputSource(sheetInputStream));
    }

    public static class SimpleSheetContentsHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

        private List<String> row = new LinkedList<>();
        //??????SimpleSheetContentsHandler???????????????????????????????????????????????????????????????????????????????????????26????????????????????????
        private static final List<String> rowValueChexkList= Arrays.asList(new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"});
        int index=0;

        @Override
        public void startRow(int rowNum) {
            row.clear();
        }

        @Override
        public void endRow(int rowNum) {
            System.err.println(rowNum + " : " + row);
        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            index=rowValueChexkList.indexOf(cellReference.substring(0,1));
            int size=row.size();
            if(row.size()>0&&index-(size-1)>1){//??????????????????????????????
                for(int i=0;i<index-(size-1)-1;i++){
                    row.add("");
                }
            }
            row.add(formattedValue);
            index=0;
        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {

        }

    }

    public static List<String> fetchOneExcelRow(Sheet sheet, int rowIndex) throws Exception {
    	if(sheet instanceof HSSFSheet) {
    		return fetchOneHSSFExcelRow((HSSFSheet)sheet, rowIndex);
    	} else {
    		return fetchOneExcelRow((XSSFSheet)sheet, rowIndex);
    	}
    }

    //??????????????????
    private static List<String> fetchOneExcelRow(XSSFSheet st, int rowIndex) {

        XSSFRow row = st.getRow(rowIndex);

        //return empty list
        if (row == null) {
            return new ArrayList<String>();
        }

        List<String> values = new ArrayList<String>();

        XSSFCell cell = null;

        for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
            String value = EMPTY_STRING;
            cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null) {
                // ????????????????????????????????????????????????????????????
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = EMPTY_STRING;
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case FORMULA:
                        //????????????????????????????????????????????????
                        if (!cell.getStringCellValue().equals(EMPTY_STRING)) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + EMPTY_STRING;
                        }
                        break;
                    case BLANK:
                        break;
                    case ERROR:
                        value = EMPTY_STRING;
                        break;
                    case BOOLEAN:
                        value = (cell.getBooleanCellValue() ? "Y" : "N");
                        break;
                    default:
                        value = EMPTY_STRING;
                }
            }
            if (columnIndex == 0 && StringUtil.testStrIsNUll(value)) {
                break;
            }
            values.add(value.trim());
        }

        //???????????????????????????????????????????????????????????????
        /*for (int index = 0; index < values.size(); index++) {
            if (CommonUtil.testStringEmpty(values.get(index))) {
                values = values.subList(0, index);
                break;
            }
        }*/
        return values;
    }

    //??????????????????
    private static List<String> fetchOneHSSFExcelRow(HSSFSheet st, int rowIndex) {

        HSSFRow row = st.getRow(rowIndex);

        //return empty list
        if (row == null) {
            return new ArrayList<>();
        }

        List<String> values = new ArrayList<>();

        HSSFCell cell = null;

        for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
            String value = EMPTY_STRING;
            cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null) {
                // ????????????????????????????????????????????????????????????
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = EMPTY_STRING;
                            }
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case FORMULA:
                        //????????????????????????????????????????????????
                        if (!cell.getStringCellValue().equals(EMPTY_STRING)) {
                            value = cell.getStringCellValue();
                        } else {
                            value = cell.getNumericCellValue() + EMPTY_STRING;
                        }
                        break;
                    case BLANK:
                        break;
                    case ERROR:
                        value = EMPTY_STRING;
                        break;
                    case BOOLEAN:
                        value = (cell.getBooleanCellValue() ? "Y" : "N");
                        break;
                    default:
                        value = EMPTY_STRING;
                }
            }
            if (columnIndex == 0 && StringUtil.testStrIsNUll(value)) {
                break;
            }
            values.add(value.trim());
        }

        //???????????????????????????????????????????????????????????????
        for (int index = 0; index < values.size(); index++) {
            if (StringUtil.testStrIsNUll(values.get(index))) {
                values = values.subList(0, index);
                break;
            }
        }
        return values;
    }


    /**
     * ??????excel??????
     *
     * @param wb workbook
     * @param sheetIndex    sheet???????????????0??????
     * @param startReadLine ??????????????????:???0??????
     * @param tailLine      ????????????????????????
     */
    public static void readExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {

        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = null;

        for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {

            row = sheet.getRow(i);
            for (Cell c : row) {
                c.setCellType(STRING);
                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                //?????????????????????????????????
                if (isMerge) {
                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                }
            }

        }

    }


    public static void setSheetSicze(File file) {
        Workbook workBook = getWorkBook(file);
        if(workBook!=null) {
            Sheet sheetAt = workBook.getSheetAt(0);
            size = sheetAt.getLastRowNum();
        }

    }

    /**
     * ????????????Excel??????
     *
     * @param file          ??????
     * @param sheetIndex    sheet???????????????0??????
     * @param startReadLine ??????????????????:???0??????
     * @return ????????????
     */
    public static List<String[]> readStandardExcel(File file, int sheetIndex, int startReadLine,int endReadLine) {

        Workbook workbook = getWorkBook(file);
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);

           /* int lastRowNum = sheet.getLastRowNum();
            size=lastRowNum;*/

            for (int rowNum = startReadLine; rowNum <= endReadLine; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                String[] cells = new String[row.getLastCellNum()];
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell).trim();
                }

                list.add(cells);
            }
        }
        return list;

    }

    /**
     * ????????????Excel??????
     *
     * @param file  ?????????????????????
     * @param sheetIndex  sheet??????  ???0??????
     * @param startRow  ????????????????????????
     * @param endRow    ??????????????????
     * @param startCell ????????????????????????
     * @param endCell   ??????????????????
     * @return  ??????list<String[]>
     */
    public static List<String[]> readStandardExcel(File file, int sheetIndex, int startRow, int endRow, int startCell, int endCell) {
        Workbook workbook = getWorkBook(file);
        List<String[]> list = new ArrayList<>();

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            endRow=endRow==0?sheet.getLastRowNum():endRow;
            for (int rowNum = startRow; rowNum <= endRow; rowNum++) {

                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                String[] cells = new String[endCell];
                for (int cellNum = startCell; cellNum < endCell; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell).trim();
                }

                list.add(cells);

            }

        }
        return list;

    }

    /**
     * ??????Excel??????????????????
     *
     * @param file  ??????????????????
     * @param sheetIndex  ???????????????sheet ??????
     * @return   ??????List<String>
     */
    public static List<String> getExcelTitle(File file, int sheetIndex) {

        List<String> titles = new ArrayList<>();

        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(0);
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            titles.add(getCellValue(cell).trim());
        }
        return titles;

    }

    /**
     * ????????????????????????Excel??????????????????
     *
     * @param file  ??????????????????
     * @param sheetIndex  ??????sheet ??????
     * @param rowNum  ????????????
     * @return  List<String>
     */
    public static List<String> getExcelTitleByRowNum(File file, int sheetIndex,int rowNum) {

        List<String> titles = new ArrayList<>();

        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowNum);
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            titles.add(getCellValue(cell).trim());
        }
        return titles;

    }

    /**
     * ???????????????????????????
     *
     * @param sheet excel???sheet??????
     * @param row   ????????????
     * @param column  ????????????
     * @return  String
     */
    private static String getMergedRegionValue(Sheet sheet, int row, int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {

                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }

    /**
     * ??????????????????
     *
     * @param sheet ?????????sheet
     * @param row   ????????????
     * @param column ????????????
     * @return false/true
     */
    public static boolean isMergedRow(Sheet sheet, int row, int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row == firstRow && row == lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param sheet  excel sheet??????
     * @param row    ?????????
     * @param column ?????????
     * @return false/true
     */
    private static boolean isMergedRegion(Sheet sheet, int row, int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {

            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ??????sheet?????????????????????????????????
     *
     * @param sheet ?????????sheet
     * @return true/false
     */
    private static boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0;
    }

    /**
     * ???????????????
     *
     * @param sheet sheet
     * @param firstRow ?????????
     * @param lastRow  ?????????
     * @param firstCol ?????????
     * @param lastCol  ?????????
     */
    private static void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * ?????????????????????
     *
     * @param cell ???????????????
     * @return string
     */
    private static String getCellValue(Cell cell) {

        if (cell == null) return "";
        // ???????????????String?????????????????????1??????1.0?????????
        if (cell.getCellTypeEnum() == NUMERIC) {
            cell.setCellType(STRING);
        }
        if (cell.getCellTypeEnum() == STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellTypeEnum() == BLANK) {
            return "NULL";
        }
        return "";
    }

    /**
     * ??????workboot
     *
     * @param file ??????
     * @return Workbook
     */
    private static Workbook getWorkBook(File file) {

        String fileName = file.getName();
        Workbook workbook = null;
        try {
            InputStream is = new FileInputStream(file);
            if (fileName.endsWith(XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * ?????????excel.
     *
     * @param fileDir   excel?????????
     * @param sheetName ?????????????????????????????????????????????????????????
     * @param titleRow  excel????????????????????????
     * @param data      ????????????
     * @return ????????????
     * @throws Exception e
     */
    public static String createExcel(String fileDir, String sheetName, String[] titleRow, String[][] data) throws Exception {
        FileUtil.checkIsExist(fileDir);
        // ??????workbook
        Workbook workbook = new XSSFWorkbook();
        // ??????Worksheet????????????sheet????????????xls????????????????????????)
        Sheet sheet = workbook.createSheet(sheetName);
        // ????????????
        String path = fileDir + "/" + sheetName + ".xlsx";
        try (FileOutputStream out = new FileOutputStream(path)) {
            // ????????????
            Row row = workbook.getSheet(sheetName).createRow(0);//???????????????
            for (short i = 0; i < titleRow.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            // ??????????????????
            if (data != null && data.length > 0) {

                for (int i = 1; i <= data.length; i++) {
                    // ?????????
                    Row dataRow = sheet.createRow(i);
                    // ????????????
                    String[] datas = data[i - 1];
                    for (int j = 0; j < datas.length; j++) {

                        Cell dataCell = dataRow.createCell(j);
                        dataCell.setCellValue(datas[j]);
                    }
                }
            }
            // ??????????????????
            workbook.write(out);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * ???????????????????????????
     * @param fileDir ????????????
     * @param sheetName sheet??????
     * @param titleRow ??????
     * @param data     ????????????
     * @param regionList  ??????
     * @return  ????????????
     * @throws Exception io??????
     */
    public static String createRegionExcel(String fileDir, String sheetName, String[] titleRow, String[][] data, List<CellRangeAddress> regionList) throws Exception {

        // ??????workbook
        Workbook workbook = new XSSFWorkbook();
        // ??????Worksheet????????????sheet????????????xls????????????????????????)
        Sheet sheet = workbook.createSheet(sheetName);
        // ????????????
        String path = fileDir + "/" + sheetName + ".xlsx";
        try (FileOutputStream out = new FileOutputStream(path)) {
            // ????????????
            Row row = workbook.getSheet(sheetName).createRow(0);//???????????????
            for (short i = 0; i < titleRow.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            // ??????????????????
            if (data != null && data.length > 0) {

                for (int i = 1; i <= data.length; i++) {
                    // ?????????
                    Row dataRow = sheet.createRow(i);
                    // ????????????
                    String[] datas = data[i - 1];
                    for (int j = 0; j < datas.length; j++) {

                        Cell dataCell = dataRow.createCell(j);
                        dataCell.setCellValue(datas[j]);
                    }
                }
            }

            for(CellRangeAddress region : regionList){
                sheet.addMergedRegion(region);
            }
            // ??????????????????
            workbook.write(out);
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
