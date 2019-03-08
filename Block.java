package sw2_a2;
import java.util.Date;


public class Block {
	public String hash;//signature
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; 
	private int nonce; //changed on each iteration till the leading 0 hash is found

  
ArrayList <Transaction> transaction = new ArrayList <>();

      	public Block(String previousHash ) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); 
	}


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
				Integer.toString(nonce)+
				data  );
		
		return calculatedhash;
	}
	
	
	public void mineBlock(int difficulty) {
		String target = new String(new 
				char[difficulty]).replace('\0', '0'); 
		//Create a string with difficulty * "0" 
		
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined : " + hash);
	}
	



public boolean addTransaction(Transaction transaction){
        if(transaction == null){return false;}
        if(previousHash != "0"){
        if(transaction.processTransaction() == false){
        System.out.println("Transaction failled.");
        return false;
        }
        }
        transactions.add(transaction);
        System.out.println("successfully,Transaction added.");
        return true;
        }	


}

