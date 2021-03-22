package filter;

public class Rules {

   public Operator operator;
   public String attribute;
   public String value;

    public Rules()
    {
     operator = Operator.ANY;
     attribute = "Category";
     value = "Sales";
    }

    @Override
    public String toString()
    {
        return attribute+" = "+value;
    }

}
