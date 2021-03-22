package filter;

import jobGroup.RuleOperator;

public class Rules<T> {

   public RuleOperator operator;
   public String attribute;
   public T value;

    public Rules(T value)
    {
     operator = RuleOperator.valueOf("NOT_EQUAL");
     attribute = "Category";
     this.value = value;
    }

    @Override
    public String toString()
    {
        return "{"+attribute+" "+ operator+" "+value+"}";
    }

}
