import database.scripts.FillDBScript;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            org.hibernate.service.ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.configure().getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.addResource("hibernate.cfg.xml").buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {

//        new MainWindow().setVisible(true);
        new FillDBScript(getSession(), "/home/malyutik/Downloads/HSK_Level_6_(New_HSK).xls");

//        try {
////            System.out.println("querying all the managed entities...");
//            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
//            for (Object key : metadataMap.keySet()) {
//                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//                final String entityName = classMetadata.getEntityName();
////                System.out.println(entityName);
//                final Query query = session.createQuery("from " + entityName);
//
////                System.out.println(sheet0.getCell(3,95).getContents());
////                System.out.println(HSK1xls.getNumberOfSheets());
////                System.out.println("executing: " + query.getQueryString());
////                for (Object o : query.list()) {
////                    System.out.println("  " + o.toString());
////                }
//            }
//        } finally {
//
//            session.flush();
//            session.close();
//        }
    }
}
