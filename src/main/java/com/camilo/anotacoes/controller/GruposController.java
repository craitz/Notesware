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

import com.camilo.anotacoes.model.Grupo;
import com.camilo.anotacoes.service.CadastroGrupoService;
import com.camilo.anotacoes.service.exception.GrupoJaCadastradoException;

@Controller
@RequestMapping("/grupos")
public class GruposController {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Grupo grupo) {
		return new ModelAndView("grupo/CadastroGrupo");
	}

	@PostMapping(value = "/novo")
	public ModelAndView cadastrar(@Validated Grupo grupo, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return novo(grupo);
		}

		try {
			cadastroGrupoService.salvar(grupo);
		} catch (GrupoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}
		
		attr.addFlashAttribute("mensagem", "Grupo salvo com sucesso!");
		
		return new ModelAndView("redirect:/grupos/novo");
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Validated Grupo grupo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		grupo = cadastroGrupoService.salvar(grupo);
		
		return ResponseEntity.ok(grupo);
	}	
}
