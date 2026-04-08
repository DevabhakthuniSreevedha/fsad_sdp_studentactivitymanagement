package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Student;
import com.klef.fsad.sdp.entity.Faculty;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.StudentRepository;
import com.klef.fsad.sdp.repository.FacultyRepository;
import com.klef.fsad.sdp.repository.EnrollmentRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	

	@Override
	public Admin verifyAdminLogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addFaculty(Faculty faculty) 
	{
		facultyRepository.save(faculty);
		return "Faculty Added Successfully";
	}

	@Override
	public List<Faculty> viewAllFaculty() 
	{
		return facultyRepository.findAll();
	}

	@Override
	public List<Student> viewAllStudents() 
	{
		return studentRepository.findAll();
	}

	@Override
	public boolean deleteFaculty(int id) 
	{
		if(facultyRepository.existsById(id))
		{
			facultyRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteStudent(int id)
	{
		Student student = studentRepository.findById(id).orElse(null);

		if(student != null)
		{
			enrollmentRepository.deleteByStudent(student);
			studentRepository.delete(student);
			return true;
		}

		return false;
	}
}