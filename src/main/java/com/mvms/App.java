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

        session.persist(address);

        tx.commit();

        session.close();


    }
}
