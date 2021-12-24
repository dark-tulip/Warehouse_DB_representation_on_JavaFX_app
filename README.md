# Warehouse DB represented on application, using JDBC, JavaFX

By purpose to learn Java.

Gets data from SQL Server, and shows the data on JavaFX application


1) Запустите скрипт для создания БД локально в SSMS
2) Смотрите чтобы TCP порт был открыт и прослушивался https://stackoverflow.com/questions/1518823/how-to-find-the-port-for-ms-sql-server-2008 
(```netstat -a```)
3) Протокол шифрования TLS при запуске может конфликтовать, желательно используйте SQL Server 2019
4) IDE: Intellij IDEA 2021.3
5) Брандмауэр Windows также может блокировать соединение, создайте правило для (обычно 1433) порта
6) Используется Windows Аутентификация 
