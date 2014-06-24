package de.uni_hamburg.informatik.hci.id.TemperaturConverter.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
public class TemperatureController 
{

    private TemperatureModel _model;
    private TemperatureView _view;
   

    public TemperatureController()
    {
        _model = new TemperatureModel();
        _view = new TemperatureView();

        _view.sichtbarMachen();

        registriereBeobachter();
        registriereUiAktionen();

    }

    private void registriereUiAktionen()
    {

        _view.gibNachCelsiusUmwandeln().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!_view.istAenderungNotwendig())
                {
                    _view.starteAenderungen();
                    _model.setzeFahrenheit(_view.gibFahrenheitText());
                    _view.beendeAenderungen();
                }

            }
        });
        _view.gibNachFahrenheitUmwandeln().addActionListener(
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent e)
                    {
                        if (!_view.istAenderungNotwendig())
                        {
                            _view.starteAenderungen();
                            _model.setzeCelsius(_view.gibCelsiusText());
                            _view.beendeAenderungen();
                        }

                    }
                });

        _view.gibFahrenheitSlider().addChangeListener(new ChangeListener()
        {

            public void stateChanged(ChangeEvent e)
            {
                if (!_view.istAenderungNotwendig())
                {
                    _view.starteAenderungen();
                    _model.setzeFahrenheit(_view.gibFahrenheitSlider().getValue());
                    _view.beendeAenderungen();
                }
            }
        });

        _view.gibCelsiusSlider().addChangeListener(new ChangeListener()
        {

            public void stateChanged(ChangeEvent e)
            {
                if (!_view.istAenderungNotwendig())
                {
                   _view.starteAenderungen();
                    _model.setzeCelsius(_view.gibCelsiusSlider().getValue());
                    _view.beendeAenderungen();
                }

            }
        });

    }

   

    private void registriereBeobachter()
    {
        _model.addObserver(_view);
       

    }
}