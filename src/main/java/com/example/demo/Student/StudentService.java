package com.example.demo.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw  new IllegalStateException("email taken");
        } else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> studentById = studentRepository.findById(studentId);
        if (studentById.isPresent()) {
            studentRepository.deleteById(studentId);
        } else {
            throw  new IllegalStateException("Student not found");
        }

    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        System.out.println(name+email);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found!"));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw  new IllegalStateException("email taken");
            } else {
                student.setEmail(email);
            }
        }
    }
}
