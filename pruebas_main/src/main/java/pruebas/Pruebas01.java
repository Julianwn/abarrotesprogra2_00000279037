/*
 * 
 */
package pruebas;

import objetosdominio.*;

//** @author Julian Daniel Ramirez Garcia

public class Pruebas01 {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DEL SISTEMA ===");

        // Crear productos base
        Producto p1 = new Producto("EM001", "Arroz", 'E', "KG");
        Producto p2 = new Producto("GR002", "Azucar", 'G', "KG");

        // Crear productos específicos
        ProductoEmpacado pe = new ProductoEmpacado(p1, 10);
        ProductoGranel pg = new ProductoGranel(p2, 2.5f);

        System.out.println("\n=== PRODUCTOS ===");
        
        System.out.println(pe);
        System.out.println(pg);

        // Crear movimientos
        MovimientoEmpacado me = new MovimientoEmpacado("10/03/2026", false, pe);
        MovimientoGranel mg = new MovimientoGranel("11/03/2026", true, pg);

        System.out.println("\n=== MOVIMIENTOS ===");
        System.out.println(me);
        System.out.println(mg);

        // Procesar movimientos
        me.setProcesado(true);

        System.out.println("\n=== MOVIMIENTO ACTUALIZADO ===");
        System.out.println(me);
    }
}
