package app.controllers;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.javalin.http.Context;

public class GroupEController
{
    private static final Map<String,Float> ratios = Stream.of(new Object[][] {
        {"USD", 1f},
        {"DKK", 7.03f},
        {"EUR", 0.94f}

    }).collect(Collectors.toMap(data -> (String)data[0], data -> (Float)data[1]));

    public static void getInput(Context ctx) {

        float value = Float.parseFloat(ctx.formParam("amount"));

        String startCurrency = ctx.formParam("startCurrency");
        String endCurrency = ctx.formParam("endCurrency");

        String calculatedValuta = Float.toString(calculator(startCurrency, endCurrency, value));

        ctx.attribute("calculatedValuta", calculatedValuta);
        valutaInit(ctx);

    }

    /**
     * Takes the start and end currentsy as well as the value you want to change 
     * Thows an exeption if one of the chosen currentsys are not in the list of valid currentsys
     * @param startCurrency
     * @param endCurrency
     * @param value
     * @return The value in the new valuta
     * @throws IllegalArgumentException
     */
    private static float calculator(String startCurrency, String endCurrency, float value) throws IllegalArgumentException
    {

        if(!ratios.containsKey(startCurrency)){
            throw new IllegalArgumentException(startCurrency + " is not a valid currentsy");
        }
        
        if(!ratios.containsKey(endCurrency)){
            throw new IllegalArgumentException(endCurrency + " is not a valid currentsy");
        }

        float startToUSD = ratios.get(startCurrency);
        float USDToEnd = ratios.get(endCurrency);

        return (value/startToUSD)*USDToEnd;
    }

    public static void valutaInit(Context ctx){
        ctx.attribute("valutaList", ratios.keySet().toArray(new String[ratios.size()]));
        ctx.render("groupE.html");
    }
}
