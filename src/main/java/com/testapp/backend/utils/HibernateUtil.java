package com.testapp.backend.utils;

import com.testapp.backend.models.Roles;
import com.testapp.backend.models.User;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class HibernateUtil {

    private final List<Class> list = new ArrayList<>(Arrays.asList(new Class[]{
         User.class
    }));


    private SessionFactory sessionFactory;

    public HibernateUtil(){
        File nf = new File(Constants.RESOURCE_PATH);
        Configuration configuration = new Configuration().configure(nf);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
        MetadataSources metadataSources =
                new MetadataSources(registry);
        for (Class cl : list) {
            metadataSources.addAnnotatedClass(cl);
        }
        sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
    }

}
