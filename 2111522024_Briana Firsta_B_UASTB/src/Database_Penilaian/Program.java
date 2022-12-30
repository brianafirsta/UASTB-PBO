package Database_Penilaian;

import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

//Class
public class Program 
{
	static Connection conn;
	
    public static void main(String[] args) 
	{
    	//Pemanggilan Constructor
    	Administrator ana = new Administrator();
        System.out.println("Nama Administrator\t: " + ana.namaAdm);
        System.out.println("NIM Administrator\t: " + ana.nimAdm);
        System.out.println("Judul Program\t\t: " + ana.judul);
    	
        //Collection Framework (LinkedList)
        LinkedList<String> List = new LinkedList<String>();
        List.add("Teknologi Informasi");
        List.add("Sistem Informasi");
        System.out.println("Fakultas dan Jurusan    : " + List);
        
    	Scanner terimaInput = new Scanner (System.in);
    	String pilihanUser;
    	boolean isLanjutkan = true;
    	
    	//Pengolahan Database (CRUD)
    	String url = "jdbc:mysql://localhost:3306/db_penilaian";
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("\nClass Driver ditemukan");
			Mahasiswa mahasiswa = new Mahasiswa();
			
			//Perulangan
			while (isLanjutkan) 
			{
				System.out.println("==================================================");
				System.out.println("Database Penilaian Mahasiswa Sistem Informasi 2022");
				System.out.println("==================================================");
				System.out.println("1. Lihat Data Mahasiswa");
				System.out.println("2. Tambah Data Mahasiswa");
				System.out.println("3. Ubah Data Mahasiswa");
				System.out.println("4. Hapus Data Mahasiswa");
				System.out.println("5. Cari Data Mahasiswa");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihanUser = terimaInput.next();
				
				//Percabangan
				switch (pilihanUser) 
				{
				case "1":
					mahasiswa.lihatData();
					break;
				case "2":
					mahasiswa.tambahData();
					break;
				case "3":
					mahasiswa.ubahData();
					break;
				case "4":
					mahasiswa.hapusData();
					break;
				case "5":
					mahasiswa.cariData();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-6]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihanUser = terimaInput.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");

			}
			//Method Date
	        Date date = new Date();
	        String str = String.format("\nProgram Selesai Pada: %tc", date);
	        System.out.println(str);
			
		}
		//Exception
		catch(ClassNotFoundException ex) 
		{
			System.err.println("Driver Error");
			System.exit(0);
		}
		//Exception
		catch(SQLException e)
		{
			System.err.println("Tidak berhasil koneksi");
		}
    }
}
