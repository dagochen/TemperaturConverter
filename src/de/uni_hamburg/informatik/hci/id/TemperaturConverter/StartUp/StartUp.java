package de.uni_hamburg.informatik.hci.id.TemperaturConverter.StartUp;

import de.uni_hamburg.informatik.hci.id.TemperaturConverter.Controller.TemperatureController;

/**
 * Startet das MVC-Projekt
 * 
 * @author Daniel Sinn
 * @version 16.06.14
 */
public class StartUp
{
    public static void main(String[] args)
    {
        TemperatureController controller = new TemperatureController();
    }
}
