package com.grupo11;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;



public class PersistenciaPassageiros {

    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")+"\\resources\\passageiros.dat";

    public static List<Passageiro> carregaPassageiros()  throws IOException{
        List<Passageiro> passageiros = new ArrayList<Passageiro>();

    try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index  
                
                String cpf = csvRecord.get("cpf");
                String nome = csvRecord.get("nome");
                FormaPagamento formaPagamento = FormaPagamento.valueOf(csvRecord.get("formaPagamento"));
                String nroCartao = csvRecord.get("nroCartao");
                Passageiro passageiro = new Passageiro(cpf,nome,formaPagamento,nroCartao);
                passageiros.add(passageiro);                   
            }
            return passageiros;
        }
    }

    public static void persistePassageiros(List<Passageiro> passageiros) throws IOException{
            try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("cpf", "nome", "formaPagamento", "nroCartao"));
        ) {

            for(Passageiro passageiro: passageiros){
            csvPrinter.printRecord(passageiro.getCpf(), passageiro.getNome(), passageiro.getFormaPgto(), passageiro.getNroCartao());
            
            } 

            csvPrinter.flush();           
        }
    }
   
}

