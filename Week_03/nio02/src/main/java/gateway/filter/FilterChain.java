package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author zhurui
 * @Date 2021/1/29 10:05 上午
 * @Version 1.0
 */
public class FilterChain {

    private HttpRequestFilter httpRequestFilter;

    public HttpRequestFilter add(HttpRequestFilter filter) {
        if (httpRequestFilter == null) {
            this.httpRequestFilter = filter;
        } else {
            this.httpRequestFilter.add(filter);
        }
        return filter;
    }

    public static FilterChain chain() {
        return new FilterChain();
    }

    public void doFilter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpRequestFilter filter = httpRequestFilter;
        while (filter != null) {
            filter.filter(fullRequest, ctx);
            filter = filter.next();
        }
    }

}
