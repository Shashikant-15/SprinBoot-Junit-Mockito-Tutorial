package com.knoldus.SpringBootRestapiJunitMockitoApplication.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.SpringBootRestapiJunitMockitoApplication.dao.Employee;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.repo.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeService#fetchEmployees()}
     */
    @Test
    void testFetchEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualFetchEmployeesResult = employeeService.fetchEmployees();
        assertSame(employeeList, actualFetchEmployeesResult);
        assertTrue(actualFetchEmployeesResult.isEmpty());
        verify(employeeRepository).findAll();
        assertSame(actualFetchEmployeesResult, employeeService.employeeList);
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById((Long) any());
        employeeService.deleteEmployee(123L);
        verify(employeeRepository).deleteById((Long) any());
        assertTrue(employeeService.employeeList.isEmpty());
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setDesignation("Agile");
        employee.setEmployeeId(163L);
        employee.setFirstName("E");
        employee.setLastName("Shaumya");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Employee> actualEmployeeById = employeeService.getEmployeeById(123L);
        assertSame(ofResult, actualEmployeeById);
        assertTrue(actualEmployeeById.isPresent());
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#saveEmployee(Employee)}
     */
    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setDesignation("Test-Automation");
        employee.setEmployeeId(121L);
        employee.setFirstName("Anshita");
        employee.setLastName("Mathur");
        when(employeeRepository.save((Employee) any())).thenReturn(employee);

        Employee employee1 = new Employee();
        employee1.setDesignation("Test-Automation");
        employee1.setEmployeeId(121L);
        employee1.setFirstName("Anshita");
        employee1.setLastName("Mathur");
        assertSame(employee1, employeeService.saveEmployee(employee1));
        verify(employeeRepository).save((Employee) any());
    }
}

