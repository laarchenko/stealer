package com.example.stealer.repo;

import com.example.stealer.entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @NotNull
    Optional<UserEntity> findById(@NotNull Long id);
}
