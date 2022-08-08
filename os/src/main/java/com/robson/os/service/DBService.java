package com.robson.os.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.robson.os.enums.Prioridade;
import com.robson.os.enums.Status;
import com.robson.os.model.Cliente;
import com.robson.os.model.OS;
import com.robson.os.model.Tecnico;
import com.robson.os.repository.ClienteRepository;
import com.robson.os.repository.OSRepository;
import com.robson.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Robson", "781.294.310-09", "819870194");
		Cliente c1 = new Cliente(null, "Valdir", "345.526.380-17", "819899488");

		OS o1 = new OS(null, LocalDateTime.now(), LocalDateTime.now(), Prioridade.ALTA, "Entregue", Status.ANDAMENTO,
				t1, c1);

		t1.getList().add(o1);
		c1.getList().add(o1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));

		osRepository.saveAll(Arrays.asList(o1));
	}
}
