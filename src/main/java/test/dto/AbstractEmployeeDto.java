package test.dto;

import lombok.Data;
import test.EmployeeGroup;
import test.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Data
public class AbstractEmployeeDto {
    private int id;
    private String name;
    private Date employmentDate;
    private int salary;
    private EmployeeGroup employeeGroup;
    private double currentPayment;
    private int countWorkYears;
    private double sumSalarySubordinates;

    public AbstractEmployeeDto(Employee employee, double sumSalarySubordinates) {
        id = employee.getId();
        name = String.format("%s %s %s", employee.getLastName(),
                employee.getFirstName(),
                employee.getSecondName());
        employmentDate = employee.getEmploymentDate();
        employeeGroup = employee.getEmploymentGroup();
        salary = employee.getSalary();
        countWorkYears = Period.between(employmentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
        currentPayment = 0.0;
        this.sumSalarySubordinates = sumSalarySubordinates;
    }

    public void calculateCurrentPayment() {
        setCurrentPayment(getSalary());
    }
}
