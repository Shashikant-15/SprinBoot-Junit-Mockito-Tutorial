package com.knoldus.SpringBootRestapiJunitMockitoApplication.repo;


import com.knoldus.SpringBootRestapiJunitMockitoApplication.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}