package Database_Penilaian;

import java.sql.*;

//Interface
public interface Penilaian 
{
	void tambahData() throws SQLException;
	void lihatData() throws SQLException;
	void ubahData() throws SQLException;
	void hapusData();
	void cariData() throws SQLException;
}
