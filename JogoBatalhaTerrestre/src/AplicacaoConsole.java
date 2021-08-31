
/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Aplicação console do Quebra-Cabeca
 * 
 */

import java.util.Scanner;

import biblioteca.BatalhaTerrestre;

public class AplicacaoConsole {
	public static void main(String[] args) {

		BatalhaTerrestre jogo = new BatalhaTerrestre();
		Scanner teclado = new Scanner(System.in);
		int linha, coluna;
		System.out.println(jogo.printar());

		do {
			System.out.println(jogo);
			System.out.println("\tTotal de acertos: " + jogo.getAcertos());
			System.out.println("\tTotal de tiros: " + jogo.getTiros());
			System.out.print("\nDigite a linha do tiro:");
			linha = teclado.nextInt();
			System.out.print("Digite a coluna do tiro: ");
			coluna = teclado.nextInt();
			try {
				String resposta = jogo.atirar(linha, coluna);
				System.out.println(resposta);
			} catch (Exception erro) {
				System.out.println(erro.getMessage());
			}
			System.out.println(" ");
		} while (!jogo.terminou());

		System.out.println("\n GAME OVER !!\n");
		System.out.println(jogo.getResultadoFinal());
		teclado.close();
	}

}
