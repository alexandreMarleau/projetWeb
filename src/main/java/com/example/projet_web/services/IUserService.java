package com.example.projet_web.services;

import com.example.projet_web.Model.entities.Comment;
import com.example.projet_web.Model.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> readOne(long Id);
    List<User> findUserBySubstr(String subStr);
    List<User> findUserByCoachId(Long coachId);

}