package conversiones.conversionesMonedas;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsultaApi {

    public double get(String dia, String Cantidad_Dinero,String pasar_De, String pasar_a  ) {
        double resultado_Final=0;

    try {
        //System.out.println("Esperando ejecución");
        URL url = new URL ("https://api.apilayer.com/exchangerates_data/convert?to="+pasar_a+"&from="+pasar_De+"&amount="+Cantidad_Dinero+"&apikey=aef32ee22107e86d433b291d");
        //System.out.print(url);
        HttpURLConnection conexion_api =(HttpURLConnection) url.openConnection() ;
        conexion_api.setRequestMethod("GET");
        conexion_api.setRequestProperty("apikey", "aef32ee22107e86d433b291d");

        if(conexion_api.getResponseCode()==200) {

            //mostrar respuesta
            List contenido= new ArrayList();
            Scanner lectura = new Scanner(url.openStream());

            while (lectura.hasNext()) {
                contenido.add(lectura.nextLine());
            }
            lectura.close();

            String resultado= (String)contenido.get(12); // para obtener el valor
            resultado= resultado.replaceAll("\"result\": ",""); // quita esa palabra y devuelve solo el numero
            resultado_Final = Double.parseDouble(resultado); // pasa el numero a dooble
            System.out.println("El valor final  es: "+resultado_Final);


        }else {
            System.out.println("error de respuesta" +conexion_api.getResponseCode());
        }

        conexion_api.disconnect();
    }

		 catch(Exception ex) {

        JOptionPane.showMessageDialog(null,"Error,Intenta mas tarde","Error",JOptionPane.ERROR_MESSAGE);
        Procesos nuevo_intento= new Procesos();
        nuevo_intento.inicio();
    }

        return resultado_Final;
    }
}
