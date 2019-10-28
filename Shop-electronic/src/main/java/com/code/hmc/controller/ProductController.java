package com.code.hmc.controller;

import com.code.hmc.model.*;
import com.code.hmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    IProductService iProductService;
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    IProducerService iProducerService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IOrderDetailDBService orderDetailDBService;

    @ModelAttribute("categories")
    public Iterable<Category> Categories() {
        return iCategoryService.findAll();
    }

    @ModelAttribute("producers")
    public Iterable<Producer> Producer() {
        return iProducerService.findAll();
    }

    private static String UPLOAD_LOCATION =
            "C:\\Users\\DELL\\IdeaProjects\\JAVA_WBD\\SPRING_MVC\\project_session\\src\\main\\webapp\\WEB-INF\\css\\images\\";

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("product") ProductUploadFile product) {
        return "create/createProduct";
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    public String createProduct(@Validated @ModelAttribute("product") ProductUploadFile product, BindingResult result) {
        new ProductUploadFile().validate(product, result);
        if (result.hasFieldErrors()) {
            return "create/createProduct";
        } else {
            MultipartFile file = product.getFile();
            String path = UPLOAD_LOCATION + file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String pathFile = "/css/images/" + file.getOriginalFilename();
            Product product1 = new Product();
            product1.setCategory(product.getCategory());
            product1.setUrlPicture(pathFile);
            product1.setDescription(product.getDescription());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setProducer(product.getProducer());
            product1.setVote(0);
            iProductService.save(product1);
            return "create/createProduct";
        }
    }

    @RequestMapping(value = "/list-product", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("s") Optional<String> s, Pageable pageable, HttpSession session) {
        Page<Product> products;

        if (s.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("home/home");
            products = iProductService.findAllNameContaining(s.get(), pageable);
            modelAndView.addObject("products", products);
            if (session.getAttribute("totalMoney") != null && session.getAttribute("quantityBookInCart") != null) {
                modelAndView.addObject("totalMoney", session.getAttribute("totalMoney"));
                modelAndView.addObject("quantityBookInCart", session.getAttribute("quantityBookInCart"));
                OrderDetail order = (OrderDetail) session.getAttribute("order");
                modelAndView.addObject("order", order);
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("home/home");
            products = iProductService.findAll(pageable);
            if (session.getAttribute("totalMoney") != null && session.getAttribute("quantityBookInCart") != null) {
                modelAndView.addObject("totalMoney", session.getAttribute("totalMoney"));
                modelAndView.addObject("quantityBookInCart", session.getAttribute("quantityBookInCart"));
            }
            modelAndView.addObject("products", products);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/viewsProduct/{id}", method = RequestMethod.GET)
    public String showDetail(@ModelAttribute("product") Product product,
                             @PathVariable("id") long id, Model model,
                             HttpSession session) {
        Iterable<Product> products;
        products = iProductService.findAll();
        Product product1 = iProductService.findById(id);
        if (session.getAttribute("totalMoney") != null && session.getAttribute("quantityBookInCart") != null) {
            model.addAttribute("totalMoney", session.getAttribute("totalMoney"));
            model.addAttribute("quantityBookInCart", session.getAttribute("quantityBookInCart"));

        }
        model.addAttribute("product1", product1);
        model.addAttribute("products", products);
        return "/detail/product";
         }



    @RequestMapping(value = "/editProduct/{id}",method = RequestMethod.GET)
    public ModelAndView editForm(@ModelAttribute("product") ProductUploadFile product,@PathVariable("id") long id){
        Product product1 = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit/product");
        product.setId(product1.getId());
        product.setPrice(product1.getPrice());
        product.setName(product1.getName());
        product.setCategory(product1.getCategory());
        product.setDescription(product1.getDescription());
        product.setProducer(product1.getProducer());
        product.setVote(product1.getVote());
        modelAndView.addObject("product1",product1);
        return modelAndView;
    }
    @RequestMapping(value = "/editProduct",method = RequestMethod.POST)
    public String editProduct(@Validated @ModelAttribute("product") ProductUploadFile product, BindingResult result) {
        new ProductUploadFile().validate(product, result);
        if (result.hasFieldErrors()) {
            return "edit/product";
        } else {
            MultipartFile file = product.getFile();
            String path = UPLOAD_LOCATION + file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String pathFile = "/css/images/" + file.getOriginalFilename();
            Product product1 = new Product();
            product1.setId(product.getId());
            product1.setCategory(product.getCategory());
            product1.setUrlPicture(pathFile);
            product1.setDescription(product.getDescription());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setProducer(product.getProducer());
            product1.setVote(product.getVote());
            iProductService.save(product1);
            return "create/createProduct";
        }
     }

    @RequestMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id")long id,Model model){
        iProductService.remove(id);
        model.addAttribute("message","Delete product success");
        return "redirect:/listProduct";
    }
}

