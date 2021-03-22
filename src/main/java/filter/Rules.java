package filter;

import jobGroup.RuleOperator;

public class Rules {

   public RuleOperator operator;
   public String attribute;
   public String value;

    public Rules()
    {
     operator = RuleOperator.valueOf("NOT_EQUAL");
     attribute = "Category";
     value = "Sales";
    }

    @Override
    public String toString()
    {
        return "{"+attribute+" "+ operator+" "+value+"}";
    }

}
