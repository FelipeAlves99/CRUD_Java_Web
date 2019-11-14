/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Modelo.Controle;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author falve
 */
@Named(value = "bgrCadastro")
@ManagedBean
@RequestScoped
public class bgrCadastro {

    private String fabricante;
    private String modelo;
    private String ano;
    private String preco;
    private String mensagem;
    
    public bgrCadastro() { }
    
    public String cadastrarCarro(){
        Controle controle = new Controle();
        
        List<String> dadosCarro = new ArrayList();
        dadosCarro.add(fabricante);
        dadosCarro.add(modelo);
        dadosCarro.add(ano);   
        dadosCarro.add(preco);
                
        controle.CadastrarCarro(dadosCarro);
        this.mensagem = controle.mensagem;
        return "/Paginas/RespostaCadastro.xhtml";
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }       
}
