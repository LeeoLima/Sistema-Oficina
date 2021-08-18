package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class TelaServico extends JDialog {
	private JTextField txtClientes;
	private JTextField txtId;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtAcessorios;
	private JTextField txtDefeito;
	private JTextField txtMecanico;
	private JTextField txtDiag;
	private JTextField txtRenavam;
	private JTextField txtPlaca;
	private JTextField txtCor;
	private JTextField txtKm;
	private JTextField txtValor;
	private JTextField txtSinal;
	private JTextField txtReceber;
	private JTable tableCliente;

	// Variavel de apoio a captura do JRadioButton
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaServico dialog = new TelaServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaServico() {
		setBounds(100, 100, 800, 610);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 29, 260, 102);
		getContentPane().add(panel);
		panel.setLayout(null);

		rdbtnServico = new JRadioButton("Servi\u00E7o");
		rdbtnServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
				buttonGroup.clearSelection();
			}
		});

		rdbtnServico.setBounds(123, 60, 93, 23);
		panel.add(rdbtnServico);

		rdbtnOrcamento = new JRadioButton("Or\u00E7amento");
		rdbtnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";

			}
		});
		buttonGroup.add(rdbtnOrcamento);
		rdbtnOrcamento.setBounds(17, 60, 109, 23);
		panel.add(rdbtnOrcamento);
		buttonGroup.clearSelection();

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(17, 6, 48, 14);
		panel.add(lblNewLabel);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(123, 6, 48, 14);
		panel.add(lblData);

		txtOS = new JTextField();
		txtOS.setEditable(false);
		txtOS.setBounds(10, 31, 83, 20);
		panel.add(txtOS);
		txtOS.setColumns(10);

		txtDATA = new JTextField();
		txtDATA.setEditable(false);
		txtDATA.setBounds(103, 31, 132, 20);
		panel.add(txtDATA);
		txtDATA.setColumns(10);

		cboSit = new JComboBox();
		cboSit.setModel(
				new DefaultComboBoxModel(new String[] { "", "Retirado", "Aguardando Pe\u00E7a", "An\u00E1lise" }));
		cboSit.setBounds(73, 141, 197, 22);
		getContentPane().add(cboSit);

		JLabel lblNewLabel_3 = new JLabel("Situa\u00E7\u00E3o");
		lblNewLabel_3.setBounds(14, 144, 73, 14);
		getContentPane().add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(9, 183, 740, 168);
		getContentPane().add(panel_1);

		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(91, 29, 262, 20);
		panel_1.add(txtModelo);

		JLabel lblNewLabel_3_1_1 = new JLabel("Modelo");
		lblNewLabel_3_1_1.setBounds(10, 32, 47, 14);
		panel_1.add(lblNewLabel_3_1_1);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(408, 29, 123, 20);
		panel_1.add(txtMarca);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Marca");
		lblNewLabel_3_1_1_1.setBounds(363, 32, 40, 14);
		panel_1.add(lblNewLabel_3_1_1_1);

		txtAcessorios = new JTextField();
		txtAcessorios.setColumns(10);
		txtAcessorios.setBounds(91, 60, 604, 20);
		panel_1.add(txtAcessorios);

		txtDefeito = new JTextField();
		txtDefeito.setColumns(10);
		txtDefeito.setBounds(91, 128, 604, 20);
		panel_1.add(txtDefeito);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Acess\u00F3rios");
		lblNewLabel_3_1_1_2.setBounds(10, 63, 71, 14);
		panel_1.add(lblNewLabel_3_1_1_2);

		JLabel lblNewLabel_3_1_1_2_1 = new JLabel("Defeito");
		lblNewLabel_3_1_1_2_1.setBounds(10, 131, 71, 14);
		panel_1.add(lblNewLabel_3_1_1_2_1);

		JLabel lblNewLabel_3_1_1_2_1_1 = new JLabel("Renavam");
		lblNewLabel_3_1_1_2_1_1.setBounds(10, 91, 71, 14);
		panel_1.add(lblNewLabel_3_1_1_2_1_1);

		txtRenavam = new JTextField();
		txtRenavam.setColumns(10);
		txtRenavam.setBounds(91, 88, 262, 20);
		panel_1.add(txtRenavam);

		JLabel lblNewLabel_3_1_1_2_1_1_1 = new JLabel("Placa");
		lblNewLabel_3_1_1_2_1_1_1.setBounds(363, 91, 40, 14);
		panel_1.add(lblNewLabel_3_1_1_2_1_1_1);

		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(404, 88, 87, 20);
		panel_1.add(txtPlaca);

		JLabel lblNewLabel_3_1_1_2_1_1_1_1 = new JLabel("Cor");
		lblNewLabel_3_1_1_2_1_1_1_1.setBounds(572, 32, 22, 14);
		panel_1.add(lblNewLabel_3_1_1_2_1_1_1_1);

		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(599, 29, 96, 20);
		panel_1.add(txtCor);

		JLabel lblNewLabel_3_1_1_2_1_1_1_2 = new JLabel("KM");
		lblNewLabel_3_1_1_2_1_1_1_2.setBounds(554, 91, 40, 14);
		panel_1.add(lblNewLabel_3_1_1_2_1_1_1_2);

		txtKm = new JTextField();
		txtKm.setColumns(10);
		txtKm.setBounds(584, 88, 111, 20);
		panel_1.add(txtKm);

		JLabel lblNewLabel_3_1 = new JLabel("Mec\u00E2nica ");
		lblNewLabel_3_1.setBounds(9, 2, 73, 14);
		panel_1.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtMecanico = new JTextField();
		txtMecanico.setColumns(10);
		txtMecanico.setBounds(80, 366, 186, 20);
		getContentPane().add(txtMecanico);

		JLabel lblNewLabel_3_1_1_3 = new JLabel("Mecanico");
		lblNewLabel_3_1_1_3.setBounds(13, 370, 71, 14);
		getContentPane().add(lblNewLabel_3_1_1_3);

		JLabel lblNewLabel_3_1_1_3_1 = new JLabel("Diagn\u00F3stico");
		lblNewLabel_3_1_1_3_1.setBounds(321, 372, 69, 14);
		getContentPane().add(lblNewLabel_3_1_1_3_1);

		txtDiag = new JTextField();
		txtDiag.setColumns(10);
		txtDiag.setBounds(402, 367, 347, 20);
		getContentPane().add(txtDiag);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente numeros ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtValor.setText("0");
		txtValor.setColumns(10);
		txtValor.setBounds(46, 407, 79, 20);
		getContentPane().add(txtValor);

		JLabel lblNewLabel_3_1_1_3_2 = new JLabel("Valor:");
		lblNewLabel_3_1_1_3_2.setBounds(10, 411, 34, 14);
		getContentPane().add(lblNewLabel_3_1_1_3_2);

		JLabel lblNewLabel_3_1_1_3_2_1 = new JLabel("Sinal:");
		lblNewLabel_3_1_1_3_2_1.setBounds(141, 411, 33, 14);
		getContentPane().add(lblNewLabel_3_1_1_3_2_1);

		txtSinal = new JTextField();
		txtSinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtSinal.setColumns(10);
		txtSinal.setBounds(174, 406, 61, 20);
		getContentPane().add(txtSinal);

		cboPag = new JComboBox();
		cboPag.setModel(new DefaultComboBoxModel(
				new String[] { "Dinheiro", "Cart\u00E3o 1x", "Cart\u00E3o 2x", "Cart\u00E3o 3x", "Boleto" }));
		cboPag.setBounds(386, 407, 87, 22);
		getContentPane().add(cboPag);

		JLabel lblNewLabel_3_1_1_3_1_1 = new JLabel("Forma de  Pagamento:");
		lblNewLabel_3_1_1_3_1_1.setBounds(254, 409, 159, 17);
		getContentPane().add(lblNewLabel_3_1_1_3_1_1);

		JLabel lblNewLabel_3_1_1_3_1_2 = new JLabel("\u00C0 receber");
		lblNewLabel_3_1_1_3_1_2.setBounds(486, 415, 69, 14);
		getContentPane().add(lblNewLabel_3_1_1_3_1_2);

		txtReceber = new JTextField();
		txtReceber.setColumns(10);
		txtReceber.setBounds(548, 411, 98, 20);
		getContentPane().add(txtReceber);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restante();
			}
		});
		btnCalcular.setBounds(659, 410, 90, 23);
		getContentPane().add(btnCalcular);

		btnAdicionar = new JButton("");
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setForeground(SystemColor.control);
		btnAdicionar.setBackground(SystemColor.control);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(10, 496, 64, 64);
		getContentPane().add(btnAdicionar);

		btnPesquisar = new JButton("");
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setForeground(SystemColor.control);
		btnPesquisar.setBackground(SystemColor.control);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/pesquisar.png")));
		btnPesquisar.setBounds(90, 496, 64, 64);
		getContentPane().add(btnPesquisar);

		dateGarantia = new JDateChooser();
		dateGarantia.setBounds(580, 456, 169, 20);
		getContentPane().add(dateGarantia);

		dateRetirada = new JDateChooser();
		dateRetirada.setBounds(341, 456, 163, 20);
		getContentPane().add(dateRetirada);

		datePrevisao = new JDateChooser();
		datePrevisao.setBounds(90, 456, 159, 20);
		getContentPane().add(datePrevisao);

		JLabel lblNewLabel_3_1_2 = new JLabel("Previs\u00E3o:");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_1_2.setBounds(14, 462, 73, 14);
		getContentPane().add(lblNewLabel_3_1_2);

		JLabel lblNewLabel_3_1_3 = new JLabel("Retirada:");
		lblNewLabel_3_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_1_3.setBounds(265, 462, 73, 14);
		getContentPane().add(lblNewLabel_3_1_3);

		JLabel lblNewLabel_3_1_4 = new JLabel("Garantia:");
		lblNewLabel_3_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_1_4.setBounds(514, 462, 73, 14);
		getContentPane().add(lblNewLabel_3_1_4);

		btnAtualizar = new JButton("");
		btnAtualizar.setBackground(SystemColor.control);
		btnAtualizar.setBorderPainted(false);
		btnAtualizar.setForeground(SystemColor.control);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnAtualizar.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/update.png")));
		btnAtualizar.setBounds(164, 496, 64, 64);
		getContentPane().add(btnAtualizar);

		btnExcluir = new JButton("");
		btnExcluir.setBackground(SystemColor.control);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setForeground(SystemColor.control);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(237, 496, 64, 64);
		getContentPane().add(btnExcluir);

		JLabel btnImprimir = new JLabel("");
		btnImprimir.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/impressora.png")));
		btnImprimir.setBounds(314, 496, 64, 64);
		getContentPane().add(btnImprimir);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(280, 29, 468, 137);
		getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblNewLabel_2 = new JLabel("Id:");
		lblNewLabel_2.setBounds(236, 14, 48, 14);
		panel_1_1.add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(260, 11, 112, 20);
		panel_1_1.add(txtId);
		txtId.setColumns(10);

		txtClientes = new JTextField();
		txtClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		txtClientes.setBounds(64, 11, 162, 20);
		panel_1_1.add(txtClientes);
		txtClientes.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Cliente:");
		lblNewLabel_2_1.setBounds(10, 14, 48, 14);
		panel_1_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(391, 7, 38, 31);
		panel_1_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/lupa.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 448, 84);
		panel_1_1.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Setar os campos da tabela
				setarCampos();
			}

		});
		scrollPane.setColumnHeaderView(tableCliente);

		RestrictedTextField modelo = new RestrictedTextField(txtModelo);
		modelo.setLimit(40);

		RestrictedTextField marca = new RestrictedTextField(txtMarca);
		marca.setLimit(9);

		RestrictedTextField cor = new RestrictedTextField(txtCor);
		cor.setLimit(9);

		RestrictedTextField acessorios = new RestrictedTextField(txtAcessorios);
		acessorios.setLimit(300);

		RestrictedTextField renavam = new RestrictedTextField(txtRenavam);
		renavam.setOnlyNums(true);

		RestrictedTextField placa = new RestrictedTextField(txtPlaca);
		placa.setLimit(15);

		RestrictedTextField km = new RestrictedTextField(txtKm);
		km.setOnlyNums(true);

		RestrictedTextField defeito = new RestrictedTextField(txtDefeito);
		defeito.setLimit(100);

		RestrictedTextField mecanico = new RestrictedTextField(txtMecanico);
		mecanico.setLimit(100);

		RestrictedTextField diag = new RestrictedTextField(txtDiag);
		diag.setLimit(100);

		// RestrictedTextField valor = new RestrictedTextField(txtValor);
		// valor.setLimit(100);

		// RestrictedTextField sinal = new RestrictedTextField(txtSinal);
		// sinal.setLimit(100);

	} // fim do construtor

//Criar Objeto para acessar a classe DAO (camada Model)
	DAO dao = new DAO();
	private JRadioButton rdbtnOrcamento;
	private JRadioButton rdbtnServico;
	private JComboBox cboSit;
	private JComboBox cboPag;
	private JDateChooser datePrevisao;
	private JDateChooser dateRetirada;
	private JDateChooser dateGarantia;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnPesquisar;
	private JTextField txtOS;
	private JTextField txtDATA;
	private JButton btnExcluir;

	// metodo para pesquisa avançada de clientes
	private void pesquisarClientes() {
		String read = "select  idcli, nomecli, fonecli  from tbclientes where nomecli like ?";
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
		// limpar();

	}

	// método para calcular o valor restante
	private void restante() {
		double valor, sinal, receber;
		valor = Double.parseDouble(txtValor.getText());
		sinal = Double.parseDouble(txtSinal.getText());
		receber = valor - sinal;
		txtReceber.setText("R$ " + receber);
	}

	// CRUD CREAT (Insert)
	private void emitirOs() {
		// Validação de ampos obrigatórios
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "identifique o cliente");
			txtClientes.requestFocus();

			// (NOT) -> Se O JradioButton não estiver selecionado
		} else if (!rdbtnServico.isSelected() && !rdbtnOrcamento.isSelected()) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS");
		} else if (cboSit.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Insira a situação do carro");
			cboSit.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o modelo do carro");
			txtModelo.requestFocus();
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a marca do carro");
			txtMarca.requestFocus();
		} else if (txtCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cor do carro");
			txtCor.requestFocus();
		} else if (txtRenavam.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o renavam do carro");
			txtRenavam.requestFocus();
		} else if (txtPlaca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a placa do carro");
			txtPlaca.requestFocus();
		} else if (txtKm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a kilometragem do carro");
			txtKm.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o defeito do carro");
			txtDefeito.requestFocus();
		} else {
			String insertOs = "insert into tbos (tipoOs, situacao, mecanico, modelo, marca, cor, km, renavam, placa, defeitoCli, diagnostico, acessorios, valor, sinal, dataPrevista, dataRetirada, formaPag, garantiaData, idcli) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(insertOs);
				// captura associada ao texto do JRadioButton
				pst.setString(1, tipo);
				pst.setString(2, cboSit.getSelectedItem().toString());
				pst.setString(3, txtMecanico.getText());
				pst.setString(4, txtModelo.getText());
				pst.setString(5, txtMarca.getText());
				pst.setString(6, txtCor.getText());
				pst.setString(7, txtKm.getText());
				pst.setString(8, txtRenavam.getText());
				pst.setString(9, txtPlaca.getText());
				pst.setString(10, txtDefeito.getText());
				pst.setString(11, txtDiag.getText());
				pst.setString(12, txtAcessorios.getText());
				pst.setString(13, txtValor.getText());
				pst.setString(14, txtSinal.getText());

				// Jcalendar
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				// Data Previsão

				if (datePrevisao.getDate() == null) {
					pst.setString(15, null);
				} else {
					String dataPrevisao = formatador.format(datePrevisao.getDate());
					pst.setString(15, dataPrevisao);
				}

				if (dateRetirada.getDate() == null) {
					pst.setString(16, null);
				} else {
					String dataRetirada = formatador.format(dateRetirada.getDate());
					pst.setString(16, dataRetirada);
				}

				if (dateGarantia.getDate() == null) {
					pst.setString(18, null);
				} else {
					String dataGarantia = formatador.format(dateGarantia.getDate());
					pst.setString(18, dataGarantia);
				}

				//String dataPrevista = formatador.format(datePrevisao.getDate());
				//pst.setString(15, dataPrevista);
				//String dataRetirada = formatador.format(dateRetirada.getDate());
				//pst.setString(16, dataRetirada);
				// ComboBox
				pst.setString(17, cboPag.getSelectedItem().toString());

				// Jcalendar
				//String dataGarantia = formatador.format(dateGarantia.getDate());
				//pst.setString(18, dataGarantia);
				pst.setString(19, txtId.getText());
				int sucesso = pst.executeUpdate();
				if (sucesso == 1) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
					btnAdicionar.setEnabled(false);
					btnPesquisar.setEnabled(true);
					btnAtualizar.setEnabled(false);
					btnExcluir.setEnabled(false);
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// pesquisar OS
	private void pesquisarOs() {
		// variável de apoio para capturar o número da OS
		String numOs = JOptionPane.showInputDialog("Número da OS");
		// simples teste
		// System.out.println(numOs);
		String readOs = "select * from tbos where os = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(readOs);
			pst.setString(1, numOs);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtDATA.setText(rs.getString(2));
				txtId.setText(rs.getString(21));
				txtMecanico.setText(rs.getString(5));
				txtModelo.setText(rs.getString(6));
				txtMarca.setText(rs.getString(7));
				txtCor.setText(rs.getString(8));
				txtKm.setText(rs.getString(9));
				txtRenavam.setText(rs.getString(10));
				txtPlaca.setText(rs.getString(11));
				txtDefeito.setText(rs.getString(12));
				txtDiag.setText(rs.getString(13));
				txtAcessorios.setText(rs.getString(14));
				txtValor.setText(rs.getString(15));
				txtSinal.setText(rs.getString(16));
				// JRadioButton - criar variável de apoio para converter o texto em seleção do
				// JradionButton correspondente
				String setarTipo = rs.getString(3);
				if (setarTipo.equals("Orçamento")) {
					rdbtnOrcamento.setSelected(true);
					tipo = "Orçamento";
				} else {
					rdbtnServico.setSelected(true);
					tipo = "Serviço";
				}
				cboSit.setSelectedItem(rs.getString(4));
				cboPag.setSelectedItem(rs.getString(19));

				// data (JCalendar) - criar uma variavel para receber a data e um objeto para
				// formatar a data

				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");

				// Data de previsao
				if (datePrevisao.getDate() == null) {
					pst.setString(15, null);
				} else {
					String dataPrevista = formatador.format(datePrevisao.getDate());
					pst.setString(15, dataPrevista);
				}

				if (dateRetirada.getDate() == null) {
					pst.setString(16, null);
				} else {
					String dataRetirada = formatador.format(dateRetirada.getDate());
					pst.setString(16, dataRetirada);
				}

				if (dateGarantia.getDate() == null) {
					pst.setString(18, null);
				} else {
					String dataGarantia = formatador.format(dateGarantia.getDate());
					pst.setString(18, dataGarantia);
				}

				btnAdicionar.setEnabled(false);
				

			} else {
				JOptionPane.showMessageDialog(null, "OS não localizada");
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(true);
				btnAtualizar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			// System.out.println(e);
		}
	}

	private void editar() {
		// Validação de ampos obrigatórios
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "identifique o cliente");
			txtClientes.requestFocus();
			// (NOT) -> Se O JradioButton não estiver selecionado
		} else if (!rdbtnServico.isSelected() && !rdbtnOrcamento.isSelected()) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS");
		} else if (cboSit.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Insira a situação do carro");
			cboSit.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o modelo do carro");
			txtModelo.requestFocus();
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a marca do carro");
			txtMarca.requestFocus();
		} else if (txtCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cor do carro");
			txtCor.requestFocus();
		} else if (txtRenavam.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o renavam do carro");
			txtRenavam.requestFocus();
		} else if (txtPlaca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a placa do carro");
			txtPlaca.requestFocus();
		} else if (txtKm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a kilometragem do carro");
			txtKm.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o defeito do carro");
			txtDefeito.requestFocus();
		} else {
			String update = "update tbos set tipoOs=?, situacao=?, mecanico=?, modelo=?, marca=?, cor=?, km=?, renavam=?, placa=?, defeitoCli=?, diagnostico=?, acessorios=?, valor=?, sinal=?, dataPrevista=?, dataRetirada=?, formaPag=?, garantiaData=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				// captura associada ao texto do JRadioButton
				pst.setString(1, tipo);
				pst.setString(2, cboSit.getSelectedItem().toString());
				pst.setString(3, txtMecanico.getText());
				pst.setString(4, txtModelo.getText());
				pst.setString(5, txtMarca.getText());
				pst.setString(6, txtCor.getText());
				pst.setString(7, txtKm.getText());
				pst.setString(8, txtRenavam.getText());
				pst.setString(9, txtPlaca.getText());
				pst.setString(10, txtDefeito.getText());
				pst.setString(11, txtDiag.getText());
				pst.setString(12, txtAcessorios.getText());
				pst.setString(13, txtValor.getText());
				pst.setString(14, txtSinal.getText());

				// Jcalendar
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				
				if (datePrevisao.getDate() == null) {
					pst.setString(15, null);
				} else {					
					String dataPrevista = formatador.format(datePrevisao.getDate());
					pst.setString(15, dataPrevista);
				}
				
				//String dataPrevista = formatador.format(datePrevisao.getDate());
				//pst.setString(15, dataPrevista);
				
				if (dateRetirada.getDate() == null) {
					pst.setString(16, null);
				} else {					
					String dataRetirada = formatador.format(dateRetirada.getDate());
					pst.setString(16, dataRetirada);
				}
				
				//String dataRetirada = formatador.format(dateRetirada.getDate());
				//pst.setString(16, dataRetirada);

				// ComboBox
				pst.setString(17, cboPag.getSelectedItem().toString());
				
				if (dateGarantia.getDate() == null) {
					pst.setString(18, null);
				} else {					
					String dataGarantia = formatador.format(dateGarantia.getDate());
					pst.setString(18, dataGarantia);
				}
				
				//String dataGarantia = formatador.format(dateGarantia.getDate());
				//pst.setString(18, dataGarantia);
				pst.setString(19, txtId.getText());
				int sucesso = pst.executeUpdate();
				System.out.println(sucesso);
				if (sucesso > 0) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
					btnAdicionar.setEnabled(true);
					btnPesquisar.setEnabled(true);
					btnAtualizar.setEnabled(false);
					btnExcluir.setEnabled(false);
					limpar();
				}

			} catch (Exception e) {
				 System.out.println(e);
			}
		}

	}

	private void deletar() {
		String delete = "delete from tbos where os=?";
		// Confirmação de exclusão do contato
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dessa OS?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOS.getText());
				pst.executeUpdate();
				limpar();
				JOptionPane.showMessageDialog(null, "Ordem de serviço excluida com sucesso");
				con.close();
				limpar();
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(true);
				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(false);

			} catch (Exception e) {
				System.out.println(e);

			}
		} else {

		}
	}

	// Limpar campos e configurar botões

	private void limpar() {
		// tipoOs
		// rdbtnOrcamento.setSelected(false);
		// rdbtnServico.setSelected(false);
		cboSit.setSelectedItem(null);
		txtClientes.setText(null);
		txtId.setText(null);
		txtModelo.setText(null);
		txtMarca.setText(null);
		txtCor.setText(null);
		txtAcessorios.setText(null);
		txtRenavam.setText(null);
		txtPlaca.setText(null);
		txtKm.setText(null);
		txtDefeito.setText(null);
		txtMecanico.setText(null);
		txtDiag.setText(null);
		txtValor.setText(null);
		txtSinal.setText(null);
		cboPag.setSelectedItem(null);
		txtReceber.setText(null);
		datePrevisao.setDate(null);
		dateRetirada.setDate(null);
		dateGarantia.setDate(null);
		// btnAdicionar.setEnabled(false);
		// btnPesquisar.setEnabled(true);
		// btnAtualizar.setEnabled(true);
		// btnExcluir.setEnabled(true);
		((DefaultTableModel) tableCliente.getModel()).setRowCount(0);
	}
}
