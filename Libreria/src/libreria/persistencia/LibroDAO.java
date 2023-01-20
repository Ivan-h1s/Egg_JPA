package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro>{
    
    @Override
    public void guardar(Libro libro) {
        try {
            if (libro == null) {
                System.out.println("Debe indicar el nombre del libro.");
            }
            super.guardar(libro);
            System.out.println("Libro guardado.");
        } catch (Exception e) { 
            System.out.println("Error al crear un libro.");
            throw e;             
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
            System.out.println("No se encontró el ISBN del libro.");
            throw e;        
        } finally {
            desconectar();
        }        
    }
    
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        conectar();
        List<Libro> listaTitulos = null;
        try {
            listaTitulos = em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE :nombre")
                                   .setParameter("nombre", titulo).getResultList();
            return listaTitulos;
        } catch (Exception e) {
            System.out.println("No se encontró el titulo del libro.");
            throw e;        
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
            System.out.println("No hay libros.");
            throw e;        
        } finally {
            desconectar();
        }               
    }
    
    public void eliminarLibro(Integer isbn) throws Exception{
        Libro libro = null;
        try {
            if (libro == null) {
                System.out.println("Debe indicar un isbn.");
            }
            libro = buscarPorIsbn(isbn);
            super.eliminar(libro);
        } catch (Exception e) {            
            System.out.println("Error al eliminar un libro.");
            throw e;        
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
            System.out.println("No se encontró el autor del libro.");
            throw e;        
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
            System.out.println("No se encontró la editorial.");
            throw e;        
        } finally {
            desconectar();
        }    
    }
    
}