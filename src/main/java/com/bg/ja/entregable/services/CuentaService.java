package com.bg.ja.entregable.services;

import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    @Autowired
    CuentaRepository repository;

    @Override
    public Cuenta save(Cuenta cuenta) throws Exception {

        Optional<Cuenta> cuentaExistente = repository.findByNumeroCuenta(cuenta.getNumeroCuenta());

        if(cuentaExistente.isPresent()){
            throw new Exception("El numero de Cuenta: " + cuenta.getNumeroCuenta()+ " ya existe");
        }

        if(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("El saldo no pùede ser negativo");
        }
        return repository.save(cuenta);
    }

    @Override
    public Cuenta update(Cuenta cuenta) throws Exception {

        Optional<Cuenta> cuentaExistente = repository.findByNumeroCuenta(cuenta.getNumeroCuenta());

        if(cuentaExistente.isPresent()){
            Cuenta actualizaCuenta = cuentaExistente.get();
            actualizaCuenta.setSaldo(cuenta.getSaldo());
            return repository.save(cuenta);
        }else {
         throw new Exception("No existe el numero de cuenta: "+ cuenta.getNumeroCuenta());
        }
    }

    @Override
    @Transactional
    public void delete(String numeroCuenta) throws Exception {
        Optional<Cuenta> cuentaExiste = repository.findByNumeroCuenta(numeroCuenta);

        if(!cuentaExiste.isPresent()){
            throw new Exception("No existe el numero de cuenta: ");
        }

        if(cuentaExiste.get().getSaldo().compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("Aun no normaliza las obligaciones" + cuentaExiste.get().getSaldo());
        }

        repository.deleteByNumeroCuenta(numeroCuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findByNumberCuenta(String cuenta) throws Exception {
        return repository.findByNumeroCuenta(cuenta).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAll() throws Exception {
        return repository.findAll();
    }
}