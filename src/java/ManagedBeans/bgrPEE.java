/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Modelo.Carros;
import Modelo.Controle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author falve
 */
@Named(value="bgrPEE")
@ManagedBean
@RequestScoped
public class bgrPEE {

    
    private String id;
    private String fabricante;
    private String modelo;
    private String ano;
    private String preco;
    private String mensagem;
    private List<Carros> listaCarros;
            
    public bgrPEE() { }
    
    public String pesquisarFabricante(){
        Controle controle = new Controle();
        List<String> dadosCarro = new ArrayList();
        listaCarros = new ArrayList();
        dadosCarro.add("0");
        dadosCarro.add(this.fabricante);
        dadosCarro.add("");
        dadosCarro.add("");
        listaCarros = controle.PesquisarCarroPorFabricante(dadosCarro);
        
        this.mensagem = controle.mensagem;
        if(listaCarros == null){
            this.mensagem = "Não existe nenhum fabricante com esse nome";
            return null;
        }
        else{
            if (listaCarros.size() == 1){
                this.id = listaCarros.get(0).getId().toString();
                this.fabricante = listaCarros.get(0).getFabricante().toString();
                this.modelo = listaCarros.get(0).getModelo().toString();
                this.ano = listaCarros.get(0).getAno().toString();
                this.preco = listaCarros.get(0).getValor().toString();
                return null;
            }
            else
                return "/Paginas/RespostaPEE.xhtml";
        }                
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Carros> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carros> listaCarros) {
        this.listaCarros = listaCarros;
    }
    
}
