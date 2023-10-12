package app.controllers;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;

public class GroupDController {

    public static void showPage(Context ctx) {
        ctx.render("groupD.html");
    }

    private static HashMap<String, Integer> selectCoffee = new HashMap<>();
    private static HashMap<String, Integer> selectSize = new HashMap<>();
    private static HashMap<String, Integer> selectExtra = new HashMap<>();

    public static void getPrice(Context ctx) {
        selectCoffee.put("Caffelatte", 22);
        selectCoffee.put("Cappuccino", 24);
        selectCoffee.put("Lungo", 20);
        selectCoffee.put("Flat White", 18);
        selectCoffee.put("Espresso", 20);

        selectSize.put("Small",0);
        selectSize.put("Medium", 10);
        selectSize.put("Large", 15);

        selectExtra.put("Milk", 3);
        selectExtra.put("Sugar", 1);
        selectExtra.put("Sirup", 5);
        selectExtra.put("None", 0);

        String coffeeType = ctx.formParam("coffee_type");
        String coffeeSize = ctx.formParam("coffee_size");
        List<String> coffeeAddition = ctx.formParamMap().get("coffee_addition");

        // Hent priserne fra HashMaps
        int coffeePrice = selectCoffee.get(coffeeType);
        int sizePrice = selectSize.get(coffeeSize);
        int extraPrice = 0;

        for (String c : coffeeAddition) {
            extraPrice += selectExtra.get(c);
        }

        // Beregn den samlede pris
        int totalPrice = coffeePrice + sizePrice + extraPrice;


        //Gem coffeePrice i Thymeleaf-konteksten

        ctx.attribute("price", totalPrice);
        ctx.render("UnderPageD.html");
    }


}


/*
package app.controllers;

import io.javalin.http.Context;
import java.util.HashMap;

public class GroupDController {

    public static void showPage(Context ctx){
        ctx.render("groupD.html");
    }
    public static void getPrice(Context ctx) {
        HashMap<String, Integer> selectCoffee = new HashMap<String, Integer>();
        selectCoffee.put("Caffelatte", 22);
        selectCoffee.put("Cappuccino", 24);
        selectCoffee.put("Lungo", 20);
        selectCoffee.put("Flat White", 18);
        selectCoffee.put("Espresso", 20);

        String coffeeType = ctx.formParam("coffee_type");
        String coffeeSize = ctx.formParam("coffee_size");
        String coffeeAddition = ctx.formParam("coffee_addition");


        if (selectCoffee.containsKey(coffeeType)) {
            int price = selectCoffee.get(coffeeType);
            int additionalPrice = 0;

            if (coffeeSize.equals("Small")) {
                additionalPrice = 0;
            } else if (coffeeSize.equals("Medium")) {
                additionalPrice = 15;
            } else if (coffeeSize.equals("Large")) {
                additionalPrice = 20;
            } else if(coffeeAddition.equals("Milk")){
                additionalPrice = 2;
            }

            int totalPrice = price + additionalPrice;

            ctx.attribute("price", totalPrice);
            ctx.render("UnderPageD.html");
        }   else{

            ctx.result("please select a coffee");
        }

    }
}
*/