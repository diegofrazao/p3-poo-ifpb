import java.awt.EventQueue;
import javax.swing.JFrame;

public class AplicacaoSwing {

	private JFrame frame;
	private JogoBatalhaTerrestreComponente jogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoSwing window = new AplicacaoSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private AplicacaoSwing() {
		frame = new JFrame();
		frame.setTitle("JOGO BATALHA TERRESTRE @BY DIEGO FRAZAO");
		frame.setBounds(200, 200, 750, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		jogo = new JogoBatalhaTerrestreComponente(frame);
		frame.getContentPane().add(jogo);
	}
}
