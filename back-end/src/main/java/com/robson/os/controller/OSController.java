package com.robson.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robson.os.dto.OSDTO;
import com.robson.os.service.OSService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/os")
public class OSController {

	@Autowired
	private OSService osService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(osService.findById(id), OSDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll() {
		List<OSDTO> listDTO = osService.findAll().stream().map(x -> mapper.map(x, OSDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<OSDTO> save(@RequestBody @Valid OSDTO obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(osService.save(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OSDTO> update(@PathVariable Integer id, @RequestBody OSDTO obj){
		obj.setId(id);
		return ResponseEntity.ok().body(mapper.map(osService.update(obj), OSDTO.class));
	}
}
