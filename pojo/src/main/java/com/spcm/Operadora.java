package com.spcm;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Operadora")
public class Operadora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operadora", unique = true, nullable = false)
	private Integer idOperadora;
	@Column(name = "name", nullable = false)
	private String name;
	private String code;
	private String category;
	private BigDecimal price;
	private String mensagem;

	public Integer getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Operadora [idOperadora=" + idOperadora + ", name=" + name + ", code=" + code + ", category=" + category
				+ ", price=" + price + "]";
	}

}
