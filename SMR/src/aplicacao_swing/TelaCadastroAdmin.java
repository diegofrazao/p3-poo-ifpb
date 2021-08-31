package aplicacao_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pessoa;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class TelaCadastroAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JButton button;
	private JLabel lblmsg;
	private JPasswordField passwordField;
	private JLabel label;
	private JTextField textField_1;
	private JLabel label_1;
	private JTextField textField_2;

	public TelaCadastroAdmin() {
		setTitle("Cadastrar Usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(62, 10, 96, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
				passwordField = new JPasswordField();
				passwordField.setBounds(62, 36, 96, 23);
				contentPane.add(passwordField);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 14, 46, 14);
		contentPane.add(lblEmail);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 39, 46, 14);
		contentPane.add(lblSenha);

		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 120, 273, 14);
		contentPane.add(lblmsg);

		label = new JLabel("Nome");
		label.setBounds(10, 69, 46, 14);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(62, 65, 96, 23);
		contentPane.add(textField_1);
		
		label_1 = new JLabel("Setor");
		label_1.setBounds(10, 96, 46, 14);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(62, 93, 96, 23);
		contentPane.add(textField_2);
		
		button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(textField.getText().isEmpty() )
						lblmsg.setText("campo vazio");
					else {
						String email = textField.getText();
						String senha = new String( passwordField.getPassword() );
						String nome = textField_1.getText();;
						String setor = textField_2.getText();;
						Pessoa adm = Fachada.cadastrarAdministrador(email, senha, nome, null, setor);
						lblmsg.setText("cadastrou: "+adm.getEmail());
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		button.setBounds(175, 92, 115, 23);
		contentPane.add(button);
	}
}
