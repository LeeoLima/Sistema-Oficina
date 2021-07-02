package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	// para manipular as propriedades dos objetos abaixo na classe Telalogin,
	// modificar private para public
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	public JButton btnCliente;
	public JLabel lblUsuario;
	private JLabel lblNewLabel_1;
	public JPanel panelrodape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Tela Principal - Oficina");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icones/iconfinder_67533_128_chevelot_yellow_icon_64px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setLocationRelativeTo(null);
				usuario.setVisible(true);
			}
		});
		
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBackground(SystemColor.control);
		btnUsuarios.setBorderPainted(false);
		btnUsuarios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/addusuario.png")));
		btnUsuarios.setBounds(12, 21, 128, 128);
		contentPane.add(btnUsuarios);

		btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setLocationRelativeTo(null);
				sobre.setVisible(true);
			}
		});
		
		btnSobre.setBorderPainted(false);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/ponto.png")));
		btnSobre.setBounds(501, 21, 64, 64);
		contentPane.add(btnSobre);

		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setBackground(SystemColor.control);
		btnRelatorios.setBorderPainted(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/iconfinder_1622837_analytics_docs_documents_graph_pdf_icon_128px.png")));
		btnRelatorios.setBounds(10, 178, 128, 128);
		contentPane.add(btnRelatorios);

		JButton btnOs = new JButton("");
		btnOs.setBorderPainted(false);
		btnOs.setBackground(SystemColor.control);
		btnOs.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/OrdemServico.png")));
		btnOs.setBounds(186, 180, 128, 128);
		contentPane.add(btnOs);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/mecanica.png")));
		lblNewLabel.setBounds(504, 134, 159, 216);
		contentPane.add(lblNewLabel);

		lblUsuario = new JLabel("");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(152, 353, 239, 14);
		contentPane.add(lblUsuario);

		lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(52, 353, 71, 14);
		contentPane.add(lblNewLabel_1);

		panelrodape = new JPanel();
		panelrodape.setBackground(Color.BLUE);
		panelrodape.setBounds(0, 346, 663, 32);
		contentPane.add(panelrodape);
		
		JButton btnDelisgar = new JButton("");
		btnDelisgar.setBorderPainted(false);
		btnDelisgar.setBackground(SystemColor.control);
		btnDelisgar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/iconfinder_1564506_close_exit_logout_power_icon_64px.png")));
		btnDelisgar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin login = new TelaLogin();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				desligar();
			}
		});
		btnDelisgar.setBounds(589, 21, 64, 64);
		contentPane.add(btnDelisgar);
		
		btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente2 cliente = new TelaCliente2();
				cliente.setVisible(true);
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setBackground(SystemColor.control);
		btnClientes.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/AddCliente.png")));
		btnClientes.setBounds(186, 21, 128, 128);
		contentPane.add(btnClientes);
		
		
	}// Fim do construto
	
	DAO dao = new DAO();
	private JButton btnClientes;
	private JButton btnSobre;
	
	private void desligar () {
		this.dispose();
	}
}
