package com.klef.fsad.sdp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Student;
import com.klef.fsad.sdp.repository.ActivityRepository;
import com.klef.fsad.sdp.repository.EnrollmentRepository;
import com.klef.fsad.sdp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	 @Autowired
     private StudentRepository studentRepository;
	 
	 @Autowired
	 private EnrollmentRepository enrollmentRepository;
	 
	 @Autowired
	 private ActivityRepository activityRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;
	
	@Override
	public Student verifyStudentLogin(String email, String pwd) 
	{
		return studentRepository.findByEmailAndPassword(email, pwd);
	}

	@Override
	public String updateStudentProfile(Student student) 
	{
		Optional<Student> optional = studentRepository.findById(student.getId());
		
		if(optional.isPresent())
		{
			Student s = optional.get();
			
			s.setContact(student.getContact());
			s.setDepartment(student.getDepartment());
			s.setName(student.getName());

            if(student.getPassword() != null
                    && !student.getPassword().isEmpty()
                    && !student.getPassword().equals("********"))
            {
                s.setPassword(passwordEncoder.encode(student.getPassword()));
            }
			
			studentRepository.save(s);
			
			return "Student Profile Updated Successfully";
		}
		else
		{
			return "Student ID Not Found to Update";
		}
	}

	@Override
	public String studentRegistration(Student student) 
	{
        student.setPassword(passwordEncoder.encode(student.getPassword()));
		studentRepository.save(student);
		return "Student Registered Successfully";
	}
	
	@Override
	public List<Activity> viewAllActivities() 
	{
		return activityRepository.findAll();
	}

	@Override
	public String enrollActivity(Enrollment enrollment) 
	{
		enrollment.setEnrollmentDate(LocalDate.now().toString());
		enrollment.setStatus("ENROLLED");
		enrollmentRepository.save(enrollment);
		return "Activity Enrolled Successfully";
	}

	@Override
	public List<Enrollment> viewEnrollmentsByStudent(int studentid) 
	{
		Student student = studentRepository.findById(studentid).orElse(null);

		if(student != null)
		{
			return enrollmentRepository.findByStudent(student);
		}

		return null;
	}
}