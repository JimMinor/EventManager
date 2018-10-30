package Main;

import Control.CambiaStage;
import Model.Impiegato;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class mainApp extends Application {
    private Stage stagePrincipale;
    private CambiaStage cambiaStagePrincipale;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStagePrincipale() {
        return stagePrincipale;
    }


    /**
     * Carica il primo stage con la schermata di login
     */
    @Override
    public void start(Stage stagePrincipale) {

        System.out.println("hey");
        this.stagePrincipale = stagePrincipale;
        cambiaStagePrincipale = new CambiaStage(this);
        cambiaStagePrincipale.mostraStageLogin();
        stagePrincipale.show();
    }


}
