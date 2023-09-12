package com.bg.ja.entregable.services;

import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.model.Transferencia;
import com.bg.ja.entregable.repository.CuentaRepository;
import com.bg.ja.entregable.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService implements ITransferenciaService{
    @Autowired
    TransferenciaRepository repositoryT;

    @Autowired
    CuentaRepository repositoryC;

    @Autowired
    CuentaService serviceC;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Transferencia save(Transferencia transferencia) throws Exception {

        Optional<Cuenta> cOrigen = repositoryC.findByNumeroCuenta(transferencia.getCuentaOrigen().getNumeroCuenta());

        Optional<Cuenta> cDestino= repositoryC.findByNumeroCuenta(transferencia.getCuentaDestino().getNumeroCuenta());

        if(cOrigen.get().getSaldo().subtract(transferencia.getMonto()).compareTo(BigDecimal.ZERO) < 0 ){
            throw new Exception("El saldo no puede ser negativo");
        }

        if(cDestino.isPresent()){
            Cuenta actualizaO = cOrigen.get();
            Cuenta actualizaD = cDestino.get();
            actualizaO.setSaldo(actualizaO.getSaldo().subtract(transferencia.getMonto()));
            actualizaD.setSaldo(actualizaD.getSaldo().add(transferencia.getMonto()));

            serviceC.update(actualizaO);
            serviceC.update(actualizaD);



            return repositoryT.save(transferencia);
        }else{
            throw new Exception("paso por aquio");
        }

    }

    @Override
    public Transferencia update(Transferencia transferencia) throws Exception {



        return null;
    }

    @Override
    public Transferencia findById(Long id) throws Exception {
        return repositoryT.findById(id).orElse(null);
    }

    @Override
    public List<Transferencia> findAll() throws Exception {
        return repositoryT.findAll();
    }

    public void actualizarSados(Transferencia transferencia){
        String o = String.valueOf(transferencia.getCuentaOrigen().getId());
        String d = String.valueOf(transferencia.getCuentaDestino().getNumeroCuenta());
        BigDecimal saldoO = transferencia.getCuentaOrigen().getSaldo();
        BigDecimal saldoD = transferencia.getCuentaDestino().getSaldo();
        BigDecimal monto = transferencia.getMonto();

        BigDecimal nuevoSaldoOrigen = saldoO.subtract(monto);
        BigDecimal nuevoSaldoDestino = saldoD.add(monto);




        System.out.println("Id Origen: " + o + " Id Destino: " + d);
        System.out.println("Saldo Origen: " + saldoO + " Saldo Destino: " + saldoD);
        System.out.println("Nuevo Saldo Origen: " + nuevoSaldoOrigen + " Nuevo Saldo Destino: " + nuevoSaldoDestino);

    }
}
