package murach.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static final String dbPassword = System.getenv("DATASOURCE_PASSWORD");

    // Sử dụng giá trị trong EntityManagerFactory
//    private static final Map<String, String> properties = new HashMap<>();
//    static {
//    	System.out.println(dbPassword);
//        properties.put("javax.persistence.jdbc.password", dbPassword);
//    }
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emailListPU");

	public static EntityManagerFactory getEmFactory() {
		System.out.println(dbPassword);
		return emf;
	}
}