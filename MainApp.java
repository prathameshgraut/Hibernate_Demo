package Demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
	public static void main(String[] args) {

		Employee emp = new Employee();
		emp.setName("Yash");
		emp.setCity("Tandulwadi");

		Configuration cfg = new Configuration().configure("Demo/hibernateConfig.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ses = sf.openSession();
		Transaction tra = ses.beginTransaction();

		// ------------------------|| Insert Operation ||--------------------------
		try {
			ses.save(emp);
			tra.commit(); // save permantly
			System.out.println("------|| User Added Successfully!! ||------");

		} catch (Exception e) {
			tra.rollback();
			e.printStackTrace();
			System.out.println("------|| User Added Not Successfully Due To The Error..! ||------");
		}

		// ------------------------|| Select Operation ||---------------------------
		try {
			Employee emp1 = ses.get(Employee.class, 5);
			if (emp1 != null) {
				System.out.println("------|| Show The Table ||------");
				System.out.println(emp1.getID());
				System.out.println(emp1.getName());
				System.out.println(emp1.getCity());
			} else {
				System.out.println("------|| User Not Found ||------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------------------|| Updated Successfully ||----------------------
		

		try {
			Employee emp2 = ses.get(Employee.class, 4);
			emp2.setID(4);
			emp2.setName("Omkar");
			
			System.out.println("------|| Updated Successfylly! ||------");
			ses.saveOrUpdate(emp2);
			tra.commit();
			System.out.println(emp2.getID() + emp2.getName() + emp2.getCity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// --------------------|| Delete Successfully ||----------------------
		try {
		Employee emp3=new Employee();
		emp3.setID(5);
		
		ses.delete(emp3);
		tra.commit();
		System.out.println("------|| Deleted Successfully ||------");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------|| Deleted Not Successfully ||------");
		}
	}

}
