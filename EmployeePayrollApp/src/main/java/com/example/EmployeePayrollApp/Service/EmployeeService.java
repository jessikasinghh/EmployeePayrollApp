package com.example.EmployeePayrollApp.Service;


import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private Long idCounter = 1L; // Simulating database auto-increment

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Get employee by ID (Return Optional)
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }

    // Add a new employee
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO);
        employee.setId(idCounter++); // Simulating DB ID generation
        employeeList.add(employee);
        return employee;
    }

    // Update employee by ID
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
