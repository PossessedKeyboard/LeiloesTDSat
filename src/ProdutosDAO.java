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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class ProdutosDAO {
    
    Connection        conn;
    PreparedStatement prep;
    ResultSet    resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        //int status;
        try {

            prep = conn.prepareStatement("INSERT INTO produtos(nome,valor,status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null,"Sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados! Por favor, verifique valores digitados!");
           // return ex.getErrorCode();
        }
    }
        
        
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement("SELECT * FROM produtos");
        resultset = prep.executeQuery();
        
        while(resultset.next()){
            ProdutosDTO produto = new ProdutosDTO();
            
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }
        return listagem;
    }
    
 public void venderProduto(int idProduto){
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement(
            "UPDATE produtos SET status = ? WHERE id = ?"
        );
        prep.setString(1, "Vendido");
        prep.setInt(2, idProduto);
        
        prep.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
}   

 public ArrayList<ProdutosDTO> listarProdutosVendidos(){
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement(
            "SELECT * FROM produtos WHERE status = ?"
        );
        prep.setString(1, "Vendido");
        
        resultset = prep.executeQuery();
        
        while(resultset.next()){
            ProdutosDTO produto = new ProdutosDTO();
            
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
    }
    
    return listagem;
}
  
 
 
 
}

