package ru.otus.spring.kreidun.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.models.Book;

import javax.transaction.Transactional;

@Service
public class AclMyServiceImpl implements AclMyService {
    private final MutableAclService aclService;

    @Autowired
    public AclMyServiceImpl(MutableAclService aclService) {
        this.aclService = aclService;
    }
    private MutableAcl getAclFor(ObjectIdentity oid) {
        MutableAcl acl;
        try {
            acl = (MutableAcl) aclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oid);
        }
        return acl;
    }

    @Transactional
    @Override
    public  void addPermission(Book book) {

        ObjectIdentity oid = new ObjectIdentityImpl(Book.class, book.getId());
        MutableAcl acl = getAclFor(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION, new GrantedAuthoritySid("ADMIN"), true);
        acl.insertAce(1, BasePermission.DELETE, new GrantedAuthoritySid("ADMIN"), true);
        acl.insertAce(2, BasePermission.READ, new GrantedAuthoritySid("USER"), true);
        aclService.updateAcl(acl);
    }
    @Transactional
    @Override
    public void deletePermission(Book book){

        ObjectIdentity oid = new ObjectIdentityImpl(Book.class, book.getId());
        MutableAcl acl = getAclFor(oid);
        aclService.deleteAcl(acl.getObjectIdentity(), true);
    }
}
