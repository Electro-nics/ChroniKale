package com.personal.chronikale.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "BlogUser", schema = "public")
@Setter
@Getter
@NoArgsConstructor
public class BlogUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String about;
	
	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)
	private List<BlogPost> userPost=new ArrayList();
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public BlogUser(String name, String email, String phoneNumber, String password, String about) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.about = about;
	}
	public BlogUser() {
	}
	@Override
	public int hashCode() {
		return Objects.hash(about, email, name, password, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogUser other = (BlogUser) obj;
		return Objects.equals(about, other.about) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "BlogUser [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", about=" + about + "]";
	}
	
}
