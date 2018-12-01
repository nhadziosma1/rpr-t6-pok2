package sample;

import com.sun.org.apache.xpath.internal.operations.String;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class Controller
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

    public void initialize() /*pokrece se odmah pri kreiranju*/
    {
        unesiGrad.setItems(FXCollections.observableArrayList("Sarajevo", "Tuzla", "Mostar", "Brcko", "Trebinje", "Zenica", "Banja Luka"));
        unesiGrad.getSelectionModel().selectFirst();
        unesiCiklusStudija.setItems(FXCollections.observableArrayList("Bachelor", "Master", "Doktorski studij", "Strucni studij"));
        unesiCiklusStudija.getSelectionModel().selectFirst();
        unesiGodinuStudija.setItems(FXCollections.observableArrayList("Prva", "Druga", "Treca"));
        unesiGodinuStudija.getSelectionModel().selectFirst();
        unesiOdsjek.setItems(FXCollections.observableArrayList("Elektroenergetika", "Autoamtika i elektronika", "Racunarstvo i informatika", "Telekomunikacije"));
        unesiOdsjek.getSelectionModel().selectFirst();

        /*svaka promjenjiva ima svoj StyleClass, klasa koja je direktno povezana sa CSSom*/
        /* ovako se bveze vise stavri iz FXMLa sa jednom stvari iz CSSa*/
        /*jedno polje moze imati vise StyleClassova*/
        unesiIme.textProperty().addListener(new ChangeListener<String>()
        {
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

        unesiGrad.editorProperty().addListener(new ChangeListener<String>()
        {
            /*za ComboBox tip u kojem postoje predlozeni unosi, da bi se izvrsila provjera mora se koristiti metoda ".editorPropery()* koja
             vraca omotac za TextFielda preko kojeg se moze ispitivati ispravnost unosa kao i za normalan TextField */

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnaGrad(n)) {
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
        unesiStatusRedovan.setSelected(true);
        unesiStatusSamofinansirajuci.setToggleGroup(group);
    }///////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validnaGrad(String rijec)
    {
        if(rijec.matches("[a-zA-z]")==false)
            return false;

        if(Character.isLowerCase(rijec.charAt(0))==false)
            return false;

        for(int i=0; i<rijec.length(); i++)
        {
            if(Character.isUpperCase(rijec.charAt(i))==true)
                return false;
        }
    }

    private boolean validnaAdresa(String rijec)
    {
        if(rijec.length()==0)
            return false;

        if(rijec.contains(" ")==false)
            return false;

        String ime_ulice = rijec.substring(0, rijec.indexOf(' '));
        String broj_ulice = rijec.substring(rijec.indexOf(' ')+1, rijec.length());

        if(ime_ulice.matches("[a-zA-z]+")==false || broj_ulice.matches("[0-9]+")==false)
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

        for(int i=0; i<rijec.length(); i++)
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
}
