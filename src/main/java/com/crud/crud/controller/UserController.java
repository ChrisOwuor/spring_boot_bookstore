package com.crud.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.model.User;
import com.crud.crud.repository.UserRepository;

@RestController
public class UserController {

    /*
     * we need to inject the user repository
     * which gives us the methods to interact with the db using the
     * user model like findall or find
     */
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User addUser(@RequestBody User newUser) {

        System.out.println(newUser.getEmail());

        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> allUsers = userRepository.findAll();

        return allUsers;
    }

    @PutMapping("/user/{id}/")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        @SuppressWarnings("null")
        Optional<User> optionalExistingUser = userRepository.findById(id);

        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            // Update existing user with data from newUser
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            // Update other fields as needed

            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            // Handle the case where user with given id doesn't exist
            throw new RuntimeException("User not found with id: " + id);
        }

    }
    // @PostMapping("/users")
    // /*
    // * User is the return type of createUser function
    // * as it will return an object
    // * we name the return type the class name from which the object
    // * is created from
    // */
    // public User createUser() {
    // User user = new User();
    // user.setName("sam");
    // user.setEmail("s@f.gon");
    // /*
    // * the save method will return an object representing the
    // * saved item from db
    // * the type of the object is User since it was created from User class
    // */
    // User createdUser = userRepository.save(user);

    // return createdUser;
    // }

}
