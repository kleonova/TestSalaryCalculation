package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.dto.EmployeeDto;
import test.model.Employee;
import test.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // todo возвращать id
    @PostMapping("/add")
    public String add(@RequestBody Employee employee) {
        employeeService.save(employee);
        return "Employee was saved";
    }

    @GetMapping("/all")
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/leads")
    public List<Employee> getAllLeads() {
        return employeeService.getAllLead();
    }
}
