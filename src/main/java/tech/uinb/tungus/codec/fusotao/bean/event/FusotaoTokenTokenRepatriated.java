package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Token.TokenRepatriated(TokenId,AccountId,AccountId,Balance)
public class FusotaoTokenTokenRepatriated extends EventRecord {
	private Hash256 param0;
	private Address param1;
	private Address param2;
	private DotAmount param3;

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

	public Address getParam2(){
		return this.param2;
	}

	public void setParam2(Address param2){
		this.param2 = param2;
	}

	public DotAmount getParam3(){
		return this.param3;
	}

	public void setParam3(DotAmount param3){
		this.param3 = param3;
	}
}