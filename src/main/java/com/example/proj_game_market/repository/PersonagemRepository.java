package com.example.proj_game_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proj_game_market.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    // Repositório JPA para a entidade Personagem
}
