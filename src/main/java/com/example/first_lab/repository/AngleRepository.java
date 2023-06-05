package com.example.first_lab.repository;

import com.example.first_lab.entity.AngleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngleRepository extends JpaRepository<AngleEntity, Double> {
}
