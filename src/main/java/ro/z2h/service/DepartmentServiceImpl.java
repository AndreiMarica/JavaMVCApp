package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.domain.Department;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/13/2014.
 */
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> findAllDepartments() {
        Connection con = DatabaseManager.getConnection("ZTH_13", "passw0rd");
        DepartmentDao myDao = new DepartmentDao();
        ArrayList<Department> myList = new ArrayList<Department>();
        try {
            myList=myDao.getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    @Override
    public Department findOneDepartment(Long myId) {
        Connection con = DatabaseManager.getConnection("ZTH_13","passw0rd");
        DepartmentDao myDao = new DepartmentDao();
        Department myEmp = new Department();
        try {
            myEmp = myDao.getDepartmentById(con,myId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myEmp;
    }
}
