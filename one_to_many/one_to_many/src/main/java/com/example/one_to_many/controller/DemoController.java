package com.example.one_to_many.controller;

import com.example.one_to_many.entity.Course;
import com.example.one_to_many.entity.Instructor;
import com.example.one_to_many.repository.AppDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {

        private final AppDao appDao;

        @PostMapping("/save")
        public Instructor saveInstructor(@RequestBody Instructor instructor) {

            List<Course> courses = instructor.getCourses();

            for (Course course : courses) {
                course.setInstructor(instructor);
            }

            return appDao.saveInstructor(instructor);
        }

        @GetMapping("/getById/{instructorId}")
        public Instructor getInstructorById(@PathVariable int instructorId) {

            Instructor instructor = appDao.getInstructorById(instructorId);

            System.out.println(instructor);
//          System.out.println("Courses:- " +instructor.getCourses());
            return instructor;
    }

    //    Finding courses based on instructorId
    @GetMapping("/getcoursesbyid/{instructorId}")
    public List<Course> getCoursesByInstructorId(@PathVariable int instructorId) {

        List<Course> courses= appDao.findCoursesByInstructorId(instructorId); // Instructor + InstructorDetail

        return courses;
    }

}
