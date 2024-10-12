/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CrearAccount extends JFrame {
    private JTextField mailT, nombreT, passT;
   // private JPasswordField passField;
    private JButton registerButton, atras;
    private  ManejoUser user;
   
    public CrearAccount(ManejoUser user) {
      this.user=user;
       
       setTitle("Registro de Usuario");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(5, 2));

       
        JLabel avisoL = new JLabel("MAIL.COM", SwingConstants.CENTER); 
        panelPrincipal.add(avisoL, BorderLayout.NORTH); 

        JLabel mailL = new JLabel("Direccion de Correo: < FAVOR NO AGREGAR @mail.com >");
        mailT = new JTextField();
        panelCampos.add(mailL);
        panelCampos.add(mailT);

        JLabel nombreL = new JLabel("Nombre:");
        nombreT = new JTextField();
        panelCampos.add(nombreL);
        panelCampos.add(nombreT);

        JLabel passL = new JLabel("Contraseña:");
        passT = new JTextField();
        panelCampos.add(passL);
        panelCampos.add(passT);

        registerButton = new JButton("Registrar");
        panelCampos.add(registerButton);
        
        atras = new JButton("Atras");
        panelCampos.add(atras);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        add(panelPrincipal);


      
  registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreT.getText();
                String password = passT.getText();
                String mail = mailT.getText();
                
                
                
                     if (!nombre.isEmpty()
                        && !mail.isEmpty()
                        && !password.isEmpty() ) {

                          if (user != null) {
                        if (!user.usuarioExiste(mail + "@mail.com" )) {
                            
                            if (user.agregarUser((mail + "@mail.com"), nombre, password)) {
                                JOptionPane.showMessageDialog(null, "Registrado Exitosamente");
                                
                                  CrearAccount.this.setVisible(false);
                                   new Menu_Principal(user).setVisible(true);
                               
                            } else {
                                JOptionPane.showMessageDialog(null, "Algo salio mal, intenta de nuevo");
                                   }

                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario existente");
                        } 


                    } else if (nombre.isEmpty() || mail.isEmpty() || password.isEmpty() ) {
                            JOptionPane.showMessageDialog(null, "¡Error! Asegúrese de llenar todos los campos");

                            }
                  } else {
                    JOptionPane.showMessageDialog(null, "Error: ManejoUser no inicializado.");
                }
                         
                   
                } 
            
        });
  
    atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearAccount.this.setVisible(false); 
            }
        });

        add(panelPrincipal);
    
    }
      /* public static void main(String[] args) {
        new CrearAccount(user).setVisible(true);*/
    }

    
  
  

