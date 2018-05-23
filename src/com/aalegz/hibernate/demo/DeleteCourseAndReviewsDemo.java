package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Course;
import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import com.aalegz.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCourseAndReviewsDemo {

    public static void main(String[] args) {

        //create session factory
        System.out.println("Session factory creation..");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        System.out.println("Session factory created!\n");

        //create session
        System.out.println("Session creation");
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Session created!\n");

        try {
            //start a transaction
            session.beginTransaction();

            //get the course
            int theId = 10;

            Course tempCourse = session.get(Course.class, theId);

            //print the course
            System.out.println("The Course: " + tempCourse);

            //delete the course
            session.delete(tempCourse);

            //print the course reviews
            System.out.println("Reviews: " + tempCourse.getReviews());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!");
        } finally {
            //add clean up code
            session.close();

            sessionFactory.close();
        }

    }
}
