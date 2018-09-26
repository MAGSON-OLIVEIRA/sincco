package com.sincco.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sincco.controller.page.PageWrapper;
import com.sincco.model.TipoPessoa;
import com.sincco.model.Usuario;
import com.sincco.repository.Grupos;
import com.sincco.repository.Usuarios;
import com.sincco.repository.filter.UsuarioFilter;
import com.sincco.service.CadastroUsuarioService;
import com.sincco.service.exception.CpfCnpjJaCadastradoException;
import com.sincco.service.exception.EmailJaCadastradoExcetion;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
	
	
	/**
	 * ??
	 * 
	 * @author  Magson Dias
	 * @since 	05/18
	 * @return 	ModelAndView
	 */
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes){
		
		if (usuario.getSenha() != null ){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setSenha(encoder.encode(usuario.getSenha()));
		}
	
		if(usuario.getConfirmacaoSenha()!= null){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setConfirmacaoSenha(encoder.encode(usuario.getConfirmacaoSenha()));
		}
		
		String msn="";
		if (result.hasErrors()){
			return novo(usuario);
		}
		

		
		if (usuario.getId() == null){
			msn = "Registro salvo com sucesso.";
		}else{
			msn = "Registro atualizado com sucesso.";
		}
		
		
		try{
			cadastroUsuarioService.salvar(usuario);
		}catch(CpfCnpjJaCadastradoException e){
			result.rejectValue("cpfCnpj", e.getMessage(), e.getMessage());
			return novo(usuario);
		}catch(EmailJaCadastradoExcetion exEmail){
			result.rejectValue("email", exEmail.getMessage(), exEmail.getMessage());
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", msn );	
		return new ModelAndView("redirect:/usuario/novo");
	}
	
	
	@RequestMapping("/{id}")
	public ModelAndView deleta(@PathVariable("id") Long id, RedirectAttributes attributes){
		Usuario usuario = cadastroUsuarioService.pesquisaPorId(id);
		cadastroUsuarioService.deletar(usuario);
		attributes.addFlashAttribute("mensagem", "Registro excluido com sucesso.");
		return new ModelAndView("redirect:/usuario/novo");
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar( @PathVariable("id") Long id){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		Usuario usuario = cadastroUsuarioService.pesquisaPorId(id);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result,
			@PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuario");
		//mv.addObject("tiposPessoa", TipoPessoa.values());
		PageWrapper<Usuario> paginaWrapper =  new PageWrapper<> (usuarios.filtar(usuarioFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		//mv.addObject("listaUsuarios", usuarios.filtar(usuarioFilter, pageable));
		// mv.addObject("listaUsuarios", usuarios.findAll(pageable));
		return mv;
	}
	
	


	
}
