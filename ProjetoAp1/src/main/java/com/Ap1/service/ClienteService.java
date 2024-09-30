package com.Ap1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ap1.model.Cliente;
import com.Ap1.model.Endereco;
import com.Ap1.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;



@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepositorio;

    // Método para listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }


     // Método para buscar um cliente pelo ID
    public Optional<Cliente> buscarPorId(int id) {
        return clienteRepositorio.findById(id);
    }


    // Método para salvar um cliente no banco de dados, após validar se o cliente é único
    public Cliente salvar(Cliente cliente) {
        validarClienteUnico(cliente);
        return clienteRepositorio.save(cliente);
    }


    // Validação para garantir que o cliente é único (CPF, email e telefone não repetidos)
    private void validarClienteUnico(Cliente cliente) {
        Optional<Cliente> clienteExistentePorCpf = clienteRepositorio.findByCpf(cliente.getCpf());
        Optional<Cliente> clienteExistentePorEmail = clienteRepositorio.findByEmail(cliente.getEmail());
        Optional<Cliente> clienteExistentePorTelefone = clienteRepositorio.findByTelefone(cliente.getTelefone());

    // Lança exceção caso um cliente com o mesmo CPF já exista
        if (clienteExistentePorCpf.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF.");
        }
    // Lança exceção caso um cliente com o mesmo email já exista
        if (clienteExistentePorEmail.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com este e-mail.");
        }
     // Lança exceção caso um cliente com o mesmo telefone já exista
        if (clienteExistentePorTelefone.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com este telefone.");
        }
    }
    // Método para atualizar as informações de um cliente já existente
    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(id);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado.");
        }

    // Atualiza os dados do cliente existente com as informações fornecidas
        Cliente cliente = clienteExistente.get();
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        
        return clienteRepositorio.save(cliente);
    }


    // Método para adicionar um novo endereço a um cliente
    public Cliente adicionarEndereco(int clienteId, Endereco novoEndereco) {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }
    

    // Adiciona o novo endereço à lista de endereços do cliente
        Cliente cliente = clienteExistente.get();
        cliente.getEnderecos().add(novoEndereco);
        return clienteRepositorio.save(cliente);
    }


    // Método para atualizar um endereço de um cliente
    public Cliente atualizarEndereco(int clienteId, int enderecoId, Endereco enderecoAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }
    
        Cliente cliente = clienteExistente.get();

     // Encontra o endereço a ser atualizado
        Endereco endereco = cliente.getEnderecos().stream()
            .filter(e -> e.getId() == (enderecoId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço com ID " + enderecoId + " não encontrado."));
    

     // Atualiza os campos do endereço
        endereco.setRua(enderecoAtualizado.getRua());
        endereco.setNumero(enderecoAtualizado.getNumero());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setEstado(enderecoAtualizado.getEstado());
        endereco.setCep(enderecoAtualizado.getCep());
    
        return clienteRepositorio.save(cliente);
    }

    // Método para remover um endereço de um cliente
    public Cliente removerEndereco(int clienteId, int enderecoId) {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }
    
        Cliente cliente = clienteExistente.get();

    // Encontra o endereço a ser removido
        Endereco endereco = cliente.getEnderecos().stream()
            .filter(e -> e.getId() == (enderecoId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço com ID " + enderecoId + " não encontrado."));
    
    // Remove o endereço da lista de endereços do cliente
        cliente.getEnderecos().remove(endereco);
        return clienteRepositorio.save(cliente);
    }
    
     // Método para buscar um cliente com seus endereços
    public Cliente buscarClienteComEnderecos(int clienteId) {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }
    
        return clienteExistente.get();
    }
    
    // Método para deletar um cliente pelo ID
    public void deletarPorId(int id) {
        clienteRepositorio.deleteById(id);
    }
}