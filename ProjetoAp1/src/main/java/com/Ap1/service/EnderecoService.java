package com.Ap1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ap1.model.Endereco;
import com.Ap1.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

// Método para listar todos os endereços do banco de dados
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

// Método para buscar um endereço específico pelo ID
    public Optional<Endereco> buscarPorId(int id) {
        return enderecoRepository.findById(id);
    }

 // Método para salvar um novo endereço no banco de dados
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

// Método para deletar um endereço pelo ID
    public void deletarPorId(int id) {
        enderecoRepository.deleteById(id);
    }
}