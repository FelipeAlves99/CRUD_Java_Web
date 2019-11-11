/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Modelo.Carros;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author falve
 */
public class CarroDAO {

    public String mensagem;
    Conexao conexao = new Conexao();
    Session session = Conexao.getSessionFactory().openSession();

    public void CadastrarCarro(Carros carro) {

        this.mensagem = "";

        try {
            session.beginTransaction();
            session.save(carro);
            session.getTransaction().commit();
            session.close();
            this.mensagem = "Cadastro efetuado";
        } catch (HibernateException e) {
            this.mensagem = e.getMessage();
        }
    }

    public Carros PesquisarCarro(Carros carro) {
        this.mensagem = "";
        Query query = session.createQuery("from Carros c where c.id = :id");
        query.setParameter("id", carro.getId());

        if (query.list().size() < 1) {
            this.mensagem = "Não existe este ID";
        } else {
            carro = (Carros) query.list().get(0);
        }

        return carro;
    }

    public void ExcluirCarro(Carros carro) {
        mensagem = "";
        try {
            session.beginTransaction();
            session.delete(carro);
            session.getTransaction().commit();
            session.close();
            mensagem = "Cadastro excluído";
        } catch (HibernateException e) {
            this.mensagem = e.getMessage();
        }
    }

    public void EditarCarro(Carros carro) {
        mensagem = "";
        try {
            session.beginTransaction();
            session.saveOrUpdate(carro);
            session.getTransaction().commit();
            session.close();
            mensagem = "Carro atualizado";
        } catch (HibernateException e) {
            this.mensagem = e.getMessage();
        }
    }

}
