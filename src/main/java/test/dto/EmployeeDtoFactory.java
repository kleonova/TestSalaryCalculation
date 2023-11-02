package test.dto;

import test.EmployeeGroup;
import test.model.Employee;

public class EmployeeDtoFactory {
    public static AbstractEmployeeDto getEmployeeDto(Employee employee, double sumSalarySubordinates) {
        if (employee.getEmploymentGroup().equals(EmployeeGroup.MANAGER)) {
            return new ManagerDto(employee, sumSalarySubordinates);
        }

        if (employee.getEmploymentGroup().equals(EmployeeGroup.SALESMAN)) {
            return new SalesmanDto(employee, sumSalarySubordinates);
        }

        return new EmployeeDto(employee);
    }
}
