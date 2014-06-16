package de.uni_hamburg.informatik.hci.id.TemperaturConverter.Controller;

import java.util.Observable;
import java.util.Observer;

import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Enum.ViewUpdate;
import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Model.TemperatureModel;
import de.uni_hamburg.informatik.hci.id.TemperaturConverter.View.TemperatureView;

/**
 * Der Controller verwaltet ein Model und beobachtet eine View. Auf Änderungen
 * an der View reagiert er entsprechend und sorgt für eine Änderung des Models.
 * 
 * @author Daniel Sinn
 * @version 16.06.14
 * 
 */
public class TemperatureController implements Observer
{

    private TemperatureModel _model;
    private TemperatureView _view;

    public TemperatureController()
    {
        _model = new TemperatureModel();
        _view = new TemperatureView();

        _view.sichtbarMachen();

        registriereBeobachter();
    }


    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg)
    {

        if (arg.equals(ViewUpdate.CelsiusText))
        {
            _model.setzeFahrenheit(_view.gibFahrenheitText());
        }
        else if (arg.equals(ViewUpdate.FahrenheitText))
        {
            _model.setzeCelsius(_view.gibCelsiusText());
        }
        else if (arg.equals(ViewUpdate.CelsiusSlider))
        {
            _model.setzeCelsius(_view.gibCelsiusSlider());
        }
        else if (arg.equals(ViewUpdate.FahrenheitSlider))
        {
            _model.setzeFahrenheit(_view.gibFahrenheitSlider());
        }
        _view.beendeAenderungen();
    }
    private void registriereBeobachter()
    {
        _model.addObserver(_view);
        _view.addObserver(this);
        
    }
}