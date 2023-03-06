package com.data.inputoutput.controller;

import com.data.inputoutput.entity.Employee;
import com.data.inputoutput.helper.MyExcelHelper;
import com.data.inputoutput.repository.EmployeeRepository;
import com.data.inputoutput.service.ExcelFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class EmployeeController {

    @Autowired
    private ExcelFileService excelFileService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping("/employee/upload")
    public ResponseEntity<?> uploadExcelFileInDb(@RequestParam("file")MultipartFile file)
    {
        if(MyExcelHelper.checkExcelFormat(file))
        {
           excelFileService.save(file);
            return new ResponseEntity<>("Excel sheet is uploaded and data is saved to DataBase", HttpStatus.OK);
        }
        return new ResponseEntity<>("Enter excel format file only", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmplyees()
    {
        return new ResponseEntity<>(excelFileService.getAllEmployees(),HttpStatus.OK);
    }
    @GetMapping("/employee/download/csv")
    public ResponseEntity<?> downloadInCsvFormat(HttpServletResponse response) throws IOException
    {
        List<Employee> rows = employeeRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"my_file.csv\"");

        try (PrintWriter writer = response.getWriter()) {
            writer.print("Employee Id , Full Name , Job Title , Department , Business Unit , Gender , Ethnicity , Age , Hire Date , Annual Salary , Bonous% ,Country , City , Exit Date\n");
            for (Employee row : rows) {
                writer.print(row.getId());
                writer.print(",");
                writer.print(row.getEmpName());
                writer.print(",");
                writer.print(row.getDesignation());
                writer.print(",");
                writer.print(row.getDepartment());
                writer.print(",");
                writer.print(row.getBusinessUnit());
                writer.print(",");
                writer.print(row.getGender());
                writer.print(",");
                writer.print(row.getEthnicity());
                writer.print(",");
                writer.print(row.getAge());
                writer.print(",");
                writer.print(row.getJoiningDate());
                writer.print(",");
                writer.print(row.getSalary());
                writer.print(",");
                writer.print(row.getBonus());
                writer.print(",");
                writer.print(row.getCountry());
                writer.print(",");
                writer.print(row.getCity());
                writer.print(",");
                writer.print(row.getExit());
                writer.println();
            }
        }
        return new ResponseEntity<>("File downloaded successfully",HttpStatus.OK);

    }
}
