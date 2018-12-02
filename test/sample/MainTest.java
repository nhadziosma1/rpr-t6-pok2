package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javax.security.auth.callback.ChoiceCallback;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class MainTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Main.class.getResource("sample.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
        stage.toFront();
    }

    @Test
    public void imeTacnoNaPocetku(FxRobot robot){
        robot.clickOn("#unesiIme");
        TextField imePolje = robot.lookup("#unesiIme").queryAs(TextField.class);
        assertEquals("",imePolje.getText());
    }

    @Test
    public void prezimeTacnoNaPocetku(FxRobot robot){
        robot.clickOn("#uensiPrezime");
        TextField prezimePolje = robot.lookup("#unesiPrezime").queryAs(TextField.class);
        assertEquals("",prezimePolje.getText());
    }

    @Test
    public void indeksTacanNaPocetku(FxRobot robot){
        robot.clickOn("#unesiBrojIndeksa");
        TextField indeksPolje = robot.lookup("#unesiBrojIndeksa").queryAs(TextField.class);
        assertEquals("",indeksPolje.getText());
    }

    @Test
    public void jmbgTacanNaPocetku(FxRobot robot){
        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        assertEquals("",jmbgPolje.getText());
    }

    @Test
    public void  kontaktAdresaTacnaNaPocetku(FxRobot robot)
    {
        robot.clickOn("#unesiAdresu");
        TextField kontaktAdresaPolje = robot.lookup("#unesiAdresu").queryAs(TextField.class);
        assertEquals("",kontaktAdresaPolje.getText());
    }

    @Test
    public void  kontaktTelefonTacanNaPocetku(FxRobot robot){
        robot.clickOn("#unesiTelefon");
        TextField kontaktTelefonPolje = robot.lookup("#unesiTelefon").queryAs(TextField.class);
        assertEquals("",kontaktTelefonPolje.getText());
    }

    @Test
    public void  datumRodjenjaTacanNaPocetku(FxRobot robot){
        robot.clickOn("#unesiDatumRodjenja");
        TextField datumRodjenjaPolje = robot.lookup("#unesiDatumRodjenja").queryAs(TextField.class);
        assertEquals("",datumRodjenjaPolje.getText());
    }

    @Test
    public void  emailTacanNaPocetku(FxRobot robot){
        robot.clickOn("#unesiEmail");
        TextField emailPolje = robot.lookup("#unesiEmial").queryAs(TextField.class);
        assertEquals("",emailPolje.getText());
    }

    @Test
    public void  imeTacnoOtkucano(FxRobot robot){
        robot.clickOn("#unesiIme");
        TextField imePolje = robot.lookup("#unesiIme").queryAs(TextField.class);
        robot.write("Nedzad");
        assertTrue( imePolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  imeNetacnoOtkucano(FxRobot robot){
        robot.clickOn("#unesiIme");
        TextField imePolje = robot.lookup("#unesiIme").queryAs(TextField.class);
        robot.write("Nedzad");
        assertAll("testiranje",
                () ->  { assertTrue( imePolje.getStyleClass().contains("poljeIspravno") ); },
                () ->  { robot.write("1");},
                () -> { assertTrue( imePolje.getStyleClass().contains("poljeNijeIspravno") ); },
                () ->  { robot.type(KeyCode.BACK_SPACE);},
                () ->  { robot.write("N");},
                () -> { assertTrue( imePolje.getStyleClass().contains("poljeNijeIspravno") ); },
                () -> {
                    while( imePolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write("nedzad");},
                () -> { assertTrue( imePolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );

    }

    @Test
    public void  prezimeTacnoOtkucano(FxRobot robot){
        robot.clickOn("#unesiPrezime");
        TextField prezimePolje = robot.lookup("#unesiPrezime").queryAs(TextField.class);
        robot.write("Hadziosmanovic");
        assertTrue( prezimePolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  prezimeNetacnoOtkucano(FxRobot robot)
    {
        robot.clickOn("#unesiPrezime");
        TextField prezimePolje = robot.lookup("#prezimeTekstPolje").queryAs(TextField.class);
        robot.write("Hadziosmanovic");
        assertAll("testiranje",
                () ->  { assertTrue( prezimePolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.write("1");},
                () -> { assertTrue( prezimePolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () ->  { robot.type(KeyCode.BACK_SPACE);},
                () ->  { robot.write("F");},

                () -> { assertTrue( prezimePolje.getStyleClass().contains("poljeNijeIspravno") ); },
                () -> {
                    while( prezimePolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write("hadz");},
                () -> { assertTrue( prezimePolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );
    }

    @Test
    public void  indeksTacnoOtkucan(FxRobot robot)
    {
        robot.clickOn("#unesiBrojIndeksa");
        TextField indeksPolje = robot.lookup("#unesiBrojIndeksa").queryAs(TextField.class);
        robot.write("18033");
        assertTrue( indeksPolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  indeksNetacnoOtkucan(FxRobot robot)
    {
        robot.clickOn("unesiBrojIndeksa");
        TextField indeksPolje = robot.lookup("#unesiBrojINdeksa").queryAs(TextField.class);
        robot.write("18033");
        assertAll("testiranje",
                () ->  { assertTrue( indeksPolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.write("1");},
                () -> { assertTrue( indeksPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () ->  { robot.type(KeyCode.BACK_SPACE);},
                () ->  { robot.write("a");},
                () -> { assertTrue( indeksPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( indeksPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write("18a31 ");},
                () -> { assertTrue( indeksPolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );


    }

    @Test
    public void  jmbgTacnoOtkucan(FxRobot robot)
    {
        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        robot.write("2908998170086");
        assertTrue( jmbgPolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  jmbgNetacnoOtkucan(FxRobot robot){
        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        robot.write("2908998170086");
        assertAll("testiranje",
                () ->  { assertTrue( jmbgPolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.write("1");},
                () -> { assertTrue( jmbgPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () ->  { robot.type(KeyCode.BACK_SPACE);},
                () ->  { robot.write("a");},
                () -> { assertTrue( jmbgPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( jmbgPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write(" 2908998a70086");},
                () -> { assertTrue( jmbgPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( jmbgPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () -> { assertTrue( jmbgPolje.getStyleClass().contains("poljeNeutralno") ); }

        );

    }

    @Test
    public void  kontaktTelefonTacnoOtkucan(FxRobot robot){
        robot.clickOn("#kontaktTelefonTekstPolje");
        TextField kontaktTelefonPolje = robot.lookup("#kontaktTelefonTekstPolje").queryAs(TextField.class);
        robot.write("060/338-1032");
        assertTrue( kontaktTelefonPolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  kontaktTelefonNetacnoOtkucan(FxRobot robot){
        robot.clickOn("#unesiTelefon");
        TextField kontaktTelefonPolje = robot.lookup("#unesiTelefon").queryAs(TextField.class);
        robot.write("246387658745");
        assertAll("testiranje",
                () ->  { assertTrue( kontaktTelefonPolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.write("2");},
                () -> { assertTrue( kontaktTelefonPolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.type(KeyCode.BACK_SPACE);},
                () ->  { robot.write("N");},
                () -> { assertTrue( kontaktTelefonPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( kontaktTelefonPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write(" 060338a1032");},
                () -> { assertTrue( kontaktTelefonPolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );
    }

    @Test
    public void  kontaktAdresaTacnoOtkucana(FxRobot robot){
        robot.clickOn("#unesiAdresu");
        TextField kontaktAdresaPolje = robot.lookup("#unesiAdresu").queryAs(TextField.class);
        robot.write("Cadrodzina 25");
        assertAll("testiranje",
                () -> { assertTrue( kontaktAdresaPolje.getStyleClass().contains("poljeIspravno") ); },
                () -> { robot.write("Cadordzina"); },
                () -> { assertTrue( kontaktAdresaPolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );
    }

    @Test
    public void  datumRodjenjaUOvisnostiOJMBGTacan(FxRobot robot){
        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        robot.write("2908998170086");
        robot.clickOn("#unesiDatumRodjenja");
        TextField datumRodjenjaPolje = robot.lookup("#unesiDatumRodjenja").queryAs(TextField.class);
        robot.write("29/08/1998");
        assertAll("testiranje",
                () -> { assertTrue(datumRodjenjaPolje.getStyleClass().contains("poljeIspravno")); },

                () -> { robot.type(KeyCode.BACK_SPACE); },
                () -> { robot.write("7"); },
                () -> { assertTrue(datumRodjenjaPolje.getStyleClass().contains("poljeNijeIspravno")); },

                () -> {
                    while( datumRodjenjaPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () -> { robot.write("290583495"); },
                () -> { assertTrue(datumRodjenjaPolje.getStyleClass().contains("poljeNijeIspravno")); }
        );
    }

    @Test
    public void  emailTacnoOtkucan(FxRobot robot){
        robot.clickOn("#unesiEmail");
        TextField emailPolje = robot.lookup("#unesiEmail").queryAs(TextField.class);
        robot.write("nedzad@hotmail.com");
        assertTrue( emailPolje.getStyleClass().contains("poljeIspravno") );
    }

    @Test
    public void  emailNetacnoOtkucan(FxRobot robot){
        robot.clickOn("#unesiEmail");
        TextField emailPolje = robot.lookup("#unesiEmail").queryAs(TextField.class);
        robot.write("nedzad@hotmail.com");
        assertAll("testiranje",
                () ->  { assertTrue( emailPolje.getStyleClass().contains("poljeIspravno") ); },

                () ->  { robot.write("1");},
                () -> { assertTrue( emailPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( emailPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () ->  { robot.write("nedzadc@.com");},
                () -> { assertTrue( emailPolje.getStyleClass().contains("poljeNijeIspravno") ); },

                () -> {
                    while( emailPolje.getText().length() != 0 )
                        robot.type(KeyCode.BACK_SPACE);
                },
                () -> { robot.write("@hotmail.com");},
                () -> { assertTrue( emailPolje.getStyleClass().contains("poljeNijeIspravno") ); }
        );
    }

    @Test
    public void testTacnogUnosa(FxRobot robot)
    {
        robot.clickOn("#unesiIme");
        TextField imePolja = robot.lookup("#unesiIme").queryAs(TextField.class);
        robot.write("Nedzad");
        robot.clickOn("#unesiPrezime");
        TextField prezimePolja = robot.lookup("#unesiPrezime").queryAs(TextField.class);
        robot.write("Hadziosmanovic");
        robot.clickOn("#unesiBrojIndeksa");
        TextField indeksPolje = robot.lookup("#unesiBrojIndeksa").queryAs(TextField.class);
        robot.write("18033");
        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        robot.write("2908998170086");
        robot.clickOn("#unesiDatumRodjenja");
        TextField datumPolje = robot.lookup("#unesiDatumRodjenja").queryAs(TextField.class);
        robot.write("29/08/1998");
        robot.clickOn("#unesiTelefon");
        TextField kontaktTelefonPolje = robot.lookup("#unesiTelefon").queryAs(TextField.class);
        robot.write("062624555");
        robot.clickOn("#unesiAdresu");
        TextField kontaktAdresaPolje = robot.lookup("#unesiAdresu").queryAs(TextField.class);
        robot.write("Cadordzina 21");
        robot.clickOn("#unesiEmial");
        TextField emailPolje = robot.lookup("#unesiEmial").queryAs(TextField.class);
        robot.write("nedzad@hotmail.com");
        robot.clickOn("#unesiGrad");
        ComboBox mjestoBox = robot.lookup("#unesiGrad").queryAs(ComboBox.class);
        robot.write("Sarajevo");
        robot.clickOn("#unesiOdsjek");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#unesiGodinuStudija");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#unesiCiklusStudija");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#unesiStatusRedovan");
        robot.clickOn("#unesiBorackiDa");

        robot.clickOn("#potvrdiBtn");
        assertAll("testiranje",

                () -> { assertTrue(imePolja.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(prezimePolja.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(indeksPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(jmbgPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(kontaktAdresaPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(kontaktTelefonPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(emailPolje.getStyleClass().contains("poljeIspravno")); },
                () -> {robot.clickOn("#unesiIme");},
                () -> { assertTrue(mjestoBox.getStyleClass().contains("poljeIspravno")); }
        );
    }

    @Test
    public void testNetacnogUnosa(FxRobot robot)
    {
        robot.clickOn("#unesiIme");
        TextField imePolja = robot.lookup("#unesiIme").queryAs(TextField.class);
        robot.write("nedzad");

        robot.clickOn("#unesiPrezime");
        TextField prezimePolja = robot.lookup("#unesiPrezime").queryAs(TextField.class);
        robot.write("hadziosmanovic");

        robot.clickOn("#unesiBrojIndeksa");
        TextField indeksPolje = robot.lookup("#unesiBrojIndeksa").queryAs(TextField.class);
        robot.write("18033");

        robot.clickOn("#unesiJMBG");
        TextField jmbgPolje = robot.lookup("#unesiJMBG").queryAs(TextField.class);
        robot.write("2908998170086");

        robot.clickOn("#unesiDatumRodjenja");
        TextField datumPolje = robot.lookup("#unesiDatumRodjenja").queryAs(TextField.class);
        robot.write("29/08/1998");

        robot.clickOn("#unesiTelefon");
        TextField kontaktTelefonPolje = robot.lookup("#unesiTelefon").queryAs(TextField.class);
        robot.write("0626a4555");

        robot.clickOn("#unesiAdresu");
        TextField kontaktAdresaPolje = robot.lookup("#unesiAdresu").queryAs(TextField.class);
        robot.write("Cadordzina");

        robot.clickOn("#unesiEmial");
        TextField emailPolje = robot.lookup("#unesiEmial").queryAs(TextField.class);
        robot.write("nedzad@hotmail");

        robot.clickOn("#unesiGrad");
        ComboBox mjestoBox = robot.lookup("#unesiGrad").queryAs(ComboBox.class);
        robot.write("sarajevo");

        robot.clickOn("#unesiOdsjek");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#unesiGodinuStudija");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#unesiCiklusStudija");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#unesiStatusRedovan");
        robot.clickOn("#unesiBorackiDa");

        robot.clickOn("#potvrdiBtn");
        assertAll("testiranje",

                () -> { assertTrue(imePolja.getStyleClass().contains("poljeNijeIspravno")); },
                () -> { assertTrue(prezimePolja.getStyleClass().contains("poljeNijeIspravno")); },
                () -> { assertTrue(indeksPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(jmbgPolje.getStyleClass().contains("poljeIspravno")); },
                () -> { assertTrue(kontaktAdresaPolje.getStyleClass().contains("poljeNijeIspravno")); },
                () -> { assertTrue(kontaktTelefonPolje.getStyleClass().contains("poljeNijeIspravno")); },
                () -> { assertTrue(emailPolje.getStyleClass().contains("poljeNijeIspravno")); }
        );
    }


}