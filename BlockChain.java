package sw2_a2;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.GsonBuilder;
import java.security.*;
import java.util.HashMap;
import org.bouncycastle.*;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
        public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
	public static Wallet walletA;
	public static Wallet walletB;
	
	public static Boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock= blockchain.get(i);
			previousBlock= blockchain.get(i-1);
			
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
				
			}	
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
		}
		System.out.println("The blockchain is valid :)");
		return true;
		
	}

	public static void main(String[] args) {

		
	/*	blockchain.add( new Block("Hi I am the first block", "0"));
		
		blockchain.add( new Block("Hi I am the second block", 
				blockchain.get(blockchain.size() -1 ).hash));
		
		blockchain.add( new Block("Hi I am the third block , Thanks God!",
				blockchain.get(blockchain.size() -1).hash));
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		
		System.out.println(blockchainJson);
		
		System.out.println("To check the blockchin's validity press 1");
		Scanner sc= new Scanner(System.in);
		int inp= sc.nextInt();
		if(inp == 1) {
			BlockChain c= new BlockChain();
			c.isValid();
		}
	}*/
        
        	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		//Create the new wallets
		walletA = new Wallet();
		walletB = new Wallet(); 
		//Test public and private keys
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		//Create a test transaction from WalletA to walletB 
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		//Verify the signature works and verify it from the public key
		System.out.println("Is signature verified");
		System.out.println(transaction.verifiySignature());
        }
}
