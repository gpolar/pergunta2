package com.pergunta2.service;

import java.util.List;

import com.pergunta2.domain.AssociacaoDomain;
import com.pergunta2.domain.CampanhaDomain;
import com.pergunta2.domain.SocioTorcedorDomain;
import com.pergunta2.domain.BaseSocioTorcedorDomain;
import com.pergunta2.entity.SocioTorcedorEntity;

/**
 * Esta classe contem a interface de serviço relacionado com os procedimentos de
 * socio torcedor
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contact@gustavopolarsa.com
 */
public interface SocioTorcedorService {

	public List<CampanhaDomain> create(BaseSocioTorcedorDomain socioTorcedorDomain);
	
	public List<SocioTorcedorDomain> listar();

	public SocioTorcedorEntity existeSocioTorcedor(String email);
	
	public AssociacaoDomain associarSocioCampanha(AssociacaoDomain associacaoDomain);

}
