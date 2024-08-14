package com.company.taskmanager.models.task;

/**
 * Перечисление, представляющее возможные статусы задачи.
 */
public enum Status {

    /**
     * Задача еще не запущена.
     */
    NOT_LAUNCH,

    /**
     * Задача в процессе выполнения.
     */
    IN_PROCESS,

    /**
     * Задача завершена.
     */
    DONE
}