package com.example.EmployeePayrollApp.Controller;



import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.Model.Employee;
import com.example.EmployeePayrollApp.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // ✅ Fix: Optional handling
    }

    // Create new employee
    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) { // ✅ Accept EmployeeDTO
        return employeeService.createEmployee(employeeDTO);
    }

    // Update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) { // ✅ Accept EmployeeDTO
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    // Delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

