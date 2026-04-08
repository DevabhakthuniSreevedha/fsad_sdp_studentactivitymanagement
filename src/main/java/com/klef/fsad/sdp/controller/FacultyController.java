package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Faculty;
import com.klef.fsad.sdp.service.FacultyService;

@RestController
@RequestMapping("facultyapi")
@CrossOrigin("*")
public class FacultyController 
{
  @Autowired
  private FacultyService facultyService;
  
  @GetMapping("/")
  public String facultyhome()
  {
	   return "Faculty Controller Module";
  }
  
  @PostMapping("login")
  public ResponseEntity<?> verifyfacultylogin(@RequestBody Faculty faculty)
  {
	   try
		{
			Faculty f = facultyService.verifyFacultyLogin(faculty.getEmail(), faculty.getPassword());
		
		    if(f!=null)
		    {
		    	return ResponseEntity.status(200).body(f);
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
  
  
@PostMapping("/addactivity")
public ResponseEntity<String> addActivity(@RequestBody Activity activity)
{
   try
   {
       String output = facultyService.addActivity(activity);
       return ResponseEntity.status(201).body(output);
   }
   catch(Exception e)
   {
       return ResponseEntity.status(500).body("Error Adding Activity");
   }
}
  
@GetMapping("/viewmyactivities/{facultyid}")
public ResponseEntity<?> viewMyActivities(@PathVariable int facultyid)
{
    try
    {
        List<Activity> activities = facultyService.viewActivitiesByFaculty(facultyid);

        if(activities == null || activities.isEmpty())
        {
            return ResponseEntity.status(204).body("No Activities Found");
        }

        return ResponseEntity.ok(activities);
    }
    catch(Exception e)
    {
        return ResponseEntity.status(500).body("Error Fetching Activities");
    }
}


@DeleteMapping("/deleteactivity/{id}")
public ResponseEntity<String> deleteActivity(@PathVariable int id)
{
 try
 {
     String output = facultyService.deleteActivity(id);
     return ResponseEntity.ok(output);
 }
 catch(Exception e)
 {
     return ResponseEntity.status(500).body("Error Deleting Activity");
 }
}

@GetMapping("/viewenrollmentsbyfaculty/{facultyid}")
public ResponseEntity<?> viewEnrollmentsByFaculty(@PathVariable int facultyid)
{
	try
	{
		List<Enrollment> list = facultyService.viewEnrollmentsByFaculty(facultyid);

		if(list == null || list.isEmpty())
		{
			return ResponseEntity.status(204).body("No Enrollments Found");
		}

		return ResponseEntity.status(200).body(list);
	}
	catch(Exception e)
	{
		return ResponseEntity.status(500).body("Error Fetching Enrollments");
	}
}

@PutMapping("/updateenrollmentstatus")
public ResponseEntity<String> updateEnrollmentStatus(@RequestBody Enrollment e)
{
	try
	{
		String output = facultyService.updateEnrollmentStatus(e.getId(), e.getStatus());
		return ResponseEntity.status(200).body(output);
	}
	catch(Exception ex)
	{
		return ResponseEntity.status(500).body("Error Updating Status");
	}
}
  
}