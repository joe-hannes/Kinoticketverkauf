package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

/**
 * Ein Werkzeug um Barzahlungen an einer Kasse zu simulieren
 * @author Johannes ,Lars, Morten, Yagmur
 */
public class BarZahlungsWerkzeug extends ObservableSubwerkzeug
{
    BarZahlungsWerkzeugUI _ui;
    private int _preis;

    /**
     * Initialisiert ein Exemplar des BarZahlungsWerkzeugs
     * @param preis Der Preis der zu verkaufenden Plätze in Cent - wird später in Euro-Beträge umgewandelt
     */
    public BarZahlungsWerkzeug(int preis)
    {
        _preis = preis;
        _ui = new BarZahlungsWerkzeugUI(_preis);

    }

    /**
     * Initialisiert die UI Aktionen und zeigt den Dialog
     */
    public void initialisiereUI()
    {
        registriereUIAktionen();
        _ui.zeigeDialog();
    }

    /**
     * Konvertiert einen String zu einen Doublewert
     * 
     * @param stringBetrag der Geldbetrag als String im Textfeld
     * 
     * @return den Geldbetrag als double
     * 
     * @ensure result >= null
     */
    private float stringZuFloat(String stringBetrag)
    {
        return Float.parseFloat(stringBetrag);
    }

    /**
     * Reagiert auf den Abbrechen-Button und schließt den Dialog
     */
    protected void reagiereAufAbbrechenButton()
    {
        _ui.schließeUI();
    }

    /**
     * Reagiert auf die Eingabe im Textfeld. Bei einem kleineren Eingabewert, als der Preis,
     * wird das Rueckgeld in Rot angezeigt und mit einem "-" davor. 
     * Wenn der Eingabewert größer gleich dem Preis ist dann wird das Rueckgeld grün angezeigt
     * 
     */

    private void reagiereAufEingabe()
    {
        String eingabe = _ui.getEingabeFeld()
            .getText();

        float preisInEuro = _ui.centZuEuro(_preis);

        if (eingabe.matches("[0-9]{1,}(\\.)?[0-9]*") && !eingabe.isEmpty())
        {
            float differenz = stringZuFloat(eingabe);
            float restPreis = differenz - preisInEuro;

            if (differenz >= preisInEuro)
            {
                //Wenn der zu zahlende Betrag 
                _ui.getRueckgabeGeldbetragLabel()
                    .setForeground(new Color(35, 175, 35));
                _ui.getRueckgabeGeldbetragLabel()
                    .setText(_ui.floatZuStringFormatted(restPreis));

                _ui.getOkButton()
                    .setEnabled(true);
            }
            else
            {
                _ui.getRueckgabeGeldbetragLabel()
                    .setForeground(new Color(255, 0, 0));
                _ui.getRueckgabeGeldbetragLabel()
                    .setText(_ui.floatZuStringFormatted(restPreis));

                _ui.getOkButton()
                    .setEnabled(false);
            }
        }
        else if (_ui.getEingabeFeld()
            .getText()
            .isEmpty())
        {
            _ui.getRueckgabeGeldbetragLabel()
                .setForeground(new Color(255, 0, 0));
            _ui.getRueckgabeGeldbetragLabel()
                .setText("-" + _ui.floatZuStringFormatted(preisInEuro));

            _ui.getOkButton()
                .setEnabled(false);
        }
        else
        {
            _ui.fangFehlerAb();
            //Leert das Eingabefeld nach fehlerhafter Eingabe. Beugt ungewolltes auftreten der Fehlermeldung vor
            _ui.getEingabeFeld()
                .setText("");

            _ui.getOkButton()
                .setEnabled(false);
        }
    }

    /**
     * Registrtiert die UI Aktionen
     */
    private void registriereUIAktionen()
    {
        _ui.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    _ui.schließeUI();
                }
            });

        _ui.getOkButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    informiereUeberAenderung();
                    _ui.schließeUI();

                }
            });

        _ui.getEingabeFeld()
            .addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e)
                {
                    reagiereAufEingabe();
                }

                @Override
                public void keyReleased(KeyEvent e)
                {
                    reagiereAufEingabe();
                }

                @Override
                public void keyPressed(KeyEvent e)
                {
                    reagiereAufEingabe();

                }
            });

    }

}
