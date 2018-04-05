package database.Database;

import database.Database.config.AbstractJavaFxApplicationSupport;
import database.Database.config.ConfigurationControllers;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class DBmain extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(DBmain.class, args);
//        SpringApplication.run(DBmain.class, args);
    }

    @Autowired
    private ConfigurationControllers.View view;

    @Override
    public void start(Stage stage) throws Exception{
        try {
            stage.setTitle("Телефонная сеть города");
            stage.setScene(new Scene(view.getView()));
            stage.setResizable(true);
            stage.centerOnScreen();
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
