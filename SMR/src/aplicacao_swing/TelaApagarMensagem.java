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
import modelo.Mensagem;

@SuppressWarnings("serial")
public class TelaApagarMensagem extends JFrame {
	private JPanel contentPane;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnApagar;
	private JLabel lblmsg;
	private JButton btnLimpar;

	public TelaApagarMensagem() {
		initialize();
	}

	private void initialize() {
		setTitle("Apagar Mensagem");
		setBounds(100, 100, 345, 190);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		lblNome = new JLabel("ID da mensagem");
		lblNome.setBounds(19, 28, 102, 14);
		contentPane.add(this.lblNome);
		textField = new JTextField();
		textField.setBounds(124, 25, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String id = textField.getText();
					Mensagem m = Fachada.apagarMensagem(Integer.parseInt(id));
					lblmsg.setText("mensagem excluida com sucesso "+m.getId());
				} catch (Exception e) {
					lblmsg.setText(e.getMessage());
				}
			}
		});
		btnApagar.setBounds(19, 71, 136, 23);
		contentPane.add(this.btnApagar);
		lblmsg = new JLabel("Mensagem do usuario");
		lblmsg.setBounds(19, 114, 294, 14);
		contentPane.add(this.lblmsg);
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField.requestFocus();
			}
		});
		btnLimpar.setBounds(172, 71, 141, 23);
		contentPane.add(this.btnLimpar);

	}
}
