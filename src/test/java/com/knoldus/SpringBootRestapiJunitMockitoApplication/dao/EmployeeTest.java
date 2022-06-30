package com.knoldus.SpringBootRestapiJunitMockitoApplication.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class EmployeeTest {
    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals() {
        Employee employee = new Employee();
        employee.setDesignation("HR");
        employee.setEmployeeId(123L);
        employee.setFirstName("Deepak");
        employee.setLastName("Kumar");
        assertNotEquals(employee, null);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals2() {
        Employee employee = new Employee();
        employee.setDesignation("Finance");
        employee.setEmployeeId(113L);
        employee.setFirstName("Ankur");
        employee.setLastName("Dhaga");
        assertNotEquals(employee, "Different type to Employee");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        Employee employee = new Employee();
        employee.setDesignation("Consultant");
        employee.setEmployeeId(103L);
        employee.setFirstName("Shashikant");
        employee.setLastName("Tanti");
        assertEquals(employee, employee);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        Employee employee = new Employee();
        employee.setDesignation("Management");
        employee.setEmployeeId(133L);
        employee.setFirstName("Harsh");
        employee.setLastName("Kumar");

        Employee employee1 = new Employee();
        employee1.setDesignation("Management");
        employee1.setEmployeeId(133L);
        employee1.setFirstName("Harsh");
        employee1.setLastName("Kumar");
        assertEquals(employee, employee1);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee1.hashCode());
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals5() {
        Employee employee = new Employee();
        employee.setDesignation("IT");
        employee.setEmployeeId(1L);
        employee.setFirstName("Gaurav");
        employee.setLastName("Goel");

        Employee employee1 = new Employee();
        employee1.setDesignation("IT");
        employee1.setEmployeeId(123L);
        employee1.setFirstName("Gaurav");
        employee1.setLastName("Goel");
        assertNotEquals(employee, employee1);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals6() {
        Employee employee = new Employee();
        employee.setDesignation("Support Staff");
        employee.setEmployeeId(null);
        employee.setFirstName("Jamesh");
        employee.setLastName("Doe");

        Employee employee1 = new Employee();
        employee1.setDesignation("Support Staff");
        employee1.setEmployeeId(143L);
        employee1.setFirstName("Jamesh");
        employee1.setLastName("Doe");
        assertNotEquals(employee, employee1);
    }
}

