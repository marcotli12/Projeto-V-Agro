package com.example.demo.repository;

import com.example.demo.model.Muda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MudaRepository extends JpaRepository<Muda, Integer> {

}