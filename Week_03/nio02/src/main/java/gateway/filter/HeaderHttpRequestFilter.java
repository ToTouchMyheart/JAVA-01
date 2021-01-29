package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter extends AbstractFilter {

    @Override
    public HttpRequestFilter filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        // 可以添加自定义的header
        fullRequest.headers().set("header-value", "head-key");
        return this.next;
    }
}
