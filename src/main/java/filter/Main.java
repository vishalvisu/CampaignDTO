package filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public JobFilter jobFilter;
    public ObjectMapper objectMapper;

    public Main()
    {
        jobFilter = new JobFilter();
        jobFilter.addFilter();
    }






    public Object base() throws JsonProcessingException {


        return dtO(jobFilter);
    }

  public Object dtO (JobFilter filter) throws JsonProcessingException {

        Object tmp = null;
       Generic generic = new Generic();

        generic.operator = filter.getOperator();

      generic.rules.addAll(filter.getRules());


   //   System.out.println(generic.toString());
       for(JobFilter filters : filter.jobFilters) {

           tmp = dtO(filters);
               generic.rules.add(tmp);

       }

       return generic;
   }




}
