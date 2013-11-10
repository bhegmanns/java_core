package de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;

import de.hegmanns.it.schulung.hibernate.domain.order.BaseEntity;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderposten;

@Entity
@Table(name = "WP_OPT_ORDER")
public class OptimisticOrder extends BaseEntity{
	
	@OneToMany
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "WP_ORDER_ID")
	private List<Orderposten> orderposten;

	@Version
	private int version;

	private String depotnummer;
	
	private Date zeitpunktErstellung;

	public List<Orderposten> getOrderposten() {
		return orderposten;
	}

	public void setOrderposten(List<Orderposten> orderposten) {
		this.orderposten = orderposten;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDepotnummer() {
		return depotnummer;
	}

	public void setDepotnummer(String depotnummer) {
		this.depotnummer = depotnummer;
	}

	public Date getZeitpunktErstellung() {
		return zeitpunktErstellung;
	}

	public void setZeitpunktErstellung(Date zeitpunktErstellung) {
		this.zeitpunktErstellung = zeitpunktErstellung;
	}
	
	
}
