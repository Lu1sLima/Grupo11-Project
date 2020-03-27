package com.grupo11;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PersistenciaPassageiros {

    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")+"\\resources\\passageiros.dat";

    public static List<Passageiro> carregaPassageiros()  throws IOException{
        int i = 0;
        List<Passageiro> passageiros = new ArrayList<Passageiro>();

    try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index  
                if(i > 0){;
                String cpf = csvRecord.get(0);
                String nome = csvRecord.get(1);
                FormaPagamento formaPagamento = FormaPagamento.valueOf(csvRecord.get(2));
                String nroCartao = csvRecord.get(3);
                Passageiro passageiro = new Passageiro(cpf,nome,formaPagamento,nroCartao);
                passageiros.add(passageiro);
                }
                

                i++;
            }

            return passageiros;
        }

        
    }
   
}