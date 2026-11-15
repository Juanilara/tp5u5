
// Alumno: Juan Ignacio Lara
// Materia: Programación III - UTN A Distancia
// Trabajo Práctico 5: Método hashCode() y colecciones basadas en hash (HashSet)

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Producto {
    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Sobrescribimos equals basado en el código del producto
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    // Sobrescribimos hashCode de forma coherente con equals (usando el mismo atributo: código)
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Producto [Código: " + codigo + ", Nombre: " + nombre + ", Precio: $" + precio + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        // Creamos un HashSet para almacenar productos únicos automáticamente
        Set<Producto> inventario = new HashSet<>();

        // Instanciamos productos (algunos con el mismo código para probar duplicados)
        Producto p1 = new Producto("P001", "Monitor Gamer 144Hz", 280000.0);
        Producto p2 = new Producto("P002", "Auriculares Inalámbricos", 65000.0);
        Producto p3 = new Producto("P001", "Monitor Gamer Versión Antigua (Duplicado)", 250000.0); // Mismo código P001
        Producto p4 = new Producto("P003", "Mousepad XL", 15000.0);
        Producto p5 = new Producto("P002", "Auriculares con Cable (Duplicado)", 60000.0); // Mismo código P002

        // Agregamos los productos al HashSet
        inventario.add(p1);
        inventario.add(p2);
        inventario.add(p3); // HashSet detectará la colisión de igualdad gracias a equals() y hashCode() y lo descartará
        inventario.add(p4);
        inventario.add(p5); // También será descartado

        // Mostramos el contenido final del HashSet
        System.out.println("--- Inventario Único (HashSet) ---");
        for (Producto p : inventario) {
            System.out.println(p);
        }
        
        System.out.println("\nTotal de productos únicos en el conjunto: " + inventario.size());
    }
}
