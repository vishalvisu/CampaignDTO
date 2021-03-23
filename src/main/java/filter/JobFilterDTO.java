package filter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Data


public class JobFilterDTO {


   public GroupOperator groupOperator;

   public List <Rules> rules;

    public List<JobFilterDTO> jobFilterDTOS;

    public JobFilterDTO(GroupOperator operator)
    {
        groupOperator = operator;

        rules = new ArrayList<>();
        jobFilterDTOS = new ArrayList<>();
    }




    public GroupOperator getOperator()
    {
        return groupOperator;
    }

    public List<Rules> getRules()
    {
        return rules;
    }

    public List<JobFilterDTO> getJobFilters()
    {
        return jobFilterDTOS;
    }


}



