package com.gerenciador.erroMessages;

public class Erro{
	
	public String mensagemUsuario;
	public String mensagemDesenv;
	
	public Erro(String mensagemUsuario, String mensagemDesenv) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenv = mensagemDesenv;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public String getMensagemDesenv() {
		return mensagemDesenv;
	}

	public void setMensagemDesenv(String mensagemDesenv) {
		this.mensagemDesenv = mensagemDesenv;
	}
	
}