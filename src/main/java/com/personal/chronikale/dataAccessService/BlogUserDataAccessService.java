package com.personal.chronikale.dataAccessService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.chronikale.DAO.UserDAO;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.repository.UserRepository;
@Repository
public class BlogUserDataAccessService implements UserDAO{
@Autowired
private UserRepository repository;
	@Override
	public Optional<BlogUser> SelectUserDetailsById(Integer userId) {
		// TODO Auto-generated method stub
		return repository.findById(userId);
	}

	@Override
	public List<BlogUser> SelectAllUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void deleteUser(Integer userId){
		repository.deleteById(userId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsuserWithDUplicateEmail(String email) {
		// TODO Auto-generated method stub
		return repository.existsByEmail(email);
	}

	@Override
	public boolean existsuserWithDuplicatePhoneNumber(String phone) {
		// TODO Auto-generated method stub
		return repository.existsByPhoneNumber(phone);
	}

	@Override
	public Boolean insertUser(BlogUser blogUser, String email) {
		repository.save(blogUser);
		return repository.existsByEmail(email);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(BlogUser blogUser) {
		repository.save(blogUser);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean existsUserById(Integer user_id) {
		
		return repository.existsById(user_id);
	}

}
