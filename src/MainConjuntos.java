public class MainConjuntos {
    public static void main(String[] args) {
        // Prueba con Strings
        String[] frutas = {"Manzana", "Banana", "Naranja"};
        String[] otrasFrutas = {"Banana", "Kiwi"};

        Conjunto<String> conjunto1 = new ConjuntoArreglo<>(frutas);
        Conjunto<String> conjunto2 = new ConjuntoArreglo<>(otrasFrutas);

        System.out.println("Conjunto 1 contiene a 'Banana': " + conjunto1.pertenece("Banana"));
        System.out.println("Conjunto 1 contiene a 'Kiwi': " + conjunto1.pertenece("Kiwi"));

        conjunto1.agregarElemento("Kiwi");
        System.out.println("Después de agregar 'Kiwi', conjunto 1 contiene a 'Kiwi': " + conjunto1.pertenece("Kiwi"));

        System.out.println("Conjunto 1 contiene a Conjunto 2: " + conjunto1.contieneConjunto(conjunto2));

        System.out.println("Unión de conjunto1 y conjunto2:");
        System.out.println(conjunto1.union(conjunto2));

        System.out.println("Intersección de conjunto1 y conjunto2:");
        System.out.println(conjunto1.interseccion(conjunto2));

        System.out.println("¿conjunto1 es igual a conjunto2?: " + conjunto1.iguales(conjunto2));

        // Prueba con enteros
        Integer[] nums1 = {1, 2, 3};
        Integer[] nums2 = {3, 4, 5};

        Conjunto<Integer> intConj1 = new ConjuntoArreglo<>(nums1);
        Conjunto<Integer> intConj2 = new ConjuntoArreglo<>(nums2);

        System.out.println("Unión de enteros:");
        System.out.println(intConj1.union(intConj2));

        System.out.println("Intersección de enteros:");
        System.out.println(intConj1.interseccion(intConj2));

        System.out.println("¿Los conjuntos de enteros son iguales?: " + intConj1.iguales(intConj2));
    }
}
