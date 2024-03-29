package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Programa {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate," + "BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)"); // os ? s�o valores n�o declarados

			st.setString(1, "Carlos Silva"); // o 1 indica que � primeira interroga��o ser� atribuido o valor 'Carlos Silva'
			st.setString(2, "carloss@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/07/1987").getTime())); // Para o jdbc deve-se usar o java.sql.Date e n�o o java.util.Date
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate(); // Para atualiza��o de Dados

			System.out.println("Done! Rows Affecteds: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();		//Tratamento do sdf.parse do java.sql.date
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeConnection();		// A Conex�o sempres deve ser fechado por ultimo!
		}

	}

}
