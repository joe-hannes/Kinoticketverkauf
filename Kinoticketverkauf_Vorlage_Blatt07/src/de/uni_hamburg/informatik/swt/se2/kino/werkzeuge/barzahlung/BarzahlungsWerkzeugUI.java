package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * 
 */
public class BarzahlungsWerkzeugUI
{
    private JLabel _zuZahlen;
    private JLabel _erhalten;
    private JLabel _rueckgeld;
    private JLabel _emptyLabel;
    private JLabel _emptyLabel2;
    private JDialog _window;
    private JPanel _buttonPanel;
    private JPanel _eingabePanel;
    //    private JLabel _buttonLabel;
    //    private JLabel _eingabeLabel;
    private JTextField _tfErhalten;

    private JButton _okButton;
    private JButton _btnCancel;

    /**
     * 
     * @param preis
     */
    public BarzahlungsWerkzeugUI(int preis)
    {

        _window = new JDialog();

        //initializeLabels();
        erstelleButtons();

        initializeLabels();

        erstellePanels();

        erstelleTextField();

        initializieTextField();

        initializePanels();

        layoutManager();

        _window.setSize(500, 300);
        _window.setVisible(true);

    }

    private void layoutManager()
    {
        _window.setLayout(new GridLayout(2, 1));
        _window.add(_eingabePanel);
        _window.add(_buttonPanel);
    }

    private void initializieTextField()
    {
        //        _tfErhalten.setPreferredSize(new Dimension(100, 50));
    }

    private void erstelleTextField()
    {
        _tfErhalten = new JTextField();
        _tfErhalten.setPreferredSize(new Dimension(50, 25));
    }

    private void initializeLabels()
    {
        _zuZahlen = new JLabel("Zu Zahlen: ");
        _erhalten = new JLabel("Erhalten: ");
        _rueckgeld = new JLabel("Rückgeld: ");
        _emptyLabel = new JLabel(" 500");
        _emptyLabel2 = new JLabel("800");
    }

    private void initializePanels()
    {
        GridBagConstraints constraints;

        _eingabePanel.setLayout(new GridLayout(2, 3));

        _eingabePanel.add(_zuZahlen);
        _eingabePanel.add(_emptyLabel);

        _eingabePanel.add(_erhalten);
        _eingabePanel.add(_tfErhalten);

        _eingabePanel.add(_rueckgeld);
        _eingabePanel.add(_emptyLabel2);

        //        _buttonPanel.setLayout(new GridLayout(1, 2));
        //        _buttonPanel.add(_okButton);
        //
        //        _buttonPanel.add(_btnCancel);

        _eingabePanel.setLayout(new GridBagLayout());
        _eingabePanel.add(_tfErhalten);

        _buttonPanel.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 5, 2, 0), 0, 0);
        _buttonPanel.add(_okButton, constraints);

        constraints = new GridBagConstraints(500, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 5, 2, 0), 0, 0);
        _buttonPanel.add(_btnCancel, constraints);
    }

    private void erstellePanels()
    {
        _eingabePanel = new JPanel();
        _buttonPanel = new JPanel();
    }

    private void erstelleButtons()
    {
        _okButton = new JButton("Ok");
        _okButton.setPreferredSize(new Dimension(75, 75));
        _okButton.setBackground(new Color(52, 235, 95));
        _btnCancel = new JButton("Abbrechen");
        _btnCancel.setPreferredSize(new Dimension(300, 75));
        _btnCancel.setBackground(new Color(235, 64, 52));
    }
    //    private void initializeLabels()
    //    {
    //        _eingabeLabel = new JLabel("preis");
    //        _eingabeLabel.setPreferredSize(new Dimension(20, 20));
    //
    //        _buttonLabel = new JLabel("test");
    //        _buttonLabel.setPreferredSize(new Dimension(20, 20));
    //
    //    }

}
