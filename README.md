# Тестовая задача - Консольное приложение "Телефонный справочник"
Простое консольное приложение - телефонный справочник
Контакты хранятся в файле, по умолчанию - src/main/resources/phonebook.txt.
Также есть возможность указать аргументом имя файла с расположением и расширением (прим. _files/phones.txt_)

Управление спрвочником осуществляется из командной строки, возможности с примерами:
- Создание контакта с номером телефона +79999999999, именем Ivan и фамилией Petrov:<br> 
`create +79999999999 Ivan Petrov`
- Удаление контакта с номером телефона +79999999999: <br>
`remove +79999999999`
- Найти по имени или фамилии равному Ivan (регистр важен, Ivan и ivan - это разные значения) <br>
`find Ivan`
- Вывести все контакты
`all`

Команда регистронезависима (можно вводить как create, так и CREATE)

Приложение намеренно упрощено в целях тестового примера и не соблюдает некоторые принципы разработки, например, используется паттерн синглтон, который нарушает принцип Single Responsibility, также не реализует Inversion Of Control, что позволило бы избавиться от сильной связи в коде, которая здесь есть (один из запахов плохого кода)  в части того, что при добавлении новой команды и CommandExecutor для неё необходимо в классе Start этот executor также добавить в коллекцию, так как для соблюдения в этой части необходимо было бы написать некий менеджер объектов, что, считаю, избыточно для тестового примера. Однако реализация при этом частично соблюдает Open Closed Principle в том смысле, что при добавлении новой команды достаточно будет добавить CommandExecutor

