package test.dto;

import test.model.Employee;

import java.util.Date;

public class ManagerDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.05;
    static final double MAX_BONUS = 0.4;
    static final double BONUS_SALARY_SUBORDINATES = 0.005;

    private double sumSalarySubordinates = 0.0;

    // Manager – это базовая ставка плюс 5% за каждый год работы,
    // но не больше 40% суммарной надбавка за стаж работы.
    // Плюс 0.5% зарплаты всех починенных первого уровня.
    public ManagerDto(Employee employee) {
        super(employee);
        sumSalarySubordinates = 1000.0;
    }

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
