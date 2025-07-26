/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.TipoIdentificacion;
import utiles.Utilidades;

/**
 *
 * @author franz
 */
public class ControllerLogin {

    Utilidades u = new Utilidades();
    private String namefile = "Cuentas";

    public boolean registrarCu(String cedula, String correo, String contrasena, String telefono) {
        String data = cedula + "\t" + correo + "\t" + contrasena + "\t" + telefono + "\t" + true + "\n";
        try {
            u.save(data, namefile);
            return true;
        } catch (Exception e) {
            System.out.println("e");
            return false;
        }
    }

    public String[][] listar() {
        try {
            return u.listAll(namefile);
        } catch (Exception e) {
            return null;
        }
    }
//valida que la contrseña coincida
    public boolean CoincidirContraseña(String contra, String contrase) {
        boolean usuar = false;
        if (contra.equals(contrase)) {
            usuar = true;
        }
        return usuar;
    }
//verifica que el correo no exista
    public boolean verifiCorreo(String correo) {
        boolean usuar = false;
        String[][] contrasenas = listar();
        if (contrasenas != null) {
            for (int i = 0; i < contrasenas.length; i++) {
                if (contrasenas[i][1].equals(correo)) {
                    usuar = true;
                    break;
                }
            }
        }
        return usuar;
    }
//verifica que el telefono no exista
    public boolean verificarTelefono(String telefono) {
        boolean usuar = false;
        String[][] contrasenas = listar();
        if (contrasenas != null) {
            for (int i = 0; i < contrasenas.length; i++) {
                if (contrasenas[i][3].equals(telefono)) {
                    usuar = true;
                    break;
                }
            }
        }
        return usuar;
    }
// verifica si el numero ingresado cumple con los digitos minimos
    public boolean verificarTelefonoValido(String telefono) {
        boolean usuar = false;
        if (telefono.length() ==10) {
            System.out.println(telefono.length());
            usuar = true;
        }
        return usuar;
    }
// verifica si el numero de cedula es el correcto dependiendo del digito verificador
    public boolean validaridentificacion(String tipo, String cedula) {
        boolean band = false;
        if (tipo.equals("Pasaporte")) {
            if (cedula.length() < 8) {
                band = false;
            } else {
               band =true;
            }
        } else if (tipo.equals("Cedula")) {
            int sum = 0;
            if (cedula.length() != 10) {
                band = false;
            } else {
                int[] par = new int[cedula.length() / 2];
                int[] impar = new int[cedula.length() / 2];
                int a = 0;
                int b = 1;
                for (int i = 0; i < cedula.length() / 2; i++) {
                    par[i] = Integer.valueOf(String.valueOf(cedula.charAt(a)));
                    a = a + 2;
                    if (i < cedula.length() / 2 - 1) {
                        impar[i] = Integer.valueOf(String.valueOf(cedula.charAt(b)));
                        b = b + 2;
                    }
                }
                for (int i = 0; i < par.length; i++) {
                    par[i] = par[i] * 2;
                    if (par[i] > 9) {
                        par[i] = par[i] - 9;
                    }
                    sum = sum + par[i] + impar[i];
                }
                int apro = ((sum / 10) + 1) * 10;
                if ((apro - sum) == Integer.valueOf(String.valueOf(cedula.charAt(cedula.length() - 1)))) {
                    band =true;
                } else if (sum % 10 == 0 && Integer.valueOf(String.valueOf(cedula.charAt(cedula.length() - 1))) == 0) {
                    band =true;
                } else {
                    band =false;
                }
            }
        }
        return band;
    }
//veerifica que la cedula no exista 
    public boolean verificarCedula(String cedula) {
        boolean usuar = false;
        String[][] contrasenas = listar();
        if (contrasenas != null) {
            for (int i = 0; i < contrasenas.length; i++) {
                if (contrasenas[i][0].equals(cedula)) {
                    usuar = true;
                    break;
                }
            }
        }
        return usuar;
    }
//verifica el correo se usa en el inicio de sesion
    public String[] verificarCorreo(String correo) {
        String[][] data = listar();
        if (data != null) {
            String[] usuar = new String[data[0].length];
            Integer cont = -1;
            for (int i = 0; i < data.length; i++) {
                if (correo.equals(data[i][1])) {
                    cont = i;
                    break;
                }
            }
            if (cont >= 0) {
                for (int i = 0; i < usuar.length; i++) {
                    usuar[i] = data[cont][i];
                }
            } else {
                usuar = null;
            }
            return usuar;
        }
        return null;
    }
// verifica que la contraseña en la posicion del correo sea igual se usa en login
    public String[] verificarcontraseña(String correo, String contrasena) {
        String[] data = verificarCorreo(correo);
        if (data != null) {
            if (data[2].equals(contrasena)) {
                if (Boolean.parseBoolean(data[4])) {
                    return data;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }
//verificador universal de cedula
    public String[] verificarCedula(String cedula,String[][] listar) {
        String[][] data = listar;
        if (data != null) {
            String[] usuar = new String[data[0].length];
            Integer cont = -1;
            for (int i = 0; i < data.length; i++) {
                if (cedula.equals(data[i][0])) {
                    cont = i;
                    break;
                }
            }
            if (cont>= 0){
                for (int i = 0; i < usuar.length; i++) {
                    usuar[i] = data[cont][i];
                }
            }else {
                usuar = null;
            }
            return usuar;
        }
        return null;
    }
//inicializa una administrador predeterminado
    public void CrearAdministrador() {
        if (listar() == null) {
            ARadministrador ar = new ARadministrador();
            if (ar.listar() == null) {
                ar.registrarAdm("1105527327", TipoIdentificacion.Cedula, "Franz Ismael", "Ludeña Arevalo", "0980482193", "ludfranz@unl.edu.ec");
                registrarCu("1105527327", "ludfranz@unl.edu.ec", "123456", "0980482193");
            } else {
                registrarCu("1105527327", "ludfranz@unl.edu.ec", "123456", "0980482193");
            }
        }
    }
}