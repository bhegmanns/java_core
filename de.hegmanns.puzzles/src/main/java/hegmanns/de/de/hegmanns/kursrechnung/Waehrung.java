package hegmanns.de.de.hegmanns.kursrechnung;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mockito.internal.matchers.Equals;

public class Waehrung {

	private String code;
	
	public Waehrung(){
		this.code = null;
	}
	
	public Waehrung(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.code).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Waehrung waehrung = (Waehrung)obj;
		return new EqualsBuilder().append(this.code, waehrung.code).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("Code", code).toString();
	}
	
	
}
