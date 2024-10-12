/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gestion_correos;

/**
 *
 * @author Dell
 */
public class ManejoUser {
    private EmailAccount usuarios[];
    private int contadorUsers;
    private EmailAccount usuarioActual;
  
    
   public ManejoUser(int cantidad){
    
      usuarios = new EmailAccount[cantidad]; 
      contadorUsers=0;
      usuarioActual = null;
   }
   
   public EmailAccount obtenerUsuario(String mail) {
        for (int i = 0; i <contadorUsers ; i++) {
            if (usuarios[i] != null && usuarios[i].getDirec().equals(mail)) {
                return usuarios[i];
            }
        }
        return null;
    }
   
     public EmailAccount obtenerUsuarioActual() {
    if (usuarioActual == null) {
        System.out.println("No hay un usuario actual");
        return null;  
    }
    return usuarioActual;
}
     
     public void setUserActual(EmailAccount UserActual){
         usuarioActual= UserActual;
     }
  
      public boolean usuarioExiste(String mail) {
        for (int i = 0; i < contadorUsers; i++) {
            if (usuarios[i] != null && usuarios[i].getDirec().equals(mail )) {
                return true;
            }
        }
        return false;
    }
   
    public  boolean agregarUser (String direccion, String nombre, String contrasena){
        if (contadorUsers < usuarios.length) {
            EmailAccount nuevoUsuario = new EmailAccount (direccion, nombre, contrasena, this);
            usuarios[contadorUsers] = nuevoUsuario;
            contadorUsers++;

            usuarioActual = nuevoUsuario;
            return true;
        } else {
            System.out.println("No se puede agregar más usuarios.");
            return false;
        }
        
     }
   
    public boolean validarCredenciales(String mail, String contrasena) {
        EmailAccount usuario = obtenerUsuario(mail);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            usuarioActual = usuario;
            return true;
        }
        return false;
    }
    
       public boolean enviarEmail(String remitente, String receptor, String asunto, String contenido) {
        EmailAccount cuentaRemitente = obtenerUsuario(remitente);
        EmailAccount cuentaReceptor = obtenerUsuario(receptor);

        if (cuentaReceptor == null) {
            System.out.println("El destinatario no existe.");
            return false;
        }

        Email nuevoEmail = new Email(remitente, asunto, contenido);

        return cuentaReceptor.recibirEmail(nuevoEmail);
    }
   
    
     
   
}
