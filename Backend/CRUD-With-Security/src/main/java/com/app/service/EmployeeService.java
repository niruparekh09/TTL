package com.app.service;

import com.app.dto.EmployeeDeleteResponse;
import com.app.dto.EmployeeInsert;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeUpdateResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse getEmployee(Long id);

    List<EmployeeResponse> getEmployeeList();

    EmployeeResponse addEmployee(EmployeeInsert emp);

    EmployeeUpdateResponse updateEmployee(Long id, EmployeeInsert emp);

    EmployeeDeleteResponse deleteEmployee(Long id);

}
