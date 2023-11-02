package test.dto;

import test.model.Employee;

import java.util.Date;

public class EmployeeDto extends AbstractEmployeeDto {
    static final double BONUS_FOR_EVERY_YEAR = 0.03;
    static final double MAX_BONUS = 0.3;

    public EmployeeDto(Employee employee) {
        super(employee);
    }

    // Employee – это базовая ставка плюс 3% за каждый год работы,
    // но не более 30% суммарной надбавки.

    @Override
    public void calculateCurrentPayment() {
        int countYears = new Date().getYear() - getEmploymentDate().getYear();

        double countBonusForWorkExperience = countYears > 0
                ? getSalary() * countYears * BONUS_FOR_EVERY_YEAR
                : 0.0;
        double maxBonusForWorkExperience = getSalary() * MAX_BONUS;
        double factBonusForWorkExperience = Math.min(countBonusForWorkExperience, maxBonusForWorkExperience);

        setCurrentPayment(getSalary() + factBonusForWorkExperience);
    }
}
