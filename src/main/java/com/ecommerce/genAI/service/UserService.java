package com.ecommerce.genAI.service;

import com.ecommerce.genAI.domain.Perfume;
import com.ecommerce.genAI.domain.User;
import graphql.schema.DataFetcher;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserById(Long userId);

    User getUserInfo(String email);
    
    Page<User> getAllUsers(Pageable pageable);

    List<Perfume> getCart(List<Long> perfumeIds);

    User updateUserInfo(String email, User user);

    DataFetcher<List<User>> getAllUsersByQuery();

    DataFetcher<User> getUserByQuery();
}
