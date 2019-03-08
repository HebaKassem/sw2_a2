package sw2_a2;


public class TransactionInput {
    
    	public String transactionOutputId; //Reference to TransactionOutputs -> transactionId
	public TransactionOutput UTXO; //Contains the Unspent transaction output
        
        public TransactionInput(String id){
        transactionOutputId=id;
        }
        
}
