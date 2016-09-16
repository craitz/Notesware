package com.camilo.anotacoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camilo.anotacoes.model.Grupo;
import com.camilo.anotacoes.service.CadastroGrupoService;

@Controller
public class GruposController {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@RequestMapping(value = "/grupos/novo", method = RequestMethod.GET)
	public ModelAndView novo(Grupo grupo) {
		return new ModelAndView("grupo/CadastroGrupo");
	}

	@RequestMapping(value = "/grupos/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Validated Grupo grupo, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(grupo);
		}

		cadastroGrupoService.salvar(grupo);
		attr.addFlashAttribute("mensagem", "Grupo salvo com sucesso!");
		
		return new ModelAndView("redirect:/grupos/novo");
	}
}
