package test.dto;

import test.model.Employee;

public class SalesmanDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.01;
    static final double MAX_BONUS = 0.35;
    static final double BONUS_SALARY_SUBORDINATES = 0.003;

    public SalesmanDto(Employee employee, double sumSalarySubordinates) {
        super(employee, sumSalarySubordinates);
    }

    // Salesman – это базовая ставка плюс 1% за каждый год работы в компании,
    // но не больше 35% суммарной надбавки за стаж работы.
    // Плюс 0.3% зарплаты всех подчиненных всех уровней.
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
