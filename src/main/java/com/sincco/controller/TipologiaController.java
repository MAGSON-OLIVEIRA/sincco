package com.sincco.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sincco.controller.page.PageWrapper;
import com.sincco.model.Servico;
import com.sincco.model.Tipologia;
import com.sincco.repository.Tipologias;
import com.sincco.repository.filter.TipologiaFilter;
import com.sincco.service.CadastroLeituraService;
import com.sincco.service.CadastroMoradiaService;
import com.sincco.service.CadastroTipologiaService;

@Controller
@RequestMapping("/tipologia")
public class TipologiaController {

	@Autowired
	private CadastroTipologiaService cadastroTipologiaService;

	@Autowired
	private CadastroMoradiaService cadastroMoradiaService;

	@Autowired
	private CadastroLeituraService cadastroLeituraService;
	
	@Autowired
	private Tipologias tipologias;


	@RequestMapping("/novo")
	public ModelAndView novo(Tipologia tipologia) {
		ModelAndView mv = new ModelAndView("tipologia/CadastroTipologia");
		mv.addObject("listarMoradia", cadastroMoradiaService.lista());
		mv.addObject("tipoLeituras", cadastroLeituraService.lista());
		return mv;
	}

	/**
	 * Iniciar Pagina simulação de emprestimo.
	 * 
	 * @author BBPF0170 - Magson Dias
	 * @since 28/12/2017
	 * @return String
	 */
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Tipologia tipologia, BindingResult result, Model model,
			RedirectAttributes attributes) {
		String msn = "";
		if (result.hasErrors()) {
			return novo(tipologia);
		}
		if (tipologia.getId() == null) {
			tipologia.setDatainclusao(new Date());
			msn = "Registro salvo com sucesso.";
		} else {
			msn = "Registro atualizado com sucesso.";
		}
		cadastroTipologiaService.salvar(tipologia);
		attributes.addFlashAttribute("mensagem", msn);
		return new ModelAndView("redirect:/tipologia/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView deleta(@PathVariable("id") Long id, RedirectAttributes attributes) {
		Tipologia tipologia = cadastroTipologiaService.pesquisaPorId(id);
		cadastroTipologiaService.deletar(tipologia);
		attributes.addFlashAttribute("mensagem", "Registro excluido com sucesso.");
		return new ModelAndView("redirect:/tipologia/novo");
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("tipologia/CadastroTipologia");
		Tipologia tipologia = cadastroTipologiaService.pesquisaPorId(id);
		mv.addObject("tipologia", tipologia);
		return mv;
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Tipologia> pesquisar(String descricao) {
		validarTamanhoCampoPesquisa(descricao);
		return cadastroTipologiaService.findByDescricaoStartingWithIgnoreCase(descricao);
	}

	private void validarTamanhoCampoPesquisa(String descricao) {
		if (StringUtils.isEmpty(descricao) || descricao.length() < 3) {
			throw new IllegalArgumentException();
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();

	}
	
	
	@GetMapping
	public ModelAndView pesquisar(TipologiaFilter tipologiaFilter, @PageableDefault(size=4) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("/tipologia/PesquisaTipologias");
		PageWrapper<Tipologia> paginaWrapper = new PageWrapper<>(tipologias.filtrar(tipologiaFilter, pageable), httpServletRequest);
		mv.addObject("pagina",paginaWrapper);
		return mv;
		
	}
	
	

}
