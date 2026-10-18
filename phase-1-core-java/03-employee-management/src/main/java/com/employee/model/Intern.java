package com.employee.model;

import java.math.BigDecimal;

public class Intern extends Employee{

    private BigDecimal monthlyStipend;

    public Intern(String employeeId, String employeeName, String email, String department, BigDecimal monthlyStipend)
    {
        super(employeeId, employeeName, email, department);
        this.monthlyStipend = monthlyStipend;
    }

    public BigDecimal getMonthlyStipend()
    {
        return monthlyStipend;
    }
    @Override
    public BigDecimal calculateSalary() {
        return monthlyStipend;
    }
}
