import conversiones.conversionesMonedas.ConvertirMoneda;
import conversiones.conversionesMonedas.Procesos;

import javax.swing.*;

public class ConversorDeDistancia {

    public void datos_a_obtener() {

        //ingresar tipo moneda
        Object [] longitud_opciones={"Km a Millas","Millas a Km","Km a Yardas","Yardas a Km","Km a metros"};
        Object longitud_opciones_elegir = JOptionPane.showInputDialog(null,"Seleccione un opción de conversión", "Menu",JOptionPane.QUESTION_MESSAGE,null,longitud_opciones, longitud_opciones[0]);
        String longitud_opcion_elegida= (String)longitud_opciones_elegir;

        if (longitud_opcion_elegida==null) {
            System.out.println("Terminar");
            Object opciones_mensaje= JOptionPane.showConfirmDialog(null, "¿Desea continuar?","Selecciona una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            int mensaje_elegido= (Integer) opciones_mensaje;

            if (mensaje_elegido==0) {
                //desea continuar
                Procesos intentar_de_nuevo= new Procesos();
                intentar_de_nuevo.inicio();
            }

            else if (mensaje_elegido==1) {
                //desea salirce
                JOptionPane.showMessageDialog(null, "Programa Finalizado","Gracias ",JOptionPane.INFORMATION_MESSAGE);

            };
            // por si escoge no seguir se sale del programa
            System.exit(0);
        }


        try {
            //ingresar valor
            String cantidad_opcion=JOptionPane.showInputDialog("Ingrese la cantidad que desea convertir \n Opción escogida : "+ longitud_opcion_elegida);
            double opcion_elegida_cantidadlongitud=Double.parseDouble (cantidad_opcion);

            //operción conversión
            ConversorDeDistancia operacion= new ConversorDeDistancia();
            operacion.conversion_Resultante(longitud_opcion_elegida,opcion_elegida_cantidadlongitud);
        }// por si se  ingresa un valor  no permitido
        catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"Valor no valido","Error",JOptionPane.ERROR_MESSAGE);
            Procesos intentar_de_nuevo= new Procesos();
            intentar_de_nuevo.inicio();
        }

    }

    public void conversion_Resultante(String Conversion,double cantidadLongitud) {
        double resultado_conversion=0;

        switch (Conversion) {
            case "Km a Millas":
                resultado_conversion=cantidadLongitud/1.609;
                break;

            case "Millas a Km":
                resultado_conversion=cantidadLongitud*1.609;
                break;

            case "Km a Yardas":
                resultado_conversion=cantidadLongitud*1.094;
                break;

            case "Yardas a Km":
                resultado_conversion=cantidadLongitud/1.094;
                break;

            case "Km a metros":
                int valordolar_cop= 3905;
                resultado_conversion=cantidadLongitud*1000;
                break;
            default:
                break;
        }


        //mostrar conversión
        JOptionPane.showMessageDialog(null,"El valor de la conversión  "+ Conversion+ " es : "+ resultado_conversion);

        //mensaje final
        System.out.println("Terminar");
        Object mensaje_opcion= JOptionPane.showConfirmDialog(null, "¿Desea continuar?","Selecciona una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        int mensaje= (Integer) mensaje_opcion;

        if (mensaje==0) {
            //desea continuar
            Procesos intentar_de_nuevo= new Procesos();
            intentar_de_nuevo.inicio();
        }

        else if (mensaje==1) {
            //desea salirce
            JOptionPane.showMessageDialog(null, "Programa Finalizado","Gracias ",JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
