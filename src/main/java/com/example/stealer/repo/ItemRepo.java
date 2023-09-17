package com.example.stealer.repo;


import com.example.stealer.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByUrl(String url);

}
