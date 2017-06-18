package com.pergunta2.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Esta classe tem o SocioTorcedorEntity que é a entidade que vai persistir no
 * banco
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contact@gustavopolarsa.com
 */
@Document(collection = "socio-torcedor")
public class SocioTorcedorEntity {

	@Id
	private String id;

	private String nomeCompleto;

	@Indexed(unique = true)
	private String email;

	private LocalDate dataNascimento;

	private Integer time;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public long getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public SocioTorcedorEntity() {
	}

	@Override
	public String toString() {
		return "SocioTorcedorEntity [nomeCompleto=" + nomeCompleto + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", time=" + time + "]";
	}

}
