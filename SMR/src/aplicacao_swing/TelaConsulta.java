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
public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConsulta_1;
	private JButton btnConsulta_2;
	private JButton btnConsulta_3;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;

	public TelaConsulta(JFrame frame) {
		setTitle("Consultar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("Trecho");
		label.setBounds(414, 154, 46, 23);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(453, 152, 232, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("Procurar mensagens a partir de um trecho");
		label_1.setBounds(414, 122, 271, 23);
		contentPane.add(label_1);
		
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 11, 348, 228);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		btnConsulta_1 = new JButton("Nao enviaram mensagem");
		btnConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String texto;
					ArrayList<Pessoa> lista1 = Fachada.relatorio1();
					texto = "Pessoas que nao enviaram mensagem: \n";
					if (lista1.isEmpty())
						texto += "todas as pessoas enviaram mensagem.\n";
					else 	
						for(Pessoa p: lista1) 
							texto +=  p + "\n";

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsulta_1.setBounds(414, 13, 271, 23);
		contentPane.add(btnConsulta_1);

		btnConsulta_2 = new JButton("Mensagens para voce mesmo");
		btnConsulta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String texto;
					ArrayList<Mensagem> lista1 = Fachada.relatorio2();
					texto = "Pessoas que enviaram mensagens para ela mesma: \n";
					if (lista1.isEmpty())
						texto += "ninguem mandou mensagem para si mesmo.\n";
					else 	
						for(Mensagem m: lista1) 
							texto += "=>" + m.getId() + " " + m.getTexto() + "\n"; 
					
					textArea.setText(texto);
				}catch (Exception erro){
					JOptionPane.showMessageDialog(frame, "-->" + erro.getMessage());
				}
			}
		});
		btnConsulta_2.setBounds(414, 47, 271, 23);
		contentPane.add(btnConsulta_2);
		
		btnConsulta_3 = new JButton("Todas as mensagens");
		btnConsulta_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Mensagem> resultado = Fachada.espionarMensagens("");
				String texto = "Espionando todas as mensagens: \n";
				if (resultado.isEmpty())
					texto += "ninguem enviou mensagem\n";
				else 	
					for(Mensagem m: resultado) 
						texto += "=>" + m.getId() + " " + m.getTexto() + "\n"; 
				textArea.setText(texto);
				}catch (Exception erro) {
					JOptionPane.showMessageDialog(frame, "-->" + erro.getMessage());
				}
			}
		});
		btnConsulta_3.setBounds(414, 81, 271, 23);
		contentPane.add(btnConsulta_3);
		
		JButton btnPrateleirasCom = new JButton("Procurar por trecho");
		btnPrateleirasCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ArrayList<Mensagem> resultado = Fachada.espionarMensagens(textField.getText());
					String texto = "Espionando mensagens pelo trecho '"+textField.getText()+"':\n";
					if (resultado.isEmpty())
						texto += "não existe mensagem com o trecho '"+textField.getText()+"':\n";
					else 	
						for(Mensagem m: resultado) 
							texto += "=>" + m.getId() + " " + m.getTexto() + "\n"; 
					textArea.setText(texto);
				}catch (Exception erro) {
					JOptionPane.showMessageDialog(frame, "-->" + erro.getMessage());
				}
			}
		});
		btnPrateleirasCom.setBounds(414, 187, 271, 23);
		contentPane.add(btnPrateleirasCom);
		
	}
}
