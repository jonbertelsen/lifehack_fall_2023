package app.controllers;

import io.javalin.http.Context;

public class GroupDController
{
    public static void showPage(Context ctx) {
        ctx.render("groupD.html");
    }

}
