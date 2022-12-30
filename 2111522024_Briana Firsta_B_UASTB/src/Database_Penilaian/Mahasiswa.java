package Database_Penilaian;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

 
public class Mahasiswa extends Tampilan implements Penilaian
{
	static Connection conn;

	//Database (CRUD)
	String url = "jdbc:mysql://localhost:3306/db_penilaian";
	
	public int nimMahasiswa = 0;
    public int nTugas = 0;
    public int nUTS = 0;
    public int nUAS = 0;
    public double nAkhir = 0;
    public char grade;
    public String namaMahasiswa;
    Scanner terimaInput = new Scanner (System.in);

    public void lihatData() throws SQLException 
	{
		String text1 = "\n===Daftar Seluruh Data Mahasiswa===";

		//Method String
		System.out.println(text1.toUpperCase());	

		//Inheritance 
		tampilData();
	}
    
    public void tambahData() throws SQLException 
	{
    	String text2 = "\n===Tambah Data Mahasiswa===";

    	//Method String
		System.out.println(text2.toUpperCase());
		
    	try 
		{
	        //NIM
	    	System.out.print("Masukkan NIM mahasiswa: ");
	        nimMahasiswa = terimaInput.nextInt();
	        terimaInput.nextLine();
	
	        //Nama 
	        System.out.print("Masukkan nama mahasiswa: ");
	        namaMahasiswa = terimaInput.nextLine(); 
	
	        System.out.print("\nNama dan NIM mahasiswa: ");
	        System.out.println(namaMahasiswa +" ("+ nimMahasiswa +")");
	        
	        //Tugas
	    	System.out.print("\nMasukkan nilai tugas: ");
	        nTugas = terimaInput.nextInt();
	        terimaInput.nextLine();
	        
	        //UTS
	    	System.out.print("Masukkan nilai UTS: ");
	        nUTS = terimaInput.nextInt();
	        terimaInput.nextLine(); 
	        
	        //UAS
	    	System.out.print("Masukkan nilai UAS: ");
	        nUAS = terimaInput.nextInt();
	        terimaInput.nextLine();
	        
	        //Proses Matematika : nilai akhir
	        nAkhir = (nTugas * 0.15 + nUTS * 0.4 + nUAS * 0.45);
	        nAkhir = (int) nAkhir;
	        System.out.println("\nNilai Akhir: " + nAkhir);
	        
	        //Grade
	        if ( nAkhir >= 85 && nAkhir <= 100) 
			{
	        	grade = 'A';
	        }
	        else if (nAkhir >= 75 && nAkhir <= 84) 
			{
	        	grade = 'B';
	        }
	        else if (nAkhir >= 65 && nAkhir <= 74) 
			{
	        	grade = 'C';
	        }
	        else if (nAkhir >= 40 && nAkhir <= 64) 
			{
	        	grade = 'D';
	        }
	        else if (nAkhir >= 0 && nAkhir <= 39) 
			{
	        	grade = 'E';
	        }
	        System.out.println("Grade: " + grade);
	        
	        //Database (CRUD)
	        String sql = "INSERT INTO nilai_mahasiswa (nim_mahasiswa, nama_mahasiswa, nilai_tugas, 	nilai_uts, nilai_uas, nilai_akhir, grade) VALUES ('"+nimMahasiswa+"','"+namaMahasiswa+"','"+nTugas+"','"+nUTS+"','"+nUAS+"','"+nAkhir+"','"+grade+"')";
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        statement.execute(sql);
	        System.out.println("Berhasil input data");
    	}
    	catch (SQLException e) 
		{
	        System.err.println("Terjadi kesalahan input data");
	    } 
    	catch (InputMismatchException e)
		{
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	} 
	public void ubahData() throws SQLException
	{
		String text3 = "\n===Ubah Data Mahasiswa===";

		//Method String
		System.out.println(text3.toUpperCase());
		
		try 
		{
            lihatData();
            System.out.print("\nMasukkan NIM mahasiswa yang akan diubah : ");
            Integer nimMahasiswa = Integer.parseInt(terimaInput.nextLine());
            
            //Database (CRUD)
            String sql = "SELECT * FROM nilai_mahasiswa WHERE nim_mahasiswa = " +nimMahasiswa;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next())
			{
                
                System.out.print("Nama baru ["+result.getString("nama_mahasiswa")+"]\t: ");
                String namaMahasiswa = terimaInput.nextLine();
                   
                //Database (CRUD)
                sql = "UPDATE nilai_mahasiswa SET nama_mahasiswa='"+namaMahasiswa+"' WHERE nim_mahasiswa='"+nimMahasiswa+"'";

                if(statement.executeUpdate(sql) > 0)
				{
                    System.out.println("Berhasil memperbaharui data mahasiswa (NIM: "+nimMahasiswa+")");
                }
            }
            statement.close();        
        } 
		catch (SQLException e) 
		{
        	System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	}
	
	public void hapusData() 
	{
		String text4 = "\n===Hapus Data Mahasiswa===";

		//Method String
		System.out.println(text4.toUpperCase());
		
		try
		{
	        lihatData();
	        System.out.print("\nKetik NIM Mahasiswa yang akan dihapus : ");
	        Integer nimMahasiswa = Integer.parseInt(terimaInput.nextLine());
	        
	        //Database (CRUD)
	        String sql = "DELETE FROM nilai_mahasiswa WHERE nim_mahasiswa = "+ nimMahasiswa;
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        
	        if(statement.executeUpdate(sql) > 0)
			{
	            System.out.println("Berhasil menghapus data mahasiswa (NIM: "+nimMahasiswa+")");
	        }
	   }
		catch(SQLException e)
		{
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
	}
	
	public void cariData () throws SQLException 
	{
		String text5 = "\n===Cari Data Mahasiswa===";
		//Method String
		System.out.println(text5.toUpperCase());
				
		//Inheritance
		tampilCariData();
	}
}
