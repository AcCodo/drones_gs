package com.fiap.drones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.drones.models.Drone;
import com.fiap.drones.models.LicencaVoo;
import com.fiap.drones.repositories.DroneRepository;
import com.fiap.drones.repositories.HistoricoVooRepository;
import com.fiap.drones.repositories.LicencaVooRepository;
import com.fiap.drones.repositories.TelemetriaRepository;

@Controller
@RequestMapping("/reader")
public class TelemetriaReaderRoleController {

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
		ModelAndView model = new ModelAndView("reader/drones/index");
		model.addObject("drones", droneRep.findAll());
		return model;
	}
	
	@GetMapping("/drones/telemetria/{id}")
	public ModelAndView getTelemetria(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("reader/drones/telemetria/index");
		model.addObject("telemetrias", telRep.findByDrone(id));
		model.addObject("droneId", id);
		return model;
	}
	
	@GetMapping("/drones/licenca/{id}")
	public ModelAndView getLicenca(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("reader/licencas/visualizar");

		Drone droneLicenca = droneRep.findById(id).orElse(null);
		
		if (droneLicenca.getLicencaVoo() == null) 
			return new ModelAndView("reader/drones/index");
		
		LicencaVoo licenca = licRep.findById(droneLicenca.getLicencaVoo()).orElse(null);
		model.addObject("licenca", licenca);
		
		return model;
	}
	
	@GetMapping("/licencas")
	public ModelAndView getLicencas() {
		ModelAndView model = new ModelAndView("reader/licencas/index");
		model.addObject("licencas", licRep.findAll());
		return model;
	}
	
	@GetMapping("/historico")
	public ModelAndView getHistorico() {
		ModelAndView model = new ModelAndView("reader/historico/index");
		model.addObject("historicos", histRep.findAll());
		return model;
	}
	
}
