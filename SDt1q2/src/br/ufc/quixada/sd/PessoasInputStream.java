package br.ufc.quixada.sd;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PessoasInputStream extends InputStream{
	private InputStream inputStream;
	
	public PessoasInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
	
	public Pessoa[] readPessoas() throws IOException {
        Scanner scanner = new Scanner(inputStream);
        int numeroDePessoas = scanner.nextInt();
        scanner.nextLine();

        Pessoa[] pessoas = new Pessoa[numeroDePessoas];
        for (int i = 0; i < numeroDePessoas; i++) {
        	scanner.next("\\d+");
            String nome = scanner.next("\\D+");
            String CPF = scanner.next();
            int idade = scanner.nextInt();
            scanner.nextLine(); // Pula a linha após a idade

            pessoas[i] = new Pessoa(nome, CPF, idade);
        }

        return pessoas;
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

}
