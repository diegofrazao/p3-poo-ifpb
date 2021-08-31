package aplicacao_swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pessoa;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblNome;
	private JButton btnCriar;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtNome;
	private JPasswordField passwordField;

	public TelaCadastro(JFrame frame) {
		setTitle("Cadastrar Pessoa");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 315, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setBounds(72, 11, 86, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		lblNome = new JLabel("Email");
		lblNome.setBounds(10, 14, 62, 14);
		contentPane.add(lblNome);
		
		label = new JLabel("Senha");
		label.setBounds(10, 41, 62, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("Nome");
		label_1.setBounds(10, 68, 62, 14);
		contentPane.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(72, 65, 86, 20);
		contentPane.add(txtNome);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(72, 39, 86, 19);
		contentPane.add(passwordField);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String email = txtEmail.getText();
					String senha = new String(passwordField.getPassword());
					String nome = txtNome.getText();
					
					Pessoa p = Fachada.cadastrarPessoa(email, senha, nome, null);

					JOptionPane.showMessageDialog(frame, "Pessoa cadastrada!\nEmail: " +p.getEmail()+"\nNome: "+p.getNome());
					txtEmail.setText("");
					txtEmail.requestFocus();
				}
				catch(Exception erro) {
					JOptionPane.showMessageDialog(frame, erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(175, 37, 115, 23);
		contentPane.add(btnCriar);
		
	}
}
