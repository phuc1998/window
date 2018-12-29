/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import entities.Bill;
import entities.Category;
import entities.Discount;
import entities.Inventory;
import entities.Payment;
import entities.Phone;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author PHUC
 */
public class ExcelProcess {

    private File file;
    private FileInputStream fileInput;
    private Workbook workbook;

    public ExcelProcess(File file) {
        this.file = file;
    }

    private void init() throws FileNotFoundException, IOException {
        fileInput = new FileInputStream(file);
        if (file.getAbsolutePath().endsWith("xls")) {
            workbook = new HSSFWorkbook(fileInput);
        } else {
            workbook = null;
        }
    }

    public List<Phone> readPhone(int sheetIndex) throws IOException {
        List<Phone> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Category category = new Category();
                Phone phone = new Phone();
                int i = 1;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 1:
                            phone.setName(getDataCell(cell, cellType).toString());
                            break;
                        case 2:
                            phone.setScreenSize(getDataCell(cell, cellType).toString());
                            break;
                        case 3:
                            phone.setCpu(getDataCell(cell, cellType).toString());
                            break;
                        case 4:
                            phone.setRam((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 5:
                            phone.setMemory((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 6:
                            phone.setPin((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 7:
                            phone.setFrontCam((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 8:
                            phone.setBackCam((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 9:
                            phone.setPrice((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 10:
                            category.setId((int) ((double) getDataCell(cell, cellType)));
                            break;
                    }

                    i++;
                }
                phone.setCategory(category);
                list.add(phone);
            }
        }
        return list;
    }

    public List<Category> readCategory(int sheetIndex) throws IOException {
        List<Category> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Category category = new Category();
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 0:
                            category.setManufacturer(getDataCell(cell, cellType).toString());
                            break;
                    }

                    i++;
                }
                list.add(category);
            }
        }
        return list;
    }

    public List<Discount> readDiscount(int sheetIndex) throws IOException, ParseException {
        List<Discount> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Discount discount = new Discount();
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 0:
                            discount.setPercentDiscount((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 1:
                            discount.setTimeBegin((new SimpleDateFormat("dd-MM-yyyy")).parse(getDataCell(cell, cellType).toString()));
                            break;
                        case 2:
                            discount.setTimeEnd((new SimpleDateFormat("dd-MM-yyyy")).parse(getDataCell(cell, cellType).toString()));
                            break;
                    }
                    i++;
                }
                list.add(discount);

            }
        }
        return list;
    }

    public List<Payment> readPayment(int sheetIndex) throws IOException, ParseException {
        List<Payment> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Payment payment = new Payment();
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 0:
                            payment.setMethod( getDataCell(cell, cellType).toString());
                            break;
                    }
                    i++;
                }
                list.add(payment);

            }
        }
        return list;
    }

    public List<Bill> readBill(int sheetIndex) throws IOException, ParseException {
        List<Bill> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Bill bill = new Bill();
                Phone phone = new Phone();
                Discount discount = new Discount();
                Payment payment = new Payment();
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 0:
                            bill.setDateSell((new SimpleDateFormat("dd-MM-yyyy")).parse(getDataCell(cell, cellType).toString()));
                            break;
                        case 1:
                            bill.setCount((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 2:
                            bill.setStatus(getDataCell(cell, cellType).toString());
                            break;
                        case 3:
                            bill.setDiscription(getDataCell(cell, cellType).toString());
                            break;
                        case 4:
                            phone.setId((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 5:
                            discount.setId((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 6:
                            payment.setId((int) ((double) getDataCell(cell, cellType)));
                            break;
                    }
                    i++;
                }
                list.add(bill);

            }
        }
        return list;
    }
    
    
    public List<Inventory> readInventory(int sheetIndex) throws IOException, ParseException {
        List<Inventory> list = new ArrayList<>();
        init();
        if (workbook == null) {
            return null;
        } else {
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Inventory inventory = new Inventory();
                Phone phone = new Phone();
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (i) {
                        case 0:
                            inventory.setCount((int) ((double) getDataCell(cell, cellType)));
                            break;
                        case 1:
                            inventory.setDateImport((new SimpleDateFormat("dd-MM-yyyy")).parse(getDataCell(cell, cellType).toString()));
                            break;
                        case 2:
                            phone.setId((int) ((double) getDataCell(cell, cellType)));
                            break;
                    }
                    i++;
                }
                inventory.setPhone(phone);
                list.add(inventory);

            }
        }
        return list;
    }
    

    private Object getDataCell(Cell cell, CellType cellType) {
        switch (cellType) {
            case _NONE:
                return "";
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return "";
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
