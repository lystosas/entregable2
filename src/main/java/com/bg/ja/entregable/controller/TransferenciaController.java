package com.bg.ja.entregable.controller;

import com.bg.ja.entregable.controller.model.ApiResponseMessage;
import com.bg.ja.entregable.controller.model.HttpStatusMessages;
import com.bg.ja.entregable.model.Cuenta;
import com.bg.ja.entregable.model.Transferencia;
import com.bg.ja.entregable.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    private final TransferenciaService service;

    @Autowired
    public TransferenciaController(TransferenciaService service){
        this.service = service;
    }
    @GetMapping
    public  ResponseEntity<?> findAll(){
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

    /*@GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> obtenerTransferenciaPorId(@PathVariable String numeroCuenta) throws Exception {
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
    }*/

    @PostMapping
    public ResponseEntity<?> crearTransferencia(@RequestBody Transferencia transferencia) {
        try {
            System.out.println("dataTry: " + transferencia);
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.save(transferencia)).build());


        } catch (Exception e) {
            System.out.println("dataCatch: " + transferencia);
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarTransferencia(@RequestBody Transferencia transferencia) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(service.update(transferencia)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }
}

