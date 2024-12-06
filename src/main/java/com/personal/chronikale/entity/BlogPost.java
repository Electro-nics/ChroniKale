package com.personal.chronikale.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="BlogPost", schema = "public")
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	private String title;
	@Column(length = 10000, nullable = false)
	private String content;
	private String imageName;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "Catagory_id")
	private BlogCatagory catagory;
	@ManyToOne
	private BlogUser user;
	public BlogPost(String title, String content, String imageName, Date addedDate) {
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
	}
	public BlogPost() {
		
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BlogCatagory getCatagory() {
		return catagory;
	}
	public void setCatagory(BlogCatagory catagory) {
		this.catagory = catagory;
	}
	public BlogUser getUser() {
		return user;
	}
	public void setUser(BlogUser user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(addedDate, content, imageName, postId, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		return Objects.equals(addedDate, other.addedDate) && Objects.equals(content, other.content)
				&& Objects.equals(imageName, other.imageName) && Objects.equals(postId, other.postId)
				&& Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "BlogPost [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", addedDate=" + addedDate + "]";
	}
	
	
	
	
	
	

}
