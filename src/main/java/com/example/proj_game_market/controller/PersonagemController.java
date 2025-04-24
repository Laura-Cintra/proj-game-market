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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.proj_game_market.model.Personagem;
import com.example.proj_game_market.repository.PersonagemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("personagem")
@Slf4j
public class PersonagemController {
    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personagem create(@RequestBody @Valid Personagem personagem) {
        log.info("Cadastrando personagem " + personagem.getNome());
        return repository.save(personagem);
    }

    @GetMapping("{id}")
    public ResponseEntity<Personagem> get(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return ResponseEntity.ok(getPersonagem(id));
    }

    // Apagar
    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando personagem " + id);
        repository.delete(getPersonagem(id));
        return ResponseEntity.noContent().build(); // o retorno de sucesso é 204
    }

    // Editar
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Personagem personagem) {
        log.info("Atualizando personagem + " + id + " " + personagem);

        getPersonagem(id); // evitando que o id seja atualizado
        personagem.setId(id); // setando o id
        repository.save(personagem); // adicionando os novos dados
        return ResponseEntity.ok(personagem);
    }

    private Personagem getPersonagem(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }
}
