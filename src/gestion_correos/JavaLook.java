
package gestion_correos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class JavaLook {
    public static void main(String[] args) {
        ManejoUser user = new ManejoUser(100);
        
       LogIn login = new LogIn(user);
        Inicio inicio = new Inicio(user);
        
        inicio.setVisible(true);
    }
}
