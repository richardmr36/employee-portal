package com.myprograms.skillenza.springboot.employee.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.myprograms.skillenza.springboot.employee.bean.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeePortalService {
    private static List<Employee> employeeList = new ArrayList<>();
    private static int idCounter = 0;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee registerEmployee(Employee employee) {
        employee.setId(++idCounter);
        employeeList.add(employee);
        Collections.sort(employeeList);
        return employee;
    }

    public Employee getEmployeeById(long id) {
        for (Employee employee : employeeList)
            if (employee.getId() == id)
                return employee;

        return null;
    }
}
