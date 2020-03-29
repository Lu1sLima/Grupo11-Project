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

public class PersistenciaVeiculos{
    
    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")+"\\resources\\veiculos.dat";

    public static List<Veiculo> carregaVeiculos() throws IOException {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser data = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            for (CSVRecord veiculo : data) {
                String placa = veiculo.get("placa");
                String marca = veiculo.get("marca");
                String cor = veiculo.get("cor");
                CategoriaVeiculo categoria = CategoriaVeiculo.valueOf(veiculo.get("categoriaVeiculo"));
                Veiculo v = new Veiculo(placa, marca, cor, categoria);
                veiculos.add(v);
            }
            return veiculos;
        }
    }
    
    public static void persisteVeiculos(List<Veiculo> veiculos) throws IOException{
        try (          BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("placa", "marca", "cor", "categoriaVeiculo"));
        ) {

            for(Veiculo veiculo: veiculos){
            csvPrinter.printRecord(veiculo.getPlaca(), veiculo.getMarca(), veiculo.getCor(), veiculo.getCategoriaVeiculo());
            
            } 

            csvPrinter.flush();           
        }
    }
}