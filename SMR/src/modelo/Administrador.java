package modelo;

import javax.swing.ImageIcon;

public class Administrador extends Pessoa {
	
	String setor;

	public Administrador(String email, String senha, String nome, ImageIcon imagem, String setor) {
		super(email, senha, nome, imagem);
		this.setor = setor;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		return super.toString() + "=>Setor: " + setor + "\n";
	}

}
