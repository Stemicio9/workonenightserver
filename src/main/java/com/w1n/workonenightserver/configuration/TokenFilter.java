package com.w1n.workonenightserver.configuration;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.w1n.workonenightserver.configuration.utils.CustomPrincipal;
import com.w1n.workonenightserver.configuration.utils.SecurityUtils;
import com.w1n.workonenightserver.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private UtenteService utenteService;

    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        log.error("STO PER CONFRONTARE IL PATH: " + path);
        log.error("SONO UGUALI? " + path.contains("notifiche/notificaprova"));
        if ( path.contains("notifiche/notificaprova")) {
            filterChain.doFilter(request, response);
            return;
        }

        String idToken = securityUtils.getTokenFromRequest(request);
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            log.error("Firebase Exception {}", e.getLocalizedMessage());
        } catch(Exception e){

        }
        if (decodedToken != null) {
            CustomPrincipal customPrincipal = new CustomPrincipal();
            customPrincipal.setUid(decodedToken.getUid());
            utenteService.updatetoken(idToken, decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    customPrincipal, decodedToken, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}