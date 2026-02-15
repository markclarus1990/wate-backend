DROP TABLE IF EXISTS prices;
CREATE TABLE prices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_by BIGINT NOT NULL,
    inventory_item_id BIGINT NOT NULL,
    price decimal(12,2) NOT NULL,
    effective_from TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id),
    FOREIGN KEY (inventory_item_id) REFERENCES inventory_items(id)

);
