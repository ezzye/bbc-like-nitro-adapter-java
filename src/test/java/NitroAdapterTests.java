import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ellioe03 on 23/06/2016.
 */


@RunWith(MockitoJUnitRunner.class)
public class NitroAdapterTests {

    private static final String pid = "Test";

    @Mock private ChannelHandlerContext ctx;
    @Mock private Function<ChannelHandlerContext,NettyBaseRequestExchange> exchangeFactory;
    @Mock private FullHttpRequest request;

    @Mock private Nitro nitro;
    @Mock private NitroRespToAssetDocAdapter nitroRespToAssetDocAdapter;
    @Mock private NitroRespToAvailabilityDocAdapter nitroRespToAvailabilityDocAdapter;
    @Mock private NitroRespToAssetAndAvailabilityDocAdapter nitroRespToAssetAndAvailabilityDocAdapter;
    @Mock private NettyBaseRequestExchange exchange;
    private NitroAdapter underTest;

    @Before
    public void setup(){
        underTest = new NitroAdapter(exchangeFactory,nitro,nitroRespToAssetDocAdapter,nitroRespToAvailabilityDocAdapter,nitroRespToAssetAndAvailabilityDocAdapter);
        when(exchangeFactory.apply(ctx)).thenReturn(exchange);
    }

    @Test
    public void itShouldRejectNonGetRequests() throws Exception {
        when(request.getMethod()).thenReturn(HttpMethod.POST);
        underTest.service(ctx, request);

        verify(exchange).handleError()
    }



}
