package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Faculty;

public interface FacultyService 
{
	public Faculty verifyFacultyLogin(String email,String pwd);
    
	public String addActivity(Activity activity);
	public List<Activity> viewActivitiesByFaculty(int facultyid);
	public String deleteActivity(int activityid);
	
	public List<Enrollment> viewEnrollmentsByFaculty(int facultyid);
	public String updateEnrollmentStatus(int id, String status);
}