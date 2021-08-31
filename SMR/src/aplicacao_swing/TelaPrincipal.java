package aplicacao_swing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import aplicacao_console.TesteConsole;
import fachada.Fachada;
import modelo.Administrador;

public class TelaPrincipal {
	private JFrame frame;
	private JLabel label; // imagem de fundo
	private JMenu mnPessoa;
	private JMenuItem itemLogin;
	private JMenuItem itemLogff;
	private JMenuItem itemCadastrar;
	private JMenuItem itemListarPessoa;
	private JMenuItem itemCadAdmin;
	private JMenu mnMensagem;
	private JMenuItem itemEnviar;
	private JMenuItem itemApagar;
	private JMenuItem itemListarEntrada;
	private JMenu mnRelatorio;
	private JMenuItem itemListarSaida;
	private JMenuItem itemEspionar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Sistema de Mensagens Rápidas");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					// pre-cadastro de objetos na Fachada
					new TesteConsole().cadastrar();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "até breve !");
			}
		});

		frame.setBounds(100, 100, 384, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// imagem de fundo
		label = new JLabel("");
		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		ImageIcon imagem = new ImageIcon(getClass().getResource("../imagens/imagem1.jpg"));
		imagem = new ImageIcon(
				imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(imagem);
		frame.getContentPane().add(label);
		frame.setResizable(true);

		// -------------BARRA DE MENU-----------------------------------
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// -------------MENU-----------------------------------
		mnMensagem = new JMenu("Mensagem");
		menuBar.add(mnMensagem);

		itemEnviar = new JMenuItem("Enviar");
		itemEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fachada.obterLogada() == null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaEnviarMensagem j = new TelaEnviarMensagem();
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(itemEnviar);

		itemListarEntrada = new JMenuItem("ListarCxEntrada");
		itemListarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fachada.obterLogada() == null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaListagem j = new TelaListagem();
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(itemListarEntrada);

		itemListarSaida = new JMenuItem("ListarCxSaida");
		itemListarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fachada.obterLogada() == null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaListagem j = new TelaListagem();
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(itemListarSaida);

		itemApagar = new JMenuItem("Apagar");
		itemApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fachada.obterLogada() == null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaApagarMensagem j = new TelaApagarMensagem();
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(itemApagar);

		itemEspionar = new JMenuItem("Espionar");
		itemEspionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fachada.obterLogada() == null && Fachada.obterLogada() instanceof Administrador)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar como administrador");
				else {
					TelaConsulta j = new TelaConsulta(frame);
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(itemEspionar);

		// -------------MENU-----------------------------------
		mnPessoa = new JMenu("Pessoa");
		menuBar.add(mnPessoa);

		itemLogin = new JMenuItem("Login");
		itemLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin j = new TelaLogin();
				j.setVisible(true);
			}
		});
		mnPessoa.add(itemLogin);

		itemLogff = new JMenuItem("Logoff");
		itemLogff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fachada.logoff();
					JOptionPane.showMessageDialog(frame, "Logoff realizado.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		mnPessoa.add(itemLogff);

		itemCadastrar = new JMenuItem("Cadastrar");
		itemCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro j = new TelaCadastro(frame);
				j.setVisible(true);
			}
		});
		mnPessoa.add(itemCadastrar);

		itemCadAdmin = new JMenuItem("Cadastrar Admin");
		itemCadAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroAdmin j = new TelaCadastroAdmin();
				j.setVisible(true);
			}
		});
		mnPessoa.add(itemCadAdmin);

		itemListarPessoa = new JMenuItem("Listar");
		itemListarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagem j = new TelaListagem();
				j.setVisible(true);
			}
		});
		mnPessoa.add(itemListarPessoa);

		// -------------MENU-----------------------------------
		mnRelatorio = new JMenu("Relatorio");
		menuBar.add(mnRelatorio);
		mnRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Fachada.obterLogada() == null && Fachada.obterLogada() instanceof Administrador)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar como administrador");
				else {
					TelaConsulta j = new TelaConsulta(frame);
					j.setVisible(true);
				}
			}
		});

	}
}
