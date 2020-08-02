package calculo.de.gasto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.File;

import calculo.de.gasto.controller.Excecao;

public class BancodeDado {

    Excecao excecao = new Excecao();

    public Connection conexao;

    public boolean conectar() {
        try {
            //onde a pasta esta sendo criado e o banco de dado
            File criar_pasta = new File("c:\\banco de dado");
            criar_pasta.mkdir();
            String url = "jdbc:sqlite:c:\\banco de dado\\banco_de_dados_do_gerenciador_de_gasto.db";
            this.conexao = DriverManager.getConnection(url);

        } catch (SQLException e) {
            excecao.Error("banco de dado e /ou Pasta \nnão pode ser criada " + e);
            return false;
        }
        return true;
    }

    public boolean desconectar() {
        try {
            if (this.conexao.isClosed() == false) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            excecao.Aviso("Banco de dado não foi desconctado " + e);
        }

        return true;
    }

    public Statement criarStatement() {
        try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }

    protected Connection getConexao() {
        return this.conexao;
    }

    public PreparedStatement criarPreparedSatament(String sql) {
        try {
            return this.conexao.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }
    }

}
