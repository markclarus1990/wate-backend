DROP TABLE IF EXISTS inventory_movements;
CREATE TABLE inventory_movements (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_by BIGINT NOT NULL,
    inventory_item_id BIGINT NOT NULL,
    movement_type_id BIGINT NOT NULL,
    quantity decimal(10,2) NOT NULL,
    reference_type_id BIGINT NOT NULL,
    reference_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id),
    FOREIGN KEY (inventory_item_id) REFERENCES inventory_items(id),
    FOREIGN KEY (movement_type_id) REFERENCES movement_type(id)

);
