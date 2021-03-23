package filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.management.MXBean;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

public class Rules<T> {

   public RuleOperator operator;
   public String field;
   public T data;



    public Rules(RuleOperator ruleOperator,String field, T data)
    {
      this.operator = ruleOperator;
      this.field = field;
      this.data = data;
    }

    @Pattern(regexp = "true",message = "Rule is Invalid, value is InValid with respect to Operator")
    public String getRulesValidation()
    {
        System.out.println("rules");
            String dataType = data.getClass().getSimpleName();

            if (operator == RuleOperator.IN || operator == RuleOperator.NOT_IN) {
                if (!dataType.equals("String"))
                    return "true";
                return "false";
            } else {
                if (dataType.equals("String"))
                    return "true";
                return "false";
            }

    }



    @Override
    public String toString()
    {
        return "{"+field+" "+ operator+" "+data+"}";
    }

}
