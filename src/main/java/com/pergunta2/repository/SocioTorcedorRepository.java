package com.pergunta2.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pergunta2.entity.SocioTorcedorEntity;

/**
 * Esta classe contem a interface da entidade SocioTorcedorEntity para persistir
 * no banco de dados
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contact@gustavopolarsa.com
 */
public interface SocioTorcedorRepository extends MongoRepository<SocioTorcedorEntity, String> {

	Optional<SocioTorcedorEntity> findByEmail(String email);
	
}
