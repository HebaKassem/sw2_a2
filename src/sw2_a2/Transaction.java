package sw2_a2;

import java.security.*;
import java.util.ArrayList;

public class Transaction {

public String transaction_id;
public PublicKey sender;
public PublicKey reciever;
public float value;
public byte[] signature;
private static int sequence;

	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
        
        public Transaction(PublicKey s,PublicKey r,float v,ArrayList<TransactionInput> input){
        sender =s;
        reciever = r;
        value =v;
        inputs=input;
        }


	private String calulateHash() {
		sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
		return StringUtil.applySha256(
				StringUtil.getStringFromKey(sender) +
                                StringUtil.getStringFromKey(reciever) +
				Float.toString(value) + sequence 
				);
	}

public void generateSignature(PrivateKey privateKey) {
	String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciever) + Float.toString(value)	;
	signature = StringUtil.applyECDSASig(privateKey,data);		
}
public boolean verifiySignature() {
	String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciever) + Float.toString(value)	;
	return StringUtil.verifyECDSASig(sender, data, signature);
}
    
}
