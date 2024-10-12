/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_correos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame{

    private JButton loginB, registroB;
    private ManejoUser user;
    
    public Inicio(ManejoUser user) {
        this.user=user;
        setTitle("Submenu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        loginB = new JButton("Login");
        registroB = new JButton("Registro");

        panelPrincipal.add(loginB);
        panelPrincipal.add(registroB);

        add(panelPrincipal);

        agregarListeners();
    }

    private void agregarListeners() {
        loginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn log = new LogIn(user);
                log.setVisible(true);
            }
        });

        // Registro
        registroB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearAccount crear = new CrearAccount(user);
                crear.setVisible(true);
            }
        });

        
    }

    
    }



    

