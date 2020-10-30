package com.example.restapi1.controller;

import com.example.restapi1.dto.UserDTO;
import com.example.restapi1.exception.UserNotFoundException;
import com.example.restapi1.model.User;
import com.example.restapi1.repository.userRepository;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    userRepository userRepo;
@GetMapping("/")
    public List<User> getUsers()
{
    return  this.userRepo.findAll();
}




@PostMapping("/users")
public ResponseEntity<Object> createuser(@Valid @RequestBody UserDTO userdto)
{
    User user = new User();
    user.setName(userdto.getUserdto());
   User userSaved= this.userRepo.save(user);
    URI location= ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("{/id}")
            .buildAndExpand(userSaved.getId()).toUri();
    return ResponseEntity.created(location).build();
 }



@GetMapping("/user/{id}")
    public  User get1user(@PathVariable int id){
    User users=this.userRepo.findById(id).orElseThrow(()->
            new UserNotFoundException("User Not Found"));
    return users;
}






@DeleteMapping("/deluser/{id}")

public  void deluser(@PathVariable int id)
{
    this.userRepo.deleteById(id);
}

@PutMapping("/upuser/{id}")
    public  User upuser(@PathVariable int id, @RequestBody UserDTO userdto)
{
    User user = this.userRepo.findById(id).orElse(null);
    user.setName(userdto.getUserdto());
    return this.userRepo.save(user);
}
}
