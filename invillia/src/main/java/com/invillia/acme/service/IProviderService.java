package com.invillia.acme.service;

import org.springframework.http.ResponseEntity;

import com.invillia.acme.dto.ProviderDTO;

public interface IProviderService {

	/**
	 * Post de um novo {@link ProviderDTO}.
	 * 
	 * @param dto
	 * @return {@link ResponseEntity}
	 */
	ResponseEntity<ProviderDTO> postProvider(ProviderDTO dto);

	/**
	 * Put de um {@link ProviderDTO} já existente.
	 * 
	 * @param dto
	 * @return {@link ResponseEntity}
	 */
	ResponseEntity<ProviderDTO> putProvider(ProviderDTO dto);

	/**
	 * Recupera um {@link ProviderDTO} pelo id.
	 * 
	 * @param id
	 * @return {@link ResponseEntity} com objeto recuperado
	 */
	ResponseEntity<ProviderDTO> getProvider(Long id);
}
