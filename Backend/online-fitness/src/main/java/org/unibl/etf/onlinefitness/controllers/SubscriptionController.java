package org.unibl.etf.onlinefitness.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.SubscriptionDTO;
import org.unibl.etf.onlinefitness.services.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<SubscriptionDTO> getAllByUserId(@PathVariable Integer userId){
        return subscriptionService.getAllByUserId(userId);
    }

    @PostMapping("/{categoryId}/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void subscribeOrUnsubscribe(@PathVariable Integer categoryId,@PathVariable Integer userId){
        subscriptionService.subscribeOrUnsubscribe(categoryId,userId);
    }
}
