package com.code.hmc.controller;

import com.code.hmc.model.*;
import com.code.hmc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller

public class HomeController {
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

    @ModelAttribute("producers")
    public Iterable<Producer> Producer() {
        return iProducerService.findAll();
    }
    @ModelAttribute("categories")
    public Iterable<Category> Categories() {
        return iCategoryService.findAll();
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("home/home");
        Iterable<Product>products = iProductService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
        public ModelAndView list(@RequestParam("s") Optional<String> s, Pageable pageable, HttpSession session){
            Page<Product> products;
            if (s.isPresent()) {
                ModelAndView modelAndView = new ModelAndView("home/data-tables");
                products = iProductService.findAllNameContaining(s.get(), pageable);
                modelAndView.addObject("products", products);
                if(session.getAttribute("totalMoney")!=null&&session.getAttribute("quantityBookInCart")!=null){
                    modelAndView.addObject("totalMoney",session.getAttribute("totalMoney"));
                    modelAndView.addObject("quantityBookInCart",session.getAttribute("quantityBookInCart"));
                }
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("home/data-tables");
                products = iProductService.findAll(pageable);
                if(session.getAttribute("totalMoney")!=null&&session.getAttribute("quantityBookInCart")!=null){
                    modelAndView.addObject("totalMoney",session.getAttribute("totalMoney"));
                    modelAndView.addObject("quantityBookInCart",session.getAttribute("quantityBookInCart"));
                }
                modelAndView.addObject("products", products);
                return modelAndView;
            }

    }
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String manage(Model model){
        Iterable<Order>orders = iOrderService.findAll();
        model.addAttribute("orders",orders);
        return "dashboard/index";
    }
    @RequestMapping(value = "/orderDetailO/{id}",method = RequestMethod.GET)
    public String listOrder(@PathVariable("id")long id,Model model){
        Iterable<OrderDetailDB>orderDetailDBS = orderDetailDBService.findAllByOrderId(id);
        Order order = iOrderService.findById(id);
        model.addAttribute("orderDetail",orderDetailDBS);
        model.addAttribute("order",order);
        return "dashboard/orderDetail";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "dashboard/login";
    }
}
