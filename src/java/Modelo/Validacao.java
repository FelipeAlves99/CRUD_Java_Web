/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.math.BigDecimal;
import java.util.List;
import javax.faces.convert.BigDecimalConverter;

/**
 *
 * @author falve
 */
public class Validacao {

    public String mensagem;
    public int id;
    public BigDecimal valor;

    public void ValidaCarro(List<String> dadosCarro) {
        this.mensagem = "";
        if (dadosCarro.get(0).length() < 3) 
            this.mensagem += "Fabricante com menos de 3 caracteres \n";
        
        if (dadosCarro.get(1).length() < 3) 
            this.mensagem += "Modelo com menos de 3 caracteres \n";       
        
        if (dadosCarro.get(2).length() > 4)
            this.mensagem += "Ano não pode ser maior que 4 caracteres \n";        
    }
    
    public void ValidarId(String id){
        this.mensagem = "";
        try {
            this.id = Integer.parseInt((id));
        } catch (Exception e) {
            this.mensagem = "Id Inválido";
        }
    }
    
    public void ValidarValor(String valor){
        this.mensagem = "";
        try {
            BigDecimal bigDecimal = new BigDecimal(valor);
            this.valor = bigDecimal;
        } catch (Exception e) {
            this.mensagem = "Valor inválido";
        }
    }
}
