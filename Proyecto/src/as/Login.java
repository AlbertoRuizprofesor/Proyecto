package as;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtContrasea;

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtast2";
	private static final String USUARIO = "root";
	private static final String CONTRASEÑA = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Inicio sesión");
		lblNewLabel_1.setFont(new Font("Louis George Cafe Light", Font.PLAIN, 26));
		lblNewLabel_1.setForeground(new Color(94, 22, 235));
		lblNewLabel_1.setBounds(159, 24, 131, 36);
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		txtEmail.setText("Usuario");
		txtEmail.setForeground(new Color(94, 22, 235));
		txtEmail.setBackground(new Color(0, 0, 0));
		txtEmail.setBounds(55, 84, 340, 35);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setBorder(new RoundedBorder(11));
		
		txtContrasea = new JTextField();
		txtContrasea.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		txtContrasea.setText("Contraseña");
		txtContrasea.setForeground(new Color(94, 22, 235));
		txtContrasea.setBackground(new Color(0, 0, 0));
		txtContrasea.setColumns(10);
		txtContrasea.setBounds(55, 132, 340, 35);
		contentPane.add(txtContrasea);
		txtContrasea.setBorder(new RoundedBorder(11));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/as/violeta-2.png")));
		btnNewButton.setBounds(207, 179, 35, 35);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            // Establecer la conexión con la base de datos
		            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);

		            // Crear una consulta SQL para buscar un usuario con el correo y contraseña especificados
		            String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";

		            // Crear una instancia de PreparedStatement para ejecutar la consulta SQL
		            PreparedStatement ps = conexion.prepareStatement(sql);
		            ps.setString(1, txtEmail.getText());
		            ps.setString(2, txtContrasea.getText());

		            // Ejecutar la consulta SQL
		            ResultSet rs = ps.executeQuery();

		            // Comprobar si se encontró un usuario con los datos especificados
		            if (rs.next()) {
		                JOptionPane.showMessageDialog(null, "Bienvenid@ " + rs.getString("nombre"));
		            } else {
		                JOptionPane.showMessageDialog(null, "Usuario no registrado");
		            }

		            // Cerrar la conexión con la base de datos
		            conexion.close();

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
		        }
		    }
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(94, 22, 235));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/as/3-2.png")));
		lblNewLabel.setBounds(0, 0, 450, 272);
		contentPane.add(lblNewLabel);
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
