package com.usuario.consumer.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Login {

	private String cpf;
	private String senha;
	private LocalDateTime dataGerado;
	private LocalDateTime dataExpiracao;
	
	public Login() {
		this.dataGerado = LocalDateTime.now();
		this.dataExpiracao = dataGerado.plusMinutes(5);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataGerado() {
		return dataGerado;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Login [cpf=" + cpf + ", senha=" + senha + ", dataGerado=" + dataGerado + ", dataExpiracao="
				+ dataExpiracao + "]";
	}		
}
