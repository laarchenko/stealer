package com.example.stealer.repo;


import com.example.stealer.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ItemEntity, Long> {
}
