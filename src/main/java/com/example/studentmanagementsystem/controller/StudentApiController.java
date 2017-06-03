package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import com.example.studentmanagementsystem.repository.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The ApiController class.
 * @author Jorge Vasquez
 * @since 1.8
 */
@RestController
@RequestMapping(value = "/students")
public class StudentApiController {

    private StudentRepository studentRepository;

    /**
     * Creates a new instance of ApiController.
     * @param studentRepository reference to the StudentRepository
     */
    @Autowired
    public StudentApiController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Creates a new Student.
     * @param student the student to be created
     * @return the student saved in the database
     */
    @RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.POST)
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Finds a student by id.
     * @param id the searched student id
     * @return the found student
     * @throws StudentNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(int id) throws StudentNotFoundException {
        return Optional.ofNullable(studentRepository.findOne(id)).orElseThrow(StudentNotFoundException::new);
    }

    /**
     * Finds students for the given search parameters.
     * @param firstName the searched first name (optional)
     * @param lastName  the searched last name (optional)
     * @return the found students for the given search parameters
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> getStudents(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName) {
        List<Student> students;
        if (firstName != null && lastName != null) {
            students = studentRepository.findStudentsByFirstAndLastName(firstName, lastName);
        } else if (firstName != null) {
            students = studentRepository.findStudentsByFirstNameContainingIgnoreCase(firstName);
        } else if (lastName != null) {
            students = studentRepository.findStudentsByLastNameContainingIgnoreCase(lastName);
        } else {
            students = studentRepository.findAll();
        }
        return students;
    }

    /**
     * Updates a student with the given data.
     * @param student the new data for the student
     * @return the updated student
     * @throws StudentNotFoundException
     */
    @RequestMapping(value = "/{id}/{firstName}/{lastName}", method = RequestMethod.PUT)
    public Student updateStudent(Student student) throws StudentNotFoundException {
        Optional.ofNullable(studentRepository.findOne(student.getId())).orElseThrow(StudentNotFoundException::new);
        return studentRepository.save(student);
    }

    /**
     * Deletes a student from the database.
     * @param id the id of the student to be deleted
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(int id) {
        studentRepository.delete(id);
    }

    /**
     * Handles StudentNotFoundException and EmptyResultDataAccessException.
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The student was not found in the system")
    @ExceptionHandler({StudentNotFoundException.class, EmptyResultDataAccessException.class})
    public void exceptionHandler() {
        // No Op
    }
}
