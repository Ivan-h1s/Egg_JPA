package libreria.services;

import java.util.List;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialService {
    
    private final EditorialDAO dao;

    public EditorialService() {
        this.dao = new EditorialDAO();
    }
    
    public Editorial createEditorial(String nombre) throws Exception{
        
        Editorial editorial = new Editorial();
        try {
            if(nombre == null) {
                throw new Exception("Error. Debe ingresar un nombre");
            }
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            dao.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Editorial getEditorialById(Integer id) {
        try {
            return dao.buscarEditPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Editorial> getEditorialByName (String nombre) {
        try {
            return dao.buscarEditPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Editorial> getAllEditoriales() {
        try {
            return dao.listarTodasEditoriales();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteEditorialById (Integer id) {
        try {
            dao.eliminarEdit(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}