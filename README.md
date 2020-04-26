# ContactsAppRoomDB
# Описание

На старте приложение получает массив контактов из нескольких источников: источник1, источник2, источник3 и сохраняет их локально. Для получения прямой ссылки на json нажать View Raw при просмотре.
В процессе загрузки контактов на экране отображается круговой ProgressBar:

<img src="https://github.com/StasonicK/ContactsAppRoomDB/blob/develop/read%20me/load_data.jpeg" width="256">  *Загрузка контактов*
<img src="https://github.com/StasonicK/ContactsAppRoomDB/blob/develop/read%20me/show_data.jpeg" width="256">  *Обновление контактов*

Контакт человека имеет следующий вид

* id (string) — ID контакта
* name (string) — Имя человека
* height (float) — Рост человека
* biography (string) — Биография человека
* temperament (enum) — Темперамент человека (melancholic, phlegmatic, sanguine, choleric)
* educationPeriod (object) — Период прохождения учебы. Состоит из дат start и end.

Если приложение запускается не в первый раз, и с момента прошлой загрузки данных прошло более 1 минуты, то данные необходимо загрузить заново, иначе нужно показать данные, сохраненные локально.

Список контактов можно обновить свайпом вниз. Если при загрузке или обновлении данных происходит ошибка, то нужно показать ее с помощью Snackbar.

## Обновление контактов

Поиск среди контактов происходит по имени или номеру телефона. Результаты поиска появляются по мере ввода символов в строку поиска и могут отображаться в основном списке. При клике на контакт открывается экран с более подробной информацией. Клик по номеру телефона открывает стандартный набор номера.
<img src="https://github.com/StasonicK/ContactsAppRoomDB/blob/develop/read%20me/show_query.jpeg" width="256">*Поиск по контактам*
<img src="https://github.com/StasonicK/ContactsAppRoomDB/blob/develop/read%20me/show_contact.jpeg" width="256">*Описание контакта*

## Цвета интерфейса
* colorPrimary — #4c9069
* colorPrimaryDark — #3d7254
* colorAccent — #4d8f69
* Черный текст — #e5000000
* Серый текст — #8a000000
* Кликабельный текст — #269df7
