package com.app.service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmployeeServiceTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    private Employee testEmployee;

    @BeforeEach
    public void setup() {
        testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setCity("New York");
        testEmployee.setSalary(50000.0);
        testEmployee.setEmail("john.doe@example.com");
        testEmployee.setPassword("password");
        testEmployee.setDept("Engineering");

        testEmployee = employeeRepository.save(testEmployee);
    }

    @Test
    public void testGetEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    public void testGetEmployee() {
        Employee employee = employeeRepository.findById(testEmployee.getId()).orElse(null);
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
    }

    @Test
    public void testAddEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName("Jane");
        newEmployee.setLastName("Doe");
        newEmployee.setCity("San Francisco");
        newEmployee.setSalary(60000.0);
        newEmployee.setEmail("jane.doe@example.com");
        newEmployee.setPassword("password");
        newEmployee.setDept("Marketing");

        Employee savedEmployee = employeeRepository.save(newEmployee);
        assertNotNull(savedEmployee);
        assertNotNull(savedEmployee.getId());
        assertEquals("Jane", savedEmployee.getFirstName());
    }

    @Test
    public void testUpdateEmployee() {
        testEmployee.setCity("Los Angeles");
        Employee updatedEmployee = employeeRepository.save(testEmployee);
        assertEquals("Los Angeles", updatedEmployee.getCity());
    }

    @Test
    public void testDeleteEmployee() {
        employeeRepository.delete(testEmployee);
        Employee deletedEmployee = employeeRepository.findById(testEmployee.getId()).orElse(null);
        assertNull(deletedEmployee);
    }
}
