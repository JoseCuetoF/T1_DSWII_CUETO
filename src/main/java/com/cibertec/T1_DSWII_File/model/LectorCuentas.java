package com.cibertec.T1_DSWII_File.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.io.FileWriter;
import java.util.List;

public class LectorCuentas {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();


        InputStream input = LectorCuentas.class.getClassLoader().getResourceAsStream("cuentas.json");
        if (input == null) {
            System.out.println("No se encontró el archivo cuentas.json en resources.");
            return;
        }

        List<Cuenta> cuentas = mapper.readValue(input, new TypeReference<List<Cuenta>>() {});

        for (Cuenta cuenta : cuentas) {
            if (cuenta.estado) {
                String nombreArchivo = "cuenta_" + cuenta.nro_cuenta + ".txt";
                FileWriter fw = new FileWriter(nombreArchivo);
                fw.write("Banco de origen: " + cuenta.banco + "\n");

                if (cuenta.saldo > 5000.00) {
                    fw.write("La cuenta con el nro de cuenta: " + cuenta.nro_cuenta + " tiene un saldo de " + cuenta.saldo + "\n");
                    fw.write("Usted es apto a participar en el  concurso de la SBS por 10000.00 soles.\n");
                    fw.write("Suerte!\n");
                } else {
                    fw.write("La cuenta con el nro de cuenta: " + cuenta.nro_cuenta + " no tiene un saldo superior a 5000.00.\n");
                    fw.write("Lamentablemente no podrá acceder al concurso de la SBS por 10000.00 soles.\n");
                    fw.write("Gracias\n");
                }

                fw.close();
            }
        }

        System.out.println("Archivos generados con éxito.");
    }
}
