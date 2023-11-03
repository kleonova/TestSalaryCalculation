package test.service;

import test.dto.EmployeeDto;
import test.model.Employee;
import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);

    public Employee getById(int id);

    public List<EmployeeDto> getAll();

    public List<Employee> getAllLead();
}
