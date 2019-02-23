package com.elong.comment.comment.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CommentStrategy extends PhysicalNamingStrategyStandardImpl {

    private static final long serialVersionUID = 1383021413247872469L;


    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalTableName(name, context);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String columnName = name.getText();
        return name.toIdentifier(columnName.toUpperCase().charAt(0) + columnName.substring(1));
    }

}