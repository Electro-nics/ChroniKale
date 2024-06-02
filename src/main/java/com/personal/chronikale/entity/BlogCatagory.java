package com.personal.chronikale.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BlogCatagory")
public class BlogCatagory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer catagoryId;
	String catagoryTitle;
	String catagoryDescription;
}
