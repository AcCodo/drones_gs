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

import com.fiap.drones.models.Drone;
import com.fiap.drones.models.LicencaVoo;
import com.fiap.drones.models.Telemetria;
import com.fiap.drones.repositories.DroneRepository;
import com.fiap.drones.repositories.HistoricoVooRepository;
import com.fiap.drones.repositories.LicencaVooRepository;
import com.fiap.drones.repositories.TelemetriaRepository;

@Controller
@RequestMapping("/seed")
public class DroneSeedRoleController {

	@Autowired
	private DroneRepository droneRep;
	
	@Autowired
	private HistoricoVooRepository histRep;
	
	@Autowired
	private LicencaVooRepository licRep;

	@Autowired
	private TelemetriaRepository telRep;
	
	@GetMapping("/drones")
	public ModelAndView getDrones() {
		ModelAndView model = new ModelAndView("seed/drones/index");
		model.addObject("drones", droneRep.findAll());
		return model;
	}
	
	@GetMapping("/drones/telemetria/{id}")
	public ModelAndView getTelemetria(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("seed/drones/telemetria/index");
		model.addObject("telemetrias", telRep.findByDrone(id));
		model.addObject("droneId", id);
		return model;
	}
	
	@GetMapping("/drones/licenca/{id}")
	public ModelAndView getLicenca(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("seed/licencas/visualizar");

		Drone droneLicenca = droneRep.findById(id).orElse(null);
		
		if (droneLicenca.getLicencaVoo() == null) 
			return new ModelAndView("seed/drones/index");
		
		LicencaVoo licenca = licRep.findById(droneLicenca.getLicencaVoo()).orElse(null);
		model.addObject("licenca", licenca);
		
		return model;
	}
	
	@GetMapping("/licencas")
	public ModelAndView getLicencas() {
		ModelAndView model = new ModelAndView("seed/licencas/index");
		model.addObject("licencas", licRep.findAll());
		return model;
	}
	
	@GetMapping("/historico")
	public ModelAndView getHistorico() {
		ModelAndView model = new ModelAndView("seed/historico/index");
		model.addObject("historicos", histRep.findAll());
		return model;
	}
	
	@GetMapping("/drones/telemetria/criar/{id}")
	public ModelAndView getCriar(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("seed/drones/telemetria/criar");
		model.addObject("droneId", id);
		return model;
	}
	
	@PostMapping("/drones/telemetria/criar")
	public String postCriar(@ModelAttribute("telemetria")Telemetria telemetria) {
		telemetria.setId("ID" + telemetria.getLatitude() + telemetria.getLongitude());
		telRep.save(telemetria);		
		return "redirect:/seed/drones/telemetria/" + telemetria.getIdDrone();
	}
	
	@GetMapping("/drones/telemetria/{droneId}/deletar/{id}")
	public String deletar(@PathVariable("droneId")String droneId ,@PathVariable("id")String idTelemetria) {
		telRep.deleteById(idTelemetria);
		return "redirect:/seed/drones/telemetria/" + droneId;
	}
	
	@GetMapping("/drones/telemetria/{droneId}/editar/{id}")
	public ModelAndView getEditar(@PathVariable("droneId")String droneId ,@PathVariable("id")String idTelemetria) {
		ModelAndView model = new ModelAndView("seed/drones/telemetria/editar");
		model.addObject("telemetria", telRep.findById(idTelemetria).orElse(null));
		return model;
	}
	
	@PostMapping("/drones/telemetria/editar/{id}")
	public String postEditar(@PathVariable("id")String id, @ModelAttribute("telemetria")Telemetria telemetria, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "editar";
		
		Telemetria telAntiga = telRep.findById(id).orElse(null);
		telAntiga.setDataHora(telemetria.getDataHora());
		telAntiga.setDirecao(telemetria.getDirecao());
		telAntiga.setVelocidade(telemetria.getVelocidade());
		
		telRep.save(telAntiga);
		
		return "redirect:/seed/drones/telemetria/" + telAntiga.getIdDrone();
	}
	
}
