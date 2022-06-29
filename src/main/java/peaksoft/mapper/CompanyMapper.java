package peaksoft.mapper;

import org.springframework.stereotype.Component;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.model.Company;

@Component
public class CompanyMapper {

    public Company create(CompanyRequest dto) {
        if(dto == null){
            return null;
        }

        Company company = new Company();

        company.setCompanyName(dto.getCompanyName());
        company.setLocatedCountry(dto.getLocatedCountry());

        return company;
    }

}
