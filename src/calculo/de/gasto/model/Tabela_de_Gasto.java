package calculo.de.gasto.model;

import java.sql.SQLException;
import java.sql.Statement;

import calculo.de.gasto.controller.Excecao;

/**
 *
 * @author Neviton viana
 */
public class Tabela_de_Gasto {

    Excecao excecao = new Excecao();

    private final BancodeDado conexaobanco;

    public Tabela_de_Gasto(BancodeDado pconexaobanco) {
        this.conexaobanco = pconexaobanco;
    }

    public void tabela_gasto() {
        String sql = "CREATE TABLE IF NOT EXISTS tb_de_gasto"
                + "("
                + "id_usuario INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT,"
                + "nome VARCHAR(255),"
                + "valor_gasto FLOAT NULL DEFAULT 0,"
                + "valor_deposito FLOAT NULL DEFAULT 0,"
                + "data TEXT"
                + ");";

        boolean conecto = false;

        try {
            conecto = this.conexaobanco.conectar();
            Statement stmt = this.conexaobanco.criarStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            excecao.Aviso("Banco de dados não desconectado " + e);
        } finally {
            if (conecto) {
                this.conexaobanco.desconectar();
            }
        }
    }
}
