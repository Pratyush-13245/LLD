package com.employee.model;

import java.math.BigDecimal;

public class PartTimeEmployee  extends Employee{

    private BigDecimal hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String employeeId, String employeeName, String email, String department, BigDecimal hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, email, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public BigDecimal calculateSalary() {
        return hourlyRate.multiply(new BigDecimal(hoursWorked));
    }
}
