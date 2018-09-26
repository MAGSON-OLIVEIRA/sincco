package com.sincco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sincco.model.Cidade;
import com.sincco.repository.Cidades;
import com.sincco.service.CadastroCidadesService;


@Controller
@RequestMapping("/cidades")
public class CidadeController {

	
	
	@Autowired
	private Cidades cidades;

	
	@RequestMapping("/novo")
	public ModelAndView novo(Cidade cidade){
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		return mv;
	}
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado( @RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado ){
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	/**
	 * ??
	 * 
	 * @author Magson Dias
	 * @since 28/12/2017
	 * @return String
	 */
	
	
}
