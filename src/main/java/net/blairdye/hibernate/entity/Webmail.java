package net.blairdye.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="Webmail", 
uniqueConstraints={@UniqueConstraint(columnNames={"organizationId"})})
public class Webmail {
	@Id
	private String organizationId;
	@Column(name="DOMAIN")  
	private String domainname;
	
	public Webmail(){}

	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
}
