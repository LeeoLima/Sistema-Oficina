package view;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class TelaCliente2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtClientes;
	private JTable tableCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCliente2 dialog = new TelaCliente2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCliente2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaCliente2.class.getResource("/icones/iconfinder_67533_128_chevelot_yellow_icon_64px.png")));
		setTitle("Clientes - Oficina");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 796, 599);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1, 571);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		txtClientes = new JTextField();
		txtClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		txtClientes.setBounds(11, 25, 459, 20);
		getContentPane().add(txtClientes);
		txtClientes.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(480, 14, 40, 40);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(TelaCliente2.class.getResource("/icones/lupa.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 757, 130);
		getContentPane().add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Setar os campos da tabela
				setarCampos();
			}
		});
		scrollPane.setColumnHeaderView(tableCliente);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(11, 206, 21, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(32, 206, 68, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(126, 206, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(164, 206, 296, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Cep:");
		lblNewLabel_3.setBounds(474, 206, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtCep = new JTextField();
		txtCep.setBounds(506, 206, 145, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);
		RestrictedTextField cep = new RestrictedTextField(txtCep);
		cep.setOnlyNums(true);

		JButton btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(661, 206, 106, 23);
		getContentPane().add(btnCep);

		JLabel lblNewLabel_4 = new JLabel("Endere\u00E7o:");
		lblNewLabel_4.setBounds(10, 240, 68, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(71, 237, 357, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("N\u00B0");
		lblNewLabel_3_1.setBounds(450, 240, 46, 14);
		getContentPane().add(lblNewLabel_3_1);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(474, 237, 55, 20);
		getContentPane().add(txtNumero);

		JLabel lblNewLabel_3_1_1 = new JLabel("Complemento:");
		lblNewLabel_3_1_1.setBounds(539, 240, 95, 14);
		getContentPane().add(lblNewLabel_3_1_1);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(622, 240, 145, 20);
		getContentPane().add(txtComplemento);

		JLabel lblNewLabel_4_1 = new JLabel("Bairro:");
		lblNewLabel_4_1.setBounds(11, 275, 46, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(54, 272, 200, 20);
		getContentPane().add(txtBairro);

		JLabel lblNewLabel_4_1_1 = new JLabel("Cidade:");
		lblNewLabel_4_1_1.setBounds(280, 275, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(336, 272, 296, 20);
		getContentPane().add(txtCidade);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("UF:");
		lblNewLabel_4_1_1_1.setBounds(652, 275, 21, 14);
		getContentPane().add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("RG:");
		lblNewLabel_4_1_1_1_1.setBounds(11, 315, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1_1_1);

		txtRg = new JTextField();
		txtRg.setColumns(10);
		txtRg.setBounds(54, 312, 169, 20);
		getContentPane().add(txtRg);
		RestrictedTextField rg = new RestrictedTextField(txtRg);
		rg.setOnlyNums(true);

		JLabel lblNewLabel_4_1_1_2 = new JLabel("CPF:");
		lblNewLabel_4_1_1_2.setBounds(250, 315, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1_2);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(286, 312, 194, 20);
		getContentPane().add(txtCpf);
		RestrictedTextField cpf = new RestrictedTextField(txtCpf);
		cpf.setOnlyNums(true);

		JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Tel:");
		lblNewLabel_4_1_1_2_1.setBounds(11, 355, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1_2_1);

		txtFone1 = new JTextField();
		txtFone1.setColumns(10);
		txtFone1.setBounds(54, 352, 235, 20);
		getContentPane().add(txtFone1);
		RestrictedTextField fone1 = new RestrictedTextField(txtFone1);
		fone1.setOnlyNums(true);

		JLabel lblNewLabel_4_1_1_2_1_1 = new JLabel("Cel:");
		lblNewLabel_4_1_1_2_1_1.setBounds(323, 355, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1_2_1_1);

		txtFone2 = new JTextField();
		txtFone2.setColumns(10);
		txtFone2.setBounds(356, 352, 286, 20);
		getContentPane().add(txtFone2);
		RestrictedTextField fone2 = new RestrictedTextField(txtFone2);
		fone2.setOnlyNums(true);

		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_4_1_1_1_1_1.setBounds(11, 386, 46, 14);
		getContentPane().add(lblNewLabel_4_1_1_1_1_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(54, 383, 318, 20);
		getContentPane().add(txtEmail);

		JLabel lblNewLabel_4_1_1_1_1_1_1 = new JLabel("Obs:");
		lblNewLabel_4_1_1_1_1_1_1.setBounds(10, 421, 40, 23);
		getContentPane().add(lblNewLabel_4_1_1_1_1_1_1);

		txtObs = new JTextField();
		txtObs.setColumns(10);
		txtObs.setBounds(54, 418, 713, 64);
		getContentPane().add(txtObs);

		btnAdicionar = new JButton("");
		btnAdicionar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			}
		});
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setBackground(SystemColor.control);
		btnAdicionar.setForeground(SystemColor.control);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaCliente2.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(54, 494, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAtualizar = new JButton("");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBorderPainted(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnAtualizar.setForeground(SystemColor.control);
		btnAtualizar.setBackground(SystemColor.control);
		btnAtualizar.setIcon(new ImageIcon(TelaCliente2.class.getResource("/icones/update.png")));
		btnAtualizar.setBounds(175, 494, 64, 64);
		getContentPane().add(btnAtualizar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnExcluir.setBorderPainted(false);
		btnExcluir.setForeground(SystemColor.control);
		btnExcluir.setBackground(SystemColor.control);
		btnExcluir.setIcon(new ImageIcon(TelaCliente2.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(296, 494, 64, 64);
		getContentPane().add(btnExcluir);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(684, 271, 83, 22);
		getContentPane().add(cboUf);

	}

// fim do construtor
//Criar Objeto para acessar a classe DAO (camda Model)
	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtFone1;
	private JTextField txtFone2;
	private JTextField txtEmail;
	private JTextField txtObs;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JComboBox cboUf;

//metodo para pesquisa avançada de clientes
	private void pesquisarClientes() {
		String read = "select * from tbclientes where nomecli like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// atenção ao % na passagem do parâmetro
			pst.setString(1, txtClientes.getText() + "%");
			// Resultado (obter os dados do contato pesquisado)
			ResultSet rs = pst.executeQuery();
			// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.out.println();
		}
	}

	// Método para setar os campos com o conteúdo da tabela
	private void setarCampos() {
		// a variavel abaixo obtem o conteúdo da linha da coluna
		int setar = tableCliente.getSelectedRow();
		// System.out.println(setar); // Apoio ao entendimento
		txtId.setText(tableCliente.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tableCliente.getModel().getValueAt(setar, 1).toString());
		txtCep.setText(tableCliente.getModel().getValueAt(setar, 2).toString());
		txtEndereco.setText(tableCliente.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(tableCliente.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(tableCliente.getModel().getValueAt(setar, 5).toString());
		txtBairro.setText(tableCliente.getModel().getValueAt(setar, 6).toString());
		txtCidade.setText(tableCliente.getModel().getValueAt(setar, 7).toString());
		cboUf.setSelectedItem(tableCliente.getModel().getValueAt(setar, 8).toString());
		txtRg.setText(tableCliente.getModel().getValueAt(setar, 9).toString());
		txtCpf.setText(tableCliente.getModel().getValueAt(setar, 10).toString());
		txtFone1.setText(tableCliente.getModel().getValueAt(setar, 11).toString());
		txtFone2.setText(tableCliente.getModel().getValueAt(setar, 12).toString());
		txtEmail.setText(tableCliente.getModel().getValueAt(setar, 13).toString());
		txtObs.setText(tableCliente.getModel().getValueAt(setar, 14).toString());
		btnAdicionar.setEnabled(false);
		btnExcluir.setEnabled(true);
		btnAtualizar.setEnabled(true);
		//limpar();

	}

	// metodo para buscar cep
	private void buscarCep() {
		// As váriaveis abaixo fora criadas para unir tipo_logradouro com logradourp
		String logradouro = "";
		String tipoLogradouro = "";
		// Variavel de apoio para verificar se o Cep existe
		String resultado = null;

		try {
			// A linha abaixo armazena o cep digitado na variavel cep
			String cep = txtCep.getText();
			// A linha abaixo executa a url de acordo com o webservice
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			// oO bloco do código abaixo usa a biblioteca dom4j para percorrer o arquivo xml
			// extraindo as informações do Cep pesquisado
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
				Element elemento = (Element) i.next();
				if (elemento.getQualifiedName().equals("resultado")) {
					resultado = elemento.getText();
				}
				// A estrutura abaixo verifica se o Cep existe
				if (resultado.equals("0")) {
					JOptionPane.showMessageDialog(null, "CEP não encontrado");
				}
				if (elemento.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("cidade")) {
					txtCidade.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("bairro")) {
					txtBairro.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("logradouro")) {
					logradouro = elemento.getText();
				}
				if (elemento.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = elemento.getText();
				}
			}
			// Setar o campo txtEndereco com o conteudo da variavel
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}

	private void adicionar() {
		// validação dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do cliente");
			txtNome.requestFocus();
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o RG do cliente");
			txtRg.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CPF do cliente");
			txtCpf.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o celular do cliente");
			txtFone1.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o E-mail do cliente");
			txtEmail.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP do cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereco do cliente");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero do cliente");
			txtNumero.requestFocus();
		} else if (cboUf.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Insira o UF do cliente");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cidade do cliente");
			txtCidade.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro do cliente");
			txtBairro.requestFocus();
		} else if (txtNome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode ter mais que 50 caracteres");
		} else if (txtCep.getText().length() > 9) {
			JOptionPane.showMessageDialog(null, "O campo 'CEP' não pode ter mais que 9 caracteres");
		} else if (txtEndereco.getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "O campo 'Endereço' não pode ter mais que 100 caracteres");
		} else if (txtNumero.getText().length() > 12) {
			JOptionPane.showMessageDialog(null, "O campo 'Numero' não pode ter mais que 12 caracteres");
		} else if (txtComplemento.getText().length() > 20) {
			JOptionPane.showMessageDialog(null, "O campo 'Complemento' não pode ter mais que 20 caracteres");
		} else if (txtBairro.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo 'Bairro' não pode ter mais que 50 caracteres");
		} else if (txtCidade.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo 'Cidade' não pode ter mais que 50 caracteres");
		} else if (txtRg.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'RG' não pode ter mais que 15 caracteres");
		} else if (txtCpf.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'CPF' não pode ter mais que 15 caracteres");
		} else if (txtFone1.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'Telefone' não pode ter mais que 15 caracteres");
		} else if (txtFone2.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'Celular' não pode ter mais que 15 caracteres");
		} else if (txtEmail.getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "O campo 'Email' não pode ter mais que 100 caracteres");
		} else if (txtObs.getText().length() > 250) {
			JOptionPane.showMessageDialog(null, "O campo 'Obs' não pode ter mais que 250 caracteres");
		} else {
			String adicionar = "insert into tbclientes (nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(adicionar);
				// substituir os parâmetros(?,?,?) pelo conteúdo das caixas de texto
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtRg.getText());
				pst.setString(10, txtCpf.getText());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtObs.getText());
				// executar a query (insert no banco de dados)
				int sucesso = pst.executeUpdate();
				if (sucesso == 1) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
				} 
				txtNome.setText(null);
				txtCep.setText(null);
				txtEndereco.setText(null);
				txtNumero.setText(null);
				txtComplemento.setText(null);
				txtBairro.setText(null);
				txtCidade.setText(null);
				cboUf.setSelectedItem(null);
				txtRg.setText(null);
				txtCpf.setText(null);
				txtFone1.setText(null);
				txtFone2.setText(null);
				txtEmail.setText(null);
				txtObs.setText(null);
				con.close();
				limpar();
			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CPF Duplicado");
				txtCpf.setText(null);
				txtCpf.requestFocus();
			
			}  catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editar() {
		// validação dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do cliente");
			txtNome.requestFocus();
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o RG do cliente");
			txtRg.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CPF do cliente");
			txtCpf.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o celular do cliente");
			txtFone1.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Email do cliente");
			txtEmail.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP do cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereco do cliente");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero do cliente");
			txtNumero.requestFocus();
		} else if (cboUf.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Insira o UF do cliente");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cidade do cliente");
			txtCidade.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro do cliente");
			txtBairro.requestFocus();
			JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode ter mais que 50 caracteres");
		} else if (txtRg.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'RG' não pode ter mais que 15 caracteres");
		} else if (txtCpf.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo Senha não pode ter mais que 15 caracteres");
		} else if (txtFone1.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'Telefone 1' não pode ter mais que 15 caracteres");
		} else if (txtFone2.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo 'Telefone 2' não pode ter mais que 15 caracteres");
		} else if (txtEmail.getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "O campo 'E-mail' não pode ter mais que 100 caracteres");
		} else if (txtCep.getText().length() > 9) {
			JOptionPane.showMessageDialog(null, "O campo 'CEP' não pode ter mais que 9 caracteres");
		} else if (txtCidade.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo 'Cidade' não pode ter mais que 50 caracteres");
		} else if (txtBairro.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo 'Bairro' não pode ter mais que 50 caracteres");
		} else {

			String update = "update tbclientes set nomecli=?,cep=?,logradouro=?,numero=?, complemento=?,bairro=?,cidade=?,uf=?,rg=?,cpf=?,fonecli=?,fonecli2=?,emailcli=? where obs=?";
			{
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(update);
					// substituir os parametros(?,?,?) pelo conteúdo das caixas de texto
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtCep.getText());
					pst.setString(3, txtEndereco.getText());
					pst.setString(4, txtNumero.getText());
					pst.setString(5, txtComplemento.getText());
					pst.setString(6, txtBairro.getText());
					pst.setString(7, txtCidade.getText());
					pst.setString(8, cboUf.getSelectedItem().toString());
					pst.setString(9, txtRg.getText());
					pst.setString(10, txtCpf.getText());
					pst.setString(11, txtFone1.getText());
					pst.setString(12, txtFone2.getText());
					pst.setString(13, txtEmail.getText());
					pst.setString(14, txtObs.getText());

					// eecutar a query (insert no banco de dados)
					int sucesso = pst.executeUpdate();
					if (sucesso == 1) {
						JOptionPane.showMessageDialog(null, "Contato editado com sucesso");
					} 
					txtNome.setText(null);
					txtCep.setText(null);
					txtEndereco.setText(null);
					txtNumero.setText(null);
					txtComplemento.setText(null);
					txtBairro.setText(null);
					txtCidade.setText(null);
					cboUf.setSelectedItem(null);
					txtRg.setText(null);
					txtCpf.setText(null);
					txtFone1.setText(null);
					txtFone2.setText(null);
					txtEmail.setText(null);
					txtObs.setText(null);
					con.close();
					limpar();
				} catch (SQLIntegrityConstraintViolationException e) {
					JOptionPane.showMessageDialog(null, "CPF Duplicado");
					txtCpf.setText(null);
					txtCpf.requestFocus();
				
				}  catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	/**
	 * *Excluir contato CRUD Delete
	 **/
	private void deletar() {
		String delete = "delete from tbclientes where idcli=?";
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
				// System.out.println();
			}
		} else {
			// btnRead.setEnabled(false);
		}
	}
	/**
	 * Limpar campos e configurar botões
	 **/

	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtRg.setText(null);
		txtCpf.setText(null);
		txtFone1.setText(null);
		txtFone2.setText(null);
		txtEmail.setText(null);
		txtObs.setText(null);
		btnAtualizar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnAdicionar.setEnabled(true);
		((DefaultTableModel) tableCliente.getModel()).setRowCount(0);
	}
	
}