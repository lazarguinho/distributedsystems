package br.ufc.quixada.sd;

import java.io.OutputStream;
import java.io.IOException;

public class PessoasOutputStream extends OutputStream{
	private OutputStream outputStream;

    public PessoasOutputStream(Pessoa[] pessoas, OutputStream outputStream) {
        this.outputStream = outputStream;
        
        try {
			this.write(pessoas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void write(Pessoa[] pessoas) throws IOException {
    	
    	String numeroDePessoas = pessoas.length + "\n";
    	outputStream.write(numeroDePessoas.getBytes());
    	
        for (Pessoa pessoa : pessoas) {
            String nomePessoa = String.format("%s ", pessoa.getNome());
            String tamPessoa = String.format("%d ", nomePessoa.getBytes().length);
            String dadosPessoa = String.format("%s %d\n", pessoa.getCPF(), pessoa.getIdade());
            outputStream.write(tamPessoa.getBytes());
            outputStream.write(nomePessoa.getBytes());
            outputStream.write(dadosPessoa.getBytes());
        }
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

}
