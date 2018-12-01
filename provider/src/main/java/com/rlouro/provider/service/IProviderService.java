package com.rlouro.provider.service;

import com.rlouro.provider.dto.ProviderDTO;
import com.rlouro.provider.model.Provider;

public interface IProviderService {

	/**
	 * Salva um novo {@link Provider}.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO} do {@link Provider} salvo
	 */
	ProviderDTO save(ProviderDTO dto);
	
	/**
	 * Atualiza um {@link Provider}.
	 * 
	 * @param dto
	 * @return {@link ProviderDTO} do {@link Provider} atualizado
	 */
	ProviderDTO update(ProviderDTO dto);

	/**
	 * Recupera um {@link Provider} pelo id.
	 * 
	 * @param id
	 * @return {@link ProviderDTO} recuperado
	 */
	ProviderDTO findById(Long id);
}
