package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Student;

public interface StudentService 
{
  public String studentRegistration(Student student);
  public Student verifyStudentLogin(String email,String pwd);
  public String updateStudentProfile(Student student);
  
  public List<Activity> viewAllActivities();
  
  public String enrollActivity(Enrollment enrollment);
  public List<Enrollment> viewEnrollmentsByStudent(int studentid);
}