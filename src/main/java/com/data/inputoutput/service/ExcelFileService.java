package com.data.inputoutput.service;

import com.data.inputoutput.entity.Employee;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelFileService {
    public void save(MultipartFile file);
    public List<Employee> getAllEmployees();
}
