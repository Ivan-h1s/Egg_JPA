package libreria.persistencia;

import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardarAutor(Autor autor) {
        if (autor == null) {
            System.out.println("Debe indicar un autor");
        } else {            
        super.guardar(autor);
            System.out.println("Autor guardado");
        }
    }
    
//    public Autor buscarPorId(String id) throws Exception {
//        conectar();
//        Autor autor = em.find(Autor.class, id);
//        desconectar();
//        return autor;
   // }
    
    
//    public void eliminar(String id) throws Exception {
//        Autor autor = bus
//    }
//    public List<Autor> buscarPorNombre(String nombre) {
//        List<Autor> listaAutor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
//                                   .setParameter("nombre", nombre).getResultList();
//        return listaAutor;
//    }
}
