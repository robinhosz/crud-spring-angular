package com.robson.os.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robson.os.dto.ClienteDTO;
import com.robson.os.exceptions.ClienteNaoEncontrado;
import com.robson.os.exceptions.DataIntegrityViolationException;
import com.robson.os.model.Cliente;
import com.robson.os.model.Pessoa;
import com.robson.os.repository.ClienteRepository;
import com.robson.os.repository.PessoaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ClienteNaoEncontrado("O tecnico com a id " + id + " não foi encontrado " + " Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente save(ClienteDTO obj) {
		findByCpf(obj);
		return clienteRepository.save(mapper.map(obj, Cliente.class));
	}
	
	public Cliente update(ClienteDTO obj) {
		findByCpf(obj);
		return clienteRepository.save(mapper.map(obj, Cliente.class));
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço, não pode ser deletado.");
		}
		clienteRepository.deleteById(id);

	}
	
	private void findByCpf(ClienteDTO objDto) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(objDto.getCpf());
		
		if (pessoa.isPresent() && !pessoa.get().getId().equals(objDto.getId())) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");

		}
	}
}
