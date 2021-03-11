package BeanValidation.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.checkerframework.checker.optional.qual.Present;

import javax.validation.Valid;
import javax.validation.constraints.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class CampaignDTO {


    @Valid
    @NotNull(message = "CampaignParams can't be null")
    public CampaignParams params;

  public  static class CampaignParams {

        @NotNull(message = "clientId can't be null")
        public String clientId;

        @Size(min = 1,message = " clientIds size should be at least 1")
        @NotNull(message = "clientIds can't be null")
        public List<String> clientIds;

        @NotNull(message ="name can't be null")
        public String name;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message ="startDate can't be null")

        @FutureOrPresent
        public LocalDate startDate;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message ="endDate can't be null")

        @Future(message = "end date must be a future date")
        public LocalDate endDate;


      @AssertTrue public boolean isValidRange() {
          return endDate.compareTo(startDate) >= 0;
      }

        @NotNull(message ="budgetCap can't be null")
        @Valid
        public CapDTO budgetCap;

        CampaignParams(String clientId) {
            this.clientId = clientId;
            this.clientIds = new ArrayList<String>();
            clientIds.add(clientId);
            this.startDate = LocalDate.now();
            this.endDate=LocalDate.now().plusWeeks(1);
        }
    }

    public CampaignDTO(String clientId) {
        this.params = new CampaignParams(clientId);
    }

    public void setName(String name){
        this.params.name=name;
    }

    public void setBudget(int budget) {
        params.budgetCap = new CapDTO();
        params.budgetCap.setValue(budget);
        params.budgetCap.setFreq(Frequency.Monthly);
        params.budgetCap.setPacing(false);
    }

    public void setEndDate(LocalDate endDate){
      params.endDate=endDate;
    }

    public void setStartDate(LocalDate startDate){
      params.startDate=startDate;
    }
}
