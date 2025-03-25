open module baseDados {
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires org.hibernate.orm.core;
    exports baseDados.entity;
    exports baseDados.bll;
    exports baseDados;
}