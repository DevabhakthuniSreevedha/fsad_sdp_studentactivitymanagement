package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.dto.FacultyDTO;
import com.klef.fsad.sdp.dto.StudentDTO;
import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Faculty;
import com.klef.fsad.sdp.service.AdminService;

@RestController
@RequestMapping("adminapi")
@CrossOrigin("*")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String index()
	{
		return "Extracurricular Activity Management System";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> checkadminlogin(@RequestBody Admin admin)
	{
		try
		{
			Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());
		
		    if(a!=null)
		    {
		    	return ResponseEntity.status(200).body(admin);
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
	
	@PostMapping("/addfaculty")
	public ResponseEntity<String> addFaculty(@RequestBody Faculty faculty)
	{
		try
		{
			String output = adminService.addFaculty(faculty);
			return ResponseEntity.status(201).body(output);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	
	@GetMapping("/viewallfaculty")
	public ResponseEntity<?> viewallfaculty()
	{
	    try
	    {
	        List<FacultyDTO> faculty = adminService.viewAllFaculty();
	        return ResponseEntity.ok(faculty);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Faculty");
	    }
	}
	
	@DeleteMapping("/deletefaculty/{id}")
	public ResponseEntity<String> deleteFaculty(@PathVariable int id)
	{
	    try
	    {
	        boolean deleted = adminService.deleteFaculty(id);

	        if(deleted)
	        {
	            return ResponseEntity.ok("Faculty Deleted Successfully");
	        }
	        else
	        {
	            return ResponseEntity.status(404).body("Faculty Not Found");
	        }
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
	
	@GetMapping("/viewallstudents")
	public ResponseEntity<?> viewAllStudents()
	{
	    try
	    {
	        List<StudentDTO> students = adminService.viewAllStudents();
	        return ResponseEntity.ok(students);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Students");
	    }
	}
	
	@DeleteMapping("/deletestudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id)
	{
	    try
	    {
	        boolean deleted = adminService.deleteStudent(id);

	        if(deleted)
	        {
	            return ResponseEntity.ok("Student Deleted Successfully");
	        }
	        else
	        {
	            return ResponseEntity.status(404).body("Student Not Found");
	        }
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
}