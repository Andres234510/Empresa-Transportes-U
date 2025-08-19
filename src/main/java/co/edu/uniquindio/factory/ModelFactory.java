package co.edu.uniquindio.factory;

import co.edu.uniquindio.model.Propietario;
import co.edu.uniquindio.model.VehiculoCarga;
import co.edu.uniquindio.model.VehiculoPasajero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelFactory {
    private static ModelFactory instancia;

    private List<VehiculoPasajero> vehiculosPasajeros;
    private List<VehiculoCarga> vehiculosCarga;

    private ModelFactory() {
        vehiculosPasajeros = new ArrayList<>();
        vehiculosCarga = new ArrayList<>();
    }

    public static ModelFactory getInstance() {
        if (instancia == null) {
            instancia = new ModelFactory();
        }
        return instancia;
    }


    public void inicializarDatos() {
        vehiculosPasajeros.clear();
        vehiculosCarga.clear();

        Propietario p1 = new Propietario("Carlos", "123", 45, 80.5);
        Propietario p2 = new Propietario("Ana", "456", 32, 65.0);
        Propietario p3 = new Propietario("Luis", "789", 50, 90.0);

        vehiculosPasajeros.add(new VehiculoPasajero("AAA111", "Chevrolet", 40));
        vehiculosPasajeros.add(new VehiculoPasajero("BBB222", "Nissan", 30));
        vehiculosPasajeros.add(new VehiculoPasajero("CCC333", "Toyota", 25));

        vehiculosCarga.add(new VehiculoCarga("DDD444", "Volvo", 10000, p1));
        vehiculosCarga.add(new VehiculoCarga("EEE555", "Mercedes", 12000, p2));
        vehiculosCarga.add(new VehiculoCarga("FFF666", "Scania", 15000, p3));

        System.out.println("Datos de prueba inicializados correctamente.");
    }

    public void crearPropietarioYVehiculo(Scanner sc) {
        System.out.print("Ingrese nombre del propietario: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese cédula del propietario: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese edad del propietario: ");
        int edad = sc.nextInt();

        System.out.print("Ingrese peso del propietario: ");
        double peso = sc.nextDouble();
        sc.nextLine();

        Propietario propietario = new Propietario(nombre, cedula, edad, peso);

        System.out.print("Ingrese placa del vehículo de carga: ");
        String placa = sc.nextLine();

        System.out.print("Ingrese marca del vehículo: ");
        String marca = sc.nextLine();

        System.out.print("Ingrese capacidad de carga en Kg: ");
        double capacidad = sc.nextDouble();

        VehiculoCarga vehiculo = new VehiculoCarga(placa, marca, capacidad, propietario);
        vehiculosCarga.add(vehiculo);

        System.out.println("\nPropietario y vehículo de carga registrados con éxito.");
        System.out.println("Propietario: " + propietario);
        System.out.println("Vehículo: " + vehiculo);
    }

    public void calcularPasajeros(Scanner sc) {
        if (vehiculosPasajeros.isEmpty()) {
            System.out.println("No hay vehículos de pasajeros registrados.");
            return;
        }

        int total = 0;
        for (VehiculoPasajero v : vehiculosPasajeros) {
            System.out.print("Ingrese número de pasajeros transportados por el vehículo con placa " + v.getPlaca() + ": ");
            int pasajeros = sc.nextInt();
            v.setNumPasajerosDiarios(pasajeros);
            total += pasajeros;
        }

        System.out.println("\nEl total de pasajeros transportados en el día es: " + total);
    }

    // 1. Propietarios que superan un valor de peso
    public void propietariosPorPeso(double peso) {
        System.out.println("\n=== Propietarios con peso mayor a " + peso + " Kg ===");
        for (VehiculoCarga v : vehiculosCarga) {
            Propietario p = v.getPropietario();
            if (p != null && p.getPeso() > peso) {
                System.out.println(p);
            }
        }
    }

    // 2. Pasajeros transportados en un vehículo por placa
    public void pasajerosPorPlaca(String placa) {
        for (VehiculoPasajero v : vehiculosPasajeros) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Vehículo " + placa + " transportó " + v.getNumPasajerosDiarios() + " pasajeros.");
                return;
            }
        }
        System.out.println("No se encontró un vehículo de pasajeros con placa " + placa);
    }

    // 3. Número de propietarios mayores de 40 años
    public void propietariosMayoresDe40() {
        int contador = 0;
        for (VehiculoCarga v : vehiculosCarga) {
            Propietario p = v.getPropietario();
            if (p != null && p.getEdad() > 40) {
                contador++;
            }
        }
        System.out.println("\nNúmero de propietarios mayores de 40 años: " + contador);
    }
}
