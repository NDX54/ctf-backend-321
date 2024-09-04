package com.csit321.ctfbackend.core.utilities;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.stream.Stream;

public class TeamIdGenerator implements IdentifierGenerator, Configurable {

    private String prefix = "TEAM";

    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(o.getClass().getName(), o).getIdentifierPropertyName(),
                o.getClass().getSimpleName());
        Stream ids = session.createQuery(query).stream();
        return null;
    }

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
    }
}
