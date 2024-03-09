package org.unibl.etf.onlinefitness.controllers;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
import org.unibl.etf.onlinefitness.models.dto.UserInfoDTO;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public UserDTO getUserByUserId(@PathVariable Integer id){
        return this.userService.getUserByUserId(id);
    }

    @GetMapping("/username/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public UserDTO getUserByUsername(@PathVariable String username){
        return this.userService.getUserByUsername(username);
    }

    @GetMapping("/id/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Integer getIdByUsername(@PathVariable String username){
        return this.userService.getIdByUsername(username);
    }
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public UserEntity addProgram(@RequestBody UserEntity userEntity) {
        return this.userService.addUser(userEntity);
    }

    @PutMapping
    public UserEntity updateUser(@RequestBody UserInfoDTO userInfoDTO){
        return  this.userService.updateUserInfo(userInfoDTO);
    }
}
