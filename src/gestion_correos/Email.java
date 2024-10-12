/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;


public class Email {
    
    private String receptor;
    private String asunto;
    private String contenido;
    private boolean seleyo;
   
    
    public Email (String receptor, String asunto, String contenido){
        this.receptor=receptor;
        this.asunto=asunto;
        this.contenido=contenido;
        this.seleyo=false;
        
        
    }
    
    //Getters
    public String getReceptor(){
        return receptor;
    }
    
    public String getAsunto(){
        return asunto;
    }
    
    public String getContenido(){
        return contenido;
    }
    
    
    public boolean getLeido(){
        return seleyo;
    }
    
    public void leido(){
        this.seleyo=true;
    }
    
    public String print(){
        return "DESTINATARIO: "+receptor+"\n"+"ASUNTO: "+asunto;
    }
      
     
    
    
}
