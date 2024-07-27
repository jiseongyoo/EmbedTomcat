import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Application {
    public void appStart() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        ServletExample servletExample = new ServletExample();
        String servletName = servletExample.getServletName();
        String servletContextPath = servletExample.getContextPath();

        Context context = tomcat.addContext(servletContextPath, new File(".").getAbsolutePath());
        Tomcat.addServlet(context, servletName, servletExample);
        context.addServletMappingDecoded("/*", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
