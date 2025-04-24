package com.example.proj_game_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proj_game_market.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Long>{
    
}
