package com.employee.repository;

import com.employee.exception.DuplicateEmailException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;

import java.util.*;

public class EmployeeRepository {
    private Map<String, Employee> employeeMap = new HashMap<>();

    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
    }

    public Employee findEmployeeById(String employeeId) {
      return  employeeMap.get(employeeId);
    }

    public void removeEmployee(String employeeId) {
        if (!employeeMap.containsKey(employeeId)) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }

        employeeMap.remove(employeeId);
    }


    public void updateEmployee(String employeeId, Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (!employeeMap.containsKey(employeeId)) {
            throw new IllegalArgumentException("Employee does not exist");
        }

        if (!employee.getEmployeeId().equals(employeeId)) {
            throw new IllegalArgumentException("Employee ID cannot be changed");
        }

        employeeMap.put(employeeId, employee);
    }


    public List<Employee> findAllEmployees()
    {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee findEmployeeByEmail(String email)
    {
        for(Employee employee : employeeMap.values())
        {
            if(employee.getEmail().equals(email))
            {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> findEmployeeByName(String employeeName)
    {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeMap.values())
        {
            if(employee.getEmployeeName().equals(employeeName))
            {
                employees.add(employee);
            }
        }
        return employees;
    }

    public List<Employee> findEmployeeByDepartment(String department)
    {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeMap.values())
        {
            if(employee.getDepartment().equals(department))
            {
                employees.add(employee);
            }
        }
        return  employees;
    }



}
