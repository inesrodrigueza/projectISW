package icai.dtc.isw.ui;

import icai.dtc.isw.client.Client;
import icai.dtc.isw.dao.ConnectionDAO;
import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class JVentana extends JFrame {
    public static void main(String[] args) {
        new JVentana();
    }
    private int id;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblContrasena;
    private JLabel lblErrorNombre;
    private JLabel lblErrorContrasena;
    private JLabel lblInfo;
    private JTextField txtNombre;
    private JTextField txtContrasena;
    private JButton btnEntrar;
    private JButton btnRegistro;
    private JButton btnVolver;
    private JButton btnAceptarRegistro;
    private JButton btnAceptarEntrada;
    public JVentana() {
        super("eXchange");
        this.setLayout(new BorderLayout());
        //Título
        JPanel pnlNorte = new JPanel();
        lblTitulo = new JLabel("Inicio", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 40));
        pnlNorte.add(lblTitulo);
        this.add(pnlNorte, BorderLayout.NORTH);

        //Botones centrales
        JPanel pnlCentro = new JPanel();
        btnEntrar = new JButton("Autenticarse");
        btnEntrar.setBounds(310,150,180,100);
        btnEntrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JVentana.this.cambiarEntrar();
            }
        });
        this.add(btnEntrar);

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(310,300,180,100);
        btnRegistro.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JVentana.this.cambiarRegistro();
            }
        });
        this.add(btnRegistro);

        //Botón volver
        btnVolver = new JButton("< Volver");
        btnVolver.setBounds(20,20,100,40);
        btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnVolver.setVisible(false);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JVentana.this.cambiarInicio();
            }
        });
        this.add(btnVolver);

        //Casillas texto
        lblNombre = new JLabel("Introduzca el nombre de usuario: ");
        lblNombre.setBounds(240,90,400,80);
        lblNombre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lblNombre.setVisible(false);
        this.add(lblNombre);

        lblContrasena = new JLabel("Introduzca la contraseña: ");
        lblContrasena.setBounds(270,210,400,80);
        lblContrasena.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lblContrasena.setVisible(false);
        this.add(lblContrasena);

        txtNombre = new JTextField("");
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        txtNombre.setBounds(220,160,350,40);
        txtNombre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        txtNombre.setVisible(false);
        this.add(txtNombre);

        txtContrasena = new JTextField("");
        txtContrasena.setHorizontalAlignment(JTextField.CENTER);
        txtContrasena.setBounds(220,280,350,40);
        txtContrasena.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        txtContrasena.setVisible(false);
        this.add(txtContrasena);

        //Botones aceptar
        btnAceptarEntrada = new JButton("Entrar");
        btnAceptarEntrada.setBounds(330,340,120,40);
        btnAceptarEntrada.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnAceptarEntrada.setVisible(false);
        btnAceptarEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JVentana.this.verificarEntrar();
            }
        });
        this.add(btnAceptarEntrada);

        btnAceptarRegistro = new JButton("Registrar");
        btnAceptarRegistro.setBounds(330,340,120,40);
        btnAceptarRegistro.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnAceptarRegistro.setVisible(false);
        btnAceptarRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JVentana.this.verificarRegistrar();
            }
        });
        this.add(btnAceptarRegistro);

        lblInfo = new JLabel("");
        lblInfo.setBounds(300,390,400,50);
        lblInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        lblInfo.setVisible(false);
        this.add(lblInfo);

        //Errores
        lblErrorNombre = new JLabel("Introduce un nombre de usuario válido");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setBounds(230,380,400,50);
        lblErrorNombre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        lblErrorNombre.setVisible(false);
        this.add(lblErrorNombre);

        lblErrorContrasena = new JLabel("Introduce una contraseña válida");
        lblErrorContrasena.setForeground(Color.RED);
        lblErrorContrasena.setBounds(260,380,400,50);
        lblErrorContrasena.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        lblErrorContrasena.setVisible(false);
        this.add(lblErrorContrasena);

        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.	Y_AXIS));
        this.add(pnlCentro, BorderLayout.CENTER);

        this.setSize(800,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void cambiarEntrar(){
        btnEntrar.setVisible(false);
        btnRegistro.setVisible(false);
        btnVolver.setVisible(true);
        lblTitulo.setText("Autenticarse");
        lblNombre.setVisible(true);
        lblContrasena.setVisible(true);
        txtNombre.setVisible(true);
        txtContrasena.setVisible(true);
        btnAceptarEntrada.setVisible(true);
    }
    public void cambiarRegistro(){
        btnVolver.setVisible(true);
        btnEntrar.setVisible(false);
        btnRegistro.setVisible(false);
        lblTitulo.setText("Registrarse");
        lblNombre.setVisible(true);
        lblContrasena.setVisible(true);
        txtNombre.setVisible(true);
        txtContrasena.setVisible(true);
        btnAceptarRegistro.setVisible(true);
    }
    public void cambiarInicio(){
        btnEntrar.setVisible(true);
        btnRegistro.setVisible(true);
        btnVolver.setVisible(false);
        lblTitulo.setText("Inicio");
        lblNombre.setVisible(false);
        lblContrasena.setVisible(false);
        txtNombre.setVisible(false);
        txtNombre.setText("");
        txtContrasena.setVisible(false);
        txtContrasena.setText("");
        btnAceptarEntrada.setVisible(false);
        btnAceptarRegistro.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorContrasena.setVisible(false);
        lblInfo.setVisible(false);
    }

    public void verificarEntrar(){
        lblInfo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorContrasena.setVisible(false);
        if(Objects.equals(txtNombre.getText(), "")){
            lblErrorNombre.setVisible(true);
        }
        else if(Objects.equals(txtContrasena.getText(), "")){
            lblErrorContrasena.setVisible(true);
        }
        else{
            this.entrarUsuario(txtNombre.getText(), txtContrasena.getText());
        }
    }

    public void verificarRegistrar(){
        lblInfo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorContrasena.setVisible(false);
        boolean registrado = false;
        if(Objects.equals(txtNombre.getText(), "")){
            lblErrorNombre.setVisible(true);
        }
        else if(Objects.equals(txtContrasena.getText(), "")){
            lblErrorContrasena.setVisible(true);
        }
        else{
            ArrayList<Customer> lista= new ArrayList<>();
            CustomerDAO.getClientes(lista);

            for (Customer customer : lista) {
                if(Objects.equals(customer.getName(), txtNombre.getText())) {
                    registrado = true;
                }
            }
            if(!registrado) {
                this.registrarUsuario(txtNombre.getText(), txtContrasena.getText());
            }
            else{
                lblInfo.setText("Usuario ya registrado");
                lblInfo.setVisible(true);
            }
        }
    }
    public void registrarUsuario(String nombre, String contrasena) {
        Connection con= ConnectionDAO.getInstance().getConnection();
        Customer cu=null;
        try (PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios VALUES ('"+contrasena+"','"+nombre+"');")){
            lblInfo.setText("Usuario registrado con éxito");
            lblInfo.setVisible(true);
            ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void entrarUsuario(String nombre, String id){
        boolean encontrado = false;
        Connection con= ConnectionDAO.getInstance().getConnection();
        Customer cu=null;
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios WHERE nombre='"+nombre+"'");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                cu= new Customer(rs.getString(1),rs.getString(2));
                if(Objects.equals(cu.getId(), id)){
                    encontrado = true;
                    lblInfo.setText("Bienvenido");
                    lblInfo.setVisible(true);
                }
                else{
                    encontrado = true;
                    lblInfo.setText("Contraseña incorrecta");
                    lblInfo.setVisible(true);
                }
            }
            if(!encontrado){
                lblInfo.setText("Nombre no encontrado");
                lblInfo.setVisible(true);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getClientes(ArrayList<Customer> lista) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lista.add(new Customer(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
