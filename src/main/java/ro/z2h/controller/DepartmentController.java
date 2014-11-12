package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by user on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all")
    public ArrayList<Department> getAllDepartments(){
        ArrayList<Department> myList = new ArrayList<Department>();
        Department myDep1 = new Department();
        Department myDep2 = new Department();
        myDep1.setDepartmentName("DEP1");
        myDep2.setDepartmentName("DEP2");
        myDep1.setId(12L);
        myDep2.setId(13L);

        myList.add(myDep1);
        myList.add(myDep2);

        return myList;
    }
}
