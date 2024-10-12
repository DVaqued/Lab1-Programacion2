/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dell
 */
public class LogIn extends JFrame {
  
    private JTextField userField;
     private JTextField passField;
    private JButton loginButton, atras;
    private  ManejoUser user;

    public LogIn (ManejoUser user) {
        this.user=user;
        setTitle("Direccion de Correo");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Correo Electronico: < FAVOR NO AGREGAR @mail.com >");
        userField = new JTextField();
        panel.add(userLabel);
        panel.add(userField);

        
        JLabel passLabel = new JLabel("Contraseña:");
        passField = new JPasswordField();
        panel.add(passLabel);
        panel.add(passField);

      
        loginButton = new JButton("Iniciar Sesión");
        panel.add(loginButton);
       
         atras = new JButton("Atras");
         panel.add(atras);

       
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = userField.getText();
                String password = passField.getText();

                if (user != null) {
                    boolean valid = user.validarCredenciales((mail+ "@mail.com"), password);
                    if (valid==true) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                        LogIn.this.setVisible(false);
                        new Menu_Principal(user).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El sistema de gestión de usuarios no está disponible.");
                }
            
                
               
            }
        });
        
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn.this.setVisible(false); 
            }
        });

        add(panel);
    
    }
    
    /* public static void main(String[] args) {
        new LogIn(user).setVisible(true);
    }*/
}



