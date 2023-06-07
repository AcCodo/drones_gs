package com.fiap.drones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.drones.models.HistoricoVoo;
import com.fiap.drones.repositories.HistoricoVooRepository;

@Controller
@RequestMapping("/admin/historico")
public class HistoricoControllerAdmin {
	
	@Autowired
	private HistoricoVooRepository histRep;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView("admin/historico/index");
		model.addObject("historicos", histRep.findAll());
		return model;
	}
	
	@GetMapping("/{id}")
	public ModelAndView criarHist(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("admin/historico/criar");
		model.addObject("droneId", id);
		return model;
	}
	
	@PostMapping("/criar")
	public String criarHistPost(@ModelAttribute("historico")HistoricoVoo historico) {
		histRep.save(historico);		
		return "redirect:/admin/drones";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarHist(@PathVariable("id")Long id) {
		histRep.deleteById(id);
		return "redirect:/admin/historico";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView getEditar(@PathVariable("id")Long id) {
		ModelAndView model = new ModelAndView("admin/historico/editar");
		model.addObject("historico", histRep.findById(id).orElse(null));
		return model;
	}
	
	@PostMapping("/editar/{id}")
	public String postEditar(@PathVariable("id")Long id, @ModelAttribute("historico")HistoricoVoo historico, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "editar";
		
		HistoricoVoo historicoOriginal = histRep.findById(id).orElse(null);
		historicoOriginal.setLatitudeInicioVoo(historico.getLatitudeInicioVoo());
		historicoOriginal.setLongitudeInicioVoo(historico.getLongitudeInicioVoo());
		historicoOriginal.setLatitudeFimVoo(historico.getLatitudeFimVoo());
		historicoOriginal.setLongitudeFimVoo(historico.getLongitudeFimVoo());
		
		historicoOriginal.setAltitudeMedia(historico.getAltitudeMedia());
		historicoOriginal.setVelocidadeMedia(historico.getVelocidadeMedia());
		historicoOriginal.setDataDecolagem(historico.getDataDecolagem());
		historicoOriginal.setDataAterrissagem(historico.getDataAterrissagem());
		
		histRep.save(historicoOriginal);
		
		return "redirect:/admin/historico";
	}
	
}
