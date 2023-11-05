package br.ufc.quixada.sd;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
    	System.out.println("Teste sua implementação utilizando como origem a entrada padrão (System.in)");
        try {
        	PessoasInputStream pessoasSystemIn = new PessoasInputStream(System.in);
        	
        	Pessoa[] pessoas = pessoasSystemIn.readPessoas();
        	
        	for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCPF());
                System.out.println("Idade: " + pessoa.getIdade());
                System.out.println();
            }
        	
        	pessoasSystemIn.close();
        	
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    	
    	System.out.println("Teste sua implementação utilizando como origem um arquivo (FileInputStream).");
    	try {
            FileInputStream fileInputStream = new FileInputStream("pessoas.txt");

            PessoasInputStream pessoasFileInputStream = new PessoasInputStream(fileInputStream);

            Pessoa[] pessoas = pessoasFileInputStream.readPessoas();

            for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCPF());
                System.out.println("Idade: " + pessoa.getIdade());
                System.out.println();
            }

            pessoasFileInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	System.out.println("Teste sua implementação utilizando como destino um servidor remoto (TCP).");
    	try {
    		Socket socket = new Socket("192.168.1.30", 234);
    		PessoasInputStream pessoasTCP = new PessoasInputStream(socket.getInputStream());
    		
    		Pessoa[] pessoas = pessoasTCP.readPessoas();
    		
    		for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCPF());
                System.out.println("Idade: " + pessoa.getIdade());
                System.out.println();
            }
    		
    		
    		pessoasTCP.close();
    		socket.close();
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
}
