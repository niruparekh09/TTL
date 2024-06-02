package com.app.service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.EmployeeDeleteResponse;
import com.app.dto.EmployeeInsert;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeUpdateResponse;
import com.app.model.Employee;
import com.app.model.Role;
import com.app.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponse getEmployee(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id " + id));
        return modelMapper.map(emp, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> getEmployeeList() {
        List<Employee> empList = employeeRepository.findAll();
        return empList
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeInsert emp) {
        Employee employee = modelMapper.map(emp, Employee.class);
        // Manually set the Role
        employee.setRole(Role.valueOf(emp.getRole().toUpperCase()));
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public EmployeeUpdateResponse updateEmployee(Long id, EmployeeInsert emp) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id " + id));
        // Update the existing employee's fields with the new values from the EmployeeInsert DTO
        existingEmployee.setFirstName(emp.getFirstName());
        existingEmployee.setLastName(emp.getLastName());
        existingEmployee.setCity(emp.getCity());
        existingEmployee.setSalary(emp.getSalary());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setPassword(emp.getPassword());
        existingEmployee.setDept(emp.getDept());

        return new EmployeeUpdateResponse("Data updated for " + id);
    }

    @Override
    public EmployeeDeleteResponse deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id " + id));
        employeeRepository.delete(existingEmployee);
        return new EmployeeDeleteResponse(id, "Employee successfully deleted.");
    }
}
