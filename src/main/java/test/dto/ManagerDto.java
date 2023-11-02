package test.dto;

import test.model.Employee;

public class ManagerDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.05;
    static final double MAX_BONUS = 0.4;
    static final double BONUS_SALARY_SUBORDINATES = 0.005;

    public ManagerDto(Employee employee, double sumSalarySubordinates) {
        super(employee, sumSalarySubordinates);
    }

    // Manager – это базовая ставка плюс 5% за каждый год работы,
    // но не больше 40% суммарной надбавка за стаж работы.
    // Плюс 0.5% зарплаты всех починенных первого уровня.
    @Override
    public void calculateCurrentPayment() {
        double countBonusForWorkExperience = getCountWorkYears() > 0
                ? getSalary() * getCountWorkYears() * BONUS_FOR_EVERY_YEAR
                : 0.0;
        double maxBonusForWorkExperience = getSalary() * MAX_BONUS;
        double factBonusForWorkExperience = Math.min(countBonusForWorkExperience, maxBonusForWorkExperience);

        setCurrentPayment(getSalary()
                + factBonusForWorkExperience
                + getSumSalarySubordinates() * BONUS_SALARY_SUBORDINATES
        );
    }
}
