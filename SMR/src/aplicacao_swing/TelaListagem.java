package aplicacao_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TelaListagem extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton button1;
	private JButton button2;
	private JButton button;
	private JLabel label;
	private JTextField textField;
	private JButton button_1;
	private JLabel label_1;

	public TelaListagem() {
		
		setTitle("Listagem");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 315);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		button1 = new JButton("Listar caixa de entrada");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ArrayList<Mensagem> lista = Fachada.listarCaixaEntrada();
					
					String texto = "Mensagens da caixa de entrada: \n";
					if (lista.isEmpty())
						texto += "caixa de entrada vazia!\n";
					else 
						for(Mensagem m: lista) 
							texto += m; 

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		button1.setBounds(500, 12, 175, 23);
		contentPane.add(button1);
		
		textArea = new JTextArea();		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 10, 480, 250);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		button2 = new JButton("Listar caixa de saida");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String texto;
					ArrayList<Mensagem> lista = Fachada.listarCaixaSaida();
					
					texto = "Mensagens da caixa de saida: \n";
					if (lista.isEmpty())
						texto += "caixa de saida vazia!\n";
					else 	
						for(Mensagem m: lista) 
							texto += m; 

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		button2.setBounds(500, 45, 175, 23);
		contentPane.add(button2);
		
		button = new JButton("Listar todas as pessoas");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto;
				ArrayList<Pessoa> pessoa = Fachada.listarPessoas("");
				texto = "Listagem de pessoas: \n";
				if (pessoa.isEmpty())
					texto += "nenhuma pessoa cadastrada!\n";
				else 	
					for(Pessoa p: pessoa) 
						texto +=  p; 

				textArea.setText(texto);
			}
		});
		button.setBounds(500, 77, 175, 23);
		contentPane.add(button);
		
		label = new JLabel("Listar pessoas pelo nome");
		label.setBounds(500, 141, 175, 13);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(550, 157, 125, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button_1 = new JButton("Listar por nome");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto;
				ArrayList<Pessoa> pessoa = Fachada.listarPessoas(textField.getText());
				texto = "Listagem de pessoas: \n";
				if (pessoa.isEmpty())
					texto += "nenhuma pessoa cadastrada!\n";
				else 	
					for(Pessoa p: pessoa) 
						texto +=  p; 

				textArea.setText(texto);
			}
		});
		button_1.setBounds(500, 186, 175, 23);
		contentPane.add(button_1);
		
		label_1 = new JLabel("Nome:");
		label_1.setBounds(500, 164, 61, 13);
		contentPane.add(label_1);
	}
}
