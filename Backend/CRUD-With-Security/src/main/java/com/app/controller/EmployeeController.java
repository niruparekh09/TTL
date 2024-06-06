package com.app.controller;

import com.app.dto.EmployeeDeleteResponse;
import com.app.dto.EmployeeInsert;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeUpdateResponse;
import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeList() {
        List<EmployeeResponse> list = employeeService.getEmployeeList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployee(id);
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasRole('ROLE_DEV')")
    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid EmployeeInsert emp) {
        EmployeeResponse response = employeeService.addEmployee(emp);
        return ResponseEntity.status(201).body(response); // Used 201 Created for new resource creation
    }

  //  @PreAuthorize("hasRole('ROLE_DEV')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeUpdateResponse> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeInsert emp) {
        EmployeeUpdateResponse response = employeeService.updateEmployee(id, emp);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDeleteResponse> deleteEmployee(@PathVariable Long id) {
        EmployeeDeleteResponse response = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }
}
