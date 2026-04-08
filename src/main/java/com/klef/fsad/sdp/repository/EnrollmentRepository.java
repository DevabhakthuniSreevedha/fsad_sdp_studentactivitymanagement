package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Enrollment;
import com.klef.fsad.sdp.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>
{
   public List<Enrollment> findByStudent(Student student);
   public List<Enrollment> findByActivityIn(List<Activity> activities);

   public void deleteByStudent(Student student);
}