package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    String port;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) { //埋雷
        try {
            int i = paymentService.create(payment);
            if (i == 1) {  //成功
                log.debug("保存成功：" + payment);
                return new CommonResult<Payment>(200, "插入数据库成功", payment);
            } else {
                log.debug("保存失败：" + payment);
                return new CommonResult<Payment>(444, "插入数据库失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("系统异常：" + e.getMessage());
            return new CommonResult<Payment>(999, "系统异常");
        }


    }

    @RequestMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        System.out.println("端口：" + port);
        try {
            Payment payment = paymentService.getPaymentById(id);
            if(payment == null){
                log.debug("查询失败：" + id);
                return new CommonResult<Payment>(404, "数据不存在",payment);
            }else{
                log.debug("查询成功：" + id);
                return new CommonResult<Payment>(200, "查询成功",payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("系统异常：" + e.getMessage());
            return new CommonResult<Payment>(999, "系统异常");
        }
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return port;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();} //单位秒
        return port;
    }


}

