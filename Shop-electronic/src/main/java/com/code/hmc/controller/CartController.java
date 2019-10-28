package com.code.hmc.controller;

import com.code.hmc.model.*;
import com.code.hmc.service.*;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
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
    @Autowired
    ICustomerService customerService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IStatusService iStatusService;

    @ModelAttribute("statuss")
    public Iterable<Status> listStatus() {
        return iStatusService.findAll();
    }

    @ModelAttribute("categories")
    public Iterable<Category> Categories() {
        return iCategoryService.findAll();
    }

    @ModelAttribute("producers")
    public Iterable<Producer> Producer() {
        return iProducerService.findAll();
    }

    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.POST)
    public String showCart(@PathVariable("id") long id, @RequestParam("quantity") int quantity,
                           HttpSession session, Model model) {
        if (quantity > 0) {
            double totalMoney = 0;
            Product product = iProductService.findById(id);
            if (session.getAttribute("order") == null) {
                int quantityBookInCart = quantity;
                OrderDetail order = new OrderDetail();
                List<IteamInCart> listIteam = new ArrayList<>();
                IteamInCart iteam = new IteamInCart();
                iteam.setQuantity(quantity);
                iteam.setProduct(product);
                iteam.setPrice(product.getPrice());
                listIteam.add(iteam);
                order.setIteam(listIteam);
                session.setAttribute("order", order);
                totalMoney = product.getPrice();
                session.setAttribute("quantityBookInCart", quantityBookInCart);
                model.addAttribute("quantityBookInCart", quantityBookInCart);
            } else {
                OrderDetail order = (OrderDetail) session.getAttribute("order");
                List<IteamInCart> listIteam = order.getIteam();
                boolean check = false;
                for (IteamInCart iteam : listIteam) {
                    if (iteam.getProduct().getId() == product.getId()) {
                        iteam.setQuantity(quantity);
                        check = true;
                    }
                }
                if (check == false) {
                    IteamInCart iteam = new IteamInCart();
                    iteam.setQuantity(quantity);
                    iteam.setProduct(product);
                    iteam.setPrice(product.getPrice());
                    listIteam.add(iteam);
                }
                int quantityBookInCart = 0;
                for (IteamInCart iteam : listIteam) {
                    quantityBookInCart += iteam.getQuantity();
                    totalMoney += (iteam.getProduct().getPrice()) * (iteam.getQuantity());
                }
                session.setAttribute("order", order);
                session.setAttribute("quantityBookInCart", quantityBookInCart);
                model.addAttribute("quantityBookInCart", quantityBookInCart);
            }

            session.setAttribute("totalMoney", totalMoney);
            model.addAttribute("totalMoney", totalMoney);
            OrderDetail order = (OrderDetail) session.getAttribute("order");
            model.addAttribute("order", order);
            Iterable<Product> products;
            products = iProductService.findAll();
            model.addAttribute("product1", product);
            model.addAttribute("products", products);
            return "redirect:/list-product";
        } else {
            model.addAttribute("error", "Quantity error");
            return "error/error";
        }


    }

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String showCart(HttpSession session, Model model) {
        OrderDetail order = (OrderDetail) session.getAttribute("order");
        model.addAttribute("totalMoney", session.getAttribute("totalMoney"));
        model.addAttribute("quantityBookInCart", session.getAttribute("quantityBookInCart"));
        model.addAttribute("order", order);
        return "cart/showCart";
    }

    @RequestMapping(value = "/clear-cart", method = RequestMethod.GET)
    public String deleteCart(HttpSession session, Model model) {
        session.removeAttribute("order");
        session.removeAttribute("quantityBookInCart");
        session.removeAttribute("totalMoney");
        return "redirect:/list-product";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String showForm(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
        OrderDetail order = (OrderDetail) session.getAttribute("order");
        model.addAttribute("totalMoney", session.getAttribute("totalMoney"));
        model.addAttribute("quantityBookInCart", session.getAttribute("quantityBookInCart"));
        model.addAttribute("order", order);
        return "order/order";
    }

    @RequestMapping(value = "/create-order", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
        customerService.save(customer);

        Order orderPayDB = new Order();
        OrderDetail order = (OrderDetail) session.getAttribute("order");
        orderPayDB.setTotalMoney((Double) session.getAttribute("totalMoney"));
        orderPayDB.setCustomer(customer);
        orderPayDB.setStatus(iStatusService.findById((long) 1));
        orderService.save(orderPayDB);
        for (IteamInCart iteam : order.getIteam()) {
            OrderDetailDB orderDetailDB = new OrderDetailDB();
            orderDetailDB.setProduct_id(iteam.getProduct().getId());
            orderDetailDB.setAmount(iteam.getQuantity());
            orderDetailDB.setPrice(iteam.getProduct().getPrice());
            orderDetailDB.setTotalMoney((iteam.getQuantity()) * (iteam.getProduct().getPrice()));
            orderDetailDB.setOrder(orderPayDB);
            orderDetailDBService.save(orderDetailDB);
        }
        session.removeAttribute("order");
        session.removeAttribute("quantityBookInCart");
        session.removeAttribute("totalMoney");
        return "order/success";
    }

    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    public String manage(Model model){
        Iterable<Order>orders = iOrderService.findAll();
        model.addAttribute("orders",orders);
        return "dashboard/dashboard-sales";
    }

    @RequestMapping(value = "/editOrder/{id}", method = RequestMethod.GET)
    public String showOrderDetail(@PathVariable("id") Long id,@ModelAttribute("order") Order order, Model model) {
        Order order1 = orderService.findById(id);
        model.addAttribute("order", order1);
        return "dashboard/editOrder";
    }
    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Long id) {
        orderDetailDBService.remove(id);
        return "redirect:/orderList";
    }
    @PostMapping("/editOrder")
    public String editOrder(@ModelAttribute("order") Order order,Model model) {
        Order order1 = new Order();
        order1.setId(order.getId());
        order1.setCustomer(orderService.findById(order.getId()).getCustomer());
        order1.setTotalMoney(orderService.findById(order.getId()).getTotalMoney());
        order1.setStatus(order.getStatus());
        orderService.save(order1);
      return "redirect:/admin";
    }

}
