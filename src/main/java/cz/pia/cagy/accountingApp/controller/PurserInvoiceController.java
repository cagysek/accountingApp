package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.model.enums.EFlashMessageType;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Controller for manage invoices
 * Accessed only for role purser except invoice overview and invoice detail
 */
@Controller
public class PurserInvoiceController extends BaseController
{
    /**
     * Default redirect
     */
    private final String DEFAULT_REDIRECT = "redirect:/invoice-list";

    private InvoiceService invoiceService;

    private CompanyService companyService;

    /**
     * Instantiates a new Purser invoice controller.
     *
     * @param invoiceService the invoice service
     * @param companyService the company service
     */
    @Autowired
    public PurserInvoiceController(InvoiceService invoiceService,
                                   CompanyService companyService
    )
    {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
    }

    /**
     * Shows all invoices
     *
     * @return the model and view
     */
    @GetMapping(value = "/invoice-list")
    public ModelAndView invoiceList()
    {
        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceList");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());

        return modelAndView;
    }

    /**
     * Shows form for add invoice
     *
     * @return the model and view
     */
    @GetMapping(value = "/purser/invoice-add")
    public ModelAndView invoiceAdd()
    {
        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceAdd");
        ModelMap modelMap = modelAndView.getModelMap();
        // modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());
        modelMap.addAttribute("companiesList", this.companyService.getCompanies());
        modelMap.addAttribute("invoice", new Invoice());
        modelMap.addAttribute("formUrl", "/purser/invoice-add");
        modelMap.addAttribute("formSubmit", "Vytvořit");

        return modelAndView;
    }

    /**
     * Handle invoice add form
     *
     * @param invoice       the invoice
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/purser/invoice-add")
    public String invoiceAddSave(@Valid Invoice invoice, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return "purser/invoice/invoiceAdd";
        }

        long id = this.invoiceService.saveInvoice(invoice);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Faktura byla úspěšně vytvořena.");

        return "redirect:/invoice-detail?invoice-id=" + id;
    }

    /**
     * Shows invoice edit form
     *
     * @param invoiceId the invoice id
     * @param atts      the atts
     * @return the model and view
     */
    @GetMapping(value = "/purser/invoice-edit")
    public ModelAndView invoiceEdit(@RequestParam(value = "invoice-id") Long invoiceId, RedirectAttributes atts)
    {
        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);

        // check if invoice is cancelled
        // cancelled invoice can not be changed
        if (invoice.isStorno())
        {
            atts.addFlashAttribute("error", "Stornovanou faktruru nelze editovat");

            return new ModelAndView(this.DEFAULT_REDIRECT);
        }

        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceEdit");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoicesList", this.invoiceService.getInvoices());
        modelMap.addAttribute("companiesList", this.companyService.getCompanies());
        modelMap.addAttribute("invoice", this.invoiceService.getInvoiceById(invoiceId));
        modelMap.addAttribute("formUrl", "/purser/invoice-edit");
        modelMap.addAttribute("formSubmit", "Uložit");

        return modelAndView;
    }

    /**
     * Handles invoice edit form
     *
     * @param invoice       the invoice
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/purser/invoice-edit")
    public String invoiceEditSave(@Valid Invoice invoice, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return "purser/invoice/invoiceEdit";
        }

        long id = this.invoiceService.saveInvoice(invoice);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Změny byly uloženy.");

        return "redirect:/invoice-detail?invoice-id=" + id;
    }

    /**
     * Handles invoice cancellation
     *
     * @param invoiceId the invoice id
     * @param atts      the atts
     * @return the string
     */
    @GetMapping(value = "/purser/invoice-delete")
    public String companyDelete(@RequestParam(value = "invoice-id") Long invoiceId, RedirectAttributes atts)
    {
        this.invoiceService.stornoInvoice(invoiceId);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Faktura č. " + invoiceId + " byla stornována.");

        return this.DEFAULT_REDIRECT;
    }

    /**
     * Shows invoice detail
     *
     * @param invoiceId the invoice id
     * @param atts      the atts
     * @return the model and view
     */
    @GetMapping(value = "/invoice-detail")
    public ModelAndView invoiceDetail(@RequestParam(value = "invoice-id") Long invoiceId, RedirectAttributes atts)
    {
        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);

        if (invoice == null)
        {
            atts.addFlashAttribute(EFlashMessageType.ERROR.toString(), "Faktura nebyla nalezena.");
            return new ModelAndView(this.DEFAULT_REDIRECT);
        }

        ModelAndView modelAndView = new ModelAndView("purser/invoice/invoiceDetail");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("invoice", invoice);

        return modelAndView;
    }
}
