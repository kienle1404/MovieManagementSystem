package com.mvms;

import com.mvms.entity.Address;
import com.mvms.entity.Customer;
import com.mvms.entity.Gender;
import com.mvms.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        System.out.println(sessionFactory);

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        Address address = new Address();
        address.setAddress1("17 Duy Tan");
        address.setAddress2("19 Duy Tan");

        List<Customer> customers = new ArrayList<>();
        Customer biden = new Customer("Biden", "Obama", Gender.MALE, "biden@gmail.com"
                , true);
        Customer obama = new Customer("Obama", "Cruso", Gender.FEMALE,
                "obama@gmail.com"
                , true);
        biden.setAddress(address);
        obama.setAddress(address);
        customers.add(biden);
        customers.add(obama);
        address.setCustomers(customers);

//        Address removedAddr = session.get(Address.class, 1L);
        //session.remove(removedAddr);

   public static void moreTests( String[] args )
    {
        insert();
        find();
        insertPhone();
    }
    public static void insert(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Category category = new Category("Comic");
        Film film = new Film("Comic film 1","A comic film");
        Film film2 = new Film("Comic film 2","A comic film part 2");
        FilmCategory filmcategory = new FilmCategory(film, category );
        FilmCategory filmcategory2 = new FilmCategory(film2, category );
        //List<Category> newCategories = new ArrayList<>();
        //newCategories.add(new Category("Romance"));
        //newCategories.add(new Category("Comedy"));
        session.persist(category);
        session.persist(film);
        session.persist(film2);
        session.persist(filmcategory);
        session.persist(filmcategory2);

        tx.commit();
        session.close();
    }
    public static void find(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        //Transaction tx = session.beginTransaction();
        FilmDao filmDao = new FilmDaoImpl();
        //Customer customer = filmDao.queryCustomer(Long.valueOf(2));
        //rental.setCustomer(customer);
        filmDao.queryByFilmCategory();

        session.close();
    }
    public static void insertPhone(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Phone phone = new Phone();
        //PhoneGroup phoneGroup;
        phone.setPhoneNumber("123");
        phone.setPhoneGroup(PhoneGroup.HOME);
        session.persist(phone);

        tx.commit();
        session.close();
    }


    }
}
