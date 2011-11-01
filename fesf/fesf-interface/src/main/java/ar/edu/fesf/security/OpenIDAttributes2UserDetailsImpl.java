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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class OpenIDAttributes2UserDetailsImpl implements OpenIDAttributes2UserDetails {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails extract(final OpenIDAuthenticationToken token) {
        String email = "";
        String firstName = "";
        String lastName = "";
        String language = "";
        List<OpenIDAttribute> attributes = token.getAttributes();
        for (OpenIDAttribute openIDAttribute : attributes) {
            if (openIDAttribute.getName().equals("firstName")) {
                firstName = StringUtils.join(openIDAttribute.getValues(), "");
            }

            if (openIDAttribute.getName().equals("email")) {
                email = StringUtils.join(openIDAttribute.getValues(), "");
            }

            if (openIDAttribute.getName().equals("lastName")) {
                lastName = StringUtils.join(openIDAttribute.getValues(), "");
            }

            if (openIDAttribute.getName().equals("language")) {
                language = StringUtils.join(openIDAttribute.getValues(), "");
            }
        }
        return new UserDetailsImpl(token.getIdentityUrl(), firstName, lastName, email, language);
    }

}
