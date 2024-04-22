/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ApplicationClasses.MaintainSMList.Machine;
import ApplicationClasses.MaintainSMList.Brand;
import ApplicationClasses.MaintainSMList.Disc;
import ApplicationClasses.MaintainSMList.Movie;
import ApplicationClasses.MaintainSMList.StorageMedium;
import ApplicationClasses.Payment.Payment;
import ApplicationClasses.Rental.FreeOffer;
import ApplicationClasses.Rental.Offer;
import ApplicationClasses.Rental.Rental;
import ApplicationClasses.User.Customer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 
 */
public class SetUp {
    
    private final String unlimitedAccountOfferDescription = "All you can watch for only 8.99 a month! " + System.lineSeparator()
                                                            + "Then start to rent as many movies as you like with no late fees." + System.lineSeparator()
                                                            + "Just bring back your movies and select the next 2 movies." + System.lineSeparator()
                                                            + "You can also cancel the service at any Xtra-vision Xpress machine.";
    
    private final String freeOfferDescription = "First Rental Free - use code FREE123";
    private final String rent1Movie1NightDescription = "Rent 1 movie for €2.99 and keep it for 1 night";
    private final String rent2Movies2NightsDescription = "Rent 2 movies for €2.99 each and keep them for 2 nights";
    private final String rent3Movies3NightsDescription = "Rent 3 movies for €2.99 each and keep them for 3 nights";
    
    private Offer unlimitedMoviesAccountOffer = new Offer(1, new BigDecimal("0"), new BigDecimal("8.99"), Double.POSITIVE_INFINITY, 2, unlimitedAccountOfferDescription); 
    private FreeOffer freeOffer = new FreeOffer("FREE123", 2, new BigDecimal("0"), new BigDecimal("0"), 1, 1, freeOfferDescription); 
    private Offer rent1Movie1NightOffer = new Offer(3, new BigDecimal("2.99"), new BigDecimal("2.99"), 1, 1, rent1Movie1NightDescription); 
    private Offer rent2Movies2NightsOffer = new Offer(4, new BigDecimal("2.99"), new BigDecimal("5.98"), 2, 2, rent2Movies2NightsDescription); 
    private Offer rent3Movies3NightsOffer = new Offer(5, new BigDecimal("2.99"), new BigDecimal("8.97"), 3, 3, rent3Movies3NightsDescription); 
    List<Offer> offers;
    List<Machine> machines;
    List<StorageMedium> sms;
    List<Brand> brands;
    
    // link to the database
    private String dbServer = "jdbc:mysql://52.50.23.197/Rolando_2019337?use_SSL=false";

    // user to query DB and perorm inserts, deletes and updaes
    private String user = "Rolando_2019337";// "root";  
    // password
    private String password = "2019337"; //"root"; 

    public SetUp() {
        
        machines = new LinkedList<>();
        sms = new LinkedList<>();
        offers = new LinkedList<>();
        brands = new LinkedList<>();
        
        Machine machine = new Machine("N39R6T3", "Hazelwood Shopping Centre", "Longford", "Longford", "Xtra-vision Xpress - Davis SuperValu Longford", "Ireland");
        machines.add(machine);
        Machine machine1 = new Machine("N37YA29", "Ballymahon Road", "Athlone", "Westmeath", "Xtra-vision Xpress - Croughan's Supervalu Ballymahon Rd", "Ireland");
        machines.add(machine1);
        Machine machine2 = new Machine("N91WPX9", "Austin Friars St", "Mullingar", "Westmeath", "Xtra-vision Xpress - Buckley's SuperValu Mullingar", "Ireland");
        machines.add(machine2);
        Machine machine3 = new Machine("H12R8P0", "", "Drumalee", "Cavan", "Xtra-vision Xpress - Tarpey's SuperValu Cavan", "Ireland");
        machines.add(machine3);
        Machine machine4 = new Machine("F52XA61", "Elphin Street", "Boyle", "Roscommon", "Xtra-vision Xpress - Kelly S's SuperValu Boyle", "Ireland");
        machines.add(machine4);
        Machine machine5 = new Machine("R35WK53", "Arden Rd", "Puttaghan", "Tullamore", "Xtra-vision Xpress - Scally's Centra Tullamore", "Ireland");
        machines.add(machine5);
        Machine machine6 = new Machine("A82P2H0", "Virginia Shopping Centre", "Virginia", "Cavan", "Xtra-vision Xpress - McEvoy's SuperValu Virginia", "Ireland");
        machines.add(machine6);
        Machine machine7 = new Machine("R42HC85", "Lower Main Street", "Banagher", "Offaly", "Xtra-vision Xpress - SuperValu Banagher", "Ireland");
        machines.add(machine7);
                  
        Disc disc = new Disc(TypeDisc.cd, "AAA111", StatusSM.availableToRent, machine);
        sms.add(disc);
        Disc disc1 = new Disc(TypeDisc.cd, "AAA222", StatusSM.availableToRent, machine);
        sms.add(disc1);
        Disc disc2 = new Disc(TypeDisc.cd, "AAA333", StatusSM.availableToRent, machine);
        sms.add(disc2);
        Disc disc3 = new Disc(TypeDisc.cd, "AAA444", StatusSM.availableToRent, machine);
        sms.add(disc3);
        Disc disc4 = new Disc(TypeDisc.cd, "AAA555", StatusSM.availableToRent, machine);
        sms.add(disc4);
        Disc disc5 = new Disc(TypeDisc.cd, "AAA666", StatusSM.availableToRent, machine1);
        sms.add(disc5);
        Disc disc6 = new Disc(TypeDisc.cd, "AAA777", StatusSM.availableToRent, machine1);
        sms.add(disc6);       
        Disc disc7 = new Disc(TypeDisc.cd, "AAA888", StatusSM.availableToRent, machine1);
        sms.add(disc7);
        Disc disc8 = new Disc(TypeDisc.cd, "AAA999", StatusSM.availableToRent, machine1);
        sms.add(disc8);
        Disc disc9 = new Disc(TypeDisc.cd, "AAA000", StatusSM.availableToRent, machine1);
        sms.add(disc9);        
        Disc disc10 = new Disc(TypeDisc.cd, "AAA122", StatusSM.availableToRent, machine2);
        sms.add(disc10);

        // new discs
        
        Disc disc11 = new Disc(TypeDisc.dvd, "BBB111", StatusSM.availableToRent, machine3);
        sms.add(disc11);

        Disc disc12 = new Disc(TypeDisc.cd, "BBB222", StatusSM.availableToRent, machine3);
        sms.add(disc12);        
        
        Disc disc13 = new Disc(TypeDisc.blueRay, "BBB333", StatusSM.availableToRent, machine3);
        sms.add(disc13);        
        
        Disc disc14 = new Disc(TypeDisc.dvd, "BBB444", StatusSM.availableToRent, machine3);
        sms.add(disc14);    
        
        Disc disc15 = new Disc(TypeDisc.blueRay, "BBB555", StatusSM.availableToRent, machine3);
        sms.add(disc15);
        
        Movie movie = new Movie("1917", "April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.", "", Category.notNew, "Drama, Thriller, War", Year.of(2019), 1, null);
        brands.add(movie);
        disc.setBrand(movie);
        disc1.setBrand(movie);
        
        movie = new Movie("Jumanji: The Next Level", "In Jumanji: The Next Level, the gang is back but the game has changed. As they return to rescue one of their own, the players will have to brave parts unknown from arid deserts to snowy mountains, to escape the world's most dangerous game.", "", Category.notNew, "Action, Adventure, Comedy", Year.of(2019), 2, null);
        brands.add(movie);
        disc2.setBrand(movie);
        disc3.setBrand(movie);
        
        movie = new Movie("Terminator: Dark Fate", "An augmented human and Sarah Connor must stop an advanced liquid Terminator from hunting down a young girl, whose fate is critical to the human race.", "", Category.notNew, "Action, Adventure, Sci-Fi", Year.of(2019), 3, null);
        brands.add(movie);
        disc4.setBrand(movie);
        disc5.setBrand(movie);
        
        movie = new Movie("Black Widow", "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.", "", Category.comingSoon, "Action, Adventure, Sci-Fi", Year.of(2021), 4, null);
        brands.add(movie);
        disc6.setBrand(movie);

        movie = new Movie("Frozen 2", "Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom.", "", Category.notNew, "Animation, Adventure, Comedy", Year.of(2019), 5, null);
        brands.add(movie);
        disc7.setBrand(movie);

        movie = new Movie("Death Proof", "Two separate sets of voluptuous women are stalked at different times by a scarred stuntman who uses his \"death proof\" cars to execute his murderous plans.", "", Category.notNew, "Action, Adventure, Thriller", Year.of(2007), 6, null);
        brands.add(movie);
        disc8.setBrand(movie);
        
        movie = new Movie("Mamma Mia!", "The story of a bride-to-be trying to find her real father told using hit songs by the popular 1970s group ABBA.", "", Category.notNew, "Comedy, Musical, Romance", Year.of(2008), 7, null);
        brands.add(movie);
        disc9.setBrand(movie);
        disc10.setBrand(movie);
        
        // new movies
        movie = new Movie("Wrath of Man", "The plot follows H, a cold and mysterious character working at a cash truck company responsible for moving hundreds of millions of dollars around Los Angeles each week.", "", Category.comingSoon, "Action, Thriller ", Year.of(2021), 8, null);
        brands.add(movie);
        disc11.setBrand(movie);
        disc12.setBrand(movie);

        movie = new Movie("Nobody", "A bystander who intervenes to help a woman being harassed by a group of men becomes the target of a vengeful drug lord.", "", Category.latest, "Action, Crime, Drama", Year.of(2021), 9, null);
        brands.add(movie);
        disc13.setBrand(movie);
        disc14.setBrand(movie);        
        disc15.setBrand(movie);        

        
    }
    
    
    
    
    // Begin Rental
    
    public int getMaximumRentedMoviesForUnlimitedAccount() {
        
        
        return 2;
        
    }

    public int getMaximumRentedMoviesForNewCustomers() {
        
        
        return 2;
        
    }    
    
    public int getMaximumRentedMoviesForGeneralAccount() {
        
        
        return 4;
        
    }
    
    public int getQuantityOfRentalsNotReturnedByCustomer(String idCustomer) throws DBException {
        
        // search on database        
       String query = "SELECT COUNT(*) AS total FROM detail WHERE id_customer = '" + idCustomer + "' AND status = 'rented';";
              
       int total = 0;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            if (rs.next()) {
                
                total = rs.getInt("total");
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw  new DBException(e.getMessage());
        }
        
        return total;   
        
    }
    
    public List<Rental> getRentalByStorageMedium(List<StorageMedium> sms) throws DBException {
        
        Rental rental;
        
        int idRental;
        String idCustomer;
        String email;
        boolean unlimitedAccount;
        LocalDate endUnlimitedAccount;
        String name;
        String eircodeRental;
        LocalDate startDate;
        LocalDate returnDate;
        BigDecimal totalCost;
        int idDetail;
        java.sql.Date date;
        String status;
        LocalDate dateReturned;
        BigDecimal price;
        BigDecimal lateFee;
        BigDecimal finalPrice;
        RentStatus rentStatus;
        String electronicTag;
        String eircodeDetail;
        List<Rental> rentals;
        Machine machine;
        // search on database        
        String query = "SELECT customer.id_customer, customer.email, customer.unlimited_account, customer.end_unlimited_account, customer.name, "
                        + "rental.id_rental, rental.eircode, rental.start_date, rental.return_date, rental.total_cost, " 
                        + "detail.id_detail, detail.status, detail.date_returned, detail.price, detail.late_fee, "
                        + "detail.final_price, detail.electronic_tag, detail.eircodeReturn "
                        + "FROM ((customer INNER JOIN rental ON customer.id_customer = rental.id_customer) INNER JOIN detail ON rental.id_rental = detail.id_rental AND" +
                        " rental.id_customer = detail.id_customer AND rental.eircode = detail.eircode) " +
                        "WHERE status = 'rented' AND ";
       
        
        int index = 1;
        // create query
        for (StorageMedium sm: sms) {
                                                
            if (index < sms.size())
                query = query + "electronic_tag = '" + sm.getElectronicTag() + "' OR ";
            else
                query = query + "electronic_tag = '" + sm.getElectronicTag() + "';";
            
            index += 1;   
        }
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            
            // create list with the rentals
            rentals = new ArrayList<>();
            
            // Loop through the result set
            while (rs.next()) {
                
                idCustomer = rs.getString("customer.id_customer");
                email = rs.getString("customer.email");
                unlimitedAccount = rs.getBoolean("customer.unlimited_account");
                date = rs.getDate("end_unlimited_account");
                endUnlimitedAccount = null;
                if (date != null) {
                    endUnlimitedAccount = date.toLocalDate();
                    if (endUnlimitedAccount.getYear() == 9999)
                        endUnlimitedAccount = LocalDate.of(Year.MAX_VALUE, endUnlimitedAccount.getMonth(), endUnlimitedAccount.getDayOfMonth());
                
                }
                
                name = rs.getString("customer.name");
                
                idRental = rs.getInt("rental.id_rental");
                eircodeRental = rs.getString("rental.eircode");
                startDate = rs.getDate("rental.start_date").toLocalDate();
                returnDate = rs.getDate("rental.return_date").toLocalDate();
                if (returnDate.getYear() == 9999)
                    returnDate = LocalDate.of(Year.MAX_VALUE, returnDate.getMonth(), returnDate.getDayOfMonth());
                
                totalCost = rs.getBigDecimal("rental.total_cost");
                
                idDetail = rs.getInt("detail.id_detail");
                status = rs.getString("detail.status");
                
                switch (status) {
                    
                    case "rented": rentStatus = RentStatus.rented; break;
                    case "returned": rentStatus = RentStatus.returned; break;
                    case "returnedLate": rentStatus = RentStatus.returnedLate; break;
                    case "notReturned": rentStatus = RentStatus.notReturned; break;
                    default: throw new DBException("Technical Error");
                    
                }
                dateReturned = null;
                date = rs.getDate("detail.date_returned");
                if (date != null)
                    dateReturned = date.toLocalDate();
                price = rs.getBigDecimal("detail.price"); 
                lateFee = rs.getBigDecimal("detail.late_fee");
                finalPrice = rs.getBigDecimal("detail.final_price");
                electronicTag = rs.getString("detail.electronic_tag");
                eircodeDetail = rs.getString("detail.eircodeReturn");
                
                machine = null;
                if (eircodeDetail != null)
                    machine = getMachine(eircodeDetail);
                                 
                rental = new Rental(idRental, startDate, returnDate, totalCost, getMachine(eircodeRental), new Customer(idCustomer, unlimitedAccount, email, endUnlimitedAccount, name));
                
                for (StorageMedium sm: sms) {
                
                    if (sm.getElectronicTag().equals(electronicTag)) {
                        
                        rental.addDetail(rental.new Detail(idDetail, rentStatus, dateReturned, price, lateFee, finalPrice, sm, machine)); 
                        break;
                        
                    }
                
                }
                
                rentals.add(rental);
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
                        
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw  new DBException(e.getMessage());
        }
        
        return rentals;  
        
        
        
    }
    
    public void save(Rental rental) throws DBException {
            
        LocalDate lDateAux;
        if (rental.getReturnDate().getYear() == Year.MAX_VALUE)
            lDateAux = LocalDate.of(9999, rental.getReturnDate().getMonthValue(), rental.getReturnDate().getDayOfMonth());
        else
            lDateAux = rental.getReturnDate();
            
        // call database
        String sql1 = "INSERT INTO rental (id_customer, eircode, start_date, return_date, total_cost) "
                    + "VALUES ('" + rental.getCustomer().getIdCustomer() + "','" + rental.getMachine().getEircode()
                    + "','" + java.sql.Date.valueOf(rental.getStartDate()) + "','" + java.sql.Date.valueOf(lDateAux) + "','" + rental.getTotalCost() +"')";
                        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement(); 
            
            stmt.executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            rs.next();
            
            int id = rs.getInt(1);
                    
            for (Rental.Detail detail: rental.getDetails()) {
                
                    stmt.addBatch("INSERT INTO detail (id_rental, id_customer, eircode, id_detail, status, price, final_price, electronic_tag) "
                    + "VALUES ('" + id + "','" + rental.getCustomer().getIdCustomer() + "','" + rental.getMachine().getEircode()
                    + "','" + detail.getId() + "','" + detail.getStatus() + "','" + detail.getPrice() + "','" 
                    + detail.getFinalPrice() + "','" +  detail.getSm().getElectronicTag() + "')");
                
            }
            
            stmt.executeBatch();
            
            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw  new DBException(e.getMessage());
        } 
    }
    
    public void updateReturn(Rental rental) throws DBException {
       // implement 
       
       // update date and status in details and the storagem medium machine and status
               
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();
            
            String sql;
            StorageMedium smAux;
            
            for (Rental.Detail d: rental.getDetails()) {
       
                sql =  "UPDATE detail SET eircodeReturn = '" + d.getMachine().getEircode() 
                        + "', status = '" + d.getStatus() + "', date_returned = '" + java.sql.Date.valueOf(d.getDateReturned()) 
                        + "', late_fee = " + d.getLateFee()
                        + " WHERE id_rental = " + rental.getId()
                        + " AND id_customer = '" + rental.getCustomer().getIdCustomer()
                        + "' AND eircode = '" + rental.getMachine().getEircode()
                        + "' AND id_detail = " + d.getId() + ";";
                            
                stmt.addBatch(sql);

                sql =  "UPDATE disc SET eircode = '" + d.getMachine().getEircode() 
                        + "', status = '" + d.getSm().getStatus()
                        + "' WHERE electronic_tag = '" + d.getSm().getElectronicTag() + "';";
                
                // update disc 
                smAux = getDiscByElectronicTag(d.getSm().getElectronicTag());
                
                smAux.setMachine(d.getSm().getMachine());
                smAux.setStatus(d.getSm().getStatus());
                        
                stmt.addBatch(sql);
                                
            }
            
            stmt.executeBatch(); 
                       
            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } 
        
    }
    
    // End Rental
    
    // Begin Offer
    
    public Offer getOfferUnlimitedAccount() {
        
        return unlimitedMoviesAccountOffer;
        
    }
    
    public Offer selectOffer(boolean unlimitedMoviesAccount, int quantity, String code) {
        
        if (unlimitedMoviesAccount)
            return unlimitedMoviesAccountOffer;
        else if (!code.isEmpty() && (quantity == 1))
                return freeOffer;
        else {
            
                switch(quantity) {
               
                    case 1: return rent1Movie1NightOffer;
                    case 2: return rent2Movies2NightsOffer;
                    case 3: return rent3Movies3NightsOffer;
                    default: return null;
               
                } 
            
        }   
    }
    
    public List<Offer> getAllOffers() {
        
        if (offers.isEmpty()) {
        
            offers.add(unlimitedMoviesAccountOffer);
            offers.add(freeOffer);
            offers.add(rent1Movie1NightOffer);
            offers.add(rent2Movies2NightsOffer);
            offers.add(rent3Movies3NightsOffer);
        
        }
        
        return offers;
        
        
    }
    
    // End Offer
    
    // Begin Brand
    
    public List<Brand> getAllBrands(Machine m) throws DBException {
        
        List<StorageMedium> smsAux = getAllDiscs();
        
        for (Brand b: brands) {
            b.setSms(new LinkedList<>());
        }
        
        for (StorageMedium sm: sms) {
            
            if (sm.getMachine().getEircode().equals(m.getEircode()) && sm.getStatus().equals(StatusSM.availableToRent)) {
                sm.getBrand().addSM(sm);
            }
                   
        }
        
        return brands;
        
    }
    
    // End Brand
    
    // Begin Machine
    public Machine getMachine(String eircode) {
        
        for (Machine m: machines) {
            
            if (m.getEircode().equals(eircode)) {
                
                return m;
                
            }
            
        }
        
        return null;
        
        
        
    }
    
    public List<Machine> getAllMachines() {
        
        return machines;
        
    }
    // End Machine
    
    // Begin Customer
    
    
    // get customer by ID
    public Customer getCustomerById(String idCustomer) throws DBException {
        
        // search on database        
       String query = "SELECT * FROM customer WHERE id_customer = '" + idCustomer + "';";
       String email,name;
       LocalDate endUnlimitedAccount;
       boolean unlimitedAccount;
       java.sql.Date date;

       Customer customer = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            if (rs.next()) {
                
                email = rs.getString("email");
                name = rs.getString("name");
                unlimitedAccount = rs.getBoolean("unlimited_account");
                date = rs.getDate("end_unlimited_account");
                endUnlimitedAccount = null;
                if (date != null) {
                    endUnlimitedAccount = date.toLocalDate();
                    if (endUnlimitedAccount.getYear() == 9999)
                        endUnlimitedAccount = LocalDate.of(Year.MAX_VALUE, endUnlimitedAccount.getMonth(), endUnlimitedAccount.getDayOfMonth());
                
                }
                customer = new Customer(idCustomer, unlimitedAccount, email, endUnlimitedAccount, name);
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw  new DBException(e.getMessage());
        }
        
        return customer;     
      
       
    }
    
    public void updateEmail (Customer customer) throws DBException {
        
               // this sql is created because the system double check is the status was not 
        // updated by the user who created this appoitment
        String sql = "UPDATE customer SET email = '" + customer.getEmail() + "' WHERE id_customer = '" + customer.getIdCustomer() + "';";
                
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql); 
                       
            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } 
        
    }
    
    public void save(Customer customer) throws DBException {
                
        String sql;
        
        if (customer.getEndUnlimitedAccountdate() != null) {
                                
            if (customer.getUnlimitedAccount()) {
                
                LocalDate lDateAux;
                if (customer.getEndUnlimitedAccountdate().getYear() == Year.MAX_VALUE)
                    lDateAux = LocalDate.of(9999, customer.getEndUnlimitedAccountdate().getMonthValue(), customer.getEndUnlimitedAccountdate().getDayOfMonth());
                else
                    lDateAux = customer.getEndUnlimitedAccountdate();
            
                sql = "INSERT INTO customer (id_customer, email, unlimited_account, end_unlimited_account, name) "
                      + "VALUES ('" + customer.getIdCustomer() + "','" + customer.getEmail() + "','" + 1
                      + "','" + java.sql.Date.valueOf(lDateAux) + "','" + customer.getName() +"')";
            } else
                
                sql = "INSERT INTO customer (id_customer, email, unlimited_account, end_unlimited_account, name) "
                      + "VALUES ('" + customer.getIdCustomer() + "','" + customer.getEmail() + "','" + 0
                      + "','" + customer.getEndUnlimitedAccountdate() + "','" + customer.getName() +"')";                
              
        } else
            
            if (customer.getUnlimitedAccount())

                sql = "INSERT INTO customer (id_customer, email, unlimited_account, name) "
                      + "VALUES ('" + customer.getIdCustomer() + "','" + customer.getEmail() + "','" + 1
                      + "','" + customer.getName() +"')";
        
            else
                
                sql = "INSERT INTO customer (id_customer, email, unlimited_account, name) "
                      + "VALUES ('" + customer.getIdCustomer() + "','" + customer.getEmail() + "','" + 0
                      + "','" + customer.getName() +"')";
                            
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw  new DBException(e.getMessage());
        } 
    }
    
    
     public void updateUnlimitedAccount (Customer customer) throws DBException {
        
        // this sql is created because the system double check is the status was not 
        // updated by the user who created this appoitment
        String sql = "";
                    
        if (customer.getUnlimitedAccount()) {
            LocalDate lDateAux;
            if (customer.getEndUnlimitedAccountdate().getYear() == Year.MAX_VALUE)
                lDateAux = LocalDate.of(9999, customer.getEndUnlimitedAccountdate().getMonthValue(), customer.getEndUnlimitedAccountdate().getDayOfMonth());
            else
                lDateAux = customer.getEndUnlimitedAccountdate();
            sql = "UPDATE customer SET unlimited_account = " + 1 + ", end_unlimited_account = '" + java.sql.Date.valueOf(lDateAux) + "' WHERE id_customer = '" + customer.getIdCustomer() + "';";
        } else
            sql = "UPDATE customer SET unlimited_account = " + 0 + ", end_unlimited_account = '" + java.sql.Date.valueOf(customer.getEndUnlimitedAccountdate()) + "' WHERE id_customer = '" + customer.getIdCustomer() + "';";
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql); 
                       
            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } 
        
    }
    
    // End customer
    
    // Begin Disc
    public void UpdateStatus(List<StorageMedium> sms, StatusSM status) throws DBException {
        
        // call database
        // updates discs with the new status
                
        String sql = "UPDATE disc SET status = '" + status + "' WHERE ";
        StorageMedium smAux;        
        int index = 1;
        // create query
        for (StorageMedium sm: sms) {
                                                
            if (index < sms.size())
                sql = sql + "electronic_tag = '" + sm.getElectronicTag() + "' OR ";
            else
                sql = sql + "electronic_tag = '" + sm.getElectronicTag() + "';";
            
            index += 1;
            
            smAux = getDiscByElectronicTag(sm.getElectronicTag());
            
            smAux.setStatus(status);
            
        }
                
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
        
    }
    
    private List<StorageMedium> getAllDiscs() throws DBException {
        
        String electronicTag;
        StatusSM statusSM;
        String status;
        String eircode; 
        List<StorageMedium> smResult = null;
       
        
         String query = "SELECT * FROM disc;";
        
               
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            //
            smResult = new LinkedList<>();
            
            // Loop through the result set
            while (rs.next()) {
                
                electronicTag = rs.getString("electronic_tag");
                status = rs.getString("status");
                
                switch(status) {
                    
                    case "rented": statusSM = StatusSM.rented; break;
                    case "sold": statusSM = StatusSM.sold; break;   
                    case "availableToRent": statusSM = StatusSM.availableToRent; break;
                    default: throw new DBException("Technical Error");
                }
                    
                eircode = rs.getString("eircode");
                smResult.add(new Disc(electronicTag, statusSM, new Machine(eircode)));
                
            }
            
            joinStorageMedium(smResult);
            
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            String msg = null;
            // Loop through the SQL Exceptions
            while (se != null) {
                msg = se.getSQLState() + " " + "Message: " + se.getMessage() + " " + "Error  : " + se.getErrorCode();
                se = se.getNextException();
                if (se != null)
                    msg =  msg + System.lineSeparator();
            }
            throw  new DBException(msg);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
        
        return smResult;
        
    }
    
    // get discs from the memory, the discs in the database are in the memory as well...
    private StorageMedium getDiscByElectronicTag(String electronicTag) {
        
        for (StorageMedium sm: sms) {
            
            if (sm.getElectronicTag().equals(electronicTag))
                return sm;
            
        }
        
        return null;
        
    }
    
    // return a movie randomly
    public StorageMedium getOneDiscRandomly() throws DBException {
        
        
        List<StorageMedium> smsAux = getAllDiscs();
        
        smsAux.clear();
        
        Random rand = new Random();
                
        List<StorageMedium> smsNotReturned = new LinkedList<>();
        
        for (StorageMedium sm: sms) {
            
            if (sm.getStatus().equals(StatusSM.rented) || sm.getStatus().equals(StatusSM.sold)) {
                
                smsNotReturned.add(sm);
                
            }
            
        }
        
        if (smsNotReturned.size() > 0) {
        
            int int_random = rand.nextInt(smsNotReturned.size());

            return smsNotReturned.get(int_random);
        }
        
        return null;
        
    }
    
    public void joinStorageMedium(List<StorageMedium> sms) {
        
        StorageMedium smAux;
        Machine machine;
        for (StorageMedium sm: sms) {
            
            smAux = getDiscByElectronicTag(sm.getElectronicTag());
            
            sm.setBrand(smAux.getBrand());
            ((Disc)sm).setTypeDisc(((Disc)smAux).getTypeDisc());
            
            smAux.setStatus(sm.getStatus());
            machine = getMachine(sm.getMachine().getEircode());
            smAux.setMachine(machine);
            sm.setMachine(machine);
        }
        
    }
    
    // end Disc
    
    // Begin Payment
    public void save(Payment payment) {
        // not implemented
    }
    
    
    // end Payment
    
}
