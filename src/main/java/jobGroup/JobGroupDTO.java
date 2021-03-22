package jobGroup;

import cap.CapDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import filter.Filter;
import filter.JobFilter;
import filter.Main;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class JobGroupDTO {
    public JobGroupParams jobGroupParams;

    public JobGroupDTO(String name,String campaignId,String clientId) throws JsonProcessingException {
        jobGroupParams = new JobGroupParams(name,campaignId,clientId);
    }

    public static class JobGroupParams {

        public String name;
        public String campaignId;
        public LocalDate startDate;
        public LocalDate endDate;
        public int priority;
        public String category;
        public int cpcBid;
        public int cpaBid;
        public String clientId;
        public List<String> clientIds;
        public String sign;
        public Object filters;
        public CapDTO budgetCap;
        public CapDTO clicksCap;
        public CapDTO appliesCap;
        public OverspendCap overspendCap;
        public CapDTO jobBudgetCap;
        public CapDTO jobClicksCap;
        public CapDTO jobAppliesCap;
        public TradingGoals tradingGoals;
        public List<Placements> placements;
        public RecommendationAudit recommendationAudit;
        public List<Integer> daysToSchedule;
        public boolean isPPC;

        public JobGroupParams(String name,String campaignId,String clientId) throws JsonProcessingException {
            this.name = name;
            this.campaignId = campaignId;
            this.startDate = LocalDate.now();
            this.endDate = LocalDate.now().plusWeeks(1);
            this.priority = 5;
            this.category = "";
            this.cpcBid = 1;
            this.cpaBid = 2;
            this.clientId = clientId;

            this.clientIds = new ArrayList<>();

            this.clientIds.add(clientId);

            this.sign = "$";

            this.filters = new Main().base();

            this.budgetCap = new CapDTO();
            this.clicksCap = new CapDTO();
            this.appliesCap = new CapDTO();

            this.overspendCap = new OverspendCap(clientId);

            this.jobBudgetCap = new CapDTO();

            this.jobClicksCap = new CapDTO();

            this.jobAppliesCap = new CapDTO();

            this.tradingGoals = new TradingGoals();

            this.placements = new ArrayList<>();
            this.placements.add(new Placements());

            recommendationAudit = new RecommendationAudit();
            this.daysToSchedule = new ArrayList<>();
            this.daysToSchedule.add(0);
            this.daysToSchedule.add(2);
            this.daysToSchedule.add(4);
            this.daysToSchedule.add(5);
            this.daysToSchedule.add(6);

            this.isPPC = true;

        }

        public static class OverspendCap {
            public String status;
            public int maxJobCount;
            public String clientId;
            public String filterBy;
            public String orderBy;
            public int timePeriodSec;

            public OverspendCap(String clientId)
            {
                this.status = "A";
                this.maxJobCount = 10;
                this.clientId = clientId;
                filterBy = "SPEND";
                orderBy = "ASCENDING";
                timePeriodSec = 2592000;

            }

        }

            public static class TradingGoals {
                public List<IoDetails> ioDetails;

                public List<PerformanceTargets> performanceTargets;

                public TradingGoals()
                {

                    ioDetails = new ArrayList<>();
                    ioDetails.add(new IoDetails());

                    performanceTargets = new ArrayList<>();
                    performanceTargets.add(new PerformanceTargets("cpc",3));
                    performanceTargets.add(new PerformanceTargets("cpa",4));


                }

                public static class IoDetails {
                    public String number;

                    public int value;

                    public LocalDate startDate;

                    public LocalDate endDate;

                    public IoDetails()
                    {
                        this.number = "10000";
                        this.value = 1000;
                        this.startDate = LocalDate.now().plusWeeks(3);
                        this.endDate = LocalDate.now().plusYears(2);
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

                            public Placements()
                            {
                                this.pValue = "pg";
                            }
                        }


                            public static class RecommendationAudit {
                                public List<String> result;

                                public List<String> acceptedResult;

                                public RecommendationAudit()
                                {
                                    this.result = new ArrayList<>();

                                    this.acceptedResult = new ArrayList<>();

                                }

                            }
                        }
                    }






