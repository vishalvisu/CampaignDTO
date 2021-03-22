package jobGroup;

public enum RuleOperator {
    EQUAL,
    NOT_EQUAL("NOT EQUAL");

    RuleOperator(String s) {
    }

    RuleOperator() {

    }

    @Override
    public String toString() {
        return (name().replaceAll("_", " "));
    }
    }
