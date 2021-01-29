package gateway.filter;

/**
 * @Author zhurui
 * @Date 2021/1/29 10:10 上午
 * @Version 1.0
 */
public abstract class AbstractFilter implements HttpRequestFilter {

    protected HttpRequestFilter next;


    @Override
    public HttpRequestFilter add(HttpRequestFilter filter) {
        this.next = filter;
        return this.next;
    }

    @Override
    public HttpRequestFilter next() {
        return this.next;
    }
}
