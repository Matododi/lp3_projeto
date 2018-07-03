/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import tools.CentroDoMonitorMaior;

/**
 *
 * @author Sandro
 */
public class GUIMenu extends JFrame {

    ImageIcon iconeLogo = new ImageIcon(getClass().getResource("/icones/logo.png"));
    JLabel logo = new JLabel(iconeLogo);

    public GUIMenu() {
        setTitle("Menu Example");
        Container cp = getContentPane();
        cp = getContentPane();
        cp.add(logo);
        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();

        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);

        // Define e adiciona dois menus drop down na barra de menus
        JMenu fileMenu = new JMenu("GUIs");
        menuBar.add(fileMenu);

        // Cria e adiciona um item simples para o menu
        JMenuItem genero = new JMenuItem("GUIGenero");
        JMenuItem editora = new JMenuItem("GUIEditora");
        JMenuItem livro = new JMenuItem("GUILivro");
        JMenuItem cliente = new JMenuItem("GUICliente");
        JMenuItem venda = new JMenuItem("GUIVenda");
        JMenuItem autor = new JMenuItem("GUIAutor");

        // Cria e aiciona um CheckButton como um item de menu
        // Cria e aiciona um RadioButton como um item de menu
        // Cria um ButtonGroup e adiciona os dois radio Button
        fileMenu.add(livro);
        fileMenu.add(genero);
        fileMenu.add(editora);
        fileMenu.addSeparator();
        fileMenu.add(cliente);
        fileMenu.addSeparator();
        fileMenu.add(venda);
        fileMenu.add(autor);
        setVisible(true);

        livro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUILivro guiLivro = new GUILivro();
            }
        });

        genero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIGenero guiGenero = new GUIGenero();
            }
        });

        editora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIEditora guiEditora = new GUIEditora();
            }
        });

        cliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUICliente guiCliente = new GUICliente();
            }
        });

        venda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIVenda guiVenda = new GUIVenda();
            }
        });
        
        autor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIAutor guiAutor = new GUIAutor();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        

        
        pack();
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
    }
}
