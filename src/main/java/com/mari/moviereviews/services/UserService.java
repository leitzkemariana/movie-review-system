package com.mari.moviereviews.services;

import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.domain.User;
import com.mari.moviereviews.dto.UserDTO;
import com.mari.moviereviews.repository.ReviewRepository;
import com.mari.moviereviews.repository.UserRepository;
import com.mari.moviereviews.services.exception.ObjectNotFoundException;
import com.mari.moviereviews.services.exception.UnavailableUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("User with id: " + id + " not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User insert(User obj) {
        if(userRepository.searchUserByUsername(obj.getUsername()).isPresent()) {
            throw new UnavailableUsernameException("User with name: " + obj.getUsername() + " already exists");
        } else {
            return userRepository.save(obj);
        }
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());

        if (!newObj.getUsername().equals(obj.getUsername())) {
            if(userRepository.searchUserByUsername(obj.getUsername()).isPresent()) {
                throw new UnavailableUsernameException("User with name: " + obj.getUsername() + " already exists");
            }
        }
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    public void updateData(User newObj, User obj) {
        newObj.setUsername(obj.getUsername());
        newObj.setPassword(obj.getPassword());
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getUsername(), objDTO.getPassword());
    }
}
