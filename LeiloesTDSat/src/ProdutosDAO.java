/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
            String sql = "INSERT INTO produtos (id, nome, valor, status) VALUES"
                + "(?, ?, ?, ?)";
        try {
            PreparedStatement ment = this.conn.prepareStatement(sql);
            int auto_increment = 0;
            ment.setInt(1, auto_increment);
            ment.setString(2, produto.getNome());
            ment.setInt(3, produto.getValor());
            ment.setString(4, produto.getStatus());
            
            ment.execute();
        } catch (Exception res) {
            System.out.println("Erro ao inserir produto: " + res.getMessage());
        }    
        
       conn = new conectaDAO().connectDB();
    
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "root");
System.out.println("Conectou com o banco de dados!!!!");
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possivel se conectar no banco de dados!");
} catch (ClassNotFoundException ex) {
            System.out.println("Erro: Driver JDBC nao encontrado!.");
        }   
    }
    
    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
                String sql = "SELECT * FROM produtos"; 
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();            

                    while (rs.next()) {
                    ProdutosDTO produto = new ProdutosDTO();           
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));
                    produto.setStatus(rs.getString("status"));
                    
                    listagem.add(produto);
                            
                    }
                  return listagem;
                    
                } catch (Exception e) {
                    return null;
                }
                
            }
    }


