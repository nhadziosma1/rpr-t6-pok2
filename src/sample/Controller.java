package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

//import javax.xml.validation.Validator;   OVA METODA JE CINILA TAKO DA SE "Validator" POSMATRA KAO KLASA, A NE NAMA POTREBNI INTERFEJS
import javafx.stage.Stage;
import org.controlsfx.validation.Validator;


import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationSupport;

//import org.apache.commons.validator.routines.EmailValidator;    ??????????????? zasto ne radi

public class Controller implements Initializable
{
    public TextField unesiIme;
    public TextField unesiPrezime;
    public TextField unesiBrojIndeksa;
    public TextField unesiJMBG;
    public DatePicker unesiDatumRodjenja;
    public ComboBox unesiGrad;
    public TextField unesiAdresu;
    public TextField unesiTelefon;
    public TextField unesiEmail;
    public ChoiceBox unesiOdsjek;              /*ako ne stavis "ime.getSelectionModel().selectFirst()"
                                                 bit ce polje postaljeno na null, pa mozes provjeriti*/
    public ChoiceBox unesiGodinuStudija;
    public ChoiceBox unesiCiklusStudija;
    public RadioButton unesiStatusRedovan;
    public RadioButton unesiStatusSamofinansirajuci;
    public CheckBox unesiBorackiDa;

    @Override
    public void initialize(URL location, ResourceBundle resources)  /*pokrece se odmah pri kreiranju*/
    {  /*ovi parametri moraju postojati, jer je obavezno preklopiti ovu metodu kada implementiramo "Initializable"*/

        unesiGrad.setItems(FXCollections.observableArrayList("Sarajevo", "Tuzla", "Mostar", "Brcko", "Trebinje", "Zenica", "Banja Luka", "Novi Pazar", "Foca"));
        unesiGrad.getSelectionModel().selectFirst();
        unesiCiklusStudija.setItems(FXCollections.observableArrayList("Bachelor", "Master", "Doktorski studij", "Strucni studij"));
        unesiCiklusStudija.getSelectionModel().selectFirst();
        unesiGodinuStudija.setItems(FXCollections.observableArrayList("Prva", "Druga", "Treca"));
        /*
            radi vjezbe na oba nacina ce biti uradjeno ubacivanje, npr ovje cemo sotaviti prazno polje, pa provjeriti je li ista uneseno,
            a ne zadati odmah defaultno sta ce biti izabrano kao sto je uradjeno gore za CiklusStudija

            1. nacin:
            unesiGodinuStudija.getSelectionModel().selectFirst();
         */
        unesiOdsjek.setItems(FXCollections.observableArrayList("Elektroenergetika", "Autoamtika i elektronika", "Racunarstvo i informatika", "Telekomunikacije"));
        unesiOdsjek.getSelectionModel().selectFirst();

        /*svaka promjenjiva ima svoj StyleClass, klasa koja je direktno povezana sa CSSom*/
        /* ovako se bveze vise stavri iz FXMLa sa jednom stvari iz CSSa*/
        /*jedno polje moze imati vise StyleClassova*/
        unesiIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoImePrezime(n)) {
                    unesiIme.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiIme.getStyleClass().add("poljeIspravno");
                } else {
                    unesiIme.getStyleClass().removeAll("poljeIspravno");
                    unesiIme.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        unesiPrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoImePrezime(n)) {
                    unesiPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiPrezime.getStyleClass().add("poljeIspravno");
                } else {
                    unesiPrezime.getStyleClass().removeAll("poljeIspravno");
                    unesiPrezime.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        unesiBrojIndeksa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanBrojIndeksa(n)) {
                    unesiBrojIndeksa.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiBrojIndeksa.getStyleClass().add("poljeIspravno");
                } else {
                    unesiBrojIndeksa.getStyleClass().removeAll("poljeIspravno");
                    unesiBrojIndeksa.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        unesiAdresu.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnaAdresa(n)) {
                    unesiAdresu.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiAdresu.getStyleClass().add("poljeIspravno");
                } else {
                    unesiAdresu.getStyleClass().removeAll("poljeIspravno");
                    unesiAdresu.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        unesiTelefon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanTelefon(n)) {
                    unesiTelefon.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiTelefon.getStyleClass().add("poljeIspravno");
                } else {
                    unesiTelefon.getStyleClass().removeAll("poljeIspravno");
                    unesiTelefon.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        /*
        ne radi ni ovaj dio, zato jer ono gore ne radi???????????????????????????????????'''
        unesiEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String o, String n) {
                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(n)) {
                    emailField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailField.getStyleClass().add("poljeIspravno");
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });*/


        unesiGrad.valueProperty().addListener(new ChangeListener<String>()
        {                                                                                                         /*".valueProperty()"*/
            /*za ComboBox tip u kojem postoje predlozeni unosi, da bi se izvrsila provjera mora se koristiti metoda ".editorPropery()* koja
             vraca omotac za TextFielda preko kojeg se moze ispitivati ispravnost unosa kao i za normalan TextField */

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanGrad(n)) {
                    unesiGrad.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiGrad.getStyleClass().add("poljeIspravno");
                } else {
                    unesiGrad.getStyleClass().removeAll("poljeIspravno");
                    unesiGrad.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        unesiDatumRodjenja.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate o, LocalDate n) {
                if (validanDatum(n)) {
                    unesiDatumRodjenja.getStyleClass().removeAll("poljeNijeIspravno");
                    unesiDatumRodjenja.getStyleClass().add("poljeIspravno");
                } else {
                    unesiDatumRodjenja.getStyleClass().removeAll("poljeIspravno");
                    unesiDatumRodjenja.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        ToggleGroup group = new ToggleGroup();  /*objednjiava RadioButtene*/
        unesiStatusRedovan.setToggleGroup(group); /*isSelected metoda postoji*/
        unesiStatusSamofinansirajuci.setToggleGroup(group);
        unesiStatusRedovan.setSelected(true);

        ValidationSupport validation = new ValidationSupport();
        unesiIme.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiIme, Validator.createEmptyValidator("Prazno polje"));
            }
        });
        unesiPrezime.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiPrezime,Validator.createEmptyValidator("Prazno polje"));
            }
        });
        unesiJMBG.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiJMBG, Validator.createEmptyValidator("Prazno polje"));
            }
        });
        unesiEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiEmail,Validator.createEmptyValidator("Prazno polje"));
            }
        });
        unesiBrojIndeksa.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiBrojIndeksa,Validator.createEmptyValidator("Prazno polje"));
            }
        });
        unesiDatumRodjenja.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if(!n)
                    validation.registerValidator(unesiDatumRodjenja,Validator.createEmptyValidator("Prazno polje"));
            }
        });

    }///////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validanTelefon(String broj)
    {
        /*postavkom zadatka je zadato da je i prazno pole tacno, zbog toga ide "*" umjesto "+" */
        if(broj.matches("[0-9]+")==false)
            return false;

        return true;
    }

    private boolean validanEmail(String rijec)
    {
        if(rijec.contains("@")==false)
            return false;

        String prije_la = rijec.substring(0, rijec.indexOf('@'));
        String poslije_la = rijec.substring(rijec.indexOf('@'));


        if(poslije_la.contains(".")==false)
            return false;

        String extenzija = poslije_la.substring(poslije_la.indexOf('.'));

        extenzija=extenzija.substring(1);

        if(extenzija.matches("[a-z]+")==false)
            return false;

        return true;
    }

    private boolean validanGrad(String rijec)
    {
        /*kada stoji bez plusa podrazumijevalo bi se da se string "rijec" sastoji od jednog karaktera koji je u zadanom rasponu,
          dok sa plusaom podrazumijevamo da je jedan karakter ili vise, dok bi sa "*" i string koji nema nijedan iz intervala vratio true*/
        if(rijec.matches("[a-zA-Z]+")==false)
            return false;

        if(Character.isUpperCase(rijec.charAt(0))==false)
            return false;

        for(int i=1; i<rijec.length(); i++)
        {
            if(Character.isUpperCase(rijec.charAt(i))==true)
                return false;
        }
        return true;
    }

    private boolean validnaAdresa(String rijec)
    {
        if(rijec.length()==0)
            return false;

        if(rijec.contains(" ")==false)
            return false;

        String ime_ulice = rijec.substring(0, rijec.indexOf(' '));
        String broj_ulice = rijec.substring(rijec.indexOf(' ')+1, rijec.length());

        if(ime_ulice.matches("[a-zA-Z]+")==false || broj_ulice.matches("[0-9]+")==false)
            return false;

        return true;
    }

    private boolean validanDatum(LocalDate s)
    {
        Date date = new Date();
        Date d = Date.from(s.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        if(date.compareTo(d) < 0)
            return false;

        int dd = Integer.parseInt(unesiJMBG.getText(0, 2));  /*uzimanje substringa za iz tipa TextField*/
        int mm = Integer.parseInt(unesiJMBG.getText(2, 4));
        int ggg = Integer.parseInt(unesiJMBG.getText(4, 7));

        if(ggg != s.getYear()%1000)
            return false;

        if(mm != s.getMonthValue())
            return false;

        if(dd != s.getDayOfMonth())
            return false;

        return true;
    }

    private boolean validnoImePrezime(String rijec)
    {
        if(rijec.length()==0 || rijec.length()>20)
        return false;

        if(rijec.matches("[a-zA-Z]+")==false)
            return false;

        /* 1. nacin ispitivanja*/
        //if(rijec.charAt(0)<'A' || rijec.charAt(0)>'Z')
        //    return false;

        /* 2. nacin ispitivanja*/
        if(Character.isLowerCase(rijec.charAt(0)))
            return false;

        for(int i=1; i<rijec.length(); i++)
        {
            if(Character.isUpperCase(rijec.charAt(i)))
                return false;
        }

        return true;
    }

    /*Pravila za JMBG:
        Broj treba da se sastoji od 13 cifara u formi „DD MM GGG RR BBB K“ (bez bijelih mjesta), gdje su:
        DD – dan rođenja
        MM – mjesec rođenja
        GGG – zadnje tri cifre godine rođenja
        RR – politički regija rođenja*/
    private boolean validanJMBG(String rijec)
    {
        if(rijec.length()!=13)
            return false;

        if(rijec.matches("[0-9]+")==false) /*metoda za ispitivanje da li svi clanovi stringa odgovoaraju odredjeno rasponu  ASCII tabele*/
            return false;

        int dd = Integer.parseInt( rijec.substring(0,2) );
        int mm = Integer.parseInt( rijec.substring(2,4) );
        int ggg = Integer.parseInt( rijec.substring(4,7) );

        if( dd < 1 || dd > YearMonth.of(ggg, mm).lengthOfMonth() )
            return false;

        if(mm>12 || mm<0)
            return false;

        if(ggg>18 && ggg<850) /*ovo vrijedi samo za trenutnu situacija sa godinama*/
            return false;

        int k = Integer.parseInt(rijec.substring(12, 13));

        int l = 11 - ((7*(Integer.parseInt(unesiJMBG.getText(0,1)) + Integer.parseInt(unesiJMBG.getText(6,7))) +
                6*(Integer.parseInt(unesiJMBG.getText(1,2)) + Integer.parseInt(unesiJMBG.getText(7,8))) +
                5*(Integer.parseInt(unesiJMBG.getText(2,3)) + Integer.parseInt(unesiJMBG.getText(8,9))) +
                4*(Integer.parseInt(unesiJMBG.getText(3,4)) + Integer.parseInt(unesiJMBG.getText(9,10))) +
                3*(Integer.parseInt(unesiJMBG.getText(4,5)) + Integer.parseInt(unesiJMBG.getText(10,11))) +
                2*(Integer.parseInt(unesiJMBG.getText(5,6)) + Integer.parseInt(unesiJMBG.getText(11,12)))) % 11);


        if(l>=1 && l<=9 && l!=k)
            return false;

        if(l>9 && k!=0)
            return false;

        return true;
    }

    private boolean validanBrojIndeksa(String rijec)
    {
        if(rijec.matches("[0-9]+")==false)
            return false;

        return true;
    }

    private void upozori(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Greška");
        alert.setHeaderText("Forma nije validna!");
        alert.setContentText("Unesite ispravne podatke!");
        alert.showAndWait();
    }

    public void potvrdi(ActionEvent actionEvent)
    {
        if(!jeLiSveValidno())
            upozori(actionEvent);
        else {
            ispisiPodatke();
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }

    }

    private boolean jeLiSveValidno() {
        if(unesiIme.getText().length() == 0 || unesiPrezime.getText().length() == 0 || unesiBrojIndeksa.getText().length() == 0 ||
                unesiEmail.getText().length() == 0 || unesiJMBG.getText().length() == 0 || unesiDatumRodjenja.getValue() == null)
            return false;

        ArrayList<ObservableList<String>> validnost = new ArrayList<>();
        validnost.add(unesiIme.getStyleClass());
        validnost.add(unesiPrezime.getStyleClass());
        validnost.add(unesiJMBG.getStyleClass());
        validnost.add(unesiBrojIndeksa.getStyleClass());
        validnost.add(unesiAdresu.getStyleClass());
        validnost.add(unesiTelefon.getStyleClass());
        validnost.add(unesiEmail.getStyleClass());
        validnost.add(unesiDatumRodjenja.getStyleClass());
        validnost.add(unesiGrad.getStyleClass());

        for(ObservableList<String> o : validnost)
        {
            if(o.contains("poljeNijeIspravno"))
                return false;
        }
        return true;
    }

    private void ispisiPodatke()
    {
        System.out.println("Ime: " + unesiIme.getText());
        System.out.println("Prezime: " + unesiPrezime.getText());
        System.out.println("Broj indeksa: " + unesiBrojIndeksa.getText());
        System.out.println("JMBG: " + unesiJMBG.getText());
        System.out.println("Datum rođenja: " + unesiDatumRodjenja.getValue());
        System.out.println("Mjesto rođenja: " + unesiGrad.getValue());          /*".getValue()" se koristi za ComboBox, ChoiceBox*/

        if(!(unesiAdresu.getText().equals("")))
            System.out.println("Kontakt adresa: " + unesiAdresu.getText());

        if( !( unesiTelefon.getText().equals("") ) )
            System.out.println("Kontakt telefon: " + unesiTelefon.getText());

        System.out.println("Email adresa: " + unesiEmail.getText());
        System.out.println("Odsjek: " + unesiOdsjek.getValue());
        System.out.println("Godina studija: " + unesiGodinuStudija.getValue());
        System.out.println("Ciklus studija: " + unesiCiklusStudija.getValue());

        if( unesiStatusRedovan.isSelected() )
            System.out.println("Redovan student");

        if( unesiStatusSamofinansirajuci.isSelected() )
            System.out.println("Redovan samofinansirajući student");

        if( unesiBorackiDa.isSelected() )
            System.out.println("Pripada boračkoj kategoriji");
        else
            System.out.println("Ne pripada boračkoj kategoriji");
    }
}
