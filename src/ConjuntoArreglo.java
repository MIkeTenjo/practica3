import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Clase que representa una estructura de datos {@link Conjunto} hecha con arreglos.
 * La clase puede hacer las mismas acciones que un {@link Conjunto} como uniones o 
 * intersecciones con otros conjuntos, saber si un elemento esta contenido en el 
 * conjunto, etc.
 * La clase extiende (obviamente) de la clase {@link Conjunto}.
 */
public class ConjuntoArreglo<T> extends Conjunto<T>{
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = 0;

            @Override
            public boolean hasNext() {
                return indice < ConjuntoArreglo.this.elementos.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elementos[indice++];
            }
        };
    }

    public T[] elementos; /*Los elementos del conjunto en un arreglo. */

    /**
     * Constructor que inicializa un objeto de tipo {@link ConjuntoArreglo} vacío.
     */
    public ConjuntoArreglo() {
        this.elementos = (T[]) new Object[0];
    }

    /** 
     * Constructor que inicializa un objeto de tipo {@link ConjuntoArreglo}
     * con los elementos pasados como argumentos.
     * @param elementos El arreglo con los elementos a convertir en un {@link ConjuntoArreglo}.
    */
    public ConjuntoArreglo(T[] elementos) {
        if (elementos == null) {
            this.elementos = (T[]) new Object[0];
        } else if (UtilArreglos.tieneDuplicados(elementos)) {
            throw new IllegalArgumentException("El arreglo contiene elementos duplicados.");
        } else if (UtilArreglos.contieneNull(elementos)) {
            throw new IllegalArgumentException("El arreglo contiene elementos nulos.");
        } else {
            this.elementos = UtilArreglos.copiaArregloGenerico(elementos);
        }
    }

    /**
     * Método que nos dice si un elemento de tipo {@code T} pertenece al
     * {@link ConjuntoArreglo}. Si el elemento pertenece al Conjunto devuelve
     * {@code true} de otro modo devuelve {@code false}
     * @param elemento El elemento de tipo {@code T} a vérificar si existe
     * en el Conjunto.
     * @return {@code true} Si el elemento pertenece al Conjunto. {@code false}
     * de otro modo.
     */
    public boolean pertenece(T elemento) {
        for(T e: elementos){
            if(e.equals(elemento)){
                return true;
            }
        }

        return false;
    }

    /**
     * Método que agrega un objeto elemento de tipo {@code T}
     * al {@link ConjuntoArreglo} del mismo tipo (Si no son del mismo
     * tipo ocurré un error).
     * Como un Conjunto no tiene orden entonces el método agrega el
     * elemento en el último espacio del {@code ConjuntoArreglo}.
     * @param elemento El elemento de tipo {@code T} a agregar en el
     * {@link ConjuntoArreglo}
     */
    public void agregarElemento(T elemento) {
        if (pertenece(elemento)) {
            return;
        }
        T[] nuevosElem = UtilArreglos.crearArregloGenerico(elementos, elementos.length + 1);
        for (int i = 0; i < elementos.length; i++) {
            nuevosElem[i] = elementos[i];
        }
        nuevosElem[elementos.length] = elemento;
        elementos = nuevosElem;
    }

    /**
     *Método que nos dice si un {@link Conjunto} está contenido
     en otro. Si el Conjunto está contenido entonces devuelve
     {@code true}, de otra forma devuelve {@code false}.
     @param c El conjunto génerico de tipo {@code T} a saber
     si está contenido.
     @return {@code true} Si el conjunto está contenido. De
     otra forma {@code false}.
     */
    public boolean contieneConjunto(Conjunto<T> c) {
        for (T t : c) {
            if (!pertenece(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que devuelve un objeto de tipo {@link ConjuntoArreglo}
     * que contiene la únión de los dos {@link Conjunto}.
     * El método recibe un Conjunto génerico de tipo {@code T} y hace
     * la operación elemental Unión con el conjunto que llama el método.
     * @param c El Conjunto de tipo  génerico {@code T} con quien
     * hacer la operación Unión.
     * @return Un nuevo {@link ConjuntoArreglo} que contiene la unión
     * de los dos conjuntos.
     */
    public Conjunto<T> union(Conjunto<T> c) {
        T[] unionaux = UtilArreglos.crearArregloGenerico(elementos, c.obtenerCardinalidad() + elementos.length);
        int i = 0;
        for (T t : elementos) {
            unionaux[i] = elementos[i];
            i++;
        }
        for (T tc : c) {
            if (!pertenece(tc)) {
                unionaux[i] = tc;
                i++;
            }            
        }
        T[] union = UtilArreglos.crearArregloGenerico(unionaux, i);
        for (int j = 0; j < i; j++) {
            union[j] = unionaux[j];
        } 
        return new ConjuntoArreglo<>(union);
    }

    /** 
     * Método que devuelve un objeto génerico de tipo {@link ConjuntoArreglo}
     * que contiene la intersección de dos conjuntos.
     * El método recibe un {@link Conjunto} génerico con el
     * cuál hacer la operación elemental intersección con el conjunto
     * que manda a llamar el método.
     * @param c El {@link Conjunto} génerico con el cúal hacer la operación
     * intersección.
     * @return Un objeto de tipo {@link ConjuntoArreglo} que contiene los elementos
     * de la intersección de los dos conjuntos.
    */
    public Conjunto<T> interseccion(Conjunto<T> c) {
        int n = Math.max(c.obtenerCardinalidad(), elementos.length);
        T[] interseccion = UtilArreglos.crearArregloGenerico(elementos, n);
        int m = 0;
        for (T t : c) {
            if (pertenece(t)) {
                interseccion[m] = t;  
                m++;
            }     
        }
        T[] inter = UtilArreglos.crearArregloGenerico(interseccion, m);
        for (int i = 0; i < m; i++) {
            inter[i] = interseccion[i];
        }
        return new ConjuntoArreglo<T>(inter);
    }

    /** 
     * Método que nos dice si dos conjuntos son iguales.
     * Para que dos conjuntos sean iguales deben de tener el
     * mismo tamaño de elementos y los mismos elementos en 
     * cualquier orden. Si los dos conjuntos son iguales el 
     * método regresa {@code true}, de otra forma regresa {@code false}.
     * @param c El {@link Conjunto} a saber si son iguales.
     * @return {@code true} Si los dos conjuntos son iguales.{@code false}
     * de otra manera.
    */
    public boolean iguales(Conjunto<T> c) {
        if (c.obtenerCardinalidad() != elementos.length) {
            return false;
        }

        for (T t : c) {
            if (!pertenece(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que devuelve la cardinalidad (tamaño) del {@link ConjuntoArreglo}.
     * @return La cardinalidad del conjunto.
     */
    public int obtenerCardinalidad(){
        return elementos.length;
    }

}
