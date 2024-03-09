package org.unibl.etf.onlinefitness.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.MessageDTO;
import org.unibl.etf.onlinefitness.models.dto.MessageRequestDTO;
import org.unibl.etf.onlinefitness.services.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public MessageDTO sendMessage(@RequestBody MessageRequestDTO messageRequestDTO){
        return messageService.sendMessage(messageRequestDTO);
    }

    @GetMapping("/{id}")
    public List<MessageDTO> getUserMessages(@PathVariable Integer id){
        return messageService.findUserMessages(id);
    }
}
