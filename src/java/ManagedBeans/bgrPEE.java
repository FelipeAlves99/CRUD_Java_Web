/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Modelo.Controle;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author falve
 */
@ManagedBean
@RequestScoped
public class bgrPEE {

    
    private String id;
    private String fabricante;
    private String modelo;
    private String ano;
    private BigDecimal preco;
            
    public bgrPEE() {
        Controle controle = new Controle();
    }
    
}
