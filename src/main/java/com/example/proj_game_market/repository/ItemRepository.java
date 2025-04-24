package com.example.proj_game_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.proj_game_market.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item>{
    
}
