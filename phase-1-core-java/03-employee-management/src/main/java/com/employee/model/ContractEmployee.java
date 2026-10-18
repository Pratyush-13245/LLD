package com.employee.model;

import java.math.BigDecimal;

public class ContractEmployee extends Employee {

    private BigDecimal contractAmount;

    public  ContractEmployee(String employeeId, String employeeName, String email, String department, BigDecimal contractAmount)
    {
        super(employeeId, employeeName, email, department);
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractAmount()
    {
        return contractAmount;
    }

    @Override
    public BigDecimal calculateSalary() {
        return contractAmount;
    }
}
