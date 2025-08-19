package co.edu.uniquindio.model;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransporte {
    private String nombre;
    public List listaPropietarios = new ArrayList();
    public List listaPropietariosVehiculosPasajero = new ArrayList();
    public List listaPropietariosVehiculosCarga = new ArrayList();


    public EmpresaTransporte() {}

    public EmpresaTransporte(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List getListaPropietarios() {
        return listaPropietarios;
    }
    public void setListaPropietarios(List listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }
    public List getListaPropietariosVehiculosPasajero() {
        return listaPropietariosVehiculosPasajero;
    }
    public List getListaPropietariosVehiculosCarga() {
        return listaPropietariosVehiculosCarga;
    }

}
