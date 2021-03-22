package filter;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class JobFilters {

    @JsonBackReference
    public JobFilter jobFilter;

    public JobFilters() {
        jobFilter = new JobFilter();
    }
}

