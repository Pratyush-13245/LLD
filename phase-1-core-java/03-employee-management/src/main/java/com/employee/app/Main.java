package com.employee.app;

import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

public class Main {
    public static void main(String[] args) {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        com.employee.app.EmployeeCLI cli = new com.employee.app.EmployeeCLI(service);
        cli.start();
    }
}