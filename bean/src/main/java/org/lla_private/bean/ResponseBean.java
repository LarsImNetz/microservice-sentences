package org.lla_private.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseBean implements Serializable {

	String satz;

	public String getSatz() {
		return satz;
	}

	public void setSatz(String a) {
		this.satz = a;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((satz == null) ? 0 : satz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseBean other = (ResponseBean) obj;
		if (satz == null) {
			if (other.satz != null)
				return false;
		} else if (!satz.equals(other.satz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bean [a=" + satz + "]";
	}

}
