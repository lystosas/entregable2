package com.bg.ja.entregable.controller;

import com.bg.ja.entregable.controller.model.ApiResponseMessage;
import com.bg.ja.entregable.controller.model.HttpStatusMessages;
import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService service;

    @Autowired
    public CuentaController(CuentaService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.findAll()).build());



        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }

    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> obtenerCuentaPorId(@PathVariable String numeroCuenta) throws Exception {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.findByNumberCuenta(numeroCuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.save(cuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarCuenta(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.update(cuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @DeleteMapping("/{numeroDeCuenta}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable String numeroDeCuenta) {
        try {
            service.delete(numeroDeCuenta);
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(null).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(null).build());
        }
    }

}
