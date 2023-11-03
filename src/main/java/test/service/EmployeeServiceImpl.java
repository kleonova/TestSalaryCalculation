package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.EmployeeGroup;
import test.dao.EmployeeDao;
import test.dto.EmployeeDto;
import test.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Comparator<EmployeeDto> compareBySalary = (o1, o2) -> Double.compare(o1.getCurrentPayment(), o2.getCurrentPayment());

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
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeDao.findAll();
        List<EmployeeDto> employeeDtoList = employees.stream()
                .map(employee -> {
                    EmployeeDto employeeDto = new EmployeeDto(employee);
                    employeeDto.setCurrentPayment(getCurrentPayment(employee));
                    return employeeDto;
                })
                .sorted(compareBySalary.reversed())
                .toList();

        return employeeDtoList;
    }

    @Override
    public List<Employee> getAllLead() {
        EmployeeGroup[] leadGroups = {EmployeeGroup.SALESMAN, EmployeeGroup.MANAGER};
        return employeeDao.findAllByGroups(Arrays.asList(leadGroups));
    }

    private double getCurrentPayment(Employee employee) {
        if (employee.getEmploymentGroup().equals(EmployeeGroup.MANAGER)) {
            return getCurrentPaymentManager(employee);
        }

        if (employee.getEmploymentGroup().equals(EmployeeGroup.SALESMAN)) {
            return getCurrentPaymentSalesman(employee);
        }

        return getCurrentPaymentEmployee(employee);
    }

    private double getCurrentPaymentEmployee(Employee employee) {
        final double BONUS_FOR_EVERY_YEAR = 0.03;
        final double MAX_BONUS = 0.3;

        double factBonusForWorkExperience = getBonusForExperience(
                employee.getSalary(),
                employee.getEmploymentDate(),
                BONUS_FOR_EVERY_YEAR, MAX_BONUS
        );

        return employee.getSalary() + factBonusForWorkExperience;
    }

    private double getCurrentPaymentManager(Employee employee) {
        final double BONUS_FOR_EVERY_YEAR = 0.05;
        final double MAX_BONUS = 0.4;
        final double BONUS_SALARY_SUBORDINATES = 0.005;

        double factBonusForWorkExperience = getBonusForExperience(
                employee.getSalary(),
                employee.getEmploymentDate(),
                BONUS_FOR_EVERY_YEAR, MAX_BONUS
        );

        double bonusSalaryFirstLevelSubordinates = getSumSalaryFirstLevelSubordinates(employee.getId()) * BONUS_SALARY_SUBORDINATES;

        return employee.getSalary() + factBonusForWorkExperience + bonusSalaryFirstLevelSubordinates;
    }

    private double getCurrentPaymentSalesman(Employee employee) {
        final double BONUS_FOR_EVERY_YEAR = 0.01;
        final double MAX_BONUS = 0.35;
        final double BONUS_SALARY_SUBORDINATES = 0.003;

        double factBonusForWorkExperience = getBonusForExperience(
                employee.getSalary(),
                employee.getEmploymentDate(),
                BONUS_FOR_EVERY_YEAR, MAX_BONUS
        );

        double bonusSalaryAllSubordinates = getSumSalaryAllSubordinates(employee.getId()) * BONUS_SALARY_SUBORDINATES;

        return employee.getSalary() + factBonusForWorkExperience + bonusSalaryAllSubordinates;
    }

    private void getAllSubordinates(int leadId, List<Employee> employees, List<Employee> allSub) {
        List<Employee> subordinates = employees.stream().filter(employee -> employee.getEmployeeLead() == leadId).toList();

        if (subordinates.size() == 0) return;

        allSub.addAll(subordinates);

        subordinates.forEach(employee -> {
            getAllSubordinates(employee.getId(), employees, allSub);
        });
    }

    private double getSumSalaryFirstLevelSubordinates(int id) {
        List<Employee> subordinates = employeeDao.findEmployeeByEmployeeLead(id);

        return subordinates.stream().mapToInt(Employee::getSalary).sum();
    }

    private int getSumSalaryAllSubordinates(int leadId) {
        List<Employee> subordinates = new ArrayList<>();
        List<Employee> employees = employeeDao.findAll();
        getAllSubordinates(leadId, employees, subordinates);

        return subordinates.stream().mapToInt(Employee::getSalary).sum();
    }

    private double getBonusForExperience(int salary, Date startWork, double bonusForEveryYear, double maxBonus) {
        int countWorkYears = getCountWorkYears(startWork);

        double countBonusForWorkExperience = countWorkYears > 0
                ? salary * countWorkYears * bonusForEveryYear
                : 0.0;
        double maxBonusForWorkExperience = salary * maxBonus;

        return Math.min(countBonusForWorkExperience, maxBonusForWorkExperience);
    }

    private int getCountWorkYears(Date startWork) {
        return Period
                .between(startWork.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())
                .getYears();
    }
}
