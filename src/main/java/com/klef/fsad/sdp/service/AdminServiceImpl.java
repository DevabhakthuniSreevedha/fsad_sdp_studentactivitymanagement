package com.klef.fsad.sdp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.dto.FacultyDTO;
import com.klef.fsad.sdp.dto.StudentDTO;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
	

	@Override
	public Admin verifyAdminLogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addFaculty(Faculty faculty) 
	{
        faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
		facultyRepository.save(faculty);
		return "Faculty Added Successfully";
	}

	@Override
	public List<FacultyDTO> viewAllFaculty() 
	{
		List<Faculty> list = facultyRepository.findAll();

		List<FacultyDTO> dtoList = new ArrayList<>();

		for(Faculty f : list)
		{
			FacultyDTO dto = new FacultyDTO();
			dto.setId(f.getId());
			dto.setName(f.getName());
			dto.setEmail(f.getEmail());
			dto.setContact(f.getContact());
			dto.setDepartment(f.getDepartment());

			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public List<StudentDTO> viewAllStudents() 
	{
		List<Student> list = studentRepository.findAll();

		List<StudentDTO> dtoList = new ArrayList<>();

		for(Student s : list)
		{
			StudentDTO dto = new StudentDTO();
			dto.setId(s.getId());
			dto.setName(s.getName());
			dto.setEmail(s.getEmail());
			dto.setContact(s.getContact());
			dto.setDepartment(s.getDepartment());
			dto.setYear(s.getYear());

			dtoList.add(dto);
		}

		return dtoList;
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