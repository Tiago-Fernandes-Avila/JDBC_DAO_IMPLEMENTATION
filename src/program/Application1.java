package program;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Application1 {
    public static void main (String[] args){
        //Implementação e testes no CRUD da classe Department:
        Scanner sc = new Scanner(System.in);

        System.out.println("====================Teste 1 findAll=====================");
        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();

        for(Department n : departmentDAO.findAll()){
            System.out.println(n);
        }
        System.out.println("====================Teste 2 findById=====================");
        System.out.println(departmentDAO.findByIdDepartment(2));

      //  System.out.println("====================Teste 3 deleteDepartment=====================");
        //departmentDAO.deleteByIdDepartment(5);

        System.out.println("====================Teste 4 UpdateDepartment=====================");
        departmentDAO.updateDepartment(new Department(3, "Sports"));

        System.out.println("====================Teste 5 InsertDepartment=====================");
        departmentDAO.insertDepartment(new Department(6, "Food"));

    }

    Scanner s;
}
