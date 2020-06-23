package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * UI Klasse des BarZahlungsWerkzeugs
 * 
 * @author Johannes, Lars, Morten, Yagmur
 *
 */
public class BarZahlungsWerkzeugUI
{
    private int _platzPreis;
    private JDialog _dialog;
    private JLabel _zuZahlenLabel;
    private JLabel _zuZahlenBetragLabel;
    private JLabel _zuZahlenEuroLabel;
    private JLabel _rueckGeldLabel;
    private JLabel _rueckGeldBetragLabel;
    private JLabel _rueckGeldEuroLabel;
    private JLabel _eingabeLabel;
    private JLabel _eingabeEuroLabel;
    private JPanel _eingabeFeldPanel;
    private JTextField _eingabeFeld;
    private JButton _okButton;
    private JButton _abbrechenButton;

    /**
     * Initialisiert ein Exemplar der UI des BarZahlungsWerkzeugs
     * 
     * @param preis Der anzuzeigende Preis in Euro
     * 
     * @require preis >= 0
     */
    public BarZahlungsWerkzeugUI(int preis)
    {
        assert preis >= 0 : "Vorbedingung verletzt: preis >= 0";

        _platzPreis = preis;

        erstelleUI(_platzPreis);
    }

    /**
     * Erzeugt die UI mit dem übergebenen Preis
     * 
     * @param preis Der zu zahlende Preis in Euro
     *  
     */
    private void erstelleUI(int preis)
    {
        _dialog = new JDialog(new JDialog(), "Barzahlung", true);
        _dialog.setLayout(new GridBagLayout());
        _dialog.setSize(300, 200);
        _dialog.getContentPane()
            .setBackground(new Color(154, 154, 154));
        _dialog.setResizable(false);
        _dialog.setLocationRelativeTo(null);
        _dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        _zuZahlenLabel = new JLabel("Zu zahlen: ");
        _zuZahlenLabel.setForeground(new Color(224, 224, 224));
        _zuZahlenLabel.setBackground(new Color(32, 32, 32));
        _zuZahlenLabel.setOpaque(true);

        _zuZahlenBetragLabel = new JLabel(
                floatZuStringFormatted(centZuEuro(_platzPreis)));
        _zuZahlenBetragLabel.setForeground(new Color(224, 224, 224));
        _zuZahlenBetragLabel.setBackground(new Color(32, 32, 32));
        _zuZahlenBetragLabel.setOpaque(true);

        _eingabeLabel = new JLabel("Erhalten: ");
        _eingabeLabel.setForeground(new Color(224, 224, 224));
        _eingabeLabel.setBackground(new Color(32, 32, 32));
        _eingabeLabel.setOpaque(true);

        _eingabeFeld = new JTextField();
        _eingabeFeld.setHorizontalAlignment(JTextField.LEFT);
        _eingabeFeld.setBackground(new Color(96, 96, 96));
        _eingabeFeld.setForeground(new Color(224, 224, 224));
        _eingabeFeld
            .setBorder(BorderFactory.createLineBorder(new Color(32, 32, 32)));

        _eingabeFeldPanel = new JPanel();
        _eingabeFeldPanel.setLayout(new BorderLayout(2, 2));
        _eingabeFeldPanel.add(_eingabeFeld, BorderLayout.CENTER);
        _eingabeFeldPanel
            .setBorder(BorderFactory.createLineBorder(new Color(32, 32, 32)));
        _eingabeFeldPanel.setPreferredSize(new Dimension(0, 16));
        _eingabeFeldPanel.setOpaque(true);

        _rueckGeldLabel = new JLabel("Rueckgeld: ");
        _rueckGeldLabel.setForeground(new Color(224, 224, 224));
        _rueckGeldLabel.setBackground(new Color(32, 32, 32));
        _rueckGeldLabel.setOpaque(true);

        _rueckGeldBetragLabel = new JLabel(
                "-" + floatZuStringFormatted(centZuEuro(_platzPreis)));
        _rueckGeldBetragLabel.setForeground(new Color(255, 0, 0));
        _rueckGeldBetragLabel.setBackground(new Color(32, 32, 32));
        _rueckGeldBetragLabel.setOpaque(true);

        _zuZahlenEuroLabel = new JLabel("€");
        _zuZahlenEuroLabel.setForeground(new Color(224, 224, 224));
        _zuZahlenEuroLabel.setBackground(new Color(32, 32, 32));
        _zuZahlenEuroLabel.setOpaque(true);

        _rueckGeldEuroLabel = new JLabel("€");
        _rueckGeldEuroLabel.setForeground(new Color(224, 224, 224));
        _rueckGeldEuroLabel.setBackground(new Color(32, 32, 32));
        _rueckGeldEuroLabel.setOpaque(true);

        _eingabeEuroLabel = new JLabel("€");
        _eingabeEuroLabel.setForeground(new Color(224, 224, 224));
        _eingabeEuroLabel.setBackground(new Color(32, 32, 32));
        _eingabeEuroLabel.setOpaque(true);

        _okButton = new JButton("Ok");
        _okButton.setPreferredSize(new Dimension(100, 20));
        _okButton.setForeground(new Color(0, 0, 0));
        _okButton.setBackground(new Color(52, 235, 95));

        _abbrechenButton = new JButton("Abbrechen");
        _abbrechenButton.setPreferredSize(new Dimension(100, 20));
        _abbrechenButton.setForeground(new Color(0, 0, 0));
        _abbrechenButton.setBackground(new Color(235, 64, 52));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(21, 0, 11, 0);
        _dialog.add(_zuZahlenLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(21, 0, 11, 0);
        _dialog.add(_zuZahlenBetragLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(21, 0, 11, 0);
        _dialog.add(_zuZahlenEuroLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(11, 0, 11, 0);
        _dialog.add(_eingabeLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 10, 0);
        _dialog.add(_eingabeFeldPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(11, 0, 11, 0);
        _dialog.add(_eingabeEuroLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(11, 0, 11, 0);
        _dialog.add(_rueckGeldEuroLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(11, 0, 10, 0);
        _dialog.add(_rueckGeldLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(11, 0, 11, 0);
        _dialog.add(_rueckGeldBetragLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 5, 11, 5);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 4;
        _dialog.add(_okButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 5, 11, 5);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 4;
        _dialog.add(_abbrechenButton, c);

        //Von Anfang an deaktiviert (aktiviert sich nur bei richtiger Eingabe)
        _okButton.setEnabled(false);
    }

    /**
     * Zeigt den Dialog
     */
    public void zeigeDialog()
    {
        _dialog.setVisible(true);
    }

    /**
     * Schließt den Dialog
     */
    public void schließeUI()
    {
        _dialog.dispose();
    }

    /**
     * Rechnet den Cent-Betrag in Euro um (Int zu float)
     * @param preis der zu zahlende Preis
     * 
     * @return euro der Centbetrag in Euro
     *
     * @ensure result < 0;
     */
    public Float centZuEuro(int preis)
    {
        float euroBetrag = (float) preis / 100;
        return euroBetrag;
    }

    /**
     * Wandelt einen Float-Wert in einen String mit maximal zwei Nachkommastellen um
     * 
     * @param wert der Wert der umgewandelt werden soll
     * @return floatString Ein String der aus einem float wert konvertiert wurde
     * 
     * @ensure result >= 0
     */
    protected String floatZuStringFormatted(float wert)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        //Nur hinzugefügt, damit die Beträge alle gleich "aussehen" das im BarZahlungsUI. 
        //Beträge in DecimalFormart würden mit einem ',' getrennt sein, aber wir können nur '.' im Eingabefeld eingeben, 
        //da float mit einem '.' getrennt ist und wir nur so damit rechnen können
        DecimalFormatSymbols andereSymbole = new DecimalFormatSymbols(
                Locale.ENGLISH);
        andereSymbole.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(andereSymbole);
        String floatString = decimalFormat.format(wert);
        return floatString;
    }

    /**
     * Gibt den Ok Button zurück
     * @return der Ok Button
     * 
     * @ensure restult != null
     */
    public JButton getOkButton()
    {
        return _okButton;
    }

    /**
     * Gibt den Abbrechen Button zurück
     * @return der Abbrechen Button
     * 
     * @ensure result !=null
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * Gibt das Eingabefeld zurück
     * @return _eingabeFeld das Eingabefeld
     */
    public JTextField getEingabeFeld()
    {
        return _eingabeFeld;
    }

    /**
     * Gibt das Rueckgabe Label für den Geldbetrag zurück
     * @return _rueckGeldBetragLabel das Rueckgabe Geldbetrag Label
     */
    public JLabel getRueckgabeGeldbetragLabel()
    {
        return _rueckGeldBetragLabel;
    }

    /**
     * Fängt in der Eingabe alle eingaben außer Zahlen ab und öffnet dann ein Fenster in dem die Fehlermeldung steht.
     */
    public void fangFehlerAb()
    {
        JOptionPane.showMessageDialog(_dialog, "Bitte nur Zahlen eingeben!",
                "Eingabefehler", JOptionPane.ERROR_MESSAGE);
    }

}
