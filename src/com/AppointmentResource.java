package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.Appointment;
import com.AppointmentService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Appointment")
public class AppointmentResource {

	private AppointmentService appRepo = new AppointmentService();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments() {
		return appRepo.readAppointments();
	}

	@GET
	@Path("Appointment/{appointmentID}")
	@Produces(MediaType.TEXT_HTML)
	public Appointment readAppointment(@PathParam("appointmentID") String appointmentID) {
		return appRepo.readAppointment(appointmentID);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("AppointmentID") int appointmentID, @FormParam("Name") String name,
			@FormParam("Email") String email, @FormParam("PhoneNo") int phoneNo, @FormParam("Gender") String gender,
			@FormParam("Symptoms") String symptoms, @FormParam("Date") String date, @FormParam("Time") String time,
			@FormParam("AppointmentType") String appointmentType, @FormParam("Hospital") String hospital)

	{
		Appointment app = new Appointment();

		app.setAppointmentID(appointmentID);
		app.setName(name);
		app.setEmail(email);
		app.setPhoneNo(phoneNo);
		app.setGender(gender);
		app.setSymptoms(symptoms);
		app.setDate(date);
		app.setTime(time);
		app.setAppointmentType(appointmentType);
		app.setHospital(hospital);

		return appRepo.insertAppointment(app);

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppointmentData) {
		Appointment app = new Appointment();
		JsonObject appObject;
		appObject = new JsonParser().parse(AppointmentData).getAsJsonObject();

		app.setAppointmentID(Integer.parseInt(appObject.get("AppointmentID").getAsString()));
		app.setName(appObject.get("Name").getAsString());
		app.setEmail(appObject.get("Email").getAsString());
		app.setPhoneNo(Integer.parseInt(appObject.get("PhoneNo").getAsString()));
		app.setGender(appObject.get("Gender").getAsString());
		app.setSymptoms(appObject.get("Symptoms").getAsString());
		app.setDate(appObject.get("Date").getAsString());
		app.setTime(appObject.get("Time").getAsString());
		app.setAppointmentType(appObject.get("AppointmentType").getAsString());
		app.setHospital(appObject.get("Hospital").getAsString());

		String output = appRepo.updateAppointment(app);
		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String AppointmentData) {
		Document doc = Jsoup.parse(AppointmentData, "", Parser.xmlParser());

		String appointmentID = doc.select("appointmentID").text();

		String output = appRepo.deleteAppointment(appointmentID);
		return output;
	}

}
