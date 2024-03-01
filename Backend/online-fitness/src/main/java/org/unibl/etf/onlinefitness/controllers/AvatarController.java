package org.unibl.etf.onlinefitness.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.onlinefitness.services.AvatarService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService){
        this.avatarService = avatarService;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer uploadAvatar(@RequestParam("avatar")MultipartFile file,@PathVariable Integer userId) throws IOException{
        return this.avatarService.uploadAvatar(file,userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadAvatar(@PathVariable Integer id) throws IOException{
        var avatar = avatarService.downloadAvatar(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(avatar.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + avatar.getName() + "\"")
                .body(avatar.getData());
    }

}
