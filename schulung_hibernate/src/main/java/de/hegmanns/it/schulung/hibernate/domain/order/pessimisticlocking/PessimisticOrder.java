package de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.hegmanns.it.schulung.hibernate.domain.order.BaseEntity;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderposten;

@Entity
@Table(name = "WP_PESS_ORDER")
public class PessimisticOrder extends BaseEntity{
	
	private String depotnummer;
	
	private List<Orderposten> orderposten;
	
	private Date zeitpunktErstellung;

	public String getDepotnummer() {
		return depotnummer;
	}

	public void setDepotnummer(String depotnummer) {
		this.depotnummer = depotnummer;
	}

	public List<Orderposten> getOrderposten() {
		return orderposten;
	}

	public void setOrderposten(List<Orderposten> orderposten) {
		this.orderposten = orderposten;
	}

	public Date getZeitpunktErstellung() {
		return zeitpunktErstellung;
	}

	public void setZeitpunktErstellung(Date zeitpunktErstellung) {
		this.zeitpunktErstellung = zeitpunktErstellung;
	}
	
	
}
