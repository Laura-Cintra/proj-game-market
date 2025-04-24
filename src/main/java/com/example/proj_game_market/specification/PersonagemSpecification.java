package com.example.proj_game_market.specification;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.example.proj_game_market.controller.PersonagemController.PersonagemFilters;
import com.example.proj_game_market.model.Personagem;

import jakarta.persistence.criteria.Predicate;

public class PersonagemSpecification {
    public static Specification<Personagem> withFilters(PersonagemFilters filters) { // recebe os filtros e transforma
                                                                                     // em uma espeficicação pro BD
        return (root, query, cb) -> {

            var predicates = new ArrayList<>();

            // busca personagens por nome
            if (filters.nome() != null) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nome")), "%" + filters.nome().toLowerCase() + "%"));
            }

            // busca personagens por classe
            if (filters.type() != null) {
                predicates.add(
                        cb.like(cb.toString(root.get("type")), filters.type().toString()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
