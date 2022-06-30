package com.knoldus.SpringBootRestapiJunitMockitoApplication.controller;

import com.knoldus.SpringBootRestapiJunitMockitoApplication.dao.Employee;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.repo.EmployeeRepository;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * These methods add employee to database  .
     *
     * @param employee the employee  .
     */
    @PostMapping("/employee/add")
    public Employee createEmployeeRecord(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * This method will fetch all the employee from database  .
     *
     * @return list of employee  .
     */
    @GetMapping("/employee/get")
    public List<Employee> getAllEmployeeRecord() {
        return employeeRepository.findAll();
    }

    /**
     * this method fetch employee with the help of its id  .
     *
     * @param employeeId the Id  .
     * @return employee  .
     */
    @GetMapping("/employee/get/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {

        return employeeRepository.findById(employeeId).get();

    }

    /**
     *   This method update the Employee present in Database  .
     *
     * @param employee updated employee  .
     * @return employee  .
     */
    @PutMapping("/employee/update")
    public Employee updateEmployee(@RequestBody Employee employee) {

        if(employee == null || employee.getEmployeeId() == null) {
            throw new NullPointerException("Book record or Id must not be Null");
        }
        Optional<Employee> optionalBook = employeeRepository.findById(employee.getEmployeeId());
        if(!optionalBook.isPresent()) {
            throw new NullPointerException("Employee id" + employee.getEmployeeId() + "Does not the exist");
        }

        Employee existingEmployeeRecord = optionalBook.get();
        existingEmployeeRecord.setFirstName(employee.getFirstName());
        existingEmployeeRecord.setLastName(employee.getLastName());
        existingEmployeeRecord.setDesignation(employee.getDesignation());
        return employeeRepository.save(existingEmployeeRecord);

    }

    /**
     *   This method will delete Employee from the Database  .
     *
     * @param id the Id  .
     */
    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
