package de.uni_hamburg.informatik.hci.id.TemperaturConverter.View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Enum.ModelUpdate;
import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Enum.ViewUpdate;
import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Model.TemperatureModel;

/**
 * Die View des TemperaturConverters stellt die Schnittstelle zur Verfügung, um
 * dem Model bei Änderungen die neuen Werte zu übergeben.
 * 
 * @author Daniel Sinn
 * @version 16.06.14
 */
public class TemperatureView extends Observable implements Observer
{

    private JTextField _fahrenheitText = new JTextField("32", 3);
    private JTextField _celsiusText = new JTextField("0", 3);
    private JSlider _fahrenheitSlider = new JSlider(JSlider.VERTICAL, -40, 158,
            32);
    private JSlider _celsiusSlider = new JSlider(JSlider.VERTICAL, -40, 70, 0);
    private JButton _nachCelsiusUmwandeln = new JButton("Convert2Celsius ->");
    private JButton _nachFahrenheitUmwandeln = new JButton(
            "<- Convert2Fahrenheit");

    private JFrame _frame;
    private boolean _aenderungNotwendig = false;

    public TemperatureView()
    {
        _frame = new JFrame();

        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // slider layout
        _celsiusSlider.setMajorTickSpacing(25);
        _celsiusSlider.setMinorTickSpacing(5);
        _celsiusSlider.setPaintTicks(true);
        _celsiusSlider.setPaintLabels(true);
        _celsiusSlider.setName("CelsiusSlider");

        _fahrenheitSlider.setMajorTickSpacing(40);
        _fahrenheitSlider.setMinorTickSpacing(5);
        _fahrenheitSlider.setPaintTicks(true);
        _fahrenheitSlider.setPaintLabels(true);
        _fahrenheitSlider.setName("FahrenheitSlider");

        // UI design
        content.add(_fahrenheitSlider);
        content.add(new JLabel("Fahrenheit"));
        content.add(_fahrenheitText);
        content.add(_nachCelsiusUmwandeln);
        content.add(_nachFahrenheitUmwandeln);
        content.add(_celsiusText);
        content.add(new JLabel("Celsius"));
        content.add(_celsiusSlider);
        _frame.setContentPane(content);
        _frame.pack();
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setTitle("Temperature Converter - MVC-Model");

        // event listener for interactive elements
        registriereListener();
    }

    /**
     * Gibt den aktuellen Wert des Textfeldes zur Fahrenheitsanzeige zurück.
     * 
     * @return Temperatur in Fahrenheit: °F
     */
    public double gibFahrenheitText()
    {
        return Double.valueOf(_fahrenheitText.getText());
    }

    /**
     * Gibt den aktuellen Wert des Textfeldes zur Celsiusanzeige zurück.
     * 
     * @return Temperatur in Celsius: °C
     */
    public double gibCelsiusText()
    {
        return Double.valueOf(_celsiusText.getText());
    }

    /**
     * Gibt den aktuellen Wert des Sliders zur Fahrenheitsanzeige zurück.
     * 
     * @return Temperatur in Fahrenheit: °F
     */
    public double gibFahrenheitSlider()
    {
        return _fahrenheitSlider.getValue();
    }

    /**
     * Gibt den aktuellen Wert des Sliders zur Celsiusanzeige zurück.
     * 
     * @return Temperatur in Celsius: °C
     */
    public double gibCelsiusSlider()
    {
        return _celsiusSlider.getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable arg0, Object arg1)
    {
        if (_aenderungNotwendig)

        {
            if (arg1.equals(ModelUpdate.Celsius))
            {
                setzeCelsiusText(((TemperatureModel) arg0).gibCelsius());
                setzeCelsiusSlider(((TemperatureModel) arg0).gibCelsius());
            }
            else if (arg1.equals(ModelUpdate.Fahrenheit))
            {
                setzeFahrenheitText(((TemperatureModel) arg0).gibFahrenheit());
                setzeFahrenheitSlider(((TemperatureModel) arg0).gibFahrenheit());
            }
        }

    }

    /**
     * Macht das Fenster sichtbar, welches diese View benutzt, sichtbar.
     */
    public void sichtbarMachen()
    {
        _frame.setVisible(true);

    }

    /**
     * Beendet die Änderungen. Wird nach Abschluss einer Änderung aufgerufen, um
     * eine Kettenreaktion von Aktualisierungen zu vermeiden.
     */
    public void beendeAenderungen()
    {
        _aenderungNotwendig = false;
    }

    private void setzeCelsiusText(double celsius)
    {
        _celsiusText.setText(String.valueOf(Math.round((celsius))));
    }

    private void setzeFahrenheitText(double fahrenheit)
    {
        _fahrenheitText.setText(String.valueOf(Math.round((fahrenheit))));
    }

    private void setzeFahrenheitSlider(double fahrenheit)
    {
        if (_aenderungNotwendig)
            _fahrenheitSlider.setValue((int) fahrenheit);
    }

    private void setzeCelsiusSlider(double celsius)
    {
        if (_aenderungNotwendig)
            _celsiusSlider.setValue((int) celsius);

    }
    
    
    private void registriereListener()
    {
        _nachCelsiusUmwandeln.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (!_aenderungNotwendig)
                {
                    _aenderungNotwendig = true;
                    setChanged();
                    notifyObservers(ViewUpdate.CelsiusText);
                }

            }
        });
        _nachFahrenheitUmwandeln.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (!_aenderungNotwendig)
                {
                    _aenderungNotwendig = true;
                    setChanged();
                    notifyObservers(ViewUpdate.FahrenheitText);

                }

            }
        });
        _fahrenheitSlider.addChangeListener(new ChangeListener()
        {

            public void stateChanged(ChangeEvent e)
            {
                if (!_aenderungNotwendig)
                {
                    _aenderungNotwendig = true;
                    setChanged();
                    notifyObservers(ViewUpdate.FahrenheitSlider);

                }
            }
        });
        _celsiusSlider.addChangeListener(new ChangeListener()
        {

            public void stateChanged(ChangeEvent e)
            {
                if (!_aenderungNotwendig)
                {
                    _aenderungNotwendig = true;
                    setChanged();
                    notifyObservers(ViewUpdate.CelsiusSlider);

                }

            }
        });

    }
}
