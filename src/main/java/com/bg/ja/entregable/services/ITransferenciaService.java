package com.bg.ja.entregable.services;

import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.model.Transferencia;

import java.util.List;

public interface ITransferenciaService {

    public Transferencia save(Transferencia transferencia) throws Exception;

    public Transferencia update(Transferencia transferencia) throws Exception;

    public Transferencia findById(Long id) throws Exception;

    List<Transferencia> findAll() throws Exception;


}
