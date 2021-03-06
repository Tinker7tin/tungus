package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.TokenDominatorClaimed(TokenId,AccountId,TokenBalance)
public class FusotaoReceiptsTokenDominatorClaimed extends EventRecord {
	private Hash256 param0;
	private Address param1;
	private DotAmount param2;

	public Hash256 getParam0(){
		return this.param0;
	}

	public void setParam0(Hash256 param0){
		this.param0 = param0;
	}

	public Address getParam1(){
		return this.param1;
	}

	public void setParam1(Address param1){
		this.param1 = param1;
	}

	public DotAmount getParam2(){
		return this.param2;
	}

	public void setParam2(DotAmount param2){
		this.param2 = param2;
	}
}