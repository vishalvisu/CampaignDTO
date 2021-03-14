package client.clientModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertTrue;

public class CapDTO {

    public int value;

    public boolean pacing;

    public Frequency frequency;

    public CapDTO(int value)
    {
        this.value=value;
        this.pacing=true;
        this.frequency= Frequency.Monthly;
    }




    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setPacing(boolean pacing) {
        this.pacing = pacing;
    }

    public boolean getPacing() {
        return this.pacing;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return this.frequency;
    }
}
