package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import service.AlumnoServicio;

@CrossOrigin("*")
@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoServicio alumnoServicio;
	
	@PostMapping("Alta")
	public String alta(@ModelAttribute Alumno alumno) {
		alumnoServicio.alta(alumno);
		return "alta";
	}
	
	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> buscarPorCurso(@RequestParam("curso") String curso){
		return alumnoServicio.buscarPorCurso(curso);
	}
	
	@GetMapping(value = "Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> cursos(){
		return alumnoServicio.cursos();
	}
}
