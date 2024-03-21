CREATE TABLE IF NOT EXISTS installments (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id       UUID NOT NULL,
    total_amount    DECIMAL(10, 2) NOT NULL,
    job_description TEXT NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS parcels (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    installment_id  UUID NOT NULL,
    amount          DECIMAL(10,2) NOT NULL,
    pay_date        DATE NOT NULL,
    is_payed        BOOLEAN,

    CONSTRAINT fk_installment FOREIGN KEY (installment_id) REFERENCES installments(id)
);