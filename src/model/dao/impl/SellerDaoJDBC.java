package model.dao.impl;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {
   private Connection conn = null;
    private PreparedStatement pstmt = null;


    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
        @Override
        public void updateSeller (Seller seller){
            PreparedStatement ps = null;
            try {

                ps = conn.prepareStatement("UPDATE seller " +
                        "set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                        "where Id = ?");
                ps.setString(1, seller.getName());
                ps.setString(2, seller.getEmail());
                ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
                ps.setDouble(4, seller.getBaseSalary());
                ps.setInt(5, seller.getDepartment().getId());
                ps.setInt(6, seller.getId());

                int rA = ps.executeUpdate();



            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                DB.closeConnection();

            }
    }

        @Override
        public void deleteByIdSeller (Integer id){
        try {
            PreparedStatement st = conn.prepareStatement("Delete from seller where Id = ?");
            st.setInt(1,id);

          int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("User has been deleted!");
            }
            else {
                throw new DbException("SQL ERROR no one row affected!");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void insertSeller (Seller seller){
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement("insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values(?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getEmail());
            ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            ps.setDouble(4, seller.getBaseSalary());
            ps.setInt(5, seller.getDepartment().getId());

            int rA = ps.executeUpdate();

            if (rA > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {

                    int id = rs.getInt(1);
                    seller.getDepartment().setId(id);
                    System.out.println("Rows affected: " + rA);
                }
            DB.closeConnection();
            }
            else {
                throw new DbException("Unexpected error, no rows affected!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeConnection();

        }
    }
        @Override
        public Seller findByIdSeller (Integer id) {

            ResultSet rs = null;
            try {

                pstmt = conn.prepareStatement("select seller.*, department.name as DepartmentName from seller\n" +
                        "inner join department \n" +
                        "on seller.DepartmentId = department.Id\n" +
                        "where seller.Id = ?");

                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    Department department = instantiateDepartment(rs);
                    Seller obj = instantiateSeller(rs, department);
                    return obj;
                }

                return null;

            } catch (SQLException e) {
                throw new DbException("Houve um problema de sqlException causado por: " + e.getMessage());
            } finally {

            }


        }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
       Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("DepartmentName"));
        return department;
    }

    @Override
        public List<Seller> findAll () {
        PreparedStatement st;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("select seller.*, department.Name as DepartmentName FROM seller INNER JOIN department  \n" +
                    "ON seller.DepartmentId = department.Id \n" +
                    "ORDER BY seller.Name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map <Integer, Department> map = new HashMap<>();

            while(rs.next()){

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                list.add(instantiateSeller(rs, dep)
                );
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeConnection();
            DB.closeResultSet(rs);

        }
    }










    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.getConnection();
         st = conn.prepareStatement("select seller.*, department.Name as DepartmentName FROM seller INNER JOIN department  \n" +
                 "ON seller.DepartmentId = department.Id \n" +
                 "where department.Id = ?\n" +
                 "ORDER BY seller.Name");

         st.setInt(1, department.getId());

         rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map <Integer, Department> map = new HashMap<>();

         while(rs.next()){

             Department dep = map.get(rs.getInt("DepartmentId"));

             if (dep == null){
                 dep = instantiateDepartment(rs);
                 map.put(rs.getInt("DepartmentId"), dep);
             }

             list.add(instantiateSeller(rs, dep)
             );
         }
         return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeConnection();
            DB.closeResultSet(rs);

        }
    }

}
