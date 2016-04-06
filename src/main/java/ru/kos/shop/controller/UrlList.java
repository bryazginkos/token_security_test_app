package ru.kos.shop.controller;

/**
 * Created by Константин on 06.04.2016.
 */
public final class UrlList {

    /**
     * Версия API
     */
    private static final String API_VERSION = "1.0";

    /**
     * URL префикс
     */
    public static final String PREFIX = "/api/" + API_VERSION;

    /**
     * POST - добавление продукта в базу
     * PUT / {id} - обновление продукта
     * DELETE - удаление продукта
     * GET - получить список продуктов
     */
    public static final String PRODUCTS = "/products";

    /**
     * GET / {id} - положить продукт в корзину
     */
    public static final String TO_BASKET = "/basket";

    /**
     * POST - создать заказ
     * GET - просмотреть список заказов за промежуток
     */
    public static final String ORDERS = "/orders";


    /**
     * Получение токена по логину и пароля
     */
    public static final String TOKEN = "/token";

    private UrlList() {
    }
}
