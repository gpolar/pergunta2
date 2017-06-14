package com.pergunta2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.entity.SocioTorcedorEntity;
import com.pergunta2.fallbacks.ServicosFallBacks;
import com.pergunta2.repository.SocioTorcedorRepository;
import com.pergunta2.service.SocioTorcedorService;

/**
 * Esta classe tem a logica dos metodos implementados em
 * SocioTorcedorService
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contato@gustavopolarsa.com
 */
@Service
public class SocioTorcedorServiceImpl implements SocioTorcedorService {
	private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorServiceImpl.class);

	private final SocioTorcedorRepository repository;
	private final ServicosFallBacks servicosFallBacks;
	
	@Autowired
	SocioTorcedorServiceImpl(SocioTorcedorRepository repository,ServicosFallBacks servicosFallBacks) {
		this.repository = repository;
		this.servicosFallBacks = servicosFallBacks;
	}

	@Override
	public List<CampanhaDomain> create(SocioTorcedorDomain socioTorcedor) throws Exception {
		
		if (Objects.isNull(existeSocioTorcedor(socioTorcedor.getEmail()))) {
			SocioTorcedorEntity socio = new SocioTorcedorEntity();
			socio.setEmail(socioTorcedor.getEmail());
			socio.setDataNascimento(socioTorcedor.getDataNascimento());
			socio.setNomeCompleto(socioTorcedor.getNomeCompleto());
			socio.setTime(socioTorcedor.getTime());
			repository.save(socio);
			return new ArrayList<CampanhaDomain>();
		}else{
			servicosFallBacks.listarCampanhasAtivas();
		}
			
		throw new Exception("Sócio já cadastrado");

	}
	
	@Override
	public AssociacaoDomain associarSocioCampanha(AssociacaoDomain associacaoDomain) {
		SocioTorcedorEntity socio = repository.findOne(associacaoDomain.getSocioId());
		if (Objects.nonNull(socio)) {
			AssociacaoDomain associacao = new AssociacaoDomain(socio.getId(), associacaoDomain.getCampanhaId());
			associacaoDomain = servicosFallBacks.associarSocioCampanha(associacao);
		}
		return associacaoDomain;
	}

	@Override
	public SocioTorcedorEntity existeSocioTorcedor(String email) {
		SocioTorcedorEntity socioTorcedor = repository.findByEmail(email).orElse(null);
		return socioTorcedor;
	}

}
