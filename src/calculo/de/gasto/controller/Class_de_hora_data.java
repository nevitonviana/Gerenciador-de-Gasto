package calculo.de.gasto.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Neviton viana
 */
public class Class_de_hora_data {

    public String Data() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);

    }

    public String Hora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);

    }
}
