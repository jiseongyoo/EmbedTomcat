import org.apache.catalina.LifecycleException;

public class EmbedTomcat {
    public static void main(String[] args) {
        try {
            new Application().appStart();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
