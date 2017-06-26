package com.holyant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mysql.jdbc.PreparedStatement;

public class MemberTest {
	private Connection conn = null;
	public MemberTest(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","fanghao");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		MemberTest memberTest = new MemberTest();
		String sql = "select id,num,place,thing from t_memery_room";
		try {
			PreparedStatement ps = (PreparedStatement) memberTest.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<MemberRoomEntity> rooms = new ArrayList<MemberRoomEntity>();
			while(rs.next()){
				MemberRoomEntity room = new MemberRoomEntity();
				room.setId(rs.getInt("id"));
				room.setNum(rs.getInt("num"));
				room.setPlace(rs.getString("place"));
				room.setThing(rs.getString("thing"));
				rooms.add(room);
			}
			while(true){
				Random ra =new Random();
//				if(ra.nextInt(2)==1){
					System.err.println(rooms.get(ra.nextInt(50)).getThing());
//				}else{
//					System.err.println(rooms.get(ra.nextInt(50)).getPlace());
//				}
				Thread.sleep(5000);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
