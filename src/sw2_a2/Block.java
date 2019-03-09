package sw2_a2;
import java.util.Date;


public class Block { 
	public String hash;//signature
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; 
	private int nonce; //changed on each iteration till the leading 0 hash is found

	public Block(String d,String prevHash ) {
		data = d;
		previousHash = prevHash;
		timeStamp = new Date().getTime();
		hash = calculateHash(); 

	}
	
	public String calculateHash() {
	
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce)+
				data  );
		
		return calculatedhash;
	}
	
	
	public void mineBlock(int difficulty) {
		
		String target = ""; 
		for(int i=0; i< difficulty; i++) {
			target+='0';
		}

		while(!hash.substring(0,difficulty).equals(target)) {
			nonce++;
			hash= calculateHash();
		}
		
		System.out.println("Block Mined : " + hash);
	}
	
	

}