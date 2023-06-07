package com.fiap.drones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.drones.models.Usuario;
import com.fiap.drones.repositories.UsuariosRepository;

@Controller
public class UsuariosController {

	@Autowired
	private UsuariosRepository userRep;
	
	@GetMapping("/cadastrar")
	public ModelAndView getCadastro() {
		ModelAndView model = new ModelAndView("usuarios/cadastro");
		return model;
	}
	
	@PostMapping("/cadastrar")
	public String postCadastro(@ModelAttribute("usuario")Usuario usuario) {
		userRep.save(usuario);
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("usuario")Usuario usuario) {
		List<Usuario> usuariosAchados = userRep.findByUNPW(usuario.getUsername(), usuario.getPassword());
		
		if (usuariosAchados.size() > 1) {
			return new ModelAndView("index");
		}
		
		if (usuariosAchados.size() == 0) {
			ModelAndView model = new ModelAndView("index");
			model.addObject("aviso", "Usuário ou senha errados!");
			return model;
		}
		
		ModelAndView model = null;
		
		switch (usuariosAchados.get(0).getRole()) {
		case "DRONE-ADMIN":
			model = new ModelAndView("admin/index");
			break;
		case "DRONE-SEED":
			model = new ModelAndView("seed/index");
			break;
		case "TELEMETRIA-READER":
			model = new ModelAndView("reader/index");
			break;
		}
		
		return model;
	}
	
	@GetMapping("/admin")
	public ModelAndView getAdminMain() {
		ModelAndView model = new ModelAndView("admin/index");
		return model;
	}
	
	@GetMapping("/reader")
	public ModelAndView getReaderMain() {
		ModelAndView model = new ModelAndView("reader/index");
		return model;
	}
	
	@GetMapping("/seed")
	public ModelAndView getSeedMain() {
		ModelAndView model = new ModelAndView("seed/index");
		return model;
	}
	
}
