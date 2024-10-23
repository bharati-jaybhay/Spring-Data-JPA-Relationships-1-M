package com.example.one_to_many.repository;


import com.example.one_to_many.entity.Course;
import com.example.one_to_many.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class AppDao {

    private final EntityManager entityManager;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
            return entityManager.merge(instructor);
        }


    public Instructor getInstructorById(int instructorId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);

//        Hibernate.initialize(instructor.getCourses()); // Ensure courses are loaded only when needed
        return instructor;
    }

    public List<Course> findCoursesByInstructorId(int instructorId) {

        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.instructorId = :data", Course.class);

        query.setParameter("data", instructorId);

        List<Course> courses = query.getResultList();

        return courses;
    }
}
