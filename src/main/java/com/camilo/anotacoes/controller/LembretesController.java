package com.camilo.anotacoes.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camilo.anotacoes.controller.page.PageWrapper;
import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.Categorias;
import com.camilo.anotacoes.repository.Grupos;
import com.camilo.anotacoes.repository.Lembretes;
import com.camilo.anotacoes.repository.filter.LembreteFilter;
import com.camilo.anotacoes.service.CadastroLembreteService;

@Controller
@RequestMapping("/lembretes")
public class LembretesController {

	@Autowired
	private CadastroLembreteService cadastroLembreteService;
	
	@Autowired
	private Lembretes lembretes;

	@Autowired
	private Categorias categorias;

	@Autowired
	private Grupos grupos;

	@GetMapping(value = "/novo")	
	public ModelAndView novo(Lembrete lembrete) {
		ModelAndView mv = new ModelAndView("lembrete/CadastroLembrete");
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("grupos", grupos.findAll());
		lembrete.setDataCadastro(new Date());
		return mv;
	}

	@PostMapping(value = "/novo")
	public ModelAndView cadastrar(@Validated Lembrete lembrete, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(lembrete);
		}

		cadastroLembreteService.salvar(lembrete);
		attr.addFlashAttribute("mensagem", "Lembrete salvo com sucesso!");

		return new ModelAndView("redirect:/lembretes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(LembreteFilter lembreteFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("lembrete/PesquisaLembretes");
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("grupos", grupos.findAll());
		
		PageWrapper<Lembrete> paginaWrapper = new PageWrapper<>(lembretes.filtrar(lembreteFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
}
