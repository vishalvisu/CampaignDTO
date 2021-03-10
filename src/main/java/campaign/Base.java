package campaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDate;


public class Base {

    public static void main (String [] args)  {

        String name = "camp6", clientId = "87956473-218d-4068-9190-3fa02c861ff9";

        CampaignDTO campaignDTO = new CampaignDTO(name, clientId);

        //over-ride the Default Budget
        campaignDTO.setBudget(12000,true,Frequency.Daily);

        //over-ride the start date
        campaignDTO.setStartDate(LocalDate.now().plusMonths(2));

        //over-ride the end date
        campaignDTO.setEndDate(LocalDate.now().plusYears(2));

        
        System.out.println(campaignDTO.getParams().getStartDate());
        System.out.println(campaignDTO.getParams().getEndDate());

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());;


        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);


        try {
            String jsonStr = objectMapper.writeValueAsString(campaignDTO);
           System.out.println(jsonStr);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

}
