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
    
    public Carros PesquisarCarroPorId(String id){
        this.mensagem = "";
        Carros carro = new Carros();
        Validacao validacao = new Validacao();
        validacao.ValidarId(id);
        if(validacao.mensagem.equals("")){
            carro.setId(validacao.id);
            CarroDAO dao = new CarroDAO();
            carro = dao.PesquisarCarroPorId(carro);
            this.mensagem = dao.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;
        
        return carro;
    }
    
    public void ExcluirCarro(String id){
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.ValidarId(id);
        if(validacao.mensagem.equals("")){
            Carros carro = new Carros();
            carro.setId(validacao.id);
            CarroDAO dao = new CarroDAO();
            dao.ExcluirCarro(carro);
            this.mensagem = dao.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;                
    }
    
    public void EditarCarro(List<String> dadosCarro){
        this.mensagem = "";
        Carros carro = new Carros();
        Validacao validacao = new Validacao();
        validacao.ValidarId(dadosCarro.get(0));
        
        if(validacao.mensagem.equals(""))
            carro.setId(validacao.id);
        else
            this.mensagem = validacao.mensagem;
        
        validacao.ValidarValor(dadosCarro.get(4));
        if(validacao.mensagem.equals(""))
            carro.setValor(validacao.valor);
        else
            this.mensagem = validacao.mensagem;
        
        validacao.ValidaCarro(dadosCarro);
        if(validacao.mensagem.equals("")){
            carro.setFabricante(dadosCarro.get(1));
            carro.setModelo(dadosCarro.get(2));
            carro.setAno(dadosCarro.get(3));     
            
            CarroDAO dao = new CarroDAO();
            dao.EditarCarro(carro);
            this.mensagem = dao.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;
    }
    
    public List<Carros> PesquisarCarroPorFabricante(List<String> dadosCarro){
        this.mensagem = "";
        Carros carro = new Carros();
        carro.setFabricante(dadosCarro.get(1));
        CarroDAO dao = new CarroDAO();
        List<Carros> carros = dao.PesquisarCarrosPorFabricante(carro);
        this.mensagem = dao.mensagem;
        return carros;
    }
}
