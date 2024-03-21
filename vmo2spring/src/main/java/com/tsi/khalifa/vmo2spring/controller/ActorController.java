package com.tsi.khalifa.vmo2spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tsi.khalifa.vmo2spring.entity.Actor;
import com.tsi.khalifa.vmo2spring.repository.ActorRepository;

@RestController
@RequestMapping("/actors")
@CrossOrigin
public class ActorController {

    @Autowired
    private ActorRepository actorRep;

    @GetMapping("/allActors")
    public Iterable<Actor> getAllActors() {
        return actorRep.findAll();
    }
}
