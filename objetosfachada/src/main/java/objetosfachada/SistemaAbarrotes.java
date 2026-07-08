/* 
 * 
 */
package objetosfachada;

//** @author Julian Daniel Ramirez Garcia

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaAbarrotes {
    
    private static final Scanner input = new Scanner(System.in);
    private static final MenuPrincipal menu = new MenuPrincipal();
    private static int opcion;
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n**** Menu Principal Sistema Abarrotes ****\n");
            System.out.println("[1] Consultar.");
            System.out.println("[2] Registrar.");
            System.out.println("[3] Actualizar.");
            System.out.println("[4] Eliminar.");
            System.out.println("[0] Salir.\n");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Tipo de dato invalido String -> int");
                continue;
            } catch (RuntimeException e) {
                System.out.println("Error de scanner...");
                continue;
            }
            switch (opcion) {
                case 0 -> {
                    System.out.println("Gracias por su preferencia, vuelva pronto!!!");
                    return;
                }
                case 1 -> {
                    mostrarMenuConsulta();
                }
                case 2 -> {
                    mostrarMenuRegistro();
                }
                case 3 -> {
                    mostrarMenuActualizar();
                }
                case 4 -> {
                    mostrarMenuEliminar();
                }
                default -> {
                    System.out.println("\nOpcion fuera de rango (0-4)");
                }
            }
        }
    }
    
    private static void mostrarMenuConsulta() {
        while (true) {
            System.out.println("\n**** Menu Consultar Sistema Abarrotes ****\n");
            System.out.println("[1] Ver todas las compras de producto empacado.");
            System.out.println("[2] Consultar una compra de producto empacado.");
            System.out.println("[3] Ver todas las ventas de producto empacado.");
            System.out.println("[4] Consultar una venta de producto empacado\n");
            
            System.out.println("[5] Ver todas las compras de producto granel.");
            System.out.println("[6] Consultar una compra de producto granel.");
            System.out.println("[7] Ver todas las ventas de producto granel.");
            System.out.println("[8] Consultar una venta de producto granel.\n");
            
            System.out.println("[9] Ver todo el catalogo.");
            System.out.println("[10] Consultar un producto del catalogo.");
            
            System.out.println("[11] Ver todo el inventario empacado.");
            System.out.println("[12] Consultar un producto empacado del inventario.");
            System.out.println("[13] Ver todo el inventario granel.");
            System.out.println("[14] Consultar un producto granel del inventario.");
            
            System.out.println("[0] Volver.\n");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Tipo de dato invalido String -> int");
                input.nextLine();
                continue;
            } catch (RuntimeException e) {
                System.out.println("Error de scanner...");
                input.nextLine();
                continue;
            }
            switch (opcion) {
                case 0 -> {
                    System.out.println("Volviendo...");
                    return;
                }
                case 1 -> {
                    menu.verComprasEmpacado();
                }
                case 2 -> {
                    menu.consultarCompraEmpacado();
                }
                case 3 -> {
                    menu.verVentasEmpacado();
                }
                case 4 -> {
                    menu.consultarVentaEmpacado();
                }
                case 5 -> {
                    menu.verComprasGranel();
                }
                case 6 -> {
                    menu.consultarCompraGranel();
                }
                case 7 -> {
                    menu.verVentasGranel();
                }
                case 8 -> {
                    menu.consultarVentaGranel();
                }
                case 9 -> {
                    menu.verCatalogo();
                }
                case 10 -> {
                    menu.consultarProductoCatalogo();
                }
                case 11 -> {
                    menu.verInventarioEmpacado();
                }
                case 12 -> {
                    menu.consultarProductoEmpacado();
                }
                case 13 -> {
                    menu.verInventarioGranel();
                }
                case 14 -> {
                    menu.consultarProductoGranel();
                }
                default -> {
                    System.out.println("\nOpcion fuera de rango (0-14)");
                    input.nextLine();
                }
            }
        }
    }
    
    private static void mostrarMenuRegistro() {
        while (true) {
            System.out.println("\n**** Menu Registrar Sistema Abarrotes ****\n");
            System.out.println("[1] Nueva compra de producto empacado.");
            System.out.println("[2] Nueva venta de producto empacado.");
            System.out.println("[3] Nueva compra de producto granel.");
            System.out.println("[4] Nueva venta de producto granel\n");
            
            System.out.println("[5] Nuevo producto empacado al inventario.");
            System.out.println("[6] Nuevo producto granel al inventario.");
            
            System.out.println("[0] Volver.\n");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Tipo de dato invalido String -> int");
                input.nextLine();
                continue;
            } catch (RuntimeException e) {
                System.out.println("Error de scanner...");
                input.nextLine();
                continue;
            }
            switch (opcion) {
                case 0 -> {
                    System.out.println("Volviendo...");
                    return;
                }
                case 1 -> {
                    menu.nuevaCompraEmpacado();
                }
                case 2 -> {
                    menu.nuevaVentaEmpacado();
                }
                case 3 -> {
                    menu.nuevaCompraGranel();
                }
                case 4 -> {
                    menu.nuevaVentaGranel();
                }
                case 5 -> {
                    menu.nuevoProductoEmpacado();
                }
                case 6 -> {
                    menu.nuevoProductoGranel();
                }
                default -> {
                    System.out.println("\nOpcion fuera de rango (0-6)");
                    input.nextLine();
                }
            }
        }
    }
    
    private static void mostrarMenuActualizar() {
        while (true) {
            System.out.println("\n**** Menu Actualizar Sistema Abarrotes ****\n");
            System.out.println("[1] Actualizar producto empacado del inventario");
            System.out.println("[2] Actualizar producto granel del inventario");
            
            System.out.println("[0] Volver.\n");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Tipo de dato invalido String -> int");
                input.nextLine();
                continue;
            } catch (RuntimeException e) {
                System.out.println("Error de scanner...");
                input.nextLine();
                continue;
            }
            switch (opcion) {
                case 0 -> {
                    System.out.println("Volviendo...");
                    return;
                }
                case 1 -> {
                    menu.actualizarProductoEmpacado();
                }
                case 2 -> {
                    menu.actualizarProductoGranel();
                }
                default -> {
                    System.out.println("\nOpcion fuera de rango (0-2)");
                    input.nextLine();
                }
            }
        }
    }
    
    private static void mostrarMenuEliminar() {
        while (true) {
            System.out.println("\n**** Menu Eliminar Sistema Abarrotes ****\n");
            System.out.println("[1] Eliminar producto empacado del inventario.");
            System.out.println("[2] Eliminar producto granel del inventario.");
            
            System.out.println("[0] Volver.\n");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Tipo de dato invalido String -> int");
                input.nextLine();
                continue;
            } catch (RuntimeException e) {
                System.out.println("Error de scanner...");
                input.nextLine();
                continue;
            }
            switch (opcion) {
                case 0 -> {
                    System.out.println("Volviendo...");
                    return;
                }
                case 1 -> {
                    menu.eliminarProductoEmpacado();
                }
                case 2 -> {
                    menu.eliminarProductoGranel();
                }
                default -> {
                    System.out.println("\nOpcion fuera de rango (0-2)");
                    input.nextLine();
                }
            }
        }
    }
}
