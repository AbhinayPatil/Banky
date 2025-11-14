//package com.abhinay.bankapp.bankapp.filter.encryptionFilter;
//
//import com.abhinay.bankapp.bankapp.util.AESUtil;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//
//@Component
//public class EncryptionFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//        if (path.startsWith("/h2-console/**")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        ResponseWrapper responseWrapper = new ResponseWrapper(response);
//
//        filterChain.doFilter(request, responseWrapper);
//
//        byte[] originalResponse = responseWrapper.getCapturedResponseBody().getBytes();
//        String plainResponse = new String(originalResponse, StandardCharsets.UTF_8);
//
//        String encryptedResponse = null;
//        try {
//            encryptedResponse = AESUtil.encrypt(plainResponse);
//            System.out.println("Encrypted Response: " + encryptedResponse);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        // Clear the original response buffer
//        response.resetBuffer();
//
//        // Set correct content type and length
//        response.setContentType("text/plain");
//        response.setContentLength(encryptedResponse.getBytes(StandardCharsets.UTF_8).length);
//
//        // Write encrypted response
//        response.getWriter().write(encryptedResponse);
//
//    }
//}
