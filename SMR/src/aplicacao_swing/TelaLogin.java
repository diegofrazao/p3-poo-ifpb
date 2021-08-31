package aplicacao_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pessoa;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {
	private JPanel contentPane;
	private JLabel label;
	private JTextField textField;
	private JButton button;	
	private JLabel label_2;
	private JButton button_2;
	private JLabel label_1;
	private JPasswordField passwordField;
//	private JButton button_1;

	public TelaLogin() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Login");
		setBounds(100, 100, 316, 175);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		label = new JLabel("email");
		label.setBounds(19, 28, 39, 14);
		contentPane.add(this.label);

		label_1 = new JLabel("senha");
		label_1.setBounds(19, 53, 46, 14);
		contentPane.add(label_1);
		label_2 = new JLabel("Mensagem do usuario");
		label_2.setBounds(19, 111, 271, 14);
		contentPane.add(this.label_2);
		button_2 = new JButton("Limpar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				passwordField.setText("");
				textField.requestFocus();
			}
		});
		button = new JButton("login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String email = textField.getText();
					String senha = new String( passwordField.getPassword() );
					Pessoa pessoa = Fachada.login(email,senha);
					if (pessoa!=null)
						label_2.setText("bemvindo: "+pessoa.getEmail());
					else
						label_2.setText("tente novamente !");
				} catch (Exception e) {
					label_2.setText(e.getMessage());
				}
			}
		});
		textField = new JTextField();
		textField.setBounds(68, 25, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(68, 50, 86, 20);
		contentPane.add(passwordField);
		button.setBounds(175, 24, 86, 23);
		contentPane.add(this.button);
		button_2.setBounds(175, 75, 86, 23);
		contentPane.add(this.button_2);
		
//		button_1 = new JButton("logoff");
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Pessoa pessoa = Fachada.logoff();
//					if (pessoa!=null)
//						label_2.setText("Tchau: "+pessoa.getEmail());
//					else
//						label_2.setText("tente novamente ");
//				} catch (Exception e) {
//					label_2.setText(e.getMessage());
//				}
//			}
//		});
//		button_1.setBounds(175, 49, 86, 23);
//		contentPane.add(button_1);

	}
}
