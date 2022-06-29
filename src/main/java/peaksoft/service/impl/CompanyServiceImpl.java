package peaksoft.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.Response;
import peaksoft.exseptions.BadRequestException;
import peaksoft.exseptions.NotFoundException;
import peaksoft.mapper.CompanyMapper;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Response saveCompany(CompanyRequest companyRequest) {
        String companyName = companyRequest.getCompanyName();

        checkCompanyName(companyName);

        Company company = companyMapper.create(companyRequest);

        Company saveCompany = companyRepository.save(company);
        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("company with companyName = %s successfully registered",
                        saveCompany.getCompanyName()))
                .build();
    }

    private void checkCompanyName(String companyName) {
        boolean exists = companyRepository.existsByCompanyName(companyName);
        if (exists) {
            throw new BadRequestException(
                    "company with companyName = " + companyName + " already exists"
            );
        }
    }

    @Override
    public Response deleteById(Long id) {

        companyRepository.deleteById(id);

        String message = String.format("Company with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("company with id = %s does not exists", id)
                    );
                });
    }

    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public Response updateById(Long id, CompanyRequest newCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
            throw new NotFoundException(
                    String.format("company with id = %s does not exists", id)
            );
        });

        String companyName = company.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();
        if (!Objects.equals(companyName, newCompanyName)) {
            company.setCompanyName(newCompanyName);
        }

        String locatedCountry = company.getLocatedCountry();
        String newLocatedCountry = newCompany.getLocatedCountry();
        if (!Objects.equals(locatedCountry, newLocatedCountry)) {
            company.setLocatedCountry(newLocatedCountry);
        }

        String message = String.format("Company with companyId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}
