package filter;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class JobFilter {

   public String id;
   public Operator operator;
   public List <Rules> rules;

    public List<JobFilter> jobFilters;

    public JobFilter()
    {
        operator = Operator.AND;
        rules = new ArrayList<>();

        rules.add(new Rules());
       // rules.add(new Rules());

        jobFilters = new ArrayList<>();

        id = UUID.randomUUID().toString();

    }

    public Operator getOperator()
    {
        return operator;
    }

    public List<Rules> getRules()
    {
        return rules;
    }

    public List<JobFilter> getJobFilters()
    {
        return jobFilters;
    }

    public void addFilter() {

        JobFilter jobFilter = new JobFilter();
        jobFilter.jobFilters.add(new JobFilter());
        jobFilters.add(jobFilter);

    }
}



