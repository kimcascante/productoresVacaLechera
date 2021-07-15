package Proyecto;

/**
 * Nombre del programa: La vaca lechera
 * Descripcion: Programa que calcule cuánto hay que pagarle a cada productor, cuál fue el productor que
 * más botellas entregó y cuál el que menos botellas entregó. El programa recibe como entrada la
 * cantidad de productores; para cada productor el id del productor y la cantidad de
 * entregas realizada y, para cada entrega, la cantidad de botellas que entregó. El sistema
 * deberá de imprimir el id de cada productor y el monto que hay pagarle a cada uno. 
 * El programa deberá usar rutinas de forma obligatoria.
 * Autor: Kimberly C.
 * Fecha de creacion: 30-07-2020
 * Modificado por: Kimberly C.
 * fecha de modificacion: 30-07-2020
 */
import java.io.*;

public class proyecto {
    public static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream escribir = System.out;

    public static int[] listaProductores;
    public static int[] botellasPorEntrega;
    public static int[] listaEntregas;

    public static double montoPagarBotella = 0;
    public static double montoVendeBotella = 0;
    public static double montoPremio = 0;

    public static void main(String[] args) throws IOException {
        boolean salir = false;

        do {
            mostrarMenu();
            int opcion = leerOpcion();
            salir = ejecutar(opcion);
        } while (!salir);
    }// Fin del Main

    public static void mostrarMenu() {
        System.out.println();
        escribir.println("1. Iniciar el programa.");
        escribir.println("2. Registrar productor.");
        escribir.println("3. Imprimir la cantidad total de botellas que entregó un productor específico.");
        escribir.println("4. Imprimir el productor que más entregas realizó. ");
        escribir.println("5. Imprimir el promedio de entregas de todos los productores.");
        escribir.println("6. Imprimir los productores que realizaron más entregas que el promedio de entregas.");
        escribir.println("7. Realizar el pago a los productores, según el precio de cada botella y si aplica o no el premio por entregas.");
        escribir.println("8. Imprimir el total de dinero que le debe pagar un productor específico");
        escribir.println("9. Imprimir el total que debe pagar a todos los productores.");
        escribir.println("10. Imprimir la ganancia total de la cooperativa.");
        escribir.println("11. Salir");
    } // Fin de la rutina mostrarMenu

    public static int leerOpcion() {
        try {
            escribir.println("Seleccione una opción:");
            int opcion = Integer.parseInt(leer.readLine());
            escribir.println();
            return opcion;
        } catch (Exception iOException) {
            return -1;
        } // Fin del Try and catch
    } // Fin de la rutina leerOpcion

    public static boolean ejecutar(int pOpcion) throws IOException {
        boolean salir = false;
        int cantidadProductores = 0;

        switch (pOpcion) {
            case 1:
                cantidadProductores = cantProductores();
                listaProductores = new int[cantidadProductores];
                listaEntregas = new int[listaProductores.length];
                botellasPorEntrega = new int[listaProductores.length];
                montoPagarBotella = montoPagarBotella();
                montoVendeBotella = montoVendeBotella();
                montoPremio = montoPagarPremio();
                break;
            case 2:
                registrarProductor(listaProductores, listaEntregas, botellasPorEntrega);

                break;
            case 3:

                totalEntregas(listaProductores, botellasPorEntrega);

                break;
            case 4:
                productorMasEntregas(listaEntregas, listaProductores);

                break;

            case 5:
                promedioEntregas(listaProductores, listaEntregas);

                break;

            case 6:
                entregasMayoresPromedio(listaEntregas, listaProductores);

                break;

            case 7:
                calcularPagoProductores(montoPagarBotella, montoPremio, botellasPorEntrega, listaEntregas);
                break;

            case 8:
                calcularTotalPagar(listaProductores, listaEntregas, montoPremio, montoPagarBotella);
                break;

            case 9:
                totalPagarProductores(montoPagarBotella, montoPremio, botellasPorEntrega, listaEntregas);
                break;

            case 10:
                calcularGanancias(montoVendeBotella, botellasPorEntrega, montoPagarBotella);
                break;

            case 11:
                salir = true;
                escribir.println("Saliendo..");
                break;
            default:
                escribir.println("Opción invalida");
                escribir.println();
                break;
        } // Fin del switch
        return salir;
    }// Fin de la rutina ejecutar

    public static int cantProductores() throws IOException {
        escribir.println("Digite la cantidad de productores:");
        int cantidadProductores = Integer.parseInt(leer.readLine());
        return cantidadProductores;
    }// Fin de la rutina cantProductores

    public static double montoVendeBotella() throws IOException {
        escribir.println("Digite el precio que se vende cada botella:");
        double montoVendeBotella = Double.parseDouble(leer.readLine());
        return montoVendeBotella;
    }// Fin de la rutina montoVendeBotella

    public static double montoPagarBotella() throws IOException {
        escribir.println("Digite el precio que se le paga por cada botella:");
        double pagarBotella = Double.parseDouble(leer.readLine());
        return pagarBotella;
    }// Fin del double montoPagarBotella

    public static double montoPagarPremio() throws IOException {
        escribir.println("Digite el premio a dar al productor con mas entregas:");
        double premio = Double.parseDouble(leer.readLine());
        return premio;
    }// Fin de la rutina montoPagarPremio

    public static void registrarProductor(int[] pListaProductores, int[] pListaEntregas, int[] pBotellasPorEntrega)
            throws IOException {
        int sumaBotellas = 0, botellas;

        for (int count = 0; count < pListaProductores.length; count++) {
            escribir.println("Digite el ID del productor " + (count + 1) + " :");
            pListaProductores[count] = Integer.parseInt(leer.readLine());
            escribir.println("Digite la cantidad de entregas realizadas:");
            pListaEntregas[count] = Integer.parseInt(leer.readLine());
            sumaBotellas = 0;

            for (int countJ = 0; countJ < pListaEntregas[count]; countJ++) {
                escribir.println("Digite la cantidad de botellas entregas en la entrega " + (countJ + 1));
                botellas = Integer.parseInt(leer.readLine());

                sumaBotellas = sumaBotellas + botellas;
            } // Fin del for pListaEntregas

            pBotellasPorEntrega[count] = sumaBotellas;

        } // Fin del for pListaProductores
    } // Fin de la rutina registrarProductor

    public static void totalEntregas(int[] pListaProductores, int[] pBotellasPorEntrega) throws IOException {
        int idProductor;
        escribir.println("Digite el ID del productor del que desea ver el total de entregas:");
        idProductor = Integer.parseInt(leer.readLine());

        for (int countk = 0; countk < pListaProductores.length; countk++) {
            if (idProductor == pListaProductores[countk]) {
                escribir.println(
                        "La suma de entregas del productor " + idProductor + " es de: " + pBotellasPorEntrega[countk]);
            } // Fin del IF
        } // Fin del FOR
    }// Fin de la rutuna totalEntregas

    public static void productorMasEntregas(int[] pListaEntregas, int[] pListaProductores) {
        int entregasMayor = 0;
        int idEntregasMayor = 0;

        for (int count = 0; count < pListaEntregas.length; count++) {
            if (pListaEntregas[count] > entregasMayor) {
                entregasMayor = pListaEntregas[count];
                idEntregasMayor = pListaProductores[count];
            } // Fin del if entregasMayor
        } // Fin del for entregasMayor

        escribir.println("El productor que más entregas realizó fue el " + idEntregasMayor + " con " + entregasMayor
                + " entregas.");

    }// Fin de la rutina productorMasEntregas

    public static void promedioEntregas(int[] pListaProductores, int[] pListaEntregas) {
        double promedio = 0;
        int sumaEntregas = 0;

        for (int count = 0; count < pListaEntregas.length; count++) {
            sumaEntregas = sumaEntregas + pListaEntregas[count];
        } // Fin del for sumaEntregas

        promedio = (sumaEntregas / pListaProductores.length);

        escribir.println("El promedio de entregas de todos los productores es de: " + promedio);

    }// Fin de la rutina promedioEntregas

    public static void entregasMayoresPromedio(int[] pListaEntrega, int[] pListaProductores) {
        double promedio = 0;
        int sumaEntregas = 0;

        for (int count = 0; count < listaEntregas.length; count++) {
            sumaEntregas = sumaEntregas + listaEntregas[count];
        } // Fin del for sumaEntregas

        promedio = (sumaEntregas / pListaProductores.length);

        for (int counti = 0; counti < listaEntregas.length; counti++) {

            if (pListaEntrega[counti] > promedio) {
                escribir.println("El productor " + pListaProductores[counti] + " entrego más que el promedio");
            } // Fin del if del productor que mas entregas realizó
        } // Fin del for del productor que mas entregas realizó
    }// Fin de la rutina entregasMayoresPromedio

    public static double calcularPagoProductores(double pMontoPagarBotella, double pMontoPremio,
            int[] pBotellasPorEntrega, int[] pListaEntrega) {
        double promedio = 0;
        int sumaEntregas = 0;
        double montoPagar = 0;

        for (int count = 0; count < pListaEntrega.length; count++) {
            sumaEntregas = sumaEntregas + listaEntregas[count];
        } // Fin del for sumaEntregas

        promedio = (sumaEntregas / listaProductores.length);

        for (int counti = 0; counti < pListaEntrega.length; counti++) {

            if (pListaEntrega[counti] > promedio) {
                montoPagar = (pMontoPagarBotella * pBotellasPorEntrega[counti]) + pMontoPremio;
            } else {
                montoPagar = (pMontoPagarBotella * pBotellasPorEntrega[counti]);
            } // Fin del if montoPagar
        } // Fin del for montoPagar

        return montoPagar;
    }// Fin de la rutina calcularPagoProductores

    public static void calcularTotalPagar(int[] pListaProductores, int[] pListaEntrega, double pMontoPremio,
            double pMontoPagarBotella) throws IOException {
        int idProductor;
        escribir.println("Digite el ID del productor del que desea ver el total a pagar:");
        idProductor = Integer.parseInt(leer.readLine());

        double promedio = 0;
        int sumaEntregas = 0;
        double montoPagar = 0;

        for (int count = 0; count < pListaEntrega.length; count++) {
            sumaEntregas = sumaEntregas + pListaEntrega[count];
        } // Fin del FOR

        promedio = (sumaEntregas / pListaProductores.length);

        for (int countk = 0; countk < pListaProductores.length; countk++) {
            if (idProductor == pListaProductores[countk]) {
                if (pListaEntrega[countk] > promedio) {
                    montoPagar = (pMontoPagarBotella * botellasPorEntrega[countk]) + pMontoPremio;
                } else {
                    montoPagar = (pMontoPagarBotella * botellasPorEntrega[countk]);

                } // Fin del IF

            } // Fin del IF
        } // Fin del FOR

        escribir.println("El monto a pagar a " + idProductor + " es de: " + montoPagar);

    }// Fin de la rutina calcularTotalPagar

    public static void totalPagarProductores(double pMontoPagarBotella, double pMontoPremio, int[] pBotellasPorEntrega,
            int[] pListaEntrega) {
        double promedio = 0;
        int sumaEntregas = 0;
        double montoPagar = 0;

        for (int count = 0; count < pListaEntrega.length; count++) {
            sumaEntregas = sumaEntregas + pListaEntrega[count];
        } // Fin del for sumaEntregas

        promedio = (sumaEntregas / listaProductores.length);

        for (int counti = 0; counti < pListaEntrega.length; counti++) {
            if (pListaEntrega[counti] > promedio) {
                montoPagar = (pMontoPagarBotella * pBotellasPorEntrega[counti]) + pMontoPremio;
            } else {
                montoPagar = (pMontoPagarBotella * pBotellasPorEntrega[counti]);
            } // Fin del if montoPagar

            escribir.println("El monto a pagar al productor " + listaProductores[counti] + " es de: " + montoPagar);

        } // Fin del for montoPagar
    }// Fin de la rutina totalPagarProductores

    public static void calcularGanancias(double pMontoVendeBotella, int[] pBotellasPorEntrega,
            double pMontoPagarBotella) {
        double ganancias = 0;
        int sumaBotellas = 0;

        for (int count = 0; count < pBotellasPorEntrega.length; count++) {
            sumaBotellas = sumaBotellas + pBotellasPorEntrega[count];
        } // Fin del for sumaBotellas

        ganancias = sumaBotellas * (pMontoVendeBotella - pMontoPagarBotella);

        escribir.println("Las ganancias totales de la cooperativa son de: " + ganancias);
    }// Fin de la rutina calcularGanancias
}// Fin de la clase