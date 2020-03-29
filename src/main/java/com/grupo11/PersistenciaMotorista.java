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

public class PersistenciaMotorista{
    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")+"\\resources\\motoristas.dat";

    public static List<Motorista> carregaMotoristas()  throws IOException{
        List<Motorista> motoristas = new ArrayList<>();
        List<Veiculo> veiculos = PersistenciaVeiculos.carregaVeiculos();

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
                String placa = csvRecord.get("veiculo");
                Veiculo v1 = null;
                for(Veiculo v : veiculos){
                    if(v.getPlaca() == placa){
                        v1 = v;
                    }
                }
                Motorista motorista = new Motorista(cpf,nome,v1,formaPagamento);
                motoristas.add(motorista);                   
            }
        }
        return motoristas;
    }

    public static void persisteMotorista(List<Motorista> motoristas)  throws IOException{
        try (          BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("cpf", "nome", "veiculo", "formaPagamento"));
        ) {

            for(Motorista motorista: motoristas){
            csvPrinter.printRecord(motorista.getCPF(), motorista.getNome(), motorista.getVeiculo().getPlaca(), motorista.getFormaPagamento());
            
            } 

            csvPrinter.flush();           
        }
    }
}