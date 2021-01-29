package gateway.filter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.internal.StringUtil;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class DefaultHttpRequestFilter extends AbstractFilter {

    List<String> blackIps = Arrays.asList("10.12.21.48");
    @Override
    public HttpRequestFilter filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String ip = fullRequest.headers().get("X-Forwarded-For");
        if (StringUtil.isNullOrEmpty(ip)) {
            InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            ip = socketAddress.getAddress().getHostAddress();
        }

        if (blackIps.contains(ip)) {
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN, Unpooled.wrappedBuffer("{\"msg\":\"failed\"}".getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", "{\"msg\":\"failed\"}".getBytes(StandardCharsets.UTF_8).length);
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            ctx.flush();
        }
        return this.next;
    }
}
