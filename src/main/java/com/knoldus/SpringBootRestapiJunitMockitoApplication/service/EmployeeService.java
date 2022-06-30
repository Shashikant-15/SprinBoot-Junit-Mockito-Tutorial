package com.knoldus.SpringBootRestapiJunitMockitoApplication.service;

import com.knoldus.SpringBootRestapiJunitMockitoApplication.dao.Employee;
import com.knoldus.SpringBootRestapiJunitMockitoApplication.repo.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    /**
     *   EmployeeRepository
     */
    @Autowired
    private static EmployeeRepository employeeRepository;

    /**
     *   employee list  .
     */
    List<Employee> employeeList = new ArrayList<>();

    /**
     *   Default Constructor  .
     *
     * @param employeeRepository
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     *   Get all employees from the database  .
     *
     * @return list of employees  .
     */
    public List<Employee> fetchEmployees() {
        employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList;
    }

    /**
     *  This method delete employee from database  .
     *
     * @param id the Id  .
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * This method fetch employee from database by Id  .
     *
     * @param id the Id  .
     * @return employee  .
     */
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    /**
     *   This method saves employees in database  .
     *
     * @param employee the Employee  .
     * @return Employee  .
     */
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

}
