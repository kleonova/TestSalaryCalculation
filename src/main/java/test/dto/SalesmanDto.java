package test.dto;

import test.model.Employee;

import java.util.Date;

public class SalesmanDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.01;
    static final double MAX_BONUS = 0.35;
    static final double BONUS_SALARY_SUBORDINATES = 0.003;

    private double sumSalarySubordinates = 0.0;

    public SalesmanDto(Employee employee) {
        super(employee);
        sumSalarySubordinates = 1000.0;
    }

    // Salesman – это базовая ставка плюс 1% за каждый год работы в компании,
    // но не больше 35% суммарной надбавки за стаж работы.
    // Плюс 0.3% зарплаты всех подчиненных всех уровней.

    @Override
    public void calculateCurrentPayment() {
        int countYears = new Date().getYear() - getEmploymentDate().getYear();

        double countBonusForWorkExperience = countYears > 0
                ? getSalary() * countYears * BONUS_FOR_EVERY_YEAR
                : 0.0;
        double maxBonusForWorkExperience = getSalary() * MAX_BONUS;
        double factBonusForWorkExperience = Math.min(countBonusForWorkExperience, maxBonusForWorkExperience);

        setCurrentPayment(getSalary()
                + factBonusForWorkExperience
                + sumSalarySubordinates * BONUS_SALARY_SUBORDINATES
        );
    }
}
