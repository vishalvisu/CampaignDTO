package campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


enum Frequency
{
    Daily,Weekly,Monthly;
}

 class BudgetCap
{
    private int value;

    private boolean pacing;

    private Frequency frequency;


    public BudgetCap()
    {
        value=1000;
        pacing=false;
        frequency= Frequency.Monthly;
    }

    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public void setPacing(boolean pacing){
        this.pacing = pacing;
    }
    public boolean getPacing(){
        return this.pacing;
    }
    public void setFreq(Frequency freq){
        this.frequency = freq;
    }
    public Frequency getFreq(){
        return this.frequency;
    }
}

 class Params
{
    private String clientId;

    private List<String> clientIds;

    private String name;

    @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
    public LocalDate startDate;

   @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
    public LocalDate endDate;

    private BudgetCap budgetCap;

    public Params(String Name,String ClientId)
    {
        clientId=ClientId;

        List<String> listClientIds= new ArrayList<>();
        listClientIds.add(clientId);
        clientIds=listClientIds;

        name=Name;


        startDate=LocalDate.now();
        endDate=LocalDate.from(startDate).plusWeeks(1);

        budgetCap= new BudgetCap();
    }


    public void setClientId(String clientId){
        this.clientId = clientId;
    }
    public String getClientId(){
        return this.clientId;
    }
    public void setClientIds(List<String> clientIds){
        this.clientIds = clientIds;
    }
    public List<String> getClientIds(){
        return this.clientIds;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }
    public LocalDate getEndDate(){
        return this.endDate;
    }
    public void setBudgetCap(BudgetCap budgetCap){
        this.budgetCap = budgetCap;
    }
    public BudgetCap getBudgetCap(){
        return this.budgetCap;
    }
}

public class CampaignDTO
{
    private Params params;

    public CampaignDTO(String name,String clientId)
    {
        params= new Params(name,clientId);
    }

    public void setParams(Params params){
        this.params = params;
    }
    public Params getParams(){
        return this.params;
    }

    public  void setBudget(int value,boolean pacing,Frequency freq)
    {
        params.getBudgetCap().setValue(value);
        params.getBudgetCap().setPacing(pacing);
        params.getBudgetCap().setFreq(freq);
    }

    public void setStartDate(LocalDate date)
    {
        params.setStartDate(date);
    }

    public void setEndDate (LocalDate date)
    {
        params.setEndDate(date);
    }

}


