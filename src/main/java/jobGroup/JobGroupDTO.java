package jobGroup;

import apple.laf.JRSUIConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import filter.JobFilterDTO;
import filter.Converter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class JobGroupDTO {

    @Valid
    public JobGroupParams params;

    public JobGroupDTO(String name,String campaignId,String clientId) throws JsonProcessingException {
        params = new JobGroupParams(name,campaignId,clientId);
    }

    public void setStartDate(LocalDate startDate) {
        params.startDate = startDate;
    }

    public void setEndDate (LocalDate endDate) {
        params.endDate = endDate;
    }

    public void setPriority(int priority) {
        params.priority = priority;
    }

    public void setCategory(String category) {
        params.category = category;
    }

    public void cpcBid (int cpcBid) {
        params.cpcBid = cpcBid;
    }

    public void cpaBid (int cpaBid){
        params.cpaBid = cpaBid;
    }

    public void setSign(String sign) {
        params.sign = sign;
    }

    public void setBudgetCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.budgetCap = new CapDTO(pacing,frequency,threshold,value);
    }

    public void setClickCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.clicksCap = new CapDTO(pacing,frequency,threshold,value);
    }

    public void setApplyCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.appliesCap = new CapDTO(pacing,frequency,threshold,value);
    }

    public void setJobBudgetCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.jobBudgetCap = new CapDTO(pacing,frequency,threshold,value);
    }


    public void setJobClickCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.jobClicksCap = new CapDTO(pacing,frequency,threshold,value);
    }

    public void setJobApplyCap(Boolean pacing,Frequency frequency,int threshold,int value) {
        params.jobAppliesCap = new CapDTO(pacing, frequency, threshold, value);
    }


    public void setJobFilter(JobFilterDTO filter) throws JsonProcessingException {

        // dto convert JobfilterDTO to Required Payload
        params.filters = new Converter().dtO(filter);
    }

    public void addIoDetail(String number,int value,LocalDate startDate,LocalDate endDate)
    {

        JobGroupParams.TradingGoals.IoDetails ioDetails =new JobGroupParams.TradingGoals.IoDetails(number,value,startDate,endDate);

        if(params.tradingGoals.ioDetails==null) {
            params.tradingGoals.ioDetails = new ArrayList<>();
        }

        params.tradingGoals.ioDetails.add(ioDetails);

    }

    public void addPerformanceTargets(String type,int value)
    {
        params.tradingGoals.performanceTargets.add(new JobGroupParams.TradingGoals.PerformanceTargets(type,value));
    }

    public void addPlacement(String pValue)
    {
        if(params.placements==null)
            params.placements = new ArrayList<>();

        params.placements.add(new JobGroupParams.Placements(pValue));
    }

    public void setIsPPC(Boolean isPPC)
    {
        params.isPPC = isPPC;
    }


    public static class JobGroupParams {


        @NotNull(message = "JobGroup name can't be null")
        public String name;

        @NotNull(message = "campaignId name can't be null")
        public String campaignId;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message = "startDate can't be null")
        @FutureOrPresent(message ="startDate can't be past")
        public LocalDate startDate;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message ="endDate can't be null")
        @FutureOrPresent(message ="endDate can't be past")
        public LocalDate endDate;

        @Min(value = 1,message = "JobGroupPriority must bw >=1")
        @Max(value = 10,message = "JobGroupPriority must bw <=10")
        @NotNull(message = "set Priority")
        public Integer priority;

        @NotNull(message = "category name can't be null")
        public String category;

        @Min(value = 0)
        @NotNull(message = "set cpcBid")
        public Integer cpcBid;

        @Min(value = 0)
        @NotNull(message = "set cpaBid")
        public Integer cpaBid;

        @NotNull(message = "clientId can't be null")
        public String clientId;

        @Size(min = 1,max = 1,message = "list of clientIds can't be greater than 1")
        @NotNull(message = "list of clientId can't be null")
        public List<String> clientIds;

        @NotNull(message = "sign can't be null")
        public String sign;

        @Valid
        @NotNull(message = "JobFilter can't be null")
        public Object filters;


        @NotNull(message = "budgetCap can't be null")
        public CapDTO budgetCap;

        @NotNull(message = "clickCap can't be null")
        public CapDTO clicksCap;

        @NotNull(message = "applyCap can't be null")
        public CapDTO appliesCap;

        @NotNull(message = "overspendCap can't be null")
        public OverspendCap overspendCap;


        @JsonInclude(JsonInclude.Include.NON_NULL)
        public CapDTO jobBudgetCap;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public CapDTO jobClicksCap;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public CapDTO jobAppliesCap;

        @NotNull(message = "tradingGoals can't be null")
        @Valid
        public TradingGoals tradingGoals;

        @NotNull(message = "placements can't be null")
        @Size(min = 1,message = "There must be 1 placement")
        @Valid
        public List<Placements> placements;

        @NotNull(message = "recommendationAudit can't be null")
        public RecommendationAudit recommendationAudit;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public List<Integer> daysToSchedule;

        public boolean isPPC;

        @AssertTrue(message = "startDate must be <= endDate")
        @JsonIgnore
        public boolean isValidRange() {

            return endDate.compareTo(startDate) >= 0;
        }

        @AssertTrue(message = "Pacing is invalid, check Caps, Pacing is available only for monthly")
        @JsonIgnore
        public boolean isValidPacing()
        {
          if(budgetCap!=null&&budgetCap.pacing&& !(budgetCap.freq == Frequency.Monthly))
              return false;
           else if(clicksCap!=null&&clicksCap.pacing&&!(clicksCap.freq==Frequency.Monthly))
               return false;
          else if(appliesCap!=null&&appliesCap.pacing&&!(appliesCap.freq==Frequency.Monthly))
              return false;
          else if(jobBudgetCap!=null&&jobBudgetCap.pacing&&!(jobBudgetCap.freq==Frequency.Monthly))
              return false;
          else if(jobAppliesCap!=null&&jobAppliesCap.pacing&&!(jobAppliesCap.freq==Frequency.Monthly))
              return false;
          else if(jobClicksCap!=null&&jobClicksCap.pacing&&!(jobClicksCap.freq==Frequency.Monthly))
              return false;
          else
              return true;
        }

        @AssertTrue(message = "ClickCap is Invalid, there is no daily option in ClickCap")
        @JsonIgnore
        public boolean isValidClickCap()
        {


            return clicksCap == null || clicksCap.freq != Frequency.Daily;

        }

        @AssertTrue(message = "ApplyCap is Invalid, there is no daily option in ApplyCap")
        @JsonIgnore
        public boolean isValidCApplyCap()
        {

            return appliesCap == null || appliesCap.freq != Frequency.Daily;

        }


        public JobGroupParams(String name,String campaignId,String clientId) throws JsonProcessingException {
            this.name = name;
            this.campaignId = campaignId;
            this.startDate = LocalDate.now();
            this.endDate = LocalDate.now().plusWeeks(1);

            this.category = "";
            this.clientId = clientId;

            this.clientIds = new ArrayList<>();

            this.clientIds.add(clientId);

            this.sign = "$";


            this.overspendCap = new OverspendCap(clientId);

            this.tradingGoals = new TradingGoals();


            recommendationAudit = new RecommendationAudit();

            this.isPPC = true;

        }



        public static class OverspendCap {
            public String status;
            public int maxJobCount;
            public String clientId;
            public String filterBy;
            public String orderBy;
            public int timePeriodSec;

            //Default One
            public OverspendCap(String clientId)
            {
                this.status = "I";
                this.maxJobCount = 10;
                this.clientId = clientId;
                filterBy = "CPA";
                orderBy = "ASCENDING";
                timePeriodSec = 2592000;
            }
        }

            public static class TradingGoals {

               @NotNull(message ="There must be at least one IoDetail")
                public List<IoDetails> ioDetails;

                public List<PerformanceTargets> performanceTargets;

                public TradingGoals()
                {
                   performanceTargets = new ArrayList<>();
                }

                public static class IoDetails {
                    public String number;

                    public int value;

                    public LocalDate startDate;

                    public LocalDate endDate;



                    public  IoDetails(String number,int value,LocalDate startDate,LocalDate endDate)
                    {
                        this.number = number;
                        this.value = value;
                        this.startDate = startDate;
                        this.endDate = endDate;
                    }
                }


                public static class PerformanceTargets {
                    public String type;
                    public int value;

                    public PerformanceTargets(String type,int value)
                    {
                        this.type = type;
                        this.value = value;
                    }
                }
            }

                        public static class Placements {
                            public String pValue;

                            public Placements(String pValue)
                            {
                                this.pValue = pValue;
                            }
                        }


                            public static class RecommendationAudit {

                                @NotNull
                                public List<String> result;

                                @NotNull
                                public List<String> acceptedResult;

                                public RecommendationAudit()
                                {
                                    this.result = new ArrayList<>();

                                    this.acceptedResult = new ArrayList<>();

                                }

                            }
                        }
                    }






