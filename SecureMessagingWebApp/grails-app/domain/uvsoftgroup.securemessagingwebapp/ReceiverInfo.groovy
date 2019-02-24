package uvsoftgroup.securemessagingwebapp

class ReceiverInfo{
	  Long aesDeId,aesEnId
	  Long aesDeKeySize
	  String aesDeSoBase64String,aesDeSelectionMode,aesDeIvInitVector,aesDeSecretKey
	  String aesDeDeBase64String,aesDePlainString

	String toString() { "${id}"}


	static constraints = {
		aesDeId()
		aesEnId()
		aesDeKeySize()
		aesDeSoBase64String()
		aesDeSelectionMode()
		aesDeIvInitVector()
		aesDeSecretKey()
		aesDeDeBase64String()
		aesDePlainString()
	}

	static belongsTo =[userRegistrations:UserRegistration]

	static mapping = {
		table 'receiverinfo'
		columns {
			id generator: 'uvsoftgroup.securemessagingwebapp.utility.ReceiverInfoIdGenerator'
		}
	  }

	Long getAesDeId() {
		return aesDeId
	}

	void setAesDeId(Long aesDeId) {
		this.aesDeId = aesDeId
	}

	Long getAesEnId() {
		return aesEnId
	}

	void setAesEnId(Long aesEnId) {
		this.aesEnId = aesEnId
	}

	Long getAesDeKeySize() {
		return aesDeKeySize
	}

	void setAesDeKeySize(Long aesDeKeySize) {
		this.aesDeKeySize = aesDeKeySize
	}

	String getAesDeSoBase64String() {
		return aesDeSoBase64String
	}

	void setAesDeSoBase64String(String aesDeSoBase64String) {
		this.aesDeSoBase64String = aesDeSoBase64String
	}

	String getAesDeSelectionMode() {
		return aesDeSelectionMode
	}

	void setAesDeSelectionMode(String aesDeSelectionMode) {
		this.aesDeSelectionMode = aesDeSelectionMode
	}

	String getAesDeIvInitVector() {
		return aesDeIvInitVector
	}

	void setAesDeIvInitVector(String aesDeIvInitVector) {
		this.aesDeIvInitVector = aesDeIvInitVector
	}

	String getAesDeSecretKey() {
		return aesDeSecretKey
	}

	void setAesDeSecretKey(String aesDeSecretKey) {
		this.aesDeSecretKey = aesDeSecretKey
	}

	String getAesDeDeBase64String() {
		return aesDeDeBase64String
	}

	void setAesDeDeBase64String(String aesDeDeBase64String) {
		this.aesDeDeBase64String = aesDeDeBase64String
	}

	String getAesDePlainString() {
		return aesDePlainString
	}

	void setAesDePlainString(String aesDePlainString) {
		this.aesDePlainString = aesDePlainString
	}
}
