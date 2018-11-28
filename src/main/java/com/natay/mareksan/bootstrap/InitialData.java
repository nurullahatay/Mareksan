package com.natay.mareksan.bootstrap;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderStatus;
import com.natay.mareksan.model.User;
import com.natay.mareksan.service.CustomerService;
import com.natay.mareksan.service.OrderService;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Ramazan on 28.11.2018.
 */
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

        Order order = new Order("Sipariş 1", new Date(),new Date(),
                "",2,"ehe",230D,10D,5D,"", OrderStatus.PREPAIRING,customer);
        Order order1 = new Order("Sipariş 2", new Date(),new Date(),
                "",2,"eh2e",230D,10D,5D,"", OrderStatus.DONE,customer);

        Order order2 = new Order("Sipariş 3", new Date(),new Date(),
                "",2,"ehasdasdsad2e",230D,10D,5D,"", OrderStatus.IN_PROGRESS,customer1);


        orderService.saveOrder(order);
        orderService.saveOrder(order1);
        orderService.saveOrder(order2);

    }
}
