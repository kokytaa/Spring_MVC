package ma.enset.spring_mvc.web;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.enset.spring_mvc.entities.Product;
import ma.enset.spring_mvc.repsoitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/index")
    //@PreAuthorize("hasRole('USER')")
    public String index(Model model) {
       List<Product> products = productRepository.findAll();
        model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
    @GetMapping("/delete")

    public String delete(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/index";
    }


    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    } @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) return "new-product";
        productRepository.save(product);
        return "redirect:/newProduct";
    }
    /*    @PreAuthorize("hasRole('ADMIN')")

    @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }*/

}
