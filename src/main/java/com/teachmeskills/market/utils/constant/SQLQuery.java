package com.teachmeskills.market.utils.constant;

public interface SQLQuery {
    String INSERT_INTO_IS_SAVE_USER = "INSERT INTO public.users (firstname, second_name, age, email, sex, telephone_number, created, updated, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String INSERT_INTO_IS_SAVE_SECURITY = "INSERT INTO public.security (login, password, role, created, updated, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    String INSERT_INTO_IS_SAVE_PRODUCT = "INSERT INTO public.product (name, price, created, updated) VALUES (?, ?, ?, ?)";
}
