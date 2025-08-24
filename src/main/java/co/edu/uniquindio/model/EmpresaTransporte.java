package co.edu.uniquindio.model;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class EmpresaTransporte {

    private final Map<String, Propietario> propietarios = new HashMap<>();
    private final Map<String, VehiculoCarga> vehiculosCarga = new HashMap<>();
    private final Map<String, VehiculoPasajero> vehiculosPasajero = new HashMap<>();

    // CRUD Propietario
    public boolean crearPropietario(Propietario p) {
        if (p == null || p.getCedula() == null || p.getCedula().isBlank()) return false;
        if (propietarios.containsKey(p.getCedula())) return false;
        propietarios.put(p.getCedula(), p);
        return true;
    }

    public Propietario obtenerPropietario(String cedula) {
        return propietarios.get(cedula);
    }

    public boolean actualizarPropietario(Propietario p) {
        if (p == null || p.getCedula() == null) return false;
        if (!propietarios.containsKey(p.getCedula())) return false;
        propietarios.put(p.getCedula(), p);
        return true;
    }

    public boolean eliminarPropietario(String cedula) {
        VehiculoCarga asociado = vehiculosCarga.values().stream()
                .filter(vc -> vc.getPropietario() != null && vc.getPropietario().getCedula().equals(cedula))
                .findFirst().orElse(null);
        if (asociado != null) return false;
        return propietarios.remove(cedula) != null;
    }

    public List<Propietario> listarPropietarios() {
        return new ArrayList<>(propietarios.values());
    }

    // CRUD VehiculoCarga
    public boolean crearVehiculoCarga(VehiculoCarga v) {
        if (v == null || v.getPlaca() == null || v.getPlaca().isBlank()) return false;
        if (vehiculosCarga.containsKey(v.getPlaca())) return false;
        vehiculosCarga.put(v.getPlaca(), v);
        return true;
    }

    public VehiculoCarga obtenerVehiculoCarga(String placa) {
        return vehiculosCarga.get(placa);
    }

    public boolean actualizarVehiculoCarga(VehiculoCarga v) {
        if (v == null || v.getPlaca() == null) return false;
        if (!vehiculosCarga.containsKey(v.getPlaca())) return false;
        vehiculosCarga.put(v.getPlaca(), v);
        return true;
    }

    public boolean eliminarVehiculoCarga(String placa) {
        return vehiculosCarga.remove(placa) != null;
    }

    public List<VehiculoCarga> listarVehiculosCarga() {
        return new ArrayList<>(vehiculosCarga.values());
    }

    // CRUD VehiculoPasajero
    public boolean crearVehiculoPasajero(VehiculoPasajero v) {
        if (v == null || v.getPlaca() == null || v.getPlaca().isBlank()) return false;
        if (vehiculosPasajero.containsKey(v.getPlaca())) return false;
        vehiculosPasajero.put(v.getPlaca(), v);
        return true;
    }

    public VehiculoPasajero obtenerVehiculoPasajero(String placa) {
        return vehiculosPasajero.get(placa);
    }

    public boolean actualizarVehiculoPasajero(VehiculoPasajero v) {
        if (v == null || v.getPlaca() == null) return false;
        if (!vehiculosPasajero.containsKey(v.getPlaca())) return false;
        vehiculosPasajero.put(v.getPlaca(), v);
        return true;
    }

    public boolean eliminarVehiculoPasajero(String placa) {
        return vehiculosPasajero.remove(placa) != null;
    }

    public List<VehiculoPasajero> listarVehiculosPasajero() {
        return new ArrayList<>(vehiculosPasajero.values());
    }

    // 1) Propietarios que superan un peso
    public List<Propietario> propietariosQueSuperanPeso(double peso) {
        List<Propietario> out = new ArrayList<>();
        for (Propietario p : propietarios.values()) {
            if (p.getPeso() > peso) out.add(p);
        }
        return out;
    }

    // 2) Pasajeros por placa de vehículo de pasajeros
    public Integer pasajerosPorPlaca(String placa) {
        VehiculoPasajero v = vehiculosPasajero.get(placa);
        return (v != null) ? v.getNumPasajerosDiarios() : null;
    }

    // 3) Número de propietarios mayores de 40
    public long numeroPropietariosMayoresDe40() {
        return propietarios.values().stream().filter(p -> p.getEdad() > 40).count();
    }

    // Ejercicio 3: total de pasajeros en un día (suma de todos los vehículos de pasajeros)
    public int totalPasajerosDia() {
        return vehiculosPasajero.values().stream().mapToInt(VehiculoPasajero::getNumPasajerosDiarios).sum();
    }

    // Setear pasajeros a un vehículo por placa (apoyo)
    public boolean registrarPasajerosPorPlaca(String placa, int pasajeros) {
        VehiculoPasajero v = vehiculosPasajero.get(placa);
        if (v == null) return false;
        v.setNumPasajerosDiarios(pasajeros);
        return true;
    }

    // Auxiliares para saber si existen datos
    public boolean hayVehiculosPasajeros() { return !vehiculosPasajero.isEmpty(); }
    public boolean hayVehiculosCarga() { return !vehiculosCarga.isEmpty(); }
    public boolean hayPropietarios() { return !propietarios.isEmpty(); }
}
