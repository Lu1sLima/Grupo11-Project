import java.util.List;

import com.grupo11.CategoriaVeiculo;

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
    private static List<Veiculo> veiculos = new ArrayList<Veiculo>();

    public static List<Veiculo> carregaVeiculos() throws IOException {
    
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser data = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVParser veiculo : data) {
                String placa = veiculo.get(0);
                String marca = veiculo.get(1);
                String cor = veiculo.get(2);
                CategoriaVeiculo categoria = CategoriaVeiculo.valueOf(veiculo.get(3));
                Veiculo v = new Veiculo(placa, marca, cor, categoria);
                veiculos.add(v);
            }
            return veiculos;
        }
    }
    
    public static boolean persisteVeiculos(List<Veiculo> veiculos) throws IOException{
        for (Veiculo veiculo: veiculos){ 
            if (veiculos.contains(veiculo)) { return true; }
        }       
        return false;
    }
}