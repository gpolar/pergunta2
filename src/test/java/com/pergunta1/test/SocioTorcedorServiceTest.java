package com.pergunta1.test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.entity.SocioTorcedorEntity;
import com.pergunta2.fallbacks.ServicosFallBacks;
import com.pergunta2.repository.SocioTorcedorRepository;
import com.pergunta2.service.impl.SocioTorcedorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SocioTorcedorServiceTest {

	@InjectMocks
	SocioTorcedorServiceImpl socioTorcedorServiceImpl;
	
	@Mock
	SocioTorcedorRepository socioTorcedorRepository;
	
	@Mock
	ServicosFallBacks servicosFallBacks;
	
	@Test
	public void createTest(){
		SocioTorcedorDomain socioTorcedorDomain = new SocioTorcedorDomain("Gustavo","gpolar@gmail.com",LocalDate.now(),5);
		SocioTorcedorEntity socioTorcedorEntity = new SocioTorcedorEntity();
		List<CampanhaDomain> campanhaDomain = new ArrayList<>();
		campanhaDomain.add(new CampanhaDomain("abc", "campanha1", 4, LocalDate.now()));
		
		when(socioTorcedorRepository.findByEmail(Matchers.anyString())).thenReturn(Optional.of(socioTorcedorEntity));
		when(servicosFallBacks.listarAssociados(Matchers.anyString())).thenReturn(null);
		when(servicosFallBacks.listarCampanhasAtivas()).thenReturn(campanhaDomain);
		
		socioTorcedorServiceImpl.create(socioTorcedorDomain);
		
		verify(socioTorcedorRepository, times(1)).findByEmail(Matchers.anyString());
		verify(servicosFallBacks, times(1)).listarAssociados(Matchers.anyString());
		verify(servicosFallBacks, times(1)).listarCampanhasAtivas();
		
	}
	
	@Test
	public void associarSocioCampanhaTest(){
		AssociacaoDomain associacaoDomain = new AssociacaoDomain("234234","23423");
		SocioTorcedorEntity socioTorcedorEntity = new SocioTorcedorEntity();
		
		when(socioTorcedorRepository.findOne(Matchers.anyString())).thenReturn(socioTorcedorEntity);
		when(servicosFallBacks.associarSocioCampanha(Matchers.any(AssociacaoDomain.class))).thenReturn(associacaoDomain);

		socioTorcedorServiceImpl.associarSocioCampanha(associacaoDomain);
		
		verify(socioTorcedorRepository, times(1)).findOne(Matchers.anyString());
		verify(servicosFallBacks, times(1)).associarSocioCampanha(Matchers.any(AssociacaoDomain.class));
		
	}
	
	@Test
	public void existeSocioTorcedorTest(){
		SocioTorcedorEntity socioTorcedorEntity = new SocioTorcedorEntity();
		
		when(socioTorcedorRepository.findByEmail(Matchers.anyString())).thenReturn(Optional.of(socioTorcedorEntity));
		
		socioTorcedorServiceImpl.existeSocioTorcedor("gpolars@gmail.com");
		
		verify(socioTorcedorRepository, times(1)).findByEmail(Matchers.anyString());
		
	}
}
