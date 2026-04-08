package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.sdp.entity.Activity;
import com.klef.fsad.sdp.entity.Faculty;

public interface ActivityRepository extends JpaRepository<Activity, Integer>
{
   public List<Activity> findByFaculty(Faculty faculty);
}