package com.vsm.devcase.util;

import com.vsm.devcase.config.MeuPasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {
		MeuPasswordEncoder encoder = new MeuPasswordEncoder();
		System.out.println(encoder.encode("123"));
	}
	
}
