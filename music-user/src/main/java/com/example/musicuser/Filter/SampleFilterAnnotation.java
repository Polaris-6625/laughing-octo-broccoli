package com.example.musicuser.Filter;

import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;
import java.util.logging.Filter;

//@Order(2)
//@WebFilter(filterName = "sampleFilterAnnotation" , urlPatterns = ("/ceshi"))
@WebFilter(urlPatterns = {"/*"})
public class SampleFilterAnnotation extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截器来咯~~~~~");
        chain.doFilter(request,response);
    }
}
