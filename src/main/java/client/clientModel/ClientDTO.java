package client.clientModel;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@JsonSerialize
public  class ClientDTO {

    @Valid
    public ClientParams params;

    public ClientDTO(String name)
    {
        params = new ClientParams(name);
    }

    public void setBudget(int budget,Frequency frequency,Boolean pacing) {
        params.budgetCap.setValue(budget);
        params.budgetCap.setFrequency(frequency);
        params.budgetCap.setPacing(pacing);
    }


    public void setCountry(String country)
    {
        params.country=country;
    }

    public void setExportedName(String exportedName)
    {
        this.params.exportedName = exportedName;
    }

    public void setAdvertiserName(String advertiserName)
    {
        this.params.advertiserName = advertiserName;
    }

    public void setAts(String ats)
    {
        this.params.ats = ats;
    }

    public void setAtsUrl(String atsUrl)
    {
        this.params.atsUrl = atsUrl;
    }

    public void setFrequency(String frequency)
    {
        this.params.frequency = frequency;
    }

    public void setApplyConvWindow(int applyConvWindow){
        this.params.applyConvWindow = applyConvWindow;
    }

    public void setTimezone (String timezone)
    {
        this.params.timezone = timezone;
    }

    public void setType (String type)
    {
        this.params.type = type;
    }

    public void setIndustry (String industry)
    {
        this.params.industry = industry;

        this.setIndustries(industry);
    }

    public void setIndustries(String industries)
    {
        this.params.industries.clear();
        this.params.industries.add(industries);
    }



    public void setExcludedPublishers (String excludedPublishers)
    {
        this.params.excludedPublishers = excludedPublishers;
    }

    public void setEndDate(LocalDate endDate){
        params.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate){
        params.startDate = startDate;
    }

    public void setMarkDown (String markDown)
    {
        this.params.markdown = markDown;
    }

    public void addFeed (String xmlUrlFeed)
    {
         this.params.addFeed(xmlUrlFeed);
    }

    public void setGloballyExcludedPublishers(String globallyExcludedPublishers)
    {
        this.params.globallyExcludedPublishers = globallyExcludedPublishers;
    }

    public static class ClientParams{

        @NotNull(message = "name can't be null")
        public String name;

        @NotNull(message = "country can't be null")
        public String country;

        @NotNull(message = "exportedName can't be null")
        public String exportedName;

        @NotNull(message = "advertiserName can't be null")
        public String advertiserName;

        @NotNull(message = "ats can't be null")
        public String ats;

        @NotNull(message = "atsUrl can't be null")
        public String atsUrl;

        @NotNull(message = "frequency can't be null")
        public String frequency;

        public int applyConvWindow;

        @NotNull(message = "timezone can't be null")
        public String  timezone;

        @NotNull(message = "type can't be null")
        public String type;

        @NotNull(message = "industry can't be null")
        public String industry;

        @NotNull(message = "excludedPublishers can't be null")
        public String excludedPublishers;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message ="endDate can't be null")
        @FutureOrPresent(message ="endDate can't be past")
        public LocalDate endDate;

        @JsonFormat(pattern = "MM/dd/yyyy",shape = JsonFormat.Shape.STRING)
        @NotNull(message = "startDate can't be null")
        @FutureOrPresent(message ="startDate can't be past")
        public LocalDate startDate;

        @AssertTrue(message = "startDate must be <= endDate")
        @JsonIgnore
        public boolean isValidRange() {
            return endDate.compareTo(startDate) >= 0;
        }

        @AssertTrue(message = "BudgetCap is invalid,pacing is available for monthly")
        @JsonIgnore
        public boolean isValidCap()
        {
            if(budgetCap.pacing == true && budgetCap.frequency == Frequency.Monthly)
                return true;

            return false;
        }

        @NotNull(message = "markdown can't be null")
        public String markdown;

        @Size(min = 1,message = "number of feed must be at least one")
        public List<Feeds> feeds;

        public boolean sjCreate;

        @NotNull(message = "budgetCap can't be null")
        public CapDTO budgetCap;

        @Size(min = 1,message = "number of industries must be at least one")
        public List<String> industries;

        @NotNull(message = "globallyExcludedPublishers can't be null,it can be empty")
        public String globallyExcludedPublishers;

        public ClientParams(String name)
        {
            this.name = name;

            this.country = "AU";

            this.exportedName = "ex1";

            this.advertiserName = "adv1";

            this.ats = "ADP Apply 2 Jobs";

            this.atsUrl = "www.google.com";

            this.frequency = "3 hours";

            this.applyConvWindow = 30;

            this.timezone = "7afb";

            this.type = "DirectEmployer";

            this.industry = "47";

            this.industries = new ArrayList<String>();

            this.industries.add(this.industry);

            this.excludedPublishers = "";

            this.startDate = LocalDate.now();

            this.endDate = LocalDate.now().plusWeeks(1);

            this.markdown = "";

            this.feeds = new ArrayList<Feeds>();

            this.sjCreate = false;

            this.budgetCap = new CapDTO(1000);

            this.globallyExcludedPublishers="";
        }

        public void addFeed (String url)
        {
            this.feeds.add(new Feeds(url));
        }

        public static class Feeds
        {
            @NotNull(message = "xmlFeedUrl can't be null")
            public String xmlFeedUrl;

            public String id;

            @Valid
            @NotNull(message = "schemaMappings can't be null")
            public SchemaMappings schemaMappings;

            @NotNull(message = "mandatoryFields can't be null")
            public Set<String> mandatoryFields;


           public Feeds(String url)
           {
               this.xmlFeedUrl = url;
               this.id = null;

               this.mandatoryFields = new HashSet<>();

               this.mandatoryFields.addAll(getMandatoryFields());

               this.schemaMappings = new SchemaMappings();
           }


           Set<String> getMandatoryFields ()
           {
               Set<String> set = new HashSet<String>();
               set.add("source");
               set.add("job");
               set.add("title");
               set.add("description");
               set.add("url");
               set.add("referencenumber");

               return set;
           }


            public static class SchemaMappings {

                @NotNull(message = "schemas can't be null")

                //<enum,String>
             public  Map<JoveoFields, String> schemas;

                @Valid
                @NotNull(message = "schemaMappingAdditional can't be null")
                public SchemaMappingAdditional schemaMappingAdditional;

                @Valid
                @NotNull(message = "schemaMappingPublisher can't be null")
                public SchemaMappingPublisher schemaMappingPublisher;

                public SchemaMappings()
                {
                    this.schemas = new HashMap<>();

                    this.schemaMappingAdditional = new  SchemaMappingAdditional();

                    this.schemaMappingPublisher = new SchemaMappingPublisher();
                }

                public static class SchemaMappingAdditional {
                    @NotNull(message = "schemas can't be null")
                    Map<String, String> additionalSchemas;

                    public SchemaMappingAdditional()
                    {
                        additionalSchemas = new HashMap<>();
                    }

                }

                public static class SchemaMappingPublisher {

                }
            }

        }

    }

}
