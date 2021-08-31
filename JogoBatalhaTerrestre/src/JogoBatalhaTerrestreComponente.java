import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import biblioteca.BatalhaTerrestre;

@SuppressWarnings("serial")
public class JogoBatalhaTerrestreComponente extends JPanel {

	private BatalhaTerrestre jogo;
	private JLabel[][] grid;
	private JLabel alvos;
	private JLabel cAlvos;
	private JLabel tent;
	private JLabel cTent;
	private JButton button;
	private JButton button_1;
	private JLabel lgAlvo;
	private JLabel lgPerto;
	private JLabel lgDist;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private ImageIcon icon;
	private ImageIcon icon_1;
	private ImageIcon icon_2;
	private ImageIcon icon_3;
	private JLabel label_3;

	public JogoBatalhaTerrestreComponente(JFrame frame) {
		jogo = new BatalhaTerrestre();
		System.out.println(jogo.printar());
		this.setLayout(null);
		this.setSize(750, 590);

		alvos = new JLabel("ACERTOS: ");
		alvos.setBounds(569, 28, 110, 19);
		add(alvos);
		alvos.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cAlvos = new JLabel("0");
		cAlvos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cAlvos.setBounds(675, 28, 22, 19);
		add(cAlvos);

		tent = new JLabel("TENTATIVAS:");
		tent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tent.setBounds(569, 51, 128, 19);
		add(tent);

		cTent = new JLabel("0");
		cTent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cTent.setBounds(698, 51, 28, 19);
		add(cTent);
		
		button = new JButton("REINICIAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(569, 411, 128, 27);
		add(button);
		
		button_1 = new JButton("SAIR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(597, 460, 82, 27);
		add(button_1);
		
		lgAlvo = new JLabel("");
		lgAlvo.setBounds(569, 134, 40, 40);
		icon = new ImageIcon(JogoBatalhaTerrestreComponente.class.getResource("/img/red.png"));
		icon.setImage(icon.getImage().getScaledInstance(
				lgAlvo.getWidth(), lgAlvo.getHeight(),
				Image.SCALE_DEFAULT));
		lgAlvo.setIcon(icon);
		add(lgAlvo);
		
		lgPerto = new JLabel("");
		lgPerto.setBounds(569, 184, 40, 40);
		icon_1 = new ImageIcon(JogoBatalhaTerrestreComponente.class.getResource("/img/blue.png"));
		icon_1.setImage(icon_1.getImage().getScaledInstance(
				lgPerto.getWidth(), lgPerto.getHeight(),
				Image.SCALE_DEFAULT));
		lgPerto.setIcon(icon_1);
		add(lgPerto);
		
		lgDist = new JLabel("");
		lgDist.setBounds(569, 234, 40, 40);
		icon_2 = new ImageIcon(JogoBatalhaTerrestreComponente.class.getResource("/img/yellow.png"));
		icon_2.setImage(icon_2.getImage().getScaledInstance(
				lgDist.getWidth(), lgDist.getHeight(),
				Image.SCALE_DEFAULT));
		lgDist.setIcon(icon_2);
		add(lgDist);
		
		label = new JLabel("ALVO");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(616, 139, 71, 27);
		add(label);
		
		label_1 = new JLabel("PERTO");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(616, 189, 71, 27);
		add(label_1);
		
		label_2 = new JLabel("DISTANTE");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(616, 239, 88, 27);
		add(label_2);
		
		label_3 = new JLabel("@diiegofrazao");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(569, 514, 121, 27);
		add(label_3);

		// inicializar a matriz de labels
		int n = 10;
		int aux = 1;
		grid = new JLabel[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				grid[x][y] = new JLabel();
				add(grid[x][y]);
				grid[x][y].setBounds(x * 55, y * 55, 55, 55);
				grid[x][y].setBackground(Color.WHITE);
				grid[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				grid[x][y].setVerticalAlignment(SwingConstants.CENTER);
				grid[x][y].setBorder(new LineBorder(Color.BLACK, 1, true));
				grid[x][y].setOpaque(true);
				icon_3 = new ImageIcon(JogoBatalhaTerrestreComponente.class.getResource("/img/" + aux + ".jpg"));
				icon_3.setImage(icon_3.getImage().getScaledInstance(
						grid[x][y].getWidth(), grid[x][y].getHeight(),
						Image.SCALE_DEFAULT));
				grid[x][y].setIcon(icon_3);
				aux ++;

				grid[x][y].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JLabel b = (JLabel) e.getSource();
						int indicex = b.getX() / 55;
						int indicey = b.getY() / 55;

						String tiro = null;
						try {
							tiro = jogo.atirar(indicex+1, indicey+1);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(frame, "Posição já clicada!", "ERRO", JOptionPane.ERROR_MESSAGE);
						}
						
						int acertos = jogo.getAcertos();
						int tiros = jogo.getTiros();

						if (tiro == "ALVO") {
							grid[indicex][indicey].setIcon(icon);
							cAlvos.setText(acertos + "");
							cTent.setText(tiros + "");
						} else if (tiro == "PERTO") {
							grid[indicex][indicey].setIcon(icon_1);
							cTent.setText(tiros + "");
						} else if (tiro == "DISTANTE") {
							grid[indicex][indicey].setIcon(icon_2);
							cTent.setText(tiros + "");
						}

						if (jogo.terminou()) {
							String[] opcoes = { "Sim", "Não" };
							int res = JOptionPane.showOptionDialog(frame,
									"O jogo acabou, " + jogo.getResultadoFinal() + "\nDeseja reiniciar o jogo?",
									"FIM DE JOGO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									opcoes, opcoes[0]);

							if (res == 0)
								reiniciar();
							else 
								System.exit(0);
							
						}
					}
				});
			} // end for x
		} // end for y

	}

	private void reiniciar() {
		int aux1=1;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				icon_3 = new ImageIcon(JogoBatalhaTerrestreComponente.class.getResource("/img/" + aux1 + ".jpg"));
				icon_3.setImage(icon_3.getImage().getScaledInstance(
						grid[j][i].getWidth(), grid[j][i].getHeight(),
						Image.SCALE_DEFAULT));
				grid[j][i].setIcon(icon_3);
				aux1 ++;
			}

		jogo = new BatalhaTerrestre();
		System.out.println(jogo.printar());

		cAlvos.setText(0 + "");
		cTent.setText(0 + "");
	}
}