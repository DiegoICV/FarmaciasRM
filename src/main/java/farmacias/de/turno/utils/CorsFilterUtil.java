package farmacias.de.turno.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Como la api de origen (farmanet.minsal.cl) presenta bloqueo de CORS,
 *  las peticiones deben realizarse mediante un filtro que establece las cabeceras
 *  genericas para el Http response
 */
@Component
public class CorsFilterUtil implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}


    @Bean(name = "corsFilterWeb")
    public org.springframework.web.filter.CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        List<String> list = new ArrayList<>();
        list.add("Access-Control-Allow-Origin");
        list.add("Access-Control-Allow-Methods");
        config.setAllowedHeaders(list);
        source.registerCorsConfiguration("/v3/api-docs", config);
        org.springframework.web.filter.CorsFilter corsFilter = new org.springframework.web.filter.CorsFilter(source);
        return corsFilter;
    }
}