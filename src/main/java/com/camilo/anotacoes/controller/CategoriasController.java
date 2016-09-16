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

import com.camilo.anotacoes.model.Categoria;
import com.camilo.anotacoes.service.CadastroCategoriaService;

@Controller
public class CategoriasController {

	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;

	@RequestMapping(value = "/categorias/novo", method = RequestMethod.GET)
	public ModelAndView novo(Categoria categoria) {
		return new ModelAndView("categoria/CadastroCategoria");
	}

	@RequestMapping(value = "/categorias/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Validated Categoria categoria, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(categoria);
		}

		cadastroCategoriaService.salvar(categoria);
		attr.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		return new ModelAndView("redirect:/categorias/novo");
	}
}
