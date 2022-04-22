package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {
	
	@Autowired
	JdbcTemplate template;

	@Override
	public void alta(Alumno alumno) {
		String sql = "insert into alumnos(nombre, curso, nota) values (?, ?, ?)";
		if(!existeAlumno(alumno.getNombre())) {
			template.update(sql, alumno.getNombre(), alumno.getCurso(), alumno.getNota());
		}else {
			System.out.println("Ya existe un alumno con ese nombre");
		}
		
	}

	@Override
	public List<Alumno> buscarPorCurso(String curso) {
		String sql = "select * from alumnos where curso=?";
		return template.query(sql, (rs, f) -> new Alumno(rs.getInt("idAlumno"),
				rs.getString("nombre"),
				rs.getString("curso"),
				rs.getInt("nota")),
				curso);
	}
	
	public boolean existeAlumno(String nombre) {
		String sql = "select * from alumnos where nombre=?";
		List<Alumno> alumnos = template.query(sql, (rs,f) -> new Alumno(rs.getInt("idAlumno"), 
				rs.getString("nombre"),
				rs.getString("curso"),
				rs.getInt("nota")),
				nombre);
		return alumnos.size()>0;
	}
	
	public List<String> cursos(){
		String sql = "select distinct(curso) from alumnos";
		return template.query(sql, (r,f) -> r.getString(1));
	}
}
