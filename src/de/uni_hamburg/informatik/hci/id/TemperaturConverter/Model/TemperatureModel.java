package de.uni_hamburg.informatik.hci.id.TemperaturConverter.Model;

import java.util.Observable;

import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Enum.ModelUpdate;

/**
 * Das Model beinhaltet die fachliche Logik für die Temperatur in Fahrenheit und
 * Celsius. Das Model reagiert auf Änderungen, welche von dem Controller
 * angestoßen werden. Wird einer der Werte geändert, wird der andere angepasst.
 * Über diese Änderungen wird die View informiert, damit die Anzeige angepasst
 * werden kann.
 * 
 * @author Daniel Sinn
 * @version 16.06.14
 */
public class TemperatureModel extends Observable
{

    private double _fahrenheit = 32.0;
    private double _celsius = 0.0;

    /**
     * Gibt die Temperatur in Grad Fahrenheit aus.
     * 
     * @return Temperatur in Fahrenheit: °F
     */
    public double gibFahrenheit()
    {
        return _fahrenheit;
    }

    /**
     * Gibt die Temperatur in Grad Celsius aus.
     * 
     * @return Temperatur in Celsius: °C
     */
    public double gibCelsius()
    {
        return _celsius;

    }

    /**
     * Ändert den Fahrenheit-Wert des Models und passt den Celsius-Wert
     * anschließend an.
     * 
     * @param fahrenheit
     *            Der neue Wert für Fahrenheit
     */
    public void setzeFahrenheit(double fahrenheit)
    {
        _fahrenheit = fahrenheit;
        setChanged();
        notifyObservers(ModelUpdate.Fahrenheit);
        aktualisiereCelsius();
    }

    /**
     * Ändert den Celsius-Wert des Models und passt den Fahrenheits-Wert
     * anschließend an.
     * 
     * @param celsius
     *            Der neue Wert für Celsius
     */
    public void setzeCelsius(double celsius)
    {
        _celsius = celsius;
        setChanged();
        notifyObservers(ModelUpdate.Celsius);
        aktualisiereFahrenheit();
    }

    private void aktualisiereCelsius()
    {
        _celsius = ((_fahrenheit - 32.0) * 5.0 / 9.0);
        setChanged();
        notifyObservers(ModelUpdate.Celsius);
    }

    private void aktualisiereFahrenheit()
    {
        _fahrenheit = (_celsius * 1.8) + 32;
        setChanged();
        notifyObservers(ModelUpdate.Fahrenheit);
    }

}