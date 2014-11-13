package ro.z2h.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeService;
import ro.z2h.service.EmployeeServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/11/2014.
 */
@MyController(urlPath = "/employee")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        EmployeeServiceImpl myService = new EmployeeServiceImpl();
        return myService.findAllEmployees();


    }
    @MyRequestMethod(urlPath = "/one")
      public Employee getOneEmployees(String idEmployee){


        EmployeeServiceImpl myService = new EmployeeServiceImpl();
        return myService.findOneEmployee(Long.parseLong(idEmployee));
    }
    @MyRequestMethod(urlPath = "/delete",methodType = "DELETE")
    public String deleteOneEmployee(String idEmployee){


        EmployeeServiceImpl myService = new EmployeeServiceImpl();
        return myService.deleteOneEmployee(Long.parseLong(idEmployee));
    }
    @MyRequestMethod(urlPath = "/add",methodType = "PUT")
    public String addOneEmployee(String myEmp){
        ObjectMapper myObj = new ObjectMapper();
        //myObj.getJsonFactory(myEmp);
        EmployeeServiceImpl myService = new EmployeeServiceImpl();
        return myService.addOneEmployee();
    }

}
