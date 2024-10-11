/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_programacion2;

/**
 *
 * @author angel
 */
public class Email {
            private String remitente;
    private String asunto;
    private String contenido;
    private boolean leido;
    public Email(String remitente, String asunto, String contenido){
        this.remitente=remitente;
        this.asunto=asunto;
        this.contenido=contenido;
        this.leido=false;
    }
    public String getRemitente(){
        return remitente;
    }
    public String getAsunto(){
        return asunto;
    }
    public String getContenido(){
        return contenido;
    }
    public boolean getLeido(){
        return leido;
    }
    public void setLeido(){
        leido=true;
    }
    public String print(){
        return "DE: "+remitente+" ASUNTO: "+asunto+" "+contenido;
    }
}
