package Client;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
import java.io.Serializable;
import java.util.*;

public class Template implements Serializable {
    private static final long serialVersionUID = 1L;
    private String header;
    private String description;
    private String consdesc;
    private int constraint;

    private Tuple<String, Boolean> option1;

    private Tuple<String, Boolean> option2;
    private Tuple<String, Boolean> option3;
    private Tuple<String, Boolean> option4;
    private Tuple<String, Boolean> option5;
    private Tuple<String, Boolean> button1;
    private Tuple<String, Boolean> button2;
    private Tuple<String, Boolean> button3;
    private List<Tuple<String, Boolean>> options;
    private List<Tuple<String, Boolean>> buttons;

    public Template() {




    }

    public String getHeader() {
        return header;
    }
    public void setHeader(String header){this.header = header;}
    private void setConsdesc(){
        if(constraint == 0){
            consdesc = "";

        } else    if(constraint == 1){
            consdesc = "select 1 option";

        }
        else {
            consdesc = "Select at most " + constraint + " options";
        }
    }
    public String getConsdesc(){return consdesc;}

    public String getDescription() {
        if(description == null)
            description = "";
        return description;
    }
    public void setDescription(String description){this.description = description;}
    public int getConstraint() {
        return constraint;
    }
    public void setConstraint(int con){
        constraint = con;
        setConsdesc();
    }

    public void setOptions(String a,String b,String c,String d,String e){
        option1 = new Tuple<>(a, false);
        option2 = new Tuple<>(b, false);
        option3 = new Tuple<>(c, false);
        option4 = new Tuple<>(d, false);
        option5 = new Tuple<>(e, false);
        options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
    }

    public void setButtons(String a,String b,String c){
        button1 = new Tuple<>(a, false);
        button2 = new Tuple<>(b, false);
        button3 = new Tuple<>(c, false);
        buttons = new ArrayList<>();

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

    }

    public Tuple getOptions(int index){
        Tuple<String, Boolean> nothing = new Tuple<>("", false);
                if(options.get(index)==null){
                    return  nothing;
                }
        return options.get(index);
    }
    public Tuple getButtons(int index){
        Tuple<String, Boolean> nothing = new Tuple<>("", false);
        if(buttons.get(index)==null) {
            return nothing;
        }
        return buttons.get(index);
    }

<<<<<<< HEAD
    public Template votingTemplate(Template votingTemplate, String[] headersList, String[] optionsList){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader(headersList[0]);
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions(optionsList[0], optionsList[1], optionsList[2], optionsList[3], optionsList[4]);
        votingTemplate.setButtons("","","Next");
        return votingTemplate;
    }
    public Template votingTemplateII(Template votingTemplate,  String[] headersList, String[] optionsList){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader(headersList[1]);
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions(optionsList[0], optionsList[1], optionsList[2], optionsList[3], optionsList[4]);
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateIII(Template votingTemplate,  String[] headersList, String[] optionsList){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader(headersList[2]);
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions(optionsList[0], optionsList[1], optionsList[2], optionsList[3], optionsList[4]);
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateIV(Template votingTemplate,  String[] headersList, String[] optionsList){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader(headersList[3]);
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions(optionsList[0], optionsList[1], optionsList[2], optionsList[3], optionsList[4]);
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateV(Template votingTemplate,  String[] headersList, String[] optionsList){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader(headersList[4]);
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions(optionsList[0], optionsList[1], optionsList[2], optionsList[3], optionsList[4]);
=======
    public Template votingTemplate(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("President");
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions("Donald J Trump", "Kamala Harris", "Barack Obama", "Hilly Hill", "Carlos Herrera");
        votingTemplate.setButtons("","","Next");
        return votingTemplate;
    }
    public Template votingTemplateII(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("Vice-President");
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions("JD Vance", "Harry Potter", "Will Wilson", "Jay Z", "CB D");
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateIII(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("State Senator");
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions("Janis Quinlan", "Katie Sieben", "Gary Kreiesel", "John Rheinberger", "Sarah Hietpas");
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateIV(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("Mayor");
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions("Pat Synder", "Bill Palmquist", "Amy Burback", "Kathy Buchhloz", "George F. Dierberger");
        votingTemplate.setButtons("Previous","","Next");
        return votingTemplate;
    }
    public Template votingTemplateV(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("Council Member'");
        votingTemplate.setDescription("Please select an options");
        votingTemplate.setOptions("Micheal B", "Steve Carlson", "Toby Hernandez", "Betty Mccollum", "Hilton H");
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
        votingTemplate.setButtons("Previous","Submit","");
        return votingTemplate;
    }

<<<<<<< HEAD
    public Template adminTemplate(Template adminTemplate){
        adminTemplate.setConstraint(1);
        adminTemplate.setHeader("Welcome to Admin Panel");
        adminTemplate.setDescription("Menu");
        adminTemplate.setOptions("", "Enable Voting", "Open Voting Session", "End Voting Session", "Disable Voting");
        adminTemplate.setButtons("","Ok","");
        return adminTemplate;
    }
    public Template confirm (Template adminTemplate){
        adminTemplate.setHeader("Vote Confirmation");
        adminTemplate.setOptions("","","VOTE IS CONFIRMED", "", "Vote is being recording. Please Wait!!!!");
        adminTemplate.setButtons("","Thank You","");
        return adminTemplate;
    }
    public Template enableVoting (Template adminTemplate){
        adminTemplate.setHeader("Admin Settings");
        adminTemplate.setOptions("","","Voting is Being Enable......", "", "Please Wait!!!!");
        adminTemplate.setButtons("","Thank You","");
        return adminTemplate;
    }
    public Template openVoting (Template adminTemplate){
        adminTemplate.setHeader("Admin Settings");
        adminTemplate.setOptions("","","Voting is Being Opened......", "", "Now Latch is being locked....");
        adminTemplate.setButtons("","Thank You","");
        return adminTemplate;
    }
    public Template closeVoting (Template adminTemplate){
        adminTemplate.setHeader("Admin Settings");
        adminTemplate.setOptions("","","Voting is Being Closed......", "", "Please Wait!!!!");
        adminTemplate.setButtons("","Thank You","");
        return adminTemplate;
    }
    public Template disableVoting (Template adminTemplate){
        adminTemplate.setHeader("Admin Settings");
        adminTemplate.setOptions("","","Voting Session is Disabled.....", "", "Please Wait!!!!");
        adminTemplate.setButtons("","Thank You","");
        return adminTemplate;
    }
    public Template enableVotingFirst(Template warningTemp){
        warningTemp.setHeader("WARNING............");
        warningTemp.setOptions("..............","","Enable Voting Session First","","................");
        warningTemp.setButtons("","Thank You", "");
        return warningTemp;
    }
    public Template rejectCard (Template rejectTem){
        rejectTem.setHeader("Voting Will Start Soon.......");
        rejectTem.setOptions("............","","Please Check Back Later.....","","..............");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template adminClose (Template rejectTem){
        rejectTem.setHeader("WARNING.......");
        rejectTem.setOptions("............","","Voting is not opened yet........","","..............");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template adminDisable(Template rejectTem){
        rejectTem.setHeader("WARNING.......");
        rejectTem.setOptions("............","","Close Before Disabling Session","","..............");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template cardReaderFailure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","Card Reader Failure!!!!!!!!!!!!!","","Voting Machine is Shutting Down....");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template printerFailure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","Printer Failure!!!!!!!!!!!!!!!","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template tamperFailure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","Tamper Driver Failure!!!!!!!!!!!!!!!","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template screenFailure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","Something went wrong with Screen!!!!!!!!!!!!!!!","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template sdW1w2Failure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","SD_Card W1/W2 is failed!!!!!!!!!!!!!!!","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template sdRFailure(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","SD_Card Read Only is failed!!!!!!!!!!!!!!!","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }
    public Template tampered(Template rejectTem){
        rejectTem.setHeader("WARNING.......WARNING.....");
        rejectTem.setOptions("............","","Tampered Detected.........","","Voting Machine is Shutting Down");
        rejectTem.setButtons("","Thank You", "");
        return rejectTem;
    }



=======
    public Template adminTemplate(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("Welcome to Admin Panel");
        votingTemplate.setDescription("Menu");
        votingTemplate.setOptions("Machine Setup", "Open Voting", "Open Voting Session", "End Voting Session", "Close Voting");
        votingTemplate.setButtons("","","");
        return votingTemplate;
    }
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

}
