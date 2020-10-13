package com.spcm.vo.generic;

import java.util.Date;

public class GenericCryptoVO {
	
	private String hashId;
	private String hashKey;
	private Date hashDate;
	
	public String getHashId() {
		return hashId;
	}
	public void setHashId(String hashId) {
		this.hashId = hashId;
	}
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public Date getHashDate() {
		return hashDate;
	}
	public void setHashDate(Date hashDate) {
		this.hashDate = hashDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
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
		GenericCryptoVO other = (GenericCryptoVO) obj;
		if (hashId == null) {
			if (other.hashId != null)
				return false;
		} else if (!hashId.equals(other.hashId))
			return false;
		return true;
	}
}
