package com.komuna.komuna_backend.repository;

import com.komuna.komuna_backend.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    Optional<Tienda> findByCodigoUnico(String codigoUnico);
    Optional<Tienda> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);
    boolean existsByCodigoUnico(String codigoUnico);
}