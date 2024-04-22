/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ApplicationClasses.MaintainSMList.Brand;
import ApplicationClasses.MaintainSMList.Disc;
import ApplicationClasses.MaintainSMList.Machine;
import ApplicationClasses.MaintainSMList.StorageMedium;
import ApplicationClasses.Payment.Card;
import ApplicationClasses.Payment.DebitCard;
import ApplicationClasses.Payment.Payment;
import ApplicationClasses.Rental.Offer;
import ApplicationClasses.Rental.Rental;
import ApplicationClasses.Rental.SMNotFoundException;
import ApplicationClasses.User.Customer;
import ApplicationClasses.User.CustomerNoUnlimitedAccount;
import ApplicationClasses.User.DateTooLongNoUnlimitedAccountException;
import Model.DBException;
import Model.SetUp;
import Model.StatusSM;
import View.View;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author
 */
public class Controller {
    
    // constants for the main menu
    // messages shown to the user
    private final String msgWelcome = "**** Welcome to Xtra-Vision Xpress System *****";
    private final String msgAdditional1 = "No Xtra-vision membership or signup required";
    private final String msgAdditional2 = "Credit and debit cards accepted";
    private final String msgAdditional3 = "While your first transaction is limited to 2 discs, after that you can rent up to 4 discs at one time. Customers with unlimited movies account can rent up to 2 at a time.";
    private final String msgAdditional4 = "For each additional day you keep it past 8pm, you’ll be charged €1.50. If you hold onto your disc for the maximum rental period – 10 days, you will be charged a maximum fee of €15 and the disc is yours to keep.";
    private final String msgSelectedItems = "The selected items are: ";    
    private final String msgQuestionMainMenu = "What option would you like to choose?";
    private final String msgMainMenuOption1 = "[1] Rent (a) movie(s) with unlimited movies account";
    private final String msgMainMenuOption2 = "[2] Rent (a) movie(s) without unlimited moives account";
    private final String msgMainMenuOption3 = "[3] Open an unlmited movies account";
    private final String msgMainMenuOption4 = "[4] Close an unlmited movies account"; 
    private final String msgMainMenuOption5 = "[5] Return a movie";     
    private final String msgMainMenuOption6 = "[6] View machine locationes"; 
    private final String msgMainMenuOption7 = "[7] Quit the program"; 

    private final String msgSecondMenuOption1 = "[1] Select movies";
    private final String msgSecondMenuOption2 = "[2] Deselect movies";
    private final String msgSecondMenuOption3 = "[3] View movies"; 
    private final String msgSecondMenuOption4 = "[4] Checkout"; 
    private final String msgSecondMenuOption5 = "[5] Go back to previous menu"; 

    private final String msgProgramFinished = "The program has finished";
    private final String msgCloseProgram = "Are you sure you wish to close this program? (Y/N)";
    private final String msgQuestionChooseAItem = "Please, select a movie from the list below"; 
    private final String msgQuestionAnotherItem = "Would you like to select another movie? (Y/N)"; 
    private final String msgQuestionViewAnotherItem = "Would you like to view another movie? (Y/N)" ; 
    private final String msgGoBackToPreviousMenu = "Go back to the previous menu";
    private final String msgNoItems = "No items were added";
    private final String msgNoItemsInTheMachineForThisMovie = "There are no items for the movie selected in the machine, sorry for any inconvenience";    
    private final String msgItemAlreadySelected = "You have already selected this item previously";

    
    private final String msgUserMaxQuantity = "This user can not select more items. Please proceed to checkout or go to previous menu";
    private final String msgErrorCheckout = "You must select movies before proceeding to checkout";
    private final String msgOfferForNewUsers = "Would you like to use the code (FREE123) to get one item rental free? (Y/N)";
    private final String msgAskForEmail = "Please, type your email address";
    private final String msgAskForReceipt = "Would you like to get the receipt by email? (Y/N)";
    private final String msgEmailFormatIncorrect = "The email typed is incorrect";    
    private final String msgDiscWasSold = "The disc was sold";
        
    private final String msgCardDetails = "Please enter the card details";
    private final String msgExpirationDate = "Could you type the expiration date (mm/yy):";
    private final String msgPAN = "Could you type the card number (16 digits without space):";
    private final String msgNameOnCard = "Could you type the account holder name:";
    private final String msgCVV = "Could you type the CVV (3 digits without space):";
    private final String msgPIN = "Could you type the PIN (4 digits without space):";  
    private final String msgErrorCardDetails = "Card details are invalid, the rental is finished";
    private final String msgErrorHasUnlimitedAccount = "This customer has an unlimited account. Please choose the option [1] 'Rent a movie with unlimited movies account' to rent a movie";
    private final String msgErrorQuantityNotAllowedUnlimitedAccount = "The customer can not rent this quantity of items because this customer has already rented two movies previously (customer could need to return items)";
    private final String msgWarningUserClosedAccountButUserCanStillUseTheAccount = "You have already closed the account, but you can still use your account because the subscription end date will expire in the near future";
    private final String msgErrorQuantityNotAllowedGeneralAccount = "The customer can not rent this quantity of items because it is greater than 4 (customer could need to return items)";
    private final String msgErrorQuantityNotAllowedNewAccount = "The customer can not rent this quantity of items because it is greater than 2 and the customer is new";
    private final String msgProcessingPayment = "The payment is being processed. Please wait..."; 
    private final String msgPaymentCompleted = "Your payment has been successfully completed"; 
    private final String msgTakeCard = "Please take your card from the card reader"; 
    private final String msgRentalUpdate = "Please wait, the system is updating the rental in the database..."; 
    private final String msgCustomerUpdate = "Please wait, the system is updating the customer in the database...";
    private final String msgRentalCompleted = "The rental has been successfully completed..."; 
    private final String msgCustomerCompleted = "The customer has been successfully completed..."; 
    private final String msgErrorNotUnlimitedAccount = "The customer does not have an unlimited movies account"; 
    private final String msgProceedToCheckout = "Would you like to proceed to checkout? (Y/N)";
    private final String msgProceedToPayment = "Would you like to proceed to payment? (Y/N)";
    private final String msgDetailsService = "Details about the service: ";
    private final String msgUnlimitedAccountCost = "Unlimited movies Account - cost: ";
    private final String msgErrorCustomerHasAnUnlimitedAccount = "This customer has already got an unlimited account, the transacction is finished";
    private final String msgErrorCustomerNotReturnedAllTheMovies = "This customer must bring back all the movies in order to open an unlimited amovies account";
    private final String msgCancelUnlimitedAccount = "Would you like to cancel your account? (Y/N)";
    private final String msgCancelInfo = "If you cancel now, you can still rent until the end of your subscription";
    private final String msgCancellationUnlimitedAccount1 = "You have cancelled your unlimited movies account. You have time until ";
    private final String msgCancellationUnlimitedAccount2 = " to use your account";
    private final String msgSMReturnedOnTime = "The movies were returned on time";
    private final String msgDiscReturned = "The customer wants to return: ";
    private final String msgCaseIsProperlyClosed = "Makes sure the cae is properly closed. If not possible or case is damaged please ask Customer Service for a new case";
    private final String msgInsertIntoReturnSlot = "Insert case into return slot";
    private final String msgOpenService = "You have just got the service UNLIMITED MOVIES ACCOUNT";
    private final String msgMoviesDispensed = "Now you movie(s) will be dispensed. Enjoy you movie!!!";
    private final String msgNotDiscSelectedRandomly = "There are no discs to return";
    private final String msgNotNewCustomerCantUseFreeOffer = "This customer is not a new one, so the free special offer can not be used";
    private final String msgDiscSold = "Item can not be returned. This item should have been sold, but this functionality is not implemented. Execute return a movie again in order to chose another item";
    private final String msgEmailSentSuccessfully = "The email was sent successfully";
    private final String msgDetailsAboutRental = "Details about the rental";
    private final String msgDetailsAboutReturning = "Details about returning";
    private final String msgPlanClosedWait = "The unlimited movies account is being closed, pleae wait...";
    private final String msgPlanClosed = "The unlimited movies account is closed successfully";
            
    
    private final View view;
    private final SetUp setUp;
    private final List<Brand> selectedItems;
    private final Machine machine;
    
    public Controller(View view, String eircode, SetUp setUp) {
        this.view = view;
        this.setUp = setUp;
        this.selectedItems = new LinkedList<>();
        
        machine = setUp.getMachine(eircode);
        
    }

    public SetUp getSetUp() {
        return setUp;
    }
    
    
    
    public void executeProgram() {
        
        int choice;
        boolean select;
        int maxQuantity;
        int position = 0;
        
        
        
        // get offers
        List<Offer> offers = setUp.getAllOffers();
        
        // create array to show the offers to the user
        // add all the offers and additional messages to show on the screen
        String[] msgAdditionals = new String[offers.size() + 5];
        
        msgAdditionals[position] = "Special Offers";
        position++;
        
        for (Offer o: offers) {
            
            msgAdditionals[position] = o.toString();
            position++;
        }
        
        msgAdditionals[position] = msgAdditional1;
        position++;
        msgAdditionals[position] = msgAdditional2;
        position++;
        msgAdditionals[position] = msgAdditional3; 
        position++;
        msgAdditionals[position] = msgAdditional4; 
        
        // add the menu
        String[] msgOptions = {msgMainMenuOption1, msgMainMenuOption2, msgMainMenuOption3, msgMainMenuOption4, msgMainMenuOption5, msgMainMenuOption6, msgMainMenuOption7};
                
        while(true) {
            try {
                // option selected from mainMenu
                choice = view.menu(msgWelcome, msgQuestionMainMenu, msgAdditionals, msgOptions);
                
                switch(choice) {
                    
                    case 1: // Rent a item with unlimited movies account
                        // max quantity of rental for an unlimited movies account
                        maxQuantity = setUp.getMaximumRentedMoviesForUnlimitedAccount();
                        executeOperations(maxQuantity, true);
                        
                        
                        
                        break;
                    case 2: // Rent a item without unlimited moives account
                        // max quantity of rental for a general account
                        maxQuantity = setUp.getMaximumRentedMoviesForGeneralAccount();
                        executeOperations(maxQuantity, false);
                        
                        break;
                    case 3: // Open an unlmited movies account
                        openUnlimitedAccount();
                        break;
                    case 4: // Close an unlmited movies account
                        closeUnlimitedAccount();
                        break;
                    case 5: // return a movie
                        returnMovies();
                        break;
                    case 6: // View machine locationes
                        viewMachines();
                        break;
                    case 7: // close the program
                        select = view.askQuestionYesOrNo(msgCloseProgram);
                        if (select) {
                            view.printMessage(msgProgramFinished);
                            view.CloseScanner();
                            return;
                        }
                        
                }
                selectedItems.clear(); // clean list with the items selected
            } catch (DBException ex) {
                view.printMessage(ex.getMessage());
                view.promptEnterKey();
            }
            
            
            
        }
        
    
    }

    // select items
    private void selectItems(int maxQuantity) throws DBException {

        // get items and discs from database
        List<Brand> items = setUp.getAllBrands(machine);
        
        // No items were selected
        if (items.isEmpty()) {
            view.printMessage(msgNoItems);
            return;
        }
                          
        // create a list with the items to show to the user
        List<String> itemsAux = new ArrayList<>(); 
        boolean found;
        int quantity = 0;
        
        // get selected items, add at the end the expression [SELECTED]
        // to show to the user the items selected
        // we also count the quantity of elements selected
        for (Brand b: items) {
        
            found = false;
            
            for (Brand itemAux: selectedItems) {
                
                if (itemAux.getIdBrand() == b.getIdBrand()) {
                    
                    found = true;
                    quantity++;
                    break;
                }
                
            }
            if (found)
                itemsAux.add(b.toShortString() + "[SELECTED]" );
            else itemsAux.add(b.toShortString());     
        }
        
                       
        int choice;
        boolean anotherOne;
        List<String> selectedItemsAux;
        
        // loop to select items
        while(true) {
            
            if (quantity == maxQuantity) { // Validation: an unlimited movies account can not select more than 2 movies
                                         // and general account no more than 4, maxquantity has this information
                
                view.printMessage(msgUserMaxQuantity);                         
                view.printMessage(msgSelectedItems);
                
                selectedItemsAux = new LinkedList<>();
                for (Brand si: selectedItems) 
                    selectedItemsAux.add(si.toShortString());
                
                view.printOptions(selectedItemsAux);                        
                view.promptEnterKey();
                break;
                
            }   
            
            choice = view.chooseOption(itemsAux, msgQuestionChooseAItem, msgGoBackToPreviousMenu);
            if(choice>=1 && choice<=itemsAux.size())  { // grab the item from the list
                
                // check if the item selected has quantity greater than 0
                if (items.get(choice - 1).quantityOfStorageMedium() > 0) {
                  
                    if (selectedItems.contains(items.get(choice - 1)))
                       view.printMessage(msgItemAlreadySelected);
                    else {
                    
                        // add movies selected
                        selectedItems.add(items.get(choice - 1));

                        quantity++;

                        //add selected to that item
                        itemsAux.set(choice - 1, itemsAux.get(choice - 1) + "[SELECTED]");

                        if (quantity == maxQuantity) { // Validation: an unlimited movies account can not select more than 2 movies
                                                 // and general account no more than 4
                            view.printMessage(msgUserMaxQuantity);
                            view.printMessage(msgSelectedItems);

                            selectedItemsAux = new LinkedList<>();
                            for (Brand si: selectedItems) 
                                selectedItemsAux.add(si.toShortString());
                            view.printOptions(selectedItemsAux);  
                            view.promptEnterKey();
                            break;

                        }  
                    }
                } else {
                    
                    view.printMessage(msgNoItemsInTheMachineForThisMovie);
                    
                }
                // would you like to choose another item?
                anotherOne = view.askQuestionYesOrNo(msgQuestionAnotherItem);
            
                if (!anotherOne)
                    break;
                                
            } else return; // go back to main menu
                        
        }
        
    }


// show itemas (movies, etc) with more details
    private void viewItems() throws DBException {

        // get items and disc from database
        List<Brand> items = new ArrayList<>();
        
        items = setUp.getAllBrands(machine);
        
        // No items were selected
        if (items.isEmpty()) {
            view.printMessage(msgNoItems);
            return;
        }
                          
        // create a list with the items
        List<String> itemsAux = new ArrayList<>(); 
        
        // get selected items....
        for (Brand b: items) {
        
            itemsAux.add(b.toShortString());     
        }
        
                       
        int choice;
        boolean anotherOne;
                
        while(true) {
             
            choice = view.chooseOption(itemsAux, msgQuestionChooseAItem, msgGoBackToPreviousMenu);
            if ((choice != -1) && choice<=itemsAux.size()) { // grab the movie from the list
                
                // view item
                view.printMessage(items.get(choice - 1).toString());
                
                // would you like to choose another items?
                anotherOne = view.askQuestionYesOrNo(msgQuestionViewAnotherItem);
            
                if (!anotherOne)
                    break;
                                
            } else return; // go back to main menu
                        
        }
        
    }
    
    // select, deselect, view items and checkout
    private void executeOperations(int maxQuantity, boolean unlimitedMoviesFlow) throws DBException {
        
        int choice;
        List<Offer> offers;
        boolean yes;
        Rental rental;
        List<StorageMedium> sms;
        Card card;
        Customer customer;
        String idCustomer;
        String name;
        int quantityNotReturned;
        boolean newCustomer;
        Payment payment;
        
        String[] msgOptions = {msgSecondMenuOption1, msgSecondMenuOption2, msgSecondMenuOption3, msgSecondMenuOption4, msgSecondMenuOption5};
        
        while(true) {
            
             choice = view.menu("", msgQuestionMainMenu, new String[0], msgOptions);
             
             switch(choice) {
                
                case 1: // select a movie 
                    selectItems(maxQuantity);                        
                    break;
                case 2: // deselect a movie
                    // NOT IMPLEMENTED

                    break;
                case 3: // view a movie
                    viewItems();
                    break;

                case 4: // checkout
                    
                    if (selectedItems.isEmpty()) { 
                        
                        view.printMessage(msgErrorCheckout);
                        view.promptEnterKey();
                        continue;
                        
                    }
                    offers = new LinkedList<>();
                    // get offer for the selection
                    Offer offer = getOffer(unlimitedMoviesFlow, selectedItems.size(), ""); 
                    if (offer != null)
                        offers.add(offer);
                    
                    // get offer for the code entered by the customer
                    if (!unlimitedMoviesFlow) {
                        
                        yes = view.askQuestionYesOrNo(msgOfferForNewUsers);
                        
                        if (yes) 
                            offers.add(getOffer(false, 1, "FREE123"));
                           
                    }
                     
                    // get Disc for the movies selected
                    sms = new LinkedList<>(); 
                    for (Brand b: selectedItems) {
                        
                        sms.add(b.getAvailableStorageMedium());
                        
                    }
                                        
                    machine.prepareCardReader();
                    
                    // create rental
                    rental = new Rental(LocalDate.now(), offers, machine, sms);
                    
                    view.printMessage(msgDetailsAboutRental);
                    view.printMessage(rental.toString());
                    
                    yes = view.askQuestionYesOrNo(msgProceedToPayment);
                    
                    if (!yes) {
                        
                        return;
                        
                    }
                    
                    
                    // insert cards data
                    card = insertCard();                    
                    
                    if (card == null) {
                        
                        machine.prepareCardReader();
                        view.printMessage(msgErrorCardDetails);
                        view.promptEnterKey();
                        return;
                        
                    }
                    
                    idCustomer = card.getPan();
                    name = card.getNameOnCard();
                    // get customer for this id
                    customer = setUp.getCustomerById(idCustomer);
                    newCustomer = false;
                    if (customer == null) { // create customer
                        customer = new Customer(idCustomer, name);
                        newCustomer = true;
                    }
                    // validations
                    if (newCustomer && unlimitedMoviesFlow) { // customer is new and 
                        // user chooses unlimited movies account path, error user is new
                        // (s)he has to choose the another path
                        
                        machine.prepareCardReader();
                        view.printMessage(msgErrorNotUnlimitedAccount);
                        view.promptEnterKey();
                        return;
                        
                    } else if (!unlimitedMoviesFlow && (customer.getUnlimitedAccount() || (customer.getEndDateOfSubscriptionPeriod() != null && (customer.getEndDateOfSubscriptionPeriod().isAfter(LocalDate.now()) ||  customer.getEndDateOfSubscriptionPeriod().isEqual(LocalDate.now()))))) { 
                        // user wants to rent with a general account, but (s)he has an unlimited account
                        // we could start this process again without showing a message to the user but it would take more time to develop
                        view.printMessage(msgErrorHasUnlimitedAccount);
                        view.promptEnterKey();
                        return; 
                                
                    } else if (unlimitedMoviesFlow && (!customer.getUnlimitedAccount() && (customer.getEndDateOfSubscriptionPeriod() == null || (customer.getEndDateOfSubscriptionPeriod() != null && customer.getEndDateOfSubscriptionPeriod().isBefore(LocalDate.now()))))) { // unlimited account
                        // user wants to rent with a premium account, but (s)he does not have it
                        // we could start this process again without showing a message to the user but it would take more time to develop
                        view.printMessage(msgErrorNotUnlimitedAccount);
                        view.promptEnterKey();
                        return; 
                    }           
                    // validations
                    rental.setCustomer(customer);
                                                   
                    if (!newCustomer) // customer already registered
                    // how many discs has not this customer returned?
                        quantityNotReturned = setUp.getQuantityOfRentalsNotReturnedByCustomer(idCustomer);
                    else quantityNotReturned = 0; // new customer
                    
                    // validations
                    if (customer.getUnlimitedAccount() || (customer.getEndDateOfSubscriptionPeriod() != null && (customer.getEndDateOfSubscriptionPeriod().isAfter(LocalDate.now()) || customer.getEndDateOfSubscriptionPeriod().isEqual(LocalDate.now())  ) )  ) { // unlimited account
                       
                        if (quantityNotReturned + sms.size() > setUp.getMaximumRentedMoviesForUnlimitedAccount()) {
                            view.printMessage(msgErrorQuantityNotAllowedUnlimitedAccount);
                            view.promptEnterKey();
                            return;
                        }    
                        
                        if (!customer.getUnlimitedAccount()) { // user cancelled acount but this user can rent because the subscription end date is after current date
                        
                            view.printMessage(msgWarningUserClosedAccountButUserCanStillUseTheAccount);
                            
                        }
                        
                    } else { // general account
                        
                        if (newCustomer) { // new customer
                            
                            if (sms.size() > setUp.getMaximumRentedMoviesForNewCustomers()) {
                                view.printMessage(msgErrorQuantityNotAllowedNewAccount);
                                view.promptEnterKey();
                                return;
                            }                                
                        } else { // customer already registered on the database
                            
                            if (quantityNotReturned + sms.size() > setUp.getMaximumRentedMoviesForGeneralAccount()) {
                                view.printMessage(msgErrorQuantityNotAllowedGeneralAccount);
                                view.promptEnterKey();
                                return;
                            }    
                            
                            Offer freeOffer = getOffer(false, 1, "FREE123");
                            // if user is not new but (s)he chose a free voucher, ERROR!!!
                            for (Offer o: offers) {
                                
                                if (o.getId() == freeOffer.getId()) {
                                    view.printMessage(msgNotNewCustomerCantUseFreeOffer);
                                    view.promptEnterKey();
                                    return;                              
                                }
                            }   
                            
                        }
                          
                    }
                    
                    
                    // create payment
                    payment = new Payment(LocalDate.now(), rental.getTotalCost(), rental.toString(), customer);
                    view.printMessage(msgProcessingPayment);

                    payment.pay(card); // pay by card
                    
                    view.printMessage(msgPaymentCompleted);

                    view.printMessage(msgTakeCard);
                    machine.releaseCardReader();
                    view.printMessage(msgRentalUpdate);
                    
                    if (newCustomer)
                        setUp.save(customer);

                    setUp.save(rental);
                    setUp.UpdateStatus(sms, StatusSM.rented);
                    setUp.save(payment);
                    view.printMessage(msgRentalCompleted);  
                    view.printMessage(msgMoviesDispensed);
                    for (StorageMedium sm: sms) { // dispense all the discs rented
                    
                        machine.dispenseDisc(sm.getElectronicTag());
                    
                    }
                    sendReceipt(customer, payment); // send receipt
                    view.promptEnterKey();
                    
                case 5: // go back to previous menu
                    return;
             }
        }
        
        
    }
    
    
    // get offer for the rental input parameter if the user has an unlimited accout or not
    // quantity of movies selected
    // code voucher
    private Offer getOffer(boolean unlimitedMoviesAccount, int quantity, String code) {
            
        return setUp.selectOffer(unlimitedMoviesAccount, quantity, code);
          
    }

    private Card insertCard() {

        view.printMessage(msgCardDetails);
        
        // ask for the PAN
        String pan = view.askForAnInput(msgPAN, "\\d{16}");
        if (pan.isEmpty())
            return null;
        
        // ask for a date // mm/yy
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/yy");
        String dateAux = view.askForAnInput(msgExpirationDate, "^(1[0-2]|0[1-9])/[0-9]{2}$");
        if (dateAux.isEmpty())
            return null;
        YearMonth ym = YearMonth.parse(dateAux, fmt);
        LocalDate date = ym.atDay(1);
        
        // ask for the Name on the card
        String name = view.askForAnInput(msgNameOnCard, "^[\\p{L} .'-]+$");
        if (name.isEmpty())
            return null;
                
        // ask fot the CVV
        String cvvAux = view.askForAnInput(msgCVV, "\\d{3}");
        if (cvvAux.isEmpty())
            return null;
        int cvv = Integer.parseInt(cvvAux);

        // ask fot the CVV
        String pinAux = view.askForAnInput(msgPIN, "\\d{4}");
        if (pinAux.isEmpty())
            return null;
        int pin = Integer.parseInt(pinAux);        
        
        if (machine.VerifyCardAndPin(pan, name, cvv, date, pin))
            // systems realizes if it is a debit card or credit card...
            // we will use a debit card
            // we could throw an exception
            return new DebitCard(pan, name, date, pin, cvv);
        else return null;

    }
       
    
    private void sendReceipt(Customer customer, Payment payment) throws DBException {
        
        String email;
        boolean yes;
        
        yes = view.askQuestionYesOrNo(msgAskForReceipt); // ask would you like to get the receipt?
                        
        if (yes) {
            
            if (customer.getEmail() != null && customer.getEmail().isEmpty()) { // email empty

                email = view.askForAnInput(msgAskForEmail, "^(.+)@(\\S+)$");
                
                if (email == null) {
                    
                    view.printMessage(msgEmailFormatIncorrect);
                    view.promptEnterKey();
                    return;
                    
                }
                
                customer.setEmail(email);
                try {
                    payment.issueReceipt();
                    view.printMessage(msgEmailSentSuccessfully);
                } catch (MessagingException e) {
                    
                    view.printMessage(e.getMessage());
                    view.promptEnterKey();
                    return;
                    
                }    
                setUp.updateEmail(customer);

            } else { // this customer has email

                try {
                    payment.issueReceipt(); 
                    view.printMessage(msgEmailSentSuccessfully);
                } catch (MessagingException e) {
                    
                    view.printMessage(e.getMessage());
                    view.promptEnterKey();
                    
                }    

            }


        }
                    
    }
    
    // method to return movies
    public void returnMovies() throws DBException {
        
        // machine open the slot in order to allow the customer to insert the disc/storage medium
        view.printMessage(msgInsertIntoReturnSlot);
        view.printMessage(msgCaseIsProperlyClosed);
        machine.openReturnSlot();
        
        // I will simulate user returns one movie, but it could be more than one
        
        String electronicTag = machine.scanSM(); // scanSM returns a electronic tag randomly
        
        StorageMedium sm = setUp.getOneDiscRandomly(); // this method should receive the electronic tag from the previous method and search it,
                                                       // but we are simulating this process, so we will get a disc from database instead
        
        // there are no discs to return
        if (sm == null) {
            
            view.printMessage(msgNotDiscSelectedRandomly);
            view.promptEnterKey();
            return;
            
        }
                                                       
                                                       
        // if the disc is sold, so inform user and return the disc                                               
        if (sm.getStatus().equals(StatusSM.sold)) {
        
            view.printMessage(msgDiscWasSold);
            machine.dispenseDisc(electronicTag);
            view.promptEnterKey();
            return;
            
        }    
        
        // Print what is returned    
        view.printMessage(msgDiscReturned + " " + sm.getBrand().getTitle() + " Electronic Tag: " + sm.getElectronicTag() + ", Type " + ((Disc)sm).getTypeDisc());
        view.promptEnterKey();
        // get the rental by Storage Medium
        List<StorageMedium> sms = new LinkedList<>();
        sms.add(sm);
        List<Rental> rentals = setUp.getRentalByStorageMedium(sms); // query to get all the rentals
        
        // algorithm for many storage mediums/discs etc but we are simulation for one disc 
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal costPerRental;
        String description = "";
        long days = 0;
        // loop through rentals
        for (Rental rental: rentals) {
            
                // we search for the storage medium using the rentals
                // because the query return all the rentals for the disc/storage medium returned by the user
                sm = null;
                for (StorageMedium smAux: sms) {
                    
                    if (rental.getDetail(smAux) != null) {
                        sm = smAux;
                        break;
                    }    
                    
                }
                
                if (sm == null) // if we can't find the storage medium, continue with the next rental
                    continue;
                                
                // user had got an unlimited account, and the SM was rented then the customer closed the unlimited movies account, and (s)he have not returned the disc
                if (!rental.getCustomer().getUnlimitedAccount() && (rental.getCustomer().getEndDateOfSubscriptionPeriod() != null && (rental.getStartDate().isBefore(rental.getCustomer().getEndDateOfSubscriptionPeriod()) || rental.getStartDate().isEqual(rental.getCustomer().getEndDateOfSubscriptionPeriod())  ) )  )  {
                    rental.setReturnDate(rental.getCustomer().getEndDateOfSubscriptionPeriod());
                }
                
                try {
                    days = rental.calculateDaysOverdue(LocalDate.now(), sm); // calculate days overdue
                    // this disc should have been sold, but this functionality is NOT implemented, that is why we did not process this rental;
                    // only for customers who do not have an unlimited account and the susbscription date expired
                    if ((!rental.getCustomer().getUnlimitedAccount() || (!rental.getCustomer().getUnlimitedAccount() && (rental.getCustomer().getEndDateOfSubscriptionPeriod() != null && rental.getCustomer().getEndDateOfSubscriptionPeriod().isBefore(LocalDate.now())))) && (days >= 10)) {
                        
                        // another thing we could do here is the user can pay the disc on the machine and return the disc to the user
                        view.printMessage(msgDiscSold);
                        view.promptEnterKey();
                        return;
                    
                    }
                    costPerRental = rental.calculateOverdueCharges(LocalDate.now(), sm);
                    if (days > 0) 
                        totalCost = totalCost.add(costPerRental);
                    rental.processReturn(sm, LocalDate.now(), machine); // return Storage Medium set internal info
                    if (days > 0) 
                        description += rental.toStringReturn() + System.lineSeparator() + rental.getDetail(sm).toStringReturn() + System.lineSeparator();                        
                } catch (SMNotFoundException ex) {}            
        }    
        
        if (days == 0) {
            
            view.printMessage(msgSMReturnedOnTime);
            view.printMessage(msgRentalUpdate);
            for (Rental r: rentals) {
                    setUp.updateReturn(r);
            };
            view.printMessage(msgRentalCompleted);                                        

            view.promptEnterKey();
            return;
            
        }
        view.printMessage(msgDetailsAboutReturning);
        view.printMessage(description);
        
        machine.prepareCardReader();
        // insert cards data any person can pay this return
        Card card = insertCard();                    

        if (card == null) { // error when use inserts the card

            machine.prepareCardReader();
            view.printMessage(msgErrorCardDetails);
            view.promptEnterKey();            
            return;
        }
        
        // get customer from card, only the receipt will be sent to the person who pay
        Customer customer;
        String idCustomer = card.getPan();
        String name = card.getNameOnCard();
        customer = setUp.getCustomerById(idCustomer);
                    
        if (customer == null) {
            customer = new Customer(idCustomer, name);            
        }
        
        // create payment
        Payment payment = new Payment(LocalDate.now(), totalCost, description, customer);
                
        view.printMessage(msgProcessingPayment);
                
        payment.pay(card);
                
        view.printMessage(msgPaymentCompleted);

        view.printMessage(msgTakeCard);

        machine.releaseCardReader();
                
        view.printMessage(msgRentalUpdate);
        
        for (Rental r: rentals) {
            setUp.updateReturn(r);
        }; 
        
        view.printMessage(msgRentalCompleted);   
        
        sendReceipt(customer, payment);
        
        view.promptEnterKey();
                            
    }

    // method to show machines to the user
    private void viewMachines() {

        List<Machine> machines = setUp.getAllMachines();
        List<String> options = new LinkedList<>();
        
        for (Machine m: machines) {
            
            options.add(m.toString());
            
            
        }
        view.printOptions(options);
        view.promptEnterKey();

    }

    private void openUnlimitedAccount() throws DBException {
        
       // get special Offer for renting unlimited account 
       Offer o = setUp.getOfferUnlimitedAccount();

       // show the description about this offer to the user
       view.printMessage(o.getDescription());
       
       // ask the user if they want to get it
       boolean yes = view.askQuestionYesOrNo(msgProceedToCheckout);
       
       if (yes) {
           // shows to the user details about this service, cost, etc...
           view.printMessage(msgDetailsService);
           view.printMessage(msgUnlimitedAccountCost);
           view.printMessage(o.getTotalPrice() + " euros");
           // proceed to pay?
           yes = view.askQuestionYesOrNo(msgProceedToPayment);

           if (yes) {
                // prepare machine to work with the card
                machine.prepareCardReader();
                
                // user inserts cards 
                Card card = insertCard();                    
                    
                if (card == null) {
                        
                    view.printMessage(msgErrorCardDetails);
                    view.promptEnterKey();
                    return;
                        
                }
                
                // get customer from card details PAN
                Customer customer = setUp.getCustomerById(card.getPan());
                boolean newCustomer = false;
                
                if (customer == null) {  try {
                    // user does not exist in the DB
                    // date is the maximum year for the current day and month
                    customer = new Customer(card.getPan(), true, "", LocalDate.of(Year.MAX_VALUE, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()), card.getNameOnCard());
                    } catch (DateTooLongNoUnlimitedAccountException | CustomerNoUnlimitedAccount ex) {}
                    newCustomer = true;
                } 
                
                // 1. user has an unlimited account?
                if (!newCustomer && customer.getUnlimitedAccount()) { // 1. yes
                        view.printMessage(msgTakeCard);
                        machine.releaseCardReader();
                        view.printMessage(msgErrorCustomerHasAnUnlimitedAccount);
                        view.promptEnterKey();
                } else { // 1. no
                        // 2. user has not returned movies rented previously? AND customer cancelled the unlimited accout but (s)he still had the account before, the subscription is not finish
                        // , so the current date is before the subscription end date, this validation is for customers who do not have an unlimited account and their subscription already finished, they need to return all the movies, this 
                        // validation allows customer who cancelled the premium account re open the account if the subscription did not finish...
                        if ((!newCustomer && setUp.getQuantityOfRentalsNotReturnedByCustomer(customer.getIdCustomer()) > 0) && // 2. no
                           (!customer.getUnlimitedAccount() || (customer.getEndDateOfSubscriptionPeriod() != null && customer.getEndDateOfSubscriptionPeriod().isBefore(LocalDate.now())))) {
                            view.printMessage(msgErrorCustomerNotReturnedAllTheMovies);
                            view.promptEnterKey();  
                        } else { // 2. yes
                            // create payment a proceed....
                            // create payment
                            Payment payment = new Payment(LocalDate.now(), o.getTotalPrice(), msgOpenService + System.lineSeparator() + o.getDescription() + System.lineSeparator() + msgUnlimitedAccountCost + o.getTotalPrice() + " euros", customer);
                            view.printMessage(msgProcessingPayment);
                            // open unlimited account
                            payment.openUnlimitedAccount(card);
                            view.printMessage(msgPaymentCompleted);
                            // release card and thus user can take it from the card reader 
                            view.printMessage(msgTakeCard);
                            machine.releaseCardReader();
                            
                            view.printMessage(msgCustomerUpdate);
                            // save customer in the database
                            if (!newCustomer) {
                            
                                customer.openAccount();
                                
                                setUp.updateUnlimitedAccount(customer);
                                
                            } else setUp.save(customer);
                            
                            setUp.save(payment);
                            
                            view.printMessage(msgCustomerCompleted);
                            // send receipt
                            sendReceipt(customer, payment);
                            
                            view.printMessage(msgOpenService);
                            view.promptEnterKey();

                        }
                        
                }
                        
                    
            }
               
               
        }
           
           
    }

    private void closeUnlimitedAccount() throws DBException {

       // get special Offer for renting unlimited account 
       Offer o = setUp.getOfferUnlimitedAccount();

       // show the description about this offer to the user
       view.printMessage(o.getDescription());
       // show information about cancellation
       view.printMessage(msgCancelInfo);
       
       // ask the user if they want to get it
       boolean yes = view.askQuestionYesOrNo(msgCancelUnlimitedAccount);
       
       if (yes) {
           
                // prepare machine to work with the card
                machine.prepareCardReader();
                
                // user inserts cards 
                Card card = insertCard();                    
                    
                if (card == null) { // error related with the card
                        
                    machine.prepareCardReader();
                    view.printMessage(msgErrorCardDetails);
                    view.promptEnterKey();
                    return;
                        
                }
                
                // get customer from card details PAN
                Customer customer = setUp.getCustomerById(card.getPan());
                
                if (customer == null) { // user does not exist, error
                    view.printMessage(msgErrorNotUnlimitedAccount);
                    view.promptEnterKey();
                } else { // user exist
                    
                    if (customer.getUnlimitedAccount() || (customer.getEndDateOfSubscriptionPeriod() != null && customer.getEndDateOfSubscriptionPeriod().isAfter(LocalDate.now()))) { // user has an unlimited account
                        
                        try {
                            // close account
                            customer.closeAccount();
                        } catch (CustomerNoUnlimitedAccount ex) {
                            
                           view.printMessage(ex.getMessage());
                           view.promptEnterKey();
                           return;
                            
                        }
                        
                        // create payment, show messages and update customer
                        Payment payment = new Payment(LocalDate.now(), BigDecimal.ZERO, msgCancellationUnlimitedAccount1 + customer.getEndDateOfSubscriptionPeriod().format(DateTimeFormatter.ISO_LOCAL_DATE) + msgCancellationUnlimitedAccount2, customer);
                        view.printMessage(msgPlanClosedWait); 

                        payment.closeUnlimitedAccount(card);
                        
                        view.printMessage(msgPlanClosed);
                                                
                        setUp.updateUnlimitedAccount(customer);
                        
                        view.printMessage(msgCancellationUnlimitedAccount1 + customer.getEndDateOfSubscriptionPeriod().format(DateTimeFormatter.ISO_LOCAL_DATE) + msgCancellationUnlimitedAccount2);
                        view.promptEnterKey();
                        
                    } else { // user does not have an unlimited account, error
                        view.printMessage(msgErrorNotUnlimitedAccount);
                        view.promptEnterKey();
                    } 
                 
                }          
       }


    }
       
    
                    
    
}


    
    
    
    

