package com.memorynotfound.ldap;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class TesteAttributeMapper implements AttributesMapper {
    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
     TesteDTO teste = new TesteDTO();
        String commonName = (String)attributes.get("cn").get();
        if(commonName != null)
            teste.setCommonName(commonName);
        String lastName = (String)attributes.get("sn").get();
        if(lastName != null)
            teste.setLastName(lastName);
        Attribute description = attributes.get("description");
        if(description != null)
            teste.setDescription((String)description.get());
        return teste;
    }
}
