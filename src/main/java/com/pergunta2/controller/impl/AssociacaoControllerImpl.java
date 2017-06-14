package com.pergunta2.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.fallbacks.ServicosFallBacks;
import com.pergunta2.service.SocioTorcedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Esta classe implementa os end-point
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contato@gustavopolarsa.com
 */
@RestController
@RequestMapping("/associacao")
@Api(basePath = "/associacao", value = "Associacao", description = "Funcoes de associacao", produces = "application/json")
public class AssociacaoControllerImpl{

	
	private final SocioTorcedorService socioService;
		
	@Autowired
	AssociacaoControllerImpl(SocioTorcedorService socioService) {
		this.socioService = socioService;
	}

	/**
	 * Método que associa socio com campanha
	 * 
	 * @param associacaoDomain
	 * @return AssociacaoDomain
	 * @throws Exception 
	 */
	@PostMapping
	@ApiOperation(value = "Associar Socio Torcedor", notes = "Associar Campanhas")
    @ApiResponses(value = {
    		@ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 201, message = "Socio Torcedor Associado") })
	public AssociacaoDomain associar(@RequestBody AssociacaoDomain associacaoDomain) throws Exception {
		return socioService.associarSocioCampanha(associacaoDomain);
	}

	
	

}
