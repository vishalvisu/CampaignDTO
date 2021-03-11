package BeanValidation;

import BeanValidation.models.CampaignDTO;
import com.sun.tools.classfile.Opcode;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.transform.Source;
import javax.validation.Validator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class Base {



    public static void main(String [] args) throws IOException, SAXException {
      CampaignDTO campaignDTO = new CampaignDTO("cId");

       campaignDTO.setName("sdk-test");
       campaignDTO.setBudget(1000);

        campaignDTO.setStartDate(LocalDate.now().plusDays(8));
       campaignDTO.setEndDate(LocalDate.now().plusWeeks(2));


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator =  factory.getValidator();

      Set<ConstraintViolation<CampaignDTO>> constraintViolations=   validator.validate(campaignDTO);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<CampaignDTO> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }
    }


}
