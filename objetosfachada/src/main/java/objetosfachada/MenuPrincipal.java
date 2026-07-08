/* 
 * 
 */
package objetosfachada;

//** @author Julian Daniel Ramirez Garcia

import objetoslogicapersistencia.PersistenciaProductosFachada;
import objetoslogicapersistencia.PersistenciaMovimientosFachada;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;
import objetosdominio.MovimientoEmpacado;
import objetosdominio.MovimientoGranel;
import objetosdominio.Producto;
import objetosdominio.ProductoEmpacado;
import objetosdominio.ProductoGranel;
import objetosdominio.TipoUnidad;
import objetospersistencia.PersistenciaException;

public class MenuPrincipal {
    
    private final PersistenciaMovimientosFachada persistenciaMovimientos;
    private final PersistenciaProductosFachada persistenciaProductos;
    private final Scanner input;
    private final DateTimeFormatter formatoFecha;
    
    public MenuPrincipal() {
        persistenciaMovimientos = new PersistenciaMovimientosFachada();
        persistenciaProductos = new PersistenciaProductosFachada();
        input = new Scanner(System.in);
        formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
    
    //** menu consultar  -------------------------------------------------------
    public void verComprasEmpacado() {
        for (MovimientoEmpacado m : persistenciaMovimientos.verComprasEmpacado()) {
            System.out.println(m.toString());
        }
    }
    
    public void consultarCompraEmpacado() {
        try {
            System.out.println(leerClaveMovimientoEmpacado(TipoOperacion.COMPRAR).toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verVentasEmpacado() {
        for (MovimientoEmpacado m : persistenciaMovimientos.verVentasEmpacado()) {
            System.out.println(m.toString());
        }
    }
    
    public void consultarVentaEmpacado() {
        try {
            System.out.println(leerClaveMovimientoEmpacado(TipoOperacion.VENDER).toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verComprasGranel() {
        for (MovimientoGranel m : persistenciaMovimientos.verComprasGranel()) {
            System.out.println(m.toString());
        }
    }
    
    public void consultarCompraGranel() {
        try {
            System.out.println(leerClaveMovimientoGranel(TipoOperacion.COMPRAR).toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verVentasGranel() {
        for (MovimientoGranel m : persistenciaMovimientos.verVentasGranel()) {
            System.out.println(m.toString());
        }
    }
    
    public void consultarVentaGranel() {
        try {
            System.out.println(leerClaveMovimientoGranel(TipoOperacion.VENDER).toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verCatalogo() {
        for (Producto p : persistenciaProductos.verCatalogo()) {
            System.out.println(p.toString() + "\n");
        }
    }
    
    public void consultarProductoCatalogo() {
        try {
            System.out.println(leerClaveProducto().toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verInventarioEmpacado() {
        for (Producto p : persistenciaProductos.verInventarioEmpacado()) {
            System.out.println(p.toString() + "\n");
        }
    }
    
    public void consultarProductoEmpacado() {
        try {
            System.out.println(leerClaveProductoEmpacado().toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verInventarioGranel() {
        for (Producto p : persistenciaProductos.verInventarioGranel()) {
            System.out.println(p.toString() + "\n");
        }
    }
    
    public void consultarProductoGranel() {
        try {
            System.out.println(leerClaveProductoGranel().toString());
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
    
    //** menu registrar --------------------------------------------------------
    public void nuevaCompraEmpacado() {
        try {
            ProductoEmpacado producto = leerClaveProductoEmpacado();
            ProductoEmpacado productoMovimiento = persistenciaProductos.consultarProductoEmpacado(producto.getClave()).copiar();
            
            int cantidadComprar = leerCantidadEmpacado(TipoOperacion.COMPRAR);

            producto.agregarCantidad(cantidadComprar);
            persistenciaMovimientos.agregarCompraEmpacado(productoMovimiento, cantidadComprar);
            System.out.println("Compra de producto empacado realizada con exito...");
        } catch (IllegalArgumentException | OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevaVentaEmpacado() {
        try {
            ProductoEmpacado producto = leerClaveProductoEmpacado();
            ProductoEmpacado productoMovimiento = persistenciaProductos.consultarProductoEmpacado(producto.getClave()).copiar();
            int cantidadVender = leerCantidadEmpacado(TipoOperacion.VENDER);
            
            producto.restarCantidad(cantidadVender);
            persistenciaMovimientos.agregarVentaEmpacado(productoMovimiento, cantidadVender);
            System.out.println("Venta de producto empacado realizada con exito...");
        } catch (IllegalArgumentException | OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevaCompraGranel() {
        try {
            ProductoGranel producto = leerClaveProductoGranel();
            ProductoGranel productoMovimiento = persistenciaProductos.consultarProductoGranel(producto.getClave()).copiar();
            float cantidadComprar = leerCantidadGranel(TipoOperacion.COMPRAR);
            
            producto.agregarCantidad(cantidadComprar);
            persistenciaMovimientos.agregarCompraGranel(productoMovimiento, cantidadComprar);
            System.out.println("Compra de producto granel realizada con exito...");
        } catch (IllegalArgumentException | OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevaVentaGranel() {
        try {
            ProductoGranel producto = leerClaveProductoGranel();
            ProductoGranel productoMovimiento = persistenciaProductos.consultarProductoGranel(producto.getClave()).copiar();
            float cantidadVender = leerCantidadGranel(TipoOperacion.VENDER);
            
            producto.restarCantidad(cantidadVender);
            persistenciaMovimientos.agregarVentaGranel(productoMovimiento, cantidadVender);
            System.out.println("Venta de producto granel realizada con exito...");
        } catch (IllegalArgumentException | OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevoProductoEmpacado() {
        try {
            String nombre = leerNombreProducto();
            int cantidad  = leerCantidadEmpacado(TipoOperacion.REGISTRAR_PRODUCTO);
            
            persistenciaProductos.agregarProductoEmpacado(nombre, cantidad);
            System.out.println("Producto empacado registrado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevoProductoGranel() {
        try {
            String nombre = leerNombreProducto();
            float cantidad = leerCantidadGranel(TipoOperacion.REGISTRAR_PRODUCTO);
            TipoUnidad unidad = leerUnidadGranel();
            persistenciaProductos.agregarProductoGranel(nombre, unidad, cantidad);
            System.out.println("Producto granel actualizado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //** meotodos actualizar ---------------------------------------------------
    public void actualizarProductoEmpacado() {
        try {
            ProductoEmpacado productoViejo = leerClaveProductoEmpacado();
            productoViejo.actualizar(new ProductoEmpacado(productoViejo.getClave(),leerNombreProducto(), leerCantidadEmpacado(TipoOperacion.REGISTRAR_PRODUCTO)));
            System.out.println(productoViejo.toString());
            System.out.println("Producto empacado actualizado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actualizarProductoGranel() {
        try {
            ProductoGranel productoViejo = leerClaveProductoGranel();
            productoViejo.actualizar(new ProductoGranel(productoViejo.getClave(), leerNombreProducto(), leerUnidadGranel(), leerCantidadGranel(TipoOperacion.REGISTRAR_PRODUCTO)));
            System.out.println(productoViejo.toString());
            System.out.println("Producto granel actualizado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //** metodos eliminar ------------------------------------------------------
    public void eliminarProductoEmpacado() {
        try {
            persistenciaProductos.eliminarProductoEmpacado(leerClaveProductoEmpacado().getClave());
            System.out.println("Producto empacado eliminado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarProductoGranel() {
        try {
            persistenciaProductos.eliminarProductoGranel(leerClaveProductoGranel().getClave());
            System.out.println("Producto granel eliminado con exito...");
        } catch (OperacionCanceladaException | PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //** metodos de apoyo ------------------------------------------------------
    
    private MovimientoEmpacado leerClaveMovimientoEmpacado(TipoOperacion operacion) throws OperacionCanceladaException, PersistenciaException {
        switch (operacion) {
            case COMPRAR -> {
                while (true) {
                    System.out.print("\nClave de compra de producto empacado ([0] Volver): ");
                    try {
                        String clave = input.next();
                        input.nextLine();
                        if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de compra de producto empacado cancelada...");
                        MovimientoEmpacado movimiento = persistenciaMovimientos.obtenerCompraEmpacado(clave);
                        return movimiento;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos ? -> String");
                        input.nextLine();
                    }
                }
            }
            case VENDER -> {
                while (true) {
                    System.out.print("\nClave de venta de producto empacado ([0] Volver): ");
                    try {
                        String clave = input.next();
                        input.nextLine();
                        if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de venta de producto empacado cancelada...");
                        MovimientoEmpacado movimiento = persistenciaMovimientos.obtenerCompraEmpacado(clave);
                        return movimiento;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos ? -> String");
                        input.nextLine();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Tipo de operacion invalido (COMPRA, VENTA, REGISTRO)");
    }
    
    private MovimientoGranel leerClaveMovimientoGranel(TipoOperacion operacion) throws OperacionCanceladaException, PersistenciaException {
        switch (operacion) {
            case COMPRAR -> {
                while (true) {
                    System.out.print("\nClave de compra de producto granel ([0] Volver): ");
                    try {
                        String clave = input.next();
                        input.nextLine();
                        if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de compra de producto granel cancelada...");
                        MovimientoGranel movimiento = persistenciaMovimientos.obtenerCompraGranel(clave);
                        return movimiento;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos ? -> String");
                        input.nextLine();
                    }
                }
            }
            case VENDER -> {
                while (true) {
                    System.out.print("\nClave de venta de producto granel ([0] Volver): ");
                    try {
                        String clave = input.next();
                        input.nextLine();
                        if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de venta de producto granel cancelada...");
                        MovimientoGranel movimiento = persistenciaMovimientos.obtenerCompraGranel(clave);
                        return movimiento;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos ? -> String");
                        input.nextLine();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Tipo de operacion invalido (COMPRA, VENTA, REGISTRO)");
    }
    
    public Producto leerClaveProducto() throws OperacionCanceladaException, PersistenciaException {
        while (true) {
            System.out.print("\nClave de producto del catalogo ([0] Volver): ");
            try {
                String clave = input.next();
                input.nextLine();
                if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de producto del catalogo cancelada...");
                Producto producto = persistenciaProductos.consultarProductoCatalogo(clave);
                return producto;
            } catch (InputMismatchException e) {
                System.out.println("Error de tipos ? -> String");
                input.nextLine();
            }
        }
    }
    
    public ProductoEmpacado leerClaveProductoEmpacado() throws OperacionCanceladaException, PersistenciaException {
        while (true) {
            System.out.print("\nClave de producto empacado del inventario ([0] Volver): ");
            try {
                String clave = input.next();
                input.nextLine();
                if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de producto empacado del inventario cancelada...");
                ProductoEmpacado producto = persistenciaProductos.consultarProductoEmpacado(clave);
                return producto;
            } catch (InputMismatchException e) {
                System.out.println("Error de tipos ? -> String");
                input.nextLine();
            }
        }
    }
    
    public ProductoGranel leerClaveProductoGranel() throws OperacionCanceladaException, PersistenciaException {
        while (true) {
            System.out.print("\nClave de producto granel del inventario ([0] Volver): ");
            try {
                String clave = input.next();
                input.nextLine();
                if (clave.equals("0")) throw new OperacionCanceladaException("Operacion leer clave de producto granel del inventario cancelada...");
                ProductoGranel producto = persistenciaProductos.consultarProductoGranel(clave);
                return producto;
            } catch (InputMismatchException e) {
                System.out.println("Error de tipos ? -> String");
                input.nextLine();
            }
        }
    }
    
    private String leerNombreProducto() throws OperacionCanceladaException {
        while (true) {
            System.out.print("\nNombre del producto empacado ([0] Volver): ");
            String nombre = input.nextLine();
            if (nombre.equals("0")) throw new OperacionCanceladaException("Operacion leer nombre de producto cancelada...");
            return nombre;
        }
    }
    
    private int leerCantidadEmpacado(TipoOperacion operacion) throws OperacionCanceladaException {
        switch (operacion) {
            case COMPRAR -> {
                while (true) {
                    System.out.print("\nCantidad empacada a comprar ([0] Volver): ");
                    try {
                        int cantidad = input.nextInt();
                        input.nextLine();
                        if (cantidad == 0) throw new OperacionCanceladaException("Operacion leer cantidad empacada a comprar cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> int");
                        input.nextLine();
                    }
                }
            }
            case VENDER -> {
                while (true) {
                    System.out.print("\nCantidad empacada a vender ([0] Volver): ");
                    try {
                        int cantidad = input.nextInt();
                        input.nextLine();
                        if (cantidad == 0) throw new OperacionCanceladaException("Operacion leer cantidad empacada a vender cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> int");
                        input.nextLine();
                    }
                }
            }
            case REGISTRAR_PRODUCTO -> {
                while (true) {
                    System.out.print("\nCantidad del producto empacado ([0] Volver): ");
                    try {
                        int cantidad = input.nextInt();
                        input.nextLine();
                        if (cantidad == 0) throw new OperacionCanceladaException("Operacion leer cantidad de producto empcado cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> int");
                        input.nextLine();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Tipo de operacion invalido (COMPRA, VENTA, REGISTRO)");
    }
    
    private float leerCantidadGranel(TipoOperacion operacion) throws OperacionCanceladaException {
        switch (operacion) {
            case COMPRAR -> {
                while (true) {
                    System.out.print("\nCantidad granel a comprar ([0] Volver): ");
                    try {
                        float cantidad = input.nextFloat();
                        input.nextLine();
                        if (cantidad == 0f) throw new OperacionCanceladaException("Operacion leer cantidad granel a comprar cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> float");
                        input.nextLine();
                    }
                }
            }
            case VENDER -> {
                while (true) {
                    System.out.print("\nCantidad granel a vender ([0] Volver): ");
                    try {
                        float cantidad = input.nextFloat();
                        input.nextLine();
                        if (cantidad == 0f) throw new OperacionCanceladaException("Operacion leer cantidad granel a vender cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> float");
                        input.nextLine();
                    }
                }
            }
            case REGISTRAR_PRODUCTO -> {
                while (true) {
                    System.out.print("\nCantidad del producto granel ([0] Volver): ");
                    try {
                        float cantidad = input.nextFloat();
                        input.nextLine();
                        if (cantidad == 0f) throw new OperacionCanceladaException("Operacion leer cantidad de producto granel cancelada...");
                        return cantidad;
                    } catch (InputMismatchException e) {
                        System.out.println("Error de tipos String -> float");
                        input.nextLine();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Tipo de operacion invalido (COMPRA, VENTA, REGISTRO)");
    }
    
    private TipoUnidad leerUnidadGranel() {
        while (true) {
            System.out.println("\nTipo Unidad de producto granel.");
            System.out.println("[1] KG.");
            System.out.println("[2] g");
            System.out.println("[3] L.");
            System.out.print("Seleccione una opcion ([0] Volver): ");
            try {
                int opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 0 -> {
                        throw new OperacionCanceladaException("Operacion leer tipo unidad de producto granel cancelada...");
                    }
                    case 1 -> {
                        return TipoUnidad.KG;
                    }
                    case 2 -> {
                        return TipoUnidad.g;
                    }
                    case 3 -> {
                        return TipoUnidad.L;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error de tipos String -> int");
                input.nextLine();
            } catch (OperacionCanceladaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
