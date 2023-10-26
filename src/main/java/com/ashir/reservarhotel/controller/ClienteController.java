package com.ashir.reservarhotel.controller;


import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>("Cliente creado con exito", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Cliente> buscarPorNumeroDocumento(@RequestParam("cedula") String cedula) {
        Cliente cliente = clienteService.obtenerClientePorCedula(cedula);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCliente(@RequestParam("cedula") String cedula, @RequestBody Cliente clienteActualizado) {
        boolean actualizado = clienteService.actualizarClientePorCedula(cedula, clienteActualizado);

        if (actualizado) {
            return new ResponseEntity<>("Cliente actualizado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> actualizarCliente(@RequestParam("cedula") String cedula) {
        boolean eliminado = clienteService.eliminarCliente(cedula);

        if (eliminado) {
            return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }


}
