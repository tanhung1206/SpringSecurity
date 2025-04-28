package tan.hung.demo1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class TenantFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headers = request.getHeader("X-TENANT-ID");
        if (headers != null && headers.contains("admin")) {
            chain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("DENIED");
        }
    }
}
