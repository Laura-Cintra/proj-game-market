package com.example.proj_game_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.proj_game_market.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long>, JpaSpecificationExecutor<Personagem> {
    // Repositório JPA para a entidade Personagem
}
