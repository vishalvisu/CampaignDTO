package cap;

import BeanValidation.models.Frequency;

public class CapDTO {

    public boolean pacing;

    public Frequency freq;

    public int threshold;

    public int value;

    public CapDTO()
    {
        pacing = true;
        freq = Frequency.Monthly;

        threshold = 80;
        value = 2000;
    }
}
