package filter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Data // Lombok

public class JobFilter {


   public GroupOperator groupOperator;

    @Valid
  public List <Object> rules;

   public JobFilter()
   {
       rules = new ArrayList<>();
   }



}
