package controllers;

/**
 *
 * @author Usuario iTC
 */
public class Controlador {
    
    public float Calcular_promedio(float Nota1, float Nota2, float Nota3) {
        float Notafinal = (Nota1 + Nota2 + Nota3) / 3;
        return (Notafinal);
    }

    public boolean validacion(float nota) {
        return nota >= 0 && nota <= 10;
    }
    
}

