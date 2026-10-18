package com.employee.model;

import java.math.BigDecimal;

public class FullTimeEmployee extends Employee {
    private BigDecimal monthlySalary;

    public FullTimeEmployee(String employeeId, String employeeName, String email, String department, BigDecimal monthlySalary) {
        super(employeeId, employeeName, email, department);
        this.monthlySalary = monthlySalary;
    }

    public BigDecimal getSalary() {
        return monthlySalary;
    }

    @Override
    public BigDecimal calculateSalary() {
        return monthlySalary;
    }
}
