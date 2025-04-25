package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {
    void updateSeller(Seller seller);
    void deleteByIdSeller(Integer id);
    void insertSeller(Seller seller);
    Seller findByIdSeller(Integer id);
    List<Seller> findAll();
    List <Seller> findByDepartment(Department department);
}
