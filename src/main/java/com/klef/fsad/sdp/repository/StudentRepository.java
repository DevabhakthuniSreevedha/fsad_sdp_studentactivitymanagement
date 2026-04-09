package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>
{
	
    Student findByEmailAndPassword(String email,String password);
    
//    @Query("SELECT s FROM Student s WHERE s.email=?1 AND s.password=?2")
//    Student checkLogin(String email,String password);
    

    Student findByEmail(String email);

//    @Query("SELECT s FROM Student s WHERE s.email=?1")
//    Student getStudentByEmail(String email);


    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.email=?1")
    int deleteStudentByEmail(String email);


    Student findByUsername(String username);

//    @Query("SELECT s FROM Student s WHERE s.username=?1")
//    Student getStudentByUsername(String username);


    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.username=?1")
    int deleteStudentByUsername(String username);

    List<Student> findByDepartment(String department);

//    @Query("SELECT s FROM Student s WHERE s.department=?1")
//    List<Student> getStudentsByDepartment(String department);


    List<Student> findByGender(String gender);

    @Query("SELECT s FROM Student s WHERE s.gender=?1")
    List<Student> getStudentsByGender(String gender);



    List<Student> findByNameContaining(String keyword);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %?1%")
    List<Student> searchStudentByName(String keyword);


    
    long count();

    @Query("SELECT COUNT(s) FROM Student s")
    long totalStudents();


    long countByDepartment(String department);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.department=?1")
    long totalStudentsByDepartment(String department);


    long countByGender(String gender);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.gender=?1")
    long totalStudentsByGender(String gender);



    boolean existsByEmail(String email);

    @Query("SELECT COUNT(s)>0 FROM Student s WHERE s.email=?1")
    boolean checkEmailExists(String email);
    

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.department=?1")
    int deleteStudentsByDepartment(String department);
   

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password=?2 WHERE s.email=?1")
    int updatePasswordByEmail(String email,String password);


    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.department=?2 WHERE s.username=?1")
    int updateDepartmentByUsername(String username,String department);
    

    long countByName(String name);

}