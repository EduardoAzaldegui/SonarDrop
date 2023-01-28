package com.app.drop.util;

import java.io.FileWriter;
import java.io.IOException;
import com.github.javafaker.Faker;

public class Util {

    public static String generateSQLNegocio(int cantidad){
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO NEGOCIO (TIPNEG, NOMNEG, PAGNEG, CONTACTO, NROCONTACTNEG, LOGNEG, CORRNEG, RUCNEG) VALUES ");
        for (int i = 0; i < cantidad; i++) {
            String tipoNegocio = faker.random().nextBoolean() ? "ECOMMERCE" : "PROVEEDOR";
            String nombreNegocio = faker.company().name();
            String paginaWeb = faker.internet().url();
            String contacto = faker.phoneNumber().cellPhone();
            String nroCelular = faker.phoneNumber().cellPhone();
            String logo = faker.internet().url();
            String correo = faker.internet().emailAddress();
            int ruc = faker.number().numberBetween(1000000, 9999999);
            sb.append("('" + tipoNegocio + "', '" + nombreNegocio + "', '" + paginaWeb + "', '" + contacto + "', '" + nroCelular + "', '" + logo + "', '" + correo + "', '" + ruc + "'),");
        }
        String insertSQL = sb.substring(0, sb.length() - 1) + ";";
        return insertSQL;
    }


    public static String generateSQLProducto(int cantidad){
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO PRODUCTOS (NOMPRO, STOCK, URLIMG, PRECVENT, IDNEG, IDCAT) VALUES ");
        for (int i = 0; i < cantidad; i++) {
            String nombreProducto = faker.commerce().productName();
            int stock = faker.number().numberBetween(1, 1000);
            String urlImagen = faker.internet().url();
            double precioVenta = faker.number().randomDouble(2, 1, 100);
            int idNeg = faker.number().numberBetween(1, 20);
            int idCat = faker.number().numberBetween(1, 5);
            sb.append(String.format("('%s','%d','%s','%f','%d','%d'),",nombreProducto,stock,urlImagen,precioVenta,idNeg,idCat));
        }
        String insertSQL = sb.substring(0, sb.length() - 1) + ";";
        return insertSQL;
    }

    public static String generateSQLEmpleado(int cantidad) {
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO EMPLEADOS (TIPEMP, NOMEMP, APEMP, USUEMP, PASSEMP, IDNEG) VALUES ");
        for (int i = 1; i <= cantidad; i++) {
            String tipoEmpleado = faker.bool().bool() ? "ADMINISTRADOR" : faker.bool().bool() ? "LOGISTICA" : "MARKETING";
            String nombreEmpleado = faker.name().firstName();
            String apellidoEmpleado = faker.name().lastName();
            String usuEmpleado = faker.name().username();
            String passEmpleado = faker.internet().password();
            int idNeg = (i % 20) + 1;
            sb.append(String.format("('%s', '%s', '%s', '%s', '%s', %d),", tipoEmpleado, nombreEmpleado, apellidoEmpleado, usuEmpleado, passEmpleado,idNeg));
        }
        String insertSQL = sb.substring(0, sb.length() - 1) + ";";
        return insertSQL;
    }


    public static String generateSQLRol(int cantidad){
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO NEGOCIO (TIPNEG, NOMNEG, CONTACTO) VALUES ");
        for (int i = 0; i < cantidad; i++) {
            String tipoNegocio = faker.bool().bool() ? "Proveedor" : "Ecommerce";
            String nombreNegocio = faker.company().name();
            String numeroContacto = faker.phoneNumber().phoneNumber();
            sb.append(String.format("('%s', '%s', '%s'),", tipoNegocio, nombreNegocio, numeroContacto));
        }
        String insertSQL = sb.substring(0, sb.length() - 1) + ";";
        return insertSQL;
    }

    public static void exportarSQL(String data, String nameFile){
        try {
            FileWriter fileWriter = new FileWriter(nameFile + ".sql");
            String separator = "\\),";
            String[] statements = data.split("\\),");
            for (String statement : statements) {
                fileWriter.write(statement + ")," + System.lineSeparator());
            }
            fileWriter.close();
            System.out.println("Sentencias SQL exportadas exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
