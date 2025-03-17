package com.example.EmployeePayrollApp.Repository;


import com.example.EmployeePayrollApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}