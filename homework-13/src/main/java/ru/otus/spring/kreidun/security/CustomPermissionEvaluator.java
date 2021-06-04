package ru.otus.spring.kreidun.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    //private final AclService aclService;
    private final MutableAclService aclService;
    private ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();
    private ObjectIdentityGenerator objectIdentityGenerator = new ObjectIdentityRetrievalStrategyImpl();
    private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();
    private PermissionFactory permissionFactory = new DefaultPermissionFactory();
    private final Log logger = LogFactory.getLog(this.getClass());

    public CustomPermissionEvaluator(MutableAclService aclService) {
        this.aclService = aclService;
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        ObjectIdentity objectIdentity = this.objectIdentityRetrievalStrategy.getObjectIdentity(targetDomainObject);
        return this.checkPermission(auth, objectIdentity, permission);
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        ObjectIdentity objectIdentity = this.objectIdentityRetrievalStrategy.getObjectIdentity(targetType);
        return this.checkPermission(auth, objectIdentity, permission);

    }

    private boolean checkPermission(Authentication authentication, ObjectIdentity oid, Object permission) {
        List<Sid> sids = this.sidRetrievalStrategy.getSids(authentication);
        List<Permission> requiredPermission = this.resolvePermission(permission);
        boolean debug = this.logger.isDebugEnabled();
        if (debug) {
            this.logger.debug("Checking permission '" + permission + "' for object '" + oid + "'");
        }

        try {
            Acl acl = this.aclService.readAclById(oid, sids);
            if (acl.isGranted(requiredPermission, sids, false)) {
                if (debug) {
                    this.logger.debug("Access is granted");
                }

                return true;
            }

            if (debug) {
                this.logger.debug("Returning false - ACLs returned, but insufficient permissions for this principal");
            }
        } catch (NotFoundException var8) {
            //если нет записи, то считаем, что разрешено
            return true;
        }

        return false;
    }

    List<Permission> resolvePermission(Object permission) {
        if (permission instanceof Integer) {
            return Arrays.asList(this.permissionFactory.buildFromMask((Integer) permission));
        } else if (permission instanceof Permission) {
            return Arrays.asList((Permission) permission);
        } else if (permission instanceof Permission[]) {
            return Arrays.asList((Permission[]) ((Permission[]) permission));
        } else {
            if (permission instanceof String) {
                String permString = (String) permission;

                Permission p;
                try {
                    p = this.permissionFactory.buildFromName(permString);
                } catch (IllegalArgumentException var5) {
                    p = this.permissionFactory.buildFromName(permString.toUpperCase(Locale.ENGLISH));
                }

                if (p != null) {
                    return Arrays.asList(p);
                }
            }

            throw new IllegalArgumentException("Unsupported permission: " + permission);
        }
    }

}
