package com.mycompany.vgtu.domain.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.domain.lecture.VoteService;
import com.mycompany.vgtu.domain.lecture.internal.CategoryDao;
import com.mycompany.vgtu.domain.lecture.internal.CategoryDaoImpl;
import com.mycompany.vgtu.domain.lecture.internal.CategoryServiceImpl;
import com.mycompany.vgtu.domain.lecture.internal.LectureDao;
import com.mycompany.vgtu.domain.lecture.internal.LectureDaoImpl;
import com.mycompany.vgtu.domain.lecture.internal.LectureServiceImpl;
import com.mycompany.vgtu.domain.lecture.internal.VoteDao;
import com.mycompany.vgtu.domain.lecture.internal.VoteDaoImpl;
import com.mycompany.vgtu.domain.lecture.internal.VoteServiceImpl;
import com.mycompany.vgtu.domain.security.PasswordService;
import com.mycompany.vgtu.domain.user.internal.UserDao;
import com.mycompany.vgtu.domain.user.internal.UserDaoImpl;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;
import com.mycompany.vgtu.domain.security.internal.MySecurityRealm;
import com.mycompany.vgtu.domain.security.internal.PasswordServiceImpl;
import com.mycompany.vgtu.domain.security.internal.ShiroAuthenticationServiceImpl;
import com.mycompany.vgtu.domain.security.internal.ShiroAuthorizationServiceImpl;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.domain.user.internal.UserServiceImpl;
import org.apache.shiro.realm.Realm;

@Singleton
public class DomainModule extends AbstractModule {

    @Override
    protected void configure() {
        bindDao();
        bindSecurity();
        bindService();
    }

    private void bindSecurity() {
        bind(Realm.class).to(MySecurityRealm.class);
        bind(ShiroAuthenticationService.class).to(ShiroAuthenticationServiceImpl.class);
        bind(ShiroAuthorizationService.class).to(ShiroAuthorizationServiceImpl.class);
        bind(PasswordService.class).to(PasswordServiceImpl.class);

    }

    private void bindDao() {
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(CategoryDao.class).to(CategoryDaoImpl.class);
        bind(LectureDao.class).to(LectureDaoImpl.class);
        bind(VoteDao.class).to(VoteDaoImpl.class);
    }

    private void bindService() {
        bind(UserService.class).to(UserServiceImpl.class);
        bind(LectureService.class).to(LectureServiceImpl.class);
        bind(CategoryService.class).to(CategoryServiceImpl.class);
        bind(VoteService.class).to(VoteServiceImpl.class);
    }
}
