package app.persistence;

import app.exceptions.DatabaseException;

import java.util.List;


    public class GroupFCalculator {

        public GroupFCalculator(int d_id, ConnectionPool connectionPool) throws DatabaseException {
            List<GroupFDrinks> sugarContent = GroupFMapper.getSugar(d_id, connectionPool);

            double sugar = sugarContent.get(0).getSugar();

            double sugarPerDay = 30 - sugar;

            if (sugarPerDay <= 0) {
                double positiveNumber = Math.abs(sugarPerDay);
                System.out.println("You need to walk for " + (positiveNumber * 0.9) + " minutes today to burn off the extra "+positiveNumber+" grams sugar");
            } else {
                System.out.println("You are good since only had "+sugarPerDay+ " grams sugar");}
        }
    }
