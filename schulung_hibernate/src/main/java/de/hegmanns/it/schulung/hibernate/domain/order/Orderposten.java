package de.hegmanns.it.schulung.hibernate.domain.order;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WP_ORDERPOSTEN")
public class Orderposten extends BaseEntity {

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "WP_ORDERPOSTEN_ID")
	private List<Orderteil> orderteile;
	
	private BigDecimal handelswert;
	
	private String wkn;

	public List<Orderteil> getOrderteile() {
		return orderteile;
	}

	public void setOrderteile(List<Orderteil> orderteile) {
		this.orderteile = orderteile;
	}

	public BigDecimal getHandelswert() {
		return handelswert;
	}

	public void setHandelswert(BigDecimal handelswert) {
		this.handelswert = handelswert;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) {
		this.wkn = wkn;
	}
	
	
}
