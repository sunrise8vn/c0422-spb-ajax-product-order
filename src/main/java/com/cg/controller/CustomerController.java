package com.cg.controller;


import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.LocationRegionDTO;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customer/list");

//        List<CustomerDTO> customerDTOS = new ArrayList<>();
//
//        List<Customer> customers = customerService.findAll();
//
//        for (Customer customer : customers) {
//            LocationRegion locationRegion = customer.getLocationRegion();
//
//            LocationRegionDTO locationRegionDTO = locationRegion.toLocationRegionDTO();
//
//            CustomerDTO customerDTO = customer.toCustomerDTO(locationRegionDTO);
//
//            customerDTOS.add(customerDTO);
//        }
//
//        modelAndView.addObject("customers", customerDTOS);

        return modelAndView;
    }
}
