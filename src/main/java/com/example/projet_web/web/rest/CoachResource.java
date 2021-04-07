package com.example.projet_web.web.rest;

import com.example.projet_web.Model.DTO.CoachDTO;
import com.example.projet_web.Model.DTO.ObjectifDTO;
import com.example.projet_web.Model.DTO.UserDTO;
import com.example.projet_web.Model.entities.Coach;
import com.example.projet_web.Model.entities.Objectif;
import com.example.projet_web.services.implementation.CoachService;
import com.example.projet_web.services.mappers.CoachMapper;
import com.example.projet_web.services.mappers.IEntityMapper;
import com.example.projet_web.services.mappers.ObjectifMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/coach")
public class CoachResource {
    private final CoachService coachService;
    private final CoachMapper mapper;

    public CoachResource(CoachService coachService, CoachMapper mapper) {
        this.coachService = coachService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<CoachDTO> getAll()
    {
        IEntityMapper<Coach, CoachDTO> mapper = new CoachMapper();
        return coachService.readAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public CoachDTO getUserById(@PathVariable long id){
        return coachService.readOne(id).map(mapper::entityToDTO).get();
    }
}