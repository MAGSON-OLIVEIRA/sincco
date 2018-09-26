package com.sincco.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sincco.controller.page.PageWrapper;
import com.sincco.controller.validator.ServicoValidator;
import com.sincco.model.Servico;
import com.sincco.repository.Servicos;
import com.sincco.repository.filter.ServicoFilter;
import com.sincco.security.UsuarioSistema;
import com.sincco.service.CadastroProdutoService;
import com.sincco.service.CadastroServicoService;
import com.sincco.session.TabelasItensSession;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private TabelasItensSession tabelasItens;
	
	@Autowired
	private CadastroServicoService cadastroServicoService;
	
	@Autowired
	private Servicos servicos;
	
	@Autowired
	private ServicoValidator servicoValidator;
	
	@InitBinder("servico")
	public void inicializarVlidador(WebDataBinder binder){
		binder.setValidator(servicoValidator);
	}
	
	@RequestMapping("/novo")
    public ModelAndView novo(Servico servico) {
          ModelAndView mv = new ModelAndView("servico/Servico");
          mv.addObject("listaProdutos", cadastroProdutoService.lista());
          servico.getServicoTotalMedida();
          if(servico.isNova()){
                servico.setDataInicio(LocalDate.now());
          }
          if(StringUtils.isEmpty(servico.getUuid())){
                servico.setUuid(UUID.randomUUID().toString());
          }
          mv.addObject("itens", servico.getListaItens());
          mv.addObject("totalMedida", tabelasItens.getTotalMedida(servico.getUuid()));
          return mv;
    }

    @PostMapping(value="/novo", params = "salvar")
    public ModelAndView salvar(Servico servico, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuario) {
          validarServico(servico, result);
          if(result.hasErrors()){
                return novo(servico);
          }
          servico.setUsuario(usuario.getUsuario());
          cadastroServicoService.salvar(servico);
          attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso.");
          return new ModelAndView("redirect:/servico/novo");
    }

    @PostMapping(value="/novo", params = "ativar")
    public ModelAndView ativar(Servico servico, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuario) {
          validarServico(servico, result);
          if(result.hasErrors()){
                return novo(servico);
          }
          servico.setUsuario(usuario.getUsuario());
          cadastroServicoService.ativar(servico);
          attributes.addFlashAttribute("mensagem", "Serviço ativo com sucesso.");
          return new ModelAndView("redirect:/servico/novo");
    }

    @PostMapping(value="/novo", params = "enviarEmail")
    public ModelAndView enviarEmail(Servico servico, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuario) {
          validarServico(servico, result);
          if(result.hasErrors()){
                return novo(servico);
          }
          servico.setUsuario(usuario.getUsuario());
          cadastroServicoService.salvar(servico);
          attributes.addFlashAttribute("mensagem", "Serviço enviado por e-mail com sucesso.");
          return new ModelAndView("redirect:/servico/novo");
    }

    private void validarServico(Servico servico, BindingResult result) {
          servico.adicionarItens(tabelasItens.getItens(servico.getUuid()));
          servico.getServicoTotalMedida();
          // fazendo a validação.
          servicoValidator.validate(servico, result);
    }

    

	@RequestMapping(value = "/itens",  method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ModelAndView adicionarItens(String uuid, Integer medida, Integer quantidadeMorador, Integer diasMedido) {
		validarCampo(medida, quantidadeMorador, diasMedido);
		tabelasItens.adicionarItem(uuid, new BigDecimal(medida), new BigDecimal(quantidadeMorador), new BigDecimal(diasMedido));
		return mvTabelaItensServico(uuid);
	}

	private void validarCampo(Integer medida, Integer quantidadeMorador, Integer diasMedido) {
		if (medida == null || quantidadeMorador == null || diasMedido == null) {
			throw new IllegalArgumentException();
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();

	}
	
	
	@DeleteMapping("/item/{codigo}/{uuid}")
	public ModelAndView excluir(@PathVariable Integer codigo, @PathVariable String uuid){
		tabelasItens.excluirItem(uuid, codigo);
		return mvTabelaItensServico(uuid);
		
	}

	private ModelAndView mvTabelaItensServico(String uuid) {
		ModelAndView mv = new ModelAndView("servico/TabelaItensServico");
		mv.addObject("itens", tabelasItens.getItens(uuid));
		mv.addObject("totalMedida", tabelasItens.getTotalMedida(uuid));
		return mv;
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(ServicoFilter servicoFilter, @PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("/servico/PesquisaServicos");
		PageWrapper<Servico> paginaWrapper = new PageWrapper<>(servicos.filtrar(servicoFilter, pageable, null), httpServletRequest);
		mv.addObject("pagina",paginaWrapper);
		return mv;
		
	}
	
	@RequestMapping("/cliente")
	public ModelAndView pesquisarCliente(ServicoFilter servicoFilter, @PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuario){
		ModelAndView mv = new ModelAndView("/servico/PesquisaServicos");
		PageWrapper<Servico> paginaWrapper = new PageWrapper<>(servicos.filtrar(servicoFilter, pageable, usuario), httpServletRequest);
		mv.addObject("pagina",paginaWrapper);
		return mv;
	}

}
