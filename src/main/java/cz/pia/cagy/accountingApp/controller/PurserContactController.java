package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.model.enums.EFlashMessageType;
import cz.pia.cagy.accountingApp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Controller for manage contact
 * Accessed only for role purser
 */
@Controller
public class PurserContactController extends BaseController
{
    /**
     * Default redirection
     */
    private final String DEFAULT_REDIRECT = "redirect:/purser/companies-list";

    private CompanyService companyService;

    /**
     * Instantiates a new Purser contact controller.
     *
     * @param companyService the company service
     */
    @Autowired
    public PurserContactController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    /**
     * Shows all companies in contacts
     *
     * @return the model and view
     */
    @GetMapping(value = "/purser/companies-list")
    public ModelAndView companiesList()
    {
        ModelAndView modelAndView = new ModelAndView("purser/contact/companyList");

        modelAndView.getModelMap().addAttribute("companiesList", this.companyService.getCompanies());

        return modelAndView;
    }

    /**
     * Shows company edit form
     *
     * @param companyId the company to show
     * @param atts      the atts
     * @return the model and view
     */
    @GetMapping(value = "/purser/company-edit")
    public ModelAndView companyEdit(@RequestParam(value = "company-id") Long companyId, RedirectAttributes atts)
    {
        Company company = this.companyService.getCompanyById(companyId);

        // check if company exists in db
        if (company == null)
        {
            atts.addFlashAttribute(EFlashMessageType.ERROR.toString(), "Kontakt nebyl nalezen.");
            return new ModelAndView(this.DEFAULT_REDIRECT);
        }

        ModelAndView modelAndView = new ModelAndView("purser/contact/companyEdit");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("company", company);
        modelMap.addAttribute("formUrl", "/purser/company-edit");
        modelMap.addAttribute("formSubmit", "Uložit");


        return modelAndView;
    }

    /**
     * Handle company edit form
     *
     * @param company       the company
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/purser/company-edit")
    public String companyEditSave(@Valid Company company, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return "purser/contact/companyEdit";
        }

        this.companyService.saveCompany(company);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Změny byly uloženy.");

        return this.DEFAULT_REDIRECT;
    }

    /**
     * Shows form for add new company to contacts
     *
     * @return the model and view
     */
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

    /**
     * Handles company add form
     *
     * @param company       the company
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/purser/company-add")
    public String companyAddSave(@Valid Company company, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return "purser/constact/companyAdd";
        }

        this.companyService.saveCompany(company);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Kontakt byl vytvořen.");

        return this.DEFAULT_REDIRECT;
    }

    /**
     * Handles company delete from contacts list
     *
     * @param companyId the company id
     * @param atts      the atts
     * @return the string
     */
    @GetMapping(value = "/purser/company-delete")
    public String companyDelete(@RequestParam(value = "company-id") Long companyId, RedirectAttributes atts)
    {
        this.companyService.deleteCompanyById(companyId);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Kontakt byl smazán.");

        return this.DEFAULT_REDIRECT;
    }


}
