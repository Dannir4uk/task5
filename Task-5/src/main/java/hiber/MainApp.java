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

      Car car1 = new Car("BMW", 2022);
      Car car2 = new Car("Audi", 2023);
      Car car3 = new Car("MB", 2024);

      userService.add(new User("Damir","Iafizov", "Damir@mail.ru", car1));
      userService.add(new User("Adam", "Immanaliev", "Adam@mail.ru", car2));
      userService.add(new User("Iakov", "Rojkov", "Iakov@mail.ru", car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      User user = userService.getUserWithCar("BMW", 2022);
      System.out.println("Владелец машины BMW 2022 года?");
      System.out.println("-" + user.getFirstName() + " " + user.getLastName());
      System.out.println("Email = " + user.getEmail());

      context.close();
   }
}
