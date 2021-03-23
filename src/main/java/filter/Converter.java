package filter;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Converter {


    public Converter()
    {

    }








  public Object dtO (JobFilterDTO filter) throws JsonProcessingException {

        Object tmp = null;
       JobFilter jobFilter = new JobFilter();

        jobFilter.groupOperator = filter.getOperator();

      jobFilter.rules.addAll(filter.getRules());

       for(JobFilterDTO filters : filter.jobFilterDTOS) {

           tmp = dtO(filters);
               jobFilter.rules.add(tmp);

       }

       return jobFilter;
   }


}
