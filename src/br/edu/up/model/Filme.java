package br.edu.up.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filme {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
private Integer id;
private String NomeFilme ;
private String Categoria;
private Integer AnoLanc;




public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNomeFilme() {
	return NomeFilme;
}
public void setNomeFilme(String nomeFilme) {
	NomeFilme = nomeFilme;
}
public String getCategoria() {
	return Categoria;
}
public void setCategoria(String categoria) {
	Categoria = categoria;
}
public Integer getAnoLanc() {
	return AnoLanc;
}
public void setAnoLanc(Integer anoLanc) {
	AnoLanc = anoLanc;
}


}
