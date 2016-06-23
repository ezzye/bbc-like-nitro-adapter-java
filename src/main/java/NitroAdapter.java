import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.function.Function;

/**
 * Created by ellioe03 on 23/06/2016.
 */
public class NitroAdapter {
    private static final int MAX_PID_LENGTH = 32;

    private final Function<ChannelHandlerContext, NettyBaseRequestExchange> exchangeFactory;
    private final Nitro nitro;
    private final NitroRespToAssetDocAdapter nitroRespToAssetDocAdapter;
    private final NitroRespToAvailabilityDocAdapter nitroRespToAvailabilityDocAdapter;
    private final NitroRespToAssetAndAvailabilityDocAdapter nitroRespToAssetAndAvailabilityDocAdapter;


    public NitroAdapter(final Function<ChannelHandlerContext, NettyBaseRequestExchange> exchangeFactory,
                        final Nitro nitro,
                        final NitroRespToAssetDocAdapter nitroRespToAssetDocAdapter,
                        final NitroRespToAvailabilityDocAdapter nitroRespToAvailabilityDocAdapter,
                        final NitroRespToAssetAndAvailabilityDocAdapter nitroRespToAssetAndAvailabilityDocAdapter) {
        this.exchangeFactory = exchangeFactory;
        this.nitro = nitro;
        this.nitroRespToAssetDocAdapter = nitroRespToAssetDocAdapter;
        this.nitroRespToAvailabilityDocAdapter = nitroRespToAvailabilityDocAdapter;
        this.nitroRespToAssetAndAvailabilityDocAdapter = nitroRespToAssetAndAvailabilityDocAdapter;


    }


    public void service(final ChannelHandlerContext ctx, final FullHttpRequest request) throws Exception {

        final NettyBaseRequestExchange exchange = this.exchangeFactory.apply(ctx);
    }
}
