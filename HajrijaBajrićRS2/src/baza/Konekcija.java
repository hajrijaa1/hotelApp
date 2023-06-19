/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hajrija Bajrić - 28
 */
public class Konekcija {
    private static final String korisnik = "root";
    private static final String sifra = "abcd1234";
    private static final String kon = "jdbc:mysql://localhost:3306/ljeto?serverTimezone=UTC";
    public Connection veza = null;
    public Konekcija() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            veza = DriverManager.getConnection(kon,korisnik,sifra);
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }
    
    
    public void unosHotela  (String ime, String mjesto, float cijena, float popustDjeca) throws SQLException{
        Statement upitBaza = (Statement) veza.createStatement();
        String upit = "INSERT INTO hotel (ime, mjesto, cijena, popustDjeca) VALUES ('"+ime+"', '"+mjesto+"', '"+cijena+"', '"+popustDjeca+"')";
        try{
            upitBaza.executeUpdate(upit);
        } 
        catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    
     public void unosAranzmana (String osoba, int brOdraslih,int brDjece ,LocalDate datumPrijave, LocalDate datumOdjave, int idHotela) throws SQLException{
        Date d=Date.valueOf(datumPrijave);
        Date d1=Date.valueOf(datumOdjave);
        Statement upitBaza = (Statement) veza.createStatement();
        String upit = "INSERT INTO aranzman (osoba, brOdraslih, brDjece, datumPrijave,datumOdjave, idHotela ) VALUES ('"+osoba+"', '"+brOdraslih+"', '"+brDjece+"', '"+d+"', '"+d1+"', '"+idHotela+"')";
        try{
            upitBaza.executeUpdate(upit);
        } 
        catch (SQLException e) {
            System.err.println(e);
        }
    }
     
     
         public ResultSet ispisAranzmana() throws SQLException 
                {
                    Statement upitBaza=(Statement) veza.createStatement();
                    ResultSet rezultat=null;
                    try
                    {
                        rezultat=upitBaza.executeQuery("select aranzman.osoba, hotel.ime, aranzman.brOdraslih, aranzman.brDjece from aranzman JOIN hotel ON aranzman.idHotela = hotel.id "); 
                        return rezultat;
                    }
                    catch(SQLException e)
                    {
                        System.err.println(e);
                    }
                    return rezultat;
                }
        
        
          public ResultSet PretragaHotela(String ime) throws SQLException 
                {
                    Statement upitBaza=(Statement) veza.createStatement();
                    ResultSet rezultat=null;
                    try
                    {
                        rezultat=upitBaza.executeQuery("select hotel.id, hotel.ime, hotel.mjesto from hotel WHERE hotel.ime LIKE '%" + ime + "%'"); 
                        return rezultat;
                    }
                    catch(SQLException e)
                    {
                        System.err.println(e);
                    }
                    return rezultat;
                }
    
    public List<String> idHotela(){
           String query="Select id from hotel ";
           List<String> a=new ArrayList<>();
           try{
               Statement stmt=veza.createStatement();
               ResultSet rs=stmt.executeQuery(query);
               while(rs.next()){
                   String id=rs.getString("id");
                   a.add(id);      
               }
               
           }catch(SQLException e){
               e.printStackTrace();
           }
           return a;
       }
    
    
    
    
    
    
    
}
