import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServletExample extends HttpServlet {
    public static final String UTF_8 = "UTF-8";
    private String contextPath = "/example";
    private String servletName = "example";

    // Servlet은 기본적으로 ISO-8859-1 인코딩을 사용한다
    // 따라서 UTF-8을 사용하는 것을 명시적으로 알려줘야 한다
    private String getDataStringInUTF8(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding(UTF_8);
        BufferedReader bufferedReader = request.getReader();
        Stream<String> lines = bufferedReader.lines();
        Collector<CharSequence, ?, String> collectors = Collectors.joining(System.lineSeparator());
        return lines.collect(collectors);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(getDataStringInUTF8(request));

        response.setCharacterEncoding(UTF_8);
        response.setContentType("text/plain");
        try (Writer writer = response.getWriter()) {
            writer.write("Hello, This is example!");
            writer.flush();
        }
    }

    public String getContextPath() {
        return this.contextPath;
    }

    public String getServletName() {
        return this.servletName;
    }
}
