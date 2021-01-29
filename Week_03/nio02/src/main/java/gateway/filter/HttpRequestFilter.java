package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    HttpRequestFilter add(HttpRequestFilter filter);

    HttpRequestFilter next();

    HttpRequestFilter filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
