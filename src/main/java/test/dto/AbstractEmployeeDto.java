package test.dto;

import lombok.Data;
import test.EmployeeGroup;
import test.model.Employee;

import java.util.Date;

@Data
public class AbstractEmployeeDto {
    private int id;
    private String name;
    private Date employmentDate;
    private int salary;
    private EmployeeGroup employeeGroup;
    private double currentPayment;

    public AbstractEmployeeDto(Employee employee) {
        id = employee.getId();
        name = String.format("%s %s %s", employee.getLastName(),
                employee.getFirstName(),
                employee.getSecondName());
        employmentDate = employee.getEmploymentDate();
        employeeGroup = employee.getEmploymentGroup();
        salary = employee.getSalary();
        currentPayment = 0.0;
    }

    public void calculateCurrentPayment() {
        setCurrentPayment(getSalary());
    }
}
