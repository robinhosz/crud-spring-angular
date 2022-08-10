package com.robson.os.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robson.os.dto.TecnicoDTO;
import com.robson.os.exceptions.DataIntegrityViolationException;
import com.robson.os.exceptions.TecnicoNaoEncontrado;
import com.robson.os.model.Pessoa;
import com.robson.os.model.Tecnico;
import com.robson.os.repository.PessoaRepository;
import com.robson.os.repository.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new TecnicoNaoEncontrado("O tecnico com a id " + id + " não foi encontrado " + " Tipo: " + Tecnico.class.getName()));
	}
	
	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico save(TecnicoDTO obj) {
		findByCpf(obj);
		return tecnicoRepository.save(mapper.map(obj, Tecnico.class));
	}
	
	public Tecnico update(TecnicoDTO obj) {
		findByCpf(obj);
		return tecnicoRepository.save(mapper.map(obj, Tecnico.class));
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico possui ordens de serviço, não pode ser deletado.");
		}
		tecnicoRepository.deleteById(id);

	}
	
	private void findByCpf(TecnicoDTO objDto) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(objDto.getCpf());
		
		if (pessoa.isPresent() && !pessoa.get().getId().equals(objDto.getId())) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");

		}
	}
}
