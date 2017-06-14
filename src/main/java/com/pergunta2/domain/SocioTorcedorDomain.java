package com.pergunta2.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pergunta2.convert.ParseDeserializer;

/**
 * Esta classe tem o SocioTorcedorDomain que serve de parse entre a entidade e o
 * cliente
 * 
 * @author: Gustavo Polar gpolars@gmail.com, contato@gustavopolarsa.com
 */
public class SocioTorcedorDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCompleto;

	private String email;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = ParseDeserializer.class)
	private LocalDate dataNascimento;

	private Integer time;

	public SocioTorcedorDomain() {

	}

	public SocioTorcedorDomain(String nomeCompleto, String email, LocalDate dataNascimento, Integer time) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.time = time;
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

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
