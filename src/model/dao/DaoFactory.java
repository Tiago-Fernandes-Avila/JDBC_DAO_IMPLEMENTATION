package model.dao;
import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class DaoFactory {

    public static SellerDAO createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDAO createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }


}
