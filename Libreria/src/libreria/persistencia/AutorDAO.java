package libreria.persistencia;

import java.util.List;

import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardar(Autor autor) {
        try {
            super.guardar(autor);
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            e.printStackTrace();
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
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;      
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
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;       
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
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;      
        } finally {
            desconectar();
        }               
    }
    
    public void eliminar(Integer id) throws Exception{
        Autor autor = null;
        try {
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