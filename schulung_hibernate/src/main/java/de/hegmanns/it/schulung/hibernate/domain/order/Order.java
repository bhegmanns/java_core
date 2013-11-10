package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cascade;

@Entity
//@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "WP_ORDER")
public class Order extends BaseEntity{
	public static final String NAME_ORDERPOSTEN = "orderposten";
	public static final String NAME_DEPOTNUMMER = "depotnummer";
	public static final String NAME_ZEITPUNKT_ERSTELLUNG = "zeitpunktErstellung";
	
	@OneToMany
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "WP_ORDER_ID")
	private List<Orderposten> orderposten;

	@Version
	private int version;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private String depotnummer;
	
	private Date zeitpunktErstellung;

	public List<Orderposten> getOrderposten() {
		return orderposten;
	}

	public void setOrderposten(List<Orderposten> orderposten) {
		this.orderposten = orderposten;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
