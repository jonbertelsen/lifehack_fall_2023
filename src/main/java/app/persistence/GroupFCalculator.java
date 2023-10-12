package app.persistence;

import app.entities.GroupFDrinks;
import app.exceptions.DatabaseException;

import java.util.List;



        public class GroupFCalculator {
            public static String calculator(int d_id, ConnectionPool connectionPool) throws DatabaseException {
                List<GroupFDrinks> sugarContent = GroupFMapper.getSugar(d_id, connectionPool);
                double sugar = sugarContent.get(0).getSugar();
                double sugarPerDay = 30 - sugar;

                if (sugarPerDay <= 0) {
                    double positiveNumber = Math.abs(sugarPerDay);
                    return "There is " + sugar + " grams of sugar in the drink, " +
                            "and the average sugar intake is 30 grams per day, so you need to walk for " +
                            (positiveNumber * 0.9) + " minutes today to burn off the extra " + positiveNumber + " grams of sugar";
                } else {
                    return "You are good since there was " + sugar + " grams of sugar in the drink, " +
                            "so out of the 30 grams per day, you have " + sugarPerDay + " grams of sugar left to enjoy";
                }
            }

        /*public class SugarContent {
            public double calculateSugar(int d_id, ConnectionPool connectionPool) throws DatabaseException {
                List<GroupFDrinks> sugarContent = GroupFMapper.getSugar(d_id, connectionPool);
                double sugar = sugarContent.get(0).getSugar();
                double sugarPerDay = 30 - sugar;
                return sugarPerDay;
            }
        }*/
    }
