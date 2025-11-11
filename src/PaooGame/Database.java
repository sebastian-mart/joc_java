package PaooGame;

import PaooGame.Items.Item;

import java.util.ArrayList;
import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:gameSave.db";

    public Database() {
        createTable();
    }

    private void createTable(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt=conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SAVES "+
                    "( PLAYER CHAR(3), "+
                    "HEROX INT, "+
                    "HEROY INT, "+
                    "HEALTH INT,  "+
                    "CURRENTWEAPONINDEX INT, "+
                    "UNLOCKEDWEAPONS CHAR(4), "+
                    "LEVEL INT, " +
                    "ITEMS CHAR(10), " +
                    "NPC CHAR(10)," +
                    "HIGHSCORE INT) ";

//
            stmt.execute(sql);
        }
        catch(Exception e){
            System.out.println("Error creating table Saves");
            e.printStackTrace();
        }

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt=conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS HIGHSCORES "+
                    "( PLAYER CHAR(3), "+
                    "SCORE INT) ";

//
            stmt.execute(sql);
        }
        catch(Exception e){
            System.out.println("Error creating table High scores");
            e.printStackTrace();
        }

    }

    public void insertTable(GameSave gameSave){
        StringBuilder weapons=new StringBuilder();
        StringBuilder items=new StringBuilder();
        StringBuilder npcs=new StringBuilder();
        for(boolean i : gameSave.getDbunlockedWeapons()){
            if(i){
                weapons.append("1");
            }
            else{
                weapons.append("0");
            }
        }

        for(int i=0;i<9;i++){
            if(gameSave.getDbcollectedItems()[i]){
                items.append("1");
            }
            else{
                items.append("0");
            }
            if(gameSave.getDbNPCs()[i]){
                npcs.append("1");
            }
            else{
                npcs.append("0");
            }
        }
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM SAVES");
            String sql = "INSERT INTO SAVES (PLAYER,HEROX,HEROY,HEALTH,CURRENTWEAPONINDEX,UNLOCKEDWEAPONS,LEVEL,ITEMS,NPC,HIGHSCORE) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,gameSave.getDbName());
            pstmt.setInt(2,gameSave.getDbHeroX());
            pstmt.setInt(3,gameSave.getDbHeroY());
            pstmt.setInt(4,gameSave.getDbHealth());
            pstmt.setInt(5,gameSave.getCurrentWeaponIndex());
            pstmt.setString(6,weapons.toString());
            pstmt.setInt(7,gameSave.getDblevel());
            pstmt.setString(8,items.toString());
            pstmt.setString(9, npcs.toString());
            pstmt.setInt(10,gameSave.getDbhighScore());
            pstmt.executeUpdate();
            System.out.println("Successfully inserted into table");
        }
        catch (SQLException e){
            System.out.println("Error inserting into table");
        }
        catch(Exception e){
            System.out.println("Error inserting table");
            e.printStackTrace();
        }
    }
    public GameSave readTable(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVES");
            if (rs.next()) {
                boolean []unlocked =new boolean[4];
                int index=0;
                for (char i:rs.getString("UNLOCKEDWEAPONS").toCharArray()){
                    if(i=='1'){
                        unlocked[index]=true;
                    }
                    else if(i=='0'){
                        unlocked[index]=false;
                    }
                    index++;
                }
                char[] items =new char[10];
                char[] npc =new char[10];
                boolean[] items1 =new boolean[10];
                boolean[] npc1 =new boolean[10];
                index=0;
                items=rs.getString("ITEMS").toCharArray();
                npc=rs.getString("NPC").toCharArray();
                for (int i=0;i<items.length;i++){
                    if(items[i]=='1'){
                        items1[i]=true;
                    }
                    else{
                        items1[i]=false;
                    }
                    if(npc[i]=='1'){
                        npc1[i]=true;
                    }
                    else{
                        npc1[i]=false;
                    }
                }
                System.out.println("citire corecta din baza de date");
                return new GameSave(rs.getString("PLAYER"),
                        rs.getInt("HEROX"),
                        rs.getInt("HEROY"),
                        rs.getInt("HEALTH"),
                        rs.getInt("CURRENTWEAPONINDEX"),
                        unlocked,
                        rs.getInt("LEVEL"),
                        items1,
                        npc1,
                        rs.getInt("HIGHSCORE"));
            }

        } catch (Exception e) {
            System.out.println("Error in reading table");
            e.printStackTrace();
        }
        return null;
    }
    public void insertHighScore(String name, int score) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO HIGHSCORES (PLAYER, SCORE) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getHighScores() {
        StringBuilder result = new StringBuilder();
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String query = "SELECT PLAYER, SCORE FROM HIGHSCORES ORDER BY SCORE DESC";
            ResultSet rs = stmt.executeQuery(query);
            int rank = 1;
            while (rs.next() && rank <= 5) {
                String player = rs.getString("PLAYER").trim(); // Elimină spațiile din campul player
                int score = rs.getInt("SCORE");
                result.append(rank)
                        .append(". ")
                        .append(player)
                        .append(" - ")
                        .append(score)
                        .append("\n");
                rank++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
