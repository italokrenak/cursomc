package com.nelioalves.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder pe;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = this.clienteRepository.findByEmail(email);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não econtrado.");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		
		if (opt == 0) { // Gerar um digito
			return (char) (random.nextInt(10) + 48);
		} else if(opt == 1) { // Gerar uma letra maiscula
			return (char) (random.nextInt(10) + 65);
		} else { // Gerar uma letra minuscula
			return (char) (random.nextInt(10) + 97);
		}
	}
}
