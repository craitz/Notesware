package com.camilo.anotacoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camilo.anotacoes.model.Categoria;
import com.camilo.anotacoes.service.CadastroCategoriaService;
import com.camilo.anotacoes.service.exception.CategoriaJaCadastradaException;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;

	@GetMapping(value = "/novo")
	public ModelAndView novo(Categoria categoria) {
		return new ModelAndView("categoria/CadastroCategoria");
	}

	@PostMapping(value = "/novo")
	public ModelAndView cadastrar(@Validated Categoria categoria, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(categoria);
		}

		try {
			cadastroCategoriaService.salvar(categoria);
		} catch (CategoriaJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(categoria);
		}
		
		attr.addFlashAttribute("mensagem", "Categoria salva com sucesso!");

		return new ModelAndView("redirect:/categorias/novo");
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Validated Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		categoria = cadastroCategoriaService.salvar(categoria);
		
		return ResponseEntity.ok(categoria);
	}
}
