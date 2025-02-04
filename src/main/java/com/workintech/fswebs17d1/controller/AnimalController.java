package com.workintech.fswebs17d1.controller;
import com.workintech.fswebs17d1.entity.Animal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals = new HashMap<>();

    public AnimalController() {

        animals.put(1, new Animal(1, "maymun"));
        animals.put(2, new Animal(2, "kedi"));
        animals.put(3, new Animal(3, "köpek"));
    }


    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Animal> returnAnimalById(@PathVariable int id) {
        Animal animal = animals.get(id);
        if (animal == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found döndür
        }
        return ResponseEntity.ok(animal); // 200 OK ve bulunan hayvanı döndür
    }


    @PostMapping
    public String addNewAnimal(@RequestBody Animal newAnimal) {
        if (animals.containsKey(newAnimal.getId())) {
            return "Bu ID zaten mevcut!";
        }
        animals.put(newAnimal.getId(), newAnimal);
        return "Hayvan eklendi: " + newAnimal.getName();
    }


    @PutMapping("/{id}")
    public String updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (!animals.containsKey(id)) {
            return "Bu ID'ye sahip hayvan bulunamadı!";
        }
        updatedAnimal.setId(id);
        animals.put(id, updatedAnimal);
        return "Hayvan güncellendi: " + updatedAnimal.getName();
    }


    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable int id) {
        if (!animals.containsKey(id)) {
            return "Bu ID'ye sahip hayvan bulunamadı!";
        }
        animals.remove(id);
        return "Hayvan silindi.";
    }
}

