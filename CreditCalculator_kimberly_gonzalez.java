package creditcalculator_kimberly_gonzalez;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author kgonz038
 */
public class CreditCalculator_kimberly_gonzalez extends Application 
{
    //Field for the empty label that will display the user's info and calculation
    private Label displayInfo;
    
    //Fields for the radio buttons that the user will choose between
    private RadioButton underGrad;
    private RadioButton grad;
    
    //Fields for the text fields the user will type their response in
    private TextField majorTxt;
    private TextField reqCredTxt;
    private TextField earnedCredTxt;
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) 
    {
       //Label and text field regarding the user's Major
       Label majorL = new Label("Enter Major:"); 
       majorTxt = new TextField();
        
       //Label and text field regarding the user's required credits
       Label reqCredL = new Label("Enter total required credits:");
       reqCredTxt = new TextField();
        
       //Label and text field regarding the user's earned credits
       Label earnedCredL = new Label("Enter the earned credits:");
       earnedCredTxt= new TextField();
       
       //Empty label that will display the user's info and credit calculations
       displayInfo = new Label();
       
       //RadioButtons that the user will choose between regarding their program
       underGrad = new RadioButton("Undergraduate");
       grad = new RadioButton("Graduate");
       
       underGrad.setSelected(true);

       //Creates a toggle group allow one radio button to be selected at a time
       ToggleGroup radioButtons = new ToggleGroup();
       
       //Adds the two radio buttons to the toggle group
       underGrad.setToggleGroup(radioButtons);
       grad.setToggleGroup(radioButtons);
        
       //Creates a Button to activate the credit calculation and user info
       Button calcCred = new Button("Calculate Remaining Credit Hours");

       //Register the event handler for clicking the credit calculation button
       calcCred.setOnAction(new calcCredButtonHandler());

       //Creates an Image object with an online TAMUSA logo link
       Image TAMUSAImage = new Image("https://i.imgur.com/EfAIR2g.jpg");
       
       //Creates an ImageView object that will display the TAMUSA logo image
       ImageView imageView = new ImageView(TAMUSAImage);

       //Changes the logo image width to 600 while perserving the aspect ratio
       imageView.setFitWidth(600);
       imageView.setPreserveRatio(true);

       //Creates a gridpane, the containers are organized with columns and rows
       GridPane gridpane = new GridPane();

       //Adds the 'major' related controls at the specified grid positions
       gridpane.add(majorL, 3, 0);
       gridpane.add(majorTxt, 4, 0);

       gridpane.add(reqCredL, 3, 1);
       gridpane.add(reqCredTxt, 4, 1);

       gridpane.add(earnedCredL, 3, 2);
       gridpane.add(earnedCredTxt, 4, 2);

       gridpane.setVgap(20);
       gridpane.setHgap(10);

       gridpane.setPadding(new Insets(20));
       gridpane.setAlignment(Pos.CENTER);

       VBox vbox = new VBox(20, imageView, underGrad, grad, gridpane, 
               calcCred, displayInfo);

       vbox.setAlignment(Pos.CENTER);
       vbox.setPadding(new Insets(10));
       Scene scene = new Scene(vbox, 900, 600);

       scene.getStylesheets().add("creditcalculator_kimberly_gonzalez/CreditCalculatorStyle_kimberly_gonzalez.css");
       //

       primaryStage.setTitle("TAMUSA Credit Calculator");
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
    class calcCredButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
          String program = "";
          
          String major = majorTxt.getText();
          
         //The text field grabs
         int reqCredits = Integer.parseInt(reqCredTxt.getText());
         
         int earnedCredits = Integer.parseInt(earnedCredTxt.getText());
         
         //Calculates the total tip and assigns it to tipCalcTotal
         int credCalc = reqCredits - earnedCredits;
         
         if (underGrad.isSelected())
             program = "You are in the Undergraduate program. ";
         
         if (grad.isSelected())
             program = "You are in the Graduate program. ";
         
         //The calculation of the tip is then set to the empty label
         displayInfo.setText(program + "Your major is " + major + ". " +
                 "Your total remaining credits are: " + credCalc + ".");
      }
}
}

