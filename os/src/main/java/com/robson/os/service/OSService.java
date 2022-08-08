package com.robson.os.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robson.os.dto.OSDTO;
import com.robson.os.dto.TecnicoDTO;
import com.robson.os.exceptions.OrdemServicoNaoEncontrada;
import com.robson.os.model.OS;
import com.robson.os.model.Tecnico;
import com.robson.os.repository.OSRepository;

@Service
public class OSService {

	@Autowired
	private OSRepository osRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public OS findById(Integer id) {
		Optional<OS> obj = osRepository.findById(id);
		return obj.orElseThrow(() -> new OrdemServicoNaoEncontrada("Ordem de serviço não encontrada com a id: " + id + " Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll() {
		return osRepository.findAll();
	}
	
	public OS save(OSDTO obj) {
		return osRepository.save(mapper.map(obj, OS.class));
	}
	
	public OS update(OSDTO obj) {
		findById(obj.getId());
		return osRepository.save(mapper.map(obj, OS.class));
	}
}
