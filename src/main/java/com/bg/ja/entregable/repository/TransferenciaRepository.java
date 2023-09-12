package com.bg.ja.entregable.repository;

import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
