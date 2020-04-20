package com;

public class Appointment {

	private int appointmentID;
	private String name;
	private String email;
	private int phoneNo;
	private String gender;
	private String symptoms;
	private String date;
	private String time;
	private String appointmentType;
	private String hospital;
	
	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentID=" + appointmentID + ", name=" + name + ", email=" + email + ", phoneNo="
				+ phoneNo + ", gender=" + gender + ", symptoms=" + symptoms + ", date=" + date + ", time=" + time
				+ ", appointmentType=" + appointmentType + ", hospital=" + hospital + "]";
	}
	
}
