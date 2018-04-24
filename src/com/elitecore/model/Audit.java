package com.elitecore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "audit")
public class Audit {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "client_id", nullable =false, length=20)
	private String client_id;

	@Column(name = "data_attempt", nullable = false, length=20)
	private String data_attempt;
	
	@Column(name = "otp_attempt", nullable = false, length=20)
	private String otp_attempt;
	
	@Column(name = "date")
	@Type(type="date")
	private String timestamp;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getData_attempt() {
		return data_attempt;
	}

	public void setData_attempt(String data_attempt) {
		this.data_attempt = data_attempt;
	}

	public String getOtp_attempt() {
		return otp_attempt;
	}

	public void setOtp_attempt(String otp_attempt) {
		this.otp_attempt = otp_attempt;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
