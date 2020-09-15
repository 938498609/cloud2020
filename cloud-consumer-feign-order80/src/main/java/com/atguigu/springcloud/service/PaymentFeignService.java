package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @RequestMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment);
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB();
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
