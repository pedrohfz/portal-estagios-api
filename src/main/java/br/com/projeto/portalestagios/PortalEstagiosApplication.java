// Universidade Presbiteriana Mackenzie
// Bacharelado em Sistemas de Informação
// Projeto de Programação de Sistemas II - Portal de Estágios
// Integrantes:
// - Nome: Pedro Henrique Leite | RA: 10418014
// - Nome: João Victor Cara Santos Souza | RA: 10409591	
// - Nome: Marcelo Takao Arakava | RA: 10437382
// - Nome: Luana dos Santos Fernandes | RA: 10439810

package br.com.projeto.portalestagios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.projeto.portalestagios")
public class PortalEstagiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalEstagiosApplication.class, args);
	}

}
