package com.abhinay.bankapp.bankapp.filter.decryptionFilter;

import com.abhinay.bankapp.bankapp.util.AESUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class DecryptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String encryptedBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            String decryptedBody = AESUtil.decrypt(encryptedBody);
            DecryptedRequestWrapper decryptedRequest = new DecryptedRequestWrapper(request, decryptedBody);
            filterChain.doFilter(decryptedRequest, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to decrypt request");
        }
    }
}

