DROP TABLE IF EXISTS sale_items;
CREATE TABLE sale_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
       sale_id BIGINT NOT NULL,
    inventory_item_id BIGINT NOT NULL,
    quantity DECIMAL(10,2) NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,
    sub_total DECIMAL(12,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (inventory_item_id) REFERENCES inventory_items(id)  

   

);
