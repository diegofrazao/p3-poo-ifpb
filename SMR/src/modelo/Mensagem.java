package modelo;

import java.time.LocalDateTime;

public class Mensagem {
	
	private int id;
	private Pessoa emitente;
	private Pessoa destinatario;
	private String texto;
	private LocalDateTime data;
	
	// CONSTRUCTOR
	public Mensagem(int id, Pessoa emitente, Pessoa destinatario, String texto, LocalDateTime data) {
		super();
		this.id = id;
		this.emitente = emitente;
		this.destinatario = destinatario;
		this.texto = texto;
		this.data = data;
	}
	
	// GETTERS & SETTERS
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pessoa getEmitente() {
		return emitente;
	}
	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}
	public Pessoa getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public String toString() {
		String aux = new String();
		aux = "\nID: " + id +
				"\nEmitente: " + emitente.getEmail() +
				"\nDestinatario: " + destinatario.getEmail() +
				"\nTexto: " + texto +
				"\nData: " + data + "\n";
		return aux;
	}
	
	
	
}