package test.dto;

import lombok.Data;
import test.EmployeeGroup;
import test.model.Employee;

import java.util.Date;

@Data
public class EmployeeDto {
    private int id;
    private String name;
    private Date employmentDate;
    private int salary;
    private EmployeeGroup employeeGroup;
    private int employeeLead;
    private double currentPayment; // итоговая выплата

    public EmployeeDto(Employee employee) {
        id = employee.getId();
        name = String.format("%s %s %s", employee.getLastName(), employee.getFirstName(), employee.getSecondName());
        employmentDate = employee.getEmploymentDate();
        salary = employee.getSalary();
        employeeGroup = employee.getEmploymentGroup();
        employeeLead = employee.getEmployeeLead();
    }
}
