package com.invillia.acme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.invillia.acme.dto.ProviderDTO;
import com.invillia.acme.service.IProviderService;

@RestController
@RequestMapping(path = "/provider")
public class InvilliaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvilliaController.class);

	private IProviderService providerService;

	@Autowired
	public InvilliaController(IProviderService providerService) {
		this.providerService = providerService;
	}

	/**
	 * Post de um novo {@link ProviderDTO}.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO}
	 */
	@PostMapping
	public ResponseEntity<ProviderDTO> postProvider(@RequestBody ProviderDTO dto) {
		LOGGER.debug("Objeto recebido: " + dto.toString());

		ResponseEntity<ProviderDTO> response = providerService.postProvider(dto);

		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			return response;
		} else {
			return new ResponseEntity<>(response.getStatusCode());
		}
	}

	/**
	 * Put de um {@link ProviderDTO} já existente.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO}
	 */
	@PutMapping
	public ResponseEntity<ProviderDTO> putProvider(@RequestBody ProviderDTO dto) {
		LOGGER.debug("Objeto recebido: " + dto.toString());

		ResponseEntity<ProviderDTO> response = providerService.putProvider(dto);

		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		} else {
			return new ResponseEntity<>(response.getStatusCode());
		}
	}

	/**
	 * Recupera um {@link ProviderDTO} pelo id.
	 * 
	 * @param id
	 * @return {@link ProviderDTO} recuperado
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProviderDTO> getProvider(@PathVariable Long id) {
		LOGGER.debug("Objeto recebido: " + id.toString());

		ResponseEntity<ProviderDTO> response = providerService.getProvider(id);

		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		} else {
			return new ResponseEntity<>(response.getStatusCode());
		}
	}

}
