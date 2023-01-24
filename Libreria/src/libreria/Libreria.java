package libreria;

import libreria.services.MenuService;

public class Libreria {

    public static void main(String[] args) throws Exception {
        
        MenuService menu = new MenuService();
        menu.menuPrincincipal();
    }

}