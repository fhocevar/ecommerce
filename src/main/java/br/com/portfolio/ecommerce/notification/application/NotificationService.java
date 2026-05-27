package br.com.portfolio.ecommerce.notification.application;
import org.springframework.stereotype.Service;
@Service public class NotificationService { public void notifyCustomer(String message){ System.out.println("NOTIFICATION: "+message); } }
