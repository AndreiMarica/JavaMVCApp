package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/12/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<Employee> findAllEmployees() {
           Connection con = DatabaseManager.getConnection("ZTH_13","passw0rd");
           EmployeeDao myDao = new EmployeeDao();
           ArrayList<Employee> myList = new ArrayList<Employee>();
        try {
            myList=myDao.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return myList;


    }

    @Override
    public Employee findOneEmployee(Long myId) {
        Connection con = DatabaseManager.getConnection("ZTH_13","passw0rd");
        EmployeeDao myDao = new EmployeeDao();
        Employee myEmp = new Employee();
        try {
             myEmp = myDao.getEmployeeById(con,myId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myEmp;
    }
}
