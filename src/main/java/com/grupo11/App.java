package com.grupo11;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
Exemplo simples de uso da API Apache Commons CVS
Extrair o arquivo commons-csv-1.7.jar para o diretorio do projeto
Para compilar no Windows: javac -cp .;.\commons-csv-1.7.jar App.java
Para compilar no Linux: javac -cp commons-csv-1.7.jar App.java
Para executar no windows: java -cp .;.\commons-csv-1.7.jar App
Para executar no Linux: java -cp .:commons-csv-1.7.jar App
Para executar: java -cp .;.\commons-csv-1.7.jar App.java
*/
public class App {
    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")+"\\resources\\veiculos.dat";
    public static void main(String[] args) throws IOException {
        
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String placa = csvRecord.get(0);
                String marca = csvRecord.get(1);
                String cor = csvRecord.get(2);
                String categoria = csvRecord.get(3);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Placa : " + placa);
                System.out.println("Marca : " + marca);
                System.out.println("Cor : " + cor);
                System.out.println("Categoria : " + categoria);
                System.out.println("---------------\n\n");
            }
        }
        //testing if the class work as expected
        // Passageiro passageiro1 = new Passageiro("462426","luis", FormaPagamento.DINHEIRO, "34234324");
        // Passageiro passageiro2 = new Passageiro("434333","adilson", FormaPagamento.DINHEIRO, "33232");
        // List<Passageiro> passageiros = new ArrayList<>();
        // passageiros.add(passageiro1);
        // passageiros.add(passageiro2);
    
        // PersistenciaPassageiros.persistePassageiros(passageiros);
        // System.out.println(PersistenciaPassageiros.carregaPassageiros());



        // List<Veiculo> veiculos = new ArrayList<>();
        // Veiculo v1 = new Veiculo("IXE1231", "VW", "Branco", CategoriaVeiculo.LUXO);
        // Veiculo v2 = new Veiculo("IXEWQ1", "FD", "Preto", CategoriaVeiculo.NORMAL);
        // veiculos.add(v1);
        // veiculos.add(v2);

        // PersistenciaVeiculos.persisteVeiculos(veiculos);

        // System.out.println(PersistenciaVeiculos.carregaVeiculos());


        List<Veiculo> veiculos = PersistenciaVeiculos.carregaVeiculos();
        List<Motorista> motoristas = new ArrayList<>();
        Veiculo v1 = veiculos.get(0);
        Motorista m1 = new Motorista("1321312", "Luis", v1, FormaPagamento.TODAS);

        Veiculo v2 = veiculos.get(1);
        Motorista m2 = new Motorista("21321312", "Lucas", v2, FormaPagamento.DINHEIRO);

        motoristas.add(m1);
        motoristas.add(m2);
        PersistenciaMotorista.persisteMotorista(motoristas);

        System.out.println(PersistenciaMotorista.carregaMotoristas());

    }
}
