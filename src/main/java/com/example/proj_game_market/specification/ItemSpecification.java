package com.example.proj_game_market.specification;

import java.util.ArrayList;


import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import com.example.proj_game_market.controller.ItemController.ItemFilters;
import com.example.proj_game_market.model.Item;

public class ItemSpecification {
    public static Specification<Item> withFilters(ItemFilters filters){
         return (root, query, cb) -> {

            var predicates = new ArrayList<>(); 

            // busca o item pelo nome
            if(filters.nome() != null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nome")), "%" + filters.nome().toLowerCase() + "%")
                );
            }

            // busca o item em uma faixa de preço (min - first e max - end)
            if (filters.firstPrice() != null && filters.endPrice() != null) {
                predicates.add(
                        cb.between(root.get("preco"), filters.firstPrice(), filters.endPrice())
                );
            }

            // busca por tipo do item 
            if (filters.tipo() != null) {
                predicates.add(
                        cb.like(cb.toString(root.get("tipo")), filters.tipo().toString()));
            }
            
            // busca por raridade do item
            if (filters.raridade() != null) {
                predicates.add(
                        cb.like(cb.toString(root.get("raridade")), filters.raridade().toString()));
            }
            

            var arrayPredicates = predicates.toArray(new Predicate[0]); // pega todos os predicados adicionados no array e jogo em um array de predicados
            return cb.and(arrayPredicates); 
        };
    }
}
