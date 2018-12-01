package com.rlouro.provider.service.impl;

import java.util.Optional;

import org.apache.commons.lang.Validate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlouro.provider.dto.ProviderDTO;
import com.rlouro.provider.model.Provider;
import com.rlouro.provider.repository.ProviderRepository;
import com.rlouro.provider.service.IProviderService;

@Service
public class ProviderService implements IProviderService {
	
	public static final String UPDATE_PROVIDER_SEM_ID_MESSAGE = "Um provider sem id não pode ser atualizado.";
	public static final String GET_ID_NAO_INFORMADO_MESSAGE = "O id deve ser informado.";

	private final ModelMapper modelMapper;

	private final ProviderRepository providerRepository;

	@Autowired
	public ProviderService(ProviderRepository providerRepository) {
		this.modelMapper = new ModelMapper();
		this.providerRepository = providerRepository;
	}

	@Override
	public ProviderDTO save(ProviderDTO dto) {
		Provider entity = modelMapper.map(dto, Provider.class);
		entity = providerRepository.save(entity);

		return modelMapper.map(entity, ProviderDTO.class);
	}
	
	@Override
	public ProviderDTO update(ProviderDTO dto) {
		Validate.notNull(dto.getId(), UPDATE_PROVIDER_SEM_ID_MESSAGE);
		
		return save(dto);
	}

	@Override
	public ProviderDTO findById(Long id) {
		Validate.notNull(id, GET_ID_NAO_INFORMADO_MESSAGE);
		
		Optional<Provider> opEntity = providerRepository.findById(id);

		if (opEntity.isPresent()) {
			return modelMapper.map(opEntity.get(), ProviderDTO.class);
		} else {
			return null;
		}
	}

}
