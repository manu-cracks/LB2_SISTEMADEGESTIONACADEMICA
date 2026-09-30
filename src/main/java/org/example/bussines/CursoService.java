package org.example.bussines;

import org.example.data.CursoRepository;

import java.util.List;

public class CursoService {
    private final CursoRepository repository;

    public CursoService() {
        repository = new CursoRepository();
    }

    public void registrar(Curso curso) {
        List<Curso> cursos = repository.listar();
        cursos.add(curso);
        repository.guardar(cursos);
    }

    public List<Curso> listar() {
        return repository.listar();
    }

    public boolean actualizar(Curso curso) {
        List<Curso> cursos = repository.listar();
        for (Curso cursoActual : cursos) {
            if (cursoActual.getId() == curso.getId()) {
                cursoActual.setNombre(curso.getNombre());
                cursoActual.setCreditos(curso.getCreditos());
                repository.guardar(cursos);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Curso> cursos = repository.listar();
        boolean eliminado = cursos.removeIf(curso -> curso.getId() == id);
        if (eliminado) {
            repository.guardar(cursos);
        }
        return eliminado;
    }
}
