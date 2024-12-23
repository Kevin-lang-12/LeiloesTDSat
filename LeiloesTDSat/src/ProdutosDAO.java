
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    
    private conectaDAO conecta;
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    private Object comboPooledDataSource;

    public ProdutosDAO() {
    this.conecta = new conectaDAO();
    this.conn = this.conecta.connectDB();
    }
    
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

    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

