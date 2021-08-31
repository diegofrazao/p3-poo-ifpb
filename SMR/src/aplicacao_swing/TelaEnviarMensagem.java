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

import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class TelaEnviarMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblDest;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JLabel label;
	private JTextPane textPane;


	public TelaEnviarMensagem() {
		setTitle("Cadastrar Prateleira");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(113, 12, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblDest = new JLabel("Destinatario");
		lblDest.setBounds(10, 14, 93, 14);
		contentPane.add(lblDest);

		
		lblmsg = new JLabel("");
		lblmsg.setBounds(29, 132, 139, 20);
		contentPane.add(lblmsg);
		
		label = new JLabel("Texto");
		label.setBounds(10, 41, 46, 13);
		contentPane.add(label);
		
		textPane = new JTextPane();
		textPane.setBounds(113, 42, 173, 67);
		contentPane.add(textPane);
		
		btnCriar = new JButton("Enviar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String destinatario = textField.getText();
					String texto = textPane.getText();
					Fachada.enviarMensagem(destinatario, texto);
					lblmsg.setText("Mensagem enviada!!");
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
					
				}
			}
		});
		btnCriar.setBounds(191, 132, 95, 23);
		contentPane.add(btnCriar);
	}
}
