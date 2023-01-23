package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial>{
    
    @Override
    public void guardar(Editorial editorial) {
        try {
            super.guardar(editorial);          
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            e.printStackTrace();             
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
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            desconectar();
        }        
    }
    
    public List<Editorial> buscarEditPorNombre(String nombre) throws Exception {
        conectar();
        List<Editorial> listaEditorial = null;
        try {
            listaEditorial = em.createQuery("SELECT a FROM Editorial a WHERE a.nombre LIKE :nombre")
                                   .setParameter("nombre", nombre).getResultList();
            return listaEditorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;       
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
            System.out.println(e.getMessage());
            e.printStackTrace(); 
            return null;
        } finally {
            desconectar();
        }               
    }
    
    public void eliminarEdit(Integer id) throws Exception{
        Editorial editorial = null;
        try {
            editorial = buscarEditPorId(id);
            super.eliminar(editorial);
        } catch (Exception e) {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            desconectar();
        }        
    }
    
}