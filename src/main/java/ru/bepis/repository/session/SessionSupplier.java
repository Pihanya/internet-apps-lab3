package ru.bepis.repository.session;

import org.hibernate.Session;

public interface SessionSupplier {
    Session supplySession();
}
