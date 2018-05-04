package com.memorynotfound.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;
import java.util.List;

@Service
public class PersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    public void getAllPersonNames() throws NamingException {


        Attribute attr = new BasicAttribute("description", "Fernando");
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes("uid=john,ou=people,dc=memorynotfound,dc=com", new ModificationItem[] {item});


    }

    public List findByUsername(String username) {

        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("uid",username));
        return ldapTemplate.search("", andFilter.encode(),new TesteAttributeMapper());
    }


}
