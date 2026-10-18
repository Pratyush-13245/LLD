package com.employee.model;

import java.math.BigDecimal;

public abstract class Employee {
    private final String employeeId;
    private final String employeeName;
    private String email;
    private String department;

    public Employee(String employeeId, String employeeName, String email, String department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.department = department;
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public abstract BigDecimal calculateSalary();

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
