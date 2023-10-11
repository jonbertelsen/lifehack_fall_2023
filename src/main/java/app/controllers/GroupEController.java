package app.controllers;
import java.util.HashMap;
import java.util.IllegalFormatException;
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

    public static void getinput(Context cxt)
    {

    }

    /**
     * Takes the start and end currentsy as well as the value you want to change 
     * Thows an exeption if one of the chosen currentsys are not in the list of valid currentsys
     * @param startCurrentsy
     * @param endCurrentsy
     * @param value
     * @return The value in the new valuta
     * @throws IllegalArgumentException
     */
    private static float calcualtor(String startCurrentsy, String endCurrentsy, float value) throws IllegalArgumentException
    {
        if(!ratios.containsKey(startCurrentsy)){
            throw new IllegalArgumentException(startCurrentsy + " is not a valid currentsy");
        }
        
        if(!ratios.containsKey(endCurrentsy)){
            throw new IllegalArgumentException(endCurrentsy + " is not a valid currentsy");
        }

        float startToUSD = ratios.get(startCurrentsy);
        float USDToEnd = ratios.get(endCurrentsy);

        return (value/startToUSD)*USDToEnd;
    }
}
