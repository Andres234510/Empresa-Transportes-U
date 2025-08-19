package co.edu.uniquindio;

import co.edu.uniquindio.factory.ModelFactory;
import co.edu.uniquindio.model.Propietario;
import co.edu.uniquindio.model.VehiculoCarga;
import co.edu.uniquindio.model.VehiculoPasajero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ModelFactory app = ModelFactory.getInstance();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE TRANSPORTE ===");
            System.out.println("1. Inicializar datos de prueba");
            System.out.println("2. Crear propietario y vehículo de carga");
            System.out.println("3. Calcular total pasajeros transportados en un día");
            System.out.println("4. Mostrar propietarios con peso mayor a un valor");
            System.out.println("5. Mostrar pasajeros por placa");
            System.out.println("6. Mostrar número de propietarios mayores de 40 años");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    app.inicializarDatos();
                    break;
                case 2:
                    app.crearPropietarioYVehiculo(sc);
                    break;
                case 3:
                    app.calcularPasajeros(sc);
                    break;
                case 4:
                    System.out.print("Ingrese el valor de peso: ");
                    double peso = sc.nextDouble();
                    app.propietariosPorPeso(peso);
                    break;
                case 5:
                    System.out.print("Ingrese la placa del vehículo: ");
                    String placa = sc.nextLine();
                    app.pasajerosPorPlaca(placa);
                    break;
                case 6:
                    app.propietariosMayoresDe40();
                    break;
                case 7:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
