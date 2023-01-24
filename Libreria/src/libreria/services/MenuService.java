package libreria.services;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

public class MenuService {
    
    private Scanner input = new Scanner(System.in).useDelimiter("\n");
    //private Scanner inputNum = new Scanner(System.in).useDelimiter("\n");
    private AutorService autorServ = new AutorService();
    private EditorialService editorialServ = new EditorialService();
    private LibroService libroServ = new LibroService();
    private Autor autor = new Autor();
    private Editorial editorial = new Editorial();
    private Libro libro = new Libro();
    private int aux;
    
    public void menuPrincincipal() throws Exception {
        
        int aux = 0;
        
        do {
            System.out.println(" --- MENU PRINCIPAL --- ");
            System.out.println(" 1 - Ingrese los datos del autor, editorial o libro");
            System.out.println(" 2 - Buscar autor por nombre.");
            System.out.println(" 3 - Buscar libro por ISBN.");
            System.out.println(" 4 - Buscar libro por título.");
            System.out.println(" 5 - Buscar libro por nombre de autor.");
            System.out.println(" 6 - Buscar libro por nombre de editorial");
            System.out.println(" 7 - Salir.");
            
            aux = input.nextInt();
           
            switch (aux) {
                case 1:
                    System.out.println(" --- subMENU --- ");
                    System.out.println(" 1 - Ingresar datos de un nuevo autor.");
                    System.out.println(" 2 - Ingresar datos de una nueva editorial.");
                    System.out.println(" 3 - Ingresar datos de un nuevo libro.");
                    System.out.println(" 4 - Salir del submenu.");
                    
                    int aux2 = input.nextInt();
                    
                    switch (aux2) {
                        case 1:
                            System.out.println("INGRESAR NUEVO AUTOR");
                            createAutorMS();
                            System.out.println("\n");
                            break;
                            
                        case 2:
                            System.out.println("INGRESAR NUEVA EDITORIAL");
                            createEditorialMS();
                            System.out.println("\n");
                            break;
                            
                        case 3:
                            System.out.println("INGRESAR NUEVO LIBRO.");
                            createLibroMS();
                            System.out.println("\n");
                            break;
                            
                        case 4:
                            System.out.println("VOLVER AL MENU PRINCIPAL");
                            break;
                            
                        default:
                            System.out.println("OPCION INCORRECTA");
                    }

                    break;

                case 2:
                    System.out.println("BUSCAR AUTOR POR NOMBRE");
                    Autor autor = (Autor) autorServ.getAutorByName(input.next());
                    System.out.println(autor + "\n");
                    break;
                    
                case 3:
                    System.out.println("BUSCAR LIBRO POR ISBN");
                    libro = libroServ.getLibroByIsbn(input.nextInt());
                    System.out.println(libro + "\n");
                    break;
                    
                case 4:
                    System.out.println("BUSCAR LIBRO POR TITULO");
                    String titulo = input.next();
                    libro = (Libro) libroServ.getLibroByTitle(titulo.trim());
                    System.out.println(libro + "\n");
                    break;
                    
                case 5:
                    System.out.println("BUSCAR NOMBRE DEL AUTOR DEL LIBRO");
                    String nombreAutor = input.nextLine();
                    libro = (Libro) libroServ.getAutorByNameLS(nombreAutor);
                    System.out.println(libro + "\n");
                    break;
                    
                case 6:
                    System.out.println("BUSCAR NOMBRE DE LA EDITORIAL DEL LIBRO");
                    String nombreEdit = input.nextLine();
                    libro = (Libro) libroServ.getEditorialByNameLS(nombreEdit);
                    System.out.println(libro);
                    break;
                    
                case 7:
                    System.out.println("SALIDA EXITOSA");
                    break;

                default:
                    System.out.println("OPCION INCORRECTA");

            }
        } while (aux != 7);
    }
    
    public void createAutorMS() {
        try {
            System.out.println("Ingrese el nombre del autor.");
            String nombre = input.next();
            autor = (Autor) autorServ.getAutorByName(nombre);
            
            if(autor == null) {
                autor = autorServ.createAutor(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void createEditorialMS() {
        try {
            System.out.println("Ingrese el nombre de la editorial.");
            String nombre = input.next();
            editorial = (Editorial) editorialServ.getEditorialByName(nombre);
            
            if(editorial == null) {
                editorial = editorialServ.createEditorial(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
//    String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
//            Integer ejemplaresRestantes, /*Boolean alta,*/ Autor autor, Editorial editorial
    
    public void createLibroMS() {
        try {
            System.out.println("Ingrese el título del libro.");
            String titulo = input.next();
            
            System.out.println("Ingrese el año de edición del libro.");
            Integer anio = input.nextInt();
            
            System.out.println("Ingrese la cantidad de ejemplares del libro.");
            Integer ejemplares = input.nextInt();
            
            System.out.println("Ingrese la cantidad de ejemplares prestados del libro.");
            Integer ejemplaresPrestados = input.nextInt();
            
            System.out.println("Ingrese la cantidad de ejemplares restantes del libro.");
            Integer ejemplaresRestantes = input.nextInt();
            createAutorMS();
            createEditorialMS();
            
            if(libro == null) {
                libro = libroServ.createLibro(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
            }
//            libro = (Libro) libroServ.createLibro(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
