package unihalls.unihalls.people;

import java.sql.Timestamp;
/**
 *
 * @author kg00k
 */
public abstract class Person {
    private String name, id, email;
    private Timestamp origin;// Timestamp on object init.
    private double toPay;//Remaining balance to pay - student covered at accomodation date.
    private static double COST;// cost of accomodation per year
}
