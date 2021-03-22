package filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.*;

public class Main {

    public static int index=0;

    public Generic generic;
    public JobFilter jobFilter;
    public ObjectMapper objectMapper;

    public Main()
    {
        jobFilter = new JobFilter();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());;
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

    public static void main(String [] args) throws JsonProcessingException {
        Main main1 = new Main();


       main1.base();



       // System.out.println( objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobFilter));

    //    System.out.println(jobFilter.rules.get(0).toString());


      //  String str  =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(main1.dtO(jobFilter));


    }

    public void base() throws JsonProcessingException {


      //  jobFilter.jobFilters.add(new JobFilter());
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtO(jobFilter)));

    }

  public Object dtO (JobFilter filters) {

        if(filters==null)
            return null;

        generic = new Generic();

        generic.operator = filters.operator;

        generic.rules.addAll(filters.rules);

      Object tmp = dtO(this.getNextFilter(filters));

        if(tmp!=null)
        generic.rules.add(tmp);

         return generic;
   }



  public   JobFilter getNextFilter(JobFilter filter)
    {
        System.out.println(index);
        if(index >= filter.jobFilters.size()) {
            index = 0;
            return null;
        }
        else
        return  filter.jobFilters.get(index++);
    }

}
