package com.myprograms.skillenza.springboot.employee;

import java.util.Date;

import static org.junit.Assert.assertTrue;

import com.myprograms.skillenza.springboot.employee.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeePortalApplicationTest {

    private static final String CONTEXT_PATH = "/employee-portal";
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private ResponseEntity<String> postData(Employee employee) {
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(CONTEXT_PATH + "/employees"), HttpMethod.POST, entity, String.class);

        return response;
    }

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Martel");
        employee.setLastName("Richard");
        employee.setGender("Male");
        employee.setDateOfBirth(new Date());
        employee.setDepartment("Application Products");
        return employee;
    }

    @Test
    public void testRegisterEmployee() {
        Employee employee = buildEmployee();
        ResponseEntity<String> response = postData(employee);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/employees/"));
    }

    @Test
    public void testGetEmployeeList() {
        Employee employee = buildEmployee();
        postData(employee);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(CONTEXT_PATH + "/employees"), HttpMethod.GET, entity, String.class);
        assertTrue(response.getBody() != null && response.getBody().length() > 0);
    }
}
