package repositorio;

import java.util.ArrayList;
import java.util.TreeMap;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Mensagem;
import modelo.Pessoa;



public class Repositorio {
	
	private TreeMap<String, Pessoa> pessoas = new TreeMap<>();
	private ArrayList<Mensagem> mensagens = new ArrayList<>();
	

	// PESSOAS
	public void adicionarPessoa (Pessoa p) {
		pessoas.put(p.getEmail()+p.getSenha(), p);
	}
	public void removerPessoa (Pessoa p) {
		pessoas.remove(p.getEmail()+p.getSenha());
	}
	public Pessoa localizarPessoa (String email, String senha) {
		return pessoas.get(email+senha);
	}
	
	public ArrayList<Pessoa> getPessoasNome (String termo) {
		if(termo.equals("")) {
			return new ArrayList<Pessoa> (pessoas.values()); // TreeMap -> ArrayList
		} else {
			ArrayList<Pessoa> pessoa = new ArrayList<>();
			for(Pessoa p : pessoas.values()) {
				if(p.getNome().contains(termo))
					pessoa.add(p);
			}
			return pessoa;
		}
	}
	
	public Pessoa getPessoaEmail (String email) {
		for (Pessoa p : pessoas.values()) {
			if (p.getEmail().equals(email))
				return p;
		}
		return null;
	}
	
	// MENSAGENS
	public void adicionarMensagem (Mensagem m) {
		mensagens.add(m);
	}
	public void removerMensagem (Mensagem m) {
		mensagens.remove(m);
	}
	public Mensagem localizarMensagem (int id) {
		for (Mensagem m: mensagens) {
			if(m.getId()==id)
				return m;
		}
		return null;
	}
	
	public ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}
	
	public TreeMap<String, Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void iniciar() {
		Pessoa p = new Pessoa("joao@ifpb", "123", "joao", null);
		pessoas.put(p.getEmail()+p.getSenha(), p);
		p = new Pessoa("maria@ifpb", "123", "maria", null);
		pessoas.put(p.getEmail()+p.getSenha(), p);
		p = new Pessoa("jose@ifpb", "123", "jose", null);
		pessoas.put(p.getEmail()+p.getSenha(), p);
		p = new Administrador("admin@ifpb", "123", "admin1", null, "DTI");
		pessoas.put(p.getEmail()+p.getSenha(), p);
		try {
		Fachada.login("joao@ifpb", "123");
        Fachada.enviarMensagem("maria@ifpb", "olá maria, sou eu joao!");
        Fachada.enviarMensagem("jose@ifpb", "olá jose, sou eu joao!");
        Fachada.logoff();

        Fachada.login("maria@ifpb", "123");
        Fachada.enviarMensagem("joao@ifpb", "olá joão, sou eu maria!");
        Fachada.enviarMensagem("jose@ifpb", "olá jose, sou eu maria!");
        Fachada.enviarMensagem("maria@ifpb", "testando pra mim mesmo..");
        Fachada.enviarMensagem("joao@ifpb", "joao, sou eu de novo, maria!");
        Fachada.logoff();
        
        Fachada.login("jose@ifpb", "123");
        
        System.out.println("cadastro concluido");
		}catch(Exception e) {
			System.out.println("==>" + e.getMessage());
		}
	}
}
