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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAuthenticationProvider;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class AttributesCaptureOpenIDAuthenticationProvider extends OpenIDAuthenticationProvider {

    private OpenIDAttributes2UserDetails attributes2UserDetails;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Authentication createSuccessfulAuthentication(final UserDetails userDetails,
            final OpenIDAuthenticationToken auth) {

        UserDetails details = this.attributes2UserDetails.extract(auth);
        OpenIDAuthenticationToken openIDAuthenticationToken = new OpenIDAuthenticationToken(details,
                details.getAuthorities(), auth.getIdentityUrl(), auth.getAttributes());
        openIDAuthenticationToken
                .setAuthenticated(openIDAuthenticationToken.getStatus() == OpenIDAuthenticationStatus.SUCCESS);
        SecurityContextHolder.getContext().setAuthentication(openIDAuthenticationToken);

        return super.createSuccessfulAuthentication(details, auth);
    }

    public void setAttributes2UserDetails(final OpenIDAttributes2UserDetails attributes2UserDetails) {
        this.attributes2UserDetails = attributes2UserDetails;
    }

}
