package com.bank.credit.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("bkalika");
    }
}
