package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.EmployeeDao;
import test.model.Employee;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> getAllLead() {
        return employeeDao.findAll();
    }

    @Override
    public int getSumSalarySubordinates(int id) {
        List<Employee> subordinates = employeeDao.findEmployeeByEmployeeLead(id);
        int sum = subordinates.stream()
                .mapToInt(Employee::getSalary).sum();

        return sum;
    }
}
