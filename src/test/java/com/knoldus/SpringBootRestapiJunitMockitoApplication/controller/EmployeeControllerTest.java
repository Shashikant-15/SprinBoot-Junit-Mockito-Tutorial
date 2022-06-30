package com.knoldus.SpringBootRestapiJunitMockitoApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.dao.Employee;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.repo.EmployeeRepository;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.service.EmployeeService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {

    //******************************************************//
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeController#createEmployeeRecord(Employee)}
     */
    @Test
    void testCreateEmployeeRecord() throws Exception {
        Employee employee = new Employee();
        employee.setDesignation("HR");
        employee.setEmployeeId(103L);
        employee.setFirstName("Deepak");
        employee.setLastName("Kumar");
        when(employeeRepository.save((Employee) any())).thenReturn(employee);

        Employee employee1 = new Employee();
        employee1.setDesignation("HR");
        employee1.setEmployeeId(103L);
        employee1.setFirstName("Deepak");
        employee1.setLastName("Kumar");
        String content = (new ObjectMapper()).writeValueAsString(employee1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"employeeId\":103,\"firstName\":\"Deepak\",\"lastName\":\"Kumar\",\"designation\":\"HR\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployeeById(Long)}
     */
    @Test
    void testDeleteEmployeeById() throws Exception {
        doNothing().when(employeeService).deleteEmployee((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link EmployeeController#getAllEmployeeRecord()}
     */
    @Test
    void testGetAllEmployeeRecord() throws Exception {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/get");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setDesignation("Finance");
        employee.setEmployeeId(121L);
        employee.setFirstName("Arunesh");
        employee.setLastName("Jain");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/get/{employeeId}", 121L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"employeeId\":121,\"firstName\":\"Arunesh\",\"lastName\":\"Jain\",\"designation\":\"Finance\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setDesignation("Management");
        employee.setEmployeeId(113L);
        employee.setFirstName("Aashif");
        employee.setLastName("Ali");
        Optional<Employee> ofResult = Optional.of(employee);

        Employee employee1 = new Employee();
        employee1.setDesignation("Management");
        employee1.setEmployeeId(113L);
        employee1.setFirstName("Aashif");
        employee1.setLastName("Ali");
        when(employeeRepository.save((Employee) any())).thenReturn(employee1);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee employee2 = new Employee();
        employee2.setDesignation("Management");
        employee2.setEmployeeId(113L);
        employee2.setFirstName("Aashif");
        employee2.setLastName("Ali");
        String content = (new ObjectMapper()).writeValueAsString(employee2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employee/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"employeeId\":113,\"firstName\":\"Aashif\",\"lastName\":\"Ali\",\"designation\":\"Management\"}"));
    }
}

