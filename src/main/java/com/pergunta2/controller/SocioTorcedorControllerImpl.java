package com.pergunta2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.domain.BaseSocioTorcedorDomain;
import com.pergunta2.service.SocioTorcedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Esta classe implementa os end-point do SocioTorcedorController
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contact@gustavopolarsa.com
 */
@RestController
@RequestMapping("/socio")
@Api(basePath = "/socio", value = "Socio", description = "Crud de Socio", produces = "application/json")
public class SocioTorcedorControllerImpl {

	
	private final SocioTorcedorService socioService;
		
	@Autowired
	SocioTorcedorControllerImpl(SocioTorcedorService socioService) {
		this.socioService = socioService;
	}

	/**
	 * Método que adiciona um socio torcedor
	 * 
	 * @param baseSocioTorcedorDomain
	 * @return List<CampanhaDomain>
	 * @throws Exception 
	 */
	@PostMapping
	@ApiOperation(value = "Adicionar Socio Torcedor", notes = "Adicionar Campanhas")
    @ApiResponses(value = {
    		@ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 201, message = "Socio Torcedor Cadastrado") })
	public List<CampanhaDomain> adicionar(@RequestBody BaseSocioTorcedorDomain socio){
		return socioService.create(socio);
	}
	
	/**
	 * Método que adiciona um socio torcedor
	 * 
	 * @return List<SocioTorcedorDomain>
	 * @throws Exception 
	 */
	@GetMapping
	@ApiOperation(value = "Lista Socio Torcedor Cadastrados", notes = "Lista Socio Torcedor Cadastrados")
    @ApiResponses(value = {
    		@ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 201, message = "Lista Socio Torcedor Cadastrados") })
	public List<SocioTorcedorDomain> listar(){
		return socioService.listar();
	}

}
