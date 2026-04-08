package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Student;
import com.klef.fsad.sdp.entity.Faculty;

public interface AdminService 
{
  public Admin verifyAdminLogin(String username,String password);
  
  public String addFaculty(Faculty faculty);
  public List<Faculty> viewAllFaculty();
  public boolean deleteFaculty(int id);
  
  public List<Student> viewAllStudents();
  public boolean deleteStudent(int id);
}