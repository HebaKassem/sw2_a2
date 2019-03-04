package sw2_a2;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.GsonBuilder;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 4;
	
	public static void main(String[] args) {

		//adding blocks to the block chain ArrayList
		
		blockchain.add( new Block("Hi I am the first block", "0"));
		System.out.println("Mining block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add( new Block("Hi I am the second block", 
				blockchain.get(blockchain.size() -1 ).hash));
		System.out.println("Mining block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add( new Block("Hi I am the third block , Thanks God!",
				blockchain.get(blockchain.size() -1).hash));
		System.out.println("Mining block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		////////////////////////////////////////////////
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		
		System.out.println(blockchainJson);
		
		System.out.println("To check the blockchin's validity press 1");
		Scanner sc= new Scanner(System.in);
		int inp= sc.nextInt();
		if(inp == 1) {
			BlockChain c= new BlockChain();
			c.isValid();
		}
	}


	
	public static Boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget= new String(new char[difficulty]).replace('\0','0');
		
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
			
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
				System.out.println("Block isn't mined !");
				return false;
			}
		}
		System.out.println("The blockchain is valid :)");
		return true;
		
	}

}