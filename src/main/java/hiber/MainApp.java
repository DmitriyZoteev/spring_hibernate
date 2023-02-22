package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"
              , new Car("Toyota Camry", 123)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"
              , new Car("BMW X1", 256)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"
              , new Car("Nissan Sentra", 412)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"
              , new Car("Nissan Juke", 785)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
         System.out.println();
      }

      User user = userService.getUserByCar("Nissan Sentra", 412);
      System.out.print("User с указанной машиной: ");
      if (user != null) {
         System.out.println(user.toString());
      } else {
         System.out.println("Отсутствует");
      }

      context.close();
   }
}
