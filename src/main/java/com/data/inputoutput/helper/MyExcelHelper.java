package com.data.inputoutput.helper;

import com.data.inputoutput.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyExcelHelper {
 //check file is excel type or not
    public static boolean checkExcelFormat(MultipartFile file)
    {
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }else {
            return false;
        }
    }

    public static List<Employee> convertExcelToListOfEmployees(InputStream is)
    {
        List<Employee> employees=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Data");
            int rowNo=0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext())
            {
                Row row = iterator.next();
                if(rowNo==0)
                {
                    rowNo++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid=0;
                Employee employee=new Employee();
                while (cells.hasNext())
                {
                    Cell cell = cells.next();
                    switch (cid)
                    {
                        case 0:
                            employee.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            employee.setEmpName(cell.getStringCellValue());
                            break;
                        case 2:
                            employee.setDesignation(cell.getStringCellValue());
                            break;
                        case 3:
                            employee.setDepartment(cell.getStringCellValue());
                            break;
                        case 4:
                            employee.setBusinessUnit(cell.getStringCellValue());
                            break;
                        case 5:
                            employee.setGender(cell.getStringCellValue());
                            break;
                        case 6:
                            employee.setEthnicity(cell.getStringCellValue());
                            break;
                        case 7:
                            employee.setAge((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            employee.setJoiningDate(cell.getDateCellValue());
                            break;
                        case 9:
                            employee.setSalary(cell.getNumericCellValue());
                            break;
                        case 10:
                            employee.setBonus(cell.getStringCellValue());
                            break;
                        case 11:
                            employee.setCountry(cell.getStringCellValue());
                            break;
                        case 12:
                            employee.setCity(cell.getStringCellValue());
                            break;
                        case 13:
                            employee.setExit(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                employees.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }

}
