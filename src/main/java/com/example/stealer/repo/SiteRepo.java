package com.example.stealer.repo;

import com.example.stealer.entity.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepo extends JpaRepository<SiteEntity, Long> {
}
