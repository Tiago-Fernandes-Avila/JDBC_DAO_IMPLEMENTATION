package model.dao.impl;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DepartmentDaoJDBC implements DepartmentDAO {
private Connection conn;
   public DepartmentDaoJDBC(Connection conn) {
       this.conn = conn;
   }
    @Override
    public void insertDepartment(Department department) {
        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO department (Id, Name) VALUES (?, ?)")){
            ps.setInt(1, department.getId());
            ps.setString(2, department.getName());

           int rowsAffected = ps.executeUpdate();
           if(rowsAffected > 0){
               System.out.println("Success Department Has Been Inserted!");
           }
           else {
                throw new RuntimeException("Insert has Failed");
           }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    @Override
    public void deleteByIdDepartment(Integer id) {
       PreparedStatement ps = null;
       try {
           ps = conn.prepareStatement("delete from Department where Id = ?");
           ps.setInt(1, id);

           int rowsAffected = ps.executeUpdate();

           if (rowsAffected >0){
               System.out.println("Department deleted with sucess!");
           }
           else {
               throw new RuntimeException("Error this Id doesn't exists!");
           }
       }
       catch (SQLException e){
           throw new DbException(e.getMessage());
       }

    }

    @Override
    public void updateDepartment(Department department) {
       try (PreparedStatement ps = conn.prepareStatement("UPDATE department set Id = ?, Name = ? where id = ?")){
           ps.setInt(1, department.getId());
           ps.setString(2, department.getName());
           ps.setInt(3, department.getId());

           int rowsAffected = ps.executeUpdate();
           if (rowsAffected > 0 ){
               System.out.println("Success department has been Updated!");
           }
           else {
               System.out.println("Update Failed!");
           }


       }
       catch(SQLException e){
           System.out.println(e.getMessage());
       }



    }

    @Override
    public Department findByIdDepartment(Integer id) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Department dep = new Department();
            try{
                ps = conn.prepareStatement("select * from department where Id = ?");
                ps.setInt(1,id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dep.setName(rs.getString("Name"));
                    dep.setId(rs.getInt("Id"));

                    return dep;
                }
                else {
                    throw new RuntimeException("This Id department doesn't exists!");
                }

            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
    }

    @Override
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();

       try {
           PreparedStatement ps = conn.prepareStatement("select * from department ");
           ResultSet rs = ps.executeQuery();

           while(rs.next()){
               departmentList.add(new Department(rs.getInt("Id"), rs.getString("Name")));
           }

       }
       catch(SQLException e){
            throw new DbException(e.getMessage());
       }
       return departmentList;
    }
}
