package Database_Penilaian;

import java.sql.*;
import java.util.Scanner;

//Class
public class Tampilan 
{
	//Scanner
	Scanner terimaInput = new Scanner (System.in);
	
	//Database (CRUD)
	static Connection conn;
	String url = "jdbc:mysql://localhost:3306/db_penilaian";
	
	public void tampilData() throws SQLException
	{
		//Database (CRUD)
		String sql ="SELECT * FROM nilai_mahasiswa";
		conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		//Pengulangan
		while(result.next())
		{
			System.out.print("\nNIM Mahasiswa\t: ");
            System.out.print(result.getInt("nim_mahasiswa"));

            System.out.print("\nNama Mahasiswa\t: ");
            System.out.print(result.getString("nama_mahasiswa"));

            System.out.print("\nNilai Tugas\t: ");
            System.out.print(result.getString("nilai_tugas"));

            System.out.print("\nNilai UTS\t: ");
            System.out.print(result.getString("nilai_uts"));

            System.out.print("\nNilai UAS\t: ");
            System.out.print(result.getString("nilai_uas"));

            System.out.print("\nNilai Akhir\t: ");
            System.out.print(result.getString("nilai_akhir"));
			
            System.out.print("\nGrade\t\t: ");
            System.out.print(result.getString("grade"));
            System.out.print("\n");
		}
	}
	
	public void tampilCariData() throws SQLException
	{
		System.out.print("Masukkan Nama Mahasiswa : ");    
		String keyword = terimaInput.nextLine();
		
		//Database (CRUD)
		String sql = "SELECT * FROM nilai_mahasiswa WHERE nama_mahasiswa LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		//Pengulangan
		while(result.next())
		{
			System.out.print("\nNIM Mahasiswa\t: ");
            System.out.print(result.getInt("nim_mahasiswa"));

            System.out.print("\nNama Mahasiswa\t: ");
            System.out.print(result.getString("nama_mahasiswa"));

            System.out.print("\nNilai Tugas\t: ");
            System.out.print(result.getString("nilai_tugas"));

            System.out.print("\nNilai UTS\t: ");
            System.out.print(result.getString("nilai_uts"));

            System.out.print("\nNilai UAS\t: ");
            System.out.print(result.getString("nilai_uas"));

            System.out.print("\nNilai Akhir\t: ");
            System.out.print(result.getString("nilai_akhir"));
			
            System.out.print("\nGrade\t\t: ");
            System.out.print(result.getString("grade"));
            System.out.print("\n");
		}
	}
}
