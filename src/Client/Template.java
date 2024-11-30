package Client;

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
        votingTemplate.setButtons("Previous","Submit","");
        return votingTemplate;
    }

    public Template adminTemplate(Template votingTemplate){
        votingTemplate.setConstraint(1);
        votingTemplate.setHeader("Welcome to Admin Panel");
        votingTemplate.setDescription("Menu");
        votingTemplate.setOptions("Machine Setup", "Open Voting", "Open Voting Session", "End Voting Session", "Close Voting");
        votingTemplate.setButtons("","","");
        return votingTemplate;
    }

}
