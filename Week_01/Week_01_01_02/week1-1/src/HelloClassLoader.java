import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @Author zhurui
 * @Date 2021/1/7 9:32 上午
 * @Version 1.0
 */
public class HelloClassLoader extends ClassLoader {


    public static void main(String[] args) throws Exception {
        Class<?> hello = new HelloClassLoader().findClass("Hello");

        System.out.println("类加载器是:" + hello.getClassLoader());
        Method[] declaredMethods = hello.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
        //利用反射获取main方法
        hello.getDeclaredMethod("hello").invoke(hello.newInstance());
    }


    private byte[] readFile(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();
        return data;
    }

    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    @Override
    protected Class<?> findClass(String name) {
        // String helloBase64 = "yv66vgAAADoAIQoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsIAA4BABdqdm0uSGVsbG8sIGNsYXNzTG9hZGVyIQoAEAARBwASDAATABQBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVggAFgEABWhlbGxvBwAYAQAJanZtL0hlbGxvAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAAtManZtL0hlbGxvOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEAIQAXAAIAAAAAAAMAAQAFAAYAAQAZAAAALwABAAEAAAAFKrcAAbEAAAACABoAAAAGAAEAAAAGABsAAAAMAAEAAAAFABwAHQAAAAEAFgAGAAEAGQAAADcAAgABAAAACbIABxINtgAPsQAAAAIAGgAAAAoAAgAAAA4ACAAPABsAAAAMAAEAAAAJABwAHQAAAAgAHgAGAAEAGQAAACUAAgAAAAAACbIABxIVtgAPsQAAAAEAGgAAAAoAAgAAAAkACAAKAAEAHwAAAAIAIA==";
        //byte[] bytes = this.decode(helloBase64);
        byte[] bytes = new byte[0];
        try {
            bytes = this.readFile(this.getClass().getClassLoader().getResource("Hello.xlass").getPath());
            byte temp;
            for (int i = 0; i < bytes.length; i++) {
                temp = bytes[i];
                bytes[i] = (byte) (~temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
