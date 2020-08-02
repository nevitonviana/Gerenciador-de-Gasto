package calculo.de.gasto.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import calculo.de.gasto.model.BancodeDado;
/**
 *
 * @author Neviton viana
 */
public class Calcular {

    Excecao excecao = new Excecao();

    BancodeDado conectarbaca = new BancodeDado();
    ResultSet resultaset = null;
    Statement statment = null;

    public float Soma_Gasto() {
        float soma = 0;
        //buscar 
        conectarbaca.conectar();

        String query = "SELECT * FROM tb_de_gasto;";

        statment = conectarbaca.criarStatement();

        try {
            resultaset = statment.executeQuery(query);
            while (resultaset.next()) {
                Float valor = resultaset.getFloat("valor_gasto");
                soma += valor;
            }
        } catch (SQLException e) {
            excecao.Error("não foi posivel acessa o banco de dado " + e);
        } finally {
            try {
                resultaset.close();
                statment.close();
                conectarbaca.desconectar();
            } catch (SQLException e) {
                excecao.Aviso("não foi posivel acessa o banco de dado " + e);
            }
        }
        return soma;
    }

    public float Soma_saldo() {
        float soma = 0;
        conectarbaca.conectar();

        String query = "SELECT * FROM tb_de_gasto;";
        statment = conectarbaca.criarStatement();
        try {
            resultaset = statment.executeQuery(query);
            while (resultaset.next()) {
                Float valor = resultaset.getFloat("valor_deposito");
                soma = valor;
            }
        } catch (SQLException e) {
            excecao.Aviso("não foi posivel acessa o banco de dado " + e);
        } finally {
            try {
                resultaset.close();
                statment.close();
                conectarbaca.desconectar();
            } catch (SQLException e) {
                excecao.Aviso("não foi posivel acessa o banco de dado " + e);
            }
        }
        return soma;
    }

    public float Calculo_gasto(float gasto) {
        float saldo = Soma_saldo();

        return saldo - gasto;
    }

    public float Calcula_Saldo(float saldo) {
        float saldo1 = Soma_saldo();
        return saldo + saldo1;
    }
}
