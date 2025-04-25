package program;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String [] args){
      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);
        SellerDAO sellerDao = DaoFactory.createSellerDao();
      /*    System.out.println("=============TESTE 1 findBySeller===============");
        Seller sl = sellerDao.findByIdSeller(2);
        System.out.println(sl);


        System.out.println("\n=============TESTE 2 findByDepartment===============");
        Department dep = new Department(1, null);
        List<Seller> sl1 = sellerDao.findByDepartment(dep);


        for (Seller n : sl1){
            System.out.println(n);
        }

        System.out.println("\n=============TESTE 3 findByDepartment===============");
        List<Seller> sl2 = sellerDao.findAll();
        for (Seller n : sl2){
            System.out.println(n);


        System.out.println("\n=============TESTE 4 InsertIntoSeller===============");

        Seller s = new Seller( null, "Felipe Fenandes", " felipe955@gmail.com", new Date(), 5000.0, new Department(2, "null"));

        sellerDao.insertSeller(s);




        System.out.println("\n=============TESTE 5 UpdateSeller===============");

            Seller seller = sellerDao.findByIdSeller(1);
            seller.setName("Fabiana miguel");
            sellerDao.updateSeller(seller);
            System.out.println("Update Complete!");

        System.out.println("\n=============TESTE 6 DeleteSeller===============\n Enter with Id seller will be Deleted from System: ");
        sellerDao.deleteByIdSeller(sc.nextInt());
*/





    }







    }

