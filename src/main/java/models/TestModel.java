package models;

public class TestModel {
    int answer;
    String text;

    public TestModel(int answer, String text) {
        this.answer = answer;
        this.text = text;
    }

    @Override
    public String toString()
    {
        String answerMsg = "";
        if(answer==0)
            answerMsg = "Anuluj";
        else if(answer==1)
            answerMsg = "Tak";
        else if(answer==2)
            answerMsg = "Nie";

        return answerMsg + (text!=null?", " + text:"");
    }
}
