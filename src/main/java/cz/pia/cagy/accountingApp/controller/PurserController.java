package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PurserController extends BaseController
{

    private final String DEFAULT_REDIRECT = "redirect:/purser/companies-list";

    private CompanyService companyService;

    @Autowired
    public PurserController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @GetMapping(value = "/purser/companies-list")
    public ModelAndView companiesList()
    {
        ModelAndView modelAndView = new ModelAndView("purser/companiesList");

        modelAndView.getModelMap().addAttribute("companiesList", this.companyService.getCompanies());

        return modelAndView;
    }

    @GetMapping(value = "/purser/company-edit")
    public ModelAndView companyEdit(@RequestParam(value = "company-id", required = true) Long companyId)
    {
        ModelAndView modelAndView = new ModelAndView("purser/companyEdit");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("company", this.companyService.getCompanyById(companyId));
        modelMap.addAttribute("formUrl", "/purser/company-edit");
        modelMap.addAttribute("formSubmit", "Uložit");


        return modelAndView;
    }

    @PostMapping(value = "/purser/company-edit")
    public String companyEditSave(@Valid Company company, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "purser/companyEdit";
        }

        this.companyService.saveCompany(company);

        return this.DEFAULT_REDIRECT;
    }

    @GetMapping(value = "/purser/company-add")
    public ModelAndView companyAdd()
    {
        ModelAndView modelAndView = new ModelAndView("purser/companyAdd");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("company", new Company());
        modelMap.addAttribute("formUrl", "/purser/company-add");
        modelMap.addAttribute("formSubmit", "Vytvořit");

        return modelAndView;
    }

    @PostMapping(value = "/purser/company-add")
    public String companyAddSave(@Valid Company company, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "purser/companyAdd";
        }

        this.companyService.saveCompany(company);

        return this.DEFAULT_REDIRECT;
    }

    @GetMapping(value = "/admin/company-delete")
    public String companyDelete(@RequestParam(value = "company-id", required = true) Long companyId)
    {
        this.companyService.deleteCompanyById(companyId);
        return this.DEFAULT_REDIRECT;
    }


}
