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

import com.fiap.drones.models.Telemetria;
import com.fiap.drones.repositories.DroneRepository;
import com.fiap.drones.repositories.TelemetriaRepository;

@Controller
@RequestMapping("/admin/drones/telemetria")
public class TelemetriaControllerAdmin {

	@Autowired
	DroneRepository droneRep;
	
	@Autowired
	TelemetriaRepository telRep;
	
	@GetMapping("/criar/{id}")
	public ModelAndView getCriar(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("admin/drones/telemetria/criar");
		model.addObject("droneId", id);
		return model;
	}
	
	@PostMapping("/criar")
	public String postCriar(@ModelAttribute("telemetria")Telemetria telemetria) {
		telemetria.setId("ID" + telemetria.getLatitude() + telemetria.getLongitude());
		telRep.save(telemetria);		
		return "redirect:/admin/drones/telemetria/" + telemetria.getIdDrone();
	}
	
	@GetMapping("/{droneId}/deletar/{id}")
	public String deletar(@PathVariable("droneId")String droneId ,@PathVariable("id")String idTelemetria) {
		telRep.deleteById(idTelemetria);
		return "redirect:/admin/drones/telemetria/" + droneId;
	}
	
	@GetMapping("/{droneId}/editar/{id}")
	public ModelAndView getEditar(@PathVariable("droneId")String droneId ,@PathVariable("id")String idTelemetria) {
		ModelAndView model = new ModelAndView("admin/drones/telemetria/editar");
		model.addObject("telemetria", telRep.findById(idTelemetria).orElse(null));
		return model;
	}
	
	@PostMapping("/editar/{id}")
	public String postEditar(@PathVariable("id")String id, @ModelAttribute("telemetria")Telemetria telemetria, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "editar";
		
		Telemetria telAntiga = telRep.findById(id).orElse(null);
		telAntiga.setDataHora(telemetria.getDataHora());
		telAntiga.setDirecao(telemetria.getDirecao());
		telAntiga.setVelocidade(telemetria.getVelocidade());
		
		telRep.save(telAntiga);
		
		return "redirect:/admin/drones/telemetria/" + telAntiga.getIdDrone();
	}
	
}
