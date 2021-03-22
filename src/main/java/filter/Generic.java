package filter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data // Lombok
public class Generic {



    //public String id;
   public Operator operator;

   @ToString.Exclude
  public List <Object> rules;

   public Generic()
   {
       rules = new ArrayList<>();
    //   this.id = UUID.randomUUID().toString();
   }

 /*  @Override
   public String toString()
   {
       String rslt ="[";
       for(int i=0;i<rules.size();i++)
           rslt= rslt+""+ rules.get(i).toString();

       rslt+="]";
       return "Operator= "+operator+" Rules "+rslt;
   }*/

}
