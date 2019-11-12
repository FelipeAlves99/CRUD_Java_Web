/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author falve
 */
public class Validacao {

    public String mensagem;
    public int id;

    public void ValidaCarro(Carros carro) {
        this.mensagem = "";
        if (carro.getFabricante().length() < 3) 
            this.mensagem += "Fabricante com menos de 3 caracteres \n";
        
        if (carro.getModelo().length() < 3) 
            this.mensagem += "Modelo com menos de 3 caracteres \n";       
        
        if (carro.getAno().length() > 4)
            this.mensagem = "Ano não pode ser maior que 4 caracteres \n";
        
        if (carro.getValor().intValue() < 0) 
            this.mensagem = "O valor do carro não pode ser menor que 0";        
    }
    
    public void ValidarId(String id){
        this.mensagem = "";
        try {
            this.id = Integer.parseInt((id));
        } catch (Exception e) {
            this.mensagem = "Id Inválido";
        }
    }
}
