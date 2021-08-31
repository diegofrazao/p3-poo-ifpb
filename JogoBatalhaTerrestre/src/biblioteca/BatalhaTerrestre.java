package biblioteca;

import java.io.*;
import java.util.*;

public class BatalhaTerrestre {

	private int countAcertos = 0;
	private int countTiros = 0;

	private String[][] alvos = new String[11][11];
	private String[][] tabuleiro = new String[11][11];

	private String historicoTiros = "";

	public BatalhaTerrestre() {

		for (int i = 1; i <= 10; i++)
			for (int j = 1; j <= 10; j++) {
				this.tabuleiro[i][j] = "#";
				this.alvos[i][j] = "-";
			}

		for (int i = 0; i < 5; i++)
			sorteioAlvos();
	}

	public String atirar(int linha, int coluna) throws Exception {

		if (linha > 10 || linha < 1)
			throw new Exception("\nLinha fora do intervalo válido!");
		if (coluna > 10 || coluna < 1)
			throw new Exception("\nColuna fora do intervalo válido!");
		else if (tabuleiro[linha][coluna] != "#")
			throw new Exception("Posição já foi usada antes!");

		countTiros++;

		if (alvos[linha][coluna] == "a") {
			tabuleiro[linha][coluna] = "A";
			countAcertos++;
			historicoTiros += "(" + linha + "," + coluna + ") ALVO\n";
			return "ALVO";
		}

		for (int i = linha - 1; i <= linha + 1; i++) {
			for (int j = coluna - 1; j <= coluna + 1; j++) {
				if (i >= 1 && i <= 10 && j >= 1 && j <= 10) {
					if (alvos[i][j] == "a") {
						tabuleiro[linha][coluna] = "P";
						historicoTiros += "(" + linha + "," + coluna + ") PERTO\n";
						return "PERTO";
					}
				}
			}
		}

		tabuleiro[linha][coluna] = "D";
		historicoTiros += "(" + linha + "," + coluna + ") DISTANTE\n";
		return "DISTANTE";
	}

	public int getAcertos() {
		return countAcertos;
	}

	public int getTiros() {
		return countTiros;
	}

	public boolean terminou() {
		if (countAcertos == 5 || countTiros == 20)
			return true;
		return false;
	}

	public String getResultadoFinal() {
		gravar(historicoTiros);
		if (countAcertos == 5 && countTiros < 20)
			return "Você ganhou!!\nQuantidade de tiros: " + getTiros();
		return "Você perdeu!!\nQuantidade de tiros: " + getTiros();
	}

	@Override
	public String toString() {
		String tab = new String();
		tab = "  1 2 3 4 5 6 7 8 9 10\n";
		for (int i = 1; i <= 10; i++) {
			tab += i + " ";
			for (int j = 1; j <= 10; j++) {
				tab += tabuleiro[i][j] + " ";
			}
			tab += "\n";
		}
		return tab;
	}

	private void sorteioAlvos() {

		Random sorteio = new Random();
		boolean alvoGerado = true;

		while (alvoGerado) {

			int linha = sorteio.nextInt(10)+1;
			int coluna = sorteio.nextInt(10)+1;

			if (this.alvos[linha][coluna] == "-") {
				this.alvos[linha][coluna] = "a";
				alvoGerado = false;
			}
		}
	}

	private void gravar(String linha) {
		try {
			FileWriter arq;
			arq = new FileWriter(new File("Tiros.txt"));
			arq.write(linha);
			arq.close();
		} catch (IOException e) {
			System.out.println("arquivo não pode ser criado!");
			System.exit(0);
		}
	}

	public String printar() {
		String res = "";
		int cont=1;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++)
				if (alvos[i][j] == "a") {
					res += cont + " alvo: (" + i + "," + j + ")\n";
					cont++;
				}
		}
		return res;
	}


} // end class JogoBatalhaTerrestre
