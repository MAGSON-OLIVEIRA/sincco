package com.sincco.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sincco.controller.page.PageWrapper;
import com.sincco.model.CepServico;
import com.sincco.repository.CepsServicos;
import com.sincco.repository.filter.CepServicoFilter;
import com.sincco.service.CadastroCepServicoService;
import com.sincco.service.CadastroEstadosService;

@Controller
@RequestMapping("/cepServico")
public class CepController {

	@Autowired
	private CadastroCepServicoService cadastroCepServicoService;
	
	@Autowired
	private CadastroEstadosService cadastroEstadosService;
	
	@Autowired
	private CepsServicos cepsServicos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(CepServico cepServico) {
		ModelAndView mv = new ModelAndView("cepServico/CadastroCepServico");
		//mv.addObject("lista", cadastroCepServicoService.lista());
		mv.addObject("listaEstado", cadastroEstadosService.lista());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid CepServico cepServico, BindingResult result, Model model,
				RedirectAttributes attributes) {
		String msn= "";
		if(result.hasErrors()){
			return novo(cepServico);
		}
		if(cepServico.getId() == null){
			msn = "Registro salvo com sucesso.";
		}else{
			msn = "Registro atualizado com sucesso.";
		}
		cadastroCepServicoService.salvar(cepServico);
		attributes.addFlashAttribute("mensagem", msn);
		return new ModelAndView("redirect:/cepServico/novo");
	}
	
	@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<CepServico> pesquisar(String cep){
		validarTamanhoCampoPesquisa(cep);
		return cadastroCepServicoService.findByCepStartingWithIgnoreCase(cep);
	}
	
	private void validarTamanhoCampoPesquisa(String descricao) {
		if (StringUtils.isEmpty(descricao) || descricao.length() < 10) {
			throw new IllegalArgumentException();
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();

	}
	
	@GetMapping
	public ModelAndView pesquisar(CepServicoFilter cepServicoFilter, @PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest ){
		ModelAndView mv = new ModelAndView("/cepServico/PesquisarCeps");
		PageWrapper<CepServico> paginaWrapper = new PageWrapper<>(cepsServicos.filtrar(cepServicoFilter, pageable),httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
