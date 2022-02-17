package ru.team.scheduler.oapi.constants;

public class SwaggerConstant {
    public static final String SECURITY_REFERENCE = "Token Access Bearer Example: \\\"Bearer {token}\\\"\"";
    public static final String AUTHORIZATION_DESCRIPTION = "Full API Permission";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_SCOPE = "Unlimited";
    public static final String CONTACT_EMAIL = "spprtscheduler@gmail.com";
    public static final String CONTACT_URL = "https://www.ya.ru";
    public static final String CONTACT_NAME = "Scheduler API Support";
    public static final String API_TITLE = "Scheduler Open API";
    public static final String API_DESCRIPTION = ""+
            "<h3>Приложение <a target='_blank' style='font-size: 20px;' href=\"http://localhost:8189/sh/api/v1/auth/login\">Sheduler</a> для составления/корректировки расписания индивидуальных занятий(уроков) преподавателей/тренеров с учениками/клиентами.</h3> </br>" +
            "<b>На примере учеников муз школы</b> : у преподавателя есть временной интервал, когда он работает, в начале года допустим все ученики выбирают удобные для них интервалы времени.</br>" +
            "Затем если выбранные интервалы не накладываются друг на друга, то это время автоматически закрепляется за учеником.</br>" +
            "В противном случае, преподаватель сам корректирует накладки, возможно самим доработать алгоритм для оптимизации составления этого расписания. </br>" +
            "<b>А после утверждения расписания, сделать возможным следующее:</b></br>" +
            "Если ученик заболел/пропускает занятие, то он в приложении уведомляет об этом - а в освободившееся окно могут записаться другие. </br>" +
            "Также если кто-то хочет время поменять , то в приложении всех учеников можно оповестить - вдруг кто-то может поменяться.</br> " +
            "<b>Обычно эта работа отнимает много времени у педагогов , которые ведут индивидуальные занятия.<b>";
    public static final String TERM_OF_SERVICE = "term of service";
    public static final String API_VERSION = "1.0";
    public static final String LICENSE = "Apache License 2.1.0";
    public static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    public static final String SECURE_PATH = "/*/.*";
    public static final String API_AUTH = "Authorization Service";
    public static final String API_USER = "Пользователи";
    public static final String API_ROLE = "Роли";
    public static final String API_DISCIPLINE = "Дисциплина";
}
