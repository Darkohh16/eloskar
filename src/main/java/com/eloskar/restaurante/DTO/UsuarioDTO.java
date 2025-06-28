package com.eloskar.restaurante.DTO;

import com.eloskar.restaurante.DAO.UsuarioDAO;

import java.util.List;
import java.sql.SQLException;

public class UsuarioDTO {
    private int idUser;
    private String dni;
    private String cel;
    private String nombre;
    private String correo;
    private String password;
    private String rol;
    private String fechaRegistro;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    public boolean insertarU(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setDni(dni);
        dto.setCel(cel);
        dto.setNombre(nombre);
        dto.setCorreo(correo);
        dto.setPassword(password);
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.insertU(dto) > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean actualizarU(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setDni(dni);
        dto.setCel(cel);
        dto.setNombre(nombre);
        dto.setCorreo(correo);
        dto.setRol(rol);
        dto.setIdUser(idUser);
        dto.setPassword(password);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.updateU(dto) > 0;

    }

    public boolean eliminarU(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUser(idUser);
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.deleteU(dto) > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<UsuarioDTO>  cargarDatosUsuarios(String filtro){
        UsuarioDAO dao = new UsuarioDAO();
        if (filtro.isBlank()){
            filtro = "";
        }
        return dao.buscarTodosU(filtro);
    }
}
