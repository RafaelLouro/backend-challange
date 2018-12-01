package com.rlouro.provider.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.rlouro.provider.dto.ProviderDTO;
import com.rlouro.provider.model.Provider;
import com.rlouro.provider.repository.ProviderRepository;
import com.rlouro.provider.service.impl.ProviderService;

@RunWith(SpringRunner.class)
public class ProviderServiceUnitTest {

	@InjectMocks
	private ProviderService providerService;

	@Mock
	private ProviderRepository providerRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final Long ID_TEST = Long.valueOf(1);

	private final String nameTest = "nameCreated";
	private final String nameUpdated = "nameUpdated";
	private final String addressTest = "addressCreated";

	private Provider provider;

	@Before
	public void setup() {
		provider = new Provider(ID_TEST, nameTest, addressTest);
	}

	@Test
	public void saveProviderValido() {
		Mockito.when(providerRepository.save(Mockito.any(Provider.class))).thenReturn(provider);

		ProviderDTO returned = providerService.save(new ProviderDTO());

		assertNotNull(returned);
		assertNotNull(returned.getId());
	}

	@Test
	public void updateProviderValido() {
		provider.setName(nameUpdated);
		Mockito.when(providerRepository.save(Mockito.any(Provider.class))).thenReturn(provider);

		ProviderDTO dto = new ProviderDTO();
		dto.setId(ID_TEST);

		ProviderDTO returned = providerService.update(dto);

		assertNotNull(returned);
		assertEquals(nameUpdated, returned.getName());
	}

	@Test
	public void updateProviderInvalido() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(ProviderService.UPDATE_PROVIDER_SEM_ID_MESSAGE);

		providerService.update(new ProviderDTO());
	}

	@Test
	public void getProviderIdValido() {
		Mockito.when(providerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(provider));

		ProviderDTO returned = providerService.findById(ID_TEST);

		assertNotNull(returned);
	}

	@Test
	public void getProviderIdInvalido() {
		Mockito.when(providerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		ProviderDTO returned = providerService.findById(ID_TEST);

		assertNull(returned);
	}

	@Test
	public void getProviderIdNulo() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(ProviderService.GET_ID_NAO_INFORMADO_MESSAGE);

		providerService.findById(null);

	}

}
