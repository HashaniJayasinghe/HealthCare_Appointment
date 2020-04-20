package com;

import com.AppointmentResource;
import com.Appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppointmentService {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthCare", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public String readAppointments() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading";

			}

			output = "<table border=\"1\">" + "<tr>" + "<th>AppointmentID</th>" + "<th>Name</th>" + "<th>Email</th>"
					+ "<th>PhoneNo</th>" + "<th>Gender</th>" + "<th>Symptoms</th>" + "<th>Date</th>" + "<th>Time</th>"
					+ "<th>AppointmentType</th>" + "<th>Hospital</th>";

			String query = "select * from appointments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String appointmentId = Integer.toString(rs.getInt("appointmentID"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNo = Integer.toString(rs.getInt("phoneNo"));
				String gender = rs.getString("gender");
				String symptoms = rs.getString("symptoms");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String appointmentType = rs.getString("appointmentType");
				String hospital = rs.getString("hospital");

				output += "<tr><td>" + appointmentId + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + symptoms + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + appointmentType + "</td>";
				output += "<td>" + hospital + "</td>";

				output += "<td><input name = \"btnUpdate\"" + "type = \"button\" value=\"Update\"></td>\r\n"
						+ "<td><form method=\"post\" action=\"Appointment.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"AppointmentID\" type=\"hidden\" "
						+ " value=\"" + appointmentId + "\">" + "</form></td></tr>";
			}

			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Appointments";
			System.err.print(e.getMessage());
		}
		return output;

	}

	public Appointment readAppointment(String appointmentID) {

		String output = "";

		Appointment app = new Appointment();

		try {
			Connection con = connect();

			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}

			String query = "select * from appointments where AppointmentID = '" + appointmentID + "'";
			assert con != null;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {

				app.setAppointmentID(rs.getInt("appointmentID"));
				app.setName(rs.getString("name"));
				app.setEmail(rs.getString("email"));
				app.setPhoneNo(rs.getInt("phoneNo"));
				app.setGender(rs.getString("gender"));
				app.setSymptoms(rs.getString("symptoms"));
				app.setDate(rs.getString("date"));
				app.setTime(rs.getString("time"));
				app.setAppointmentType(rs.getString("appointmentType"));
				app.setHospital(rs.getString("hospital"));

				System.out.println("Appointment retrieval Successful");
				con.close();
				return app;
			}

		} catch (Exception e) {
			System.out.println("Error while reading appointment. appointment retrieval Unsuccessful");
			System.err.println(e.getMessage());
		}
		System.out.println("No such appointment in system");
		return new Appointment();

	}

	public String insertAppointment(Appointment app) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
                return "Error while connecting to the database for inserting.";
            }

            String query = " insert into appointments (`AppointmentID`,`Name`,`Email`,`PhoneNo`,`Gender`,`Symptoms`,`Date`,`Time`,`AppointmentType`,`Hospital`)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, app.getAppointmentID());
            preparedStmt.setString(2, app.getName());
            preparedStmt.setString(3, app.getEmail());
            preparedStmt.setInt(4, app.getPhoneNo());
            preparedStmt.setString(5, app.getGender());
            preparedStmt.setString(6, app.getSymptoms());
            preparedStmt.setString(7, app.getDate());
            preparedStmt.setString(8, app.getTime());
            preparedStmt.setString(9, app.getAppointmentType());
            preparedStmt.setString(10, app.getHospital());
           


            preparedStmt.execute();
            con.close();
            output = "Inserted successfully";
            System.out.println("Appointment inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting Appointment details.";
            System.out.println("Error while inserting Appointment");
            System.out.println(e.getMessage());
        }
        return output;	}

	public String updateAppointment(Appointment app) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE appointments SET Name=?,Email=?,PhoneNo=?, Gender=?, Symptoms=?, Date=?, Time=?, AppointmentType=?, Hospital=? WHERE AppointmentID=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, app.getName());
			ps.setString(2, app.getEmail());
			ps.setInt(3, app.getPhoneNo());
			ps.setString(4, app.getGender());
			ps.setString(5, app.getSymptoms());
			ps.setString(6, app.getDate());
			ps.setString(7, app.getTime());
			ps.setString(8, app.getAppointmentType());
			ps.setString(9, app.getHospital());
			ps.setInt(10, app.getAppointmentID());

			ps.executeUpdate();
			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating doctor.";

			System.err.println(e.getMessage());
		}
		return output;

	}

	public String deleteAppointment(String appointmentID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from appointments where AppointmentID=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, appointmentID);

			ps.execute();
			con.close();

			output = "Deleted successfully";

		} catch (Exception e) {
			output = "Error while deleting Appointment.";
			System.err.println(e.getMessage());

		}
		return output;

	}

}
