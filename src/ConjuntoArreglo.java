import java.util.Iterator;
import java.util.NoSuchElementException;

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

    public T[] elementos;

    public ConjuntoArreglo() {
        this.elementos = (T[]) new Object[0];
    }

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

    public boolean pertenece(T elemento) {
        for(T e: elementos){
            if(e.equals(elemento)){
                return true;
            }
        }

        return false;
    }

    public void agregarElemento(T elemento) {
        if (pertenece(elemento)) {
            return;
        }
        T[] nuevosElem = UtilArreglos.crearArregloGenerico(elementos, elementos.length);
        for (int i = 0; i < elementos.length; i++) {
            nuevosElem[i] = elementos[i];
        }
        nuevosElem[elementos.length] = elemento;
        elementos = nuevosElem;
    }

    public boolean contieneConjunto(Conjunto<T> c) {
        for (T t : c) {
            if (!pertenece(t)) {
                return false;
            }
        }
        return true;
    }

    public Conjunto<T> union(Conjunto<T> c) {
        T[] unionaux = UtilArreglos.crearArregloGenerico(elementos, c.obtenerCardinalidad() + elementos.length);
        int i;
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

    public Conjunto<T> interseccion(Conjunto<T> c) {
        int n = Math.max(c.obtenerCardinalidad(), elementos.length);
        T[] interseccion = UtilArreglos.crearArregloGenerico(elementos, n);
        int m;
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
        return new ConjuntoArreglo<>(inter);
    }

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

    public int obtenerCardinalidad(){
        return elementos.length;
    }

}
