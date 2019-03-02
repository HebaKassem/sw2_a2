package sw2_a2;
import java.util.Date;


public class Block {
	public String hash;//signature
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; 

	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); 

	}
	
	public String calculateHash() {
	
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				data 
				);
		return calculatedhash;
	}
}
