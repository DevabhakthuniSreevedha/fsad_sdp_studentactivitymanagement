package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Faculty;
import com.klef.fsad.sdp.repository.ActivityRepository;
import com.klef.fsad.sdp.repository.EnrollmentRepository;
import com.klef.fsad.sdp.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService
{
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Override
	public Faculty verifyFacultyLogin(String email, String pwd) 
	{
		return facultyRepository.findByEmailAndPassword(email, pwd);
	}

	@Override
	public String addActivity(Activity activity) 
	{
		activityRepository.save(activity);
		return "Activity Added Successfully";
	}

	@Override
	public String deleteActivity(int activityid) 
	{
		activityRepository.deleteById(activityid);
		return "Activity Deleted Successfully";
	}

	@Override
	public List<Activity> viewActivitiesByFaculty(int facultyid) 
	{
		Faculty faculty = facultyRepository.findById(facultyid).orElse(null);

	    if(faculty!= null)
	    {
	        return activityRepository.findByFaculty(faculty);
	    }

	    return null;
	}
	
	@Override
	public List<Enrollment> viewEnrollmentsByFaculty(int facultyid) 
	{
		Faculty faculty = facultyRepository.findById(facultyid).orElse(null);

		if(faculty != null)
		{
			List<Activity> activities = activityRepository.findByFaculty(faculty);

			if(activities != null)
			{
				return enrollmentRepository.findByActivityIn(activities);
			}
		}

		return null;
	}

	@Override
	public String updateEnrollmentStatus(int id, String status) 
	{
		Enrollment e = enrollmentRepository.findById(id).orElse(null);

		if(e != null)
		{
			e.setStatus(status);
			enrollmentRepository.save(e);
			return "Status Updated Successfully";
		}

		return "Enrollment Not Found";
	}

}