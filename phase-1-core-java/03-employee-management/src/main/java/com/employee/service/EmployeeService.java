package com.employee.service;

import com.employee.exception.DuplicateEmailException;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void createEmployee(Employee employee) {

        // 1. Validate employee object
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        // 2. Validate employee ID
        if (employee.getEmployeeId() == null ||
                employee.getEmployeeId().isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be blank");
        }

        // 3. Check for duplicate employee ID
        if (employeeRepository.findEmployeeById(employee.getEmployeeId()) != null) {
            throw new EmployeeAlreadyExistsException("Employee already exists");
        }

        // 4. Validate employee name
        if (employee.getEmployeeName() == null ||
                employee.getEmployeeName().isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be blank");
        }

        // 5. Validate email
        if (employee.getEmail() == null ||
                employee.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        // 6. Check for duplicate email
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()) != null) {
            throw new DuplicateEmailException("Email already exists");
        }

        // 7. Validate department
        if (employee.getDepartment() == null ||
                employee.getDepartment().isBlank()) {
            throw new IllegalArgumentException("Department cannot be blank");
        }

        // 8. Save only after all validations pass
        employeeRepository.addEmployee(employee);
    }


    public Employee findEmployeeById(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be blank"
            );
        }
        Employee existingEmployee =
                employeeRepository.findEmployeeById(employeeId);

        if (existingEmployee == null) {
            throw new EmployeeNotFoundException(
                    "Employee does not exist"
            );
        }
        return existingEmployee;

    }


    public void updateEmployee(String employeeId, Employee employee) {

        // 1. Validate employee object
        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee cannot be null and cannot be updated"
            );
        }

        // 2. Validate employee ID
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be blank"
            );
        }

        // 3. Check whether employee exists
        Employee existingEmployee =
                employeeRepository.findEmployeeById(employeeId);

        if (existingEmployee == null) {
            throw new EmployeeNotFoundException(
                    "Employee does not exist"
            );
        }

        // 4. Prevent changing the employee ID
        if (!employee.getEmployeeId().equals(employeeId)) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be changed"
            );
        }

        // 5. Validate email
        if (employee.getEmail() == null ||
                employee.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "Email cannot be blank"
            );
        }

        // 6. Check for duplicate email
        Employee employeeWithEmail =
                employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (employeeWithEmail != null &&
                !employeeWithEmail.getEmployeeId().equals(employeeId)) {
            throw new DuplicateEmailException(
                    "Email already belongs to another employee"
            );
        }

        // 7. Update only after all validations pass
        employeeRepository.updateEmployee(employeeId, employee);
    }


    public void deleteEmployee(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be null or blank"
            );
        }

        Employee existingEmployee =
                employeeRepository.findEmployeeById(employeeId);
        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }


        employeeRepository.removeEmployee(employeeId);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    public List<Employee> findAllEmployeesByDepartment(String department) {
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(
                    "Department cannot be null or blank"
            );
        }

        return employeeRepository.findEmployeeByDepartment(department);
    }
    public BigDecimal calculateSalary(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be null or blank"
            );
        }

        Employee existingEmployee =
                employeeRepository.findEmployeeById(employeeId);
        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }
        return existingEmployee.calculateSalary();

    }
}
