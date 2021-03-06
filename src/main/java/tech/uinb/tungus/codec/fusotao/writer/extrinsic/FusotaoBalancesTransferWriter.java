package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesTransfer;
import java.io.IOException;

import tech.uinb.tungus.codec.StaticLookupSourceWriter;

public class FusotaoBalancesTransferWriter implements ScaleWriter<FusotaoBalancesTransfer> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesTransfer value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getDest());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getValue().getValue());
	}
}
