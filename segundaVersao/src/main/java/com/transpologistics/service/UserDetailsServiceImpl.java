package com.transpologistics.service;

import com.transpologistics.repository.ClienteRepository;
import com.transpologistics.repository.MotoristaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final MotoristaRepository motoristaRepository;

    public UserDetailsServiceImpl(ClienteRepository clienteRepository, MotoristaRepository motoristaRepository) {
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clienteRepository.findByCnpj(username)
                .map(cliente -> new User(cliente.getCnpj(), cliente.getPassword(), new ArrayList<>()))
                .orElseGet(() -> motoristaRepository.findByCpf(username)
                        .map(motorista -> new User(motorista.getCpf(), motorista.getPassword(), new ArrayList<>()))
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));
    }
}
