package com.sean.scs.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
    public static SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        try{
            sessionFactory = new Configuration().buildSessionFactory();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void testSave(){
        Teacher newTeacher = new Teacher("t001", "sean", "cs", 200);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(newTeacher);
        session.getTransaction().commit();
    }
}