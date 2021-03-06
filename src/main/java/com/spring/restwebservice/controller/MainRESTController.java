package com.spring.restwebservice.controller;

import com.spring.restwebservice.dao.EmployeeDAO;
import com.spring.restwebservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRESTController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    // URL:
    // http://localhost:8080/employees
    // http://localhost:8080/employees.xml
    // http://localhost:8080/employees.json
    @RequestMapping(
            value = "/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    // http://localhost:8080/employee/{empNo}.xml
    // http://localhost:8080/employee/{empNo}.json
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/employee
    // http://localhost:8080/employee.xml
    // http://localhost:8080/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }

    // URL:
    // http://localhost:8080/employee
    // http://localhost:8080/employee.xml
    // http://localhost:8080/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        System.out.println("(Service Side) Deleting employee: " + empNo);
        employeeDAO.deleteEmployee(empNo);
    }

}
