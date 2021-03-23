package jobGroup;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CapDTO {

    public Boolean pacing;

    public Frequency freq;

    @Max(value = 100,message = "threshold can't be greater than 100")
    @Min(value = 0,message = "threshold can't be less than 0")
    public int threshold;


    public int value;

    public CapDTO()
    {
        pacing = false;
        freq = Frequency.Daily;

        threshold = 80;
        value = 20000;
    }

    public  CapDTO (Boolean pacing,Frequency frequency, int threshold,int value)
    {
        this.pacing = pacing;
        this.freq = frequency;
        this.threshold = threshold;
        this.value = value;
    }

    public void setCap(Boolean pacing,Frequency frequency, int threshold,int value)
    {
        this.pacing = pacing;
        this.freq = frequency;
        this.threshold = threshold;
        this.value = value;
    }



}
