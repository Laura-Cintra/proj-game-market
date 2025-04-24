package com.example.proj_game_market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.proj_game_market.model.Item;
import com.example.proj_game_market.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> index() {
        return itemRepository.findAll();
    } 

    @PostMapping
    public Item create(@RequestBody @Valid Item item){
        log.info("Cadastrando item " + item.getNome());
        return itemRepository.save(item);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> get(@PathVariable Long id){
        log.info("Buscando item " + id);
        return ResponseEntity.ok(getItem(id));
    } 

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("Apagando item: " + id);

        itemRepository.delete(getItem(id));
        return ResponseEntity.noContent().build(); 
    } 

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable @Valid Long id, @RequestBody Item item) {
        log.info("Atualizando categoria + " + id + " com " + item);

        getItem(id);
        item.setId(id); 
        itemRepository.save(item); 
        return ResponseEntity.ok(item);
    }


    //pega o primeiro, ou senão lança uma exceção
    private Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

}
