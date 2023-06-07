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
import com.fiap.drones.repositories.DroneRepository;
import com.fiap.drones.repositories.LicencaVooRepository;
import com.fiap.drones.repositories.TelemetriaRepository;

@Controller
@RequestMapping("/admin/drones")
public class DroneControllerAdmin {
	
	@Autowired
	DroneRepository droneRep;
	
	@Autowired
	LicencaVooRepository licRep;

	@Autowired
	TelemetriaRepository telRep;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("admin/drones/index");
		
		model.addObject("drones", droneRep.findAll());
		
		return model;
	}
	
	@GetMapping("/criar")
	public ModelAndView getCriar() {
		ModelAndView model = new ModelAndView("admin/drones/criar");
		return model;
	}
	
	@PostMapping("/criar")
	public String postCriar(@ModelAttribute("drone") Drone drone) {
		droneRep.save(drone);
		return "redirect:/admin/drones";
	}
	
	@GetMapping("/deletar/{id}")
    public String delete(@PathVariable("id")String id) {
		droneRep.deleteById(id);
        return "redirect:/admin/drones";
    }
	
	@GetMapping("/licenca/{id}")
	public ModelAndView getLicenca(@PathVariable("id")String id) {
		Drone droneLicenca = droneRep.findById(id).orElse(null);
		if (droneLicenca.getLicencaVoo() == null) droneLicenca.setLicencaVoo((long) 0);
		LicencaVoo licenca = licRep.findById(droneLicenca.getLicencaVoo()).orElse(null);
		
		if (licenca == null) {
			ModelAndView model = new ModelAndView("admin/licencas/criar");
			model.addObject("droneId", id);
			return model;
		}
		
		ModelAndView model = new ModelAndView("admin/licencas/editar");
		model.addObject("licenca", licenca);
		return model;
	}
	
	@GetMapping("/telemetria/{id}")
	public ModelAndView getTelemetria(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("admin/drones/telemetria/index");
		
		model.addObject("telemetrias", telRep.findByDrone(id));
		model.addObject("droneId", id);
		
		return model;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView getEditar(@PathVariable("id")String id) {
		ModelAndView model = new ModelAndView("admin/drones/editar");
		model.addObject("drone", droneRep.findById(id).orElse(null));		
		return model;
	}
	
	@PostMapping("/editar/{id}")
	public String postEditar(@PathVariable("id")String id, @ModelAttribute("drone")Drone droneAtualizado, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editar";
		}
		
		Drone droneOriginal = droneRep.findById(id).orElse(null);
		droneOriginal.setCapacidadeBateria(droneAtualizado.getCapacidadeBateria());
		droneOriginal.setCapacidadeCarga(droneAtualizado.getCapacidadeCarga());
		droneOriginal.setDataCompra(droneAtualizado.getDataCompra());
		droneOriginal.setModelo(droneAtualizado.getModelo());
		droneOriginal.setNumeroSerie(droneAtualizado.getNumeroSerie());
		
		droneRep.save(droneOriginal);
		
		return "redirect:/admin/drones";
	}
	
}
