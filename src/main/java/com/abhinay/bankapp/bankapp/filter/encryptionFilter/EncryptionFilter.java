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
//import java.util.Set;
//
//
//@Component
//public class EncryptionFilter extends OncePerRequestFilter {
//
//    private static final Set<String> EXCLUDE_PREFIXES = Set.of("/h2-console", "/swagger-ui", "/v3/api-docs");
//    private static final Set<String> EXCLUDE_EXTENSIONS = Set.of(".css", ".js", ".png", ".jpg", ".svg", ".ico", ".woff", ".woff2");
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//        if (shouldSkip(path)) {
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
//    private boolean shouldSkip(String uri) {
//        if (uri == null) return false;
//        for (String p : EXCLUDE_PREFIXES) {
//            if (uri.startsWith(p)) return true;
//        }
//        for (String ext : EXCLUDE_EXTENSIONS) {
//            if (uri.endsWith(ext)) return true;
//        }
//        return false;
//    }
//}
