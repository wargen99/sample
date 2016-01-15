package com.example.repository.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.dto.userInfo.User;

import java.lang.String;
import java.util.List;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	List<User> findByEmail(String email);
}
