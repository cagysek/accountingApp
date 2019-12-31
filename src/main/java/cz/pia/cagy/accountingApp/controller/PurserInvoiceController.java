package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.service.CompanyService;
import cz.pia.cagy.accountingApp.service.InvoiceService;
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
public class PurserInvoiceController extends BaseController
{
    private final String DEFAULT_REDIRECT = "redirect:/purser/invoice-list";
    private InvoiceService invoiceService;
    private CompanyService companyService;

    @Autowired
    public PurserInvoiceController(InvoiceService invoiceService, CompanyService companyService)
    {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
    }

    @GetMapping(value = "/purser/invoice-list")
    public ModelAndView invoiceList()
    {
        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceList");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());

        return modelAndView;
    }

    @GetMapping(value = "/purser/invoice-add")
    public ModelAndView invoiceAdd()
    {
        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceAdd");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());
        modelMap.addAttribute("companiesList", this.companyService.getCompanies());
        modelMap.addAttribute("invoice", new Invoice());
        modelMap.addAttribute("formUrl", "/purser/invoice-add");
        modelMap.addAttribute("formSubmit", "Vytvořit");

        return modelAndView;
    }

    @PostMapping(value = "/purser/invoice-add")
    public String invoiceAddSave(@Valid Invoice invoice, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "purser/invoice/invoiceAdd";
        }

        this.invoiceService.saveInvoice(invoice);

        return this.DEFAULT_REDIRECT;
    }

    @GetMapping(value = "/purser/invoice-edit")
    public ModelAndView invoiceEdit(@RequestParam(value = "invoice-id", required = true) Long invoiceId)
    {
        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceEdit");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());
        modelMap.addAttribute("companiesList", this.companyService.getCompanies());
        modelMap.addAttribute("invoice", this.invoiceService.getInvoiceById(invoiceId));
        modelMap.addAttribute("formUrl", "/purser/invoice-edit");
        modelMap.addAttribute("formSubmit", "Uložit");

        return modelAndView;
    }

    @PostMapping(value = "/purser/invoice-edit")
    public String invoiceEditSave(@Valid Invoice invoice, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "purser/invoice/invoiceEdit";
        }
        this.invoiceService.saveInvoice(invoice);

        return this.DEFAULT_REDIRECT;
    }

    @GetMapping(value = "/purser/invoice-delete")
    public String companyDelete(@RequestParam(value = "invoice-id", required = true) Long invoiceId)
    {
        this.invoiceService.deleteInvoiceById(invoiceId);
        return this.DEFAULT_REDIRECT;
    }
}
