package filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    public static int index=0;

   // public Generic generic;
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

    }

    public void base() throws JsonProcessingException {

        jobFilter.addFilter();
      //  jobFilter.addFilter();
      //  jobFilter.addFilter();

     //   System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobFilter));
       System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Filter(dtO(jobFilter))));

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
          // System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(generic));
              // System.out.println(generic.rules.toString());
       }

       return generic;
   }



  public JobFilter getNextFilter(JobFilter filter)
    {
        System.out.println(index);
        if(index >= filter.getJobFilters().size()) {
            index = 0;
            return null;
        }
        else
        return  filter.getJobFilters().get(index++);
    }
}
