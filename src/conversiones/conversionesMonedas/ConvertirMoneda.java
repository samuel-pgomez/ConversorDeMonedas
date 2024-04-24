package conversiones.conversionesMonedas;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertirMoneda {

    public void recoger_datos() {
        //ingresar tipo moneda
        Object [] divisa_opciones={"De Pesos(MX) a Dolar","De Pesos (COP) a Dólar","De Pesos (COP) a Euro","De pesos (COP) a pesos (MX)","De Dolar a Pesos(COP)"};
        Object opciones_divisa_elegir = JOptionPane.showInputDialog(null,"Seleccione un opción de conversión", "Menu",JOptionPane.QUESTION_MESSAGE,null,divisa_opciones, divisa_opciones[0]);
        System.out.println(opciones_divisa_elegir);
        String opcionMoneda_elegida= (String)opciones_divisa_elegir;

        // por si no elige ninguna opcion de conversion
        if (opciones_divisa_elegir==null) {
            System.out.println("terminar");
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
            // por si escoge no seguir se sale del programa
            System.exit(0);
        }


        try {
            //ingresar valor monetario
            String opcion_cantidad_dinero=JOptionPane.showInputDialog("Ingrese la cantidad de dinero que desea convertir \n Opción escogida: "+ opcionMoneda_elegida);

            //operción conversión
            ConvertirMoneda operacion= new ConvertirMoneda();
            operacion.monedaConvertida(opcionMoneda_elegida, opcion_cantidad_dinero);
        }
        // por si se  ingresa un valor  no permitido
        catch(NumberFormatException | NullPointerException exception) {
            JOptionPane.showMessageDialog(null,"Valor no valido","Error",JOptionPane.ERROR_MESSAGE);
            Procesos intentar_de_nuevo= new Procesos();
            intentar_de_nuevo.inicio();
        }
    }

    public void monedaConvertida(String tipoConvercion, String cantidaDinero) {

        String info_api[];//guarda los daots que se envia a  la API
        info_api= new String[4];

        String pasar_De="",pasar_a = "";

        switch (tipoConvercion) {
            case "De Pesos(MX) a Dolar":
                pasar_De="MXN";
                pasar_a="USD";
                break;

            case "De Pesos (COP) a Dólar":
                pasar_De="COP";
                pasar_a="USD";
                break;

            case "De Pesos (COP) a Euro":
                pasar_De="COP";
                pasar_a="EUR";
                break;

            case "De pesos (COP) a pesos (MX)":
                pasar_De="COP";
                pasar_a="MXN";
                break;

            case "De Dolar a Pesos(COP)":
                pasar_De="USD";
                pasar_a="COP";
                break;

            case "De Dolar a Euro":
                pasar_De="USD";
                pasar_a="EUR";
                break;

            case "De Pesos(MX) a Pesos(COP)":
                pasar_De="MXN";
                pasar_a="COP";
                break;

            case "De Euro a Pesos(COP)" :
                pasar_De="EUR";
                pasar_a="COP";
                break;

            case "Otros valores":
                ConvertirMoneda operacion= new ConvertirMoneda();


            default:
                break;
            //"De Pesos(MX) a Pesos(COP)",
        }



        // obtener la fecha actual
        LocalDate actualDate =LocalDate.now();
        // pasar de tipo LocalDate a String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String fecha_formateada = actualDate.format(formatter);


        // guardar los datos en el array
        info_api[0]= fecha_formateada;
        info_api[1]=cantidaDinero;
        info_api[2]=pasar_De;
        info_api[3]=pasar_a;

        //pasar datos a la Api
        ConsultaApi solicitud = new ConsultaApi();
        double valor_convertido=solicitud.get(info_api[0],info_api[1],info_api[2],info_api[3]);


        //formato decimales del  resultado
        double valor_formateado=0;

        valor_formateado=Math.round((valor_convertido*100.0)/100.0);


        //mostrar conversión
        JOptionPane.showMessageDialog(null,"El valor de la conversión  "+ tipoConvercion+ " es : $"+ valor_formateado);

        //mensaje final

        System.out.println("terminar");
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