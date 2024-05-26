package com.personal.chronikale.DAO;

import java.util.List;
import java.util.Optional;

import com.personal.chronikale.entity.BlogUser;

public interface UserDAO {
Optional<BlogUser> SelectUserDetailsById(Integer userId);
List<BlogUser> SelectAllUser();
void deleteUser(Integer userId);
boolean existsuserWithDUplicateEmail(String email);
boolean existsuserWithDuplicatePhoneNumber(String phone);
void insertUser(BlogUser blogUser);
void updateUser(BlogUser blogUser);
}
