package aplicacao_console;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Mensagem;
import repositorio.Repositorio;

public class TesteConsole {

	public void teste1() {
		System.out.println("================= TESTE 1 ======================");
		Mensagem m;

		try {
			Fachada.cadastrarPessoa("joao@ifpb", "123", "joao", null);
			Fachada.cadastrarPessoa("maria@ifpb", "123", "maria", null);
			Fachada.cadastrarPessoa("jose@ifpb", "123", "jose", null);
			Fachada.cadastrarAdministrador("admin@ifpb", "123", "admin1", null, "DTI");
			System.out.println("\n-------- listar todas pessoas---------");
			System.out.println(Fachada.listarPessoas(""));
			System.out.println("\n-------- listar  pessoas jo---------");
			System.out.println(Fachada.listarPessoas("jo"));
		} catch (Exception e) {
			System.out.println("mensagem de erro==>" + e.getMessage());
		}

		try {
			Fachada.login("joao@ifpb", "123");
			System.out.println("\npessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("maria@ifpb", "ola maria quanto tempo! saudades");
			m = Fachada.enviarMensagem("jose@ifpb", "ola jose quanto tempo!");
			m = Fachada.enviarMensagem("joao@ifpb", "testando pra mim mesmo...");
			System.out.println("-------- caixa de entrada joao---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("-------- caixa de saida joao---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			Fachada.logoff();
		} catch (Exception e) {
			System.out.println("mensagem de erro==>" + e.getMessage());
		}

		try {
			Fachada.login("maria@ifpb", "123");
			System.out.println("\npessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("joao@ifpb", "oi joao, saudades tb! vamos nos encontar!");
			m = Fachada.enviarMensagem("joao@ifpb", "que tal um almoco?");
			m = Fachada.enviarMensagem("joao@ifpb", "vamos chamar jose?");
			m = Fachada.enviarMensagem("maria@ifpb", "testando pra mim mesmo...");
			System.out.println("-------- caixa de entrada maria---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("-------- caixa de saida maria---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			m = Fachada.apagarMensagem(1);
			System.out.println("mensagem excluida =>" + m.getId());
			Fachada.logoff();
		} catch (Exception e) {
			System.out.println("mensagem de erro==>" + e.getMessage());
		}

		try {
//			Pessoa adm = new Administrador("admin@ifpb", "123", "admin1", null, "DTI");
//			Repositorio.getPessoas().put(adm.getEmail() + adm.getSenha(), adm);
			Fachada.login("admin@ifpb", "123");
			System.out.println("\npessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("joao@ifpb", "Benvindo ao sistema!");
			m = Fachada.enviarMensagem("maria@ifpb", "Benvindo ao sistema!");
			m = Fachada.enviarMensagem("jose@ifpb", "Benvindo ao sistema!");
			System.out.println("-------- caixa de entrada admin---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("-------- caixa de saida admin---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			System.out.println("\n\n******* espionando as mensagens do sistema*********");
			imprimirMensagens(Fachada.espionarMensagens(""));
			System.out.println("\n-------- espionando as mensagens do sistema---------");
			imprimirMensagens(Fachada.espionarMensagens("saudade"));
			System.out.println("\n-------- RELATOIO 1 - pessoas q nao enviaram mensagens---------");
			System.out.println(Fachada.relatorio1());
			System.out.println("\n-------- RELATOIO 2 - mensagens com emitente igual destinatario---------");
			imprimirMensagens(Fachada.relatorio2());
			Fachada.logoff();
		} catch (Exception e) {
			System.out.println("mensagem de erro==>" + e.getMessage());
		}
		System.out.println("teste1 concluido");
	}

	public static void imprimirMensagens(ArrayList<Mensagem> lista) {
		for (Mensagem msg : lista) {
			System.out.println(msg.getId() + " " + msg.getTexto() + " ## " + msg.getEmitente().getNome() + " ## "
					+ msg.getDestinatario().getNome());
		}
	}

	public void teste2() {
		System.out.println("\n================= TESTE 2 ======================");
		try {
			Fachada.login("jose@ifpb", "123");
			Fachada.enviarMensagem("jose@ifpb", "");
			Fachada.enviarMensagem("jose@ifpb",
					"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"
							+ "xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"
							+ "xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"
							+ "xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"
							+ "xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"
							+ "xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx");
			System.out.println("Falha1 => nao valida texto da mensagem");
		} catch (Exception e) {
			System.out.println("==>" + e.getMessage());
		}

		try {
			System.out.println("pessoa logada =>" + Fachada.obterLogada().getNome() + " ");
			Fachada.apagarMensagem(1);
			System.out.println("Falha2 => apagar mensagem de outra pessoa");
		} catch (Exception e) {
			System.out.println("==>" + e.getMessage());
		}

		try {
			Fachada.login("maria@ifpb", "123");
			System.out.println("Falha3 => login invalido");
		} catch (Exception e) {
			System.out.println("==>" + e.getMessage());
		}

		System.out.println("teste2 concluido");
	}

	public void teste3() {
		System.out.println("\n================= TESTE 3 ======================");

		try {
			ImageIcon icon = null;
			icon = new ImageIcon(getClass().getResource("../imagens/pessoa.jpg"));
			Fachada.cadastrarPessoa("ana@ifpb", "123", "ana", icon);
		} catch (Exception e) {
			System.out.println("mensagem de erro==>" + e.getMessage());
		}
		System.out.println("teste3 concluido");
	}

	public void cadastrar() {
		try {
			Fachada.cadastrarPessoa("joao@ifpb", "123", "joao", null);
			Fachada.cadastrarPessoa("maria@ifpb", "123", "maria", null);
			Fachada.cadastrarPessoa("jose@ifpb", "123", "jose", null);
			Fachada.cadastrarAdministrador("admin@ifpb", "123", "admin1", null, "DTI");
			Fachada.login("joao@ifpb", "123");
			Fachada.enviarMensagem("maria@ifpb", "olá maria, sou eu joao!");
			Fachada.enviarMensagem("jose@ifpb", "olá jose, sou eu joao!");
			Fachada.logoff();
			
			Fachada.login("admin@ifpb", "123");
			Fachada.enviarMensagem("joao@ifpb", "Benvindo ao sistema!");
			Fachada.enviarMensagem("maria@ifpb", "Benvindo ao sistema!");
			Fachada.enviarMensagem("jose@ifpb", "Benvindo ao sistema!");
			Fachada.logoff();

			Fachada.login("maria@ifpb", "123");
			Fachada.enviarMensagem("joao@ifpb", "olá joão, sou eu maria!");
			Fachada.enviarMensagem("jose@ifpb", "olá jose, sou eu maria!");
			Fachada.enviarMensagem("maria@ifpb", "testando pra mim mesmo..");
			Fachada.enviarMensagem("joao@ifpb", "joao, sou eu de novo, maria!");
			Fachada.logoff();

			Fachada.login("jose@ifpb", "123");

			System.out.println("cadastro concluido");
		} catch (Exception e) {
			System.out.println("==>" + e.getMessage());
		}
	}

	// ***********************************************
	public static void main(String[] args)
	// ***********************************************
	{
		TesteConsole testeconsole = new TesteConsole();
		testeconsole.teste1();
		testeconsole.teste2();
		testeconsole.teste3();
		testeconsole.cadastrar();
	}

}
