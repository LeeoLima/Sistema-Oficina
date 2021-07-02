package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;
import java.awt.SystemColor;

public class TelaUsuario extends JDialog {
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario dialog = new TelaUsuario();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaUsuario() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaUsuario.class.getResource("/icones/iconfinder_67533_128_chevelot_yellow_icon_64px.png")));
		setTitle("Oficina - Usuarios ");
		setBounds(100, 100, 769, 393);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(29, 49, 46, 14);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(96, 46, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_1.setBounds(26, 91, 65, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(96, 88, 236, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login:");
		lblNewLabel_2.setBounds(26, 137, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(96, 134, 105, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setBounds(235, 137, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(291, 134, 134, 20);
		getContentPane().add(txtSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil:");
		lblNewLabel_4.setBounds(26, 186, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(96, 182, 86, 22);
		getContentPane().add(cboPerfil);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.setBackground(SystemColor.control);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBorder(null);
		btnCreate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/create.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnCreate.setBounds(96, 262, 64, 64);
		getContentPane().add(btnCreate);

		JButton btnRead = new JButton("");
		btnRead.setBackground(SystemColor.control);
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.setBorder(null);
		btnRead.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/read.png")));
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnRead.setBounds(361, 49, 64, 64);
		getContentPane().add(btnRead);

		btnUpdate = new JButton("");
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(SystemColor.control);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBorder(null);
		btnUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/update.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnUpdate.setBounds(229, 262, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.setBackground(SystemColor.control);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBorder(null);
		btnDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/delete.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDelete.setBounds(373, 262, 64, 64);
		getContentPane().add(btnDelete);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/OficinaMecanica.png")));
		lblNewLabel_5.setBounds(470, 91, 250, 150);
		getContentPane().add(lblNewLabel_5);

	}

	// Criação de um objeto para acessar o método da classe DAO
	DAO dao = new DAO();
	private JButton btnRead;
	private JComboBox cboPerfil;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;

	private void adicionar() {
		// validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Nome de Usuário");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Login Obrigatório");
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Senha Obrigatório");
		} else if (cboPerfil.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Campo Perfil Obrigatório");
		} else {
			String create = "insert into tbusuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				// substituir os parâmetros(?,?,?) pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario adicionado");
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println();

			}
		}
	}

	/**
	 ** Selecionar contato
	 **/
	private void pesquisar() {
		// instrução sql para pesquisar um contato pelo nome
		String read = "select * from tbusuarios where usuario = (?)";
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// preparar a instrução sql
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parâmetro (?) pelo nome do contato
			pst.setString(1, txtUsuario.getText());
			// resultado (obter os dados do contato pesquisado)
			ResultSet rs = pst.executeQuery();
			// se existir um contato correspondente
			if (rs.next()) {
				txtId.setText(rs.getString(1)); // 1 - iduser
				txtUsuario.setText(rs.getString(2)); // 2 - usuario
				txtLogin.setText(rs.getString(3)); // 3 - login
				txtSenha.setText(rs.getString(4)); // 4 - Senha
				cboPerfil.setSelectedItem(rs.getString(5));
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnRead.setEnabled(false);

			} else {
				// Criar uma caixa de mensagem no Java
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnCreate.setEnabled(true);
				btnRead.setEnabled(false);
				btnDelete.setEnabled(false);
				btnUpdate.setEnabled(false);
				pst.executeUpdate();
				con.close();
			}

		} catch (Exception e) {
			System.out.println();
		}

	}

	/**
	 * Editar contato CRUD Update
	 **/
	private void alterar() {
		// validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Usuario");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do Usuario");
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
		} else if (txtUsuario.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo Usuario não pode ter mais que 50 caracteres");
		} else if (txtLogin.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo Login não pode ter mais que 15 caracteres");
		} else if (txtSenha.getText().length() > 150) {
			JOptionPane.showMessageDialog(null, "O campo Senha não pode ter mais que 15 caracteres");
		} else {
			String update = "update tbusuarios set usuario=?,login=?,senha=?,perfil=? where iduser=?";
			{
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(update);
					// substituir os parametros(?,?,?) pelo conteúdo das caixas de texto
					pst.setString(1, txtUsuario.getText());
					pst.setString(2, txtLogin.getText());
					pst.setString(3, txtSenha.getText());
					pst.setString(4, cboPerfil.getSelectedItem().toString());
					pst.setString(5, txtId.getText());
					// eecutar a query (insert no banco de dados)
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Contato editado com sucesso");
					con.close();
				} catch (Exception e) {
					System.out.println();
				}
			}
		}
	}

	/**
	 * *Excluir contato CRUD Delete
	 **/
	private void deletar() {
		String delete = "delete from tbusuarios where iduser=?";
		// Confirmação de exclusão do contato
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Usuario?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();
				limpar();
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
				con.close();
			} catch (Exception e) {
				//System.out.println();
			}
		} else {
			btnRead.setEnabled(false);
		}
	}

	/**
	 * Limpar campos e configurar botões
	 **/

	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);

	}
}
