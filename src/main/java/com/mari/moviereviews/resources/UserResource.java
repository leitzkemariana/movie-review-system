package com.mari.moviereviews.resources;

import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.domain.User;
import com.mari.moviereviews.dto.AuthorDTO;
import com.mari.moviereviews.dto.UserDTO;
import com.mari.moviereviews.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public  ResponseEntity<List<AuthorDTO>> getAllUsers(){
        List<User> list = userService.findAll();
        List<AuthorDTO> listUsers = list.stream().map(x -> new AuthorDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listUsers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok(new UserDTO(obj));
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findUserByName(@RequestParam (value = "username", defaultValue = " ") String username){
        try {
            username = URLDecoder.decode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        User obj = userService.findByUsername(username);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
        User obj = userService.fromDTO(objDTO);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO objDTO){
        User obj = userService.fromDTO(objDTO);
        obj.setId(id);
        userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
