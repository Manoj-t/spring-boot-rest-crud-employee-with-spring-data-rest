package com.manoj.springboot.cruddemo.service;

import com.manoj.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee employee);

    void deleteById(int id);

}
