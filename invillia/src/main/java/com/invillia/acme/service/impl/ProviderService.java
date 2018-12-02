package com.invillia.acme.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.dto.ProviderDTO;
import com.invillia.acme.service.IProviderService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class ProviderService implements IProviderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProviderService.class);

	private static final String SLASH = "/";
	private static final String PROVIDER_SERVICE_NAME = "provider";
	private static final String API = "api";

	private final EurekaClient client;

	private final RestTemplateBuilder restTemplateBuilder;

	@Autowired
	public ProviderService(EurekaClient client, RestTemplateBuilder restTemplateBuilder) {
		this.client = client;
		this.restTemplateBuilder = restTemplateBuilder;
	}

	@Override
	public ResponseEntity<ProviderDTO> postProvider(ProviderDTO dto) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		StringBuilder postUrl = createUrlBuilderProviderService();

		ResponseEntity<ProviderDTO> response = restTemplate.exchange(postUrl.toString(), HttpMethod.POST,
				new HttpEntity<ProviderDTO>(dto), ProviderDTO.class);

		return response;
	}

	@Override
	public ResponseEntity<ProviderDTO> putProvider(ProviderDTO dto) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		StringBuilder putUrl = createUrlBuilderProviderService();

		ResponseEntity<ProviderDTO> response = restTemplate.exchange(putUrl.toString(), HttpMethod.PUT,
				new HttpEntity<ProviderDTO>(dto), ProviderDTO.class);

		return response;
	}

	@Override
	public ResponseEntity<ProviderDTO> getProvider(Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		StringBuilder getUrl = createUrlBuilderProviderService();
		getUrl.append(SLASH);
		getUrl.append(id.toString());

		ResponseEntity<ProviderDTO> response = restTemplate.exchange(getUrl.toString(), HttpMethod.GET, null,
				ProviderDTO.class);

		return response;
	}

	/**
	 * Cria um {@link StringBuilder} com a parte comum da URL do service de
	 * providers.
	 * 
	 * @return builder com parte comum da URL
	 */
	private StringBuilder createUrlBuilderProviderService() {
		InstanceInfo instanceInfo = client.getNextServerFromEureka(PROVIDER_SERVICE_NAME, false);

		String baseUrl = instanceInfo.getHomePageUrl();

		LOGGER.debug("URL Base: " + baseUrl);

		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append(SLASH);
		urlBuilder.append(PROVIDER_SERVICE_NAME);
		urlBuilder.append(SLASH);
		urlBuilder.append(API);

		LOGGER.debug("URL criada: " + baseUrl);

		return urlBuilder;
	}

}
