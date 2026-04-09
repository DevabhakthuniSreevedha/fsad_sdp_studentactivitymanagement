package com.klef.fsad.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Faculty;
import com.klef.fsad.sdp.entity.Student;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.FacultyRepository;
import com.klef.fsad.sdp.repository.StudentRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException
    {
        Optional<Admin> adminOpt = adminRepository.findById(input);
        if (adminOpt.isPresent())
        {
            Admin admin = adminOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ADMIN"))
            );
        }

        Faculty faculty = facultyRepository.findByEmail(input);
        if (faculty != null)
        {
            return new org.springframework.security.core.userdetails.User(
                    faculty.getEmail(),
                    faculty.getPassword(),
                    List.of(new SimpleGrantedAuthority("FACULTY"))
            );
        }

        Student student = studentRepository.findByEmail(input);
        if (student != null)
        {
            return new org.springframework.security.core.userdetails.User(
                    student.getEmail(),
                    student.getPassword(),
                    List.of(new SimpleGrantedAuthority("STUDENT"))
            );
        }

        throw new UsernameNotFoundException("User not found with input: " + input);
    }

    @Override
    public Object getUserByLogin(String input)
    {
        Optional<Admin> adminOpt = adminRepository.findById(input);
        if (adminOpt.isPresent())
        {
            return adminOpt.get();
        }

        Faculty faculty = facultyRepository.findByEmail(input);
        if (faculty != null)
        {
            return faculty;
        }

        Student student = studentRepository.findByEmail(input);
        if (student != null)
        {
            return student;
        }

        return null;
    }
}