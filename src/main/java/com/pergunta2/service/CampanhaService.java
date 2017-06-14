package com.pergunta2.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;

/**
 * Esta classe contem a interface da entidade para comunicação com o servico de
 * campanha e associacao
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contato@gustavopolarsa.com
 */
@FeignClient(name = "campanhaService", url = "http://localhost:8080")
public interface CampanhaService {

	@RequestMapping(method = RequestMethod.GET, value = "/campanha")
	public List<CampanhaDomain> listarCampanhasAtivas();
	
	@RequestMapping(method = RequestMethod.GET, value = "/associacao/{socioId}")
	public List<AssociacaoDomain> listarCampanhasPorSocio(@PathVariable("socioId") String socioId);
	
	@RequestMapping(method = RequestMethod.POST, value = "/associacao")
	public AssociacaoDomain adicionarCampanhas(@RequestBody AssociacaoDomain associacaoDomain);

}
