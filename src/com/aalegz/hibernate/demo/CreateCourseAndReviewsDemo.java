package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Course;
import com.aalegz.hibernate.demo.entity.Instructor;
import com.aalegz.hibernate.demo.entity.InstructorDetail;
import com.aalegz.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndReviewsDemo {

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

            //create a course
            Course tempCourse = new Course("How to win in Pacman");

            //add some reviews
            tempCourse.addReview(new Review("Great!"));
            tempCourse.addReview(new Review("Perfect!!!"));
            tempCourse.addReview(new Review("Ideally"));

            //save the course ... and leverage the cascade all
            System.out.println("\nSaving the course: ");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

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
