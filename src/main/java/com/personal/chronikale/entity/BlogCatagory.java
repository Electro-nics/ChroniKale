package com.personal.chronikale.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BlogCatagory")
@Getter
@Setter
@NoArgsConstructor
public class BlogCatagory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catagoryId;
	private String catagoryTitle;
	private String catagoryDescription;
	
	@OneToMany(
			mappedBy = "catagory",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
	private List<BlogPost> userPost=new ArrayList();
	
	public BlogCatagory(String catagoryTitle, String catagoryDescription) {
		this.catagoryTitle = catagoryTitle;
		this.catagoryDescription = catagoryDescription;
	}
	public BlogCatagory() {
	}
	public Integer getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(Integer catagoryId) {
		this.catagoryId = catagoryId;
	}
	public String getCatagoryTitle() {
		return catagoryTitle;
	}
	public void setCatagoryTitle(String catagoryTitle) {
		this.catagoryTitle = catagoryTitle;
	}
	public String getCatagoryDescription() {
		return catagoryDescription;
	}
	public void setCatagoryDescription(String catagoryDescription) {
		this.catagoryDescription = catagoryDescription;
	}
	@Override
	public String toString() {
		return "BlogCatagory [catagoryId=" + catagoryId + ", catagoryTitle=" + catagoryTitle + ", catagoryDescription="
				+ catagoryDescription + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(catagoryDescription, catagoryId, catagoryTitle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogCatagory other = (BlogCatagory) obj;
		return Objects.equals(catagoryDescription, other.catagoryDescription)
				&& Objects.equals(catagoryId, other.catagoryId) && Objects.equals(catagoryTitle, other.catagoryTitle);
	}
	
}
