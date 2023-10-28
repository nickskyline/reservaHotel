package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {

        if (clienteRepository.findByCedula(cliente.getCedula()) != null) {
            throw new RuntimeException("El cliente con la cédula proporcionada ya existe");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorCedula(String cedula) {

        Cliente cliente = clienteRepository.findByCedula(cedula);
        if (cliente == null) {
            throw new RuntimeException("El cliente con la cédula proporcionada no existe");
        }

        return cliente;
    }

    public boolean actualizarClientePorCedula(String cedula, Cliente clienteActualizado) {

        Cliente clienteExistente = clienteRepository.findByCedula(cedula);
        if (clienteExistente == null) {
            throw new RuntimeException("El cliente con la cédula proporcionada no existe");
        }

        if (clienteRepository.findByCedula(clienteExistente.getCedula()) != null) {
            throw new RuntimeException("El cliente con la cédula proporcionada ya existe");
        }


        // Actualizar los campos del cliente
        clienteExistente.setNombres(clienteActualizado.getNombres());
        clienteExistente.setApellidos(clienteActualizado.getApellidos());
        clienteExistente.setCedula(clienteActualizado.getCedula());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setEdad(clienteActualizado.getEdad());
        clienteExistente.setEmail(clienteActualizado.getEmail());

        clienteRepository.save(clienteExistente);
        return true;
    }

    public boolean eliminarCliente(String cedula) {

        Cliente cliente = clienteRepository.findByCedula(cedula);
        if (cliente == null) {
            throw new RuntimeException("El cliente con la cédula proporcionada no existe");
        }

        clienteRepository.delete(cliente);
        return true;
    }
}

