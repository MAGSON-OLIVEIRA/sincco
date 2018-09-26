package com.sincco.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sincco.controller.page.PageWrapper;
import com.sincco.model.Produto;
import com.sincco.model.Servico;
import com.sincco.repository.Produtos;
import com.sincco.repository.filter.ProdutoFilter;
import com.sincco.repository.filter.ServicoFilter;
import com.sincco.service.CadastroProdutoService;


@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private Produtos produtos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("listaProdutos", cadastroProdutoService.lista());
		return mv;
	}
	
	
	/**
	 * ??
	 * 
	 * @author Magson Dias
	 * @since 28/12/2017
	 * @return String
	 */
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result,  RedirectAttributes attributes){
		String msn="";
		if (result.hasErrors()){
			return novo(produto);
		}
		if (produto.getId() == null){
			msn = "Registro salvo com sucesso.";
		}else{
			msn = "Registro atualizado com sucesso.";
		}
		cadastroProdutoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", msn );	
		return new ModelAndView("redirect:/produto/novo");
	}
	
	
	@RequestMapping("/{id}")
	public ModelAndView deleta(@PathVariable("id") Long id, RedirectAttributes attributes){
		Produto produto = cadastroProdutoService.pesquisaPorId(id);
		cadastroProdutoService.deletar(produto);
		attributes.addFlashAttribute("mensagem", "Registro excluido com sucesso.");
		return new ModelAndView("redirect:/produto/novo");
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar( @PathVariable("id") Long id){
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		Produto produto = cadastroProdutoService.pesquisaPorId(id);
		mv.addObject("produto", produto);
		return mv;
	}
	
	
	@GetMapping
	public ModelAndView pesquisar( ProdutoFilter produtoFilter, @PageableDefault(size=4) Pageable pageable, HttpServletRequest httpServletRequest ){
		ModelAndView mv = new ModelAndView("/produto/PesquisarProdutos");
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(produtos.filtrar(produtoFilter, pageable),httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
}
