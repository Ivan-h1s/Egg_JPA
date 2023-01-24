package libreria.services;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroService {
    
    private final LibroDAO dao;
    private Autor autor = new Autor();
    private AutorService autorServ = new AutorService();
    private Editorial editorial = new Editorial();
    private EditorialService editorialServ = new EditorialService();
    
    public LibroService() {
        this.dao = new LibroDAO();
    }
    
     // este método persiste un registro de tipo Libro en la base de datos
    // a través del método guardar() de la clase DAO.
    public Libro  createLibro(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
            Integer ejemplaresRestantes, /*Boolean alta,*/ Autor autor, Editorial editorial) throws Exception{
            
        Libro libro = new Libro();
        
        try {
            if(titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Error. Debe ingresar el título.");
            }
            if(anio == null) {
                throw new Exception("Error. Debe ingresar el año de edición.");
            }
            if(ejemplares == null) {
                throw new Exception("Error. Debe ingresar el número de ejemplares.");
            }
            if (ejemplaresPrestados == null) {
                throw new Exception("Error. Debe ingresar el número de ejemplares prestados.");
            }
            if (autor == null) {
                throw new Exception("Error. Debe ingresar el autor.");
            }
            if (editorial == null) {
                throw new Exception("Error. Debe ingresar la editorial.");
            }            
            
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(true);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            dao.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Libro getLibroByIsbn(Integer id) {
        try {
            return dao.buscarPorIsbn(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Libro> getLibroByTitle(String nombre) {
        try {
            return dao.buscarLibroPorTitulo(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Libro> getAllLibros() {
        try {
            return dao.listarTodosLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteLibroByIsbn (Integer id) {
        try {
            dao.eliminarLibro(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Libro> getAutorByNameLS(String nombreAutor) throws Exception {
        try {
            return dao.buscarAutor(nombreAutor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }        
    }
    
    public List<Libro> getEditorialByNameLS(String nombreEdit) throws Exception {
        try {
            return dao.buscarEdit(nombreEdit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }        
    }
}
