import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Clase Producto con encapsulamiento
class Producto {
    // Atributos privados
    private int id;
    private String nombre;
    private String marca;
    private double precio;
    
    // Constructor por defecto
    public Producto() {
    }
    
    // Constructor con parámetros
    public Producto(int id, String nombre, String marca, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    // Método toString para mostrar información del producto
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Marca: %s | Precio: $%.2f", 
                           id, nombre, marca, precio);
    }
}

// Clase principal
public class GestorProductos {
    
    public static void main(String[] args) {
        // Crear ArrayList para almacenar los productos
        ArrayList<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE REGISTRO DE PRODUCTOS ===\n");
        
        // Variables de control
        boolean continuar = true;
        int contadorId = 1;
        
        // Bucle para registrar productos
        while (continuar) {
            System.out.println("--- Registro de Producto #" + contadorId + " ---");
            
            // Crear nuevo producto
            Producto producto = new Producto();
            producto.setId(contadorId);
            
            // Solicitar nombre del producto
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            producto.setNombre(nombre);
            
            // Solicitar marca del producto
            System.out.print("Ingrese la marca del producto: ");
            String marca = scanner.nextLine();
            producto.setMarca(marca);
            
            // Solicitar precio con validación
            double precio = 0;
            boolean precioValido = false;
            while (!precioValido) {
                try {
                    System.out.print("Ingrese el precio del producto: $");
                    precio = Double.parseDouble(scanner.nextLine());
                    if (precio >= 0) {
                        producto.setPrecio(precio);
                        precioValido = true;
                    } else {
                        System.out.println("Error: El precio debe ser mayor o igual a 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un precio válido");
                }
            }
            
            // Agregar producto al ArrayList
            productos.add(producto);
            contadorId++;
            
            System.out.println("\n¡Producto registrado exitosamente!\n");
            
            // Preguntar si desea continuar
            String respuesta;
            do {
                System.out.print("¿Desea registrar otro producto? (s/n): ");
                respuesta = scanner.nextLine().toLowerCase().trim();
                
                if (respuesta.equals("n") || respuesta.equals("no")) {
                    continuar = false;
                } else if (!respuesta.equals("s") && !respuesta.equals("si") && !respuesta.equals("sí")) {
                    System.out.println("Por favor, ingrese 's' para sí o 'n' para no.");
                }
            } while (!respuesta.equals("s") && !respuesta.equals("si") && !respuesta.equals("sí") 
                     && !respuesta.equals("n") && !respuesta.equals("no"));
            
            System.out.println();
        }
        
        // Mostrar productos como factura usando Iterator
        mostrarFactura(productos);
        
        scanner.close();
    }
    
    /**
     * Método para mostrar los productos en formato de factura usando Iterator
     */
    public static void mostrarFactura(ArrayList<Producto> productos) {
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("                         FACTURA DE VENTA                    ");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("Empresa: TechStore Solutions                                 ");
        System.out.println("RNC: 101-12345-6                                           ");
        System.out.println("Dirección: Av. Principal #123, San Salvador                ");
        System.out.println("Teléfono: (503) 2222-3333                                 ");
        System.out.println("════════════════════════════════════════════════════════════");
        
        // Fecha actual simulada
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date fechaActual = new java.util.Date();
        System.out.println("Fecha: " + sdf.format(fechaActual));
        System.out.println("Factura No: FAC-" + String.format("%06d", (int)(Math.random() * 999999 + 1)));
        System.out.println("Cajero: Sistema Automático");
        System.out.println();
        
        if (productos.isEmpty()) {
            System.out.println("                    No hay productos registrados");
            System.out.println("════════════════════════════════════════════════════════════");
            return;
        }
        
        // Encabezado de la tabla
        System.out.println("CANT  DESCRIPCIÓN                    MARCA           PRECIO");
        System.out.println("------------------------------------------------------------");
        
        // Variables para totales
        double subtotal = 0.0;
        int totalItems = 0;
        
        // Usar Iterator para recorrer los productos
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            
            // Formatear línea de producto
            System.out.printf("%-5d %-30s %-15s $%8.2f\n", 
                1, // Cantidad fija de 1
                producto.getNombre().length() > 30 ? 
                    producto.getNombre().substring(0, 27) + "..." : producto.getNombre(),
                producto.getMarca().length() > 15 ? 
                    producto.getMarca().substring(0, 12) + "..." : producto.getMarca(),
                producto.getPrecio()
            );
            
            subtotal += producto.getPrecio();
            totalItems++;
        }
        
        // Línea separadora
        System.out.println("------------------------------------------------------------");
        
        // Calcular impuestos y total
        double impuesto = subtotal * 0.13; // IVA 13% para El Salvador
        double total = subtotal + impuesto;
        
        // Mostrar totales
        System.out.printf("Subtotal (%d items):                           $%8.2f\n", totalItems, subtotal);
        System.out.printf("IVA (13%%):                                     $%8.2f\n", impuesto);
        System.out.println("------------------------------------------------------------");
        System.out.printf("TOTAL A PAGAR:                                $%8.2f\n", total);
        System.out.println("════════════════════════════════════════════════════════════");
        
        // Información adicional
        System.out.println("Forma de pago: Efectivo");
        System.out.println("Estado: PAGADO");
        System.out.println();
        System.out.println("              ¡Gracias por su compra!");
        System.out.println("           Conserve este comprobante");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println();
        
        // Resumen estadístico usando Iterator
        mostrarResumenVenta(productos, subtotal, impuesto, total);
    }
    
    /**
     * Método para mostrar resumen de la venta
     */
    public static void mostrarResumenVenta(ArrayList<Producto> productos, double subtotal, double impuesto, double total) {
        System.out.println("=== RESUMEN DE LA VENTA ===");
        
        if (productos.isEmpty()) return;
        
        // Encontrar producto más caro y más barato usando Iterator
        Iterator<Producto> iterator = productos.iterator();
        Producto productoMasCaro = iterator.next();
        Producto productoMasBarato = productoMasCaro;
        
        // Reiniciar iterator
        iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            
            if (producto.getPrecio() > productoMasCaro.getPrecio()) {
                productoMasCaro = producto;
            }
            
            if (producto.getPrecio() < productoMasBarato.getPrecio()) {
                productoMasBarato = producto;
            }
        }
        
        double promedio = subtotal / productos.size();
        
        System.out.printf("• Artículos vendidos: %d\n", productos.size());
        System.out.printf("• Precio promedio: $%.2f\n", promedio);
        System.out.printf("• Artículo más caro: %s ($%.2f)\n", productoMasCaro.getNombre(), productoMasCaro.getPrecio());
        System.out.printf("• Artículo más barato: %s ($%.2f)\n", productoMasBarato.getNombre(), productoMasBarato.getPrecio());
        System.out.printf("• Subtotal: $%.2f\n", subtotal);
        System.out.printf("• IVA recaudado: $%.2f\n", impuesto);
        System.out.printf("• Total facturado: $%.2f\n", total);
        System.out.println("════════════════════════════════════════════════════════════");
    }
}