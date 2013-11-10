package de.hegmanns.it.schulung.hibernate.domain.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WP_ORDERVORGANG")
public class Ordervorgang extends BaseEntity{
	
	private String vorgangsart;

	public String getVorgangsart() {
		return vorgangsart;
	}

	public void setVorgangsart(String vorgangsart) {
		this.vorgangsart = vorgangsart;
	}
	
	
	
	@OneToOne(optional = true, mappedBy = "internerVorgang")
	private Ordervorgang externerVorgang;
	
	@OneToOne(optional = true, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "ORDERVORGANG_ID_INTERN")
	private Ordervorgang internerVorgang;

	public Ordervorgang getExternerVorgang() {
		return externerVorgang;
	}

	public void setExternerVorgang(Ordervorgang externerVorgang) {
		this.externerVorgang = externerVorgang;
	}

	public Ordervorgang getInternerVorgang() {
		return internerVorgang;
	}

	public void setInternerVorgang(Ordervorgang internerVorgang) {
		this.internerVorgang = internerVorgang;
	}
	
	
}
