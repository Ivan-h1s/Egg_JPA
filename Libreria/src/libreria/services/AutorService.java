package libreria.services;

import java.util.List;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorService {
    
    private final AutorDAO dao;
    
    public AutorService() {
        this.dao = new AutorDAO();
    }
  
    public Autor createAutor(String nombre) throws Exception{
        
        Autor autor = new Autor();
        try {
            if(nombre == null) {
                throw new Exception("Error. Debe ingresar un nombre");
            }
            autor.setNombre(nombre);
            autor.setAlta(true);
            dao.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Autor getAutorById(Integer id) {
        try {
            return dao.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Autor> getAutorByName(String nombre) {
        try {
            return dao.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Autor> getAllAutores() {
        try {
            return dao.listarTodosAutores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteAutorById (Integer id) {
        try {
            dao.eliminar(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}