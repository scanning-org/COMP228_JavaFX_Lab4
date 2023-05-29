package exercise1.brunomorgado_comp228lab4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Controller Class
public class StudentController implements Initializable{

    //Scene Components
    @FXML
    private TextArea taStudentSummary;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox cbCourses;
    @FXML
    private ToggleGroup Program;
    @FXML
    private RadioButton rbComputerScience;
    @FXML
    private RadioButton rbBusiness;
    @FXML
    private ListView listView;
    @FXML
    private CheckBox studentCouncil;
    @FXML
    private CheckBox volunteerWork;


    //Method to initialize the process
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        //Call method to populate the ComboBox
        populateComboBox();

        //Event Handler for ComputerScience Radio Button
        rbComputerScience.setOnAction((event)->{
            populateComboBox();
            listView.getItems().clear();
            taStudentSummary.clear();
        });
        //Event Handler for Business Radio Button
        rbBusiness.setOnAction((event) ->{
           populateComboBox();
            listView.getItems().clear();
           taStudentSummary.clear();
        });

        //Event Handler for Courses ComboBox
        cbCourses.setOnAction((event)->{

            if(cbCourses.getValue() != null){
                String value = cbCourses.getValue().toString();
                populateTextArea(value);
            }


        });

    };


    //Method to populate the ComboBox according to Radio Button selection
    public void populateComboBox(){

        if(rbComputerScience.isSelected()){
            cbCourses.getItems().clear();
            cbCourses.getItems().addAll("Java", "Python", "C#", "Ruby");

        }else {
            cbCourses.getItems().clear();
            cbCourses.getItems().addAll("Finance", "Project Management", "Tax System", "Technical Analysis");

        }

    }

    //Method to populate the LIstView
    @ControllerAdvice
    public void populateTextArea(String course){

        if(listView.getItems().contains(course)){
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("This course has already been selected");
            fail.showAndWait();
        }else {
            listView.getItems().add(course);
        }

    }

    //Method to validate if all fields are filled in
    private boolean validateFields(){

        if(txtName.getText() == null || txtName.getText().trim().isEmpty() ||
                txtAddress.getText() == null || txtAddress.getText().trim().isEmpty() ||
                txtProvince.getText() == null || txtProvince.getText().trim().isEmpty() ||
                txtCity.getText() == null || txtCity.getText().trim().isEmpty() ||
                txtPostalCode.getText() == null || txtPostalCode.getText().trim().isEmpty() ||
                txtPhoneNumber.getText() == null || txtPhoneNumber.getText().trim().isEmpty() ||
                txtEmail.getText() == null || txtEmail.getText().trim().isEmpty()){

            Alert fail= new Alert(Alert.AlertType.WARNING);
            fail.setHeaderText("failure");
            fail.setContentText("Please fill in all the fields");
            fail.showAndWait();

            return false;
        }else if(listView.getItems() == null || listView.getItems().isEmpty()){
            Alert fail= new Alert(Alert.AlertType.WARNING);
            fail.setHeaderText("failure");
            fail.setContentText("Please Select your courses");
            fail.showAndWait();

            return false;
        }

        return true;
    }

    //Email validation
    public boolean validateEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txtEmail.getText());

        if(m.find() && m.group().equals(txtEmail.getText())){
            return true;
        }
        else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid email");
            alert.setContentText("Please enter a valid email");
            alert.showAndWait();

            return false;
        }
    }

    //Name Validation
    public boolean validateName(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txtName.getText());

        if(m.find() && m.group().equals(txtName.getText())){
            return true;
        }
        else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid Name");
            alert.setContentText("Please enter a Name");
            alert.showAndWait();

            return false;
        }
    }

    //Phone Vallidation
    public boolean validatePhone(){
        Pattern p = Pattern.compile("[4-9][0-9]{9}");
        Matcher m = p.matcher(txtPhoneNumber.getText());

        if(m.find() && m.group().equals(txtPhoneNumber.getText())){
            return true;
        }
        else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid Phone Format");
            alert.setContentText("Please enter a valid Phone Number");
            alert.showAndWait();

            return false;
        }
    }

    //Method to retrieve the value of the selected Radio Button
    public String getRadioButton(){
        RadioButton selectedRadioButton = (RadioButton) Program.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        return toggleGroupValue;
    }

    //Method to retrieve the values from the CheckBoxes
    public String getAdditionalActivities(){

        String additionalActivities = "";

        if(studentCouncil.isSelected()){
            additionalActivities += studentCouncil.getText() + "\n";
//
        }
        if(volunteerWork.isSelected()){
            additionalActivities += volunteerWork.getText() + "\n";
        }

        return additionalActivities;

    }

    //Method to display a summary of all filled in / selected fields
    public void displayStudent(){

        if(validateFields() && validateEmail() && validateName() && validatePhone()){

            taStudentSummary.setText("STUDENT:\t\t" + txtName.getText()+ "\n" + "ADDRESS:\t\t" + txtAddress.getText() +", "
                    + txtProvince.getText().toUpperCase() + ", " + txtCity.getText() + ", " + txtPostalCode.getText() + "\n"
                    + "PHONE:\t\t" + txtPhoneNumber.getText() + "\n" + "EMAIL:\t\t\t" + txtEmail.getText().toLowerCase() + "\n" +
                    "\nMAJOR:\t\t" + getRadioButton() + "." + "\n" +
                    "\nCOURSES: " + "\t\t" + listView.getItems() +
                    "\n\nADDITIONAL ACTIVITIES: " + "\n\n" + getAdditionalActivities());

        }


    }

}