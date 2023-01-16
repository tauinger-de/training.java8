package appl;

public class ABC {
	
	public int aId;
	public String aName;
	public int bId;
	public String bName;
	public int cId;
	public String cName;
	
	public ABC(int aId, String aName, int bId, String bName, int cId, String cName) {
		this.aId = aId;
		this.aName = aName;
		this.bId = bId;
		this.bName = bName;
		this.cId = cId;
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "ABC [aId=" + aId + ", aName=" + aName + ", bId=" + bId + ", bName=" + bName + ", cId=" + cId
				+ ", cName=" + cName + "]";
	}


	
}
