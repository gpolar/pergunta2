package com.pergunta2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.domain.BaseSocioTorcedorDomain;
import com.pergunta2.entity.SocioTorcedorEntity;
import com.pergunta2.exception.http.UnprocessableEntity;
import com.pergunta2.fallbacks.ServicosFallBacks;
import com.pergunta2.repository.SocioTorcedorRepository;
import com.pergunta2.service.SocioTorcedorService;

/**
 * Esta classe tem a logica dos metodos implementados em SocioTorcedorService
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contact@gustavopolarsa.com
 */
@Service
public class SocioTorcedorServiceImpl implements SocioTorcedorService {

	private final SocioTorcedorRepository repository;
	private final ServicosFallBacks servicosFallBacks;

	@Autowired
	SocioTorcedorServiceImpl(SocioTorcedorRepository repository, ServicosFallBacks servicosFallBacks) {
		this.repository = repository;
		this.servicosFallBacks = servicosFallBacks;
	}

	@Override
	public List<CampanhaDomain> create(BaseSocioTorcedorDomain socioTorcedor) {

		SocioTorcedorEntity socioEntity = existeSocioTorcedor(socioTorcedor.getEmail());

		if (Objects.isNull(socioEntity)) {
			SocioTorcedorEntity socio = new SocioTorcedorEntity();
			socio.setEmail(socioTorcedor.getEmail());
			socio.setDataNascimento(socioTorcedor.getDataNascimento());
			socio.setNomeCompleto(socioTorcedor.getNomeCompleto());
			socio.setTime(socioTorcedor.getTime());
			repository.save(socio);
			return new ArrayList<CampanhaDomain>();
		}

		List<AssociacaoDomain> associados = servicosFallBacks.listarAssociados(socioEntity.getId());
		if (Objects.isNull(associados) || associados.size() == 0) {
			List<CampanhaDomain> lista = servicosFallBacks.listarCampanhasAtivas();
			if (Objects.isNull(lista) || lista.size() == 0)
				throw new UnprocessableEntity("Nao existe campanhas ativas");
			return lista;
		}

		throw new UnprocessableEntity("Socio já registrado");

	}

	@Override
	public AssociacaoDomain associarSocioCampanha(AssociacaoDomain associacaoDomain) {
		SocioTorcedorEntity socio = repository.findOne(associacaoDomain.getSocioId());
		if (Objects.nonNull(socio)) {
			AssociacaoDomain associacao = new AssociacaoDomain(socio.getId(), associacaoDomain.getCampanhaId());
			associacaoDomain = servicosFallBacks.associarSocioCampanha(associacao);
			if (Objects.nonNull(associacaoDomain))
				return associacaoDomain;
		}
		throw new UnprocessableEntity("Sócio não cadastrado");
	}

	@Override
	public SocioTorcedorEntity existeSocioTorcedor(String email) {
		SocioTorcedorEntity socioTorcedor = repository.findByEmail(email).orElse(null);
		return socioTorcedor;
	}

	@Override
	public List<SocioTorcedorDomain> listar() {
		return repository.findAll().stream().map(x-> new SocioTorcedorDomain(x.getId(),x.getNomeCompleto(), x.getEmail(), x.getDataNascimento(),  x.getTime())).collect(Collectors.toList());
	}
	
	

}
