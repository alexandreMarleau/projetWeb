package com.example.projet_web.web.rest;

import com.example.projet_web.Model.DTO.CategoryDTO;
import com.example.projet_web.Model.DTO.ObjectifDTO;
import com.example.projet_web.Model.DTO.UserDTO;
import com.example.projet_web.Model.entities.Category;
import com.example.projet_web.Model.entities.Objectif;
import com.example.projet_web.Model.entities.User;
import com.example.projet_web.services.implementation.ObjectifService;
import com.example.projet_web.services.mappers.IEntityMapper;
import com.example.projet_web.services.mappers.ObjectifMapper;
import com.example.projet_web.services.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/objectif")
public class ObjectifResource {
    private final ObjectifService objectifService;
    private final ObjectifMapper mapper;

    public ObjectifResource(ObjectifService objectifService, ObjectifMapper mapper) {
        this.objectifService = objectifService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<ObjectifDTO> getAll()
    {
        IEntityMapper<Objectif, ObjectifDTO> mapper = new ObjectifMapper();
        return objectifService.readAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getObjectifById(@PathVariable long id)
    {
        Optional<Objectif> objectif = objectifService.readOne(id);
        Optional<ObjectifDTO> categoryDTO = objectif.map(mapper::entityToDTO);
        return categoryDTO.isPresent() ? new ResponseEntity<>(categoryDTO.get(),
                HttpStatus.OK) : new ResponseEntity<>("Objectif Id not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/most-recent")
    public List<ObjectifDTO> getObjectifByMostRecent(){
        IEntityMapper<Objectif, ObjectifDTO> mapper = new ObjectifMapper();
        return objectifService.findObjectifSortByMostRecent().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/accomplished")
    public List<ObjectifDTO> getAccomplished() {
        IEntityMapper<Objectif, ObjectifDTO> mapper = new ObjectifMapper();
        return objectifService.findObjectifAccomplished().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Long countAll(){
        return objectifService.countAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ObjectifDTO> save(@RequestBody @Valid ObjectifDTO objectif) {
        Objectif saved = this.objectifService.save(objectif);
        IEntityMapper<Objectif, ObjectifDTO> mapper = new ObjectifMapper();
        return new ResponseEntity<ObjectifDTO>(mapper.entityToDTO(saved), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.objectifService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/edit")
    public ResponseEntity<Void> update(@RequestBody @Valid ObjectifDTO objectif) {
        this.objectifService.update(objectif);
        return ResponseEntity.noContent().build();
    }



}
