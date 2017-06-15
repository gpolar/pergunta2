package com.pergunta2.fallbacks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.service.CampanhaService;

/**
 * Esta classe vai ter a logica de retorno no caso um servico externo não
 * estiver funcionando
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contato@gustavopolarsa.com
 */
@Component
public class ServicosFallBacks {

	@Autowired
	private CampanhaService campanhaService;

	@HystrixCommand(fallbackMethod = "erroConexaoCampanhaList")
	public List<CampanhaDomain> listarCampanhasAtivas() {
		return campanhaService.listarCampanhasAtivas();
	}

	public List<CampanhaDomain> erroConexaoCampanhaList() {
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "erroConexaoAssociacaoList")
	public List<AssociacaoDomain> listarAssociados(String socioId) {
		return campanhaService.listarCampanhasPorSocio(socioId);
	}

	public List<AssociacaoDomain> erroConexaoAssociacaoList(String socioId) {
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "erroConexaoAssociacaoAdd")
	public AssociacaoDomain associarSocioCampanha(AssociacaoDomain associacaoDomain) {
		return campanhaService.adicionarCampanhas(associacaoDomain);
	}

	public AssociacaoDomain erroConexaoAssociacaoAdd(AssociacaoDomain associacaoDomain) {
		return null;
	}

}