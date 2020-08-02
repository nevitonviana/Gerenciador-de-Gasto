/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculo.de.gasto.controller;

/**
 *
 * @author Nevit
 */
public class Verificador {
    
   public float Verificado(String paramentro){
       
       try {
          float valor = Float.parseFloat(paramentro);
          return valor;
       } catch (NumberFormatException e) {
           return 0;
       }
   }
}
