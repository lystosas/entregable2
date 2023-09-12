package com.bg.ja.entregable.services;

import com.bg.ja.entregable.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ICuentaService {

    public Cuenta save(Cuenta cuenta) throws Exception;

    public Cuenta update(Cuenta cuenta) throws Exception;

    public void delete(String numeroCuenta) throws Exception;

    public Cuenta findByNumberCuenta(String cuenta) throws Exception;

    List<Cuenta> findAll() throws Exception;

}
