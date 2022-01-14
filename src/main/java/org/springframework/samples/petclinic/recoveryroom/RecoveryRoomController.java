package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {
	
	private RecoveryRoomService recoveryRoomService;
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService recoveryRoomService) {
		this.recoveryRoomService = recoveryRoomService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	//AÑADIR EL FORMULARIO DEL LISTADO DE TIPO DE PRODUCTOS
	
	@ModelAttribute("types")
	public List<RecoveryRoomType> populatePetTypes() {
		return this.recoveryRoomService.getAllRecoveryRoomTypes();
	}
	
	@GetMapping(value = "/recoveryroom/create")
	public String processCreationForm(Map<String, Object> model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
		model.put("recoveryRoom", recoveryRoom);
		return "recoveryroom/createOrUpdateRecoveryRoomForm";
	}
	
	@PostMapping(value = "/recoveryroom/create")
	public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "recoveryroom/createOrUpdateRecoveryRoomForm";
		}
		else 
			this.recoveryRoomService.save(recoveryRoom);
		
		return "welcome";
	}
    
}
