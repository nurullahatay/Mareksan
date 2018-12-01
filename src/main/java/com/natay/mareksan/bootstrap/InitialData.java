package com.natay.mareksan.bootstrap;

import com.natay.mareksan.model.*;
import com.natay.mareksan.service.CustomerService;
import com.natay.mareksan.service.OrderService;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    // Implemente edilen ApplicationListener interface'i , spring context ayaga kalktıgında cagrılan bir arayüzdür.
    // alttaki onApplicationEvent methodunu data eklemek için kullanıyoruz.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        User user = new User("Nurullah","Atay", "123","5355422108","nataymachine@gmail.com");
        User user1 = new User("Ramazan","Demir", "123","5308711257","rdemir057@gmail.com");
        userService.saveUser(user);
        userService.saveUser(user1);

        Customer customer = new Customer("Müşteri 1","2124556655","Fatih/İstanbul",
                "Ali","5555555","sirket@gmail.com","123");


        Customer customer1 = new Customer("Müşteri Şirketi 2","2124556655","Fatih/İstanbul",
                "Veli","5555555","sirke2t@gmail.com","123");

        customerService.saveCustomer(customer);
        customerService.saveCustomer(customer1);

        Order order = new Order("Sipariş 3","28/11/2018","30/11/2018",4,"acil iş",130,100,
                50,"iş çok acil ",OrderStatus.IN_PROGRESS.getValue(),OrderType.ADDITION.getValue(),customer);

        Order order1 =new Order("Sipariş 3","22/11/2018","30/11/2018",12,"acil iş",350,100,
                10,"iş iş tanımı",OrderStatus.PREPAIRING.getValue(),OrderType.BILL.getValue(),customer1);


        Order order2 = new Order("Sipariş 3","15/10/2018","30/11/2018",7,"acil iş",2000,100,
                22,"iş çok acil , iş tanımı",OrderStatus.DONE.getValue(),OrderType.BROCHURE.getValue(),customer);


        Order order3 = new Order("Sipariş 3","28/11/2018","30/11/2018",2,"acil iş",1350,100,
                150,"iş çok acil , iş tanımı",OrderStatus.IN_PROGRESS.getValue(),OrderType.BUSINESS_CARD.getValue(),customer1);


        orderService.saveOrder(order);
        orderService.saveOrder(order1);
        orderService.saveOrder(order2);
        orderService.saveOrder(order3);

    }
}

