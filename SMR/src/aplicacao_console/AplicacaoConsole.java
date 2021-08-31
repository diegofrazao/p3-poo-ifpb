package aplicacao_console;

import java.util.ArrayList;
import java.util.Scanner;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;

public class AplicacaoConsole {
	private Scanner teclado = new Scanner(System.in);

	public AplicacaoConsole() {
		//pre-cadastro
		try{
			TesteConsole teste = new TesteConsole();
			teste.cadastrar();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		processarMenu();
	}

	public  void processarMenu() {
		String entrada;
		int opcao;
		System.out.println("\n\n <-- Bem vindo ao chat SMR --> ");
		do {
			exibirMenu();
			try{
				entrada = teclado.nextLine();
				opcao = Integer.parseInt(entrada);
				switch (opcao) {
				case 0:	break;
				case 1:	tarefa_cadastrarPessoa();				break;
				case 2:	tarefa_cadastrarAdministrador();		break;
				case 3:	tarefa_efetuarLogin();					break;
				case 4: tarefa_efetuarLogoff();					break;
				case 5:	tarefa_verLogada();						break;
				case 6: tarefa_enviarMensagem();				break;
				case 7:	tarefa_apagarMensagem();				break;
				case 8: tarefa_listarPessoas();					break;
				case 9: tarefa_listarCaixaEntrada();			break;
				case 10: tarefa_listarCaixaSaida();				break;
				case 11: tarefa_espionarMensagens();			break;
				case 12: tarefa_relatorio1();					break;
				case 13: tarefa_relatorio2();					break;
				default: System.out.println("Opção Invalida !! \n");
				}
			}
			catch(NumberFormatException e)	{
				System.out.println("Digite somente número! \n");
				opcao=-1;
			}
			catch(Exception e)	{
				System.out.println("erro:" + e.getMessage());
				opcao=-1;
			}		
		}while (opcao != 0);
		System.out.println("\n <-- Até Breve -->");
	}

	public void exibirMenu() {
		System.out.println("\n\n| - - - - - - - - -  Menu  - - - - - - - - - - - |");
		System.out.println("|  [0]- Sair                                     |");
		System.out.println("|  [1]- Cadastrar pessoa                         |");
		System.out.println("|  [2]- Cadastrar administrador                  |");
		System.out.println("|  [3]- Efetuar login                            |");
		System.out.println("|  [4]- Efetuar logoff                           |");
		System.out.println("|  [5]- Ver pessoa logada                        |");
		System.out.println("|  [6]- Enviar mensagem                          |");
		System.out.println("|  [7]- Apagar mensagem                          |");
		System.out.println("|  [8]- Listar pessoas                           |");
		System.out.println("|  [9]- Listar caixa de entrada                  |");
		System.out.println("|  [10]- Listar caixa de saida                   |");
		System.out.println("|  [11]- Espionar mensagens                      |");
		System.out.println("|  [12]- Relatorio (1)                           |");
		System.out.println("|  [13]- Relatorio (2)                           |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - -|");
		System.out.print("  Opção : ");
	}


	//	---------------------------------------	
	public void tarefa_cadastrarPessoa(){
	//	---------------------------------------			
		System.out.println("\n---CADASTRO DE PESSOA---");
		Pessoa p;
		String email;
		String senha;
		String nome;
		try{
			System.out.print("Email da Pessoa(ou ENTER para voltar):");
			email = teclado.nextLine();
			System.out.print("Senha da Pessoa(ou ENTER para voltar):");
			senha = teclado.nextLine();
			System.out.print("Nome da Pessoa(ou ENTER para voltar):");
			nome = teclado.nextLine();
			p = Fachada.cadastrarPessoa(email, senha, nome, null) ;
			System.out.println("--> cadastrado com sucesso ! --> " + p.getNome() +"\n");
		}
		catch(Exception e){
			System.out.println("-->" + e.getMessage());
		}	
	}

//	---------------------------------------	
	public void tarefa_cadastrarAdministrador(){
	//	---------------------------------------			
		System.out.println("\n---CADASTRO DE ADMINISTRADOR---");
		Pessoa adm;
		String email;
		String senha;
		String nome;
		String setor;
		try{
			System.out.print("Email do Administrador(ou ENTER para voltar):");
			email = teclado.nextLine();
			System.out.print("Senha do Administrador(ou ENTER para voltar):");
			senha = teclado.nextLine();
			System.out.print("Nome do Administrador(ou ENTER para voltar):");
			nome = teclado.nextLine();
			System.out.print("Setor do Administrador(ou ENTER para voltar):");
			setor = teclado.nextLine();
			adm = Fachada.cadastrarAdministrador(email, senha, nome, null, setor) ;
			System.out.println("--> cadastrado com sucesso ! --> " + adm.getNome() +"\n");
		}
		catch(Exception e){
			System.out.println("-->" + e.getMessage());
		}	
	}
	
	//	---------------------------------------	
	public void tarefa_efetuarLogin(){
	//	---------------------------------------			
		System.out.println("\n---EFETUAR LOGIN---");
		String email;
		String senha;
		try{
			System.out.print("Email da Pessoa(ou ENTER para voltar):");
			email = teclado.nextLine();
			System.out.print("Senha da Pessoa(ou ENTER para voltar):");
			senha = teclado.nextLine();
			if(Fachada.login(email, senha) != null)
				System.out.println("login com sucesso! --> "+Fachada.obterLogada().getEmail());
							
		}catch(Exception e) {
			System.out.println("-->" + e.getMessage());
		}
	}
	
	//	---------------------------------------	
	public void tarefa_efetuarLogoff()
	//	---------------------------------------	
	{
		System.out.println("\n---EFETUAR LOGOFF---");
		System.out.println("pessoa se deslogando: " + Fachada.obterLogada().getEmail());
		try{
			Fachada.logoff();
		}catch (Exception e) {
			System.out.println("-->"+e.getMessage());
		}
	}
	//	---------------------------------------	
	public void tarefa_verLogada(){
	//	---------------------------------------	
		System.out.println("\n---VER PESSOA LOGADA");
		System.out.println("\n ---> "+Fachada.obterLogada().getEmail());
	}
	
	//	---------------------------------------	
	public void tarefa_enviarMensagem(){
	//	---------------------------------------			
		System.out.println("\n---ENVIO DE MENSAGEM---");
		Mensagem m;
		
		String destinatario;
		String texto;
		System.out.print("destinatario da mensagem(ou ENTER para voltar): ");
		destinatario = teclado.nextLine();	
		System.out.println("texto da mensagem(ou ENTER para voltar): ");
		texto = teclado.nextLine();
		while (!destinatario.equals("")) {
			try{				
				m = Fachada.enviarMensagem(destinatario, texto) ;
				System.out.println("--> cadastrado com sucesso ! --> " + m.getId() +"\n");
			}
			catch(Exception e){
				System.out.println("-->" + e.getMessage());
			}
			System.out.print("destinatario da mensagem(ou ENTER para voltar): ");
			destinatario = teclado.nextLine();	
			System.out.println("texto da mensagem(ou ENTER para voltar): ");
			texto = teclado.nextLine();	
		}
	}
	
	//	---------------------------------------	
	public void tarefa_apagarMensagem(){
	//	---------------------------------------	
		System.out.println("\n---APAGAR MENSAGEM---");
		int id;
		System.out.println("id da mensagem(ou ENTER para voltar): ");
		id = teclado.nextInt();
		try {
			Fachada.apagarMensagem(id);
		}catch (Exception e){
			System.out.println("-->" + e.getMessage());
		}
	}


	//	---------------------------------------	
	public void tarefa_listarPessoas()
	//	---------------------------------------	
	{
		System.out.println("\n---LISTAGEM DE PESSOAS---");
		String nome;
		System.out.print("Nome do Pessoa(ou ENTER para voltar):");
		nome = teclado.nextLine();	
		if (nome.equals("")) {
			try{
				System.out.println("\n---------inicio--------");
				String texto;
				ArrayList<Pessoa> lista1 = Fachada.listarPessoas(nome);
				texto = "Listagem de pessoas: \n";
				for (Pessoa p: lista1)
					texto += p;
				
				System.out.println(texto);
				System.out.println("-----------fim-----------");			
			}
			catch(Exception e){
				System.out.println("-->" + e.getMessage());
			}
		}else {
			while(!nome.equals("")) {
				try{
					System.out.println("\n---------inicio--------");
					String texto;
					ArrayList<Pessoa> lista1 = Fachada.listarPessoas(nome);
					texto = "Listagem de pessoas: \n";
					for (Pessoa p: lista1)
						texto += p;
					
					System.out.println(texto);
					System.out.println("-----------fim-----------");
				}
				catch(Exception e){
					System.out.println("-->" + e.getMessage());
				}
				System.out.print("\nNome do Pessoa(ou ENTER para voltar):");
				nome = teclado.nextLine();	
			}
		}
	}

	//	---------------------------------------	
	public void tarefa_listarCaixaEntrada()
	//	---------------------------------------	
	{
		System.out.println("\n---------inicio--------");
		try{
			ArrayList<Mensagem> lista2 = Fachada.listarCaixaEntrada();
			String texto = "==> Listagem da caixa de entrada: \n\n";
			if (lista2.isEmpty())
				texto += "não tem mensagem na caixa de entrada\n";
			else 
				for(Mensagem p: lista2) 
					texto +=  p.getTexto() + "\n"; 
			System.out.println(texto);
		}catch(Exception e) {
			System.out.println("-->" + e.getMessage());
		}
		System.out.println("-----------fim-----------");
	}

	//	---------------------------------------	
	public void tarefa_listarCaixaSaida()
	//	---------------------------------------	
	{
		System.out.println("\n---------inicio--------");
		try{
			ArrayList<Mensagem> lista3 = Fachada.listarCaixaSaida();
			String texto = "==> Listagem da caixa de saida: \n";
			if (lista3.isEmpty())
				texto += "não tem mensagem na caixa de saida\n";
			else 
				for(Mensagem p: lista3) 
					texto +=  p.getTexto() + "\n"; 
			System.out.println(texto);
		}catch(Exception e) {
			System.out.println("-->" + e.getMessage());
		}
		System.out.println("-----------fim-----------");
	}

	//	---------------------------------------	
	public void tarefa_espionarMensagens() 
	//	---------------------------------------	
	{
		System.out.println("\n---ESPIONANDO MENSAGENS---");
		String termo;
		System.out.print("Termo para busca da mensagem(ou ENTER para voltar):");
		termo = teclado.nextLine();	
		if (termo.equals("")) {
			try{
				System.out.println("\n---------inicio--------");
				String texto;
				ArrayList<Mensagem> lista1 = Fachada.espionarMensagens(termo);
				texto = "Listagem de mensagens: \n";
				for (Mensagem m: lista1)
					texto += m.getTexto() + "\n";
				
				System.out.println(texto);
				System.out.println("-----------fim-----------");			
			}
			catch(Exception e){
				System.out.println("-->" + e.getMessage());
			}
		}else {
			while(!termo.equals("")) {
				try{
					System.out.println("\n---------inicio--------");
					String texto;
					ArrayList<Mensagem> lista1 = Fachada.espionarMensagens(termo);
					texto = "Listagem de mensagens: \n";
					for (Mensagem m: lista1)
						texto += m.getTexto() + "\n";
					
					System.out.println(texto);
					System.out.println("-----------fim-----------");
				}
				catch(Exception e){
					System.out.println("-->" + e.getMessage());
				}
				System.out.print("Termo para busca da mensagem(ou ENTER para voltar):");
				termo = teclado.nextLine();	
			}
		}
	}

	//	---------------------------------------	
	public void tarefa_relatorio1() 
	//	---------------------------------------	
	{
		System.out.println("\n---EXIBINDO O RELATORIO (1)---");
		try{
			ArrayList<Pessoa> pessoa = new ArrayList<>(Fachada.relatorio1());
			for (Pessoa p: pessoa) {
				System.out.println(p);
			}
		}catch (Exception e) {
			System.out.println("-->" + e.getMessage());
		}
	}
	
	//	---------------------------------------	
	public void tarefa_relatorio2() 
	//	---------------------------------------	
	{
		System.out.println("\n---EXIBINDO O RELATORIO (2)---");
		try{
			ArrayList<Mensagem> mensagem = new ArrayList<>(Fachada.relatorio2());
			for (Mensagem m: mensagem) {
				System.out.println(m.getTexto()+" || Emitente: "+m.getEmitente().getEmail());
			}
		}catch (Exception e) {
			System.out.println("-->" + e.getMessage());
		}
	}
	
	//  ***********************************************
	public static void main (String[] args)   
	//  ***********************************************
	{
		new AplicacaoConsole();
	}

}
