package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;
import ro.z2h.service.DepartmentServiceImpl;
import ro.z2h.service.EmployeeServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments(){

            DepartmentServiceImpl myService = new DepartmentServiceImpl();
            return myService.findAllDepartments();


    }
    @MyRequestMethod(urlPath = "/one")
    public Department getOneDepartment(String idDepartment){


        DepartmentServiceImpl myService = new DepartmentServiceImpl();
        return myService.findOneDepartment(Long.parseLong(idDepartment));
    }
}
