package test.dto;

import test.model.Employee;

public class EmployeeDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.03;
    static final double MAX_BONUS = 0.3;

    public EmployeeDto(Employee employee) {
        super(employee, 0.0);
    }

    // Employee – это базовая ставка плюс 3% за каждый год работы,
    // но не более 30% суммарной надбавки.
    @Override
    public void calculateCurrentPayment() {
        double countBonusForWorkExperience = getCountWorkYears() > 0
                ? getSalary() * getCountWorkYears() * BONUS_FOR_EVERY_YEAR
                : 0.0;
        double maxBonusForWorkExperience = getSalary() * MAX_BONUS;
        double factBonusForWorkExperience = Math.min(countBonusForWorkExperience, maxBonusForWorkExperience);

        setCurrentPayment(getSalary() + factBonusForWorkExperience);
    }
}
