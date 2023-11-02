package test.service;

import test.model.Employee;
import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);

    public Employee getById(int id);

    public List<Employee> getAll();

    public List<Employee> getAllLead();

    public int getSumSalarySubordinates(int id);
}
