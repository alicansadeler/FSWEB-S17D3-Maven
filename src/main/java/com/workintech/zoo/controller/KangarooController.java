package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos= new HashMap<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Kangaroo> getKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Kangaroo getKangaroos(@PathVariable long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("ID bulunamadı", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping

    public Kangaroo postKangaroos(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getName() == null) {
            throw new ZooException("Kangaroo bilgileri boş olamaz.", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo putKangaroos(@PathVariable long id, @RequestBody Kangaroo kangaroo) {
        if (id <= 0) {
            throw new ZooException("ID sıfır ve negatif olamaz.", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Girilen ID'ye ait bir Kangaroo bulunamadı.", HttpStatus.NOT_FOUND);
        }
        if(kangaroo == null) {
            throw new ZooException("Kangaroo bilgileri boş olamaz.", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kangaroo> deleteKangaroos(@PathVariable long id) {
        if (id <= 0) {
            throw new ZooException("Hatalı ID", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Girilen ID'ye ait bir Kangaroo bulunamadı.", HttpStatus.NOT_FOUND);
        }
        Kangaroo deletedKangaroo = kangaroos.remove(id);
        return new ResponseEntity<>(deletedKangaroo, HttpStatus.OK);
    }

}
