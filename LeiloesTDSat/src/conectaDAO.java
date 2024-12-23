
import java.sql.Connection;
import java.sql.DriverManager;



public class conectaDAO {
    
    public Connection connectDB(){
        try {
            Connection conn1 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uc11", // linha de conexao
                    "root", // usuario do mysql
                    "root"// senha do mysql
            );
            return conn1;
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
    
}