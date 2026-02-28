package com.mari.moviereviews.repository;

import com.mari.moviereviews.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> searchUserByUsername(String username);

    User findUserByUsername(String username);
}
