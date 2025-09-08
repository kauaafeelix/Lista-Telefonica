package org.example.DAO;

import org.example.database.Conexao;
import org.example.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void inserirContato(Contato contato) throws SQLException {
        String query = "INSERT INTO contato (nome, telefone, email, observacao) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getObservacao());
            stmt.executeUpdate();
        }
    }
    public List<Contato>listarContatos() throws SQLException{
        List<Contato>contatos = new ArrayList<>();
        String query = "SELECT id, nome, telefone, email, observacao FROM contato";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String observacao = rs.getString("observacao");

                var contato = new Contato(id, nome, telefone, email, observacao);
                contatos.add(contato);
            }
        }
        return contatos;
    }

    public List<Contato> buscarContatoPorNome (String nome) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String query = "SELECT id, nome, telefone, email, observacao FROM contato WHERE nome LIKE ?";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, "%"+nome+"%");

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    int id = rs.getInt("id");
                    String nomeBanco = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String email = rs.getString("email");
                    String observacao = rs.getString("observacao");

                    var contato = new Contato(id, nomeBanco, telefone, email, observacao);
                    contatos.add(contato);
                }
        }
        return contatos;
    }

    public void atualizarContato (String novoNome,String novoTelefone, String novoEmail, String novaObservacao, int id) throws SQLException{
        String query = "UPDATE contato SET nome = ?, telefone = ?, email = ?, observacao = ? WHERE id = ?";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,novoNome);
            stmt.setString(2, novoTelefone);
            stmt.setString(3, novoEmail);
            stmt.setString(4, novaObservacao);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }
}
