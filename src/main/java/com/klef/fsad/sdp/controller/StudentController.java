package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Student;
import com.klef.fsad.sdp.service.StudentService;

@RestController
@RequestMapping("studentapi")
@CrossOrigin("*")
public class StudentController 
{
   @Autowired
   private StudentService studentService;

   @GetMapping("/")
   public String studenthome()
   {
       return "Student Controller Module";
   }

   @PostMapping("/registration")
   public ResponseEntity<String> studentregistration(@RequestBody Student s)
   {
       try
       {
           String output = studentService.studentRegistration(s);

           return ResponseEntity.status(201).body(output);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Internal Server Error");
       }
   }

   @PostMapping("/login")
   public ResponseEntity<?> verifystudentlogin(@RequestBody Student student)
   {
       try
       {
           Student s = studentService.verifyStudentLogin(student.getEmail(),student.getPassword());

           if(s != null)
           {
               return ResponseEntity.status(200).body(s);
           }
           else
           {
               return ResponseEntity.status(401).body("Login Invalid");
           }
       }
       catch (Exception e) 
       {
           return ResponseEntity.status(500).body("Internal Server Error");
       }
   }

   @PostMapping("/updateprofile")
   public ResponseEntity<String> studentupdateprofile(@RequestBody Student s)
   {
       try
       {
           String output = studentService.updateStudentProfile(s);

           return ResponseEntity.status(201).body(output);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Internal Server Error");
       }
   }

   @GetMapping("/viewallactivities")
   public ResponseEntity<?> viewallactivities()
   {
       try
       {
           List<Activity> activities = studentService.viewAllActivities();

           if(activities == null || activities.isEmpty())
           {
               return ResponseEntity.status(204).body("No Activities Found");
           }
           else
           {
               return ResponseEntity.status(200).body(activities);
           }
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Fetching Activities");
       }
   }

   @PostMapping("/enrollactivity")
   public ResponseEntity<String> enrollactivity(@RequestBody Enrollment enrollment)
   {
       try
       {
           String output = studentService.enrollActivity(enrollment);

           return ResponseEntity.status(201).body(output);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Enrolling Activity");
       }
   }

   @GetMapping("/viewenrollments/{studentid}")
   public ResponseEntity<?> viewenrollments(@PathVariable int studentid)
   {
       try
       {
           List<Enrollment> enrollments = studentService.viewEnrollmentsByStudent(studentid);

           if(enrollments == null || enrollments.isEmpty())
           {
               return ResponseEntity.status(204).body("No Enrollments Found");
           }
           else
           {
               return ResponseEntity.status(200).body(enrollments);
           }
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Fetching Enrollments");
       }
   }
}