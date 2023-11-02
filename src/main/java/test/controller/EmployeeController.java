package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.EmployeeDtoFactory;
import test.dto.AbstractEmployeeDto;
import test.model.Employee;
import test.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public String add(@RequestBody Employee employee) {
        employeeService.save(employee);
        return "Employee was saved";
    }

    @GetMapping("/all")
    public List<AbstractEmployeeDto> getAll() {
        return employeeService.getAll()
                .stream()
                .map(employee -> {
                    AbstractEmployeeDto employeeDto = EmployeeDtoFactory.getEmployeeDto(employee);
                    employeeDto.calculateCurrentPayment();
                    return employeeDto;
                }).collect(Collectors.toList());
    }
}