package model.plugins.temperature;

import model.Convertible;
import model.Unit;
import model.plugins.PluginConverter;

import java.util.List;

/**
 * Converter class for the {@link TemperaturePlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class TemperatureConverter extends PluginConverter {

    private Unit fahrenHeitUnit;
    private Unit kelvinUnit;

    public TemperatureConverter(List<Unit> conversions) {
        super(conversions);
        this.fahrenHeitUnit = getUnit("fahrenheit");
        this.kelvinUnit = getUnit("kelvin");
    }

    @Override
    public Convertible convert(Object source, Unit target, Object... args) {
        if (source instanceof TemperatureObject) {
            if (source instanceof Celsius) {
                if (target.getSystem().equals(Celsius.class)) {
                    return celsiusToCelsius((Celsius) source);
                } else if (target.getSystem().equals(Fahrenheit.class)) {
                    return celsiusToFahrenheit((Celsius) source);
                } else if (target.getSystem().equals(Kelvin.class)) {
                    return celsiusToKelvin((Celsius) source);
                }
            } else if (source instanceof Fahrenheit) {
                if (target.getSystem().equals(Fahrenheit.class)) {
                    return fahrenheitToFahrenheit((Fahrenheit) source);
                } else if (target.getSystem().equals(Celsius.class)) {
                    return fahrenheitToCelsius((Fahrenheit) source);
                } else if (target.getSystem().equals(Kelvin.class)) {
                    return fahrenheitToKelvin((Fahrenheit) source);
                }
            } else if (source instanceof Kelvin) {
                if (target.getSystem().equals(Kelvin.class)) {
                    return kelvinToKelvin((Kelvin) source);
                } else if (target.getSystem().equals(Celsius.class)) {
                    return kelvinToCelsius((Kelvin) source);
                } else if (target.getSystem().equals(Fahrenheit.class)) {
                    return kelvinToFahrenheit((Kelvin) source);
                }
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target.getName());
    }

    private Celsius celsiusToCelsius(Celsius celsius) {
        return celsius;
    }

    private Fahrenheit fahrenheitToFahrenheit(Fahrenheit fahrenheit) {
        return fahrenheit;
    }

    private Kelvin kelvinToKelvin(Kelvin kelvin) {
        return kelvin;
    }

    private Fahrenheit celsiusToFahrenheit(Celsius celsius) {
        Fahrenheit fahrenheit;
        double temperature = celsius.getTemperature();
        Double conversionRate;

        conversionRate = fahrenHeitUnit.getConversionRate();
        temperature = (temperature * 9 / 5) + conversionRate;

        fahrenheit = new Fahrenheit(temperature, fahrenHeitUnit);

        return fahrenheit;
    }

    private Fahrenheit kelvinToFahrenheit(Kelvin kelvin) {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }

    private Kelvin fahrenheitToKelvin(Fahrenheit fahrenheit) {
        return celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
    }

    private Kelvin celsiusToKelvin(Celsius celsius) {
        Kelvin kelvin;
        double temperature = celsius.getTemperature();
        Double conversionRate;

        conversionRate = kelvinUnit.getConversionRate() - 1;
        temperature = temperature + conversionRate;

        kelvin = new Kelvin(temperature, kelvinUnit);

        return kelvin;
    }

    private Celsius fahrenheitToCelsius(Fahrenheit fahrenheit) {
        Celsius celsius;
        double temperature = fahrenheit.getTemperature();
        Unit fahrUnit = fahrenheit.getUnit();
        Double conversionRate;

        conversionRate = fahrUnit.getConversionRate();
        temperature = (temperature - conversionRate) * (5/9.0);
        celsius = new Celsius(temperature, baseUnit);

        return celsius;
    }

    private Celsius kelvinToCelsius(Kelvin kelvin) {
        Celsius celsius;
        double temperature = kelvin.getTemperature();
        Unit kelUnit = kelvin.getUnit();
        Double conversionRate;

        conversionRate = kelUnit.getConversionRate() - 1;
        temperature = temperature - conversionRate;
        celsius = new Celsius(temperature, baseUnit);

        return celsius;
    }
}
