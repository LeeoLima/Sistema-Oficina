package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/icones/mecanica.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();

			}
		});
		setResizable(false);
		setTitle("Oficina - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setBounds(10, 47, 81, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(101, 44, 148, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(10, 93, 81, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(101, 90, 148, 20);
		contentPane.add(txtSenha);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(101, 139, 89, 23);
		contentPane.add(btnLogin);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(10, 164, 46, 72);
		contentPane.add(lblStatus);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/mecanica.png")));
		lblNewLabel_2.setBounds(276, 23, 129, 213);
		contentPane.add(lblNewLabel_2);
	}

	DAO dao = new DAO();
	private JButton btnLogin;

	private void status() {
		try {

			Connection con = dao.conectar();

			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Login do usuário
	@SuppressWarnings("deprecation")
	private void logar() {
		// validação dos campos obrigatorio
		if (txtUsuario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Prencha o nome do usuario");
			txtUsuario.requestFocus();
		} else if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Prencha a senha ");
			txtSenha.requestFocus();
		} else {
			String read = "select * from tbusuarios where login=? and senha=md5(?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Prepara comando sql para executar no banco de dados
				PreparedStatement pst = con.prepareStatement(read);
				pst = con.prepareStatement(read);
				// Substituir o conteúdo da caixa de texto pelo parÂmetro(?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());
				// Resultado (obter os dados do contato pesquisado)
				ResultSet rs = pst.executeQuery();
				// Se existir um contato correspondente
				if (rs.next()) {
					// tratamento de perfil de usuario
					String perfil = rs.getString(5);
					// apoio ao enrendimento da lógica
					// System.out.println(perfil);

					if (perfil.equals("admin")) {
						// criar um objeto para acessar a classe Principal
						TelaPrincipal principal = new TelaPrincipal();
						// habilitar os botões
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						// alterar a label lblUsuario para o nome do usuario
						principal.lblUsuario.setText(rs.getString(2));
						// alterar a cor do painel (rodape)
						principal.panelrodape.setBackground(Color.RED);
						// centralizar a tela principal
						principal.setLocationRelativeTo(null);
						// Ativar a tela principal
						principal.setVisible(true);
						// Fechar o JFrame (TelaLogin)
						this.dispose();
						// Encerrar a conexão
						con.close();
					} else {
						TelaPrincipal principal = new TelaPrincipal();
						// alterar a label lblUsuario para o nome do usuario
						principal.lblUsuario.setText(rs.getString(2));
						principal.setVisible(true);
						this.dispose();
						con.close();

					}
				} else {
					JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
					txtUsuario.setText(null);
					txtSenha.setText(null);
					txtUsuario.requestFocus();
				}
			} catch (Exception e) {
				System.out.println();
			}
		}

	}
}
