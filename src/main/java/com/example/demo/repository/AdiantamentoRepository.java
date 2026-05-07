package com.example.demo.repository;

import com.example.demo.model.Adiantamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdiantamentoRepository extends JpaRepository<Adiantamento, Long> {

    List<Adiantamento> findByIdResponsavel(Integer idResponsavel);
    
    List<Adiantamento> findByUsuarioNomeContainingIgnoreCase(String usuarioNome);
}