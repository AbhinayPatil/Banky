//// java
//package com.abhinay.bankapp.bankapp.filter.decryptionFilter;
//
//import com.abhinay.bankapp.bankapp.util.AESUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class DecryptionFilter extends OncePerRequestFilter {
//
//    private static final Set<String> EXCLUDE_PREFIXES = Set.of("/h2-console", "/swagger-ui", "/v3/api-docs");
//    private static final Set<String> EXCLUDE_EXTENSIONS = Set.of(".css", ".js", ".png", ".jpg", ".svg", ".ico", ".woff", ".woff2");
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        if (shouldSkip(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String encryptedBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        if (encryptedBody == null || encryptedBody.isBlank()) {
//            // nothing to decrypt, pass through
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            String decryptedBody = AESUtil.decrypt(encryptedBody);
//            DecryptedRequestWrapper decryptedRequest = new DecryptedRequestWrapper(request, decryptedBody);
//            filterChain.doFilter(decryptedRequest, response);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("Failed to decrypt request");
//        }
//    }
//
//    private boolean shouldSkip(HttpServletRequest request) {
//        String uri = request.getRequestURI();
//        String servletPath = request.getServletPath();
//        String pathInfo = request.getPathInfo();
//        String method = request.getMethod();
//
//        if (uri == null && servletPath == null && pathInfo == null) return false;
//
//        // skip H2 console paths explicitly
//        if ((uri != null && uri.contains("/h2-console")) ||
//                (servletPath != null && servletPath.contains("/h2-console")) ||
//                (pathInfo != null && pathInfo.contains("/h2-console"))) {
//            return true;
//        }
//
//        // skip common prefixes
//        for (String p : EXCLUDE_PREFIXES) {
//            if ((uri != null && uri.startsWith(p)) || (servletPath != null && servletPath.startsWith(p))) {
//                return true;
//            }
//        }
//
//        // skip static assets by extension
//        for (String ext : EXCLUDE_EXTENSIONS) {
//            if (uri != null && uri.endsWith(ext)) return true;
//        }
//
//        // skip GET (typically H2 console pages and static requests)
//        if ("GET".equalsIgnoreCase(method)) return true;
//
//        // skip if no body is expected
//        int contentLength = request.getContentLength();
//        if (contentLength == 0) return true;
//
//        return false;
//    }
//}
