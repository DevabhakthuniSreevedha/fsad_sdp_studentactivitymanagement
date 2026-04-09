package com.klef.fsad.sdp.dto;

public class StudentDTO 
{
   private int id;
   private String name;
   private String email;
   private String contact;
   private String department;
   private int year;

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }

   public String getContact() {
      return contact;
   }
   public void setContact(String contact) {
      this.contact = contact;
   }

   public String getDepartment() {
      return department;
   }
   public void setDepartment(String department) {
      this.department = department;
   }

   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
   }
}