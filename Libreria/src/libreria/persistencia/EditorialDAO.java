package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial>{
    
    @Override
    public void guardar(Editorial editorial) {
        try {
            if (editorial == null) {
                System.out.println("Debe indicar una editorial.");
            }
            super.guardar(editorial);
            System.out.println("Editorial guardada.");
        } catch (Exception e) { 
            System.out.println("Error al crear una editorial.");
            throw e;             
        } finally {
            desconectar();
        }  
    }  
    
    public Editorial buscarEditPorId(Integer id) throws Exception {
        conectar();
        Editorial idEditorial = null;
        try {
            idEditorial = (Editorial) em.find(Editorial.class, id);
            return idEditorial;
        } catch (Exception e) {            
            System.out.println("No se encontró el ID de la editorial.");
            throw e;        
        } finally {
            desconectar();
        }        
    }
    
    public List<Editorial> buscarEditPorNombre(String nombre) {
        conectar();
        List<Editorial> listaEditorial = null;
        try {
            listaEditorial = em.createQuery("SELECT a FROM Editorial a WHERE a.nombre LIKE :nombre")
                                   .setParameter("nombre", nombre).getResultList();
            return listaEditorial;
        } catch (Exception e) {
            System.out.println("No se encontró el nombre de la editorial.");
            throw e;        
        } finally {
            desconectar();
        }    
    }
    
    public List<Editorial> listarTodasEditoriales() throws Exception {
        conectar();
        List<Editorial> allEditorials = null;
        try {
            allEditorials = em.createQuery("SELECT a FROM Editorial a").getResultList();
            return allEditorials;
        } catch (Exception e) { 
            System.out.println("No hay editoriales.");
            throw e;        
        } finally {
            desconectar();
        }               
    }
    
    public void eliminarEdit(Integer id) throws Exception{
        Editorial editorial = null;
        try {
            if (editorial == null) {
                System.out.println("Debe indicar una editorial.");
            }
            editorial = buscarEditPorId(id);
            super.eliminar(editorial);
        } catch (Exception e) {            
            System.out.println("Error al eliminar editorial.");
            throw e;        
        } finally {
            desconectar();
        }        
    }
}
