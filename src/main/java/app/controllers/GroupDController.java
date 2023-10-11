package app.controllers;

import app.entities.Product;
import io.javalin.http.Context;
import java.util.HashMap;

import java.util.HashMap;

public class GroupDController
{
    public static void showPage(Context ctx) {
        ctx.render("groupD.html");
    }
    public static void Coffee(Context ctx, Product product) {
        HashMap<String, Integer> selectCoffee = new HashMap<String, Integer>();
        selectCoffee.put("Caffelatte", 22);
        selectCoffee.put("Coffee", 18);
        selectCoffee.put("Cappuccino", 24);
        selectCoffee.put("Cacao", 25);
        selectCoffee.put("flat white", 22);
        selectCoffee.put("Espresso", 20);
        String coffename = ctx.formParam("coffeeName");
        String price = ctx.formParam("checkprice");


        int addprice ;
        if (selectCoffee.equals("s")){
            product.getPrice();
        }
        if(selectCoffee.equals("m")){

            addprice = 15+ product.getPrice();
        }
        if (selectCoffee.equals("L")){
            addprice= 20+product.getPrice();
        }


        ctx.render("groupD.html");

    }
}
