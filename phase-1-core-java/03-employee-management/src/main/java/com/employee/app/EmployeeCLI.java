
package com.employee.app;

import com.employee.exception.DuplicateEmailException;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.*;
import com.employee.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class EmployeeCLI {

    private final EmployeeService employeeService;
    private final Scanner scanner;

    public EmployeeCLI(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    findEmployeeById();
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                    deleteEmployee();
                    break;
                case "5":
                    listAllEmployees();
                    break;
                case "6":
                    searchByDepartment();
                    break;
                case "7":
                    calculateSalary();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting Employee Management System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0-7.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Employee Management System =====");
        System.out.println("1. Add Employee");
        System.out.println("2. Find Employee by ID");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. List All Employees");
        System.out.println("6. Search by Department");
        System.out.println("7. Calculate Salary");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addEmployee() {
        try {
            System.out.println("\nSelect Employee Type:");
            System.out.println("1. Full-Time");
            System.out.println("2. Part-Time");
            System.out.println("3. Contract");
            System.out.println("4. Intern");
            System.out.print("Enter employee type: ");

            String typeInput = scanner.nextLine().trim();
            int type = Integer.parseInt(typeInput);

            if (type < 1 || type > 4) {
                System.out.println("Invalid employee type.");
                return;
            }

            String id = readText("Employee ID: ");
            String name = readText("Employee Name: ");
            String email = readText("Email: ");
            String department = readText("Department: ");

            Employee employee;

            switch (type) {
                case 1: {
                    BigDecimal salary = readMoney("Monthly Salary: ");
                    employee = new FullTimeEmployee(
                            id, name, email, department, salary);
                    break;
                }
                case 2: {
                    BigDecimal hourlyRate = readMoney("Hourly Rate: ");
                    int hoursWorked = readInteger("Hours Worked: ");
                    employee = new PartTimeEmployee(
                            id, name, email, department,
                            hourlyRate, hoursWorked);
                    break;
                }
                case 3: {
                    BigDecimal contractAmount =
                            readMoney("Contract Amount: ");
                    employee = new ContractEmployee(
                            id, name, email, department, contractAmount);
                    break;
                }
                case 4: {
                    BigDecimal stipend = readMoney("Monthly Stipend: ");
                    employee = new Intern(
                            id, name, email, department, stipend);
                    break;
                }
                default:
                    return;
            }

            employeeService.createEmployee(employee);
            System.out.println("Employee created successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EmployeeAlreadyExistsException |
                 DuplicateEmailException e) {
            System.out.println("Unable to create employee: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid employee details: " + e.getMessage());
        }
    }

    private void findEmployeeById() {
        try {
            String id = readText("Enter employee ID: ");
            Employee employee = employeeService.findEmployeeById(id);
            printEmployee(employee);
        } catch (EmployeeNotFoundException |
                 IllegalArgumentException e) {
            System.out.println("Unable to find employee: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            String id = readText("Enter employee ID to update: ");
            Employee existing = employeeService.findEmployeeById(id);

            System.out.println("Enter updated details:");

            String name = readText("Employee Name: ");
            String email = readText("Email: ");
            String department = readText("Department: ");

            Employee updated;

            if (existing instanceof FullTimeEmployee) {
                BigDecimal salary = readMoney("Monthly Salary: ");
                updated = new FullTimeEmployee(
                        id, name, email, department, salary);

            } else if (existing instanceof PartTimeEmployee) {
                BigDecimal rate = readMoney("Hourly Rate: ");
                int hours = readInteger("Hours Worked: ");
                updated = new PartTimeEmployee(
                        id, name, email, department, rate, hours);

            } else if (existing instanceof ContractEmployee) {
                BigDecimal amount = readMoney("Contract Amount: ");
                updated = new ContractEmployee(
                        id, name, email, department, amount);

            } else if (existing instanceof Intern) {
                BigDecimal stipend = readMoney("Monthly Stipend: ");
                updated = new Intern(
                        id, name, email, department, stipend);

            } else {
                System.out.println("Unsupported employee type.");
                return;
            }

            employeeService.updateEmployee(id, updated);
            System.out.println("Employee updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (EmployeeNotFoundException |
                 DuplicateEmailException |
                 IllegalArgumentException e) {
            System.out.println("Unable to update employee: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        try {
            String id = readText("Enter employee ID to delete: ");

            System.out.print("Confirm deletion? (y/n): ");
            String confirmation = scanner.nextLine().trim();

            if (!confirmation.equalsIgnoreCase("y")) {
                System.out.println("Deletion cancelled.");
                return;
            }

            employeeService.deleteEmployee(id);
            System.out.println("Employee deleted successfully!");

        } catch (EmployeeNotFoundException |
                 IllegalArgumentException e) {
            System.out.println("Unable to delete employee: " + e.getMessage());
        }
    }

    private void listAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            printEmployee(employee);
        }
    }

    private void searchByDepartment() {
        try {
            String department = readText("Enter department: ");
            List<Employee> employees =
                    employeeService.findAllEmployeesByDepartment(department);

            if (employees.isEmpty()) {
                System.out.println("No employees found in this department.");
                return;
            }

            for (Employee employee : employees) {
                printEmployee(employee);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid department: " + e.getMessage());
        }
    }

    private void calculateSalary() {
        try {
            String id = readText("Enter employee ID: ");
            BigDecimal salary = employeeService.calculateSalary(id);

            System.out.println("Calculated salary: " + salary);

        } catch (EmployeeNotFoundException |
                 IllegalArgumentException e) {
            System.out.println("Unable to calculate salary: " + e.getMessage());
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private BigDecimal readMoney(String prompt) {
        System.out.print(prompt);
        return new BigDecimal(scanner.nextLine().trim());
    }

    private int readInteger(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private void printEmployee(Employee employee) {
        System.out.println("\n------------------------------");
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getEmployeeName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Salary: " + employee.calculateSalary());
        System.out.println("Employee Type: "
                + employee.getClass().getSimpleName());
        System.out.println("------------------------------");
    }
}
