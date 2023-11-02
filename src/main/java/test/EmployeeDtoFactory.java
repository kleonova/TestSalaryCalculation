package test;

import test.dto.AbstractEmployeeDto;
import test.dto.EmployeeDto;
import test.dto.ManagerDto;
import test.dto.SalesmanDto;
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
