package as;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JTextField txtContrasea;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtast2";
	private static final String USUARIO = "root";
	private static final String CONTRASEÑA = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			
			Class.forName(CONTROLADOR);

			DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
			
			JOptionPane.showMessageDialog(null, "Conectado");
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el controlador");
			e.printStackTrace();
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error de conexión");
			
		}
	}
	
	public Registro() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setForeground(new Color(56, 182, 255));
		lblNewLabel.setFont(new Font("Louis George Cafe Light", Font.PLAIN, 28));
		lblNewLabel.setBounds(179, 23, 92, 34);
		contentPane.add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(56, 182, 255));
		txtEmail.setBackground(new Color(0, 0, 0));
		txtEmail.setFont(new Font("Louis George Cafe Light", Font.ITALIC, 16));
		txtEmail.setText("Email");
		txtEmail.setBounds(55, 69, 340, 35);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setBorder(new RoundedBorder(11));
		
		txtUsuario = new JTextField();
		txtUsuario.setBackground(new Color(0, 0, 0));
		txtUsuario.setForeground(new Color(56, 182, 255));
		txtUsuario.setFont(new Font("Louis George Cafe Light", Font.ITALIC, 16));
		txtUsuario.setText("Usuario");
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(55, 110, 340, 35);
		contentPane.add(txtUsuario);
		txtUsuario.setBorder(new RoundedBorder(11));
		
		txtContrasea = new JTextField();
		txtContrasea.setForeground(new Color(56, 182, 255));
		txtContrasea.setBackground(new Color(0, 0, 0));
		txtContrasea.setFont(new Font("Louis George Cafe Light", Font.ITALIC, 16));
		txtContrasea.setText("Contraseña");
		txtContrasea.setColumns(10);
		txtContrasea.setBounds(55, 153, 340, 35);
		contentPane.add(txtContrasea);
		txtContrasea.setBorder(new RoundedBorder(11));
		
		btnNewButton = new JButton("");
		
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setIcon(new ImageIcon(Registro.class.getResource("/as/logeo-6.png")));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Louis George Cafe Light", Font.PLAIN, 13));
		btnNewButton.setBounds(182, 200, 35, 35);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            // Establecer la conexión con la base de datos
		            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);

		            // Crear una consulta SQL para insertar los datos en la tabla 'usuarios'
		            String sql = "INSERT INTO usuarios (correo, nombre, contrasena) VALUES (?, ?, ?)";

		            // Crear una instancia de PreparedStatement para ejecutar la consulta SQL
		            PreparedStatement ps = conexion.prepareStatement(sql);
		            ps.setString(1, txtEmail.getText());
		            ps.setString(2, txtUsuario.getText());
		            ps.setString(3, txtContrasea.getText());

		            // Ejecutar la consulta SQL
		            int filasAfectadas = ps.executeUpdate();

		            if (filasAfectadas > 0) {
		                JOptionPane.showMessageDialog(null, "Registro exitoso");
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
		            }

		            // Cerrar la conexión con la base de datos
		            conexion.close();

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
		        }
		    }
		});
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Registro.class.getResource("/as/registro-2.png")));
		btnNewButton_1.setForeground(new Color(56, 182, 255));
		btnNewButton_1.setFont(new Font("Louis George Cafe Light", Font.PLAIN, 13));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(232, 200, 35, 35);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setIcon(new ImageIcon(Registro.class.getResource("/as/O-4.png")));
		lblNewLabel_1.setBounds(0, 0, 450, 272);
		contentPane.add(lblNewLabel_1);
	}
	
	class RoundedBorder implements Border {

	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }

	    public boolean isBorderOpaque() {
	        return true;
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}
