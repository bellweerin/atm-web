package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.CustomerService;


@Controller
@RequestMapping("/login")
public class LoginController {
    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    CustomerService  customerService;


        @PostMapping
        public String login(@ModelAttribute Customer customer, Model model){
            //1.check to see if ID and PIN matched customer info
            Customer matchingCustomer = customerService.checkPin(customer);
            //2.if match, welcome customer.
            if(matchingCustomer != null){
                model.addAttribute("greeting","Welcome, "+matchingCustomer.getName());
            }
            //3.if not match, display that customer info is incorrect.
            else{
                model.addAttribute("greeting", "Welcome can't find customer");
            }
            return "home";
        }



        @GetMapping
        public String getLoginPage() {
            return "login";   // return login.html
        }
}
