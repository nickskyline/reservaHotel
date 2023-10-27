package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.Repository.ClienteRepository;
import com.ashir.reservarhotel.entities.Cliente;
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
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    public boolean actualizarClientePorCedula(String cedula, Cliente clienteActualizado) {
        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findByCedula(cedula));

        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();

            clienteExistente.setNombres(clienteActualizado.getNombres());
            clienteExistente.setApellidos(clienteActualizado.getApellidos());
            clienteExistente.setCedula(clienteActualizado.getCedula());
            clienteExistente.setDireccion(clienteActualizado.getDireccion());
            clienteExistente.setEdad(clienteActualizado.getEdad());
            clienteExistente.setEmail(clienteActualizado.getEmail());

            clienteRepository.save(clienteExistente);
            return true;
        }

        return false;
    }

    public boolean eliminarCliente(String cedula) {
        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findByCedula(cedula));

        if (clienteOptional.isPresent()) {
            clienteRepository.delete(clienteOptional.get());
            return true;
        }
        return false;
    }

}
