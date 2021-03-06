package com.example.studentmanagementsystem.repository.exception;

/**
 * The StudentNotFoundException class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class StudentNotFoundException extends Exception {

    /**
     * Creates a new instance of StudentNotFoundException.
     * @param studentId the studentId
     */
    public StudentNotFoundException(int studentId) {
        super("Record for student with id " + studentId + " not found");
    }
}
