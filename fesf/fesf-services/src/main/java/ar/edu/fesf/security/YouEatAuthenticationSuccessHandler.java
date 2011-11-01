/**
 * Copyright 2011 the original author or authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package ar.edu.fesf.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 
 * Custom {@link AuthenticationSuccessHandler} that create a new {@link OpenIDAuthenticationToken} that contains as a
 * principal an instance of {@link UserDetailsImpl} populated with "firstName", "lastName", "email" extracted from the
 * available OpenIDAttribute. The new OpenIDAuthenticationToken is set on the {@link SecurityContextHolder} overriding
 * the previous one.
 * 
 * @author Alessandro Vincelli
 * 
 */
public class YouEatAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements
        AuthenticationSuccessHandler {

    private OpenIDAttributes2UserDetails attributes2UserDetails;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {
        OpenIDAuthenticationToken auth = (OpenIDAuthenticationToken) authentication;
        UserDetails details = this.attributes2UserDetails.extract((OpenIDAuthenticationToken) authentication);
        OpenIDAuthenticationToken openIDAuthenticationToken = new OpenIDAuthenticationToken(details,
                details.getAuthorities(), auth.getIdentityUrl(), auth.getAttributes());
        SecurityContextHolder.getContext().setAuthentication(openIDAuthenticationToken);
        this.handle(request, response, authentication);
    }

    public void setAttributes2UserDetails(final OpenIDAttributes2UserDetails attributes2UserDetails) {
        this.attributes2UserDetails = attributes2UserDetails;
    }

    public OpenIDAttributes2UserDetails getAttributes2UserDetails() {
        return this.attributes2UserDetails;
    }

}
