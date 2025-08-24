package co.edu.uniquindio.factory;

import co.edu.uniquindio.model.EmpresaTransporte;
import co.edu.uniquindio.model.Propietario;
import co.edu.uniquindio.model.VehiculoCarga;
import co.edu.uniquindio.model.VehiculoPasajero;

import java.util.List;
import java.util.Scanner;

public class ModelFactory {

    private static ModelFactory instance;
    private final EmpresaTransporte empresa;

    private ModelFactory() {
        this.empresa = new EmpresaTransporte();
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public EmpresaTransporte getEmpresa() {
        return empresa;
    }
    // Ejercicio 1: inicializar datos de prueba

    public void inicializarDatosDePrueba() {
        // Propietarios
        empresa.crearPropietario(new Propietario("111", "Carlos", 45, 82.0));
        empresa.crearPropietario(new Propietario("222", "Ana", 33, 64.5));
        empresa.crearPropietario(new Propietario("333", "Luis", 52, 91.2));
        empresa.crearPropietario(new Propietario("444", "María", 28, 70.0));

        // Vehículos de pasajeros
        empresa.crearVehiculoPasajero(new VehiculoPasajero("AAA111", "Chevrolet", 40));
        empresa.crearVehiculoPasajero(new VehiculoPasajero("BBB222", "Nissan", 30));
        empresa.crearVehiculoPasajero(new VehiculoPasajero("CCC333", "Toyota", 25));

        // Vehículos de carga asociados a propietarios
        empresa.crearVehiculoCarga(new VehiculoCarga("DDD444", "Volvo", 10000,
                empresa.obtenerPropietario("111")));
        empresa.crearVehiculoCarga(new VehiculoCarga("EEE555", "Mercedes", 12000,
                empresa.obtenerPropietario("222")));
        empresa.crearVehiculoCarga(new VehiculoCarga("FFF666", "Scania", 15000,
                empresa.obtenerPropietario("333")));

        System.out.println("Datos de prueba inicializados.");
    }

    // Ejercicio 2: Crear Propietario y Vehículo de Carga (consola)
    public void crearPropietarioYVehiculoCargaPorConsola(Scanner sc) {
        System.out.println("\n=== Crear Propietario y su Vehículo de Carga ===");

        System.out.print("Cédula: ");
        String cedula = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(sc.nextLine());

        Propietario p = new Propietario(cedula, nombre, edad, peso);
        if (!empresa.crearPropietario(p)) {
            System.out.println("⚠ No se pudo crear el propietario (ya existe o datos inválidos).");
            return;
        }

        System.out.print("Placa vehículo de carga: ");
        String placa = sc.nextLine();

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Capacidad de carga (kg): ");
        double capacidad = Double.parseDouble(sc.nextLine());

        VehiculoCarga vc = new VehiculoCarga(placa, marca, capacidad, p);
        if (!empresa.crearVehiculoCarga(vc)) {
            System.out.println("No se pudo crear el vehículo de carga (placa duplicada o datos inválidos).");
            return;
        }

        System.out.println("Propietario y vehículo de carga creados correctamente.");
        System.out.println(p);
        System.out.println(vc);
    }

    // Ejercicio 3: Total pasajeros por día (con captura por placa)
    public void capturarPasajerosDelDiaPorConsola(Scanner sc) {
        if (!empresa.hayVehiculosPasajeros()) {
            System.out.println("No hay vehículos de pasajeros registrados.");
            return;
        }
        System.out.println("\n=== Captura de pasajeros por vehículo (hoy) ===");
        for (VehiculoPasajero v : empresa.listarVehiculosPasajero()) {
            System.out.print("Pasajeros hoy en placa " + v.getPlaca() + ": ");
            int pasajeros = Integer.parseInt(sc.nextLine());
            empresa.registrarPasajerosPorPlaca(v.getPlaca(), pasajeros);
        }
        System.out.println("Captura finalizada. Total del día: " + empresa.totalPasajerosDia());
    }

    // ==========================
    // Funcionalidades adicionales pedidas
    // ==========================
    public void mostrarPropietariosQueSuperanPeso(double peso) {
        List<Propietario> lista = empresa.propietariosQueSuperanPeso(peso);
        System.out.println("\n=== Propietarios con peso > " + peso + " kg ===");
        if (lista.isEmpty()) {
            System.out.println("No hay propietarios que superen ese peso.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    public void mostrarPasajerosPorPlaca(String placa) {
        Integer cantidad = empresa.pasajerosPorPlaca(placa);
        if (cantidad == null) {
            System.out.println("⚠ No existe un vehículo de pasajeros con placa " + placa);
        } else {
            System.out.println("Vehículo " + placa + " movilizó " + cantidad + " pasajeros hoy.");
        }
    }

    public void mostrarNumeroPropietariosMayoresDe40() {
        long n = empresa.numeroPropietariosMayoresDe40();
        System.out.println("Número de propietarios > 40 años: " + n);
    }

    // ==========================
    // CRUD expuesto (por si quieres usar desde Main)
    // ==========================
    public EmpresaTransporte crud() { return empresa; }
}
