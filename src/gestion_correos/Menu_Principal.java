/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Principal extends JFrame {

    private JButton verInboxB, mandarCorreoB, leerCorreoB, limpiarInboxB, cerrarSesionB;
    private JTextArea areaMensajes;
    private JLabel estadoLabel;
    //private EmailAccount user;
    private ManejoUser user;
    private ManejoUser us;

    private String accountActual;

    public Menu_Principal(ManejoUser user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        this.user = user;
        this.accountActual = user.obtenerUsuarioActual().getDirec();

        setTitle("Menú Principal");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));

        System.out.println("User act " + user.obtenerUsuarioActual().getDirec());
        System.out.println("cobtrasena " + user.obtenerUsuarioActual().getContrasena());

        verInboxB = new JButton("Ver mi Inbox");
        mandarCorreoB = new JButton("Mandar Correo");
        leerCorreoB = new JButton("Leer un Correo");
        limpiarInboxB = new JButton("Limpiar mi Inbox");
        cerrarSesionB = new JButton("Cerrar Sesión");

        panelBotones.add(verInboxB);
        panelBotones.add(mandarCorreoB);
        panelBotones.add(leerCorreoB);
        panelBotones.add(limpiarInboxB);
        panelBotones.add(cerrarSesionB);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMensajes);

        estadoLabel = new JLabel("Sesión iniciada: " + accountActual);

        panelPrincipal.add(panelBotones, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(estadoLabel, BorderLayout.SOUTH);

        add(panelPrincipal);

        agregarListeners();
    }

    private void agregarListeners() {
        // Ver inbox
        verInboxB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaMensajes.setText(user.obtenerUsuarioActual().printInbox());
            }
        });

        mandarCorreoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mandarCorreo();
            }
        });

        leerCorreoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerCorreo();
            }
        });

        limpiarInboxB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarInbox();
            }
        });

        cerrarSesionB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

    }

   
   

   
    private void mandarCorreo() {
        JTextField destinatarioField = new JTextField();
        JTextField asuntoField = new JTextField();
        JTextArea contenidoField = new JTextArea(5, 20);

        JPanel panelCorreo = new JPanel(new GridLayout(0, 1));
        panelCorreo.add(new JLabel("Destinatario: < FAVOR AGREGAR @mail.com >"));
        panelCorreo.add(destinatarioField);
        panelCorreo.add(new JLabel("Asunto:"));
        panelCorreo.add(asuntoField);
        panelCorreo.add(new JLabel("Contenido:"));
        panelCorreo.add(new JScrollPane(contenidoField));

        int result = JOptionPane.showConfirmDialog(this, panelCorreo,
                "Mandar Correo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String destinatario = destinatarioField.getText();
            String asunto = asuntoField.getText();
            String contenido = contenidoField.getText();

             
            if (destinatario.isEmpty() || asunto.isEmpty() || contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                               
            } else {
                


                if(user.enviarEmail(user.obtenerUsuarioActual().getDirec(), destinatario, asunto, contenido)==true){
                    JOptionPane.showMessageDialog(this, "Correo enviado a " + destinatario + " con éxito.");
                }else{
                    JOptionPane.showMessageDialog(this, "El destinatario no existe");
                }

            }
        }
    }

   
    private void leerCorreo() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el número de correo que desea leer:");

       if (input != null && !input.trim().isEmpty()) { 
           try {
               int num = Integer.parseInt(input);
               EmailAccount usuarioActual = user.obtenerUsuarioActual();

               if (usuarioActual == null) {
                   JOptionPane.showMessageDialog(this, "No hay usuario autenticado.");
                   return;
               }

               if (num > 0 && num <= 50) {  
                   areaMensajes.setText("");  
                   usuarioActual.leerEmail(num);  
               } else {
                   JOptionPane.showMessageDialog(this, "El número ingresado está fuera de rango.");
               }
           } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.");
           }
       } else {
           JOptionPane.showMessageDialog(this, "Debe ingresar un número.");
       }

    }

   
    private void limpiarInbox() {
          EmailAccount usuarioActual = user.obtenerUsuarioActual(); 

        if (usuarioActual != null) {
            
            String correosLeidos = usuarioActual.CorreosLeidos();

            areaMensajes.setText("Correos leídos:\n" + correosLeidos);

            usuarioActual.borrarLeidos();

            areaMensajes.append("\nLos correos leídos han sido eliminados.");
        } else {
            areaMensajes.setText("No hay un usuario activo o no se encontró la bandeja de entrada.");
        }

    }

    private void cerrarSesion() {
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de que desea cerrar la sesión?", 
            "Confirmar cierre de sesión", 
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (user.obtenerUsuarioActual().cerrarSesion()) { 
                this.dispose(); 
                new Inicio(user).setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(null, "No hay ninguna sesión activa.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
       
            
    }
}
