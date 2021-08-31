package modelo;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pessoa {

	private String email;
	private String senha;
	private String nome;
	private ImageIcon imagem;
	private ArrayList<Mensagem> caixaEntrada = new ArrayList<Mensagem>();
	private ArrayList<Mensagem> caixaSaida = new ArrayList<Mensagem>();

	// CONSTRUCTOR
	public Pessoa(String email, String senha, String nome, ImageIcon imagem) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.imagem = imagem;
	}

	// GETTERS & SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Mensagem> getCaixaEntrada(){
		return caixaEntrada;
	}
	
	public ArrayList<Mensagem> getCaixaSaida(){
		return caixaSaida;
	}
	public int getSaidaSize() {
		return caixaSaida.size();
	}

	@Override
	public String toString() {
		String texto = new String();
		texto = "\n=>Email: " + email + "\n=>Senha: " + senha + "\n=>Nome: " + nome + "\n";
		return texto;
	}

	
}
