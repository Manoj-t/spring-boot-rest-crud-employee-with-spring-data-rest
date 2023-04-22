package com.manoj.springboot.cruddemo.rest;

import com.manoj.springboot.cruddemo.entity.Employee;
import com.manoj.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class EmployeeRestController {

    // inject EmployeeService
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // expose "/employees/{employeeId}" and return the employee of that id
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable(name = "employeeId") int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        if(null == theEmployee){
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        return theEmployee;
    }

    // expose POST "/employees" - create the Employee in the database
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        /*
            also just in case if they pass an id in JSON ... set id to 0
            this is to force a save a new item ... instead of update
         */

            theEmployee.setId(0);

            return employeeService.save(theEmployee);
    }


    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        return employeeService.save(theEmployee);
    }


    // add mapping for DELETE "/employee/{employeeId}"
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable(name = "employeeId") int employeeId){

        Employee employee = employeeService.findById(employeeId);

        if(null == employee){
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employeeId - " + employeeId;
    }



}
