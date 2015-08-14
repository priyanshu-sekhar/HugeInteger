package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HugeController {

    @RequestMapping(value="/model", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("model", new HugeModel());
        return "model";
    }

    @RequestMapping(value="/model", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute HugeModel greeting, Model model) {
        model.addAttribute("model", greeting);
        return "result";
    }

    
}