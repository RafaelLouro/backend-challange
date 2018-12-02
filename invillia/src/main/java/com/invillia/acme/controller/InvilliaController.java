package com.invillia.acme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.invillia.acme.dto.ProviderDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class InvilliaController {

	private static final String SLASH = "/";
	private static final String PROVIDER_SERVICE_NAME = "provider";
	private static final String API = "api";

	private static final Logger LOGGER = LoggerFactory.getLogger(InvilliaController.class);

	@Autowired
	private EurekaClient client;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@PostMapping(path = "/provider")
	public ResponseEntity<ProviderDTO> postProvider(@RequestBody ProviderDTO dto) {
		LOGGER.debug("Objeto recebido: " + dto.toString());

		RestTemplate restTemplate = restTemplateBuilder.build();
		StringBuilder postUrl = createUrlBuilderProviderService();

		ResponseEntity<ProviderDTO> response = restTemplate.exchange(postUrl.toString(), HttpMethod.POST,
				new HttpEntity<ProviderDTO>(dto), ProviderDTO.class);

		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			return response;
		} else {
			return new ResponseEntity<>(response.getStatusCode());
		}
	}

	@PutMapping(path = "/provider")
	public ResponseEntity<ProviderDTO> putProvider(@RequestBody ProviderDTO dto) {
		LOGGER.debug("Objeto recebido: " + dto.toString());

		RestTemplate restTemplate = restTemplateBuilder.build();
		StringBuilder putUrl = createUrlBuilderProviderService();

		ResponseEntity<ProviderDTO> response = restTemplate.exchange(putUrl.toString(), HttpMethod.PUT,
				new HttpEntity<ProviderDTO>(dto), ProviderDTO.class);

		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		} else {
			return new ResponseEntity<>(response.getStatusCode());
		}
	}

	private StringBuilder createUrlBuilderProviderService() {
		InstanceInfo instanceInfo = client.getNextServerFromEureka(PROVIDER_SERVICE_NAME, false);
		String baseUrl = instanceInfo.getHomePageUrl();

		LOGGER.debug("URL Base: " + baseUrl);

		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append(SLASH);
		urlBuilder.append(PROVIDER_SERVICE_NAME);
		urlBuilder.append(SLASH);
		urlBuilder.append(API);

		return urlBuilder;
	}

}
