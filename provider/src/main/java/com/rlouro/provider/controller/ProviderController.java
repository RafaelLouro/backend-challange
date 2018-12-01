package com.rlouro.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rlouro.provider.dto.ProviderDTO;
import com.rlouro.provider.model.Provider;
import com.rlouro.provider.service.IProviderService;

@RestController
@RequestMapping(path = "/api")
public class ProviderController {

	private final IProviderService providerService;

	@Autowired
	public ProviderController(IProviderService providerService) {
		this.providerService = providerService;
	}

	/**
	 * Salva um novo {@link Provider}.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO} do {@link Provider} salvo
	 */
	@PostMapping
	public ResponseEntity<ProviderDTO> save(@RequestBody ProviderDTO dto) {
		return new ResponseEntity<>(providerService.save(dto), HttpStatus.CREATED);
	}

	/**
	 * Atualiza um {@link Provider}.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO} do {@link Provider} atualizado
	 */
	@PutMapping
	public ResponseEntity<ProviderDTO> update(@RequestBody ProviderDTO dto) {
		return new ResponseEntity<>(providerService.save(dto), HttpStatus.OK);
	}

	/**
	 * Recupera um {@link Provider} pelo id.
	 * 
	 * @param id
	 * @return {@link ProviderDTO} recuperado
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProviderDTO> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
	}

}
