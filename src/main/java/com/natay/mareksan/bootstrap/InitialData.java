package com.natay.mareksan.bootstrap;

import com.natay.mareksan.model.*;
import com.natay.mareksan.repository.RoleRepository;
import com.natay.mareksan.service.CustomerService;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

   /* @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    // Implemente edilen ApplicationListener interface'i , spring context ayaga kalktıgında cagrılan bir arayüzdür.
    // alttaki onApplicationEvent methodunu data eklemek için kullanıyoruz.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
/*
      roleRepository.save(new Role(1,"ADMIN"));
      roleRepository.save(new Role(2,"CUSTOMER"));

      Customer customer = new Customer("Müşteri 1","2124556655","Fatih/İstanbul",
              "Ali","5555555","sirket@gmail.com","123456",1);
      Customer customer1 = new Customer("Müşteri Şirketi 2","2124556655","Fatih/İstanbul",
              "Veli","5555555","sirke2t@gmail.com","123456",1);
      customerService.saveCustomer(customer);
      customerService.saveAdmin(customer1);

      Order order = new Order("Sipariş 3","28/11/2018","30/11/2018",4,"acil iş",130,100,
              50,"iş çok acil ",OrderStatus.IN_PROGRESS.getValue(),OrderType.ADDITION.getValue(),customer,true);

      Order order1 =new Order("Sipariş 3","22/11/2018","30/11/2018",12,"acil iş",350,100,
              10,"iş iş tanımı",OrderStatus.PREPAIRING.getValue(),OrderType.BILL.getValue(),customer1,true);


      Order order2 = new Order("Sipariş 3","15/10/2018","30/11/2018",7,"acil iş",2000,100,
              22,"iş çok acil , iş tanımı",OrderStatus.DONE.getValue(),OrderType.BROCHURE.getValue(),customer,true);


      Order order3 = new Order("Sipariş 3","28/11/2018","30/11/2018",2,"acil iş",1350,100,
              150,"iş çok acil , iş tanımı", OrderStatus.IN_PROGRESS.getValue(), OrderType.BUSINESS_CARD.getValue(),customer1,true);


      orderService.saveOrder(order);
      orderService.saveOrder(order1);
      orderService.saveOrder(order2);
      orderService.saveOrder(order3);
*/
    }
}

