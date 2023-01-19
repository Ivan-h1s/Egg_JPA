package libreria.persistencia;

import java.util.List;

import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardar(Autor autor) {
        try {
            if (autor == null) {
                System.out.println("Debe indicar un autor.");
            }
            super.guardar(autor);
            System.out.println("Autor guardado.");
        } catch (Exception e) { 
            System.out.println("Error al crear un autor.");
            throw e;             
        } finally {
            desconectar();
        }  
    }   
        
    public Autor buscarPorId(Integer id) throws Exception {
        conectar();
        Autor idAutor = null;
        try {
            idAutor = (Autor) em.find(Autor.class, id);
            return idAutor;
        } catch (Exception e) {            
            System.out.println("No se encontró el ID del autor.");
            throw e;        
        } finally {
            desconectar();
        }        
    }
    
    public List<Autor> buscarPorNombre(String nombre) {
        conectar();
        List<Autor> listaAutor = null;
        try {
            listaAutor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                                   .setParameter("nombre", nombre).getResultList();
            return listaAutor;
        } catch (Exception e) {
            System.out.println("No se encontró el nombre del autor.");
            throw e;        
        } finally {
            desconectar();
        }    
    }
    
    public List<Autor> listarTodosAutores() throws Exception {
        conectar();
        List<Autor> getAllAutors = null;
        try {
            getAllAutors = em.createQuery("SELECT a FROM Autor a").getResultList();
            return getAllAutors;
        } catch (Exception e) { 
            System.out.println("No hay autores.");
            throw e;        
        } finally {
            desconectar();
        }               
    }
    
    public void eliminar(Integer id) throws Exception{
        Autor autor = null;
        try {
            if (autor == null) {
                System.out.println("Debe indicar un autor.");
            }
            autor = buscarPorId(id);
            super.eliminar(autor);
        } catch (Exception e) {            
            System.out.println("Error al eliminar autor.");
            throw e;        
        } finally {
            desconectar();
        }        
    }
    
}