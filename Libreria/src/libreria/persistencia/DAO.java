// Esta clase padre nos permite heredar métodos comunes con el fin de reutilizar código
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> extends Object{
    
    protected EntityManagerFactory EMF = Persistence.createEntityManagerFactory("LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();
    
    // método para conectar con la base de datos.
    // verifica si la conexión está realizada, en caso contrario, se realiza en la línea 18
    protected void conectar() {
        if(!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }
    
    // método para desconectarnos de la base de datos
    // verifica si existe una conexión, y de ser el caso, se 
    // cierra la conexión y se desconecta el programa con la base de datos.    
    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    // método para persistir un objeto en la base de datos.
    // Toma como parámetro el objeto a persistir, vemos que es genérico, por
    // lo que puede aceptar cualquier tipo de objeto (Autor, LIbro, Editorial)
    protected void guardar(T objeto) {//create
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        
        em.getTransaction().commit();       
        desconectar();       
    }
    
    // método para modificar una tupla de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    protected void editar(T objeto) {//update
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        
        em.getTransaction().commit();
        desconectar();
    }
    
    // método para eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    protected void eliminar(T objeto) {//delete
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        
        em.getTransaction().commit();
        desconectar();
    }

}