package service;

import java.util.List;

import model.Alumno;

public interface AlumnoServicio {
	void alta(Alumno alumno);
	List<Alumno> buscarPorCurso(String curso);
	List<String> cursos();
}
