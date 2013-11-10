package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WP_ORDERTEIL")
public class Orderteil extends BaseEntity{
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "WP_ORDERTEIL_ID")
	private List<Ordervorgang> ordervorgang;
	
	private String status;

	public List<Ordervorgang> getOrdervorgang() {
		return ordervorgang;
	}

	public void setOrdervorgang(List<Ordervorgang> ordervorgang) {
		this.ordervorgang = ordervorgang;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
