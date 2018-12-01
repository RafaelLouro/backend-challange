package com.rlouro.provider.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rlouro.provider.dto.ProviderDTO;
import com.rlouro.provider.service.IProviderService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProviderController.class)
public class ProviderControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IProviderService providerService;

	private static final String ERROR_MESSAGE = "Error message";
	private static final Long ID_TEST = Long.valueOf(1);

	private final String nameTest = "nameCreated";
	private final String nameUpdated = "nameUpdated";
	private final String addressTest = "addressCreated";

	private ProviderDTO dto;

	@Before
	public void setup() {
		dto = new ProviderDTO(ID_TEST, nameTest, addressTest);
	}

	@Test
	public void saveProviderValido() throws Exception {
		Mockito.when(providerService.save(Mockito.any(ProviderDTO.class))).thenReturn(dto);

		mvc.perform(MockMvcRequestBuilders.post("/api").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"" + nameTest + "\",\"address\":\"" + addressTest + "\"}"))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.name", equalTo(dto.getName())));
	}

	@Test
	public void updateProviderValido() throws Exception {
		dto.setName(nameUpdated);
		Mockito.when(providerService.save(Mockito.any(ProviderDTO.class))).thenReturn(dto);

		mvc.perform(MockMvcRequestBuilders.put("/api").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1, \"name\":\"" + nameTest + "\",\"address\":\"" + addressTest + "\"}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(dto.getId().intValue())))
				.andExpect(jsonPath("$.name", equalTo(dto.getName())));
	}

	@Test
	public void updateProviderInvalido() throws Exception {
		Mockito.when(providerService.save(Mockito.any(ProviderDTO.class)))
				.thenThrow(new IllegalArgumentException(ERROR_MESSAGE));

		mvc.perform(MockMvcRequestBuilders.put("/api").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"" + nameTest + "\",\"address\":\"" + addressTest + "\"}"))
				.andExpect(status().isInternalServerError()).andExpect(jsonPath("$.message", notNullValue()));
	}

	@Test
	public void getProviderValido() throws Exception {
		Mockito.when(providerService.findById(Mockito.anyLong())).thenReturn(dto);

		mvc.perform(MockMvcRequestBuilders.get("/api/" + ID_TEST).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(dto.getId().intValue())))
				.andExpect(jsonPath("$.name", equalTo(dto.getName())));
	}

	@Test
	public void getProviderInvalido() throws Exception {
		Mockito.when(providerService.findById(Mockito.anyLong())).thenReturn(null);

		mvc.perform(MockMvcRequestBuilders.get("/api/" + ID_TEST).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").doesNotExist());
	}

}
