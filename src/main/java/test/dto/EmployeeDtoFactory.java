package test.dto;

import test.EmployeeGroup;
import test.model.Employee;

public class EmployeeDtoFactory {
    public static AbstractEmployeeDto getEmployeeDto(Employee employee) {
        if (employee.getEmploymentGroup().equals(EmployeeGroup.MANAGER)) {
            return new ManagerDto(employee);
        }

        if (employee.getEmploymentGroup().equals(EmployeeGroup.SALESMAN)) {
            return new SalesmanDto(employee);
        }

        return new EmployeeDto(employee);
    }
}
