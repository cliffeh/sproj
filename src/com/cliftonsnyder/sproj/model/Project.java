package com.cliftonsnyder.sproj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "project")
@Table(name = "project")
public class Project {

	public static final int URG_OKAY = 0;
	public static final int URG_URGENT = 1;
	public static final int URG_CRITICAL = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	private String name;
	private String owner;
	private String status;
	private int urgency;

	public Project() {
		this(0);
	}

	public Project(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public String getStatus() {
		return status;
	}

	public int getUrgency() {
		return urgency;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

}
