/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAL.CarroDAO;
import java.util.List;

/**
 *
 * @author falve
 */
public class Controle {
    
    public String mensagem;
    
    public void CadastrarCarro(List<String> dadosCarro){
        
        this.mensagem = "";
        
        Carros carro = new Carros();
        Validacao validacao = new Validacao();
        
        validacao.ValidaCarro(dadosCarro);
        validacao.ValidarValor(dadosCarro.get(3));
        
        if(validacao.mensagem.equals("")){
            
            carro.setId(0);
            carro.setFabricante(dadosCarro.get(0));
            carro.setModelo(dadosCarro.get(1));
            carro.setAno(dadosCarro.get(2));
            carro.setValor(validacao.valor);
            
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.CadastrarCarro(carro);
            this.mensagem = carroDAO.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;
    }
}
