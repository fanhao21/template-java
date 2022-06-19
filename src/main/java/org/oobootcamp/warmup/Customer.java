package org.oobootcamp.warmup;

import java.util.Objects;

public record Customer(String identity) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return identity.equals(customer.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }
}
