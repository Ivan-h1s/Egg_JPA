package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro>{
    
    @Override
    public void guardar(Libro libro) {
        try {
            super.guardar(libro);
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            e.printStackTrace();            
        } finally {
            desconectar();
        }  
    }  
    
    public Libro buscarPorIsbn(Integer isbn) throws Exception {
        conectar();
        Libro libro = null;
        try {
            libro = (Libro) em.find(Libro.class, isbn);
            return libro;
        } catch (Exception e) {            
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;       
        } finally {
            desconectar();
        }        
    }
    
    public List<Libro> buscarLibroPorTitulo(String titulo) throws Exception {
        conectar();
        List<Libro> listaTitulos = null;
        try {
            listaTitulos = em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE :nombre")
                                   .setParameter("nombre", titulo).getResultList();
            return listaTitulos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;       
        } finally {
            desconectar();
        }    
    }
    
    public List<Libro> listarTodosLibros() throws Exception {
        conectar();
        List<Libro> books = null;
        try {
            books = em.createQuery("SELECT a FROM Libro a").getResultList();
            return books;
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;        
        } finally {
            desconectar();
        }               
    }
    
    public void eliminarLibro(Integer isbn) throws Exception{
        Libro libro = null;
        try {
            libro = buscarPorIsbn(isbn);
            super.eliminar(libro);
        } catch (Exception e) {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            desconectar();
        }        
    }
    
    public List<Libro> buscarAutor(String nombreAutor) throws Exception {
        conectar();
        List <Libro> libro= null;
        try {
            libro = em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre")
                                   .setParameter("nombre", nombreAutor).getResultList();
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;       
        } finally {
            desconectar();
        }    
    }
    
    public List<Libro> buscarEdit(String nameEditorial) throws Exception {
        conectar();
        List <Libro> libro= null;
        try {
            libro = em.createQuery("SELECT li FROM Libro li WHERE li.editorial.nombre LIKE :nombre")
                                   .setParameter("nombre", nameEditorial).getResultList();
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;      
        } finally {
            desconectar();
        }    
    }
    
}