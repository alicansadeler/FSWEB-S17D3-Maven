package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas= new HashMap<>();
    }

    @GetMapping
    public List<Koala> getKoalas() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalas(@PathVariable long id) {
        return koalas.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Koala postKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala putKoala(@PathVariable long id, @RequestBody Koala koala) {
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable long id) {
        koalas.remove(id);
    }


}
