package com.camilo.anotacoes.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.Categorias;
import com.camilo.anotacoes.repository.Grupos;
import com.camilo.anotacoes.repository.Lembretes;
import com.camilo.anotacoes.service.CadastroLembreteService;

@Controller
public class LembretesController {

	@Autowired
	private CadastroLembreteService cadastroLembreteService;
	
	@Autowired
	private Categorias categorias;

	@Autowired
	private Grupos grupos;

	@RequestMapping(value = "/lembretes/novo", method = RequestMethod.GET)	
	public ModelAndView novo(Lembrete lembrete) {
		ModelAndView mv = new ModelAndView("lembrete/CadastroLembrete");
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("grupos", grupos.findAll());
		lembrete.setDataCadastro(new Date());
		return mv;
	}

	@RequestMapping(value = "/lembretes/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Validated Lembrete lembrete, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(lembrete);
		}

		cadastroLembreteService.salvar(lembrete);
		attr.addFlashAttribute("mensagem", "Lembrete salvo com sucesso!");

		return new ModelAndView("redirect:/lembretes/novo");
	}
}
