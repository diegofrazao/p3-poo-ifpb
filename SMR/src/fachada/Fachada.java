package fachada;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import modelo.Administrador;
import modelo.Mensagem;
import modelo.Pessoa;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio rep = new Repositorio();
	private static Pessoa log;
	private static int idmensagem = 1;
	
	// EFETUAR O LOGIN
	public static Pessoa login(String email, String senha) throws Exception {
		if (log != null)
			throw new Exception("Já existe um usuário logado: " + log.getEmail());
		
		Pessoa pessoa = rep.localizarPessoa(email, senha);
		
		if (pessoa == null)
			throw new Exception("email ou senha invalido(s)!");
		else if (log == pessoa)
			throw new Exception("Você já está logado(a)!!");
		
		log = pessoa;
		return log;
	}
	
	// EFETUAR O LOGOFF
	public static Pessoa logoff() throws Exception {
		if (log == null)
			throw new Exception("Sem pessoa logada!");
			
		log = null;
		return log;
	}
	
	// VISUALIZAR QUEM ESTA LOGADO
	public static Pessoa obterLogada() {
		return log;
	}
	
	// CADASTRAR UMA PESSOA
	public static Pessoa cadastrarPessoa(String email, String senha, String nome, ImageIcon imagem)
			throws Exception {
		Pessoa pessoa = rep.localizarPessoa(email, senha);
		if (pessoa != null)
			throw new Exception("Operação invalida!\nUsuário já cadastrado.");

		pessoa = new Pessoa(email, senha, nome, imagem);
		rep.adicionarPessoa(pessoa);
		return pessoa;
	}
	
	// CADASTRAR UM ADMINISTRADOR
	public static Pessoa cadastrarAdministrador(String email, String senha, String nome, ImageIcon imagem, String setor) 
			throws Exception{
//		if (log instanceof Administrador == false)
//			throw new Exception ("Precisa esta logado como Administrador para cadastrar");
		
		Pessoa adm = new Administrador(email, senha, nome,imagem,setor);
		
		if (rep.localizarPessoa(adm.getEmail(), adm.getSenha()) != null)
			throw new Exception("O administrador já foi cadastrado !!!");
		
		rep.adicionarPessoa(adm);
		
		return adm;
	}
	
	// LISTAR TODAS AS PESSOAS
	public static ArrayList<Pessoa> listarPessoas(String termo) {
		return rep.getPessoasNome(termo);
	}
	
	// ENVIAR UMA MENSAGEM PARA UM DESTINATARIO (PESSOA LOGADA = REMETENTE)
	public static Mensagem enviarMensagem(String emailDestinatario, String texto) 
			throws Exception{
		if(log == null)
			throw new Exception ("Você não está logado(a).");
		else if (texto.equals("") || texto == null)
			throw new Exception ("Mensagem vazia!");
		else if (texto.length() > 200)
			throw new Exception ("Mensagem muito grande!");
		
		Pessoa destinatario = rep.getPessoaEmail(emailDestinatario);
		
		if (destinatario == null)
			throw new Exception ("Destinatario não cadastrado!");
		
		LocalDateTime data = LocalDateTime.now();
		
		Mensagem m = new Mensagem(idmensagem, log, destinatario, texto, data);
		
		
			destinatario.getCaixaEntrada().add(m);
			log.getCaixaSaida().add(m);
			rep.adicionarMensagem(m);
			idmensagem++;
			return m;
		
	}
	
	// LISTAR A CAIXA DE ENTRAR DA PESSOA LOGADA
	public static ArrayList<Mensagem> listarCaixaEntrada() throws Exception{
		if(log == null) 
			throw new Exception("Não existe usuário logado !!!");
		else 
			return log.getCaixaEntrada();
	}
	
	// LISTAR A CAIXA DE SAIDA DA PESSOA LOGADA
	public static ArrayList<Mensagem> listarCaixaSaida() throws Exception{
		if(log == null) 
			throw new Exception("Não existe usuário logado !!!");
		else 
			return log.getCaixaSaida();
	}

	// APAGA AS MENSAGENS DAS CAIXAS DE ENTRADA/SAIDA
	public static Mensagem apagarMensagem(int idmensagem) throws Exception{
		
		if(log == null) 
			throw new Exception("É preciso estar logado para excluir a mensagem.");
		
		Mensagem mensagem = rep.localizarMensagem(idmensagem);
		
		if(mensagem == null)
			throw new Exception("A mensagem não existe.");
		
		Pessoa emitente = mensagem.getEmitente();
		Pessoa destinatario = mensagem.getDestinatario();
		
		if (emitente == log) {
				log.getCaixaSaida().remove(mensagem);
		}else if(destinatario == log) {
			log.getCaixaEntrada().remove(mensagem);
		}else {
			throw new Exception("Não pode remover mensagem do amiguinho.");
		}
		return mensagem;
	}
	
	// QUANDO ADMINISTRADOR, VER AS MENSAGENS DE TODAS AS PESSOAS
	public static ArrayList<Mensagem> espionarMensagens(String termo) throws Exception{
		if(log instanceof Administrador == false)
			throw new Exception("Você precisa ser um administrador do sistema !!!");
		
		ArrayList<Mensagem> mensagem = rep.getMensagens();
		ArrayList<Mensagem> listaMensagens = new ArrayList<Mensagem>();
		
		if(termo.trim().equals("")) {
			return mensagem;
		}else {
			for(Mensagem m: mensagem) {
				if(m.getTexto().contains(termo))
					listaMensagens.add(m);
			}
			return listaMensagens;
		}
	}
	
	public static ArrayList<Pessoa> relatorio1() throws Exception {
//		if(log instanceof Administrador == false)
//			throw new Exception("Você precisa ser um administrador do sistema !!!");
	
		ArrayList<Pessoa> pessoa = new ArrayList<>();
		for (Pessoa p : rep.getPessoas().values()) {
			if (p.getSaidaSize() == 0)
				pessoa.add(p);
		}
		return pessoa;
	}
	
	public static ArrayList<Mensagem> relatorio2() throws Exception {
		if(log instanceof Administrador == false)
			throw new Exception("Você precisa ser um administrador do sistema !!!");
	
		ArrayList<Mensagem> mensagem = new ArrayList<>();
		for (Mensagem m : rep.getMensagens()) {
			if (m.getEmitente().getEmail().equals(m.getDestinatario().getEmail()))
				mensagem.add(m);
		}
		return mensagem;
	}
}




