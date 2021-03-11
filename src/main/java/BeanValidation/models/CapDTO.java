package BeanValidation.models;

public class CapDTO {
    private int value;

    private boolean pacing;

    private Frequency frequency;

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

    public void setFreq(Frequency freq) {
        this.frequency = freq;
    }

    public Frequency getFreq() {
        return this.frequency;
    }
}
