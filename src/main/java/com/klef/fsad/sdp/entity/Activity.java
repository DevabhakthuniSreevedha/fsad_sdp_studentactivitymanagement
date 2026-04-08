package com.klef.fsad.sdp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="activity_table")
public class Activity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=100,nullable=false)
    private String category;

    @Column(length=100,nullable=false)
    private String name;

    @Column(length=500,nullable=false)
    private String description;

    @Column(nullable=false)
    private String location;

    @Column(nullable=false)
    private String activityDate;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getActivityDate() {
	return activityDate;
    }

    public void setActivityDate(String activityDate) {
	this.activityDate = activityDate;
    }

    public Faculty getFaculty() {
	return faculty;
    }

    public void setFaculty(Faculty faculty) {
	this.faculty = faculty;
    }

    @Override
    public String toString() {
	return "Activity [id=" + id + ", category=" + category
			+ ", name=" + name + ", description=" + description
			+ ", location=" + location
			+ ", activityDate=" + activityDate + "]";
    }
}

