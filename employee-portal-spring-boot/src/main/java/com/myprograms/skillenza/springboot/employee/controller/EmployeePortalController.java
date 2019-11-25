package com.myprograms.skillenza.springboot.employee.controller;

import java.net.URI;
import java.util.List;

import com.myprograms.skillenza.springboot.employee.bean.Employee;
import com.myprograms.skillenza.springboot.employee.service.EmployeePortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeePortalController {

    @Autowired
    private EmployeePortalService employeePortalService;

    @GetMapping(path = "/employees")
    public List<Employee> getEmployeeList() {
        return employeePortalService.getEmployeeList();
    }

    @GetMapping(path = "/employees/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeePortalService.getEmployeeById(id);
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<Void> registerEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeePortalService.registerEmployee(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
