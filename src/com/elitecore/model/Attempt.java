package com.elitecore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "attempts")
public class Attempt {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "client_id", nullable =false, length=20)
	private String client_id;

	@Column(name = "OTP", nullable = false, length=20)
	private String OTP;
	
	@Column(name = "timestamp")
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

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
