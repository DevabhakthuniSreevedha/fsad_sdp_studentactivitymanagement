package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer>
{
   Faculty findByEmailAndPassword(String email, String password);
   Faculty findByEmail(String email);
}