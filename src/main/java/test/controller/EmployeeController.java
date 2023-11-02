package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.dto.EmployeeDto;
import test.dto.EmployeeDtoFactory;
import test.dto.AbstractEmployeeDto;
import test.model.Employee;
import test.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    Comparator<AbstractEmployeeDto> compareBySalary = new Comparator<AbstractEmployeeDto>() {
        @Override
        public int compare(AbstractEmployeeDto o1, AbstractEmployeeDto o2) {
            return Double.compare(o1.getCurrentPayment(), o2.getCurrentPayment());
        }
    };


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
                    double sumSalarySubordinates = employeeService.getSumSalarySubordinates(employee.getId());
                    AbstractEmployeeDto employeeDto = EmployeeDtoFactory.getEmployeeDto(employee, sumSalarySubordinates);
                    employeeDto.calculateCurrentPayment();

                    return employeeDto;
                })
                .sorted(compareBySalary.reversed())
                .collect(Collectors.toList());
    }
}
