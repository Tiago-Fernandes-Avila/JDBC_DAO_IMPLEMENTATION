package model.dao;

import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public interface DepartmentDAO {

    void insertDepartment(Department department);
    void deleteByIdDepartment(Integer id);
    void updateDepartment(Department department);
    Department findByIdDepartment(Integer id);
    List<Department> findAll();
}
