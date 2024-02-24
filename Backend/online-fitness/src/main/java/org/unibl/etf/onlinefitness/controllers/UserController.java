package org.unibl.etf.onlinefitness.controllers;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.dto.UserDTO;
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
    public UserDTO findProgramByProgramId(@PathVariable Integer id){
        return this.userService.getUserByUserId(id);
    }
}
