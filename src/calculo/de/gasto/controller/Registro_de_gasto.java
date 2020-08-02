package calculo.de.gasto.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import calculo.de.gasto.model.BancodeDado;
import calculo.de.gasto.model.Tabela_de_Gasto;


/**
 *
 * @author Neviton viana
 */
public class Registro_de_gasto {

    BancodeDado conectarbaca = new BancodeDado();
    Tabela_de_Gasto tabela_gasto = new Tabela_de_Gasto(conectarbaca);
    Excecao excecao = new Excecao();

    public boolean Inserir_gasto(String nome, float gasto, float deposito, String data) {
        tabela_gasto.tabela_gasto();
        conectarbaca.conectar();

        String sqlInsert = "INSERT INTO tb_de_gasto ("
                + "nome,"
                + "valor_gasto,"
                + "valor_deposito,"
                + "data"
                + ") VALUES(?, ?, ?, ?);";
        PreparedStatement Prepastate = conectarbaca.criarPreparedSatament(sqlInsert);
        try {
            Prepastate.setString(1, nome);
            Prepastate.setFloat(2, gasto);
            Prepastate.setFloat(3, deposito);
            Prepastate.setString(4, data);
            Prepastate.execute();

        } catch (SQLException e) {
            excecao.Error("não posivel salvar os dado no \nbanco de dados " + e);
            return false;
        } finally {
            if (Prepastate != null) {
                try {
                    Prepastate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Registro_de_gasto.class.getName()).log(Level.SEVERE, null, ex);
                    excecao.Error("não posivel salvar os dado no \nbanco de dados " + ex);
                }
                conectarbaca.desconectar();
                return true;
            }
        }
        return false;

    }

    public int exluir_ultimo_item(String ultimo_nome) {
        int linha_deletadas = 0;
        conectarbaca.conectar();
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM tb_de_gasto WHERE nome = ?;";
        preparedStatement = conectarbaca.criarPreparedSatament(query);
        try {
            preparedStatement.setString(1, ultimo_nome);
            linha_deletadas = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            excecao.Error("não posivel excluir os dado no \nbanco de dados "+e);
        } finally {
            try {
                preparedStatement.close();
                conectarbaca.desconectar();
            } catch (SQLException ex) {
                excecao.Aviso("não posivel fecha o banco de dados "+ex);
            }
        }
        return linha_deletadas;
    }
}
