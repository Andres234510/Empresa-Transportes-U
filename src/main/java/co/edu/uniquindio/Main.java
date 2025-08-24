package co.edu.uniquindio;

import co.edu.uniquindio.factory.ModelFactory;
import co.edu.uniquindio.model.Propietario;
import co.edu.uniquindio.model.VehiculoCarga;
import co.edu.uniquindio.model.VehiculoPasajero;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        mainTransporte();
    }

    // Requisito: método mainTransporte que orquesta el flujo
    public static void mainTransporte() {
        Scanner sc = new Scanner(System.in);
        ModelFactory mf = ModelFactory.getInstance();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== EMPRESA DE TRANSPORTE ===");
            System.out.println("1. Inicializar datos de prueba");
            System.out.println("2. Crear Propietario y Vehículo de Carga (consola)");
            System.out.println("3. Capturar pasajeros del día (por vehículo) y total del día");
            System.out.println("4. Propietarios que superan un peso (mostrar)");
            System.out.println("5. Pasajeros transportados por placa (mostrar)");
            System.out.println("6. Número de propietarios > 40 años (mostrar)");
            System.out.println("7. CRUD: Propietario");
            System.out.println("8. CRUD: Vehículo de Pasajeros");
            System.out.println("9. CRUD: Vehículo de Carga");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    mf.inicializarDatosDePrueba();
                    break;

                case "2":
                    mf.crearPropietarioYVehiculoCargaPorConsola(sc);
                    break;

                case "3":
                    mf.capturarPasajerosDelDiaPorConsola(sc);
                    break;

                case "4":
                    System.out.print("Peso (kg): ");
                    double peso = Double.parseDouble(sc.nextLine());
                    mf.mostrarPropietariosQueSuperanPeso(peso);
                    break;

                case "5":
                    System.out.print("Placa vehículo de pasajeros: ");
                    String placa = sc.nextLine();
                    mf.mostrarPasajerosPorPlaca(placa);
                    break;

                case "6":
                    mf.mostrarNumeroPropietariosMayoresDe40();
                    break;

                case "7":
                    menuCrudPropietario(sc, mf);
                    break;

                case "8":
                    menuCrudVehiculoPasajero(sc, mf);
                    break;

                case "9":
                    menuCrudVehiculoCarga(sc, mf);
                    break;

                case "0":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }

    // Menús CRUD
    private static void menuCrudPropietario(Scanner sc, ModelFactory mf) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CRUD Propietario ---");
            System.out.println("1. Crear");
            System.out.println("2. Obtener por cédula");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar todos");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.print("Cédula: ");
                    String cedula = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = Integer.parseInt(sc.nextLine());
                    System.out.print("Peso (kg): ");
                    double peso = Double.parseDouble(sc.nextLine());
                    boolean creado = mf.crud().crearPropietario(new Propietario(cedula, nombre, edad, peso));
                    System.out.println(creado ? "Creado" : "No se pudo crear");
                    break;

                case "2":
                    System.out.print("Cédula: ");
                    Propietario p = mf.crud().obtenerPropietario(sc.nextLine());
                    System.out.println(p != null ? p : "No encontrado");
                    break;

                case "3":
                    System.out.print("Cédula (existente): ");
                    String c = sc.nextLine();
                    Propietario actual = mf.crud().obtenerPropietario(c);
                    if (actual == null) {
                        System.out.println("No existe ese propietario.");
                        break;
                    }
                    System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
                    String nNombre = sc.nextLine();
                    if (!nNombre.isBlank()) actual.setNombre(nNombre);

                    System.out.print("Nueva edad (" + actual.getEdad() + "): ");
                    String nEdad = sc.nextLine();
                    if (!nEdad.isBlank()) actual.setEdad(Integer.parseInt(nEdad));

                    System.out.print("Nuevo peso (" + actual.getPeso() + "): ");
                    String nPeso = sc.nextLine();
                    if (!nPeso.isBlank()) actual.setPeso(Double.parseDouble(nPeso));

                    System.out.println(mf.crud().actualizarPropietario(actual) ? " Actualizado" : "No se pudo actualizar");
                    break;

                case "4":
                    System.out.print("Cédula: ");
                    System.out.println(mf.crud().eliminarPropietario(sc.nextLine()) ? "Eliminado" : "No se pudo " +
                            "eliminar (quizá está asociado a un vehículo de carga)");
                    break;

                case "5":
                    mf.crud().listarPropietarios().forEach(System.out::println);
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuCrudVehiculoPasajero(Scanner sc, ModelFactory mf) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CRUD Vehículo Pasajero ---");
            System.out.println("1. Crear");
            System.out.println("2. Obtener por placa");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar todos");
            System.out.println("6. Registrar pasajeros hoy");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Capacidad pasajeros: ");
                    int cap = Integer.parseInt(sc.nextLine());
                    boolean creado = mf.crud().crearVehiculoPasajero(new VehiculoPasajero(placa, marca, cap));
                    System.out.println(creado ? "Creado" : "No se pudo crear");
                    break;

                case "2":
                    System.out.print("Placa: ");
                    VehiculoPasajero vp = mf.crud().obtenerVehiculoPasajero(sc.nextLine());
                    System.out.println(vp != null ? vp : "No encontrado");
                    break;

                case "3":
                    System.out.print("Placa (existente): ");
                    String pl = sc.nextLine();
                    VehiculoPasajero vpa = mf.crud().obtenerVehiculoPasajero(pl);
                    if (vpa == null) { System.out.println("No existe ese vehículo."); break; }
                    System.out.print("Nueva marca (" + vpa.getMarca() + "): ");
                    String nMarca = sc.nextLine();
                    if (!nMarca.isBlank()) vpa.setMarca(nMarca);
                    System.out.print("Nueva capacidad (" + vpa.getCapacidadPasajeros() + "): ");
                    String nCap = sc.nextLine();
                    if (!nCap.isBlank()) vpa.setCapacidadPasajeros(Integer.parseInt(nCap));
                    System.out.println(mf.crud().actualizarVehiculoPasajero(vpa) ? "Actualizado" : "No se pudo actualizar");
                    break;

                case "4":
                    System.out.print("Placa: ");
                    System.out.println(mf.crud().eliminarVehiculoPasajero(sc.nextLine()) ? "Eliminado" : "No se pudo eliminar");
                    break;

                case "5":
                    mf.crud().listarVehiculosPasajero().forEach(System.out::println);
                    break;

                case "6":
                    System.out.print("Placa: ");
                    String placaReg = sc.nextLine();
                    System.out.print("Pasajeros hoy: ");
                    int cant = Integer.parseInt(sc.nextLine());
                    System.out.println(mf.crud().registrarPasajerosPorPlaca(placaReg, cant) ? "Registrado" : "Vehículo no encontrado");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuCrudVehiculoCarga(Scanner sc, ModelFactory mf) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CRUD Vehículo de Carga ---");
            System.out.println("1. Crear");
            System.out.println("2. Obtener por placa");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar todos");
            System.out.println("6. Asociar propietario por cédula");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Capacidad (kg): ");
                    double kg = Double.parseDouble(sc.nextLine());
                    boolean creado = mf.crud().crearVehiculoCarga(new VehiculoCarga(placa, marca, kg));
                    System.out.println(creado ? "Creado" : "No se pudo crear");
                    break;

                case "2":
                    System.out.print("Placa: ");
                    VehiculoCarga vc = mf.crud().obtenerVehiculoCarga(sc.nextLine());
                    System.out.println(vc != null ? vc : "No encontrado");
                    break;

                case "3":
                    System.out.print("Placa (existente): ");
                    String pl = sc.nextLine();
                    VehiculoCarga vca = mf.crud().obtenerVehiculoCarga(pl);
                    if (vca == null) { System.out.println("No existe ese vehículo."); break; }
                    System.out.print("Nueva marca (" + vca.getMarca() + "): ");
                    String nMarca = sc.nextLine();
                    if (!nMarca.isBlank()) vca.setMarca(nMarca);
                    System.out.print("Nueva capacidad (" + vca.getCapacidadCargaKg() + "): ");
                    String nKg = sc.nextLine();
                    if (!nKg.isBlank()) vca.setCapacidadCargaKg(Double.parseDouble(nKg));
                    System.out.println(mf.crud().actualizarVehiculoCarga(vca) ? "Actualizado" : "No se pudo actualizar");
                    break;

                case "4":
                    System.out.print("Placa: ");
                    System.out.println(mf.crud().eliminarVehiculoCarga(sc.nextLine()) ? "Eliminado" : "No se pudo eliminar");
                    break;

                case "5":
                    mf.crud().listarVehiculosCarga().forEach(System.out::println);
                    break;

                case "6":
                    System.out.print("Placa: ");
                    String placaAsoc = sc.nextLine();
                    VehiculoCarga vAsoc = mf.crud().obtenerVehiculoCarga(placaAsoc);
                    if (vAsoc == null) { System.out.println("No existe ese vehículo."); break; }
                    System.out.print("Cédula propietario: ");
                    String ced = sc.nextLine();
                    Propietario prop = mf.crud().obtenerPropietario(ced);
                    if (prop == null) { System.out.println("No existe ese propietario."); break; }
                    vAsoc.setPropietario(prop);
                    System.out.println(mf.crud().actualizarVehiculoCarga(vAsoc) ? "Asociado" : "No se pudo asociar");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
