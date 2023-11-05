package br.ufc.quixada.sd;

import java.io.FileOutputStream;
import java.net.Socket;

import br.ufc.quixada.sd.Pessoa;
import br.ufc.quixada.sd.PessoasOutputStream;

public class Main {
	public static void main(String[] args) {
		Pessoa[] pessoas = new Pessoa[2];
		
		pessoas[0] = new Pessoa("João", "123.456.789-00", 30);
		pessoas[1] = new Pessoa("Maria", "987.654.321-00", 25);
		
		//Teste sua implementação utilizando como destino a saída padrão (System.out).
		PessoasOutputStream pessoasSystemOut = new PessoasOutputStream(pessoas, System.out);
		
		//Teste sua implementação utilizando como destino um arquivo (FileOutputStream).
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("pessoas.txt");
			PessoasOutputStream pessoasFileOutputStream = new PessoasOutputStream(pessoas, fileOutputStream);
			pessoasFileOutputStream.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//Teste sua implementação utilizando como destino um servidor remoto (TCP).
		try {
			Socket socket = new Socket("192.168.1.32", 234);
			PessoasOutputStream pessoasTCP = new PessoasOutputStream(pessoas, socket.getOutputStream());
			pessoasTCP.close();
			socket.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}±
		
	}

}
