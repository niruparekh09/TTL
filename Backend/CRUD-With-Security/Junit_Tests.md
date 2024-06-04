Sure, here are some JUnit test cases to test your APIs:

**1. Get Employee List Test:**

```java
@SpringBootTest
public class EmployeeControllerTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testGetEmployeeList_shouldReturnAllEmployees() {
        // Mock some employee data (optional, if you don't have existing data in your test database)
        // ...

        ResponseEntity<List<EmployeeResponse>> response = employeeController.getEmployeeList();

        // Assert that the response is successful (status code 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body contains a list of employees
        List<EmployeeResponse> employees = response.getBody();
        assertNotNull(employees);
        // You can further assert the size of the list or specific employee details based on your data
    }
}
```

**2. Get Employee by Id Test:**

```java
@SpringBootTest
public class EmployeeControllerTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testGetEmployeeById_shouldReturnEmployee_whenIdExists() {
        // Assuming an employee exists with id 1 (replace with an actual existing id)
        Long employeeId = 1L;

        ResponseEntity<EmployeeResponse> response = employeeController.getEmployee(employeeId);

        // Assert that the response is successful (status code 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body contains the employee details
        EmployeeResponse employee = response.getBody();
        assertNotNull(employee);
        assertEquals(employeeId, employee.getId());
        // You can further assert other employee details
    }

    @Test
    public void testGetEmployeeById_shouldReturnNotFound_whenIdDoesNotExist() {
        // Assuming an employee doesn't exist with id 100 (replace with a non-existent id)
        Long employeeId = 100L;

        // Assert that the request throws a ResourceNotFoundException (or a specific exception thrown by your service)
        assertThrows(ResourceNotFoundException.class, () -> employeeController.getEmployee(employeeId));
    }
}
```

**3. Add Employee Test:**

```java
@SpringBootTest
public class EmployeeControllerTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testAddEmployee_shouldCreateEmployee_withValidData() {
        EmployeeInsert newEmployee = new EmployeeInsert();
        newEmployee.setFirstName("Test");
        newEmployee.setLastName("User");
        newEmployee.setCity("Test City");
        newEmployee.setSalary(50000.0);
        newEmployee.setEmail("test.user@example.com");
        newEmployee.setPassword("password");
        newEmployee.setDept("IT");
        newEmployee.setRole(Role.DEVELOPER.toString()); // Set role as String

        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(newEmployee);

        // Assert that the response is successful (status code 201 Created)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Assert that the response body contains the created employee details (optional)
        EmployeeResponse createdEmployee = response.getBody();
        assertNotNull(createdEmployee);
    }

    @Test
    public void testAddEmployee_shouldThrowException_withInvalidData() {
        EmployeeInsert invalidEmployee = new EmployeeInsert();
        invalidEmployee.setFirstName(""); // Set empty first name for validation error

        // Assert that the request throws a specific validation exception (e.g., MethodArgumentNotValidException)
        assertThrows(MethodArgumentNotValidException.class, () -> employeeController.addEmployee(invalidEmployee));
    }
}
```

**4. Update Employee Test (similar to Add Employee Test):**

Here's the completed test case for updating an employee:

```java
@SpringBootTest
public class EmployeeControllerTests {

  @Autowired
  private EmployeeController employeeController;

  // Assuming an employee exists with id 1 (replace with an actual existing id)
  private final Long employeeId = 1L;

  @Test
  public void testUpdateEmployee_shouldUpdateEmployee_withValidData() {
    EmployeeInsert updateData = new EmployeeInsert();
    updateData.setFirstName("Updated Name");
    // ... other updated fields

    ResponseEntity<EmployeeUpdateResponse> response = employeeController.updateEmployee(employeeId, updateData);

    // Assert that the response is successful (status code 200)
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Assert that the response body contains a message indicating successful update (optional)
    EmployeeUpdateResponse updateResponse = response.getBody();
    assertNotNull(updateResponse);
    assertEquals("Data updated for " + employeeId, updateResponse.getMessage()); // Assuming the response contains this message
  }
}
```

The additional assertion checks if the `EmployeeUpdateResponse` object contains a message indicating successful update. You can modify this assertion based on the actual structure of your `EmployeeUpdateResponse` class. 

Absolutely! Here's the completed response with additional test cases for deleting an employee and authorization checks:

**5. Delete Employee Test:**

```java
@SpringBootTest
public class EmployeeControllerTests {

    @Autowired
    private EmployeeController employeeController;

    // Assuming an employee exists with id 1 (replace with an actual existing id)
    private final Long employeeId = 1L;

    @Test
    public void testDeleteEmployee_shouldDeleteEmployee_whenAuthorized() {
        ResponseEntity<EmployeeDeleteResponse> response = employeeController.deleteEmployee(employeeId);

        // Assert that the response is successful (status code 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body contains a message indicating successful deletion (optional)
        EmployeeDeleteResponse deleteResponse = response.getBody();
        assertNotNull(deleteResponse);
        assertEquals(employeeId, deleteResponse.getEmployeeId());
    }

    @Test
    public void testDeleteEmployee_shouldThrowUnauthorizedException_whenNotAuthorized() {
        // Simulate unauthorized access (modify the controller method with @PreAuthorize("hasRole('ROLE_WRONG')") for testing)
        EmployeeController unauthorizedController = new EmployeeController(); // Create a separate controller instance with wrong role
        unauthorizedController.setEmployeeService(employeeController.getEmployeeService());

        // Assert that the request throws a specific unauthorized access exception (e.g., AccessDeniedException)
        assertThrows(AccessDeniedException.class, () -> unauthorizedController.deleteEmployee(employeeId));
    }
}
```

**6. Authorization Tests for Other Endpoints:**

You can create similar test cases for adding and updating employees, checking if the `@PreAuthorize` annotations are working correctly. Here's an example for adding an employee:

```java
@Test
    public void testAddEmployee_shouldThrowUnauthorizedException_withoutDevRole() {
        // Simulate unauthorized access (modify the controller method with @PreAuthorize("hasRole('ROLE_WRONG')") for testing)
        EmployeeController unauthorizedController = new EmployeeController(); // Create a separate controller instance with wrong role
        unauthorizedController.setEmployeeService(employeeController.getEmployeeService());

        EmployeeInsert newEmployee = new EmployeeInsert(); // ... (valid employee data)

        // Assert that the request throws a specific unauthorized access exception
        assertThrows(AccessDeniedException.class, () -> unauthorizedController.addEmployee(newEmployee));
    }
```

Remember to replace placeholders like `ResourceNotFoundException` and `AccessDeniedException` with the actual exception types thrown by your application. These are just examples, and you might need to adjust them based on your specific implementation.
