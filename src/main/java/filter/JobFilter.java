package filter;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;


public class JobFilter {

   public Operator operator;
   public List <Rules> rules;
    public List<JobFilter> jobFilters;


    public JobFilter()
    {
        operator = Operator.AND;
        rules = new ArrayList<>();

        rules.add(new Rules());
        rules.add(new Rules());

        jobFilters = new ArrayList<>();

    }
}

