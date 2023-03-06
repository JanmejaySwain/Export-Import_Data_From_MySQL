package com.data.inputoutput.service.Impl;

import com.data.inputoutput.entity.Employee;
import com.data.inputoutput.helper.MyExcelHelper;
import com.data.inputoutput.repository.EmployeeRepository;
import com.data.inputoutput.service.ExcelFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {
    @Autowired
    private EmployeeRepository employeeRepository;
    

    @Override
    public void save(MultipartFile file) {
        try {
            List<Employee> employees = MyExcelHelper.convertExcelToListOfEmployees(file.getInputStream());
            employeeRepository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
