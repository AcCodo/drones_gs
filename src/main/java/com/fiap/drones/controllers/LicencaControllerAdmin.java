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

@Controller
@RequestMapping("/admin/licencas")
public class LicencaControllerAdmin {
	
	@Autowired
	LicencaVooRepository licRep;
	
	@Autowired
	DroneRepository droneRep;

	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("admin/licencas/index");
		model.addObject("licencas", licRep.findAll());
		return model;
	}
	
	@PostMapping("/criar")
	public String postCriar(@ModelAttribute("licencaVoo")LicencaVoo licenca) {
		Drone drone = droneRep.findById(licenca.getIdDrone()).orElseThrow();
		
		licenca = licRep.save(licenca);
		
		drone.setLicencaVoo(licenca.getId());
		
		droneRep.save(drone);
		
		return "redirect:/admin/drones";
	}
	
	@PostMapping("/editar/{id}")
	public String postEditar(@PathVariable("id")Long id, @ModelAttribute("licencaVoo")LicencaVoo licenca, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "editar";
		
		LicencaVoo licencaAntiga = licRep.findById(id).orElse(null);
		licencaAntiga.setCodigoLicencaVoo(licenca.getCodigoLicencaVoo());
		licencaAntiga.setDataEmissao(licenca.getDataEmissao());
		licencaAntiga.setDataValidade(licenca.getDataValidade());
		
		licRep.save(licencaAntiga);
		
		return "redirect:/admin/drones";
	}
	
}
