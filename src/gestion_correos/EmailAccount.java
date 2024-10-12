/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;

import javax.swing.JOptionPane;


public class EmailAccount {
    
    private String  nombre;
    private String  direccion;
    private String  contrasena;
    
    private ManejoUser user;
    
    private Email [] inbox;
    private int contadorI;
    private int pos;

    public EmailAccount(String direccion, String nombre, String contrasena, ManejoUser user) {
        this.direccion = direccion; 
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.user=user;
        
        this.inbox=new Email[50];
        this.contadorI=0;
    }

    public EmailAccount getUser(){
        return user.obtenerUsuarioActual();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDirec() {
        return direccion;
    }

    public void setDirec(String direccion) {
        this.direccion = direccion;
    }
    
    
     public boolean recibirEmail(Email em){
           for (int i = 0; i < inbox.length; i++) {
               if (inbox[i]==null) {
                   inbox[i]=em; 
                   return true;
               }
           } return false;
       } 
       
        public String printInbox() {
                StringBuilder result = new StringBuilder(); 
                result.append("CORREO: ").append(direccion).append("   - NOMBRE: ").append(nombre).append("\n\n");

                int correosSinLeer = 0;
                int totalCorreos = 0;

                for (int i = 0; i < inbox.length; i++) {
                    if (inbox[i] != null) {
                        totalCorreos++;
                        String estado = inbox[i].getLeido() ? "LEIDO" : "SIN LEER";
                        result.append((i + 1)).append(" - ").append(inbox[i].getReceptor()).append(" - ")
                              .append(inbox[i].getAsunto()).append(" - ").append(estado).append("\n");

                        if (!inbox[i].getLeido()) {
                            correosSinLeer++;
                        }
                    }
                }

        result.append("Correos sin leer: ").append(correosSinLeer).append("\n");
        result.append("Total de correos recibidos: ").append(totalCorreos).append("\n");

        return result.toString();
    }

       
      public void leerEmail(int pos) {
        int index = pos - 1;  
        if (index >= 0 && index < inbox.length && inbox[index] != null) {
            JOptionPane.showMessageDialog(null, "DESTINATARIO: " + inbox[index].getReceptor() 
                                                + "\nASUNTO: " + inbox[index].getAsunto()
                                                + "\nCONTENIDO: " + inbox[index].getContenido());  
            inbox[index].leido();  
        } else {
            JOptionPane.showMessageDialog(null, "Correo No Existe");
        }
    }
       
       public void borrarLeidos(){
            int nuevaPos = 0;

            for (int i = 0; i < inbox.length; i++) {
                if (inbox[i] != null && !inbox[i].getLeido()) {
                    inbox[nuevaPos] = inbox[i];
                    nuevaPos++;
                }
            }

            for (int i = nuevaPos; i < inbox.length; i++) {
                inbox[i] = null;
            }

            JOptionPane.showMessageDialog(null, "Correos leídos eliminados exitosamente.");
       }
       
       public String CorreosLeidos() {
            StringBuilder result = new StringBuilder();
            int contadorLeidos = 0;

            for (int i = 0; i < inbox.length; i++) {
                if (inbox[i] != null && inbox[i].getLeido()) {
                    result.append((i + 1)).append(" - ").append(inbox[i].getAsunto()).append(" - LEÍDO\n");
                    contadorLeidos++;
                }
            }

            if (contadorLeidos == 0) {
                return "No hay correos leídos.";
            } else {
                return "Correos leídos:\n" + result.toString();
            }
    }

    
       
        public boolean cerrarSesion() {
        if (user.obtenerUsuarioActual() != null) {
            user.setUserActual(null);
            return true;
        }
        return false;
    }
   
    
    
    
}
