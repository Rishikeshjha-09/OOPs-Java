package com.kiet.student.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiet.student.Model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> users = new ArrayList<>();

    @PostMapping
    public String postMethodName(@RequestBody Student u) {
        users.add(u);
        return "Added " + u.getName();
    }

    @GetMapping
    public List<Student> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public Student getUser(@PathVariable int id) {
        for (Student u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);

        if (removed) {
            return "Deleted user " + id;
        }

        return "User not found";
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        List<Student> result = new ArrayList<>();

        for (Student s : users) {
            if (s.getName().equalsIgnoreCase(name)) {
                result.add(s);
            }
        }

        return result;
    }
}