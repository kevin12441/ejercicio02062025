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
        
        // Mostrar productos registrados usando Iterator
        System.out.println("=== PRODUCTOS REGISTRADOS ===\n");
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            // Usar Iterator para recorrer la colección
            Iterator<Producto> iterator = productos.iterator();
            int contador = 1;
            
            while (iterator.hasNext()) {
                Producto producto = iterator.next();
                System.out.println("Producto " + contador + ":");
                System.out.println(producto.toString());
                System.out.println("-".repeat(50));
                contador++;
            }
            
            System.out.println("Total de productos registrados: " + productos.size());
        }
        
        scanner.close();
    }
}