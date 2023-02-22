package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
//      TypedQuery<Car> query1 = sessionFactory.getCurrentSession().createQuery("from Car where model =: model and series =: series");
//      List<Car> carList = query1.setParameter("model", model).setParameter("series", series).getResultList();
//      if (carList.size() > 0) {
//         System.out.println(carList.get(0).getModel());
      TypedQuery<User> query2 = sessionFactory.getCurrentSession()
              .createQuery("from User where car.model =: carModel and car.series =: carSeries");
      List<User> userList = query2.setParameter("carModel", model).setParameter("carSeries", series).getResultList();
      if (userList.size() > 0) {
         return userList.get(0);
      }
      return null;
   }

}
