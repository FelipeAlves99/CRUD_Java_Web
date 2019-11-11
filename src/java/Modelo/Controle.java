/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAL.CarroDAO;

/**
 *
 * @author falve
 */
public class Controle {
    
    public String mensagem;
    
    public void CadastrarCarro(Carros carro){
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.ValidaCarro(carro);
        if(validacao.mensagem.equals("")){
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.CadastrarCarro(carro);
            this.mensagem = carroDAO.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;
    }
}
